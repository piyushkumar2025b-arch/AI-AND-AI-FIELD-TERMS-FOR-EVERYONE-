package com.example.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class OpenRouterService {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun queryWordInsights(
        apiKey: String,
        model: String,
        term: String,
        existingMeaning: String
    ): Result<String> = withContext(Dispatchers.IO) {
        if (apiKey.isBlank()) {
            return@withContext Result.failure(IllegalArgumentException("OpenRouter API key is missing. Please set your key in Settings."))
        }

        try {
            val jsonPayload = JSONObject().apply {
                put("model", if (model.isBlank()) "google/gemini-2.5-flash" else model)
                put("max_tokens", 900)
                put("temperature", 0.3)

                val messagesArray = JSONArray().apply {
                    put(JSONObject().apply {
                        put("role", "system")
                        put(
                            "content",
                            "You are a friendly, expert computer science teacher. When asked about an AI, networking, or engineering term, provide:\n1. A memorable everyday human analogy\n2. Key architectural nuances & common pitfalls\n3. A practical real-world scenario\nKeep language clear, humanized, and formatted with bullet points."
                        )
                    })
                    put(JSONObject().apply {
                        put("role", "user")
                        put(
                            "content",
                            "Explain the term \"$term\" in depth. Context summary: $existingMeaning. Give me an intuitive analogy, nuances/pitfalls, and real-world implementation advice."
                        )
                    })
                }
                put("messages", messagesArray)
            }

            val requestBody = jsonPayload.toString().toRequestBody("application/json; charset=utf-8".toMediaType())

            val request = Request.Builder()
                .url("https://openrouter.ai/api/v1/chat/completions")
                .header("Authorization", "Bearer ${apiKey.trim()}")
                .header("HTTP-Referer", "https://github.com/aistudio-lexicon")
                .header("X-Title", "AI Lexicon Android")
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string().orEmpty()

            if (!response.isSuccessful) {
                val errorMsg = try {
                    val errorJson = JSONObject(responseBody)
                    errorJson.optJSONObject("error")?.optString("message")
                        ?: "HTTP ${response.code}: ${response.message}"
                } catch (e: Exception) {
                    "HTTP ${response.code}: ${response.message}"
                }
                return@withContext Result.failure(Exception(errorMsg))
            }

            val rootJson = JSONObject(responseBody)
            val choices = rootJson.optJSONArray("choices")
            if (choices != null && choices.length() > 0) {
                val firstChoice = choices.getJSONObject(0)
                val message = firstChoice.optJSONObject("message")
                val content = message?.optString("content").orEmpty()
                if (content.isNotBlank()) {
                    return@withContext Result.success(content.trim())
                }
            }

            Result.failure(Exception("Empty response received from OpenRouter."))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
