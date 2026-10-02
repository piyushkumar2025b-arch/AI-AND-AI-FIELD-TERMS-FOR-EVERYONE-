package com.example.data.local

object DefaultWordsPart3CtoG {
    fun getTerms(): List<WordEntity> = listOf(
        // C
        WordEntity(
            term = "Cache / caching",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "Storing copies of frequently requested data in fast temporary memory so future requests can be served instantly without repeating expensive calculations or network calls.",
            keyPoints = "• Drastically reduces latency and backend server load.\n• Can be applied in memory (Redis), in browsers (HTTP cache), or at edge networks (CDNs).\n• Requires cache invalidation policies (TTL, LRU) to prevent serving stale data.",
            practicalUses = "• Caching API responses in Room or Redis so offline users see instant content.",
            examples = "Example: In this app, dictionary terms are cached in local SQLite so opening the app is instantaneous.",
            referenceUrl = "https://en.wikipedia.org/wiki/Cache_(computing)"
        ),
        WordEntity(
            term = "Cache breakpoint",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "A designated marker placed in an LLM prompt that instructs the provider to save the preceding context in GPU memory cache for instant reuse on subsequent calls.",
            keyPoints = "• Used in Anthropic prompt caching to save up to 90% on input token costs.\n• Up to 4 breakpoints can be defined in a request.\n• Reused tokens process up to 4x faster with massive latency reductions.",
            practicalUses = "• Reusing a 100-page API documentation file across 50 consecutive developer questions.",
            examples = "Example: Setting 'cache_control: { type: \"ephemeral\" }' on system instructions in Anthropic API.",
            referenceUrl = "https://docs.anthropic.com/en/docs/build-with-claude/prompt-caching"
        ),
        WordEntity(
            term = "Cache hit",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "When a requested piece of data is found successfully in fast cache memory, avoiding a slow round trip to the original database or API.",
            keyPoints = "• Opposite of a 'cache miss' (which forces a slow disk or network read).\n• High cache hit ratio (e.g. 95%+) indicates an efficient, cost-effective system.\n• In LLM prompt caching, a cache hit means paying only a fraction of normal input token costs.",
            practicalUses = "• Serving web assets from local CDN nodes in 5ms.",
            examples = "Example: An HTTP 304 Not Modified response or a prompt cache read returning instantly.",
            referenceUrl = "https://en.wikipedia.org/wiki/Cache_(computing)#Cache_hit"
        ),
        WordEntity(
            term = "Cache read/write tokens",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "The token billing units used by LLM providers: writing new context into cache costs slightly more upfront (~25%), while reading cached tokens costs up to 90% less on subsequent calls.",
            keyPoints = "• Dramatically slashes repetitive API costs for agentic loops and coding harnesses.\n• Cache write: token price charged when caching a prompt for the first time.\n• Cache read: deeply discounted rate charged when reusing that cached prompt later.",
            practicalUses = "• Running 20-step coding agent loops affordably because the codebase is cached after turn 1.",
            examples = "Example: Anthropic charging $0.375 per million tokens for cache reads vs $3.00 for regular prompt tokens.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Canvas",
            category = "Tools & UI",
            part = "Part 3: A to Z master list",
            humanMeaning = "An interactive, split-screen workspace interface in AI apps (like ChatGPT Canvas) that lets users co-edit documents, text, or code side-by-side with an AI assistant.",
            keyPoints = "• Moves beyond ephemeral chat bubbles to durable, living documents.\n• Supports targeted inline edits, reading levels, and automated debugging.\n• Keeps the full work in view while chatting about specific paragraphs.",
            practicalUses = "• Iteratively drafting essays, blog posts, or Python scripts with an AI co-author.",
            examples = "Example: Highlighting a function in Canvas and asking ChatGPT to 'add error handling here'.",
            referenceUrl = "https://openai.com/index/introducing-canvas/"
        ),
        WordEntity(
            term = "Capability evals",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "Evaluations and benchmarks designed to discover the upper limit of what an AI model can do (math, coding, visual spatial reasoning, language translation).",
            keyPoints = "• Measures model power and frontier intelligence.\n• Tested on challenging benchmarks like SWE-bench, Olympiad math, and GPQA.\n• Contrasts with safety evals (which test what a model refuses to do).",
            practicalUses = "• Deciding which model is powerful enough to handle autonomous software engineering.",
            examples = "Example: Testing whether a model can solve college-level organic chemistry exam questions.",
            referenceUrl = "https://en.wikipedia.org/wiki/Evaluation_of_artificial_intelligence"
        ),
        WordEntity(
            term = "Chain-of-thought",
            category = "Reasoning & Models",
            part = "Part 3: A to Z master list",
            humanMeaning = "A prompting and reasoning technique where the AI breaks complex questions down and reasons step-by-step before producing its final answer.",
            keyPoints = "• Discovered in the seminal paper 'Chain-of-Thought Prompting Elicits Reasoning in Large Language Models'.\n• Giving the model 'thinking space' tokens allows it to compute intermediate logical steps.\n• Dramatically improves performance on math, logic puzzles, and code synthesis.",
            practicalUses = "• Adding 'Let's think step by step' to prompts to prevent simple arithmetic mistakes.",
            examples = "Example: An AI writing out the algebraic steps: Step 1: 5 * 4 = 20, Step 2: 20 + 3 = 23.",
            referenceUrl = "https://en.wikipedia.org/wiki/Chain-of-thought_prompting"
        ),
        WordEntity(
            term = "Chatbot",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "A software application designed to simulate human conversation via text or voice, ranging from simple rule-based scripts to modern generative LLMs like ChatGPT.",
            keyPoints = "• Conversational user interface.\n• Can answer questions, look up orders, and guide users through workflows.\n• Foundation for modern autonomous agents.",
            practicalUses = "• Customer support portals, personal companions, automated FAQ responders.",
            examples = "Example: A bank chatbot helping you check your account balance in a chat bubble.",
            referenceUrl = "https://en.wikipedia.org/wiki/Chatbot"
        ),
        WordEntity(
            term = "Chunking",
            category = "RAG & Context",
            part = "Part 3: A to Z master list",
            humanMeaning = "Breaking down large documents or codebases into smaller, bite-sized text passages (chunks) before generating vector embeddings for RAG retrieval.",
            keyPoints = "• Chunks must be small enough to stay semantically focused, but large enough to preserve context.\n• Techniques: fixed-size chunking (with overlap), semantic chunking, syntax-aware code chunking.\n• Overlap (e.g. 50 tokens) prevents concepts from getting severed at chunk borders.",
            practicalUses = "• Slicing a 300-page PDF manual into 500-token chunks for vector database indexing.",
            examples = "Example: Chunking a Kotlin file by function definitions rather than arbitrary character counts.",
            referenceUrl = "https://en.wikipedia.org/wiki/Chunking_(information_processing)"
        ),
        WordEntity(
            term = "Citations",
            category = "RAG & Context",
            part = "Part 3: A to Z master list",
            humanMeaning = "Direct source references, footnotes, or links provided in an AI answer pointing back to the exact passage or document used to generate each fact.",
            keyPoints = "• Essential for verification and building user trust in enterprise AI.\n• Prevents unseen hallucinations by tying statements to grounding sources.\n• Built-in feature in Perplexity, Gemini, and enterprise RAG systems.",
            practicalUses = "• Allowing lawyers or doctors to verify the exact document paragraph behind an AI answer.",
            examples = "Example: 'The return window is 30 days [Doc: RefundPolicy.pdf, Page 3]'.",
            referenceUrl = "https://en.wikipedia.org/wiki/Citation"
        ),
        WordEntity(
            term = "CLI",
            category = "Harness Building Blocks",
            part = "Part 3: A to Z master list",
            humanMeaning = "Command-Line Interface: a text-based user interface where users and AI agents interact with software by typing commands into a terminal rather than clicking buttons.",
            keyPoints = "• Lightweight, fast, and easily scriptable by automated tools.\n• Standard environment for software development, server management, and git.\n• The preferred playground for autonomous coding agents (Claude Code, Aider).",
            practicalUses = "• Running build tools, git commands, and server management scripts.",
            examples = "Example: Running 'git status' or 'curl -s https://api.openrouter.ai' in a bash terminal.",
            referenceUrl = "https://en.wikipedia.org/wiki/Command-line_interface"
        ),
        WordEntity(
            term = "Client",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "The hardware device or software program (like a web browser or mobile app) that requests services, data, or computing resources from a central server.",
            keyPoints = "• Initiates communication by sending requests.\n• Renders the user interface and handles local touch interactions.\n• Communicates with servers over networks via protocols like HTTP or WebSocket.",
            practicalUses = "• This Android smartphone app acting as the client that displays words and calls OpenRouter.",
            examples = "Example: Your phone's Chrome browser requesting index.html from a remote web server.",
            referenceUrl = "https://en.wikipedia.org/wiki/Client_(computing)"
        ),
        WordEntity(
            term = "Client-server",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "A computing architecture where tasks are partitioned between service providers (servers) that store data and clients that request and display that data.",
            keyPoints = "• Centralizes business logic and data storage on secure servers.\n• Clients remain lightweight and focus on presentation.\n• The fundamental architectural blueprint of the World Wide Web.",
            practicalUses = "• Mobile banking apps, cloud software, streaming media services.",
            examples = "Example: The client sends login credentials; the server verifies them against the database and returns a token.",
            referenceUrl = "https://en.wikipedia.org/wiki/Client%E2%80%93server_model"
        ),
        WordEntity(
            term = "Closed source",
            category = "Business & Strategy",
            part = "Part 3: A to Z master list",
            humanMeaning = "Proprietary software or AI models where the creator keeps the source code, training weights, and architecture secret, accessible only via paid APIs.",
            keyPoints = "• Creators monetize by charging for access without revealing how it works.\n• Protects company intellectual property and commercial advantage.\n• Examples include OpenAI GPT-4o, Anthropic Claude, and Google Gemini.",
            practicalUses = "• Using frontier models that cost tens of millions of dollars to train without having to buy supercomputers.",
            examples = "Example: Accessing Claude 3.5 Sonnet through the Anthropic API where weights remain strictly private.",
            referenceUrl = "https://en.wikipedia.org/wiki/Proprietary_software"
        ),
        WordEntity(
            term = "Cloud (AWS/GCP/Azure)",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "On-demand availability of computer system resources (servers, storage, databases, networking, AI GPUs) over the internet with pay-as-you-go pricing.",
            keyPoints = "• The Big Three: Amazon Web Services (AWS), Google Cloud Platform (GCP), Microsoft Azure.\n• Eliminates the need to buy and maintain physical data centers.\n• Enables instant global scaling from 1 to 10,000 servers.",
            practicalUses = "• Hosting apps, databases, and AI model training fleets with high availability.",
            examples = "Example: Renting an NVIDIA H100 GPU cluster on AWS for 2 hours to fine-tune a model.",
            referenceUrl = "https://en.wikipedia.org/wiki/Cloud_computing"
        ),
        WordEntity(
            term = "Code execution tool",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "A tool that allows an AI model to write executable code (Python, Bash, Kotlin) and run it in real time to calculate math, test logic, or manipulate files.",
            keyPoints = "• Solves LLM limitations with arithmetic, date calculations, and deterministic data sorting.\n• Sandboxed to prevent security breaches.\n• Returns stdout and stderr back to the model's context window.",
            practicalUses = "• Performing precise statistical calculations and verifying code snippets before giving them to users.",
            examples = "Example: ChatGPT Code Interpreter executing Python to analyze a CSV spreadsheet and draw a chart.",
            referenceUrl = "https://en.wikipedia.org/wiki/Execution_(computing)"
        ),
        WordEntity(
            term = "Code execution with MCP",
            category = "MCP & Tooling",
            part = "Part 3: A to Z master list",
            humanMeaning = "Executing programming code inside a standardized Model Context Protocol server environment, giving the agent a safe, audited sandbox for terminal commands.",
            keyPoints = "• Standardizes code execution tools across different agent harnesses.\n• Restricts filesystem access and environment variables safely.\n• Streamlines multi-language execution (Python, Node, Bash).",
            practicalUses = "• Giving Claude Desktop the ability to run local scripts safely.",
            examples = "Example: An MCP server exposing an 'execute_bash' tool with timeout and output capture.",
            referenceUrl = "https://modelcontextprotocol.io"
        ),
        WordEntity(
            term = "Coding round",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A technical job interview session where an applicant is asked to live-code algorithms, build a feature, or debug code in front of an interviewer.",
            keyPoints = "• Evaluates problem-solving speed, clean code structure, and debugging composure.\n• Focuses on data structures, system design, or domain development (Android, Compose).\n• Modern interviews increasingly allow AI coding assistants to test agentic collaboration.",
            practicalUses = "• Proving engineering mastery during recruitment at top tech firms.",
            examples = "Example: Building an Android screen using Jetpack Compose to display a list of dictionary words in 45 minutes.",
            referenceUrl = "https://en.wikipedia.org/wiki/Technical_interview"
        ),
        WordEntity(
            term = "Cold outreach",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "Reaching out proactively to founders, hiring managers, or potential clients who don't know you yet (via LinkedIn or email) to pitch your skills or product.",
            keyPoints = "• Highly effective when personalized with specific proof of work.\n• Highlight concrete value: 'I built an offline dictionary using your API, here is the demo'.\n• Bypasses competitive automated job application resume filters.",
            practicalUses = "• Landing engineering interviews and freelance software development clients.",
            examples = "Example: Sending a direct LinkedIn message to an engineering director with a 30-second video demo of a project.",
            referenceUrl = "https://en.wikipedia.org/wiki/Cold_calling"
        ),
        WordEntity(
            term = "Commit",
            category = "Harness Building Blocks",
            part = "Part 3: A to Z master list",
            humanMeaning = "A permanent saved snapshot of file changes recorded into a Git repository history, labeled with a descriptive message explaining what changed.",
            keyPoints = "• Atomic unit of version control history.\n• Creates a unique cryptographic SHA-1/SHA-256 hash identifying the exact state.\n• Allows rewinding mistakes and reviewing team code contributions.",
            practicalUses = "• Saving incremental working progress when building features.",
            examples = "Example: git commit -m 'feat: add dark mode toggle switch and persistence'.",
            referenceUrl = "https://git-scm.com/docs/git-commit"
        ),
        WordEntity(
            term = "Compaction",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "Summarizing and compressing long conversation history so an ongoing agent session stays within context token limits without losing key facts or instructions.",
            keyPoints = "• Trims verbose terminal outputs while preserving core conclusions.\n• Prevents hitting maximum context window limits during 50-turn coding sessions.\n• Keeps model responses fast, focused, and cost-effective.",
            practicalUses = "• Enabling an AI coding agent to continue working on a codebase for 3 straight hours without crashing.",
            examples = "Example: Replacing 500 lines of compiler logs with the single line: 'Build succeeded with 0 warnings'.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Compliance (SOC 2, GDPR)",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Adhering to strict legal, security, and privacy standards to protect user data (GDPR in Europe for privacy, SOC 2 in enterprise security auditing).",
            keyPoints = "• SOC 2: certifies that a company protects customer data against breaches.\n• GDPR: grants users the right to data access, correction, and deletion ('right to be forgotten').\n• Mandatory requirement for selling enterprise B2B software.",
            practicalUses = "• Guaranteeing enterprise customers that their proprietary prompts won't be used to train public AI models.",
            examples = "Example: An enterprise requiring an AI vendor to sign a Business Associate Agreement (BAA) and SOC 2 Type II audit.",
            referenceUrl = "https://en.wikipedia.org/wiki/General_Data_Protection_Regulation"
        ),
        WordEntity(
            term = "Compute",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "The raw processing power (GPUs, TPUs, CPUs) required to train machine learning models and generate live token inferences, measured in FLOPs.",
            keyPoints = "• The primary bottleneck and largest financial expense in frontier AI development.\n• Governed by scaling laws: more compute consistently yields smarter models.\n• Dominated by specialized hardware like NVIDIA H100/B200 GPUs and Google TPUs.",
            practicalUses = "• Calculating the GPU hours needed to train or run inference on a 70B parameter model.",
            examples = "Example: Training a frontier foundation model requiring $100M+ worth of GPU compute time.",
            referenceUrl = "https://en.wikipedia.org/wiki/Computing"
        ),
        WordEntity(
            term = "Computer use",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "Anthropic's capability that allows Claude to look at a desktop screen, move the mouse cursor, click buttons, and type text to operate any standard desktop app.",
            keyPoints = "• Interacts with software the same way humans do (keyboard and mouse coordinates).\n• Bypasses the need for custom APIs: if a human can click it, the agent can use it.\n• Takes screenshots to observe the visual state after each click.",
            practicalUses = "• Automating legacy enterprise desktop software that lacks modern REST APIs.",
            examples = "Example: Claude opening a spreadsheet app, clicking row 4, typing a formula, and saving the file.",
            referenceUrl = "https://docs.anthropic.com/en/docs/build-with-claude/computer-use"
        ),
        WordEntity(
            term = "Computer vision",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "The field of artificial intelligence that trains computers to interpret, understand, and extract meaningful information from digital images and videos.",
            keyPoints = "• Powers object detection, facial recognition, image classification, and OCR.\n• Combines Convolutional Neural Networks (CNNs) and Vision Transformers (ViTs).\n• Native component of multimodal frontier LLMs (GPT-4o, Claude 3.5, Gemini 2.5).",
            practicalUses = "• Autonomous self-driving cars reading road signs; medical imaging detecting tumors.",
            examples = "Example: Uploading a photo of a whiteboard diagram and having the AI generate Android Compose code for it.",
            referenceUrl = "https://en.wikipedia.org/wiki/Computer_vision"
        ),
        WordEntity(
            term = "Connectors",
            category = "RAG & Context",
            part = "Part 3: A to Z master list",
            humanMeaning = "Pre-built software integrations that automatically sync documents and data from external platforms (Google Drive, Notion, Slack, Jira) into an AI knowledge base.",
            keyPoints = "• Handles authentication, permission checking, and automatic incremental sync.\n• Extracts raw text and metadata from proprietary document formats.\n• Core building block of enterprise search and RAG platforms.",
            practicalUses = "• Connecting an AI assistant to your company's Google Docs folder so it answers questions from meeting notes.",
            examples = "Example: LlamaIndex Notion connector continuously indexing updated engineering docs.",
            referenceUrl = "https://en.wikipedia.org/wiki/Enterprise_search"
        ),
        WordEntity(
            term = "Consistency (CAP theorem)",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "The guarantee in distributed database systems that every client sees the exact same, most up-to-date data at the same moment, regardless of which node they read from.",
            keyPoints = "• Part of the CAP Theorem: a distributed system can only guarantee two out of Consistency, Availability, and Partition Tolerance.\n• Strong consistency requires waiting for all database replicas to confirm writes.\n• Eventual consistency allows temporary discrepancies to maximize speed and availability.",
            practicalUses = "• Demanding strong consistency for bank account balances; allowing eventual consistency for social media likes.",
            examples = "Example: Google Spanner using atomic clocks to provide globally distributed strong consistency.",
            referenceUrl = "https://en.wikipedia.org/wiki/CAP_theorem"
        ),
        WordEntity(
            term = "Constitutional AI",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Anthropic's alignment training methodology where an AI model uses a written set of core principles (a 'constitution') to critique and refine its own behavior.",
            keyPoints = "• Replaces thousands of expensive human feedback ratings with automated AI-assisted self-critique.\n• Guides model tone to be helpful, honest, and harmless without becoming preachy.\n• Makes safety guidelines transparent, inspectable, and easily updateable.",
            practicalUses = "• Training Claude models to refuse dangerous requests politely while answering benign questions openly.",
            examples = "Example: The model reviews its own draft against the principle 'Choose the response that is least harmful' and revises it.",
            referenceUrl = "https://www.anthropic.com/news/claudes-constitution"
        ),
        WordEntity(
            term = "Container (Docker)",
            category = "CI/CD & DevOps",
            part = "Part 3: A to Z master list",
            humanMeaning = "A standardized, lightweight software package containing an application and everything it needs to run (code, runtime, system libraries) in isolated uniformity.",
            keyPoints = "• Shares the host OS kernel, making it start in milliseconds with minimal RAM overhead.\n• Guarantees consistent execution across laptops, staging servers, and cloud clusters.\n• Defined by a declarative Dockerfile and deployed via Docker or Kubernetes.",
            practicalUses = "• Running an AI inference server or sandboxed coding agent safely.",
            examples = "Example: 'docker run -d -p 5432:5432 postgres' launching a local database in 2 seconds.",
            referenceUrl = "https://en.wikipedia.org/wiki/OS-level_virtualization"
        ),
        WordEntity(
            term = "Context",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "The complete bundle of information (system prompt, conversation history, user query, retrieved documents, tool outputs) provided to an LLM for a specific generation.",
            keyPoints = "• Large language models are stateless; they only know what is present in the current context.\n• Everything the model needs to make a decision must be injected into the context.\n• Constrained by the model's context window limit.",
            practicalUses = "• Supplying an error log and source code file so the model understands why an app crashed.",
            examples = "Example: Passing the user's past 3 questions so the AI understands follow-up pronouns like 'it'.",
            referenceUrl = "https://en.wikipedia.org/wiki/Context_(language_use)"
        ),
        WordEntity(
            term = "Context clash",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "When two contradictory instructions, conflicting document facts, or opposing system prompts exist inside the context window, confusing the model.",
            keyPoints = "• Degrades reasoning and causes erratic, unpredictable model behavior.\n• Common in RAG when retrieved documents contain conflicting policies from different years.\n• Resolved by chronological sorting, explicit authority hierarchies, or prompt deduplication.",
            practicalUses = "• Preventing customer support bots from giving contradictory refund answers.",
            examples = "Example: Document A says 'Return within 14 days' while Document B says 'Return within 30 days'.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Context compaction",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "Condensing long conversational transcripts and tool outputs into concise summaries so long-running agent sessions don't exhaust the token window.",
            keyPoints = "• Replaces massive raw logs with actionable takeaways.\n• Keeps recent turn details pristine while summarizing older background turns.\n• Vital technique for building reliable 50+ turn coding agents.",
            practicalUses = "• Keeping a coding agent focused on the active task after reviewing 30 previous files.",
            examples = "Example: Condensing 50 pages of terminal outputs into: 'Database schema migration applied successfully'.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Context confusion",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "When an LLM gets overwhelmed by excessive irrelevant details or poorly formatted data in its prompt, causing it to overlook the user's core question.",
            keyPoints = "• More context is not always better; uncurated data introduces noise.\n• Can cause models to hallucinate or latch onto tangential trivia.\n• Solved by clean prompt templating and precise RAG re-ranking.",
            practicalUses = "• Stripping unnecessary CSS and HTML tags before feeding a web page to an AI summarizer.",
            examples = "Example: An AI giving the wrong answer because the prompt was buried inside 50,000 tokens of boilerplate.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Context degradation",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "The observed decrease in an LLM's reasoning quality and attention precision as the context window fills up with hundreds of thousands of tokens.",
            keyPoints = "• Also known as the 'Needle In A Haystack' phenomenon or middle-context blindness.\n• Models pay highest attention to the beginning (system prompt) and end (user query) of the context.\n• Mitigated by prompt caching, re-ranking, and keeping prompts concise.",
            practicalUses = "• Structuring prompts so critical instructions appear at the very start and end of the prompt.",
            examples = "Example: A model missing an important rule placed in the middle of a 200,000 token prompt.",
            referenceUrl = "https://arxiv.org/abs/2307.03172"
        ),
        WordEntity(
            term = "Context distraction",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "When tangential information, obsolete debug logs, or noisy comments in the prompt divert the model's focus away from executing the main objective.",
            keyPoints = "• Models naturally attend to emotionally charged or repetitive text patterns.\n• Outdated error logs from 10 turns ago can trick the model into re-fixing solved bugs.\n• Pruning old tool outputs keeps the agent strictly on track.",
            practicalUses = "• Cleaning terminal scratchpads before asking an agent to write the final release code.",
            examples = "Example: An agent obsessing over an irrelevant deprecation warning instead of fixing the crashing null pointer.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Context editing / pruning / trimming",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "Programmatically removing unnecessary tokens, old tool outputs, and redundant conversational turns from the prompt to optimize space and focus.",
            keyPoints = "• Slashes API token costs and speeds up generation time.\n• Prevents context degradation and rot over multi-turn workflows.\n• Standard practice in advanced agent harnesses like Claude Code and Cursor.",
            practicalUses = "• Truncating a 10,000-line test output down to the last 20 failing lines.",
            examples = "Example: Replacing raw HTML scrapes with markdown text before passing them to the model.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Context engineering",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "The modern software discipline of dynamically curating, formatting, pruning, and structuring the exact right information to inject into an LLM's prompt window.",
            keyPoints = "• The natural evolution beyond basic string 'prompt engineering'.\n• Encompasses RAG retrieval, MCP tool schemas, memory files, and token compaction.\n• The single most impactful lever for building reliable AI software applications.",
            practicalUses = "• Orchestrating repository maps, recent git diffs, and lint errors into a compact 10,000-token prompt.",
            examples = "Example: Injecting only the relevant Room database schema when a user asks about offline storage.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Context poisoning",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "When inaccurate information, hallucinated facts, or malicious prompt injection attacks get permanently accepted into an agent's memory or context history.",
            keyPoints = "• Once poisoned, subsequent reasoning loops build upon the false premise.\n• Can cause cascading errors across multi-turn agent sessions.\n• Requires session rollbacks or resetting memory files to recover.",
            practicalUses = "• Sanitizing third-party website scrapes before injecting them into an agent's working memory.",
            examples = "Example: An agent reading a blog post that claims an API method exists, causing it to generate non-existent code for 10 turns.",
            referenceUrl = "https://en.wikipedia.org/wiki/Prompt_injection"
        ),
        WordEntity(
            term = "Context rot",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "The gradual degradation of an agent's performance over long conversational sessions as old, obsolete assumptions accumulate in context history.",
            keyPoints = "• Analogous to software bit rot: longer sessions become sluggish and unfocused.\n• The model spends attention tokens rereading irrelevant early exploration steps.\n• Cured by spinning up fresh subagents with clean context windows.",
            practicalUses = "• Knowing when to start a new chat session instead of continuing a 100-message thread.",
            examples = "Example: An agent getting confused because it still remembers temporary dummy files deleted 30 minutes ago.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Context window",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "The maximum number of tokens (words and punctuation) that an AI model can read and process in a single interaction (e.g. 128k, 200k, or 2M tokens).",
            keyPoints = "• Acts as the model's short-term working memory capacity.\n• Modern frontier models support from 128,000 up to 2,000,000 tokens.\n• Larger windows allow ingesting whole books, code repositories, or hour-long videos.",
            practicalUses = "• Ingesting an entire Android project into Gemini 2.5 Flash in one single prompt.",
            examples = "Example: Gemini 1.5/2.5 Pro processing a 1-million-token context containing a full codebase.",
            referenceUrl = "https://en.wikipedia.org/wiki/Large_language_model#Context_window"
        ),
        WordEntity(
            term = "Contract",
            category = "Business & Strategy",
            part = "Part 3: A to Z master list",
            humanMeaning = "A legally binding agreement between an enterprise client and an AI service provider defining pricing, usage limits, data privacy, and service level agreements (SLAs).",
            keyPoints = "• Governs enterprise software procurement.\n• Specifies zero data retention clauses (guaranteeing prompts aren't used for training).\n• Outlines uptime commitments and liability caps.",
            practicalUses = "• Negotiating an annual $50,000 enterprise agreement with Anthropic or OpenAI.",
            examples = "Example: Adding a clause stating all customer data must be encrypted with customer-managed keys.",
            referenceUrl = "https://en.wikipedia.org/wiki/Contract"
        ),
        WordEntity(
            term = "Copilot",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "An interactive AI companion that works alongside a human user inside their software, offering suggestions, auto-completing tasks, and answering questions.",
            keyPoints = "• Keeps the human in the driver's seat ('human-in-the-loop').\n• Suggests solutions rather than taking fully autonomous unreviewed actions.\n• Popularized by GitHub Copilot, Microsoft 365 Copilot, and Cursor.",
            practicalUses = "• Writing code faster by accepting multi-line tab completions in your editor.",
            examples = "Example: Pressing Tab in VS Code to accept a suggested Kotlin function implementation.",
            referenceUrl = "https://en.wikipedia.org/wiki/Microsoft_Copilot"
        ),
        WordEntity(
            term = "Copyright",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Legal rights protecting original intellectual works, currently at the center of global debates over whether training AI on internet text and images constitutes fair use.",
            keyPoints = "• Lawsuits by authors, newspapers, and artists against AI labs (NYT vs OpenAI).\n• AI-generated works without human authorship generally cannot be copyrighted under current US law.\n• Enterprise AI providers offer copyright indemnification to shield business customers.",
            practicalUses = "• Ensuring commercial AI software does not reproduce copyrighted code or media verbatim.",
            examples = "Example: Guardrails preventing an image generator from recreating Disney characters with trademarked names.",
            referenceUrl = "https://en.wikipedia.org/wiki/Copyright"
        ),
        WordEntity(
            term = "CTC / compensation",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "Cost to Company / Total Compensation: the complete financial package offered to a tech employee, including base salary, annual bonuses, equity (RSUs), and benefits.",
            keyPoints = "• AI and machine learning engineering compensations are among the highest in the tech sector.\n• Equity grants often comprise 40% to 70%+ of total compensation at late-stage startups and Big Tech.\n• CTC is the common term used in India; 'Total Comp' (TC) in the US.",
            practicalUses = "• Evaluating and negotiating job offers from AI frontier labs or enterprise tech companies.",
            examples = "Example: A compensation offer consisting of $180,000 base + $120,000 annual RSU equity grant.",
            referenceUrl = "https://en.wikipedia.org/wiki/Total_compensation"
        ),
        WordEntity(
            term = "CUDA",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Compute Unified Device Architecture: NVIDIA's proprietary parallel computing platform and programming model that allows software to use NVIDIA GPUs for general processing.",
            keyPoints = "• The secret weapon behind NVIDIA's AI dominance and massive competitive moat.\n• Powers PyTorch, TensorFlow, vLLM, and all major deep learning frameworks.\n• Executes matrix multiplications thousands of times faster than traditional CPUs.",
            practicalUses = "• Accelerating AI training and inference on NVIDIA GPUs (RTX 4090, H100, B200).",
            examples = "Example: Setting device = torch.device('cuda') in PyTorch to train a model on GPU.",
            referenceUrl = "https://en.wikipedia.org/wiki/CUDA"
        ),
        WordEntity(
            term = "Custom GPTs",
            category = "Tools & UI",
            part = "Part 3: A to Z master list",
            humanMeaning = "Customized versions of ChatGPT created with specific instructions, uploaded reference files, and external web APIs that anyone can build without coding.",
            keyPoints = "• Built directly inside ChatGPT web interface via natural language dialogue.\n• Can be shared publicly or published to the GPT Store.\n• Supports custom action schemas (calling third-party REST APIs).",
            practicalUses = "• Creating a personalized coding assistant that knows your private company coding style.",
            examples = "Example: A custom GPT that critiques resumes against tech industry hiring rubrics.",
            referenceUrl = "https://openai.com/blog/introducing-gpts"
        ),
        WordEntity(
            term = "Custom instructions",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "User preferences saved in your AI account settings that are automatically injected into the system prompt of every conversation to tailor responses to your style.",
            keyPoints = "• Eliminates the need to repeat your background and preferences in every prompt.\n• Can specify your role ('I am an Android developer using Kotlin') and output style ('Be concise, no fluff').\n• Supported in ChatGPT, Claude, and Gemini.",
            practicalUses = "• Ensuring the AI always responds with production-ready Kotlin code instead of generic Java.",
            examples = "Example: Custom instruction: 'Never apologize, always use Jetpack Compose, avoid button outlines'.",
            referenceUrl = "https://openai.com/blog/custom-instructions-for-chatgpt"
        )
    )
}
