package com.example.data.local

object DefaultWordsPart2Harnesses {
    fun getTerms(): List<WordEntity> = listOf(
        // Coding agent harnesses (17)
        WordEntity(
            term = "Claude Code",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Anthropic's terminal-native coding agent tool that executes terminal commands, reads your codebase, edits files, and runs git workflows autonomously.",
            keyPoints = "• Operates directly inside your terminal, IDE, and desktop.\n• Uses agentic loops with bash execution, file search, and test verification.\n• Configured using CLAUDE.md project memory and custom subagents.",
            practicalUses = "• Refactoring large codebases, writing tests, investigating bugs, creating git pull requests.",
            examples = "Example: Running 'claude' in a terminal and asking 'migrate our database schema and update unit tests'.",
            referenceUrl = "https://docs.anthropic.com/en/docs/agents-and-tools/claude-code"
        ),
        WordEntity(
            term = "Codex CLI",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "OpenAI's command-line coding agent designed to automate terminal tasks, write code, and navigate repository file trees from the command line.",
            keyPoints = "• CLI interface powered by OpenAI reasoning and code generation models.\n• Translates natural language intent into shell commands and file changes.\n• Supports interactive plan-and-execute workflows.",
            practicalUses = "• Quick command generation, scaffolding scripts, automated code repairs.",
            examples = "Example: Running codex to generate and run a bash deployment pipeline.",
            referenceUrl = "https://openai.com"
        ),
        WordEntity(
            term = "Gemini CLI",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Google's open-source terminal developer agent that leverages Gemini models to analyze local repos, generate code, and automate developer tasks.",
            keyPoints = "• Integrates Google's massive context window Gemini models.\n• Can ingest entire multi-megabyte repos into context in a single prompt.\n• Open-source and customizable for terminal workflows.",
            practicalUses = "• Analyzing huge code repositories that exceed traditional context limits.",
            examples = "Example: Asking Gemini CLI to map the dependency graph across 500 files.",
            referenceUrl = "https://github.com/google-gemini"
        ),
        WordEntity(
            term = "Cursor",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An AI-first code editor (forked from VS Code) with an autonomous Composer agent mode that plans, writes, and edits multiple files concurrently.",
            keyPoints = "• Features Composer (Cmd+I) multi-file agentic editing.\n• Fast codebase indexing with semantic vector embeddings.\n• Native tab auto-completion predicting multi-line edits.",
            practicalUses = "• Full-stack feature development, multi-file refactoring, instant code generation.",
            examples = "Example: Typing 'add dark mode toggle to the top bar and update theme state' in Cursor Composer.",
            referenceUrl = "https://cursor.com"
        ),
        WordEntity(
            term = "Windsurf",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An AI IDE developed by Codeium featuring 'Cascade', a collaborative agent that understands your real-time actions and writes code alongside you.",
            keyPoints = "• Cascade flow deep-links editor actions with agent reasoning.\n• Real-time awareness of active terminals, open tabs, and recent clicks.\n• Seamless handoffs between human typing and agent execution.",
            practicalUses = "• Building features with an agent that sees your terminal errors as you type.",
            examples = "Example: Cascade detects a TypeScript compile error and immediately offers a 1-click fix.",
            referenceUrl = "https://codeium.com/windsurf"
        ),
        WordEntity(
            term = "GitHub Copilot (agent mode)",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "GitHub's agentic mode inside VS Code and GitHub.com that iterates autonomously to fix issues, write tests, and submit complete pull requests.",
            keyPoints = "• Iterates on terminal test failures until tests pass.\n• Integrated with GitHub Issues, Pull Requests, and Actions.\n• Backed by OpenAI GPT-4o and Anthropic Claude models.",
            practicalUses = "• Assigning an open GitHub issue to Copilot Agent to write the pull request.",
            examples = "Example: In VS Code, @workspace /agent 'implement search filter on words list'.",
            referenceUrl = "https://github.com/features/copilot"
        ),
        WordEntity(
            term = "Cline",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source autonomous coding agent extension for VS Code that asks for permission to run terminal commands, create files, and use browser testing.",
            keyPoints = "• Complete transparent control: asks for human approval before destructive actions.\n• Uses Model Context Protocol (MCP) to connect external tools.\n• Supports any model provider (OpenRouter, Anthropic, Ollama).",
            practicalUses = "• Safe autonomous coding inside VS Code without vendor lock-in.",
            examples = "Example: Cline reading your workspace, installing npm packages, and building the project.",
            referenceUrl = "https://github.com/cline/cline"
        ),
        WordEntity(
            term = "Roo Code",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A popular community-driven fork of Cline with specialized persona modes (Code, Architect, Ask, Debugger) and custom system prompts.",
            keyPoints = "• Specialized role modes tailoring behavior for architecture vs. raw coding.\n• Enhanced diff viewer and token usage tracking.\n• Robust MCP tool integration.",
            practicalUses = "• Switching to 'Architect mode' to design a database before switching to 'Code mode' to write it.",
            examples = "Example: Using Roo Code in Architect mode to create an API specification.",
            referenceUrl = "https://github.com/RooVetGit/Roo-Code"
        ),
        WordEntity(
            term = "Aider",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A beloved terminal pair-programming tool that pairs with you to edit code in your local git repository and automatically commits changes with descriptive messages.",
            keyPoints = "• Automatically makes sensible git commits for every change.\n• Uses repository map algorithms to fit whole-codebase maps into context.\n• Works with Claude 3.5 Sonnet, GPT-4o, DeepSeek, and local Ollama.",
            practicalUses = "• Command-line pair programming directly inside your terminal git workflow.",
            examples = "Example: Running 'aider app.py' and prompting 'add user authentication using JWT'.",
            referenceUrl = "https://aider.chat"
        ),
        WordEntity(
            term = "OpenHands",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source AI software engineering agent platform (formerly OpenDevin) that runs in Docker sandboxes to autonomously solve software tasks.",
            keyPoints = "• Sandboxed Docker container environment for safe command execution.\n• Web browser UI, terminal, and code editor view.\n• Capable of autonomously reproducing and fixing bugs on GitHub.",
            practicalUses = "• Autonomous overnight bug fixing and running SWE-bench evaluations.",
            examples = "Example: Spinning up OpenHands on Docker to resolve a bug ticket end-to-end.",
            referenceUrl = "https://github.com/All-Hands-AI/OpenHands"
        ),
        WordEntity(
            term = "Goose",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source, on-machine extensible AI agent developed by Block that operates through local MCP servers and terminal commands to automate workflows.",
            keyPoints = "• Built by Block (Square/CashApp) as an extensible desktop/terminal agent.\n• Deep native support for Model Context Protocol (MCP).\n• Can control your desktop, databases, and development environment.",
            practicalUses = "• Running automated DevOps routines and local machine automation safely.",
            examples = "Example: Using Goose to check local Docker containers and run migration scripts.",
            referenceUrl = "https://github.com/block/goose"
        ),
        WordEntity(
            term = "OpenCode",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source terminal coding agent that provides an agile, customizable CLI assistant for software developers.",
            keyPoints = "• Lightweight terminal interface.\n• Works with open-weight and proprietary LLM endpoints.\n• Focuses on quick code generations and terminal explanations.",
            practicalUses = "• Terminal-based coding assistance without heavy graphical IDE overhead.",
            examples = "Example: Inspecting compiler logs and generating patch fixes in terminal.",
            referenceUrl = "https://github.com"
        ),
        WordEntity(
            term = "Amp",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A high-leverage coding agent created by Sourcegraph that leverages massive codebase intelligence and code graphs to execute multi-repository changes.",
            keyPoints = "• Deep integration with Sourcegraph's universal code search index.\n• Handles large-scale multi-repo batch changes.\n• Understands cross-service dependencies and symbols.",
            practicalUses = "• Updating a deprecated API method across 40 different microservice repos at once.",
            examples = "Example: Refactoring internal logging libraries across an enterprise monorepo.",
            referenceUrl = "https://sourcegraph.com"
        ),
        WordEntity(
            term = "Kiro",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A spec-driven agentic IDE and harness from AWS designed to take formal specifications and generate robust, cloud-compliant architectures.",
            keyPoints = "• Spec-first development approach.\n• Direct integration with AWS cloud infrastructure patterns.\n• Verifies generated code against security and architecture specifications.",
            practicalUses = "• Building enterprise AWS serverless and containerized systems from architectural specs.",
            examples = "Example: Generating DynamoDB models and Lambda handlers from a JSON schema spec.",
            referenceUrl = "https://aws.amazon.com"
        ),
        WordEntity(
            term = "Devin",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The world's first fully autonomous AI software engineer developed by Cognition, featuring a long-term reasoning sandboxed environment with browser, shell, and editor.",
            keyPoints = "• Operates independently on complex multi-hour engineering tasks.\n• Plans workflows, reads documentation, debugs errors, and reports progress.\n• Holds the record for high-benchmark scores on SWE-bench.",
            practicalUses = "• Delegating complete feature tickets, API migrations, and software upgrades autonomously.",
            examples = "Example: Devin reading a documentation page, setting up an open-source project from scratch, and passing tests.",
            referenceUrl = "https://cognition.ai"
        ),
        WordEntity(
            term = "Zed",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A lightning-fast, GPU-accelerated code editor written in Rust with built-in multi-model AI assistant panels and agentic workflows.",
            keyPoints = "• Sub-millisecond keystroke responsiveness powered by Rust and GPUI.\n• Built-in assistant panel supporting Anthropic, OpenAI, and Ollama.\n• Collaborative multiplayer pairing built-in.",
            practicalUses = "• Developers who demand maximum editor speed with integrated AI assistance.",
            examples = "Example: Transforming code inline using Cmd+? with instant stream rendering.",
            referenceUrl = "https://zed.dev"
        ),
        WordEntity(
            term = "Continue",
            category = "Agent Harnesses",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The leading open-source AI code assistant extension for VS Code and JetBrains that lets you use any LLM for autocomplete, chat, and codebase editing.",
            keyPoints = "• Full support for open local models via Ollama, LM Studio, or vLLM.\n• Context providers (@code, @docs, @git) to pull relevant info into prompts.\n• Zero telemetry option for strict enterprise privacy.",
            practicalUses = "• Running 100% offline, private AI autocomplete and chat inside company laptops.",
            examples = "Example: In VS Code, highlighting a function and pressing Cmd+I to edit it using Continue.",
            referenceUrl = "https://continue.dev"
        ),

        // Agent frameworks and SDKs (16)
        WordEntity(
            term = "Claude Agent SDK",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Anthropic's software development kit for building custom autonomous agents on the battle-tested Claude Code harness.",
            keyPoints = "• Built on Anthropic's Claude 3.5 Sonnet / Haiku tool-use primitives.\n• Provides agent loop management, sandboxing, and subagent orchestration.\n• Production-ready harness for building internal enterprise software engineers.",
            practicalUses = "• Creating internal automated bug-fix bots that integrate with company git repos.",
            examples = "Example: Initializing an agent with ClaudeAgentSDK to process incoming customer bug reports.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "OpenAI Agents SDK",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "OpenAI's lightweight multi-agent orchestration framework (the successor to Swarm) designed for ergonomic agent handoffs and tool execution.",
            keyPoints = "• Focuses on clean 'handoffs' between specialized agents.\n• Lightweight Python abstractions without unnecessary boilerplate.\n• Native support for function calling and guardrails.",
            practicalUses = "• Building triage support bots that hand off tickets to billing, tech, or refund agents.",
            examples = "Example: Agent A recognizing a billing query and handing off control to BillingAgent.",
            referenceUrl = "https://github.com/openai"
        ),
        WordEntity(
            term = "LangGraph",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A graph-based stateful agent orchestration library from LangChain that models agent loops, branching logic, and human approval gates as cyclic graphs.",
            keyPoints = "• First-class support for cycles, loops, and branching (unlike standard DAGs).\n• Built-in persistence: state is checkpointed at every graph node.\n• Enables time-travel debugging and human-in-the-loop approval gates.",
            practicalUses = "• Complex multi-step reasoning agents that must loop back and fix errors before proceeding.",
            examples = "Example: Graph: [Draft Code] -> [Run Tests] -> if failed loop to [Fix Code] -> if passed [Deploy].",
            referenceUrl = "https://langchain-ai.github.io/langgraph"
        ),
        WordEntity(
            term = "LangChain",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The original and most widespread software framework for building LLM applications, offering modular abstractions for prompts, models, vector stores, and chains.",
            keyPoints = "• Standardized integrations with hundreds of models, databases, and APIs.\n• LCEL (LangChain Expression Language) for declarative streaming pipelines.\n• Extensive ecosystem of document loaders and text splitters.",
            practicalUses = "• Rapidly prototyping RAG apps, document question-answering, and chatbot backends.",
            examples = "Example: prompt | model | StrOutputParser() in Python.",
            referenceUrl = "https://github.com/langchain-ai/langchain"
        ),
        WordEntity(
            term = "LlamaIndex",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The premier data framework for connecting private custom data sources, documents, and enterprise databases to large language models for RAG.",
            keyPoints = "• Advanced indexing structures (vector, keyword, graph, hierarchical).\n• Production RAG features: re-ranking, query routing, hybrid retrieval.\n• Extensive document connectors (PDF, Slack, Google Drive, Notion).",
            practicalUses = "• Ingesting company PDF manuals and enabling conversational Q&A over them.",
            examples = "Example: VectorStoreIndex.from_documents(docs) and querying with query_engine.query('What is the refund policy?').",
            referenceUrl = "https://www.llamaindex.ai"
        ),
        WordEntity(
            term = "CrewAI",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A framework for orchestrating role-playing autonomous AI agents that collaborate as a crew with specific roles, goals, and backstories.",
            keyPoints = "• Define agents with specific personas (e.g., 'Senior Researcher', 'Chief Editor').\n• Assign sequential or hierarchical tasks across crew members.\n• Built-in memory and tool sharing between agents.",
            practicalUses = "• Content marketing research crews, competitive intelligence gathering, multi-step code reviews.",
            examples = "Example: Researcher agent finds news -> Writer agent drafts article -> Editor agent reviews and polishes.",
            referenceUrl = "https://www.crewai.com"
        ),
        WordEntity(
            term = "AutoGen",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Microsoft's multi-agent conversational framework that enables multiple AI agents to converse with each other to solve complex tasks collaboratively.",
            keyPoints = "• Conversational programming paradigm: agents solve problems by chatting.\n• Supports human input participation in the group conversation.\n• Code execution sandbox allows agents to write and execute Python scripts.",
            practicalUses = "• Financial analysis pipelines where a data agent and a math agent cross-verify formulas.",
            examples = "Example: GroupChat with UserProxyAgent, CoderAgent, and CriticAgent.",
            referenceUrl = "https://microsoft.github.io/autogen"
        ),
        WordEntity(
            term = "Microsoft Agent Framework",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Microsoft's unified modern agent platform combining the multi-agent conversational strengths of AutoGen with the enterprise architecture of Semantic Kernel.",
            keyPoints = "• Enterprise-ready agent orchestration across C# and Python.\n• Native Azure AI security, identity, and compliance integration.\n• Scales from single-turn copilot plugins to complex multi-agent systems.",
            practicalUses = "• Enterprise Microsoft 365 copilot extensions and Azure cloud automation.",
            examples = "Example: Deploying an agent system into Azure Container Apps with managed identity.",
            referenceUrl = "https://learn.microsoft.com"
        ),
        WordEntity(
            term = "Semantic Kernel",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Microsoft's open-source enterprise SDK that integrates LLMs into conventional C#, Python, and Java software using plugins and planners.",
            keyPoints = "• Seamlessly blends traditional code functions with AI prompt plugins.\n• Strong enterprise typing, telemetry, and dependency injection support.\n• Used internally across Microsoft Copilot products.",
            practicalUses = "• Adding AI copilot capabilities to existing enterprise .NET and Java backends.",
            examples = "Example: Calling a native C# billing function as an AI tool using [KernelFunction].",
            referenceUrl = "https://github.com/microsoft/semantic-kernel"
        ),
        WordEntity(
            term = "Google ADK",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Google's Agent Development Kit: a developer SDK providing structured patterns to build, test, and deploy intelligent agents powered by Gemini models.",
            keyPoints = "• Native support for Gemini 2.0 / 2.5 multimodal features and function calling.\n• Integrated with Google Vertex AI and Firebase AI Logic.\n• Built-in support for grounding with Google Search and enterprise data.",
            practicalUses = "• Deploying high-scale conversational agents on Google Cloud.",
            examples = "Example: Building an agent that grounds its answers in Google Search results and private BigQuery tables.",
            referenceUrl = "https://cloud.google.com/vertex-ai"
        ),
        WordEntity(
            term = "Mastra",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An opinionated, type-safe TypeScript agent framework that provides workflows, agents, tools, and evaluations for modern JavaScript/TypeScript developers.",
            keyPoints = "• Built specifically for TypeScript with full type inference.\n• Graph-based workflows with branching, retries, and checkpoints.\n• Built-in support for evals and observability.",
            practicalUses = "• Next.js and Node.js full-stack teams building production AI agents.",
            examples = "Example: const agent = new Agent({ name: 'Support', instructions: '...', model: openai('gpt-4o') });",
            referenceUrl = "https://mastra.ai"
        ),
        WordEntity(
            term = "Pydantic AI",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A Python agent framework built by the creators of Pydantic that brings production-grade type validation, dependency injection, and structured outputs to LLM agents.",
            keyPoints = "• Uses standard Pydantic models for bulletproof structured data output.\n• Type-safe dependency injection for database connections and HTTP clients.\n• Model-agnostic (works with OpenAI, Anthropic, Gemini, Groq, Ollama).",
            practicalUses = "• Mission-critical Python backends where LLM outputs must strictly adhere to data schemas.",
            examples = "Example: agent = Agent('openai:gpt-4o', result_type=UserProfileResponse).",
            referenceUrl = "https://ai.pydantic.dev"
        ),
        WordEntity(
            term = "smolagents",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A lightweight, minimalist Python agent library from Hugging Face where agents write actions as raw Python code rather than awkward JSON tool calls.",
            keyPoints = "• CodeAgent writes real Python code snippets to call tools (Code-as-action).\n• Much more flexible and concise for complex calculations or loop iterations.\n• Extremely small, transparent codebase (~1,000 lines of Python).",
            practicalUses = "• Quick experimental research agents and Hugging Face Hub workflows.",
            examples = "Example: CodeAgent(tools=[duckduckgo_search], model=HfApiModel()).",
            referenceUrl = "https://github.com/huggingface/smolagents"
        ),
        WordEntity(
            term = "DSPy",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Stanford's revolutionary declarative framework that replaces manual prompt engineering with algorithmic prompt optimization and automatic few-shot compilation.",
            keyPoints = "• Treats prompts as optimizable code modules rather than brittle strings.\n• Uses teleprompters (optimizers) to discover the best prompts against an evaluation metric.\n• Automatically generates few-shot examples and tunes chain-of-thought steps.",
            practicalUses = "• Drastically improving pipeline accuracy on benchmarks without manually tweaking prompts.",
            examples = "Example: Compiling a pipeline with dspy.teleprompt.BootstrapFewShot to maximize test score.",
            referenceUrl = "https://dspy.ai"
        ),
        WordEntity(
            term = "Haystack",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source Python framework by deepset for building customizable, production-grade search and RAG pipelines using directed acyclic graphs.",
            keyPoints = "• Component-based pipeline architecture (clean DAG).\n• Supports hybrid BM25 and dense vector search retrieval.\n• Modular integrations with leading vector databases and cloud providers.",
            practicalUses = "• Enterprise semantic document search engines and question-answering systems.",
            examples = "Example: Connecting a DocumentStore, Retriever, and Reader component into an end-to-end pipeline.",
            referenceUrl = "https://haystack.deepset.ai"
        ),
        WordEntity(
            term = "Vercel AI SDK",
            category = "Frameworks & SDKs",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The gold standard TypeScript library for building reactive AI web apps, providing unified APIs for streaming text, generative UI, and tool calling.",
            keyPoints = "• streamText and streamUI for instant client-side streaming in React and Next.js.\n• Unified multi-provider interface (OpenAI, Anthropic, Google, Groq).\n• useChat and useCompletion hooks for effortless frontend state management.",
            practicalUses = "• Modern web applications with instant AI streaming interfaces.",
            examples = "Example: const { messages, input, handleSubmit } = useChat();",
            referenceUrl = "https://sdk.vercel.ai"
        )
    )
}
