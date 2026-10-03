package com.example.data.knowledge

import com.example.data.local.WordEntity

enum class KnowledgeSourceType(val displayName: String) {
    STANDARD("Standards & RFC"),
    RESEARCH_PAPER("Research Paper / ArXiv"),
    OFFICIAL_DOCS("Official Documentation"),
    SPECIFICATION("Specification"),
    ENCYCLOPEDIA("Encyclopedia")
}

data class KnowledgeSourceItem(
    val name: String,
    val type: KnowledgeSourceType,
    val description: String,
    val url: String,
    val citation: String
)

object KnowledgeSourceRegistry {

    private val CURATED_SOURCES: Map<String, List<KnowledgeSourceItem>> = mapOf(
        // Networking & Protocols
        "TCP" to listOf(
            KnowledgeSourceItem(
                name = "RFC 9293: Transmission Control Protocol (TCP)",
                type = KnowledgeSourceType.STANDARD,
                description = "The authoritative IETF Internet Standard obsoleting RFC 793, defining TCP state transitions and flow control.",
                url = "https://www.rfc-editor.org/rfc/rfc9293",
                citation = "IETF STD 7, Aug 2022"
            ),
            KnowledgeSourceItem(
                name = "MDN Web Docs: TCP Overview",
                type = KnowledgeSourceType.OFFICIAL_DOCS,
                description = "Developer-focused explanation of reliable transport layer semantics and handshakes.",
                url = "https://developer.mozilla.org/en-US/docs/Glossary/TCP",
                citation = "Mozilla Developer Network"
            )
        ),
        "UDP" to listOf(
            KnowledgeSourceItem(
                name = "RFC 768: User Datagram Protocol",
                type = KnowledgeSourceType.STANDARD,
                description = "Foundational specification for connectionless, low-overhead datagram packet transmission.",
                url = "https://www.rfc-editor.org/rfc/rfc768",
                citation = "IETF STD 6, J. Postel, Aug 1980"
            )
        ),
        "IP" to listOf(
            KnowledgeSourceItem(
                name = "RFC 791 (IPv4) & RFC 8200 (IPv6)",
                type = KnowledgeSourceType.STANDARD,
                description = "Core Internet Protocol specifications establishing packet addressing, routing, and header format.",
                url = "https://www.rfc-editor.org/rfc/rfc8200",
                citation = "IETF STD 86, Jul 2017"
            )
        ),
        "HTTP" to listOf(
            KnowledgeSourceItem(
                name = "RFC 9110: HTTP Semantics",
                type = KnowledgeSourceType.STANDARD,
                description = "Current standard for HTTP architecture, methods, status codes, and header fields.",
                url = "https://www.rfc-editor.org/rfc/rfc9110",
                citation = "IETF STD 97, Jun 2022"
            ),
            KnowledgeSourceItem(
                name = "RFC 9113: HTTP/2 & RFC 9114: HTTP/3 (QUIC)",
                type = KnowledgeSourceType.STANDARD,
                description = "Binary multiplexing and UDP-based QUIC transport standards for modern high-speed web.",
                url = "https://www.rfc-editor.org/rfc/rfc9114",
                citation = "IETF RFC 9114, Jun 2022"
            )
        ),
        "DNS" to listOf(
            KnowledgeSourceItem(
                name = "RFC 1034 & RFC 1035: Domain Names",
                type = KnowledgeSourceType.STANDARD,
                description = "Original and governing domain name system concept, facilities, and implementation specifications.",
                url = "https://www.rfc-editor.org/rfc/rfc1035",
                citation = "IETF STD 13, P. Mockapetris, Nov 1987"
            )
        ),
        "TLS" to listOf(
            KnowledgeSourceItem(
                name = "RFC 8446: The Transport Layer Security (TLS) Protocol Version 1.3",
                type = KnowledgeSourceType.STANDARD,
                description = "Modern cryptographic protocol standard providing 1-RTT handshake, forward secrecy, and zero-RTT resumption.",
                url = "https://www.rfc-editor.org/rfc/rfc8446",
                citation = "IETF RFC 8446, E. Rescorla, Aug 2018"
            )
        ),
        "BGP" to listOf(
            KnowledgeSourceItem(
                name = "RFC 4271: A Border Gateway Protocol 4 (BGP-4)",
                type = KnowledgeSourceType.STANDARD,
                description = "The standard exterior routing protocol running the global Internet routing table across autonomous systems (AS).",
                url = "https://www.rfc-editor.org/rfc/rfc4271",
                citation = "IETF RFC 4271, Y. Rekhter et al., Jan 2006"
            )
        ),
        "QUIC" to listOf(
            KnowledgeSourceItem(
                name = "RFC 9000: QUIC: A UDP-Based Multiplexed and Secure Transport",
                type = KnowledgeSourceType.STANDARD,
                description = "Next-generation transport layer standard solving head-of-line blocking and integrating native TLS 1.3.",
                url = "https://www.rfc-editor.org/rfc/rfc9000",
                citation = "IETF RFC 9000, May 2021"
            )
        ),
        "WEBSOCKET" to listOf(
            KnowledgeSourceItem(
                name = "RFC 6455: The WebSocket Protocol",
                type = KnowledgeSourceType.STANDARD,
                description = "Bidirectional, full-duplex communication channel over a single TCP connection initiated via HTTP upgrade.",
                url = "https://www.rfc-editor.org/rfc/rfc6455",
                citation = "IETF RFC 6455, Dec 2011"
            )
        ),

        // AI Core, Architectures & Foundational Papers
        "TRANSFORMER" to listOf(
            KnowledgeSourceItem(
                name = "Attention Is All You Need",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Foundational landmark paper introducing the self-attention Transformer architecture replacing recurrent and convolutional networks.",
                url = "https://arxiv.org/abs/1706.03762",
                citation = "Vaswani et al., Google Brain / NeurIPS 2017"
            ),
            KnowledgeSourceItem(
                name = "The Illustrated Transformer",
                type = KnowledgeSourceType.OFFICIAL_DOCS,
                description = "Renowned visual architectural deep dive into multi-head attention mechanisms and embeddings.",
                url = "https://jalammar.github.io/illustrated-transformer/",
                citation = "Jay Alammar Research"
            )
        ),
        "RAG" to listOf(
            KnowledgeSourceItem(
                name = "Retrieval-Augmented Generation for Knowledge-Intensive NLP Tasks",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Original paper introducing end-to-end hybrid generative models that query external non-parametric document indices.",
                url = "https://arxiv.org/abs/2005.11401",
                citation = "Lewis et al., Meta AI / NeurIPS 2020"
            ),
            KnowledgeSourceItem(
                name = "LangChain / LlamaIndex Architecture Guides",
                type = KnowledgeSourceType.OFFICIAL_DOCS,
                description = "Production blueprints for chunking, vector indexing, reranking, and contextual retrieval.",
                url = "https://docs.langchain.com",
                citation = "LangChain Docs"
            )
        ),
        "LORA" to listOf(
            KnowledgeSourceItem(
                name = "LoRA: Low-Rank Adaptation of Large Language Models",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Seminal parameter-efficient fine-tuning paper that freezes pretrained weights and injects trainable rank decomposition matrices.",
                url = "https://arxiv.org/abs/2106.09685",
                citation = "Edward Hu et al., Microsoft Research / ICLR 2022"
            ),
            KnowledgeSourceItem(
                name = "Hugging Face PEFT Library Documentation",
                type = KnowledgeSourceType.OFFICIAL_DOCS,
                description = "Practical guide to configuring rank (r), alpha scaling, and target projection layers.",
                url = "https://huggingface.co/docs/peft",
                citation = "Hugging Face"
            )
        ),
        "MCP" to listOf(
            KnowledgeSourceItem(
                name = "Model Context Protocol (MCP) Specification",
                type = KnowledgeSourceType.SPECIFICATION,
                description = "Open standard protocol for securely connecting AI assistants to data sources, tools, and execution environments.",
                url = "https://modelcontextprotocol.io/",
                citation = "Anthropic / Model Context Protocol Spec 2024"
            )
        ),
        "SPECULATIVE DECODING" to listOf(
            KnowledgeSourceItem(
                name = "Fast Inference from Transformers via Speculative Decoding",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Technique accelerating inference 2-3x by using a small draft model to generate tokens validated in parallel by the target model.",
                url = "https://arxiv.org/abs/2211.17192",
                citation = "Leviathan, Kalman, Matias / Google Research, 2023"
            )
        ),
        "FLASHATTENTION" to listOf(
            KnowledgeSourceItem(
                name = "FlashAttention: Fast and Memory-Efficient Exact Attention with IO-Awareness",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "IO-aware exact attention algorithm optimizing GPU SRAM tiled access to reduce memory reads and speed up training.",
                url = "https://arxiv.org/abs/2205.14135",
                citation = "Tri Dao et al., Stanford University / NeurIPS 2022"
            )
        ),
        "DPO" to listOf(
            KnowledgeSourceItem(
                name = "Direct Preference Optimization: Your Language Model is Secretly a Reward Model",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Groundbreaking alignment method optimizing policy directly on human preference pairs without training a separate reward model or using RL.",
                url = "https://arxiv.org/abs/2305.18290",
                citation = "Rafailov et al., Stanford University / NeurIPS 2023"
            )
        ),
        "CHAIN OF THOUGHT" to listOf(
            KnowledgeSourceItem(
                name = "Chain-of-Thought Prompting Elicits Reasoning in Large Language Models",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Foundational reasoning discovery demonstrating that step-by-step intermediate tokens dramatically improve complex multi-step logic.",
                url = "https://arxiv.org/abs/2201.11903",
                citation = "Wei et al., Google Research / NeurIPS 2022"
            )
        ),

        // Harnesses, Tools & Systems
        "DOCKER" to listOf(
            KnowledgeSourceItem(
                name = "Open Container Initiative (OCI) Image & Runtime Specs",
                type = KnowledgeSourceType.SPECIFICATION,
                description = "Open industry governance standard for container image formats, layers, and cgroup/namespace runtimes.",
                url = "https://opencontainers.org/",
                citation = "Linux Foundation / OCI"
            ),
            KnowledgeSourceItem(
                name = "Docker Official Documentation",
                type = KnowledgeSourceType.OFFICIAL_DOCS,
                description = "Comprehensive reference for Dockerfiles, buildkit, networks, and compose topologies.",
                url = "https://docs.docker.com",
                citation = "Docker Inc."
            )
        ),
        "KUBERNETES" to listOf(
            KnowledgeSourceItem(
                name = "Kubernetes Core Architectural Concepts",
                type = KnowledgeSourceType.OFFICIAL_DOCS,
                description = "Official cloud-native guide for Pods, ReplicaSets, Services, Ingress controllers, and etcd consensus.",
                url = "https://kubernetes.io/docs/concepts/",
                citation = "Cloud Native Computing Foundation (CNCF)"
            )
        ),
        "PYTORCH" to listOf(
            KnowledgeSourceItem(
                name = "PyTorch Documentation & Autograd Mechanics",
                type = KnowledgeSourceType.OFFICIAL_DOCS,
                description = "Official reference for dynamic computational graphs, torch.nn layers, and distributed training (DDP).",
                url = "https://pytorch.org/docs/stable/",
                citation = "PyTorch Foundation / Linux Foundation"
            )
        ),
        "LANGCHAIN" to listOf(
            KnowledgeSourceItem(
                name = "LangChain Architecture & Expression Language (LCEL)",
                type = KnowledgeSourceType.OFFICIAL_DOCS,
                description = "Framework documentation for composable chains, agent runtimes, memory, and tool integration.",
                url = "https://python.langchain.com/docs/",
                citation = "LangChain Inc."
            )
        )
    )

    /**
     * Resolves knowledge sources for any word: returns curated specific standards/papers
     * or dynamically constructs authoritative sources based on category and reference URL.
     */
    fun getKnowledgeSourcesForWord(word: WordEntity): List<KnowledgeSourceItem> {
        val upperTerm = word.term.trim().uppercase()
        val found = CURATED_SOURCES[upperTerm]
        if (!found.isNullOrEmpty()) {
            return found
        }

        // Check if any key is contained in the term
        for ((key, sources) in CURATED_SOURCES) {
            if (upperTerm.contains(key) || key.contains(upperTerm)) {
                return sources
            }
        }

        // Dynamic synthesis based on word category and reference
        val list = mutableListOf<KnowledgeSourceItem>()

        // Wikipedia Encyclopedia source
        val wikiUrl = if (word.referenceUrl.isNotBlank()) {
            word.referenceUrl
        } else {
            "https://en.wikipedia.org/wiki/${word.term.trim().replace(" ", "_")}"
        }
        list.add(
            KnowledgeSourceItem(
                name = "Wikipedia: ${word.term}",
                type = KnowledgeSourceType.ENCYCLOPEDIA,
                description = "Peer-reviewed, open encyclopedic reference and historical background for ${word.term}.",
                url = wikiUrl,
                citation = "Wikipedia, The Free Encyclopedia"
            )
        )

        // Add domain-specific source
        val categoryLower = word.category.lowercase()
        when {
            categoryLower.contains("networking") -> {
                list.add(
                    KnowledgeSourceItem(
                        name = "IETF RFC Index & Internet Standards",
                        type = KnowledgeSourceType.STANDARD,
                        description = "Official Internet Engineering Task Force RFC index and protocol request documents.",
                        url = "https://www.rfc-editor.org/search/rfc_search.php",
                        citation = "Internet Engineering Task Force (IETF)"
                    )
                )
            }
            categoryLower.contains("ai") || categoryLower.contains("model") || categoryLower.contains("reasoning") -> {
                list.add(
                    KnowledgeSourceItem(
                        name = "ArXiv Computer Science & Machine Learning Repository",
                        type = KnowledgeSourceType.RESEARCH_PAPER,
                        description = "Open-access archive of 2M+ scholarly articles in computer science and machine learning (cs.AI / cs.LG / cs.CL).",
                        url = "https://arxiv.org/search/?query=${word.term.replace(" ", "+")}&searchtype=all",
                        citation = "Cornell University ArXiv"
                    )
                )
                list.add(
                    KnowledgeSourceItem(
                        name = "Hugging Face Models & Research Hub",
                        type = KnowledgeSourceType.OFFICIAL_DOCS,
                        description = "Open source community weights, benchmark evals, and architectural implementation notes.",
                        url = "https://huggingface.co/models",
                        citation = "Hugging Face"
                    )
                )
            }
            categoryLower.contains("system") || categoryLower.contains("devops") || categoryLower.contains("ci/cd") -> {
                list.add(
                    KnowledgeSourceItem(
                        name = "Cloud Native Computing Foundation (CNCF)",
                        type = KnowledgeSourceType.SPECIFICATION,
                        description = "Open cloud-native standards, container runtime specifications, and graduated systems landscape.",
                        url = "https://landscape.cncf.io/",
                        citation = "CNCF / Linux Foundation"
                    )
                )
            }
            else -> {
                list.add(
                    KnowledgeSourceItem(
                        name = "MDN & Developer Technical Specs",
                        type = KnowledgeSourceType.OFFICIAL_DOCS,
                        description = "Authoritative web standards, software architecture, and developer specifications.",
                        url = "https://developer.mozilla.org/en-US/search?q=${word.term.replace(" ", "+")}",
                        citation = "Open Web Docs"
                    )
                )
            }
        }

        return list
    }
}
