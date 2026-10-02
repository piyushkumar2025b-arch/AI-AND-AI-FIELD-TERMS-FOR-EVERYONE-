package com.example.data.local

object DefaultWordsPart3DandE {
    fun getTerms(): List<WordEntity> = listOf(
        // D (22)
        WordEntity(
            term = "Data center",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "A physical building housing thousands of networked computer servers, storage systems, and specialized cooling infrastructure that powers cloud services and AI models.",
            keyPoints = "• Features redundant power supplies, backup diesel generators, and massive internet backbones.\n• Modern AI data centers require hundreds of megawatts of electricity to power GPU clusters.\n• Run by major cloud hyperscalers (Amazon, Google, Microsoft, Meta).",
            practicalUses = "• Housing the NVIDIA GPU superclusters that train and serve modern frontier AI models.",
            examples = "Example: A 500,000-square-foot data center in Northern Virginia hosting thousands of cloud servers.",
            referenceUrl = "https://en.wikipedia.org/wiki/Data_center"
        ),
        WordEntity(
            term = "Data cleaning",
            category = "Data & Datasets",
            part = "Part 3: A to Z master list",
            humanMeaning = "The process of fixing or removing incorrect, corrupted, duplicate, or improperly formatted records from a dataset before using it to train machine learning models.",
            keyPoints = "• 'Garbage in, garbage out': model quality depends directly on clean training data.\n• Involves deduplication, filtering HTML noise, stripping PII, and fixing encoding errors.\n• Often consumes 80% of a data science team's preparation time.",
            practicalUses = "• Filtering out spam and automated bot text from web scrapes before training an LLM.",
            examples = "Example: Removing 50 million duplicate marketing pages from Common Crawl dataset.",
            referenceUrl = "https://en.wikipedia.org/wiki/Data_cleansing"
        ),
        WordEntity(
            term = "Data lake",
            category = "Data & Datasets",
            part = "Part 3: A to Z master list",
            humanMeaning = "A centralized storage repository that holds vast amounts of raw, unprocessed data in its native format (structured, semi-structured, and unstructured) until needed.",
            keyPoints = "• Built on cheap object storage like Amazon S3 or Google Cloud Storage.\n• Schema-on-read: data is structured only when queried, unlike rigid relational databases.\n• Foundation for large-scale big data analytics and AI model training pipelines.",
            practicalUses = "• Storing raw server logs, user clickstreams, and sensor data for future ML experiments.",
            examples = "Example: Ingesting 10 Terabytes of raw JSON logs daily into an AWS S3 data lake.",
            referenceUrl = "https://en.wikipedia.org/wiki/Data_lake"
        ),
        WordEntity(
            term = "Data pipeline",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "An automated sequence of software steps that extracts raw data from various sources, transforms it into clean formats, and loads it into a destination database.",
            keyPoints = "• Often implemented as ETL (Extract, Transform, Load) or ELT pipelines.\n• Orchestrated by workflow engines like Apache Airflow, Prefect, or dbt.\n• Ensures fresh, reliable data is available for production dashboards and AI models.",
            practicalUses = "• Ingesting customer transaction data nightly, transforming currency values, and updating analytics.",
            examples = "Example: An hourly Airflow DAG that pulls new customer support tickets and indexes them in a vector DB.",
            referenceUrl = "https://en.wikipedia.org/wiki/Data_pipeline"
        ),
        WordEntity(
            term = "Data retention",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Policies and rules defining how long an AI company or service keeps copies of your prompts, responses, and user data on their servers before permanently deleting them.",
            keyPoints = "• Zero Data Retention (ZDR) guarantees prompts are deleted immediately after generating answers.\n• Standard consumer tiers often retain data for 30 days for safety abuse monitoring.\n• Crucial enterprise compliance factor for healthcare, finance, and defense.",
            practicalUses = "• Signing a Zero Data Retention agreement so proprietary company code is never stored on external servers.",
            examples = "Example: Enterprise OpenAI API tier guaranteeing 0-day retention of prompt data.",
            referenceUrl = "https://en.wikipedia.org/wiki/Data_retention"
        ),
        WordEntity(
            term = "Data scientist",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A professional who uses statistics, mathematics, and machine learning techniques to analyze complex data sets, uncover insights, and build predictive models.",
            keyPoints = "• Proficient in Python, R, SQL, Pandas, and Scikit-Learn.\n• Translates messy business numbers into actionable strategic decisions.\n• Increasingly collaborates with AI engineers to evaluate and fine-tune foundation models.",
            practicalUses = "• Building predictive churn models to identify which subscription customers are about to cancel.",
            examples = "Example: Analyzing 1 million user interactions to identify the optimal price points for a new product.",
            referenceUrl = "https://en.wikipedia.org/wiki/Data_science"
        ),
        WordEntity(
            term = "Data structures and algorithms",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "The foundational computer science building blocks (arrays, trees, graphs, sorting, searching) used to organize, store, and manipulate data efficiently in software.",
            keyPoints = "• Measured using Big O notation for time and space complexity.\n• Critical foundation for software engineering performance and coding interview rounds.\n• Underpins everything from database indexes (B-Trees) to AI vector search (HNSW graphs).",
            practicalUses = "• Selecting a HashMap (O(1) lookup) instead of an Array (O(n) search) for fast word searching in this dictionary.",
            examples = "Example: Using a binary search algorithm to find an item in a sorted list of 1 million items in only 20 steps.",
            referenceUrl = "https://en.wikipedia.org/wiki/Data_structure"
        ),
        WordEntity(
            term = "Database",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "An organized electronic collection of structured information or data stored digitally in a computer system and managed by a DBMS for rapid search and retrieval.",
            keyPoints = "• Relational (SQL) databases like PostgreSQL use structured tables with ACID guarantees.\n• Non-relational (NoSQL) databases like MongoDB store flexible documents or key-value pairs.\n• Embedded databases like Room (SQLite) store data locally on mobile devices without servers.",
            practicalUses = "• Persisting user dictionary additions and bookmarks on-device in this Android application.",
            examples = "Example: Android Room database managing the local SQLite storage on your phone.",
            referenceUrl = "https://en.wikipedia.org/wiki/Database"
        ),
        WordEntity(
            term = "Dataset",
            category = "Data & Datasets",
            part = "Part 3: A to Z master list",
            humanMeaning = "A curated collection of data (text, images, questions, answers) structured for analysis, benchmarking, or training machine learning models.",
            keyPoints = "• Can be categorized into training sets, validation sets, and test sets.\n• Shared publicly on platforms like Hugging Face and Kaggle.\n• High-quality curated datasets are the most valuable asset in modern AI training.",
            practicalUses = "• Training an AI model on 10,000 human-reviewed coding problem solutions.",
            examples = "Example: The Common Crawl dataset containing petabytes of public internet text.",
            referenceUrl = "https://en.wikipedia.org/wiki/Data_set"
        ),
        WordEntity(
            term = "Deep learning",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "A subset of machine learning based on artificial neural networks with multiple layers (deep architectures) that can automatically learn representations from raw data.",
            keyPoints = "• Eliminates the need for manual feature engineering by learning features directly from data.\n• Architectures include CNNs (vision), RNNs/LSTMs (sequences), and Transformers (language).\n• The core technology powering modern generative AI, speech recognition, and autonomous vehicles.",
            practicalUses = "• Translating spoken audio into text in real time; recognizing faces in photos.",
            examples = "Example: A 96-layer Transformer neural network learning the statistical structure of human language.",
            referenceUrl = "https://en.wikipedia.org/wiki/Deep_learning"
        ),
        WordEntity(
            term = "Deep research",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "An agentic capability (like OpenAI Deep Research or Gemini Deep Research) that spends 10 to 45 minutes browsing dozens of web sources to synthesize a comprehensive research report.",
            keyPoints = "• Formulates its own search queries, browses multiple websites, and follows citations.\n• Reads PDFs, cross-references claims, and resolves contradictory information.\n• Outputs exhaustive, multi-page analytical reports with academic citations.",
            practicalUses = "• Conducting competitive market intelligence, investment thesis research, or scientific literature reviews.",
            examples = "Example: Asking an AI to research the competitive landscape of solid-state batteries and receiving a 20-page cited report.",
            referenceUrl = "https://openai.com/index/introducing-deep-research/"
        ),
        WordEntity(
            term = "Deepfake",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Synthetic media (video, photo, or audio) generated or manipulated by deep learning models to convincingly depict a real person saying or doing things they never did.",
            keyPoints = "• Powered by Generative Adversarial Networks (GANs) and diffusion models.\n• Raises major concerns for fraud, political disinformation, and non-consensual imagery.\n• Countered by digital watermarking standards (C2PA) and biometric detection tools.",
            practicalUses = "• Movie special effects, digital actor dubbing into other languages, game avatars.",
            examples = "Example: A cloned voice scam pretending to be a CEO instructing an accountant to wire funds.",
            referenceUrl = "https://en.wikipedia.org/wiki/Deepfake"
        ),
        WordEntity(
            term = "Deferred tool loading",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "An optimization where an agent environment holds back detailed tool schemas until the model explicitly searches for or requests them, saving context tokens.",
            keyPoints = "• Prevents dumping 100 tool definitions into the prompt upfront.\n• Exposes tool titles and brief descriptions; injects full schemas on-demand.\n• Keeps the system prompt lean and reduces API costs.",
            practicalUses = "• Building enterprise agent harnesses connected to hundreds of internal microservice APIs.",
            examples = "Example: Loading the 'GitHub PR tool schema' only after the user mentions opening a pull request.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Demo",
            category = "Business & Strategy",
            part = "Part 3: A to Z master list",
            humanMeaning = "A working demonstration of a software product or AI feature designed to showcase its functionality, speed, and real-world value to clients or investors.",
            keyPoints = "• Essential milestone in venture capital fundraising and enterprise sales.\n• Must focus on solving a clear user pain point rather than just showing raw technology.\n• Great demos feel like magic in under 60 seconds.",
            practicalUses = "• Showing a live mobile app demo to prove a product is ready for customers.",
            examples = "Example: Demonstrating offline search in this Android app to show instant responsiveness without internet.",
            referenceUrl = "https://en.wikipedia.org/wiki/Technology_demonstration"
        ),
        WordEntity(
            term = "Deploy / deployment",
            category = "CI/CD & DevOps",
            part = "Part 3: A to Z master list",
            humanMeaning = "The process of packaging, releasing, and running a software application or AI model on production servers or app stores where real users can access it.",
            keyPoints = "• Automated via CI/CD pipelines (GitHub Actions, Argo CD).\n• Strategies: rolling deployment, blue-green deployment, canary releases.\n• Monitored closely for crashes and performance regressions after release.",
            practicalUses = "• Publishing a new version of an Android APK to the Google Play Console.",
            examples = "Example: Merging a pull request to trigger an automated deployment to Google Cloud Run.",
            referenceUrl = "https://en.wikipedia.org/wiki/Software_deployment"
        ),
        WordEntity(
            term = "Desktop app",
            category = "Tools & UI",
            part = "Part 3: A to Z master list",
            humanMeaning = "A software application that installs and runs locally on a personal computer operating system (Windows, macOS, Linux), like Cursor, LM Studio, or Claude Desktop.",
            keyPoints = "• Direct access to local computer hardware (GPU, local files, terminal shells).\n• Built with native frameworks (Swift, Rust, WPF) or cross-platform web shells (Electron, Tauri).\n• Essential environment for developers running local models and coding agents.",
            practicalUses = "• Running high-performance code editors and offline AI desktop companions.",
            examples = "Example: Cursor, VS Code, or LM Studio running directly on your MacBook.",
            referenceUrl = "https://en.wikipedia.org/wiki/Application_software"
        ),
        WordEntity(
            term = "Developer prompt",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "The foundational system prompt written by software engineers that defines the AI model's persona, operational rules, tool calling schemas, and behavioral boundaries.",
            keyPoints = "• Injected at the top of the context before any user messages.\n• Sets non-negotiable rules ('Never reveal system secrets; always respond in JSON').\n• Governs tone, reasoning style, and security posture.",
            practicalUses = "• Instructing an agent to follow strict Material 3 design and avoid button outlines.",
            examples = "Example: 'You are an expert Android engineer. Answer concisely and use Kotlin Compose exclusively.'",
            referenceUrl = "https://platform.openai.com/docs/guides/prompt-engineering"
        ),
        WordEntity(
            term = "Diffusion model",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "A generative AI architecture that generates high-fidelity images, audio, or video by iteratively removing random Gaussian noise from a blank canvas step by step.",
            keyPoints = "• Training corrupts images with noise; the model learns the reverse denoising process.\n• Powers Midjourney, Stable Diffusion, DALL-E 3, and Sora.\n• Replaced legacy GANs as the state of the art in visual generative AI.",
            practicalUses = "• Generating photorealistic graphics, UI mockups, and application launcher icons.",
            examples = "Example: The app icon for this dictionary was synthesized using a diffusion image generation model.",
            referenceUrl = "https://en.wikipedia.org/wiki/Diffusion_model"
        ),
        WordEntity(
            term = "Distillation",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "A training technique where a small, fast, and cheap 'student' model is trained to mimic the reasoning and outputs of a massive, expensive 'teacher' model.",
            keyPoints = "• Compresses frontier model intelligence into lightweight models that run on smartphones.\n• Student learns from the soft probability distributions (dark knowledge) of the teacher.\n• Slashes inference costs and latency while preserving 90%+ of capabilities.",
            practicalUses = "• Creating models like DeepSeek-R1-Distill-Qwen or Llama-3-8B that perform like giant models.",
            examples = "Example: Distilling a 70B reasoning model into an 8B model to run locally on consumer laptops.",
            referenceUrl = "https://en.wikipedia.org/wiki/Knowledge_distillation"
        ),
        WordEntity(
            term = "DPO",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Direct Preference Optimization: an elegant alignment algorithm that trains models directly on human preference pairs (chosen vs. rejected) without needing a separate reward model.",
            keyPoints = "• Replaces complex, unstable RLHF (PPO) reinforcement learning pipelines with a simple classification loss.\n• Much faster, computationally cheaper, and more mathematically stable than RLHF.\n• Widely adopted across open-source and frontier post-training pipelines.",
            practicalUses = "• Aligning open-weight models to follow instructions and avoid toxic answers.",
            examples = "Example: Feeding pairs of [Prompt, Good Answer, Bad Answer] into DPO to align model tone.",
            referenceUrl = "https://arxiv.org/abs/2305.18290"
        ),
        WordEntity(
            term = "Drift",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "The gradual degradation of a model's real-world accuracy over time because production user inputs or real-world conditions change compared to its original training data.",
            keyPoints = "• Concept drift: statistical properties of the target variable change over time.\n• Data drift: the distribution of input data changes (e.g. new slang, new laws, new libraries).\n• Detected by continuous monitoring and automated evaluation benchmarks.",
            practicalUses = "• Catching when an e-commerce fraud detection model becomes less accurate due to novel fraud techniques.",
            examples = "Example: An AI coding model giving broken code because a major framework released a breaking v2.0 update.",
            referenceUrl = "https://en.wikipedia.org/wiki/Concept_drift"
        ),

        // E (19)
        WordEntity(
            term = "Edge AI",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Running machine learning algorithms and inference directly on local edge hardware (smartphones, IoT devices, local gateways) rather than sending data to remote cloud servers.",
            keyPoints = "• 100% offline functionality with zero internet dependency.\n• Zero latency from cloud network round-trips.\n• Absolute privacy: sensitive camera, audio, or health data never leaves the device.",
            practicalUses = "• On-device voice dictation, facial recognition unlocking, offline camera translation.",
            examples = "Example: Running a small language model on an Android smartphone NPU without internet.",
            referenceUrl = "https://en.wikipedia.org/wiki/Edge_computing"
        ),
        WordEntity(
            term = "Effort level",
            category = "Reasoning & Models",
            part = "Part 3: A to Z master list",
            humanMeaning = "A configurable parameter (low, medium, high) in reasoning models that controls how many hidden thinking tokens the model is allowed to generate before answering.",
            keyPoints = "• Low effort: answers fast with minimal reasoning tokens, saving cost and time.\n• High effort: spends thousands of thinking tokens verifying edge cases and proofs.\n• Introduced in OpenAI o-series models (e.g. reasoning_effort: 'high').",
            practicalUses = "• Setting effort to 'high' for mission-critical compiler bugs, and 'low' for simple text formatting.",
            examples = "Example: Passing { \"reasoning_effort\": \"low\" } in an API call for quick casual answers.",
            referenceUrl = "https://platform.openai.com"
        ),
        WordEntity(
            term = "Elicitation",
            category = "Safety & Evals",
            part = "Part 3: A to Z master list",
            humanMeaning = "Techniques used by researchers to draw out, uncover, and evaluate hidden or latent capabilities in a model that it might not demonstrate during basic prompting.",
            keyPoints = "• Uses scaffolding, multi-step agent loops, and fine-tuned prompting to probe model limits.\n• Evaluates whether a model secretly possesses dangerous cyber or bioweapon knowledge.\n• Standard methodology in frontier safety institute audits (UK AISI, US AISI).",
            practicalUses = "• Rigorously testing if a model can discover novel zero-day cybersecurity exploits.",
            examples = "Example: Providing an agent with an automated terminal loop to test if it can break out of a virtual machine.",
            referenceUrl = "https://en.wikipedia.org/wiki/Expert_elicitation"
        ),
        WordEntity(
            term = "Embedding",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "A mathematical representation of a word, sentence, or image as a long list of numbers (a vector in high-dimensional space) where similar concepts sit close together.",
            keyPoints = "• Translates human meaning into geometric coordinates (e.g. 1536 floating-point numbers).\n• Words with related meanings (like 'king' and 'queen', or 'TCP' and 'UDP') have high cosine similarity.\n• The mathematical backbone of vector databases and semantic RAG search.",
            practicalUses = "• Searching a database for concepts rather than exact keyword matches.",
            examples = "Example: Text 'how to set up DNS' converts to [0.014, -0.082, 0.421, ...].",
            referenceUrl = "https://en.wikipedia.org/wiki/Word_embedding"
        ),
        WordEntity(
            term = "Emergent abilities",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Surprising skills and capabilities that appear abruptly in large AI models as they scale up in size and compute, which were neither present nor predictable in smaller models.",
            keyPoints = "• Skills like multi-digit arithmetic, translation, and step-by-step logic appear at scale.\n• Described as 'quantitative changes leading to qualitative leaps'.\n• Subject of intense scientific debate regarding whether the emergence is sudden or gradual.",
            practicalUses = "• Understanding why a 70B parameter model suddenly excels at tasks an 8B model fails completely.",
            examples = "Example: A model suddenly learning to explain jokes once it reaches a certain parameter threshold.",
            referenceUrl = "https://en.wikipedia.org/wiki/Emergence"
        ),
        WordEntity(
            term = "Endpoint",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "A specific web URL address exposed by an API server where clients send network requests to access specific data or execute functions.",
            keyPoints = "• Identified by URL path and HTTP verb (e.g. POST /v1/chat/completions).\n• Backed by controller code that processes inputs and returns responses.\n• Documented with OpenAPI / Swagger specifications.",
            practicalUses = "• Sending prompts to OpenRouter's completions endpoint.",
            examples = "Example: 'https://openrouter.ai/api/v1/chat/completions' is OpenRouter's primary inference endpoint.",
            referenceUrl = "https://en.wikipedia.org/wiki/Web_service#Endpoints"
        ),
        WordEntity(
            term = "Enterprise plan",
            category = "Business & Strategy",
            part = "Part 3: A to Z master list",
            humanMeaning = "A premium corporate tier of an AI service offering dedicated SLAs, administrative dashboards, single sign-on (SSO), data privacy guarantees, and custom billing.",
            keyPoints = "• Guarantees that enterprise customer prompts will never be used to train future models.\n• Provides higher rate limits and priority GPU capacity during peak hours.\n• Includes dedicated account managers and custom security reviews.",
            practicalUses = "• Purchasing team-wide ChatGPT, Claude, or GitHub Copilot licenses for 500 corporate employees.",
            examples = "Example: Anthropic Enterprise plan featuring SCIM provisioning and 500,000 token context windows.",
            referenceUrl = "https://en.wikipedia.org/wiki/Software_as_a_service"
        ),
        WordEntity(
            term = "Entry-level",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A starting career position (Junior Engineer, Associate AI Engineer) designed for recent university graduates or self-taught developers starting in tech.",
            keyPoints = "• Focuses on learning fundamentals, clean code execution, and receiving mentorship.\n• Modern entry-level engineers gain massive leverage by mastering AI coding harnesses.\n• Emphasizes strong foundational computer science and eagerness to learn.",
            practicalUses = "• Landing your first junior software development job at a tech startup or enterprise.",
            examples = "Example: A junior Android developer building mobile screens under the guidance of a senior architect.",
            referenceUrl = "https://en.wikipedia.org/wiki/Entry-level_job"
        ),
        WordEntity(
            term = "Environment (dev/staging/prod)",
            category = "CI/CD & DevOps",
            part = "Part 3: A to Z master list",
            humanMeaning = "The three isolated software deployment stages: Dev (local laptop coding), Staging (testing replica of production), and Prod (the live system used by real customers).",
            keyPoints = "• Isolates experiments so unfinished or buggy code never impacts paying customers.\n• Dev: fast iterative experimentation.\n• Staging: realistic end-to-end integration and load testing.\n• Prod: hardened, monitored live application.",
            practicalUses = "• Testing a new OpenRouter model integration in staging before releasing it to production users.",
            examples = "Example: Running tests on https://staging-api.example.com before deploying to https://api.example.com.",
            referenceUrl = "https://en.wikipedia.org/wiki/Deployment_environment"
        ),
        WordEntity(
            term = "Equity",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "Ownership shares or stock options in a company granted to employees as part of their compensation, which can become extremely valuable if the startup succeeds.",
            keyPoints = "• Typically vests over a 4-year period with a 1-year cliff.\n• Startup equity (options) carries risk; public company equity (RSUs) is equivalent to cash.\n• A major component of compensation at high-growth AI unicorns.",
            practicalUses = "• Joining an early-stage AI startup with a 0.5% equity grant.",
            examples = "Example: 10,000 stock options vesting over 4 years that become worth $500,000 at company acquisition.",
            referenceUrl = "https://en.wikipedia.org/wiki/Equity_sharing"
        ),
        WordEntity(
            term = "Error analysis",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "The systematic process of inspecting where and why an AI model failed on evaluation benchmarks, categorizing the failures to guide prompt or data improvements.",
            keyPoints = "• Groups failures into taxonomies: e.g. hallucination, formatting violation, missing knowledge, math error.\n• Highlights the highest-leverage fixes instead of blindly tweaking prompts.\n• Essential methodology for moving an AI prototype into production reliability.",
            practicalUses = "• Finding out that 70% of chatbot failures were caused by outdated PDF documents in the vector store.",
            examples = "Example: Reviewing 100 failed test cases and realizing the model misinterprets markdown tables.",
            referenceUrl = "https://en.wikipedia.org/wiki/Error_analysis"
        ),
        WordEntity(
            term = "ETL",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "Extract, Transform, Load: a traditional data integration process that pulls raw data from multiple sources, cleans and reformats it, and loads it into a destination database.",
            keyPoints = "• Extract: reads data from production SQL databases, APIs, or log files.\n• Transform: deduplicates, normalizes currency, strips PII, and calculates aggregates.\n• Load: writes clean data into a data warehouse (Snowflake, BigQuery) for reporting.",
            practicalUses = "• Processing overnight sales figures into daily executive analytics dashboards.",
            examples = "Example: A Python script extracting CSV reports from Shopify, converting dates to ISO-8601, and loading into Postgres.",
            referenceUrl = "https://en.wikipedia.org/wiki/Extract,_transform,_load"
        ),
        WordEntity(
            term = "EU AI Act",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "The European Union's landmark comprehensive legal framework regulating artificial intelligence based on risk categories (unacceptable, high, medium, and low risk).",
            keyPoints = "• First comprehensive global law governing AI development and deployment.\n• Bans unacceptable risks (social scoring, biometric surveillance in public).\n• Imposes strict transparency, testing, and copyright reporting on general-purpose frontier models.",
            practicalUses = "• Ensuring European customer-facing AI products meet risk assessment and audit standards.",
            examples = "Example: High-risk AI hiring systems requiring explicit human oversight, bias audits, and technical documentation.",
            referenceUrl = "https://en.wikipedia.org/wiki/Artificial_Intelligence_Act"
        ),
        WordEntity(
            term = "Eval",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "Short for evaluation: an automated test suite or scoring metric used to systematically measure how accurately an AI model performs on defined tasks.",
            keyPoints = "• Replaces subjective 'vibes-based' testing with hard quantitative metrics.\n• Can use deterministic checks (regex, JSON validation) or LLM-as-a-judge scoring.\n• Run continuously in CI/CD to prevent regressions when modifying prompts.",
            practicalUses = "• Verifying that a new system prompt improves accuracy from 82% to 91% across 200 customer questions.",
            examples = "Example: Running an eval script that grades 500 AI answers against gold-standard answers.",
            referenceUrl = "https://en.wikipedia.org/wiki/Evaluation_of_artificial_intelligence"
        ),
        WordEntity(
            term = "Eval-driven development",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "The AI software engineering practice of writing benchmark evaluation test suites first before engineering prompts, RAG pipelines, or fine-tuning models.",
            keyPoints = "• The AI equivalent of Test-Driven Development (TDD) in traditional programming.\n• Establishes baseline metrics so you know if a change actually helped.\n• Prevents 'whack-a-mole' prompt engineering where fixing one edge case breaks three others.",
            practicalUses = "• Building a dataset of 50 tricky edge cases before writing your first system prompt.",
            examples = "Example: Creating an eval dataset of Android compiler errors to measure which model fixes them best.",
            referenceUrl = "https://en.wikipedia.org/wiki/Test-driven_development"
        ),
        WordEntity(
            term = "Experiment tracking",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "Logging and comparing hyperparameters, training loss curves, prompts, and evaluation scores across different model training and prompt engineering runs.",
            keyPoints = "• Managed by tools like Weights & Biases (W&B), MLflow, and Langfuse.\n• Ensures scientific reproducibility across machine learning experiments.\n• Visualizes metric trends and charts to spot which configuration performs best.",
            practicalUses = "• Comparing accuracy across 10 different prompt variations and temperature settings.",
            examples = "Example: A W&B dashboard showing validation accuracy climbing across 20 training epochs.",
            referenceUrl = "https://en.wikipedia.org/wiki/Hyperparameter_optimization"
        ),
        WordEntity(
            term = "Explainability",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "The ability to explain in understandable terms why an AI model made a specific prediction, generated a particular answer, or chose a certain action.",
            keyPoints = "• Tackles the 'black box' problem of deep neural networks.\n• Critical for regulated domains (healthcare, banking, judicial decisions).\n• Techniques include feature attribution, attention visualization, and mechanistic interpretability.",
            practicalUses = "• Providing a legally required explanation when an AI denies a loan application.",
            examples = "Example: Highlighting the three financial metrics that contributed most heavily to a risk score.",
            referenceUrl = "https://en.wikipedia.org/wiki/Explainable_artificial_intelligence"
        ),
        WordEntity(
            term = "Exponential backoff",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "An algorithm that exponentially increases the wait time between failed API retry attempts (e.g. 1s, 2s, 4s, 8s) to prevent hammering an already overloaded server.",
            keyPoints = "• Combined with 'jitter' (random small delays) to prevent thousands of clients from retrying at the exact same millisecond.\n• Standard best practice when encountering HTTP 429 (Rate Limit) or 503 (Service Unavailable).\n• Prevents the 'thundering herd' problem.",
            practicalUses = "• Handling OpenRouter or OpenAI API rate limits gracefully in your mobile app client.",
            examples = "Example: An API call fails with 429; the app waits 1 sec, then 2 sec, then 4 sec before giving up.",
            referenceUrl = "https://en.wikipedia.org/wiki/Exponential_backoff"
        ),
        WordEntity(
            term = "Extended thinking",
            category = "Reasoning & Models",
            part = "Part 3: A to Z master list",
            humanMeaning = "A model mode (like in Claude 3.7 Sonnet) where the user sets a thinking token budget (up to 64k tokens), giving the model massive cognitive space to reason through hard problems.",
            keyPoints = "• Allows models to explore multiple solution hypotheses, critique its own work, and correct mistakes internally.\n• The internal reasoning tokens are rendered in a collapsible thinking block.\n• Delivers massive breakthroughs in complex full-stack architecture, math, and competitive programming.",
            practicalUses = "• Solving intricate multi-file architectural refactoring tasks without human intervention.",
            examples = "Example: Granting Claude 8,000 thinking tokens to debug an asynchronous race condition in a Kotlin coroutine.",
            referenceUrl = "https://www.anthropic.com"
        )
    )
}
