package com.example.data.local

object DefaultWordsCatalog {
    fun getAllCatalogWords(): List<WordEntity> {
        val allRaw = mutableListOf<WordEntity>()
        allRaw.addAll(DefaultWordsPart1Networking.getTerms())
        allRaw.addAll(DefaultWordsPart1Extended.getTerms())
        allRaw.addAll(DefaultWordsPart1Advanced.getTerms())
        allRaw.addAll(DefaultWordsPart2Harnesses.getTerms())
        allRaw.addAll(DefaultWordsPart2Tools.getTerms())
        allRaw.addAll(DefaultWordsPart2Infra.getTerms())
        allRaw.addAll(DefaultWordsPart3AtoG.getTerms())
        allRaw.addAll(DefaultWordsPart3CtoG.getTerms())
        allRaw.addAll(DefaultWordsPart3DandE.getTerms())
        allRaw.addAll(DefaultWordsPart3FtoM.getTerms())
        allRaw.addAll(DefaultWordsPart3NtoR.getTerms())
        allRaw.addAll(DefaultWordsPart3StoZ.getTerms())

        // Deduplicate by term (case-insensitive), sorting alphabetically
        val dedupedMap = linkedMapOf<String, WordEntity>()
        for (item in allRaw) {
            val key = item.term.trim().lowercase()
            if (!dedupedMap.containsKey(key)) {
                dedupedMap[key] = item
            } else {
                // If existing entry has less content, replace it
                val existing = dedupedMap[key]!!
                if (item.humanMeaning.length > existing.humanMeaning.length ||
                    item.keyPoints.length > existing.keyPoints.length
                ) {
                    dedupedMap[key] = item
                }
            }
        }

        // Map and classify each term with its level (BASIC, INTERMEDIATE, ADVANCED) and isImportant flag
        return dedupedMap.values.map { word ->
            val (level, isImportant) = classifyWord(word.term, word.category, word.part)
            word.copy(level = level, isImportant = isImportant)
        }.sortedBy { it.term.lowercase() }
    }

    /**
     * Determines the conceptual difficulty level (BASIC, INTERMEDIATE, ADVANCED)
     * and whether the term is an essential must-know concept (isImportant).
     */
    fun classifyWord(term: String, category: String, part: String): Pair<String, Boolean> {
        val t = term.trim().lowercase()

        // 1. Check if it's considered an Important / High-Value term
        val isImportant = IMPORTANT_TERMS.any { it.equals(t, ignoreCase = true) } ||
                t in listOf(
                    "ip", "dns", "tcp", "udp", "http", "https", "ssl", "tls", "api", "rest",
                    "llm", "prompt", "token", "rag", "transformer", "agent", "mcp",
                    "fine-tuning", "lora", "context window", "embeddings", "vector db",
                    "docker", "kubernetes", "gpu", "inference", "quantization",
                    "bgp", "subnet", "nat", "reverse proxy", "load balancer",
                    "claude code", "cursor", "langchain", "hallucination",
                    "flashattention", "kv cache", "moe", "speculative decoding", "rlhf", "dpo"
                )

        // 2. Check for Advanced terms
        if (ADVANCED_TERMS.any { it.equals(t, ignoreCase = true) } ||
            part.contains("Advanced", ignoreCase = true) ||
            t.contains("parallelism") ||
            t.contains("quantization") ||
            t.contains("speculative") ||
            t.contains("ebpf") ||
            t.contains("attention") && !t.contains("basics")
        ) {
            return Pair(WordEntity.LEVEL_ADVANCED, isImportant)
        }

        // 3. Check for Intermediate terms
        if (INTERMEDIATE_TERMS.any { it.equals(t, ignoreCase = true) } ||
            category.contains("Harness", ignoreCase = true) ||
            category.contains("Framework", ignoreCase = true) ||
            part.contains("Harness", ignoreCase = true) ||
            part.contains("Tools", ignoreCase = true) ||
            t.contains("pipeline") ||
            t.contains("protocol") ||
            t.contains("middleware") ||
            t.contains("vector")
        ) {
            return Pair(WordEntity.LEVEL_INTERMEDIATE, isImportant)
        }

        // 4. Check for explicitly Basic terms, or default to BASIC
        return Pair(WordEntity.LEVEL_BASIC, isImportant)
    }

    private val IMPORTANT_TERMS = setOf(
        "ip", "dns", "tcp", "udp", "http", "https", "ssl", "tls", "api", "rest", "json",
        "cloud", "server", "client", "llm", "prompt", "token", "gpu", "cpu", "cache",
        "rag", "transformer", "agent", "mcp", "fine-tuning", "lora", "qlora", "context window",
        "embeddings", "vector db", "docker", "kubernetes", "inference", "quantization",
        "bgp", "cidr", "subnet", "nat", "vpc", "router", "gateway", "load balancer", "reverse proxy",
        "websockets", "grpc", "wireguard", "vpn", "firewall", "redis", "kafka", "pytorch",
        "claude code", "cursor", "langchain", "llamaindex", "chromadb", "pinecone", "hugging face",
        "flashattention", "kv cache", "moe", "speculative decoding", "rlhf", "dpo", "hallucination"
    )

    private val ADVANCED_TERMS = setOf(
        "flashattention", "kv cache", "quantization", "moe", "mixture of experts",
        "speculative decoding", "direct preference optimization", "dpo", "rlhf",
        "proximal policy optimization", "ppo", "peft", "gguf", "awq", "gptq", "exl2", "vllm",
        "tensor parallelism", "pipeline parallelism", "data parallelism", "zero", "deepspeed", "fsdp",
        "rotary position embedding", "rope", "alibi", "diffusion models", "latent diffusion",
        "contrastive learning", "clip", "ebpf", "wireguard", "quic", "bgp", "anycast", "mpls",
        "syn flood", "bbr", "congestion control", "zero-copy", "dpdk", "sr-iov", "rdma", "infiniband",
        "cuda", "triton", "pagedattention", "continuous batching", "chunked prefill", "medusa",
        "draft model", "grouped-query attention", "gqa", "multi-head latent attention", "mla",
        "deepseek", "constitutional ai", "kernel", "backpropagation", "loss landscape",
        "needle in a haystack", "dpo vs rlhf", "gradient checkpointing", "activation checkpointing"
    )

    private val INTERMEDIATE_TERMS = setOf(
        "rag", "fine-tuning", "transformer", "agent", "mcp", "model context protocol",
        "langchain", "llamaindex", "crewai", "autogen", "function calling", "tool calling",
        "vector db", "embeddings", "cosine similarity", "chromadb", "pinecone", "weaviate", "milvus",
        "qdrant", "faiss", "hnsw", "semantic search", "hybrid search", "reranking",
        "context window", "hallucination", "overfitting", "underfitting", "lora", "zero-shot", "few-shot",
        "in-context learning", "subnet", "cidr", "nat", "vpc", "reverse proxy", "load balancer",
        "websockets", "grpc", "redis", "kafka", "kubernetes", "docker", "pytorch", "tensorflow",
        "hugging face", "ollama", "prompt engineering", "chain of thought", "react", "rag triad",
        "guardrails", "eval", "bleu", "rouge", "perplexity", "loss function", "adamw",
        "dropout", "batch normalization", "layer normalization", "cross-attention",
        "multi-head attention", "positional encoding", "tokenizer", "bpe", "wordpiece",
        "cursor", "claude code", "windsurf", "copilot", "synthetic data", "distillation"
    )
}
