package com.example.data.network

import com.example.data.local.DefaultWordsCatalog
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
    ): Result<String> {
        return queryWordInsightsWithMode(
            apiKey = apiKey,
            model = model,
            term = term,
            existingMeaning = existingMeaning,
            mode = com.example.data.knowledge.AiMode.DEEP_ANALOGY,
            customQuestion = null,
            wikipediaContext = null
        )
    }

    suspend fun queryWordInsightsWithMode(
        apiKey: String,
        model: String,
        term: String,
        existingMeaning: String,
        mode: com.example.data.knowledge.AiMode,
        customQuestion: String? = null,
        wikipediaContext: String? = null
    ): Result<String> = withContext(Dispatchers.IO) {
        if (apiKey.isBlank()) {
            return@withContext Result.failure(
                IllegalArgumentException("OpenRouter API key is missing. Tap Settings to enter your free key from openrouter.ai/keys, or tap 'Use Offline Smart Knowledge' for instant breakdown.")
            )
        }

        try {
            val selectedModel = if (model.isBlank()) DEFAULT_FREE_MODEL else model
            val knowledgeContextSnippet = if (!wikipediaContext.isNullOrBlank()) {
                "\nGrounding Knowledge (from authoritative encyclopedic sources):\n\"\"\"\n$wikipediaContext\n\"\"\"\n"
            } else ""

            val systemPrompt = when (mode) {
                com.example.data.knowledge.AiMode.DEEP_ANALOGY ->
                    "You are a friendly, brilliant computer science educator. Provide a memorable, creative everyday human analogy for the concept, explain how it turns chaos into order, and share a practical real-world scenario. Use clean markdown formatting and bullet points."

                com.example.data.knowledge.AiMode.CODE_LAB ->
                    "You are a principal software engineer. Provide a concrete, practical, runnable code snippet or CLI command demonstrating how this concept works in production. Use realistic Python, Bash, or appropriate language snippets, clear comments, and production tips."

                com.example.data.knowledge.AiMode.STAFF_ENGINEER ->
                    "You are a staff infrastructure architect. Provide a rigorous architectural deep dive: primary bottlenecks (latency/concurrency), subtle failure modes, trade-offs (e.g. consistency vs latency), and a production readiness checklist."

                com.example.data.knowledge.AiMode.INTERVIEW_QUIZ ->
                    "You are a senior technical interviewer at a top tech company. Provide 3 high-impact interview questions testing deep understanding of this term, along with clear model answers and key technical keywords candidate should mention."

                com.example.data.knowledge.AiMode.CUSTOM_QNA ->
                    "You are an expert AI, networking, and systems mentor. Answer the user's specific question about this concept clearly, accurately, and concisely."
            }

            val userContent = when (mode) {
                com.example.data.knowledge.AiMode.DEEP_ANALOGY ->
                    "Explain the term \"$term\" with an intuitive, unforgettable human analogy.$knowledgeContextSnippet Context: $existingMeaning"

                com.example.data.knowledge.AiMode.CODE_LAB ->
                    "Show practical code snippets and CLI tools demonstrating \"$term\".$knowledgeContextSnippet Context: $existingMeaning"

                com.example.data.knowledge.AiMode.STAFF_ENGINEER ->
                    "Provide a staff engineer architectural breakdown of \"$term\" including trade-offs and failure modes.$knowledgeContextSnippet Context: $existingMeaning"

                com.example.data.knowledge.AiMode.INTERVIEW_QUIZ ->
                    "Give me 3 top technical interview flashcard questions and answers for \"$term\".$knowledgeContextSnippet Context: $existingMeaning"

                com.example.data.knowledge.AiMode.CUSTOM_QNA ->
                    "Question on \"$term\": ${customQuestion ?: "Explain this concept in depth."}$knowledgeContextSnippet Context: $existingMeaning"
            }

            val jsonPayload = JSONObject().apply {
                put("model", selectedModel)
                put("max_tokens", 1000)
                put("temperature", 0.3)

                val messagesArray = JSONArray().apply {
                    put(JSONObject().apply {
                        put("role", "system")
                        put("content", systemPrompt)
                    })
                    put(JSONObject().apply {
                        put("role", "user")
                        put("content", userContent)
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
        term: String,
        wikipediaContext: String? = null
    ): Result<WordEntity> = withContext(Dispatchers.IO) {
        if (apiKey.isBlank()) {
            return@withContext Result.failure(
                IllegalArgumentException("OpenRouter API key is missing. Please set your key in Settings (free key from openrouter.ai/keys).")
            )
        }

        try {
            val selectedModel = if (model.isBlank()) DEFAULT_FREE_MODEL else model
            val wikiGrounding = if (!wikipediaContext.isNullOrBlank()) {
                "\nGrounding reference from Wikipedia:\n$wikipediaContext\n"
            } else ""

            val prompt = """
                You are an expert technical dictionary lexicographer.
                The user wants to add the term: "$term" into an offline AI, Networking, and Systems dictionary.
                $wikiGrounding
                Generate the definition strictly matching this JSON schema:
                {
                  "term": "$term",
                  "category": "One of: AI Core, Agent Harnesses, Networking, Frameworks & SDKs, Evals & Observability, MCP & Tooling, Vector Databases, Gateways & Inference, CI/CD & DevOps, System Design, Reasoning & Models, Safety & Alignment, Prompt Engineering, RAG & Context, Data & Datasets, Careers & Jobs",
                  "level": "One of: BASIC, INTERMEDIATE, ADVANCED (based on conceptual difficulty)",
                  "isImportant": true or false (true if this is a high-value core foundational term everyone should understand),
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

                val (defaultLevel, defaultImportant) = DefaultWordsCatalog.classifyWord(generatedTerm, category, "Custom")
                val parsedLevelRaw = parsedJson.optString("level", "").trim().uppercase()
                val finalLevel = when (parsedLevelRaw) {
                    "ADVANCED" -> WordEntity.LEVEL_ADVANCED
                    "INTERMEDIATE" -> WordEntity.LEVEL_INTERMEDIATE
                    "BASIC" -> WordEntity.LEVEL_BASIC
                    else -> defaultLevel
                }
                val finalImportant = if (parsedJson.has("isImportant")) {
                    parsedJson.optBoolean("isImportant", defaultImportant)
                } else {
                    defaultImportant
                }

                val entity = WordEntity(
                    term = generatedTerm,
                    category = category,
                    part = "Custom (AI Generated)",
                    humanMeaning = humanMeaning,
                    keyPoints = keyPoints,
                    practicalUses = practicalUses,
                    examples = examples,
                    referenceUrl = referenceUrl,
                    level = finalLevel,
                    isImportant = finalImportant,
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
