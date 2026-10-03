package com.example.data.knowledge

import com.example.data.local.WordEntity

enum class KnowledgeSourceType(val displayName: String) {
    STANDARD("Standards & RFC"),
    RESEARCH_PAPER("Research Paper / ArXiv"),
    OFFICIAL_DOCS("Official Documentation"),
    SPECIFICATION("Specification & PEP"),
    ENCYCLOPEDIA("Encyclopedia"),
    DICTIONARY("Linguistic & Lexical")
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
        // Networking & Internet Protocols
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
        "WIREGUARD" to listOf(
            KnowledgeSourceItem(
                name = "WireGuard: Next Generation Kernel Network Tunnel",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Seminal whitepaper introducing high-performance, cryptographically opinionated Noise protocol VPN tunnels.",
                url = "https://www.wireguard.com/papers/wireguard.pdf",
                citation = "Jason A. Donenfeld, NDSS 2017"
            )
        ),
        "ZERO TRUST" to listOf(
            KnowledgeSourceItem(
                name = "NIST SP 800-207: Zero Trust Architecture",
                type = KnowledgeSourceType.STANDARD,
                description = "Official US government enterprise cybersecurity standard defining 'never trust, always verify' per-session enforcement.",
                url = "https://csrc.nist.gov/publications/detail/sp/800-207/final",
                citation = "National Institute of Standards and Technology (NIST), Aug 2020"
            )
        ),

        // Programming Languages, Compilers & Formal Grammars
        "PYTHON" to listOf(
            KnowledgeSourceItem(
                name = "The Python Language Reference & PEP Index",
                type = KnowledgeSourceType.SPECIFICATION,
                description = "Official formal syntax, execution model, grammar specifications, and Python Enhancement Proposals.",
                url = "https://docs.python.org/3/reference/",
                citation = "Python Software Foundation"
            ),
            KnowledgeSourceItem(
                name = "PEP 8: Style Guide for Python Code",
                type = KnowledgeSourceType.STANDARD,
                description = "Authoritative conventions for Python language code readability, naming, and layout.",
                url = "https://peps.python.org/pep-0008/",
                citation = "G. van Rossum, B. Warsaw, N. Coghlan"
            )
        ),
        "RUST" to listOf(
            KnowledgeSourceItem(
                name = "The Rust Reference & Language Specification",
                type = KnowledgeSourceType.SPECIFICATION,
                description = "Official reference defining grammar, memory model, borrow checker semantics, and type system.",
                url = "https://doc.rust-lang.org/reference/",
                citation = "Rust Foundation"
            ),
            KnowledgeSourceItem(
                name = "Ferrocene Language Specification",
                type = KnowledgeSourceType.STANDARD,
                description = "Safety-critical, qualified specification of the Rust programming language for ISO 26262 and IEC 61508.",
                url = "https://spec.ferrocene.dev/",
                citation = "Ferrous Systems"
            )
        ),
        "KOTLIN" to listOf(
            KnowledgeSourceItem(
                name = "Kotlin Language Specification",
                type = KnowledgeSourceType.SPECIFICATION,
                description = "Formal grammar, type system, coroutines, and semantic rules for the Kotlin programming language.",
                url = "https://kotlinlang.org/spec/",
                citation = "Kotlin Foundation / JetBrains"
            )
        ),
        "JAVASCRIPT" to listOf(
            KnowledgeSourceItem(
                name = "ECMA-262: ECMAScript Language Specification",
                type = KnowledgeSourceType.STANDARD,
                description = "The international standard defining syntax, semantics, and built-in objects for JavaScript.",
                url = "https://tc39.es/ecma262/",
                citation = "Ecma International / TC39"
            )
        ),
        "TYPESCRIPT" to listOf(
            KnowledgeSourceItem(
                name = "TypeScript Language Specification & Handbooks",
                type = KnowledgeSourceType.SPECIFICATION,
                description = "Structural type system, type inference, generics, and compiler transform semantics.",
                url = "https://www.typescriptlang.org/docs/",
                citation = "Microsoft"
            )
        ),
        "C++" to listOf(
            KnowledgeSourceItem(
                name = "ISO/IEC 14882: Programming Languages — C++",
                type = KnowledgeSourceType.STANDARD,
                description = "The international standard for C++ (WG21), memory model, templates, and Standard Template Library.",
                url = "https://en.cppreference.com/w/cpp",
                citation = "ISO/IEC JTC 1/SC 22/WG 21"
            )
        ),
        "SQL" to listOf(
            KnowledgeSourceItem(
                name = "ISO/IEC 9075: Database Language SQL",
                type = KnowledgeSourceType.STANDARD,
                description = "International standard defining the SQL syntax, relational query processing, and transactional isolation.",
                url = "https://www.iso.org/standard/76583.html",
                citation = "ISO/IEC JTC 1/SC 32"
            )
        ),
        "JSON" to listOf(
            KnowledgeSourceItem(
                name = "RFC 8259 / ECMA-404: The JSON Data Interchange Format",
                type = KnowledgeSourceType.STANDARD,
                description = "Strict standard defining the JavaScript Object Notation grammar and character encoding.",
                url = "https://www.rfc-editor.org/rfc/rfc8259",
                citation = "IETF STD 90 / Ecma International"
            )
        ),

        // Linguistics, Lexical Standards & Language Codes
        "UNICODE" to listOf(
            KnowledgeSourceItem(
                name = "The Unicode Standard Version 16.0",
                type = KnowledgeSourceType.STANDARD,
                description = "Authoritative worldwide character encoding standard covering 150,000+ characters across 161 modern and historic scripts.",
                url = "https://www.unicode.org/versions/latest/",
                citation = "Unicode Consortium"
            ),
            KnowledgeSourceItem(
                name = "Unicode Standard Annex #29: Unicode Text Segmentation",
                type = KnowledgeSourceType.STANDARD,
                description = "Algorithmic specification for identifying grapheme clusters, words, and sentences across all human languages.",
                url = "https://www.unicode.org/reports/tr29/",
                citation = "Unicode Consortium (UAX #29)"
            )
        ),
        "LANGUAGE" to listOf(
            KnowledgeSourceItem(
                name = "IETF BCP 47 (RFC 5646): Tags for Identifying Languages",
                type = KnowledgeSourceType.STANDARD,
                description = "Standard protocol syntax for language tags (e.g. en-US, zh-Hant), subtag registries, and matching algorithms.",
                url = "https://www.rfc-editor.org/rfc/bcp/bcp47.txt",
                citation = "IETF BCP 47, A. Phillips, M. Davis"
            ),
            KnowledgeSourceItem(
                name = "ISO 639 International Language Code Registry",
                type = KnowledgeSourceType.STANDARD,
                description = "Authoritative global identifier catalog for human natural languages (ISO 639-1, 639-2, 639-3).",
                url = "https://iso639-3.sil.org/",
                citation = "ISO 639 Registration Authority"
            )
        ),
        "GRAMMAR" to listOf(
            KnowledgeSourceItem(
                name = "ISO/IEC 14977: Extended Backus-Naur Form (EBNF)",
                type = KnowledgeSourceType.STANDARD,
                description = "Syntactic metalanguage standard for formally defining context-free grammars and computer programming languages.",
                url = "https://www.cl.cam.ac.uk/~mgk25/iso-14977.pdf",
                citation = "ISO/IEC 14977:1996"
            ),
            KnowledgeSourceItem(
                name = "The Chomsky Hierarchy of Formal Grammars",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Foundational computational linguistics hierarchy: Regular, Context-Free, Context-Sensitive, and Recursively Enumerable.",
                url = "https://en.wikipedia.org/wiki/Chomsky_hierarchy",
                citation = "Noam Chomsky, IRE Trans. Information Theory, 1956"
            )
        ),

        // AI Core, Architectures & Foundational Research
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
        "TOKENIZATION" to listOf(
            KnowledgeSourceItem(
                name = "Neural Machine Translation of Rare Words with Subword Units (BPE)",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "The foundational paper adapting Byte Pair Encoding (BPE) compression for subword tokenization in modern LLMs.",
                url = "https://arxiv.org/abs/1508.07909",
                citation = "Rico Sennrich et al., University of Edinburgh / ACL 2016"
            ),
            KnowledgeSourceItem(
                name = "Hugging Face Tokenizers Architecture & Fast BPE",
                type = KnowledgeSourceType.OFFICIAL_DOCS,
                description = "High-performance Rust-based implementation guide for Byte-Level BPE, WordPiece, and SentencePiece.",
                url = "https://huggingface.co/docs/tokenizers",
                citation = "Hugging Face"
            )
        ),
        "PROMPT INJECTION" to listOf(
            KnowledgeSourceItem(
                name = "OWASP Top 10 for Large Language Model Applications (LLM01)",
                type = KnowledgeSourceType.STANDARD,
                description = "Definitive industry security standard identifying direct and indirect prompt injection attack vectors and defenses.",
                url = "https://owasp.org/www-project-top-10-for-large-language-model-applications/",
                citation = "Open Web Application Security Project (OWASP), 2024"
            )
        ),
        "RAFT" to listOf(
            KnowledgeSourceItem(
                name = "In Search of an Understandable Consensus Algorithm (Raft)",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Authoritative consensus algorithm paper decomposing distributed state machine replication into leader election, log replication, and safety.",
                url = "https://raft.github.io/raft.pdf",
                citation = "Ongaro & Ousterhout, Stanford University / USENIX ATC 2014"
            )
        ),
        "CAP THEOREM" to listOf(
            KnowledgeSourceItem(
                name = "Brewer's Conjecture and Consistent, Available, Partition-Tolerant Web Services",
                type = KnowledgeSourceType.RESEARCH_PAPER,
                description = "Formal proof establishing that asynchronous network systems cannot simultaneously achieve Consistency, Availability, and Partition Tolerance.",
                url = "https://users.ece.cmu.edu/~adrian/731-sp04/readings/GL-cap.pdf",
                citation = "Gilbert & Lynch, MIT / ACM SIGACT 2002"
            )
        )
    )

    /**
     * Resolves multi-source knowledge grounded for any word: returns curated specific
     * standards/papers as well as universal linguistic, lexical, and encyclopedic sources.
     */
    fun getKnowledgeSourcesForWord(word: WordEntity): List<KnowledgeSourceItem> {
        val list = mutableListOf<KnowledgeSourceItem>()
        val upperTerm = word.term.trim().uppercase()

        // 1. Add specific curated technical standards/specifications if available
        val directCurated = CURATED_SOURCES[upperTerm]
        if (!directCurated.isNullOrEmpty()) {
            list.addAll(directCurated)
        } else {
            for ((key, sources) in CURATED_SOURCES) {
                if (upperTerm.contains(key) || key.contains(upperTerm)) {
                    list.addAll(sources)
                    break
                }
            }
        }

        // 2. Wikipedia Encyclopedic Knowledge Source
        val cleanEncodedTerm = word.term.trim().replace(" ", "_")
        val wikiUrl = if (word.referenceUrl.isNotBlank() && word.referenceUrl.contains("wikipedia")) {
            word.referenceUrl
        } else {
            "https://en.wikipedia.org/wiki/$cleanEncodedTerm"
        }
        list.add(
            KnowledgeSourceItem(
                name = "Wikipedia: ${word.term}",
                type = KnowledgeSourceType.ENCYCLOPEDIA,
                description = "Peer-reviewed, open encyclopedic reference and historical background for '${word.term}'.",
                url = wikiUrl,
                citation = "Wikipedia, The Free Encyclopedia"
            )
        )

        // 3. Wiktionary Linguistic & Lexical Grammar Source
        list.add(
            KnowledgeSourceItem(
                name = "Wiktionary: ${word.term}",
                type = KnowledgeSourceType.DICTIONARY,
                description = "Official Wiktionary lexical entry: grammatical definitions, pronunciation, parts of speech, and etymology for '${word.term}'.",
                url = "https://en.wiktionary.org/wiki/$cleanEncodedTerm",
                citation = "Wiktionary, Wikimedia Lexical Database"
            )
        )

        // 4. Merriam-Webster English Lexicographical Reference
        val mwTerm = word.term.trim().lowercase().replace(" ", "%20")
        list.add(
            KnowledgeSourceItem(
                name = "Merriam-Webster: ${word.term}",
                type = KnowledgeSourceType.DICTIONARY,
                description = "Authoritative American English definition, phonetic pronunciation, word origins, and authentic usage sentences.",
                url = "https://www.merriam-webster.com/dictionary/$mwTerm",
                citation = "Merriam-Webster Collegiate Dictionary"
            )
        )

        // 5. Etymology and Language Origins (Etymonline)
        list.add(
            KnowledgeSourceItem(
                name = "Online Etymology Dictionary",
                type = KnowledgeSourceType.DICTIONARY,
                description = "Linguistic historical origins, proto-Indo-European roots, and semantic evolution of '${word.term}'.",
                url = "https://www.etymonline.com/word/$mwTerm",
                citation = "Douglas Harper / Online Etymology Dictionary"
            )
        )

        // 6. Category-specific authoritative sources (RFCs, W3C, ACM, ArXiv)
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
                list.add(
                    KnowledgeSourceItem(
                        name = "IANA Protocol Registries & Port Numbers",
                        type = KnowledgeSourceType.STANDARD,
                        description = "Internet Assigned Numbers Authority authoritative registry of protocols, port numbers, and enterprise numbers.",
                        url = "https://www.iana.org/protocols",
                        citation = "Internet Assigned Numbers Authority (IANA)"
                    )
                )
            }
            categoryLower.contains("ai") || categoryLower.contains("model") || categoryLower.contains("reasoning") || categoryLower.contains("nlp") -> {
                list.add(
                    KnowledgeSourceItem(
                        name = "ArXiv Computer Science & Computation Repository",
                        type = KnowledgeSourceType.RESEARCH_PAPER,
                        description = "Open-access archive of 2M+ scholarly articles in computational linguistics (cs.CL), AI (cs.AI), and machine learning (cs.LG).",
                        url = "https://arxiv.org/search/?query=${word.term.replace(" ", "+")}&searchtype=all",
                        citation = "Cornell University ArXiv"
                    )
                )
                list.add(
                    KnowledgeSourceItem(
                        name = "ACL Anthology (Computational Linguistics)",
                        type = KnowledgeSourceType.RESEARCH_PAPER,
                        description = "Digital archive of research papers in natural language processing and computational linguistics.",
                        url = "https://aclanthology.org/",
                        citation = "Association for Computational Linguistics (ACL)"
                    )
                )
                list.add(
                    KnowledgeSourceItem(
                        name = "Hugging Face Models & Papers Hub",
                        type = KnowledgeSourceType.OFFICIAL_DOCS,
                        description = "Open source community weights, tokenizers, benchmark evals, and architectural implementation notes.",
                        url = "https://huggingface.co/models",
                        citation = "Hugging Face"
                    )
                )
            }
            categoryLower.contains("system") || categoryLower.contains("devops") || categoryLower.contains("ci/cd") || categoryLower.contains("cloud") -> {
                list.add(
                    KnowledgeSourceItem(
                        name = "Cloud Native Computing Foundation (CNCF)",
                        type = KnowledgeSourceType.SPECIFICATION,
                        description = "Open cloud-native standards, container runtime specifications, and graduated systems landscape.",
                        url = "https://landscape.cncf.io/",
                        citation = "CNCF / Linux Foundation"
                    )
                )
                list.add(
                    KnowledgeSourceItem(
                        name = "USENIX Advanced Computing Systems Association",
                        type = KnowledgeSourceType.RESEARCH_PAPER,
                        description = "Peer-reviewed proceedings from OSDI, SOSP, FAST, and NSDI systems conferences.",
                        url = "https://www.usenix.org/conferences",
                        citation = "USENIX Association"
                    )
                )
            }
            else -> {
                list.add(
                    KnowledgeSourceItem(
                        name = "W3C Web Standards & Recommendations",
                        type = KnowledgeSourceType.STANDARD,
                        description = "World Wide Web Consortium international standards for Web architecture, accessibility, and internationalization.",
                        url = "https://www.w3.org/standards/",
                        citation = "World Wide Web Consortium (W3C)"
                    )
                )
                list.add(
                    KnowledgeSourceItem(
                        name = "MDN Web Docs & Technical Specifications",
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
