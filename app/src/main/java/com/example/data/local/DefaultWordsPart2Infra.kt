package com.example.data.local

object DefaultWordsPart2Infra {
    fun getTerms(): List<WordEntity> = listOf(
        // Gateways and inference (14)
        WordEntity(
            term = "LiteLLM",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source proxy and Python SDK that translates 100+ LLM provider APIs (Anthropic, Bedrock, Vertex, Ollama) into a standard OpenAI-compatible format.",
            keyPoints = "• Call all models using one unified format (client.chat.completions.create).\n• Built-in load balancing, fallback routing, and cost tracking.\n• Proxy server with virtual keys and team rate limits.",
            practicalUses = "• Switching between model providers without rewriting application code.",
            examples = "Example: litellm.completion(model='claude-3-5-sonnet', messages=[...]).",
            referenceUrl = "https://github.com/BerriAI/litellm"
        ),
        WordEntity(
            term = "OpenRouter",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A unified API gateway that gives you access to hundreds of AI models from Anthropic, OpenAI, Meta, Google, Mistral, and open-source creators with one API key.",
            keyPoints = "• Single API endpoint (https://openrouter.ai/api/v1) for hundreds of models.\n• Automatic failover routing: if one provider is down, routes to another instance.\n• Pay-as-you-go with transparent token pricing and leaderboard benchmarks.",
            practicalUses = "• Powering multi-model applications, coding agents, and cost-optimized fallback pipelines.",
            examples = "Example: Sending POST /v1/chat/completions with header 'Authorization: Bearer sk-or-v1-...' and model 'google/gemini-2.5-flash'.",
            referenceUrl = "https://openrouter.ai"
        ),
        WordEntity(
            term = "Portkey",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An AI gateway and control plane for production LLM apps that provides automated fallbacks, load balancing, semantic caching, and full observability.",
            keyPoints = "• Blazing fast 5ms edge gateway routing.\n• Automatic retries, rate-limiting, and cost budgets.\n• Detailed audit logs and compliance tracing.",
            practicalUses = "• Production enterprise AI apps needing 99.99% uptime with multi-provider fallbacks.",
            examples = "Example: If OpenAI returns 429 Rate Limit, Portkey automatically retries on Anthropic without code changes.",
            referenceUrl = "https://portkey.ai"
        ),
        WordEntity(
            term = "Ollama",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A simple, friendly tool that lets you run large language models (Llama 3, DeepSeek, Mistral, Gemma) locally on your own Mac, Windows, or Linux laptop.",
            keyPoints = "• One-line command setup: 'ollama run llama3.3'.\n• Bundles model weights, quantization, and GPU acceleration into a Modelfile.\n• Exposes an OpenAI-compatible REST API on localhost:11434.",
            practicalUses = "• 100% offline private AI development with zero cloud API costs or data leakage.",
            examples = "Example: Running local code assistance with 'ollama run qwen2.5-coder'.",
            referenceUrl = "https://ollama.com"
        ),
        WordEntity(
            term = "vLLM",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A high-throughput, memory-efficient open-source LLM inference and serving engine powered by PagedAttention for production GPU clusters.",
            keyPoints = "• PagedAttention eliminates memory fragmentation in the KV cache, boosting throughput 2x-4x.\n• Continuous batching of incoming requests for maximum GPU saturation.\n• Standard serving backend used by AI startups and cloud providers.",
            practicalUses = "• Serving open-weight models at scale on private AWS/GCP GPU instances.",
            examples = "Example: Running 'python3 -m vllm.entrypoints.openai.api_server --model mistralai/Mistral-7B-Instruct-v0.3'.",
            referenceUrl = "https://github.com/vllm-project/vllm"
        ),
        WordEntity(
            term = "llama.cpp",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A port of LLaMA inference in pure C/C++ by Georgi Gerganov that enables running quantized models on consumer CPUs, Apple Silicon Macs, and modest GPUs.",
            keyPoints = "• Minimal dependencies and zero heavy Python baggage.\n• Pioneered the GGUF model file format and 4-bit/8-bit integer quantization.\n• Powers the underlying inference inside Ollama and LM Studio.",
            practicalUses = "• Running 70B models smoothly on everyday consumer hardware.",
            examples = "Example: Running './main -m models/llama-3-8b.Q4_K_M.gguf -p \"Hello!\"' on an Apple M3 MacBook.",
            referenceUrl = "https://github.com/ggerganov/llama.cpp"
        ),
        WordEntity(
            term = "LM Studio",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A desktop application that lets you browse, download, and chat with open-source LLMs locally on your computer with a clean graphical interface.",
            keyPoints = "• Built-in Hugging Face model browser and 1-click GGUF downloader.\n• Local developer server with OpenAI-compatible API endpoints.\n• Hardware acceleration for Apple Metal and NVIDIA CUDA.",
            practicalUses = "• Testing open-weight models locally without touching terminal command lines.",
            examples = "Example: Downloading DeepSeek-R1-Distill locally and chatting with it offline.",
            referenceUrl = "https://lmstudio.ai"
        ),
        WordEntity(
            term = "Hugging Face",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The 'GitHub of Machine Learning': the world's primary community platform and open repository for AI models, datasets, and demo apps (Spaces).",
            keyPoints = "• Hosts over 1 million open-source model weights (Transformers, Diffusers).\n• Hub for state-of-the-art benchmarks and leaderboards.\n• Provides standard Python libraries: transformers, datasets, accelerate, safetensors.",
            practicalUses = "• Finding and downloading model checkpoints, datasets, and tokenizers.",
            examples = "Example: from transformers import AutoModelForCausalLM; model = AutoModelForCausalLM.from_pretrained('meta-llama/Llama-3-8B').",
            referenceUrl = "https://huggingface.co"
        ),
        WordEntity(
            term = "AWS Bedrock",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Amazon's fully managed cloud service that provides access to leading foundation models (Claude, Llama, Mistral, Titan) through a secure AWS API.",
            keyPoints = "• Run frontier models within your private AWS VPC without data leaving AWS.\n• Integrated with AWS IAM permissions, CloudWatch logs, and KMS encryption.\n• Supports custom model fine-tuning and agent guardrails.",
            practicalUses = "• Enterprise companies using Claude 3.5 under strict AWS compliance agreements.",
            examples = "Example: Calling bedrock_runtime.invoke_model with anthropic.claude-3-5-sonnet.",
            referenceUrl = "https://aws.amazon.com/bedrock"
        ),
        WordEntity(
            term = "Google Vertex AI",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Google Cloud's end-to-end enterprise machine learning platform that serves Gemini models, Model Garden open models, and custom training pipelines.",
            keyPoints = "• Managed platform for Google Gemini 1.5, 2.0, and 2.5 models.\n• Native grounding with Google Search and internal enterprise BigQuery/vector data.\n• Deep integration with Firebase AI Logic for Android and iOS mobile developers.",
            practicalUses = "• Enterprise generative AI applications with Google-grade security and multimodality.",
            examples = "Example: Deploying a customer service bot powered by Gemini 2.5 Flash on Vertex AI.",
            referenceUrl = "https://cloud.google.com/vertex-ai"
        ),
        WordEntity(
            term = "Azure AI Foundry",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Microsoft's enterprise cloud studio (formerly Azure AI Studio) for building, evaluating, and deploying generative AI solutions and OpenAI models.",
            keyPoints = "• Hosts Azure OpenAI Service (GPT-4o, o1, o3-mini) with private enterprise SLAs.\n• Integrated prompt flow, safety filters, and model benchmarking.\n• Complies with enterprise HIPAA, FedRAMP, and ISO standards.",
            practicalUses = "• Fortune 500 companies deploying enterprise AI assistants inside Microsoft clouds.",
            examples = "Example: Generating AI copilot pipelines with Azure OpenAI endpoints and Azure AI Search.",
            referenceUrl = "https://azure.microsoft.com/en-us/solutions/ai"
        ),
        WordEntity(
            term = "Groq",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A specialized AI hardware company whose custom LPU (Language Processing Unit) chips generate LLM tokens at blistering speeds of 300 to 800+ tokens per second.",
            keyPoints = "• Custom LPU chip architecture designed strictly for sequential LLM inference.\n• Near-instantaneous streaming response generation.\n• OpenAI-compatible API hosting popular open-weight models (Llama, Gemma, Whisper).",
            practicalUses = "• Ultra-low-latency conversational voice agents and instant code generation.",
            examples = "Example: Receiving a 500-word response from Llama 3 in less than 1.5 seconds.",
            referenceUrl = "https://groq.com"
        ),
        WordEntity(
            term = "Together AI",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A fast cloud platform specializing in cost-effective inference and fine-tuning of open-source AI models on optimized GPU infrastructure.",
            keyPoints = "• Blazing-fast inference for DeepSeek, Llama, and Mistral models.\n• Dedicated GPU clusters and serverless pay-per-token pricing.\n• Full fine-tuning APIs and custom model hosting.",
            practicalUses = "• Developers wanting fast, cheap open-source model inference without managing GPUs.",
            examples = "Example: Fine-tuning Llama 3 on customer support transcripts and deploying the endpoint in minutes.",
            referenceUrl = "https://www.together.ai"
        ),
        WordEntity(
            term = "Fireworks AI",
            category = "Gateways & Inference",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A production-grade AI inference platform optimized for compound AI systems, sub-second latency, and custom LoRA adapter switching.",
            keyPoints = "• Industry-leading speed on open-source language, multimodal, and image models.\n• Instant dynamic LoRA swapping without redeploying base models.\n• Built-in function calling and structured JSON schema enforcement.",
            practicalUses = "• Serving specialized fine-tuned models at enterprise scale with low latency.",
            examples = "Example: Dynamically attaching a user's custom LoRA adapter to a base model on every API request.",
            referenceUrl = "https://fireworks.ai"
        ),

        // CI/CD and DevOps tools (14)
        WordEntity(
            term = "GitHub Actions",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "GitHub's built-in continuous integration and deployment automation platform that runs tests, builds APKs, and deploys code on every git push.",
            keyPoints = "• Defined as YAML workflow files inside .github/workflows/.\n• Huge marketplace of community-contributed actions.\n• Runs on hosted Linux, macOS, and Windows virtual machine runners.",
            practicalUses = "• Automatically compiling Android APKs and running Robolectric tests on every PR.",
            examples = "Example: Workflow running 'gradle testDebugUnitTest' whenever code is pushed to main.",
            referenceUrl = "https://github.com/features/actions"
        ),
        WordEntity(
            term = "GitLab CI",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "GitLab's integrated continuous delivery platform that uses a single .gitlab-ci.yml configuration file to automate testing, security scanning, and deployment.",
            keyPoints = "• Deeply integrated with GitLab issues, merge requests, and container registries.\n• Built-in static application security testing (SAST) and license compliance.\n• Supports self-hosted runners for on-premise compute.",
            practicalUses = "• Enterprise DevSecOps pipelines with automated vulnerability scans before merge.",
            examples = "Example: Pipeline building Docker containers and deploying them to Kubernetes staging.",
            referenceUrl = "https://about.gitlab.com/stages-devops-lifecycle/continuous-integration"
        ),
        WordEntity(
            term = "Jenkins",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The veteran open-source Java-based automation server used by thousands of companies to build, test, and deploy software via customizable pipelines.",
            keyPoints = "• Highly extensible ecosystem with over 1,800 community plugins.\n• Pipelines defined using Groovy-based Jenkinsfile scripts.\n• Self-hosted on dedicated servers or Kubernetes clusters.",
            practicalUses = "• Heavy legacy enterprise build pipelines requiring custom hardware or complex orchestrations.",
            examples = "Example: Running nightly regression tests across multiple physical hardware test racks.",
            referenceUrl = "https://www.jenkins.io"
        ),
        WordEntity(
            term = "CircleCI",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A fast, cloud-hosted CI/CD automation service known for aggressive parallelism, Docker layer caching, and rapid feedback loops.",
            keyPoints = "• Configured in .circleci/config.yml with reusable Orbs.\n• Powerful test splitting and parallelism across multiple machines.\n• Fast Docker container spin-up times.",
            practicalUses = "• Cutting a 30-minute test suite down to 3 minutes by running across 10 parallel containers.",
            examples = "Example: Running yarn test --split-by=timings across 8 parallel test workers.",
            referenceUrl = "https://circleci.com"
        ),
        WordEntity(
            term = "Argo CD",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A declarative GitOps continuous delivery tool for Kubernetes that continuously monitors your git repo and synchronizes your live cluster to match it.",
            keyPoints = "• Git is the single source of truth for desired infrastructure state.\n• Detects configuration drift and automatically syncs live Kubernetes pods.\n• Visual web dashboard showing application health and rollout states.",
            practicalUses = "• Deploying new microservice versions simply by updating a tag in a Git repository.",
            examples = "Example: Changing image tag from :v1.0 to :v1.1 in Git triggers Argo CD to roll out new pods automatically.",
            referenceUrl = "https://argo-cd.readthedocs.io"
        ),
        WordEntity(
            term = "Terraform",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "HashiCorp's declarative Infrastructure-as-Code tool that lets you define cloud servers, networks, and databases using human-readable HCL configuration files.",
            keyPoints = "• Multi-cloud support across AWS, GCP, Azure, Cloudflare, and Kubernetes.\n• Generates an execution plan ('terraform plan') before applying changes.\n• Maintains a state file tracking real-world cloud resources.",
            practicalUses = "• Provisioning complete cloud VPCs, databases, and load balancers with a single command.",
            examples = "Example: resource \"aws_instance\" \"web\" { ami = \"...\" instance_type = \"t3.micro\" }.",
            referenceUrl = "https://www.terraform.io"
        ),
        WordEntity(
            term = "Pulumi",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An modern Infrastructure-as-Code platform that lets you provision and manage cloud infrastructure using real programming languages like TypeScript, Python, and Go.",
            keyPoints = "• Use real programming constructs (loops, functions, classes, type checking) instead of YAML.\n• Native IDE autocomplete and refactoring for cloud components.\n• Interoperates with existing Terraform providers.",
            practicalUses = "• Software engineers who prefer writing real code to manage cloud resources instead of learning proprietary DSLs.",
            examples = "Example: const bucket = new aws.s3.Bucket('my-bucket'); in TypeScript.",
            referenceUrl = "https://www.pulumi.com"
        ),
        WordEntity(
            term = "Ansible",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source agentless configuration management and automation tool by Red Hat that configures servers over standard SSH using simple YAML playbooks.",
            keyPoints = "• Agentless: requires no special software installed on target servers, only SSH and Python.\n• Idempotent: running a playbook multiple times produces the same safe result.\n• Human-readable YAML playbooks describing desired server states.",
            practicalUses = "• Installing security updates and configuring Nginx across 100 Linux servers simultaneously.",
            examples = "Example: Running 'ansible-playbook -i hosts deploy-nginx.yml'.",
            referenceUrl = "https://www.ansible.com"
        ),
        WordEntity(
            term = "Docker",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The premier software platform for containerization, packaging an application and all its dependencies into an isolated container that runs identically on any computer.",
            keyPoints = "• Eliminates the 'it works on my machine' problem forever.\n• Lightweight virtualization sharing the host OS kernel (much faster than virtual machines).\n• Standardized Dockerfile blueprints and OCI container images.",
            practicalUses = "• Packaging web services, databases, and AI coding agent sandboxes for dependable execution.",
            examples = "Example: Running 'docker run -p 8080:80 nginx' launches a web server in 1 second.",
            referenceUrl = "https://www.docker.com"
        ),
        WordEntity(
            term = "Kubernetes",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Google's open-source container orchestration platform (K8s) that automates the deployment, scaling, health-checking, and management of containerized apps across server clusters.",
            keyPoints = "• Self-healing: restarts failed containers, reschedules crashed nodes.\n• Automated horizontal scaling based on CPU/memory usage.\n• Declarative YAML configuration of Pods, Deployments, and Services.",
            practicalUses = "• Running high-scale web apps, microservices, and AI inference fleets across hundreds of machines.",
            examples = "Example: Setting 'replicas: 10' in a Deployment to guarantee 10 instances of an API are always running.",
            referenceUrl = "https://kubernetes.io"
        ),
        WordEntity(
            term = "Helm",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The package manager for Kubernetes (like apt or npm for K8s) that packages complex collections of Kubernetes YAML manifests into versioned, reusable charts.",
            keyPoints = "• Helm Charts bundle deployments, services, configs, and ingresses into a single package.\n• Parameterized values.yaml allows customizing settings per environment (dev, staging, prod).\n• 1-command installation and rollbacks (helm install, helm rollback).",
            practicalUses = "• Installing complex software like Redis, Prometheus, or PostgreSQL into a Kubernetes cluster in seconds.",
            examples = "Example: Running 'helm install my-redis bitnami/redis' to deploy a production-ready Redis cluster.",
            referenceUrl = "https://helm.sh"
        ),
        WordEntity(
            term = "Vercel",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The leading frontend cloud platform (creators of Next.js) that automates web deployments with instant preview URLs on every git push and global edge routing.",
            keyPoints = "• Zero-configuration deployments for Next.js, React, Svelte, and Vue.\n• Instant automatic preview URLs for every pull request.\n• Global serverless and edge compute with automatic asset optimization.",
            practicalUses = "• Hosting modern web apps and AI chat frontends with worldwide sub-50ms performance.",
            examples = "Example: Pushing a git branch automatically deploys a live preview at my-branch.vercel.app.",
            referenceUrl = "https://vercel.com"
        ),
        WordEntity(
            term = "Netlify",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A pioneer in the Jamstack movement, Netlify is a developer platform for building, hosting, and deploying modern web applications and serverless backends.",
            keyPoints = "• Continuous deployment directly from GitHub and GitLab.\n• Serverless functions and edge middleware built-in.\n• Form handling, identity management, and deploy previews.",
            practicalUses = "• Deploying static websites, documentation portals, and serverless web tools.",
            examples = "Example: Dragging a build folder to Netlify's web UI to instantly launch a live website.",
            referenceUrl = "https://www.netlify.com"
        ),
        WordEntity(
            term = "Cloudflare Workers",
            category = "CI/CD & DevOps",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A serverless execution environment that runs JavaScript/Wasm code on Cloudflare's global edge network in hundreds of cities with 0ms cold starts.",
            keyPoints = "• Uses lightweight V8 isolates instead of heavy Docker containers, enabling 0ms cold starts.\n• Runs within milliseconds of 95% of the world's connected population.\n• Includes built-in key-value (KV), SQL (D1), and vector (Vectorize) databases.",
            practicalUses = "• Building ultra-fast API gateways, auth verifiers, and edge AI applications.",
            examples = "Example: An edge worker inspecting authorization headers and modifying responses before they reach the browser.",
            referenceUrl = "https://workers.cloudflare.com"
        ),

        // System design building blocks (13)
        WordEntity(
            term = "PostgreSQL",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The world's most advanced open-source relational database, renowned for rock-solid ACID reliability, powerful SQL query capabilities, and JSON/vector extensibility.",
            keyPoints = "• Robust ACID compliance guarantees data integrity.\n• Native JSONB support blends relational and document database paradigms.\n• Extensible architecture supporting extensions like pgvector and PostGIS.",
            practicalUses = "• The default primary database choice for modern web and mobile backends.",
            examples = "Example: Storing customer profiles, payment records, and vector embeddings in a single database.",
            referenceUrl = "https://www.postgresql.org"
        ),
        WordEntity(
            term = "MySQL",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The popular open-source relational database management system that powers a huge percentage of the internet, including WordPress and Wikipedia.",
            keyPoints = "• Fast read performance and straightforward replication topology.\n• InnoDB storage engine provides ACID transactions.\n• Managed by Oracle, with widespread hosting support across every cloud.",
            practicalUses = "• Web content management, e-commerce transactions, enterprise relational storage.",
            examples = "Example: WordPress storing blog posts and comments in MySQL tables.",
            referenceUrl = "https://www.mysql.com"
        ),
        WordEntity(
            term = "MongoDB",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A flexible, document-oriented NoSQL database that stores data in JSON-like BSON documents, allowing schemas to evolve naturally without migrations.",
            keyPoints = "• Schema-less flexibility: documents in the same collection can have different fields.\n• Powerful aggregation framework for filtering, grouping, and transforming data.\n• Native horizontal sharding for massive write scaling.",
            practicalUses = "• Content management, rapid MVP prototyping, user profile storage.",
            examples = "Example: Storing user preferences as a nested JSON document { id: 1, theme: 'dark', tags: ['ai', 'code'] }.",
            referenceUrl = "https://www.mongodb.com"
        ),
        WordEntity(
            term = "Redis",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An ultra-fast in-memory key-value data store used as a high-speed database, cache, message broker, and rate limiter with sub-millisecond response times.",
            keyPoints = "• Stores data directly in RAM for microsecond read/write operations.\n• Supports rich data structures: strings, hashes, lists, sets, sorted sets.\n• Configurable persistence to disk (RDB snapshots and AOF logs).",
            practicalUses = "• Session caching, API rate-limiting tokens, live leaderboard scores.",
            examples = "Example: Running SET key value EX 3600 to cache an API response for 1 hour.",
            referenceUrl = "https://redis.io"
        ),
        WordEntity(
            term = "Kafka",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Apache Kafka: a distributed event streaming platform designed to handle trillions of event messages a day with high throughput, fault tolerance, and durability.",
            keyPoints = "• Append-only distributed commit log architecture partitioned across brokers.\n• Publish-subscribe model decoupling producers from consumers.\n• Messages are retained on disk, allowing consumers to replay history.",
            practicalUses = "• Financial transaction processing, real-time analytics pipelines, activity tracking.",
            examples = "Example: Publishing user clickstream events to a 'user-clicks' topic read by analytics and recommendation workers.",
            referenceUrl = "https://kafka.apache.org"
        ),
        WordEntity(
            term = "RabbitMQ",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A widely deployed open-source message broker that reliably routes background messages between software applications using flexible queues and exchanges.",
            keyPoints = "• Implements AMQP protocol with advanced routing keys, topic exchanges, and dead-letter queues.\n• Guarantees message delivery with worker acknowledgments.\n• Lightweight and easy to run in microservices.",
            practicalUses = "• Asynchronous task queues (e.g. video processing, email sending) in Celery or background workers.",
            examples = "Example: When a user registers, web app drops a task into RabbitMQ to send the welcome email.",
            referenceUrl = "https://www.rabbitmq.com"
        ),
        WordEntity(
            term = "Amazon S3",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Simple Storage Service: AWS's massively scalable object storage service built to store and retrieve any amount of data (images, backups, datasets) from anywhere.",
            keyPoints = "• 99.999999999% (11 9's) data durability.\n• Stores data as objects inside buckets with unique keys.\n• Industry-standard S3 API adopted by MinIO, Cloudflare R2, and Wasabi.",
            practicalUses = "• Storing user avatar uploads, AI model checkpoints, database backups, static website assets.",
            examples = "Example: Uploading a 20 GB fine-tuned model checkpoint file to s3://my-ai-models/checkpoint.pt.",
            referenceUrl = "https://aws.amazon.com/s3"
        ),
        WordEntity(
            term = "Nginx",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A high-performance open-source HTTP web server, reverse proxy, and load balancer known for its asynchronous event-driven architecture and low memory footprint.",
            keyPoints = "• Event-driven non-blocking architecture handles 100,000+ concurrent connections easily.\n• Serves static files at blazing speeds and terminates SSL/TLS.\n• Universal reverse proxy in front of Node, Python, and Go apps.",
            practicalUses = "• Handling incoming internet traffic, compressing assets with Gzip/Brotli, and load balancing backends.",
            examples = "Example: Nginx listening on port 80/443 and proxy_pass to http://localhost:8080.",
            referenceUrl = "https://www.nginx.com"
        ),
        WordEntity(
            term = "Envoy",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A high-performance C++ distributed edge and service proxy designed for cloud-native microservices, serving as the default data plane for service meshes like Istio.",
            keyPoints = "• Extremely low memory footprint and high CPU efficiency written in modern C++.\n• L3/L4 and L7 architecture with advanced HTTP/2 and gRPC filtering.\n• Dynamic configuration via discovery services (xDS APIs) without server restarts.",
            practicalUses = "• Sidecar proxy managing microservice network communication in Kubernetes clusters.",
            examples = "Example: Envoy handling mTLS mutual encryption and automatic retries between two microservice pods.",
            referenceUrl = "https://www.envoyproxy.io"
        ),
        WordEntity(
            term = "Prometheus",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An open-source systems monitoring and alerting toolkit that scrapes and stores numeric metrics as time-series data with a powerful query language (PromQL).",
            keyPoints = "• Pull-based metric collection: periodically scrapes HTTP /metrics endpoints.\n• Multi-dimensional data model with key-value labels.\n• Standard monitoring system graduated by the Cloud Native Computing Foundation (CNCF).",
            practicalUses = "• Monitoring CPU usage, API error rates, and memory consumption across server clusters.",
            examples = "Example: Querying 'rate(http_requests_total[5m])' in PromQL to calculate current requests per second.",
            referenceUrl = "https://prometheus.io"
        ),
        WordEntity(
            term = "Grafana",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "The premier open-source interactive analytics and visualization web platform that turns raw metrics and logs from Prometheus, Datadog, or SQL into beautiful real-time dashboards.",
            keyPoints = "• Connects to dozens of data sources (Prometheus, InfluxDB, PostgreSQL, Loki).\n• Highly customizable graphical dashboards with charts, heatmaps, and gauges.\n• Built-in alerting to Slack, PagerDuty, or Discord when metrics cross thresholds.",
            practicalUses = "• Creating wall monitors and engineering dashboards to track server health and business KPIs.",
            examples = "Example: A real-time Grafana dashboard visualizing live API latency and 5xx error spikes.",
            referenceUrl = "https://grafana.com"
        ),
        WordEntity(
            term = "Datadog",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A leading cloud observability and security monitoring platform that provides unified infrastructure metrics, distributed APM application tracing, and log management.",
            keyPoints = "• Full-stack visibility: combines metrics, logs, traces, and security scans in one SaaS UI.\n• AI-driven anomaly detection spots abnormal latency spikes before users complain.\n• Over 700 turn-key cloud and database integrations.",
            practicalUses = "• Enterprise production monitoring and troubleshooting complex distributed systems.",
            examples = "Example: Drilling down from a high 500 error rate directly to the exact SQL query that timed out.",
            referenceUrl = "https://www.datadoghq.com"
        ),
        WordEntity(
            term = "Sentry",
            category = "System Design",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An application performance and error tracking platform that automatically catches software crashes, unhandled exceptions, and slow transactions in real time.",
            keyPoints = "• Captures complete stack traces, breadcrumb user actions, and device metadata when a crash occurs.\n• Maps minified production code back to original source code using source maps.\n• Native SDKs for Android, iOS, React, Python, and Go.",
            practicalUses = "• Getting instant alerts when a user experiences a crash in your mobile or web app.",
            examples = "Example: Sentry catching an unhandled NullPointerException on Android and alerting developers in Slack with the exact line number.",
            referenceUrl = "https://sentry.io"
        ),

        // Harness building blocks (14)
        WordEntity(
            term = "AGENTS.md",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A standardized markdown instruction file placed in a repository's root to give all AI coding agents shared rules, architectural guidelines, and project conventions.",
            keyPoints = "• Universal format readable by multiple coding agents (Cursor, Claude Code, Cline, Devin).\n• Specifies build commands, testing procedures, styling guidelines, and constraints.\n• Prevents agents from making repetitive architectural mistakes.",
            practicalUses = "• Setting project rules like 'Always use Kotlin Coroutines instead of RxJava'.",
            examples = "Example: Stating in AGENTS.md: 'Never edit debug.keystore; always run compile_applet after edits'.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "CLAUDE.md",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A project instructions and memory file specifically tailored for Anthropic's Claude Code agent, read automatically on startup to guide its tool use and coding style.",
            keyPoints = "• Read by Claude Code at the start of every session.\n• Documents common commands (build, test, lint) and codebase architecture.\n• Preserves project memory and developer preferences across terminal sessions.",
            practicalUses = "• Telling Claude Code: 'Build command is gradle assembleDebug; prefer Jetpack Compose'.",
            examples = "Example: Adding frequent test commands and architecture diagrams directly into CLAUDE.md.",
            referenceUrl = "https://docs.anthropic.com/en/docs/agents-and-tools/claude-code"
        ),
        WordEntity(
            term = "Skills",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Modular, reusable folders of specialized instructions, domain references, and scripts that an AI agent loads dynamically on-demand when tackling specific tasks.",
            keyPoints = "• Contains a SKILL.md defining rules and references.\n• Loaded just-in-time only when a task matches, conserving context window space.\n• Encapsulates deep expertise (e.g. Room database, app icon generation, OAuth flows).",
            practicalUses = "• Giving an agent specialized knowledge for Android Jetpack Compose or Firebase integrations.",
            examples = "Example: Reading /skills/room-database-integration/SKILL.md before writing Room database code.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Hooks",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Automated scripts or event triggers that execute at designated lifecycle points in an agent's loop (e.g., before tool execution, after code generation, or on error).",
            keyPoints = "• Pre-tool hooks can sanitize inputs or block unauthorized dangerous commands.\n• Post-tool hooks can run linters or unit tests automatically after every file edit.\n• Injects deterministic programmatic control into autonomous agent loops.",
            practicalUses = "• Automatically running prettier/ktlint whenever an agent saves a source file.",
            examples = "Example: A post-edit hook that runs 'npm test' and feeds any error output back to the agent.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Subagents",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Isolated, specialized helper agents spawned by a primary orchestrator agent to handle specific subtasks in their own clean context window without cluttering the main conversation.",
            keyPoints = "• Possess their own isolated prompt context, preventing context rot and confusion.\n• Return summarized outputs or structured results back to the primary agent.\n• Can execute in parallel to save wall-clock time.",
            practicalUses = "• Delegating a deep documentation search to a research subagent while the main agent continues coding.",
            examples = "Example: Main agent spawning 'ResearchSubagent' to find how a library's API works.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Slash commands",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Reusable prompt shortcuts prefixed with a forward slash (like /test, /review, /commit) that invoke standardized instructions or agent routines quickly.",
            keyPoints = "• Standardizes frequent developer workflows into single keystrokes.\n• Can accept arguments (e.g. /test auth_flow).\n• Expands into detailed system instructions under the hood.",
            practicalUses = "• Quick code reviews, triggering test runs, or generating standardized commit messages.",
            examples = "Example: Typing '/commit' to have the agent review git diff and craft an atomic commit.",
            referenceUrl = "https://cursor.com"
        ),
        WordEntity(
            term = "Plan mode",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "An agent operational mode where the agent thoroughly explores the codebase, analyzes trade-offs, and presents a structured plan for human approval before modifying files.",
            keyPoints = "• Read-only exploration phase: inspects files without making modifications.\n• Produces an architectural plan detailing which files will change and why.\n• Execution only begins after the user approves the proposed strategy.",
            practicalUses = "• Large architectural migrations, complex refactoring, and high-risk database alterations.",
            examples = "Example: Switching to Plan Mode to outline an OAuth migration before writing any code.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "Permissions",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Access control rules and policies that specify exactly what an AI agent is permitted to do automatically versus what requires explicit human confirmation.",
            keyPoints = "• Restricts dangerous terminal commands (e.g. rm -rf, git push, droplet deletion).\n• Defines read-only versus read-write tool policies.\n• Essential safeguard for maintaining human control over autonomous systems.",
            practicalUses = "• Allowing safe commands (ls, cat, git status) while requiring approval for destructive actions.",
            examples = "Example: Cline prompting 'Do you want to run: npm install -g firebase-tools? [Allow / Deny]'.",
            referenceUrl = "https://github.com/cline/cline"
        ),
        WordEntity(
            term = "Sandboxing",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Executing an agent's code, scripts, and terminal commands inside an isolated environment (like a Docker container or microVM) so errors cannot damage the host machine.",
            keyPoints = "• Prevents accidental file deletion or malicious script execution on the developer's laptop.\n• Ephemeral containers can be reset cleanly if an agent breaks the system.\n• Isolates network access and sensitive environment secrets.",
            practicalUses = "• Running untested AI-generated Python or shell scripts safely.",
            examples = "Example: OpenHands executing bash commands inside an isolated Docker container rather than your Mac.",
            referenceUrl = "https://en.wikipedia.org/wiki/Sandbox_(computer_security)"
        ),
        WordEntity(
            term = "Git worktrees",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A Git feature allowing multiple linked working directories attached to the same repository simultaneously, enabling agents to work on separate branches without disturbing your active code.",
            keyPoints = "• Eliminates the need to switch branches or stash unfinished local work.\n• Multiple agents can work on different git branches in parallel on the same machine.\n• Shares the underlying .git database, saving disk space.",
            practicalUses = "• Having an AI agent fix an urgent bug on a worktree branch while you continue writing a feature on main.",
            examples = "Example: 'git worktree add ../bugfix-branch' allows an agent to edit in ../bugfix-branch independently.",
            referenceUrl = "https://git-scm.com/docs/git-worktree"
        ),
        WordEntity(
            term = "Checkpoints",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Saved historical snapshots of the codebase and agent state taken before each change, allowing you to instantly rewind or roll back if the agent makes a mistake.",
            keyPoints = "• Enables risk-free experimentation: undo an entire agent attempt with 1 click.\n• Tracks file diffs and conversation context at each decision point.\n• Essential UX feature in modern coding agent harnesses.",
            practicalUses = "• Rolling back an agent's attempt when it took an unwanted refactoring approach.",
            examples = "Example: Clicking 'Revert to checkpoint' in Cursor to restore all files to their state before the agent prompt.",
            referenceUrl = "https://cursor.com"
        ),
        WordEntity(
            term = "Memory files",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Persistent markdown or JSON files where an agent writes notes, architectural discoveries, and user preferences so it remembers context across future conversations.",
            keyPoints = "• Overcomes context window limits by storing persistent long-term notes on disk.\n• Agent updates the file when it learns project quirks or user instructions.\n• Read automatically on subsequent runs.",
            practicalUses = "• Remembering: 'This user prefers MVVM and hates third-party utility libraries'.",
            examples = "Example: Writing learnings into .cursorrules or .ai/memory.md so future chat sessions remember.",
            referenceUrl = "https://docs.anthropic.com"
        ),
        WordEntity(
            term = "MCP servers",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "Lightweight programs implementing the Model Context Protocol that expose tools, resources, and prompt templates to AI models through standard JSON-RPC.",
            keyPoints = "• Decouples AI models from proprietary integrations: write an MCP server once, use it in any client.\n• Standardized transports: stdio (local subprocess) and SSE (Server-Sent Events over HTTP).\n• Exposes tools (functions), resources (file/data content), and prompts.",
            practicalUses = "• Exposing custom corporate databases, issue trackers, and internal APIs to Claude or Cursor.",
            examples = "Example: Running a local Postgres MCP server over stdio to query employee databases.",
            referenceUrl = "https://modelcontextprotocol.io"
        ),
        WordEntity(
            term = "Tool search",
            category = "Harness Building Blocks",
            part = "Part 2: Harnesses and tools",
            humanMeaning = "A technique where an agent dynamically searches and loads tool definitions only when relevant, instead of dumping hundreds of tool schemas into the prompt context at once.",
            keyPoints = "• Solves context window saturation when an agent has access to hundreds of potential tools.\n• Tool definitions are retrieved on-demand using keyword or semantic search.\n• Reduces token cost and prevents tool selection confusion in models.",
            practicalUses = "• Agent environments with 500+ microservice APIs where loading all schemas would exhaust the context.",
            examples = "Example: Agent calls search_tools('github') to load GitHub tools only when the user mentions an issue.",
            referenceUrl = "https://docs.anthropic.com"
        )
    )
}
