package com.example.data.local

object DefaultWordsPart3FtoM {
    fun getTerms(): List<WordEntity> = listOf(
        // F
        WordEntity(
            term = "Failure modes taxonomy",
            category = "Safety & Evals",
            part = "Part 3: A to Z master list",
            humanMeaning = "A structured categorization of the specific ways an AI system can fail (e.g., hallucination, prompt injection, reasoning breakdown, schema violation).",
            keyPoints = "• Moves beyond vague complaints ('it didn't work') to root-cause debugging.\n• Identifies whether failure originated in retrieval, context packing, or model reasoning.\n• Guides team priorities on where to invest engineering resources.",
            practicalUses = "• Creating a bug tracking label system for an enterprise AI support bot.",
            examples = "Example: Classifying 100 customer bugs into: 40% retrieval failure, 35% hallucination, 25% instruction non-compliance.",
            referenceUrl = "https://en.wikipedia.org/wiki/Failure_mode_and_effects_analysis"
        ),
        WordEntity(
            term = "Fairness",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Ensuring an AI model makes unbiased, equitable decisions and does not discriminate against individuals or groups based on race, gender, or background.",
            keyPoints = "• Quantified through mathematical parity metrics (demographic parity, equal opportunity).\n• Involves cleaning historical bias out of training datasets.\n• Required by consumer protection laws and international regulations.",
            practicalUses = "• Auditing AI hiring algorithms to ensure equal selection rates across demographic groups.",
            examples = "Example: Verifying that an AI credit approval model gives equal interest rates to equal income profiles.",
            referenceUrl = "https://en.wikipedia.org/wiki/Fairness_(machine_learning)"
        ),
        WordEntity(
            term = "Faithfulness",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "A metric measuring whether an AI's generated response is strictly grounded in and truthful to the provided context documents, with zero made-up hallucinations.",
            keyPoints = "• Primary quality metric in RAG (Retrieval-Augmented Generation) systems.\n• Scored by checking if every claim in the answer is directly supported by the context.\n• Evaluated automatically using frameworks like Ragas or TruLens.",
            practicalUses = "• Ensuring medical or legal AI assistants never invent facts outside provided case notes.",
            examples = "Example: If context mentions 'dosage is 5mg' and model says 'dosage is 10mg', faithfulness score is 0.",
            referenceUrl = "https://en.wikipedia.org/wiki/Hallucination_(artificial_intelligence)"
        ),
        WordEntity(
            term = "Fallback",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "A safety backup mechanism that automatically routes a request to an alternative model, provider, or cached response whenever the primary AI service fails or times out.",
            keyPoints = "• Ensures high availability (99.99%) even during frontier lab outages.\n• Fallback chains: e.g. Claude 3.5 Sonnet -> GPT-4o -> Gemini 2.5 Flash -> Local cache.\n• Built-in feature in AI gateways like OpenRouter, Portkey, and LiteLLM.",
            practicalUses = "• Preventing customer app crashes when OpenAI or Anthropic experiences an API outage.",
            examples = "Example: If primary model returns 503 Overloaded, OpenRouter automatically routes to an alternate provider.",
            referenceUrl = "https://en.wikipedia.org/wiki/Fault_tolerance"
        ),
        WordEntity(
            term = "Fault tolerance",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "The ability of a software system to continue operating properly without crashing even when one or more of its underlying components (servers, networks, APIs) fail.",
            keyPoints = "• Achieved through redundancy, automated retries, and circuit breakers.\n• Graceful degradation: secondary features disable while core functionality remains online.\n• Essential architectural requirement for mission-critical enterprise systems.",
            practicalUses = "• Designing this app so it works 100% offline using Room if internet is disconnected.",
            examples = "Example: A database cluster automatically promoting a replica to leader within 2 seconds of a primary server crash.",
            referenceUrl = "https://en.wikipedia.org/wiki/Fault_tolerance"
        ),
        WordEntity(
            term = "Feature store",
            category = "Data & Datasets",
            part = "Part 3: A to Z master list",
            humanMeaning = "A centralized data management layer that stores, manages, and serves curated machine learning features for both offline training and online low-latency inference.",
            keyPoints = "• Solves training-serving skew by guaranteeing consistent feature calculations.\n• Dual storage: fast in-memory store (Redis) for real-time inference, data warehouse for training.\n• Popular platforms include Feast, Hopsworks, and Tecton.",
            practicalUses = "• Serving real-time user click features to recommendation models in under 10ms.",
            examples = "Example: Looking up a customer's 'average purchase amount last 30 days' during checkout fraud detection.",
            referenceUrl = "https://en.wikipedia.org/wiki/Feature_store"
        ),
        WordEntity(
            term = "Few-shot",
            category = "Prompt Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "Providing the AI with a few concrete examples (shots) of inputs and expected outputs inside the prompt to demonstrate the desired format, style, or logic.",
            keyPoints = "• One of the easiest and most effective ways to boost prompt accuracy.\n• Teaches edge-case handling and formatting without fine-tuning weights.\n• Contrasts with 'zero-shot' (no examples provided).",
            practicalUses = "• Showing the model 2 examples of how to parse raw text into clean JSON.",
            examples = "Example: Prompting: 'Input: 12-05-2026 -> Output: 2026-12-05. Input: 03-01-2024 -> Output: 2024-03-01. Now convert: 08-09-2025 ->'.",
            referenceUrl = "https://en.wikipedia.org/wiki/In-context_learning_(natural_language_processing)"
        ),
        WordEntity(
            term = "Files API",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "An API endpoint provided by AI labs (OpenAI, Anthropic, Google) that lets you upload large files (PDFs, images, videos) once and reference them across multiple prompts.",
            keyPoints = "• Avoids re-uploading massive megabyte files in base64 on every single API call.\n• Files are cached server-side for hours or days.\n• Essential for analyzing large documents and video footage efficiently.",
            practicalUses = "• Uploading a 200-page financial quarterly report once and asking 20 follow-up questions.",
            examples = "Example: Calling client.files.create(file=open('report.pdf')) and passing file_id in chat completions.",
            referenceUrl = "https://platform.openai.com/docs/api-reference/files"
        ),
        WordEntity(
            term = "Fine-tuning",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Taking a pre-trained base model and training it further on a smaller, specialized dataset to adapt its style, vocabulary, or domain performance.",
            keyPoints = "• Adjusts underlying model weights (unlike RAG or prompt engineering).\n• Parameter-Efficient Fine-Tuning (PEFT/LoRA) trains only 1% of weights, saving massive GPU compute.\n• Ideal for teaching custom internal jargon, rigid JSON output formats, or medical nuances.",
            practicalUses = "• Training an 8B model to generate SQL matching private company database conventions.",
            examples = "Example: Fine-tuning Llama 3 on 5,000 legal contracts using LoRA on an NVIDIA GPU.",
            referenceUrl = "https://en.wikipedia.org/wiki/Fine-tuning_(deep_learning)"
        ),
        WordEntity(
            term = "Forward-deployed engineer",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A high-impact engineering role popularized by Palantir where developers work directly onsite with enterprise clients to build, integrate, and deploy custom AI solutions.",
            keyPoints = "• Combines deep technical software engineering with high-level customer empathy.\n• Builds custom integrations bridging customer legacy data with modern AI platforms.\n• High autonomy and high business impact.",
            practicalUses = "• Deploying defense, intelligence, or healthcare AI platforms directly inside customer premises.",
            examples = "Example: Working onsite at a hospital system to integrate an AI medical imaging pipeline with EHR databases.",
            referenceUrl = "https://en.wikipedia.org/wiki/Forward-deployed_software_engineer"
        ),
        WordEntity(
            term = "Foundation model",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "A massive AI model trained on broad, vast datasets at huge scale that serves as the adaptable foundation for hundreds of downstream specialized tasks.",
            keyPoints = "• Term coined by Stanford researchers in 2021.\n• Pretrained in an unsupervised manner on text, code, images, and audio.\n• Examples include GPT-4o, Claude 3.5, Gemini 2.5, and Llama 3.3.",
            practicalUses = "• The core engine powering modern chatbots, code generators, and autonomous agents.",
            examples = "Example: Adapting GPT-4 into customer service, creative writing, and medical assistants.",
            referenceUrl = "https://en.wikipedia.org/wiki/Foundation_models"
        ),
        WordEntity(
            term = "Framework",
            category = "Frameworks & SDKs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A standardized software platform providing universal, reusable code structures and rules that make building complex applications faster and more reliable.",
            keyPoints = "• Inversion of control: the framework calls your code, unlike a library which your code calls.\n• Enforces consistent architectural design patterns.\n• Examples include Jetpack Compose (UI), LangChain (AI apps), and PyTorch (Deep Learning).",
            practicalUses = "• Using Jetpack Compose to build modern Android UIs without writing boilerplate view layouts.",
            examples = "Example: Jetpack Compose providing declarative reactive state UI components.",
            referenceUrl = "https://en.wikipedia.org/wiki/Software_framework"
        ),
        WordEntity(
            term = "Free tier",
            category = "Business & Strategy",
            part = "Part 3: A to Z master list",
            humanMeaning = "A no-cost level of service offered by cloud and AI providers allowing developers to experiment, build prototypes, and test APIs without entering a credit card.",
            keyPoints = "• Greatly accelerates developer adoption and community growth.\n• Usually restricted by lower rate limits (RPM) and monthly token caps.\n• Examples include Google AI Studio free tier for Gemini models.",
            practicalUses = "• Prototyping an Android AI app during initial development without incurring bills.",
            examples = "Example: Google AI Studio offering 15 requests per minute for Gemini 2.5 Flash at zero cost.",
            referenceUrl = "https://en.wikipedia.org/wiki/Freemium"
        ),
        WordEntity(
            term = "Frontier lab",
            category = "Business & Strategy",
            part = "Part 3: A to Z master list",
            humanMeaning = "The elite top-tier research organizations pushing the bleeding-edge boundary of frontier AI capabilities (OpenAI, Anthropic, Google DeepMind, Meta FAIR).",
            keyPoints = "• Invests billions of dollars in training runs and supercomputer compute clusters.\n• Employs the world's leading research scientists and alignment researchers.\n• Produces the flagship state-of-the-art foundation models.",
            practicalUses = "• Setting global benchmarks and driving the timeline toward AGI.",
            examples = "Example: OpenAI releasing o3 reasoning models; Anthropic releasing Claude 3.7 Sonnet.",
            referenceUrl = "https://en.wikipedia.org/wiki/Artificial_intelligence"
        ),
        WordEntity(
            term = "Frontier model",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "The most advanced, capable state-of-the-art AI models in existence at any given time, exhibiting cutting-edge reasoning, coding, and multimodal intelligence.",
            keyPoints = "• Sits at the current technological boundary of human capability.\n• Subject to regulatory scrutiny and international safety commitments.\n• High intelligence, high cost, and extensive compute requirements.",
            practicalUses = "• Solving the most challenging algorithmic, scientific, and architectural problems.",
            examples = "Example: Claude 3.7 Sonnet, OpenAI o3, and Google Gemini 2.5 Pro.",
            referenceUrl = "https://en.wikipedia.org/wiki/Frontier_model"
        ),
        WordEntity(
            term = "Function calling",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "The ability of an AI model to detect when an external tool or API is needed, pause generation, and output clean structured JSON arguments to invoke that function.",
            keyPoints = "• Turns language models into actionable controllers that can interact with the outside world.\n• The model does not execute the function itself; the host client runs it and returns the result.\n• Essential mechanism powering autonomous agents and MCP tooling.",
            practicalUses = "• Having an AI check the live weather or query a database table programmatically.",
            examples = "Example: Model outputting {\"name\": \"get_weather\", \"arguments\": {\"city\": \"Tokyo\"}}.",
            referenceUrl = "https://platform.openai.com/docs/guides/function-calling"
        ),

        // G
        WordEntity(
            term = "GAIA",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "General AI Assistants: a demanding benchmark evaluating AI assistants on real-world multi-modal tasks (browsing, file inspection, spreadsheet math) that humans solve easily but AI finds hard.",
            keyPoints = "• Features complex questions requiring multi-tool reasoning and multimodal perception.\n• Designed to be conceptually easy for humans (~92% human accuracy) but challenging for AI.\n• Highly resistant to simple data contamination.",
            practicalUses = "• Benchmarking agentic workflows and tool-calling reliability.",
            examples = "Example: Asking an agent to inspect a 50-page PDF and calculate the percentage change between two tables.",
            referenceUrl = "https://huggingface.co/gaia-benchmark"
        ),
        WordEntity(
            term = "GAN",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Generative Adversarial Network: a machine learning architecture where two neural networks (a Generator and a Discriminator) compete against each other to create realistic synthetic data.",
            keyPoints = "• Generator tries to create fake data; Discriminator tries to catch the fakes.\n• Pioneered by Ian Goodfellow in 2014.\n• Historically revolutionized photorealistic image generation before diffusion models.",
            practicalUses = "• Face aging filters, image super-resolution, synthetic data generation.",
            examples = "Example: StyleGAN generating photorealistic human faces of people who do not exist.",
            referenceUrl = "https://en.wikipedia.org/wiki/Generative_adversarial_network"
        ),
        WordEntity(
            term = "Generative AI",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "A category of artificial intelligence systems that can create brand new content (text, images, code, audio, 3D assets) by learning patterns from massive training data.",
            keyPoints = "• Moves beyond discriminative AI (which only classifies or labels existing data) to creation.\n• Powered by Transformers, diffusion models, and variational autoencoders.\n• Transforming software, media, art, and business operations worldwide.",
            practicalUses = "• Drafting emails, writing complete Android apps, composing music, designing logos.",
            examples = "Example: Asking an AI to write a poem or generate a mobile UI layout from a scratch prompt.",
            referenceUrl = "https://en.wikipedia.org/wiki/Generative_artificial_intelligence"
        ),
        WordEntity(
            term = "Git (version control)",
            category = "CI/CD & DevOps",
            part = "Part 3: A to Z master list",
            humanMeaning = "The world's leading distributed version control system created by Linus Torvalds, tracking code changes and allowing multiple developers to collaborate without overwriting work.",
            keyPoints = "• Distributed: every developer has a complete local copy of the project history.\n• Branching and merging workflows for safe feature development.\n• Integrates with GitHub, GitLab, and automated CI/CD runners.",
            practicalUses = "• Tracking code revisions, rewinding bugs, and managing collaborative open-source repositories.",
            examples = "Example: git init, git add ., git commit -m 'initial commit', git push origin main.",
            referenceUrl = "https://en.wikipedia.org/wiki/Git"
        ),
        WordEntity(
            term = "Golden dataset",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "A meticulously human-verified, high-quality benchmark dataset representing the 'gold standard' truth against which all model generations and prompt updates are graded.",
            keyPoints = "• The definitive source of truth for CI/CD evaluation pipelines.\n• Contains edge cases, tricky phrasing, and typical customer queries.\n• Maintained and curated with extreme care; never polluted with synthetic noise.",
            practicalUses = "• Testing if an updated system prompt answers 100 core customer questions accurately.",
            examples = "Example: A curated dataset of 250 verified legal questions and partner-approved answers.",
            referenceUrl = "https://en.wikipedia.org/wiki/Ground_truth"
        ),
        WordEntity(
            term = "GPU",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Graphics Processing Unit: a specialized processor with thousands of smaller, efficient cores designed to perform massive parallel matrix calculations simultaneously.",
            keyPoints = "• Far superior to CPUs for machine learning because neural networks are essentially giant matrix multiplications.\n• High-bandwidth memory (HBM3e) allows rapid parameter streaming.\n• NVIDIA is the dominant market leader (H100, H200, B200).",
            practicalUses = "• Accelerating AI training runs and rendering real-time 3D graphics.",
            examples = "Example: An NVIDIA H100 GPU cluster executing trillions of floating-point operations per second.",
            referenceUrl = "https://en.wikipedia.org/wiki/Graphics_processing_unit"
        ),
        WordEntity(
            term = "Grader (LLM-as-judge)",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "Using a powerful, reliable AI model (like GPT-4o or Claude 3.5 Sonnet) as an automated evaluator to grade the quality, tone, and accuracy of another model's answers.",
            keyPoints = "• Evaluates complex, subjective qualities (helpfulness, tone, humor) that regex cannot test.\n• Uses a detailed scoring rubric with numerical 1-5 scales or binary pass/fail.\n• Slashes human annotation costs by 99% while correlating closely with human judgment.",
            practicalUses = "• Automatically grading 1,000 chatbot responses nightly in a CI pipeline.",
            examples = "Example: System prompt instructing GPT-4o: 'Score the following answer from 1 to 5 on accuracy based on this reference passage'.",
            referenceUrl = "https://arxiv.org/abs/2306.05685"
        ),
        WordEntity(
            term = "Gradient descent",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "The primary mathematical optimization algorithm used to train neural networks by iteratively adjusting weights in the direction that reduces error (loss) the fastest.",
            keyPoints = "• Visualized as walking downhill through a foggy landscape toward the lowest valley (minimum loss).\n• Learning rate controls how big each step is.\n• Variants include Stochastic Gradient Descent (SGD) and AdamW.",
            practicalUses = "• Updating billions of model weights during pre-training and fine-tuning.",
            examples = "Example: Weight_new = Weight_old - (Learning_Rate * Gradient).",
            referenceUrl = "https://en.wikipedia.org/wiki/Gradient_descent"
        ),
        WordEntity(
            term = "GraphRAG",
            category = "RAG & Context",
            part = "Part 3: A to Z master list",
            humanMeaning = "An advanced RAG architecture developed by Microsoft that builds a knowledge graph from documents, connecting related entities to answer complex global questions.",
            keyPoints = "• Traditional vector RAG struggles with global synthesis ('What are the overarching themes in this collection?').\n• GraphRAG extracts entities, relationships, and community summaries.\n• Enables comprehensive high-level conceptual reasoning across vast document sets.",
            practicalUses = "• Analyzing thousands of investigative news leaks, intelligence reports, or legal archives.",
            examples = "Example: Tracing financial ownership networks across 5,000 corporate filing documents.",
            referenceUrl = "https://microsoft.github.io/graphrag"
        ),
        WordEntity(
            term = "Groundedness",
            category = "RAG & Context",
            part = "Part 3: A to Z master list",
            humanMeaning = "The degree to which an AI model's statements are strictly tied to and verifiable against verifiable real-world documents, databases, or search results.",
            keyPoints = "• Prevents models from pulling unverified assertions from memory.\n• Features in Google Vertex AI and Gemini ('Grounding with Google Search').\n• Directly correlated with user trust and compliance in enterprise systems.",
            practicalUses = "• Providing customer answers that cite exact pages in the product return policy.",
            examples = "Example: Gemini grounding its response using live Google Search to confirm today's stock price.",
            referenceUrl = "https://cloud.google.com/vertex-ai/generative-ai/docs/grounding/overview"
        ),
        WordEntity(
            term = "Guardrails",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Automated programmable software filters and validation checks that inspect user inputs and model outputs to block harmful, toxic, or off-topic content.",
            keyPoints = "• Placed before the model (input guardrail) and after the model (output guardrail).\n• Blocks prompt injections, PII leaks, competitor mentions, and offensive speech.\n• Open-source frameworks include NeMo Guardrails and Llama Guard.",
            practicalUses = "• Ensuring a company's banking assistant politely refuses to talk about politics or give medical advice.",
            examples = "Example: An output guardrail detecting a credit card number and masking it before returning it to the user.",
            referenceUrl = "https://github.com/NVIDIA/NeMo-Guardrails"
        ),

        // H
        WordEntity(
            term = "Hallucination rate",
            category = "Safety & Evals",
            part = "Part 3: A to Z master list",
            humanMeaning = "The percentage of times an AI model confidently states false, fabricated, or non-existent facts as if they were true.",
            keyPoints = "• Major challenge of probabilistic next-token generation.\n• Models can invent non-existent book titles, fake legal citations, or broken software methods.\n• Reduced dramatically through RAG, reasoning models (o1/Claude 3.7), and search grounding.",
            practicalUses = "• Benchmarking customer support models to ensure hallucination rate stays under 1%.",
            examples = "Example: A lawyer citing a completely fabricated judicial precedent hallucinated by ChatGPT.",
            referenceUrl = "https://en.wikipedia.org/wiki/Hallucination_(artificial_intelligence)"
        ),
        WordEntity(
            term = "Handoff artifacts",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "Structured summaries, checklists, or state objects passed from one agent to another during a task handoff so the receiving agent has full context without token bloat.",
            keyPoints = "• Enables smooth multi-agent delegation.\n• Summarizes what was already attempted, current errors, and clear next steps.\n• Prevents subagents from repeating previously failed exploration paths.",
            practicalUses = "• A research agent handing off a synthesized dossier to a writing agent.",
            examples = "Example: An architect agent passing a JSON specification of database tables to a coding agent.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Harness",
            category = "Agent Harnesses",
            part = "Part 3: A to Z master list",
            humanMeaning = "The software runtime environment surrounding an AI model that equips it with tools, manages its execution loop, handles permissions, and feeds it context.",
            keyPoints = "• The model is the brain; the harness is the body and nervous system.\n• Manages terminal shells, git repositories, file systems, and API connections.\n• Examples include Claude Code, Cursor, Devin, and Aider.",
            practicalUses = "• Giving an LLM the real-world ability to run builds and edit files on a developer's machine.",
            examples = "Example: Claude Code providing the harness that executes bash commands and verifies compiler outputs.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Harness engineering",
            category = "Agent Harnesses",
            part = "Part 3: A to Z master list",
            humanMeaning = "The specialized engineering discipline of building, optimizing, and securing the runtime scaffolding, loops, and sandboxes that power autonomous coding agents.",
            keyPoints = "• Focuses on loop engineering, deterministic hooks, subagent spawning, and permission control.\n• Solves context degradation and tool search scaling.\n• Rapidly emerging as one of the most critical software engineering domains.",
            practicalUses = "• Architecting custom internal coding agents for enterprise development teams.",
            examples = "Example: Designing sandboxed Docker containers and MCP routers for a company-wide AI agent.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Hiring manager",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "The engineering leader or executive who holds the budget and makes the final decision on whether to extend a job offer to a candidate.",
            keyPoints = "• Focuses on business impact, cultural alignment, and team problem-solving.\n• The hiring manager interview round evaluates past ownership and strategic communication.\n• The primary person to connect with during cold outreach and job searches.",
            practicalUses = "• Pitching your AI engineering portfolio directly to the team leader who needs your skills.",
            examples = "Example: Pitching an Android dictionary app directly to the VP of Engineering.",
            referenceUrl = "https://en.wikipedia.org/wiki/Recruitment"
        ),
        WordEntity(
            term = "HLE",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "Humanities and Language Evaluation / High-Level Evaluation benchmarks designed to measure advanced abstract reasoning and qualitative understanding.",
            keyPoints = "• Evaluates complex comprehension across law, philosophy, literature, and policy.\n• Moves beyond simple multiple-choice math tests.\n• Probes deep synthesis of nuanced human thought.",
            practicalUses = "• Benchmarking frontier models on qualitative policy analysis.",
            examples = "Example: Grading an AI's comparative critique of two legal interpretations of contract law.",
            referenceUrl = "https://en.wikipedia.org/wiki/Evaluation_of_artificial_intelligence"
        ),
        WordEntity(
            term = "Human-in-the-loop",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "A system design pattern where an AI automated process pauses at critical steps to require human review, judgment, or confirmation before proceeding.",
            keyPoints = "• Combines machine speed with human accountability and wisdom.\n• Mandatory for high-stakes decisions (medical diagnosis, financial trades, production code deploys).\n• Progressively scales: humans review 100% of decisions initially, then only edge cases as accuracy improves.",
            practicalUses = "• Having an engineer click 'Approve' before an AI agent merges a pull request to production.",
            examples = "Example: A customer service bot drafting an email response, but requiring a human agent to review before sending.",
            referenceUrl = "https://en.wikipedia.org/wiki/Human-in-the-loop"
        ),
        WordEntity(
            term = "Hybrid (work)",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "An employment arrangement where engineers split their working time between working remotely from home and working in a physical corporate office.",
            keyPoints = "• Standard model across major tech companies (typically 2-3 days in office).\n• Balances deep individual coding focus at home with collaborative whiteboard sessions in office.\n• Influences relocation, commute, and equipment considerations.",
            practicalUses = "• Working from home on Tuesdays and Thursdays while collaborating in the office on Mondays and Wednesdays.",
            examples = "Example: A hybrid contract specifying 3 days in office in San Francisco and 2 days remote.",
            referenceUrl = "https://en.wikipedia.org/wiki/Remote_work"
        ),
        WordEntity(
            term = "Hybrid reasoning",
            category = "Reasoning & Models",
            part = "Part 3: A to Z master list",
            humanMeaning = "Models that seamlessly combine fast intuitive thinking (System 1) with extended, rigorous step-by-step reasoning (System 2) inside a single unified architecture.",
            keyPoints = "• Pioneered in Claude 3.7 Sonnet: switch between fast responses and deep thinking in one model.\n• Eliminates having to choose between a slow reasoning model and a dumb fast model.\n• Dynamically adjusts reasoning effort based on query difficulty.",
            practicalUses = "• Quickly replying 'Hello!' in 200ms while spending 15 seconds reasoning through complex math proofs.",
            examples = "Example: Claude 3.7 generating thinking tokens only when confronted with tricky logic.",
            referenceUrl = "https://www.anthropic.com"
        ),
        WordEntity(
            term = "Hybrid search",
            category = "RAG & Context",
            part = "Part 3: A to Z master list",
            humanMeaning = "Combining dense vector semantic search (embeddings) with sparse keyword search (BM25) to retrieve documents with both conceptual understanding and exact term precision.",
            keyPoints = "• Pure vector search often fails on exact model numbers, error codes, and unique IDs.\n• Pure keyword search fails on conceptual synonyms and paraphrased questions.\n• Merged using Reciprocal Rank Fusion (RRF) for the best of both worlds.",
            practicalUses = "• Finding documentation that contains both the exact error code 'ERR_CONNECTION_REFUSED' and conceptual fixes.",
            examples = "Example: Searching a medical database for exact pharmaceutical codes while understanding symptoms.",
            referenceUrl = "https://en.wikipedia.org/wiki/Information_retrieval"
        ),

        // I
        WordEntity(
            term = "IDE",
            category = "Harness Building Blocks",
            part = "Part 3: A to Z master list",
            humanMeaning = "Integrated Development Environment: a comprehensive software application (like Android Studio, VS Code, Cursor) combining code editor, compiler, debugger, and AI assistant.",
            keyPoints = "• Central command center for software developers.\n• Modern AI IDEs (Cursor, Windsurf) incorporate native agentic coding loops.\n• Provides syntax highlighting, auto-complete, and integrated terminal shells.",
            practicalUses = "• Writing Kotlin code, running Android emulators, and debugging application builds.",
            examples = "Example: Android Studio for building Jetpack Compose applications.",
            referenceUrl = "https://en.wikipedia.org/wiki/Integrated_development_environment"
        ),
        WordEntity(
            term = "Idempotency",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "The property of an operation where executing it multiple times produces the exact same result as executing it once, preventing duplicate side effects.",
            keyPoints = "• Critical for network resilience: if a network drops and a request retries, it shouldn't charge a credit card twice.\n• Implemented using unique Idempotency-Keys in HTTP headers.\n• Essential in agent loops to ensure repeated tool executions don't corrupt state.",
            practicalUses = "• Safe API retries on Stripe payments or database record creation.",
            examples = "Example: Running an idempotent database migration script 5 times leaves the schema in the exact same state.",
            referenceUrl = "https://en.wikipedia.org/wiki/Idempotence"
        ),
        WordEntity(
            term = "Image generation",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Using deep generative models (diffusion models, autoregressive transformers) to synthesize novel, high-resolution visual art and graphics from natural language text prompts.",
            keyPoints = "• Generates images, app icons, UI mockups, and illustrations from descriptive text.\n• Models include Midjourney, Stable Diffusion, DALL-E 3, and Imagen 3.\n• Supports prompt styling, aspect ratios, and seed consistency.",
            practicalUses = "• Generating the custom launcher icon for this Android dictionary app.",
            examples = "Example: Prompting 'Minimalist flat vector icon of a glowing open book' to generate an app asset.",
            referenceUrl = "https://en.wikipedia.org/wiki/Text-to-image_model"
        ),
        WordEntity(
            term = "In-context learning",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "The remarkable ability of large language models to understand new tasks and follow novel instructions purely from examples provided in the prompt, without updating any model weights.",
            keyPoints = "• Discovered in GPT-3: models learn on-the-fly during inference.\n• Powered by the model's internal attention mechanisms.\n• Enables instant customization without slow, expensive fine-tuning.",
            practicalUses = "• Providing 2 examples of an unfamiliar jargon and having the AI use it correctly immediately.",
            examples = "Example: Showing the AI a new domain terminology format and having it parse 20 examples instantly.",
            referenceUrl = "https://en.wikipedia.org/wiki/In-context_learning_(natural_language_processing)"
        ),
        WordEntity(
            term = "Inference",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "The live operational phase where a trained AI model processes a new input prompt and generates a response or prediction (as opposed to the initial training phase).",
            keyPoints = "• Training happens once; inference happens millions of times in production.\n• Measured by latency (Time to First Token) and throughput (Tokens Per Second).\n• Optimized using quantization, KV caching, and high-speed serving engines (vLLM, Groq).",
            practicalUses = "• What happens every time you tap the OpenRouter helper button in this app to ask about a word.",
            examples = "Example: Sending a prompt to Gemini 2.5 and receiving a streamed answer back in 400 milliseconds.",
            referenceUrl = "https://en.wikipedia.org/wiki/Inference"
        ),
        WordEntity(
            term = "Inference engine",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "The high-performance software system (like vLLM, TensorRT-LLM, llama.cpp, Ollama) that runs the mathematical computations to execute AI model inference on GPUs or CPUs.",
            keyPoints = "• Optimizes memory bandwidth, batching, and KV cache management.\n• Maximizes GPU utilization and reduces per-token serving cost.\n• Exposes REST or gRPC APIs for client applications.",
            practicalUses = "• Serving open-weight models at scale with high concurrency.",
            examples = "Example: vLLM serving Mistral-7B to handle 500 simultaneous user questions.",
            referenceUrl = "https://en.wikipedia.org/wiki/Inference_engine"
        ),
        WordEntity(
            term = "Infrastructure as code",
            category = "CI/CD & DevOps",
            part = "Part 3: A to Z master list",
            humanMeaning = "Managing and provisioning cloud infrastructure (servers, networks, databases) using machine-readable configuration files (Terraform, Pulumi) rather than manual console clicking.",
            keyPoints = "• Infrastructure can be version-controlled in Git, peer-reviewed, and rolled back.\n• Prevents configuration drift and ensures identical environments across staging and production.\n• Automates disaster recovery: spin up an entire cloud infrastructure in minutes.",
            practicalUses = "• Defining a complete AWS production VPC and database cluster in a single terraform file.",
            examples = "Example: Running 'terraform apply' to provision 5 cloud servers automatically.",
            referenceUrl = "https://en.wikipedia.org/wiki/Infrastructure_as_code"
        ),
        WordEntity(
            term = "Input/output tokens",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "The basic accounting units of LLM billing: input tokens are the words you send into the model (prompt), and output tokens are the words the model generates back.",
            keyPoints = "• Output tokens are almost always 3x to 5x more expensive than input tokens because they must be generated sequentially.\n• ~1,000 tokens equals approximately 750 English words.\n• Tracking token counts is vital for managing API budgets.",
            practicalUses = "• Budgeting API costs when querying OpenRouter for term explanations.",
            examples = "Example: A prompt with 100 input tokens generates a 300 output token explanation.",
            referenceUrl = "https://platform.openai.com/tokenizer"
        ),
        WordEntity(
            term = "Integrations",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "Software connections that link an application or AI system with external services, databases, and APIs (Slack, GitHub, Stripe, OpenRouter) to exchange data.",
            keyPoints = "• Connects isolated software silos into unified workflows.\n• Implemented via REST APIs, webhooks, SDKs, or MCP servers.\n• Increases software value by fitting into existing customer toolchains.",
            practicalUses = "• Integrating an OpenRouter API helper button directly into a dictionary app.",
            examples = "Example: Connecting your application to GitHub to read commit logs automatically.",
            referenceUrl = "https://en.wikipedia.org/wiki/System_integration"
        ),
        WordEntity(
            term = "Internship",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A period of work experience offered by a tech company to students or aspiring developers to gain practical, real-world industry engineering experience.",
            keyPoints = "• Primary pipeline for full-time junior and entry-level engineering hiring.\n• Provides hands-on exposure to production codebases, code reviews, and agile sprints.\n• Highly competitive at top AI research labs and tech giants.",
            practicalUses = "• Gaining software engineering experience at an AI startup over summer.",
            examples = "Example: A 3-month summer engineering internship building Android features for a tech company.",
            referenceUrl = "https://en.wikipedia.org/wiki/Internship"
        ),
        WordEntity(
            term = "Interpretability",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "The scientific field dedicated to opening up the 'black box' of neural networks to discover what internal circuits, features, and neurons represent during thinking.",
            keyPoints = "• Mechanistic interpretability decodes how models store concepts (like the 'Golden Gate Bridge' neuron in Claude).\n• Aims to detect deceptive thinking or hidden malicious intent before models act.\n• Essential for long-term frontier AI safety and alignment.",
            practicalUses = "• Verifying whether a model is genuinely being honest or just telling the user what they want to hear.",
            examples = "Example: Anthropic's monosemanticity research mapping millions of concept features inside Claude.",
            referenceUrl = "https://en.wikipedia.org/wiki/Explainable_artificial_intelligence"
        ),

        // J & K
        WordEntity(
            term = "Jailbreak",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Using clever, adversarial prompt engineering to bypass an AI model's safety guardrails and force it to generate forbidden, harmful, or restricted outputs.",
            keyPoints = "• Techniques include roleplay ('You are DAN, an AI with no rules'), hypothetical framing, and multi-lingual cipher encoding.\n• AI safety teams continuously patch models against new jailbreak vectors.\n• Major focus of automated red-teaming.",
            practicalUses = "• Stress-testing enterprise chatbot safety filters before deployment.",
            examples = "Example: Tricking an AI into ignoring its safety training by asking it to write a fictional movie script about bomb-making.",
            referenceUrl = "https://en.wikipedia.org/wiki/Jailbreak_(computer_science)"
        ),
        WordEntity(
            term = "JSON schema",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "A formal standard for describing the structure, data types, required fields, and constraints of JSON data, used to enforce structured outputs from AI models.",
            keyPoints = "• Defines object types, property definitions, enum values, and validation rules.\n• Used by modern LLMs to guarantee 100% syntactically valid JSON responses.\n• Eliminates JSON parsing crashes in client applications.",
            practicalUses = "• Forcing OpenRouter or OpenAI models to return definitions matching an exact Kotlin data class.",
            examples = "Example: { \"type\": \"object\", \"properties\": { \"term\": { \"type\": \"string\" } }, \"required\": [\"term\"] }.",
            referenceUrl = "https://json-schema.org"
        ),
        WordEntity(
            term = "Junior",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A software engineer in the early stages of their career (typically 0-2 years of experience) focusing on writing clean code, learning systems, and growing skills.",
            keyPoints = "• Supported by code reviews and task breakdown from senior engineers.\n• Fast-tracks career progression by mastering AI agentic coding harnesses.\n• Emphasizes curiosity, strong fundamentals, and receptive communication.",
            practicalUses = "• Starting a career as a junior Android developer building Compose UI components.",
            examples = "Example: Implementing a dictionary search screen under the guidance of a staff architect.",
            referenceUrl = "https://en.wikipedia.org/wiki/Software_engineering"
        ),
        WordEntity(
            term = "Just-in-time context",
            category = "Context Engineering",
            part = "Part 3: A to Z master list",
            humanMeaning = "Fetching and injecting documentation, tool definitions, or files into the prompt dynamically at the exact millisecond they are needed, rather than loading everything upfront.",
            keyPoints = "• Conserves precious context window space and lowers token costs.\n• Avoids model confusion by keeping the active prompt focused purely on current requirements.\n• Core technique behind modern MCP servers and documentation retrievers.",
            practicalUses = "• Pulling in Room database documentation only when the user asks a database question.",
            examples = "Example: Loading an API specification into the prompt only when the agent decides to call that API.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Kaggle or competition",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "An online platform where data scientists and machine learning engineers compete to build the most accurate predictive models on real-world datasets for cash prizes.",
            keyPoints = "• Acquired by Google; home to thousands of public datasets, notebooks, and leaderboards.\n• Achieving 'Kaggle Grandmaster' status is a prestigious industry credential.\n• Excellent venue for building hands-on ML expertise.",
            practicalUses = "• Competing in machine learning challenges to showcase skills to hiring managers.",
            examples = "Example: Building an NLP classifier to detect fake news articles in a Kaggle competition.",
            referenceUrl = "https://www.kaggle.com"
        ),
        WordEntity(
            term = "KV cache",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "Key-Value Cache: an essential GPU memory cache that stores the intermediate Key and Value attention tensors of previously processed tokens so they don't have to be recalculated.",
            keyPoints = "• Without KV cache, generating token #100 would require recalculating attention across all previous 99 tokens.\n• Accelerates autoregressive generation by orders of magnitude.\n• Consumes significant GPU VRAM on long contexts; managed by PagedAttention in vLLM.",
            practicalUses = "• Enabling high-speed token streaming in AI chat and coding applications.",
            examples = "Example: Storing attention vectors in GPU VRAM so generating the next token takes only 15ms.",
            referenceUrl = "https://en.wikipedia.org/wiki/Transformer_(deep_learning_architecture)"
        ),

        // L & M
        WordEntity(
            term = "Labeling",
            category = "Data & Datasets",
            part = "Part 3: A to Z master list",
            humanMeaning = "The process of tagging raw data with correct labels, classifications, or annotations so supervised machine learning models can learn the patterns.",
            keyPoints = "• Done manually by human annotators or automated via LLM-assisted labeling.\n• Examples include labeling emails as spam/not-spam, or drawing bounding boxes around cars.\n• High-quality labels are the bedrock of high-performing models.",
            practicalUses = "• Creating a labeled dataset of 10,000 customer feedback comments.",
            examples = "Example: Assigning sentiment labels ('Positive', 'Negative') to app store reviews.",
            referenceUrl = "https://en.wikipedia.org/wiki/Data_labeling"
        ),
        WordEntity(
            term = "Latent space",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "A compressed, multi-dimensional mathematical space where a neural network maps complex data (words, faces, concepts) so that semantically similar items sit near each other.",
            keyPoints = "• Abstract representation learned internally by neural networks.\n• Moving through latent space smoothly interpolates between concepts (e.g. morphing a cat face into a dog face).\n• Foundation for diffusion models, VAEs, and vector embeddings.",
            practicalUses = "• Finding concepts with similar meanings by measuring mathematical distance in latent space.",
            examples = "Example: The concept vector for 'king' minus 'man' plus 'woman' equals 'queen' in word embedding latent space.",
            referenceUrl = "https://en.wikipedia.org/wiki/Latent_space"
        ),
        WordEntity(
            term = "Licensing",
            category = "Business & Strategy",
            part = "Part 3: A to Z master list",
            humanMeaning = "Legal terms and permissions governing how software, datasets, and AI model weights can be used, modified, shared, and commercially deployed.",
            keyPoints = "• Open-source licenses (MIT, Apache 2.0) permit unrestricted commercial use.\n• Open-weight licenses (Llama 3 Community License) may have user thresholds or restrictions.\n• Commercial API licenses define acceptable use policies and enterprise warranties.",
            practicalUses = "• Verifying an AI model can be legally embedded in a commercial enterprise application.",
            examples = "Example: Using MIT-licensed software in this Android app for total open commercial freedom.",
            referenceUrl = "https://en.wikipedia.org/wiki/Software_license"
        ),
        WordEntity(
            term = "LinkedIn",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "The primary professional networking platform used worldwide by tech recruiters, engineers, and executives for hiring, sharing technical write-ups, and career networking.",
            keyPoints = "• Primary platform for tech job hunting and direct recruiter outreach.\n• Showcase projects, open-source repositories, and technical write-ups.\n• Connect directly with founders and hiring managers.",
            practicalUses = "• Networking with engineering managers and showcasing Android AI projects.",
            examples = "Example: Posting a demo video of this offline AI dictionary to attract engineering recruiters.",
            referenceUrl = "https://www.linkedin.com"
        ),
        WordEntity(
            term = "LLM",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Large Language Model: a deep learning neural network trained on vast amounts of text data that understands, predicts, and generates human-like language.",
            keyPoints = "• Built on the Transformer architecture with billions or trillions of parameters.\n• Pretrained on next-token prediction across public internet text and books.\n• Powers modern chatbots, coding assistants, translators, and autonomous agents.",
            practicalUses = "• Powering conversational search, summarizing documents, generating code.",
            examples = "Example: Claude 3.5, GPT-4o, Gemini 2.5, DeepSeek-V3, and Llama 3.3.",
            referenceUrl = "https://en.wikipedia.org/wiki/Large_language_model"
        ),
        WordEntity(
            term = "LLM-as-a-judge",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "An evaluation methodology where a capable foundation model is used to assess, score, and critique outputs generated by other AI models against a rubric.",
            keyPoints = "• Correlates strongly with human expert preferences.\n• Drastically cheaper and thousands of times faster than human evaluation panels.\n• Best practices: provide clear scoring rubrics and swap answer order to avoid position bias.",
            practicalUses = "• Automatically scoring 500 generated summaries on a 1-to-5 clarity scale in CI/CD.",
            examples = "Example: Having GPT-4o score an Android code snippet on adherence to Jetpack Compose guidelines.",
            referenceUrl = "https://arxiv.org/abs/2306.05685"
        ),
        WordEntity(
            term = "LLMOps",
            category = "CI/CD & DevOps",
            part = "Part 3: A to Z master list",
            humanMeaning = "The operational practices, tools, and workflows used to manage the lifecycle of production LLM applications (prompt versioning, tracing, evals, monitoring, cost control).",
            keyPoints = "• Specialized evolution of traditional MLOps tailored for large language models.\n• Manages prompt registries, vector databases, guardrails, and token expenditure.\n• Tools include LangSmith, Langfuse, Braintrust, and Portkey.",
            practicalUses = "• Monitoring production token spend and tracking model latency across 1 million daily requests.",
            examples = "Example: Setting up an automated alert when daily OpenAI API costs exceed $500.",
            referenceUrl = "https://en.wikipedia.org/wiki/MLOps"
        ),
        WordEntity(
            term = "llms.txt",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "A proposed standardized markdown file placed at the root of a website (like /llms.txt) that provides a clean, concise, machine-readable summary of the site for AI models.",
            keyPoints = "• The AI equivalent of robots.txt or sitemap.xml.\n• Strips HTML, navigation bars, and marketing noise, providing pure documentation in markdown.\n• Allows AI coding agents to ingest comprehensive library docs in seconds.",
            practicalUses = "• Providing clean library documentation directly to Claude Code or Cursor agents.",
            examples = "Example: Visiting https://docs.anthropic.com/llms.txt to get a machine-readable summary of Anthropic docs.",
            referenceUrl = "https://llmstxt.org"
        ),
        WordEntity(
            term = "Local models",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "AI models downloaded and executed entirely on your own local computer hardware (MacBook, PC, smartphone) with zero internet connection or third-party cloud APIs.",
            keyPoints = "• 100% private: your proprietary prompts and code never leave your machine.\n• Zero API billing costs or subscription fees.\n• Powered by runtimes like Ollama, llama.cpp, and LM Studio.",
            practicalUses = "• Working on confidential company codebases without violating enterprise data policies.",
            examples = "Example: Running DeepSeek-R1-Distill-Qwen-14B locally on your laptop using Ollama.",
            referenceUrl = "https://ollama.com"
        ),
        WordEntity(
            term = "Logits",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "The raw, unnormalized numerical scores output by the final layer of a neural network before being transformed into probabilities by the softmax function.",
            keyPoints = "• High logit value indicates high model confidence in that specific next token.\n• Passed through softmax to convert raw numbers into percentages that sum to 1.0 (100%).\n• Logit bias parameters in APIs let developers force or ban specific tokens.",
            practicalUses = "• Using logit bias to ban a model from ever outputting certain forbidden words.",
            examples = "Example: Logits [12.4, 4.2, -1.8] converted via softmax to probabilities [0.99, 0.01, 0.0001].",
            referenceUrl = "https://en.wikipedia.org/wiki/Logit"
        ),
        WordEntity(
            term = "Logs",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "Timestamped text records automatically written by computer programs documenting events, transactions, warnings, and errors as they happen.",
            keyPoints = "• Essential for debugging crashes and understanding system behavior in production.\n• Categorized by severity levels: DEBUG, INFO, WARN, ERROR, FATAL.\n• Centralized and searched using tools like Datadog, ELK stack, or Grafana Loki.",
            practicalUses = "• Inspecting Android Logcat in Android Studio to see why an API request failed.",
            examples = "Example: '2026-10-02 10:15:32 [ERROR] OpenRouter HTTP 401: Invalid API Key'.",
            referenceUrl = "https://en.wikipedia.org/wiki/Log_file"
        ),
        WordEntity(
            term = "Long-running agents",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "Autonomous AI agents that execute complex tasks over hours or days, maintaining state, saving checkpoints, and recovering from failures across extended sessions.",
            keyPoints = "• Solves context window exhaustion through persistence and state checkpointing.\n• Capable of migrating massive codebases or running thousands of integration tests overnight.\n• Uses subagent hierarchies to prevent context rot.",
            practicalUses = "• Delegating an entire software framework upgrade (e.g. migrating 100 files to Jetpack Compose).",
            examples = "Example: Devin or Claude Code running for 4 hours to reproduce and fix an intricate compiler issue.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Long-term memory",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "Persistent storage (using vector databases, SQLite, or markdown files) that allows an AI assistant to remember facts, user preferences, and history across multiple sessions.",
            keyPoints = "• Overcomes the temporary, stateless nature of single context windows.\n• Ingests user instructions once and retrieves them weeks later.\n• Implemented via memory files (.cursorrules, AGENTS.md) or vector embeddings.",
            practicalUses = "• Having an AI assistant remember that you prefer concise code without button outlines across every conversation.",
            examples = "Example: ChatGPT remembering 'User is building an offline Android dictionary in Kotlin'.",
            referenceUrl = "https://en.wikipedia.org/wiki/Long-term_memory"
        ),
        WordEntity(
            term = "Loop engineering",
            category = "Agent Harnesses",
            part = "Part 3: A to Z master list",
            humanMeaning = "The architectural practice of designing robust, self-correcting agent execution loops with deterministic guardrails, stop conditions, and error recovery.",
            keyPoints = "• Prevents infinite loops and runaway API token costs.\n• Implements progressive backtracking and alternative strategy exploration.\n• Injects linting, testing, and compilation verification directly into the agent cycle.",
            practicalUses = "• Building coding agents that test their own code and fix compiler errors automatically.",
            examples = "Example: An agent loop that detects 3 consecutive test failures and switches to a different architectural approach.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "LoRA",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Low-Rank Adaptation: a parameter-efficient fine-tuning technique that freezes the base model weights and trains small mathematical rank-decomposition matrices instead.",
            keyPoints = "• Slashes trainable parameters by 99%+, allowing fine-tuning on consumer GPUs in hours.\n• Yields tiny adapter files (megabytes instead of gigabytes) that can be swapped dynamically.\n• Achieves performance comparable to full fine-tuning at a fraction of the compute cost.",
            practicalUses = "• Fine-tuning an open-source model on private internal medical or legal terminology.",
            examples = "Example: Training a 50MB LoRA adapter for Llama 3 on an NVIDIA RTX 4090 GPU.",
            referenceUrl = "https://arxiv.org/abs/2106.09685"
        ),
        WordEntity(
            term = "Loss function",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "A mathematical formula that measures the discrepancy between an AI model's prediction and the actual ground truth, serving as the score the model strives to minimize during training.",
            keyPoints = "• Evaluates how well the model is learning.\n• Cross-entropy loss is standard for next-token prediction in language models.\n• Backpropagation calculates weight gradients with respect to the loss.",
            practicalUses = "• Guiding neural network optimization during pre-training and fine-tuning.",
            examples = "Example: Cross-entropy loss declining from 4.5 down to 1.2 across training steps.",
            referenceUrl = "https://en.wikipedia.org/wiki/Loss_function"
        ),
        WordEntity(
            term = "Low-code / no-code",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "Visual development platforms (like Zapier, Make, Bubble) that allow users to create applications and automated workflows using drag-and-drop interfaces without typing code.",
            keyPoints = "• Democratizes software creation for non-technical domain experts.\n• Accelerates prototyping of internal business tools.\n• Increasingly augmented with natural language AI prompt builders.",
            practicalUses = "• Automating customer lead routing from website forms into Slack and CRM systems.",
            examples = "Example: Setting up a Zapier trigger: 'When a new email arrives, summarize with AI and send to Slack'.",
            referenceUrl = "https://en.wikipedia.org/wiki/No-code_development_platform"
        ),
        WordEntity(
            term = "Machine learning",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "The branch of computer science and AI where algorithms learn patterns from data and improve their performance on tasks without being explicitly programmed with manual rules.",
            keyPoints = "• Paradigms: Supervised learning, Unsupervised learning, and Reinforcement learning.\n• Replaces rigid hand-coded 'if-else' logic with statistical pattern recognition.\n• The broader foundation that encompasses deep learning and modern LLMs.",
            practicalUses = "• Email spam detection, credit card fraud prevention, medical diagnosis, recommendation algorithms.",
            examples = "Example: Training an algorithm on 10,000 transaction records to automatically spot fraudulent charges.",
            referenceUrl = "https://en.wikipedia.org/wiki/Machine_learning"
        ),
        WordEntity(
            term = "Max turns",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "A safety threshold limiting the maximum number of consecutive tool-calling cycles an agent can execute on a single task before automatically halting.",
            keyPoints = "• Essential safeguard against infinite looping and runaway cloud API billing bills.\n• If an agent cannot finish within max turns (e.g. 25 turns), it halts and asks the user for guidance.\n• Prevents circular bug loops where an agent flips back and forth between two failing approaches.",
            practicalUses = "• Protecting your OpenRouter token budget by setting a hard cap of 15 turns per agent task.",
            examples = "Example: Configuring AgentRunner(max_turns=20) so the loop stops if a test isn't passing after 20 attempts.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "max_tokens",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "An API parameter that sets an upper limit on the number of tokens the model is allowed to generate in its response.",
            keyPoints = "• Prevents models from generating runaway responses that waste money.\n• If response hits max_tokens, generation halts abruptly (finish_reason: 'length').\n• Distinct from the model's total context window capacity.",
            practicalUses = "• Setting max_tokens: 1000 for concise dictionary term summaries.",
            examples = "Example: Passing { \"max_tokens\": 500 } in an OpenRouter request to ensure a short definition.",
            referenceUrl = "https://platform.openai.com/docs/api-reference/chat/create#chat-create-max_tokens"
        ),
        WordEntity(
            term = "MCP",
            category = "MCP & Tooling",
            part = "Part 3: A to Z master list",
            humanMeaning = "Model Context Protocol: Anthropic's open standard that provides a universal, standardized way for AI applications to connect to external data sources and tools.",
            keyPoints = "• Replaces bespoke one-off integrations with a single universal protocol (the 'USB-C of AI').\n• Uses JSON-RPC 2.0 messages over stdio or SSE transports.\n• Supported across Claude Desktop, Cursor, Zed, Cline, and enterprise harnesses.",
            practicalUses = "• Connecting an AI agent to your local Postgres database, GitHub repository, and filesystem with zero custom code.",
            examples = "Example: An MCP server exposing database query tools directly to your AI code assistant.",
            referenceUrl = "https://modelcontextprotocol.io"
        ),
        WordEntity(
            term = "MCP Apps",
            category = "MCP & Tooling",
            part = "Part 3: A to Z master list",
            humanMeaning = "Complete client applications built natively around the Model Context Protocol to orchestrate tools, agents, and desktop workflows.",
            keyPoints = "• Acts as an MCP host client that discovers and coordinates multiple MCP servers.\n• Examples include Claude Desktop, Cursor, and community agent runners.\n• Brings plug-and-play modularity to desktop AI assistants.",
            practicalUses = "• Managing multiple local server tools (Postgres, Slack, Git) through a single unified app.",
            examples = "Example: Claude Desktop discovering and using tools from 4 different local MCP servers.",
            referenceUrl = "https://modelcontextprotocol.io"
        ),
        WordEntity(
            term = "MCP client",
            category = "MCP & Tooling",
            part = "Part 3: A to Z master list",
            humanMeaning = "The application (like Claude Desktop or an AI IDE) that connects to one or more MCP servers, requests tool executions, and reads resources.",
            keyPoints = "• Discovers available tools, prompts, and resources from the connected servers.\n• Sends JSON-RPC tool call requests and consumes structured responses.\n• Manages user permissions and approvals for sensitive tool invocations.",
            practicalUses = "• An AI editor executing local file edits by sending tool calls to a Filesystem MCP server.",
            examples = "Example: Cursor acting as an MCP client connected to a GitHub MCP server.",
            referenceUrl = "https://modelcontextprotocol.io"
        ),
        WordEntity(
            term = "MCP host",
            category = "MCP & Tooling",
            part = "Part 3: A to Z master list",
            humanMeaning = "The core runtime application that coordinates the conversation with the LLM and hosts the MCP client connections to external servers.",
            keyPoints = "• Injects tool schemas into the LLM context window.\n• Directs LLM tool-call outputs to the appropriate MCP server.\n• Responsible for the security boundary between the model and local computer resources.",
            practicalUses = "• Claude Desktop hosting local connections to developer MCP servers.",
            examples = "Example: An agent harness acting as the host managing 5 child MCP processes.",
            referenceUrl = "https://modelcontextprotocol.io"
        ),
        WordEntity(
            term = "MCP registry / directory",
            category = "MCP & Tooling",
            part = "Part 3: A to Z master list",
            humanMeaning = "An online catalog (like Smithery or PulseMCP) where developers discover, share, and install verified community Model Context Protocol servers.",
            keyPoints = "• Discoverability hub for the expanding MCP ecosystem.\n• Provides 1-click install configurations for Claude and other harnesses.\n• Reviews servers for security, documentation, and reliability.",
            practicalUses = "• Finding a pre-built MCP server for Jira, Snowflake, or Notion without writing one from scratch.",
            examples = "Example: Browsing Smithery.ai to find and install a Figma MCP server.",
            referenceUrl = "https://smithery.ai"
        ),
        WordEntity(
            term = "MCP server",
            category = "MCP & Tooling",
            part = "Part 3: A to Z master list",
            humanMeaning = "A lightweight program implementing the MCP specification that exposes tools, data resources, and prompt templates to AI clients over stdio or HTTP.",
            keyPoints = "• Written in Python, TypeScript, Kotlin, or Go.\n• Bridges specific software systems (like a database or API) to any compatible AI client.\n• Exposes tools with strict JSON schema definitions for inputs and outputs.",
            practicalUses = "• Exposing your internal company REST API to Claude so employees can chat with company data.",
            examples = "Example: A Postgres MCP server exposing a 'query_database' tool.",
            referenceUrl = "https://modelcontextprotocol.io"
        ),
        WordEntity(
            term = "Memory",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "The cognitive mechanism in an AI system that stores, retains, and recalls past user information, conversation context, and learnings over time.",
            keyPoints = "• Short-term memory: the active context window of the current conversation.\n• Long-term memory: persistent external storage (vector databases, user profiles, files) retrieved across sessions.\n• Crucial for creating personalized assistants that remember your coding preferences.",
            practicalUses = "• Remembering a user's dark mode preference or preferred programming language across reboots.",
            examples = "Example: An assistant remembering that you prefer Jetpack Compose over XML layouts in Android.",
            referenceUrl = "https://en.wikipedia.org/wiki/Memory"
        ),
        WordEntity(
            term = "Memory files",
            category = "Harness Building Blocks",
            part = "Part 3: A to Z master list",
            humanMeaning = "Markdown or JSON files saved on disk (like .cursorrules, CLAUDE.md, memory.json) where an agent logs project rules and learnings for future sessions.",
            keyPoints = "• Survives across restarts and context clears.\n• Agent updates the file when it discovers unique project quirks or instructions.\n• Simple, transparent, and version-controllable in git.",
            practicalUses = "• Preserving architectural conventions so future agent runs don't revert design patterns.",
            examples = "Example: Storing 'Always use Material 3 and avoid button outlines' in an instruction file.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Merge",
            category = "CI/CD & DevOps",
            part = "Part 3: A to Z master list",
            humanMeaning = "Combining the code changes from one Git branch (e.g. a feature branch) into another (e.g. the main production branch) after review and passing tests.",
            keyPoints = "• Resolves divergent lines of development.\n• Can be fast-forward, 3-way merge, or squash merge.\n• Handled through Pull Requests on GitHub after CI tests pass.",
            practicalUses = "• Integrating an AI agent's tested feature branch into your company's main branch.",
            examples = "Example: Running 'git checkout main && git merge feature/dark-mode'.",
            referenceUrl = "https://git-scm.com/docs/git-merge"
        ),
        WordEntity(
            term = "Messages API",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "The modern conversation-based API format (pioneered by OpenAI and Anthropic) where requests are structured as an array of role-based messages (system, user, assistant).",
            keyPoints = "• Replaced legacy raw-string completion endpoints.\n• Roles clearly distinguish between developer rules (system), user queries (user), and AI replies (assistant).\n• Standard format adopted across the industry and OpenRouter.",
            practicalUses = "• Structuring chat dialogues and tool responses cleanly for LLM processing.",
            examples = "Example: POST /v1/chat/completions with messages: [{\"role\": \"user\", \"content\": \"Explain DNS\"}].",
            referenceUrl = "https://docs.anthropic.com/en/api/messages"
        ),
        WordEntity(
            term = "Metric",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "A quantifiable measure used to track, score, and evaluate the performance, accuracy, latency, or cost of a software system or AI model.",
            keyPoints = "• Examples: Accuracy, Latency (ms), Tokens/sec, Hallucination Rate, API Cost ($).\n• Essential for objective benchmarking and observability.\n• Drives data-informed engineering decisions.",
            practicalUses = "• Tracking whether an optimization reduced app startup time from 1.2s to 300ms.",
            examples = "Example: Measuring Time-To-First-Token (TTFT) across OpenRouter model providers.",
            referenceUrl = "https://en.wikipedia.org/wiki/Performance_metric"
        ),
        WordEntity(
            term = "Microservices vs. monolith",
            category = "System Design",
            part = "Part 3: A to Z master list",
            humanMeaning = "Architectural choice: a Monolith builds an entire application as a single unified codebase; Microservices split it into multiple small, independent services communicating over APIs.",
            keyPoints = "• Monoliths: simple to build, test, and deploy early on; can become unwieldy at huge scale.\n• Microservices: allow independent scaling and deployment by separate teams; introduce network complexity.\n• Most startups start with a clean modular monolith before breaking off microservices.",
            practicalUses = "• Deciding how to architect backend infrastructure for an AI platform.",
            examples = "Example: Splitting a monolithic web app into an Auth Service, Billing Service, and AI Inference Service.",
            referenceUrl = "https://en.wikipedia.org/wiki/Microservices"
        ),
        WordEntity(
            term = "Mid",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A mid-level software engineer (typically 2-5 years of experience) who can independently design, build, and deliver complete features without hand-holding.",
            keyPoints = "• Possesses solid technical competence, debugging skills, and architectural judgment.\n• Delivers production-ready code while collaborating effectively with product managers.\n• Stepping stone between junior developer and senior engineer.",
            practicalUses = "• Leading the development of core mobile app features and backend integrations.",
            examples = "Example: A mid-level Android engineer architecting the local database and networking layers.",
            referenceUrl = "https://en.wikipedia.org/wiki/Software_engineering"
        ),
        WordEntity(
            term = "Misuse",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "Using artificial intelligence tools for malicious, harmful, or illegal activities (e.g. creating malware, generating disinformation, running phishing scams).",
            keyPoints = "• Primary concern addressed by AI lab terms of service and safety alignment.\n• Monitored by automated abuse detection systems and rate limiters.\n• Frontier labs enforce acceptable use policies to prevent dangerous misuse.",
            practicalUses = "• Detecting and blocking accounts attempting to generate automated phishing emails.",
            examples = "Example: Guardrails preventing an AI from helping someone write an exploit payload for a bank.",
            referenceUrl = "https://en.wikipedia.org/wiki/Computer_misuse"
        ),
        WordEntity(
            term = "ML engineer",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A machine learning engineer who designs, trains, optimizes, and deploys predictive and generative models onto production infrastructure at scale.",
            keyPoints = "• Strong coding skills (Python, C++, CUDA) combined with deep math and statistical knowledge.\n• Focuses on model architectures, distributed training on GPU clusters, and low-latency inference serving.\n• High demand and top-tier compensation across the tech industry.",
            practicalUses = "• Training custom neural networks and deploying them to production Kubernetes clusters.",
            examples = "Example: An ML engineer optimizing a 14B model to run with 4-bit quantization on mobile devices.",
            referenceUrl = "https://en.wikipedia.org/wiki/Machine_learning_engineer"
        ),
        WordEntity(
            term = "ML or AI round",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "A technical job interview session dedicated to evaluating your knowledge of machine learning theory, deep learning architectures, RAG design, and model evaluation.",
            keyPoints = "• Covers topics like backpropagation, transformers, attention, embeddings, and prompt caching.\n• May include coding an attention head in PyTorch or designing an end-to-end RAG architecture.\n• Evaluates both theoretical depth and practical engineering trade-offs.",
            practicalUses = "• Passing technical assessments when applying for AI engineering positions.",
            examples = "Example: Explaining the difference between fine-tuning with LoRA versus using RAG during an interview.",
            referenceUrl = "https://en.wikipedia.org/wiki/Technical_interview"
        ),
        WordEntity(
            term = "MLOps / platform engineer",
            category = "Careers & Jobs",
            part = "Part 3: A to Z master list",
            humanMeaning = "An engineer who builds and maintains the developer platforms, CI/CD pipelines, GPU clusters, and monitoring tools that allow ML teams to train and deploy models reliably.",
            keyPoints = "• Combines DevOps skills (Kubernetes, Docker, Terraform) with machine learning workflows.\n• Manages model registries, feature stores, and automated training pipelines.\n• Ensures high availability and cost efficiency for GPU clusters.",
            practicalUses = "• Keeping GPU training clusters running 24/7 without out-of-memory crashes.",
            examples = "Example: Setting up automated model deployment to Kubernetes when validation loss reaches a target threshold.",
            referenceUrl = "https://en.wikipedia.org/wiki/MLOps"
        ),
        WordEntity(
            term = "Mobile app",
            category = "Tools & UI",
            part = "Part 3: A to Z master list",
            humanMeaning = "A software application built specifically to run natively on mobile operating systems like Android and iOS, optimized for touchscreens, portability, and offline access.",
            keyPoints = "• Native performance using languages like Kotlin (Android) and Swift (iOS).\n• Direct access to device hardware (cameras, sensors, local SQLite databases, push notifications).\n• Works smoothly offline without depending constantly on cloud connections.",
            practicalUses = "• This exact application: an offline Android AI dictionary built with Jetpack Compose.",
            examples = "Example: An Android app installed on your smartphone providing instant, responsive dictionary lookups.",
            referenceUrl = "https://en.wikipedia.org/wiki/Mobile_app"
        ),
        WordEntity(
            term = "Model",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "The trained mathematical file (weights and architecture) that captures patterns learned from data, taking inputs and computing predictions or generating text.",
            keyPoints = "• Saved as binary weight files (.safetensors, .gguf, .onnx).\n• Runs inside an inference engine on GPU, NPU, or CPU.\n• Sized by parameter counts (e.g. 8 Billion, 70 Billion parameters).",
            practicalUses = "• Generating human-like text, answering questions, or predicting stock trends.",
            examples = "Example: Google Gemini 2.5 Flash model weights processing user prompt inputs.",
            referenceUrl = "https://en.wikipedia.org/wiki/Statistical_model"
        ),
        WordEntity(
            term = "Model card",
            category = "Safety & Alignment",
            part = "Part 3: A to Z master list",
            humanMeaning = "A standardized documentation report published with an AI model detailing its architecture, intended use cases, training data, limitations, and safety evaluations.",
            keyPoints = "• Introduced by Margaret Mitchell et al. to bring transparency to AI models.\n• Details known failure modes, demographic biases, and benchmark performance.\n• Standard practice on Hugging Face model repositories.",
            practicalUses = "• Reviewing a model card to check if a model was trained on coding tasks before selecting it.",
            examples = "Example: Reading the Llama 3 model card to understand its license terms and evaluation metrics.",
            referenceUrl = "https://en.wikipedia.org/wiki/Model_card"
        ),
        WordEntity(
            term = "Model hub",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "A centralized online repository (like Hugging Face or Google Cloud Model Garden) where developers can discover, download, evaluate, and share trained AI models.",
            keyPoints = "• The 'GitHub for model weights'.\n• Provides model cards, version history, demo playgrounds, and community discussion.\n• Hosts thousands of open-source models ready to deploy.",
            practicalUses = "• Finding and downloading the latest quantized GGUF weights for local execution.",
            examples = "Example: Hugging Face Hub hosting over 1 million open-source model checkpoints.",
            referenceUrl = "https://huggingface.co/models"
        ),
        WordEntity(
            term = "Model monitoring",
            category = "Evals & Observability",
            part = "Part 3: A to Z master list",
            humanMeaning = "Continuously tracking the operational health, accuracy, latency, error rates, and drift of an AI model running in live production environments.",
            keyPoints = "• Alerts engineers when latency spikes, token costs surge, or hallucination rates rise.\n• Captures live user feedback (thumbs up / thumbs down) for quality analysis.\n• Managed by observability tools like Langfuse, Datadog, or Arize.",
            practicalUses = "• Spotting that an API update caused average response latency to jump from 500ms to 3.2s.",
            examples = "Example: An alert triggering when 5% of production chatbot queries receive negative user feedback.",
            referenceUrl = "https://en.wikipedia.org/wiki/Model_monitoring"
        ),
        WordEntity(
            term = "Model registry",
            category = "CI/CD & DevOps",
            part = "Part 3: A to Z master list",
            humanMeaning = "A centralized repository that stores, versions, and manages machine learning model artifacts and checkpoints across development, staging, and production stages.",
            keyPoints = "• Tracks model lineage: which training code, dataset, and hyperparameters created each model artifact.\n• Manages model promotion: smoothly transition a model from staging to production.\n• Tools include MLflow Model Registry, Weights & Biases, and AWS SageMaker.",
            practicalUses = "• Tagging a fine-tuned model checkpoint as 'production-v2.1' for automated deployment.",
            examples = "Example: Promoting a newly fine-tuned LoRA adapter from candidate to production in MLflow.",
            referenceUrl = "https://en.wikipedia.org/wiki/MLOps#Model_registry"
        ),
        WordEntity(
            term = "Model routing / gateway",
            category = "Gateways & Inference",
            part = "Part 3: A to Z master list",
            humanMeaning = "An intelligent software layer (like OpenRouter or RouteLLM) that automatically directs each prompt to the fastest, cheapest, or smartest model capable of handling it.",
            keyPoints = "• Simple prompts go to cheap, fast models (Gemini Flash, Haiku); hard prompts go to frontier models (Claude 3.7, o3).\n• Can cut overall AI inference costs by 60% to 80% with zero quality loss.\n• Automatically handles failovers and provider rate limits.",
            practicalUses = "• Sending casual queries to a $0.10/M token model while routing complex code tasks to a $3.00/M token model.",
            examples = "Example: OpenRouter routing prompt traffic across multiple global providers for maximum uptime.",
            referenceUrl = "https://openrouter.ai"
        ),
        WordEntity(
            term = "MoE",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "Mixture of Experts: a neural network architecture that contains multiple specialized sub-networks ('experts') and routes each token to only a few active experts per forward pass.",
            keyPoints = "• Massive total parameter count (e.g. 400B) but low active parameters per token (e.g. 40B).\n• Blazing-fast inference speed and dramatically lower compute cost per token.\n• Powers models like Mixtral 8x7B, DeepSeek-V3, and modern frontier models.",
            practicalUses = "• Getting the intelligence of a massive model with the inference speed of a small model.",
            examples = "Example: DeepSeek-V3 activating only 37B out of its 671B total parameters for any single token.",
            referenceUrl = "https://en.wikipedia.org/wiki/Mixture_of_experts"
        ),
        WordEntity(
            term = "Multi-agent",
            category = "Agentic AI",
            part = "Part 3: A to Z master list",
            humanMeaning = "A system architecture where multiple specialized AI agents with distinct roles, tools, and prompts collaborate, converse, and cross-verify to solve complex objectives.",
            keyPoints = "• Divides and conquers complex tasks better than a single giant prompt.\n• Role specialization: e.g. Researcher, Architect, Coder, and Tester agents working together.\n• Orchestrated by frameworks like CrewAI, AutoGen, and LangGraph.",
            practicalUses = "• An automated software development team where one agent writes code and another independently writes tests.",
            examples = "Example: A planner agent delegating code execution to a junior agent and review to a critic agent.",
            referenceUrl = "https://en.wikipedia.org/wiki/Multi-agent_system"
        ),
        WordEntity(
            term = "Multimodal",
            category = "AI Core",
            part = "Part 3: A to Z master list",
            humanMeaning = "An AI model capable of processing, understanding, and generating multiple different types of data (text, images, audio, video) seamlessly in a single unified architecture.",
            keyPoints = "• Transcends text-only limitations: can look at screenshots, listen to speech, and read PDFs.\n• Native multimodality processes images and audio directly without separate external OCR or speech tools.\n• Standard feature of frontier models (GPT-4o, Claude 3.5, Gemini 2.5).",
            practicalUses = "• Taking a photo of an error on your laptop screen and having the AI debug the issue immediately.",
            examples = "Example: Uploading an architectural diagram and asking the AI to write the corresponding database schema.",
            referenceUrl = "https://en.wikipedia.org/wiki/Multimodal_interaction"
        )
    )
}
