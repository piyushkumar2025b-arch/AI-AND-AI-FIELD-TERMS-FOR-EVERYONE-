package com.example.data.network

import com.example.data.local.WordEntity
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
    companion object {
        val FREE_MODELS = listOf(
            "openrouter/free" to "OpenRouter Free (Auto-routed across available free models)",
            "meta-llama/llama-3.3-70b-instruct:free" to "Meta Llama 3.3 70B Instruct (Free)",
            "deepseek/deepseek-r1:free" to "DeepSeek R1 Reasoning (Free)",
            "google/gemini-2.0-flash-exp:free" to "Google Gemini 2.0 Flash Exp (Free)",
            "qwen/qwen-2.5-coder-32b-instruct:free" to "Qwen 2.5 Coder 32B (Free)",
            "mistralai/mistral-small-24b-instruct-2501:free" to "Mistral Small 24B Instruct (Free)",
            "meta-llama/llama-3.2-3b-instruct:free" to "Meta Llama 3.2 3B Instruct (Free)",
            "deepseek/deepseek-chat-v3-0324:free" to "DeepSeek V3 Chat (Free)",
            "nousresearch/deephermes-3-llama-3-8b-preview:free" to "DeepHermes 3 Llama 3 8B (Free)"
        )
        const val DEFAULT_FREE_MODEL = "openrouter/free"
    }

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
            return@withContext Result.failure(
                IllegalArgumentException("OpenRouter API key is missing. Tap Settings to enter your free key from openrouter.ai/keys.")
            )
        }

        try {
            val selectedModel = if (model.isBlank()) DEFAULT_FREE_MODEL else model
            val jsonPayload = JSONObject().apply {
                put("model", selectedModel)
                put("max_tokens", 900)
                put("temperature", 0.3)

                val messagesArray = JSONArray().apply {
                    put(JSONObject().apply {
                        put("role", "system")
                        put(
                            "content",
                            "You are a friendly, expert computer science teacher. When asked about an AI, networking, or engineering term, provide:\n1. A memorable everyday human analogy\n2. Key architectural nuances & common pitfalls\n3. A practical real-world scenario\nKeep language clear, humanized, and formatted with clean bullet points."
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

    suspend fun generateWordDefinition(
        apiKey: String,
        model: String,
        term: String
    ): Result<WordEntity> = withContext(Dispatchers.IO) {
        if (apiKey.isBlank()) {
            return@withContext Result.failure(
                IllegalArgumentException("OpenRouter API key is missing. Please set your key in Settings (free key from openrouter.ai/keys).")
            )
        }

        try {
            val selectedModel = if (model.isBlank()) DEFAULT_FREE_MODEL else model
            val prompt = """
                You are an expert technical dictionary lexicographer.
                The user wants to add the term: "$term" into an offline AI, Networking, and Systems dictionary.
                Generate the definition strictly matching this JSON schema:
                {
                  "term": "$term",
                  "category": "One of: AI Core, Agent Harnesses, Networking, Frameworks & SDKs, Evals & Observability, MCP & Tooling, Vector Databases, Gateways & Inference, CI/CD & DevOps, System Design, Reasoning & Models, Safety & Alignment, Prompt Engineering, RAG & Context, Data & Datasets, Careers & Jobs",
                  "humanMeaning": "Clear, friendly, conversational plain-English explanation as if explaining to someone normally without jargon (1-2 sentences).",
                  "keyPoints": "• First key point about technical mechanism.\n• Second key point about how it works.\n• Third key point about why it matters.",
                  "practicalUses": "• First real-world practical use case.\n• Second real-world use case in industry.",
                  "examples": "Example: A concrete scenario, code snippet, CLI command, or intuitive analogy.",
                  "referenceUrl": "https://en.wikipedia.org/wiki/${term.trim().replace(" ", "_")}"
                }
                CRITICAL: Output ONLY the raw JSON object. Do not enclose in markdown and do not write any introductory or concluding words.
            """.trimIndent()

            val jsonPayload = JSONObject().apply {
                put("model", selectedModel)
                put("max_tokens", 1000)
                put("temperature", 0.2)

                val messagesArray = JSONArray().apply {
                    put(JSONObject().apply {
                        put("role", "system")
                        put("content", "You are a professional dictionary lexicographer. Always output valid JSON only.")
                    })
                    put(JSONObject().apply {
                        put("role", "user")
                        put("content", prompt)
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
                var rawContent = message?.optString("content").orEmpty().trim()

                // Robust extraction: locate the outermost JSON object braces
                val firstBrace = rawContent.indexOf('{')
                val lastBrace = rawContent.lastIndexOf('}')
                if (firstBrace != -1 && lastBrace != -1 && lastBrace > firstBrace) {
                    rawContent = rawContent.substring(firstBrace, lastBrace + 1).trim()
                }

                val parsedJson = try {
                    JSONObject(rawContent)
                } catch (e: Exception) {
                    // Fallback JSON constructor if slightly malformed
                    JSONObject().apply {
                        put("term", term)
                        put("humanMeaning", rawContent.take(300))
                    }
                }

                val generatedTerm = parsedJson.optString("term", term).ifBlank { term }
                val category = parsedJson.optString("category", "General AI").ifBlank { "General AI" }
                val humanMeaning = parsedJson.optString("humanMeaning", "").ifBlank {
                    "A core concept in modern AI and computing: $term."
                }
                val keyPoints = parsedJson.optString("keyPoints", "• Fundamental building block of AI systems.\n• Designed to enhance efficiency and capabilities.")
                val practicalUses = parsedJson.optString("practicalUses", "• Applied in production AI architectures and modern software.")
                val examples = parsedJson.optString("examples", "Example: Used widely in cutting-edge development and engineering workflows.")
                val referenceUrl = parsedJson.optString("referenceUrl", "https://en.wikipedia.org/wiki/${term.trim().replace(" ", "_")}")

                val entity = WordEntity(
                    term = generatedTerm,
                    category = category,
                    part = "Custom (AI Generated)",
                    humanMeaning = humanMeaning,
                    keyPoints = keyPoints,
                    practicalUses = practicalUses,
                    examples = examples,
                    referenceUrl = referenceUrl,
                    isCustom = true,
                    isBookmarked = false
                )

                return@withContext Result.success(entity)
            }

            Result.failure(Exception("Empty response from OpenRouter free model."))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
