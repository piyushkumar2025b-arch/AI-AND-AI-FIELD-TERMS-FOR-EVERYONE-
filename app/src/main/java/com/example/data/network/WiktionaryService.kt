package com.example.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

data class WiktionarySummary(
    val title: String,
    val extract: String,
    val partOfSpeech: String? = null,
    val pageUrl: String,
    val language: String = "English"
)

/**
 * Service to query Wiktionary REST API for lexical grounding,
 * grammatical definitions, pronunciation, and language accuracy.
 */
class WiktionaryService {
    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    private val userAgent = "AIStudioLexicon/1.0 (Android; Educational Language & Knowledge Dictionary)"

    suspend fun fetchLexicalSummary(term: String): Result<WiktionarySummary> = withContext(Dispatchers.IO) {
        val cleanTerm = term.trim()
        if (cleanTerm.isBlank()) {
            return@withContext Result.failure(IllegalArgumentException("Term cannot be empty"))
        }

        // 1. Try Wiktionary REST summary endpoint
        val summaryResult = queryWiktionarySummary(cleanTerm)
        if (summaryResult.isSuccess) {
            return@withContext summaryResult
        }

        // 2. Try Wiktionary REST definition endpoint
        val definitionResult = queryWiktionaryDefinitions(cleanTerm)
        if (definitionResult.isSuccess) {
            return@withContext definitionResult
        }

        summaryResult
    }

    private fun queryWiktionarySummary(title: String): Result<WiktionarySummary> {
        return try {
            val encodedTitle = URLEncoder.encode(title.replace(" ", "_"), "UTF-8")
            val url = "https://en.wiktionary.org/api/rest_v1/page/summary/$encodedTitle"

            val request = Request.Builder()
                .url(url)
                .header("User-Agent", userAgent)
                .header("Accept", "application/json")
                .get()
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string().orEmpty()

            if (!response.isSuccessful) {
                return Result.failure(Exception("Wiktionary returned HTTP ${response.code}"))
            }

            val json = JSONObject(body)
            val extract = json.optString("extract").trim()
            if (extract.isBlank()) {
                return Result.failure(Exception("No Wiktionary extract available"))
            }

            val resolvedTitle = json.optString("title", title)
            val pageUrl = json.optJSONObject("content_urls")
                ?.optJSONObject("desktop")
                ?.optString("page")
                ?: "https://en.wiktionary.org/wiki/$encodedTitle"

            Result.success(
                WiktionarySummary(
                    title = resolvedTitle,
                    extract = extract,
                    partOfSpeech = json.optString("description").ifBlank { null },
                    pageUrl = pageUrl
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun queryWiktionaryDefinitions(title: String): Result<WiktionarySummary> {
        return try {
            val encodedTitle = URLEncoder.encode(title.lowercase().replace(" ", "_"), "UTF-8")
            val url = "https://en.wiktionary.org/api/rest_v1/page/definition/$encodedTitle"

            val request = Request.Builder()
                .url(url)
                .header("User-Agent", userAgent)
                .header("Accept", "application/json")
                .get()
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string().orEmpty()

            if (!response.isSuccessful) {
                return Result.failure(Exception("Wiktionary definition returned HTTP ${response.code}"))
            }

            val json = JSONObject(body)
            val enList = json.optJSONArray("en")
            if (enList == null || enList.length() == 0) {
                return Result.failure(Exception("No English definitions in Wiktionary"))
            }

            val firstEntry = enList.getJSONObject(0)
            val partOfSpeech = firstEntry.optString("partOfSpeech")
            val definitions = firstEntry.optJSONArray("definitions")
            val firstDef = if (definitions != null && definitions.length() > 0) {
                definitions.getJSONObject(0).optString("definition")
                    // strip HTML tags if any
                    .replace(Regex("<[^>]*>"), "")
                    .trim()
            } else ""

            if (firstDef.isBlank()) {
                return Result.failure(Exception("Empty definition"))
            }

            val pageUrl = "https://en.wiktionary.org/wiki/$encodedTitle"

            Result.success(
                WiktionarySummary(
                    title = title,
                    extract = firstDef,
                    partOfSpeech = partOfSpeech.ifBlank { null },
                    pageUrl = pageUrl
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
