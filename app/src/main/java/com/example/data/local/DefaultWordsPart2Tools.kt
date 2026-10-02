package com.example.data.local

object DefaultWordsPart2Tools {
    fun getTerms(): List<WordEntity> = listOf(
        // Eval and observability tools (12)
        WordEntity(
            term = "Braintrust",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An enterprise evaluation and observability platform for AI applications that tracks prompt experiments, runs CI/CD regression tests, and logs production traces.",
            keyPoints = "• Integrated eval runner that benchmarks LLM accuracy against ground truth datasets.\n• High-throughput production logging and latency tracking.\n• Prompt playground with versioning and A/B scoring.",
            practicalUses = "• Running automated eval tests in GitHub Actions before deploying prompt changes to production.",
            examples = "Example: Scoring an AI summarizer on faithfulness and concise tone across 500 test cases.",
            referenceUrl = "https://www.braintrust.dev"
        ),
        WordEntity(
            term = "LangSmith",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "LangChain's unified observability and evaluation platform that visualizes every step, tool call, token cost, and latency inside complex LLM chains and agents.",
            keyPoints = "• Deep trace visualization: shows exactly what prompt was sent to each agent step.\n• Annotation queues for human-in-the-loop review.\n• Dataset curation from production failures for continuous testing.",
            practicalUses = "• Debugging why an autonomous agent looped infinitely or picked the wrong tool.",
            examples = "Example: Inspecting an execution tree to identify which subagent added 3 seconds of latency.",
            referenceUrl = "https://www.langchain.com/langsmith"
        ),
        WordEntity(
            term = "Langfuse",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source LLM engineering platform providing distributed tracing, evaluation metrics, prompt management, and cost tracking with full self-hosting support.",
            keyPoints = "• 100% open-source and easy to host locally in Docker.\n• Captures full execution trees, user feedback scores, and token spend.\n• Seamless integration with OpenAI, LangChain, LlamaIndex, and LiteLLM.",
            practicalUses = "• Privacy-conscious companies tracking LLM costs and latency on their own servers.",
            examples = "Example: Self-hosting Langfuse on a private VPC to monitor customer chatbot interactions.",
            referenceUrl = "https://langfuse.com"
        ),
        WordEntity(
            term = "Promptfoo",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A fast, open-source CLI and library for testing prompts, running automated red-teaming security assessments, and comparing model outputs side-by-side.",
            keyPoints = "• Runs assertions against outputs (e.g. contains-json, semantic-similarity, regex).\n• Automated red-teaming checks for prompt injection, jailbreaks, and PII leaks.\n• Integrates cleanly into CI pipelines to block bad prompt changes.",
            practicalUses = "• Security testing AI systems against adversarial jailbreak attacks.",
            examples = "Example: Running 'npx promptfoo eval' before merging a new system prompt.",
            referenceUrl = "https://www.promptfoo.dev"
        ),
        WordEntity(
            term = "Arize Phoenix",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source AI observability platform focused on tracing, evaluation, and embedding analysis for troubleshooting RAG systems and LLM applications.",
            keyPoints = "• Evaluates RAG retrieval relevance, hallucination rate, and QA correctness.\n• Visualizes high-dimensional embeddings in 3D to spot knowledge gaps.\n• Supports OpenTelemetry-based tracing.",
            practicalUses = "• Diagnosing why a RAG retriever pulled irrelevant chunks for a user query.",
            examples = "Example: Visualizing vector clusters to discover missing customer support documentation.",
            referenceUrl = "https://phoenix.arize.com"
        ),
        WordEntity(
            term = "Inspect",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source evaluation framework developed by the UK AI Security Institute (UK AISI) for assessing frontier model capabilities and safety risks.",
            keyPoints = "• Standardized benchmark creation for frontier safety evaluations.\n• Evaluates autonomous cyber capabilities, chemical/biological risks, and deception.\n• Python-based modular architecture.",
            practicalUses = "• National safety testing and frontier lab pre-deployment safety audits.",
            examples = "Example: Testing whether a model can independently find and exploit a buffer overflow in code.",
            referenceUrl = "https://inspect.ai-safety-institute.org.uk"
        ),
        WordEntity(
            term = "DeepEval",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source evaluation framework for LLMs designed like Pytest, making unit testing LLM outputs for hallucination and toxicity feel like normal software testing.",
            keyPoints = "• 14+ out-of-the-box metrics (G-Eval, Hallucination, Faithfulness, Toxicity, Bias).\n• Integrates into standard CI/CD with unit test syntax.\n• Provides actionable failure diagnostics when an assertion fails.",
            practicalUses = "• Writing 'test_customer_support.py' to verify chatbot answers match policy documents.",
            examples = "Example: assert_test(test_case, [hallucination_metric, faithfulness_metric]).",
            referenceUrl = "https://github.com/confident-ai/deepeval"
        ),
        WordEntity(
            term = "Ragas",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Retrieval Augmented Generation Assessment: a specialized evaluation framework for measuring RAG pipeline quality without requiring human ground truth annotations.",
            keyPoints = "• Core metrics: Context Precision, Context Recall, Faithfulness, and Answer Relevance.\n• Synthesizes test datasets automatically from your knowledge base documents.\n• Pinpoints whether bad answers are caused by poor retrieval or poor generation.",
            practicalUses = "• Objectively benchmarking different chunking sizes and embedding models.",
            examples = "Example: Measuring that a chunk size of 512 tokens achieves 92% Faithfulness vs 74% at 1024 tokens.",
            referenceUrl = "https://github.com/explodinggradients/ragas"
        ),
        WordEntity(
            term = "W&B Weave",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Weights & Biases Weave: a lightweight toolkit for tracking, evaluating, and visualizing generative AI applications and agent trajectories.",
            keyPoints = "• Minimalist logging decorator (@weave.op) that captures function inputs and outputs.\n• Built-in evaluation leaderboard to compare prompts and models.\n• Integrates with existing Weights & Biases ML experiment tracking.",
            practicalUses = "• Machine learning teams comparing prompt variations and fine-tuned checkpoints.",
            examples = "Example: Tracking token latency across 10,000 production calls on a W&B dashboard.",
            referenceUrl = "https://wandb.ai/site/weave"
        ),
        WordEntity(
            term = "lm-evaluation-harness",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "EleutherAI's standard open-source benchmarking runner used across the AI community to evaluate language models on hundreds of academic tasks (MMLU, GSM8K, ARC).",
            keyPoints = "• Powers the Hugging Face Open LLM Leaderboard.\n• Supports over 200 standard evaluation tasks out-of-the-box.\n• Standardized zero-shot and few-shot evaluation prompts.",
            practicalUses = "• Verifying the accuracy of a newly pre-trained or fine-tuned open-source model.",
            examples = "Example: Running 'lm_eval --model hf --model_args pretrained=meta-llama/Llama-3-8B --tasks mmlu'.",
            referenceUrl = "https://github.com/EleutherAI/lm-evaluation-harness"
        ),
        WordEntity(
            term = "OpenAI Evals",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "OpenAI's open-source framework and registry for creating and running evaluations against models and APIs to prevent regressions.",
            keyPoints = "• Registry of standardized prompt-and-expected-response benchmarks.\n• Used by OpenAI to evaluate new model candidates before release.\n• Community can contribute new evals to highlight model blind spots.",
            practicalUses = "• Creating regression suites for custom domain capabilities.",
            examples = "Example: Running an eval suite to test model adherence to complex JSON schemas.",
            referenceUrl = "https://github.com/openai/evals"
        ),
        WordEntity(
            term = "OpenTelemetry",
            category = "Evals & Observability",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An industry-wide, vendor-neutral observability standard for instrumenting, generating, and exporting traces, metrics, and logs across distributed systems.",
            keyPoints = "• Avoids vendor lock-in by decoupling telemetry collection from storage backends.\n• OpenInference semantic conventions standardize GenAI spans (prompts, tokens, embeddings).\n• Supported by Datadog, Honeycomb, New Relic, and Langfuse.",
            practicalUses = "• Tracing a request from the Android app, through the API gateway, down to the LLM call.",
            examples = "Example: Emitting an OpenTelemetry span for an agent tool execution with duration and token counts.",
            referenceUrl = "https://opentelemetry.io"
        ),

        // MCP servers and tooling (10)
        WordEntity(
            term = "Playwright MCP",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A Model Context Protocol server that gives AI agents the ability to open a real web browser, navigate pages, click buttons, fill forms, and take screenshots.",
            keyPoints = "• Connects headless Chromium/Firefox/WebKit to an LLM.\n• Allows agents to test web applications and scrape dynamic JavaScript sites.\n• Executes browser interactions via standardized MCP tool calls.",
            practicalUses = "• Autonomous end-to-end web testing, verifying UI layouts, booking flights.",
            examples = "Example: An agent calling browser_navigate('https://example.com') and browser_click('#submit').",
            referenceUrl = "https://github.com/modelcontextprotocol"
        ),
        WordEntity(
            term = "GitHub MCP",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An MCP server that allows AI coding agents to interact with GitHub: searching repos, reading issues, creating branches, and opening pull requests.",
            keyPoints = "• Connects using GitHub personal access tokens.\n• Provides tools: get_issue, create_pull_request, search_repositories, push_files.\n• Enables fully automated GitHub repo maintenance.",
            practicalUses = "• Letting an agent read an issue, clone the repo, fix the bug, and create a PR with zero human typing.",
            examples = "Example: Claude Code using GitHub MCP to create a PR: 'fix: resolve race condition in auth token'.",
            referenceUrl = "https://github.com/modelcontextprotocol/servers/tree/main/src/github"
        ),
        WordEntity(
            term = "Context7",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An MCP service that fetches fresh, real-time documentation and code examples for modern software libraries so agents don't hallucinate outdated APIs.",
            keyPoints = "• Ingests up-to-date docs for frameworks that changed after the model's knowledge cutoff.\n• Delivers concise, relevant code snippets directly into the agent context.\n• Dramatically reduces compile errors on bleeding-edge libraries.",
            practicalUses = "• Providing accurate documentation for Next.js 15, Tailwind v4, or Jetpack Compose 2026 releases.",
            examples = "Example: Agent querying Context7 for the latest syntax of Jetpack Navigation Compose.",
            referenceUrl = "https://context7.com"
        ),
        WordEntity(
            term = "Filesystem MCP",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The foundational MCP server that provides secure, sandboxed file system access (read, write, list directories, move) to local agent harnesses.",
            keyPoints = "• Restricts agent access to explicitly authorized directory paths.\n• Implements tools: read_file, write_file, list_directory, search_files.\n• Essential utility for any coding assistant working on local projects.",
            practicalUses = "• Enabling Claude Desktop or Cursor to inspect and edit files on your hard drive.",
            examples = "Example: Agent reading package.json and updating dependency versions safely.",
            referenceUrl = "https://github.com/modelcontextprotocol/servers/tree/main/src/filesystem"
        ),
        WordEntity(
            term = "Postgres MCP",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An MCP server that lets AI agents inspect PostgreSQL database schemas, run read-only queries, and analyze data tables in natural language.",
            keyPoints = "• Inspects table schemas, foreign keys, and indexes.\n• Can enforce read-only execution modes to prevent accidental data deletion.\n• Enables natural language to SQL queries and data analysis.",
            practicalUses = "• Asking an agent 'Show me top 10 customers by revenue last month' directly from chat.",
            examples = "Example: Agent running SELECT * FROM orders WHERE created_at > NOW() - INTERVAL '30 days'.",
            referenceUrl = "https://github.com/modelcontextprotocol/servers/tree/main/src/postgres"
        ),
        WordEntity(
            term = "Slack MCP",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An MCP integration server that connects agents to Slack workspaces so they can read channels, answer questions, post alerts, and summarize discussions.",
            keyPoints = "• Reads channel history, threads, and user profiles.\n• Posts formatted Slack messages and updates.\n• Authenticates via Slack Bot and User tokens.",
            practicalUses = "• Autonomous standup summarizers, incident response bots, and support triaging.",
            examples = "Example: Agent summarizing a 50-message technical discussion thread in #engineering.",
            referenceUrl = "https://github.com/modelcontextprotocol/servers/tree/main/src/slack"
        ),
        WordEntity(
            term = "Sentry MCP",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An MCP server connecting agents directly to Sentry error monitoring, enabling them to inspect stack traces, fetch error events, and identify root causes.",
            keyPoints = "• Pulls unresolved exception reports and stack traces.\n• Links error traces back to local source code line numbers.\n• Speeds up production incident debugging.",
            practicalUses = "• Having an agent automatically pull the latest production crash report and write the fix.",
            examples = "Example: Agent inspecting a NullPointerException from Sentry and patching the null check in code.",
            referenceUrl = "https://sentry.io"
        ),
        WordEntity(
            term = "Figma MCP",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An MCP server that exposes Figma design file trees, component properties, colors, and layout tokens directly to AI frontend coding agents.",
            keyPoints = "• Reads vector paths, auto-layout constraints, and typography tokens from Figma.\n• Bridges the gap between UI design and frontend code generation.\n• Generates accurate Jetpack Compose, Tailwind, or SwiftUI code matching designs.",
            practicalUses = "• Converting a Figma mockup into pixel-perfect Compose UI code instantly.",
            examples = "Example: Agent inspecting button padding, hex colors, and corner radius directly from a Figma frame.",
            referenceUrl = "https://figma.com"
        ),
        WordEntity(
            term = "MCP Inspector",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An official interactive web tool for testing, exploring, and debugging Model Context Protocol servers before connecting them to production agents.",
            keyPoints = "• Provides an interactive browser UI to test tools, prompts, and resources on an MCP server.\n• Validates input schemas and views raw JSON-RPC messages.\n• Indispensable developer tool when building custom MCP servers.",
            practicalUses = "• Testing your own custom Python/TypeScript MCP server locally.",
            examples = "Example: Running 'npx @modelcontextprotocol/inspector' to debug tool inputs.",
            referenceUrl = "https://github.com/modelcontextprotocol/inspector"
        ),
        WordEntity(
            term = "Smithery",
            category = "MCP & Tooling",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A public registry, discovery directory, and CLI package manager for finding and installing Model Context Protocol (MCP) servers with 1 click.",
            keyPoints = "• Community registry listing hundreds of verified MCP servers.\n• 1-click installation for Claude Desktop and other agent harnesses.\n• Automated dependency management and setup scripts.",
            practicalUses = "• Quickly searching for and installing new tools for your local AI desktop agent.",
            examples = "Example: Running 'npx -y @smithery/cli install postgres' to configure a database tool in Claude.",
            referenceUrl = "https://smithery.ai"
        ),

        // Vector databases and retrieval (8)
        WordEntity(
            term = "Pinecone",
            category = "Vector Databases",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A fully managed, cloud-native vector database designed for high-scale semantic search, AI memory, and RAG pipelines without managing infrastructure.",
            keyPoints = "• Serverless architecture with automatic scaling and metadata filtering.\n• Ultra-fast approximate nearest neighbor (ANN) search over billions of vectors.\n• Enterprise security, high availability, and SOC 2 compliance.",
            practicalUses = "• Production RAG knowledge bases, recommendation engines, and long-term agent memory.",
            examples = "Example: Upserting document vector embeddings and querying top_k=5 nearest matches.",
            referenceUrl = "https://www.pinecone.io"
        ),
        WordEntity(
            term = "Weaviate",
            category = "Vector Databases",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source vector database that combines vector search with structured BM25 keyword search, offering hybrid retrieval and built-in vectorization modules.",
            keyPoints = "• Native hybrid search (combining dense vector math with sparse BM25 text match).\n• Built-in AI modules that vectorize raw text or images automatically.\n• Can be self-hosted in Docker/Kubernetes or used as a managed cloud service.",
            practicalUses = "• Multimodal image and text search, hybrid enterprise document search.",
            examples = "Example: Querying 'find images matching cozy mountain cabin' using multimodal vector embeddings.",
            referenceUrl = "https://weaviate.io"
        ),
        WordEntity(
            term = "Qdrant",
            category = "Vector Databases",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A high-performance open-source vector similarity search engine written in Rust, renowned for blazing speed, low memory usage, and advanced payload filtering.",
            keyPoints = "• Written in Rust for maximum memory efficiency and speed.\n• Rich JSON payload filtering combined with vector cosine similarity.\n• Quantization support (scalar and product quantization) to fit massive datasets in RAM.",
            practicalUses = "• High-concurrency recommendation systems and real-time semantic search.",
            examples = "Example: Filtering vectors by { 'category': 'networking', 'year': 2026 } while running cosine search.",
            referenceUrl = "https://qdrant.tech"
        ),
        WordEntity(
            term = "Chroma",
            category = "Vector Databases",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A lightweight, open-source AI-native embedding database that runs completely in-memory or embedded in Python/JavaScript applications with zero setup.",
            keyPoints = "• Zero-configuration: pip install chromadb and you have an embedding store.\n• Embedded or client-server mode backed by SQLite and ClickHouse.\n• Ideal default choice for developers starting local RAG experiments.",
            practicalUses = "• Local desktop apps, educational tutorials, and self-contained prototypes.",
            examples = "Example: chroma_client.create_collection('docs').add(documents=['...']).",
            referenceUrl = "https://www.trychroma.com"
        ),
        WordEntity(
            term = "pgvector",
            category = "Vector Databases",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source extension for PostgreSQL that adds native vector embedding data types, HNSW indexing, and distance operators directly inside standard SQL tables.",
            keyPoints = "• Stores vector embeddings right next to normal relational customer data in Postgres.\n• Eliminates the need to maintain a separate dedicated vector database.\n• Supports HNSW and IVFFlat indexing for fast approximate nearest neighbor search.",
            practicalUses = "• 80%+ of early startups who already run PostgreSQL and want simple, unified RAG.",
            examples = "Example: SELECT * FROM documents ORDER BY embedding <=> '[0.12, 0.45, ...]' LIMIT 5;",
            referenceUrl = "https://github.com/pgvector/pgvector"
        ),
        WordEntity(
            term = "Milvus",
            category = "Vector Databases",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source, highly scalable distributed vector database built to manage trillions of high-dimensional vector embeddings across enterprise clusters.",
            keyPoints = "• Distributed cloud-native architecture decoupling storage from compute.\n• Powers massive internet-scale enterprise search systems.\n• Supports multiple indexing algorithms (HNSW, IVF, ScaNN).",
            practicalUses = "• Global enterprise search engines, massive e-commerce catalogs with 100M+ items.",
            examples = "Example: Distributed cluster handling 10,000 vector queries per second across 1 billion items.",
            referenceUrl = "https://milvus.io"
        ),
        WordEntity(
            term = "FAISS",
            category = "Vector Databases",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Facebook AI Similarity Search: Meta's C++ library for ultra-fast vector clustering and approximate nearest neighbor search, optimized for GPUs and CPUs.",
            keyPoints = "• Raw algorithmic library rather than a full database.\n• Extreme GPU acceleration for indexing and searching billions of vectors in milliseconds.\n• Powers the vector search engine inside many commercial products.",
            practicalUses = "• High-performance academic research and offline batch similarity matching.",
            examples = "Example: faiss.IndexFlatL2(d) indexing millions of vectors on an NVIDIA H100 GPU.",
            referenceUrl = "https://github.com/facebookresearch/faiss"
        ),
        WordEntity(
            term = "Elasticsearch",
            category = "Vector Databases",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The industry-standard distributed search and analytics engine that now combines full-text BM25 keyword search with native dense vector embeddings for hybrid retrieval.",
            keyPoints = "• Combines traditional inverted index keyword search with dense vector similarity.\n• Reciprocal Rank Fusion (RRF) automatically merges keyword and vector rankings.\n• Battle-tested enterprise reliability and multi-node sharding.",
            practicalUses = "• Enterprise knowledge search where users search for both exact IDs/codes and fuzzy conceptual meaning.",
            examples = "Example: Querying an internal legal database for both exact contract numbers and conceptual topic matches.",
            referenceUrl = "https://www.elastic.co"
        )
    )
}
