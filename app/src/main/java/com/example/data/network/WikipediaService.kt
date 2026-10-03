package com.example.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

data class WikipediaSummary(
    val title: String,
    val extract: String,
    val description: String? = null,
    val pageUrl: String,
    val thumbnailUrl: String? = null
)

class WikipediaService {
    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    private val userAgent = "AIStudioLexicon/1.0 (Android; Educational Knowledge Dictionary)"

    suspend fun fetchSummary(term: String): Result<WikipediaSummary> = withContext(Dispatchers.IO) {
        val cleanTerm = term.trim()
        if (cleanTerm.isBlank()) {
            return@withContext Result.failure(IllegalArgumentException("Term cannot be empty"))
        }

        // Try direct summary endpoint first
        val directResult = queryDirectSummary(cleanTerm)
        if (directResult.isSuccess) {
            return@withContext directResult
        }

        // Fallback: search Wikipedia opensearch to find closest matching article
        val searchResult = searchClosestArticle(cleanTerm)
        if (searchResult.isSuccess) {
            val closestTitle = searchResult.getOrThrow()
            return@withContext queryDirectSummary(closestTitle)
        }

        directResult
    }

    private fun queryDirectSummary(title: String): Result<WikipediaSummary> {
        return try {
            val encodedTitle = URLEncoder.encode(title.replace(" ", "_"), "UTF-8")
            val url = "https://en.wikipedia.org/api/rest_v1/page/summary/$encodedTitle"

            val request = Request.Builder()
                .url(url)
                .header("User-Agent", userAgent)
                .header("Accept", "application/json")
                .get()
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string().orEmpty()

            if (!response.isSuccessful) {
                return Result.failure(Exception("Wikipedia returned HTTP ${response.code}"))
            }

            val json = JSONObject(body)
            val type = json.optString("type")
            // Ignore disambiguation pages if empty extract
            val extract = json.optString("extract").trim()
            if (extract.isBlank()) {
                return Result.failure(Exception("No extract available"))
            }

            val resolvedTitle = json.optString("title", title)
            val description = json.optString("description").ifBlank { null }
            val pageUrl = json.optJSONObject("content_urls")
                ?.optJSONObject("desktop")
                ?.optString("page")
                ?: "https://en.wikipedia.org/wiki/$encodedTitle"

            val thumbnail = json.optJSONObject("thumbnail")?.optString("source")

            Result.success(
                WikipediaSummary(
                    title = resolvedTitle,
                    extract = extract,
                    description = description,
                    pageUrl = pageUrl,
                    thumbnailUrl = thumbnail
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun searchClosestArticle(term: String): Result<String> {
        return try {
            val encoded = URLEncoder.encode(term, "UTF-8")
            val url = "https://en.wikipedia.org/w/api.php?action=opensearch&search=$encoded&limit=1&namespace=0&format=json"

            val request = Request.Builder()
                .url(url)
                .header("User-Agent", userAgent)
                .header("Accept", "application/json")
                .get()
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string().orEmpty()

            if (!response.isSuccessful) {
                return Result.failure(Exception("Wikipedia search failed: ${response.code}"))
            }

            val jsonArray = JSONArray(body)
            if (jsonArray.length() >= 2) {
                val titlesArray = jsonArray.getJSONArray(1)
                if (titlesArray.length() > 0) {
                    val firstTitle = titlesArray.getString(0)
                    if (firstTitle.isNotBlank()) {
                        return Result.success(firstTitle)
                    }
                }
            }

            Result.failure(Exception("No matching article found on Wikipedia"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
