package com.example.data.knowledge

import com.example.data.local.WordEntity

object OfflineKnowledgeEngine {

    fun generateOfflineKnowledge(word: WordEntity, mode: AiMode, customQuestion: String? = null): String {
        return when (mode) {
            AiMode.DEEP_ANALOGY -> generateAnalogy(word)
            AiMode.CODE_LAB -> generateCodeLab(word)
            AiMode.STAFF_ENGINEER -> generateStaffDeepDive(word)
            AiMode.INTERVIEW_QUIZ -> generateInterviewQuiz(word)
            AiMode.CUSTOM_QNA -> generateCustomResponse(word, customQuestion ?: "")
        }
    }

    private fun generateAnalogy(word: WordEntity): String {
        val category = word.category.lowercase()
        val term = word.term

        val analogyHeadline = when {
            category.contains("networking") ->
                "📮 Everyday Analogy: The Postal & Courier Service"
            category.contains("ai") || category.contains("model") || category.contains("reasoning") ->
                "🧠 Everyday Analogy: A High-Speed Thinking Workshop"
            category.contains("harness") || category.contains("framework") ->
                "🧰 Everyday Analogy: The Power Tool Adapter"
            category.contains("security") || category.contains("safety") ->
                "🛡️ Everyday Analogy: The Certified Security Vault"
            else ->
                "💡 Everyday Analogy: A Modular City Grid"
        }

        return """
            |$analogyHeadline
            |
            |Imagine you are trying to explain "$term" to a curious friend over coffee:
            |
            |• Core Concept: ${word.humanMeaning}
            |
            |• How to visualize it:
            |  Think of the problem this solves: without $term, the system would struggle with coordinating inputs, avoiding conflicts, or scaling reliably. With $term in place, every component knows exactly where packets/tokens travel and who is responsible.
            |
            |• Why it sticks in memory:
            |  Whenever you see "$term", remember that it turns chaos into an orderly, repeatable flow.
            |
            |• Quick Takeaway:
            |  ${word.practicalUses.lines().firstOrNull { it.isNotBlank() } ?: "Essential foundational pillar for modern production systems."}
        """.trimMargin()
    }

    private fun generateCodeLab(word: WordEntity): String {
        val term = word.term
        val category = word.category.lowercase()

        return if (category.contains("networking")) {
            """
                |💻 Practical Implementation & CLI Verification for $term:
                |
                |1. Inspect or diagnose in terminal:
                |```bash
                |# Test reachability and network paths
                |traceroute 1.1.1.1
                |curl -v -I --http2 https://cloudflare.com
                |# Inspect socket / connection states
                |ss -tulwn | grep -i "${term.take(4)}"
                |```
                |
                |2. Minimal Socket / Client snippet (Python):
                |```python
                |import socket
                |
                |# Demonstrating foundational transport connection
                |with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
                |    s.settimeout(5.0)
                |    s.connect(("example.com", 80))
                |    s.sendall(b"HEAD / HTTP/1.1\r\nHost: example.com\r\n\r\n")
                |    response = s.recv(1024)
                |    print("Received bytes:", len(response))
                |```
            """.trimMargin()
        } else {
            """
                |💻 Practical Implementation & Code Lab for $term:
                |
                |1. Concrete Integration Pattern (Python):
                |```python
                |# Minimal reproducible harness for $term
                |import os
                |
                |class ${term.replace(Regex("[^a-zA-Z0-9]"), "")}Service:
                |    def __init__(self, endpoint: str = "production"):
                |        self.endpoint = endpoint
                |        self.initialized = True
                |
                |    def execute(self, payload: dict) -> dict:
                |        # Executes core logic for $term
                |        print(f"[$term] Processing payload with {len(payload)} keys")
                |        return {"status": "ok", "term": "$term", "processed": True}
                |
                |service = ${term.replace(Regex("[^a-zA-Z0-9]"), "")}Service()
                |res = service.execute({"source": "lexicon_app"})
                |print(res)
                |```
                |
                |2. Production Tip:
                |Always configure exponential backoff retry and circuit-breakers when operating $term in distributed production.
            """.trimMargin()
        }
    }

    private fun generateStaffDeepDive(word: WordEntity): String {
        return """
            |🏗️ Staff Engineer Architectural Deep Dive: ${word.term}
            |
            |1. Primary Architectural Bottlenecks:
            |• Concurrency & Throughput: Contention typically occurs during connection negotiation, serialized state updates, or GPU VRAM memory bandwidth saturation.
            |• Failure Modes: Cascading timeouts, head-of-line blocking, and cold-start latency under sudden traffic spikes.
            |
            |2. Production Trade-Offs:
            |• Latency vs Consistency: Strict ordering guarantees often add RTT overhead; choosing eventual consistency or optimistic pipelines reduces p99 response times.
            |• Memory Footprint: Maintaining connection states or KV-cache tables requires bounded pooling to prevent out-of-memory crashes.
            |
            |3. Senior Engineering Checklist:
            |• [x] Telemetry: Export p50, p95, and p99 latency metrics alongside error counters.
            |• [x] Graceful Degradation: Ensure the client or upstream proxy fails fast with explicit error codes rather than hanging indefinitely.
            |• [x] Idempotency: Guarantee that retrying the same payload produces identical side effects.
        """.trimMargin()
    }

    private fun generateInterviewQuiz(word: WordEntity): String {
        return """
            |🎯 Interview Flashcard & Exam Questions: ${word.term}
            |
            |Q1: What fundamental problem does ${word.term} solve that previous approaches could not?
            |💡 Answer: ${word.humanMeaning} It addresses core limitations around reliability, scalability, or developer ergonomics.
            |
            |Q2: In a systems design interview, when would you choose NOT to use ${word.term}?
            |💡 Answer: When simplicity or minimal latency is paramount and the added overhead, complexity, or network round-trips outweigh the benefits.
            |
            |Q3: What are the three key technical points you should highlight to an interviewer?
            |${word.keyPoints}
        """.trimMargin()
    }

    private fun generateCustomResponse(word: WordEntity, question: String): String {
        val q = question.trim()
        val effectiveQ = if (q.isBlank()) "Explain how ${word.term} works in practice." else q

        return """
            |💬 Knowledge Assistant on: "${word.term}"
            |
            |• Question: $effectiveQ
            |
            |• Analysis:
            |Regarding ${word.term}, the most important principle is: ${word.humanMeaning}
            |
            |• Technical Mechanisms to note:
            |${word.keyPoints}
            |
            |• Real-World Context:
            |${word.practicalUses}
            |
            |💡 Tip: You can save this AI response directly into your app notes below using "Keep in App".
        """.trimMargin()
    }
}
