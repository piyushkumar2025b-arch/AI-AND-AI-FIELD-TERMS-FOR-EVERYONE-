package com.example.data.local

object DefaultWordsPart1Advanced {
    fun getTerms(): List<WordEntity> = listOf(
        // DNS terms (7)
        WordEntity(
            term = "Domain name",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "An easy-to-remember human-friendly string address (like wikipedia.org) used to access websites without having to remember numerical IP addresses.",
            keyPoints = "• Managed hierarchically under Top-Level Domains (like .org, .com).\n• Registered through accredited registrars (GoDaddy, Namecheap, Cloudflare).\n• Translated by DNS into numerical IP addresses.",
            practicalUses = "• Branding websites, configuring custom company emails, securing SSL certificates.",
            examples = "Example: 'openrouter.ai' or 'github.com'.",
            referenceUrl = "https://en.wikipedia.org/wiki/Domain_name"
        ),
        WordEntity(
            term = "Nameserver",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A specialized server in the DNS ecosystem that stores and serves the official authoritative DNS records for a domain name.",
            keyPoints = "• Answers DNS questions about which IP corresponds to a specific domain.\n• Authoritative nameservers hold the definitive source of truth.\n• Typically deployed in redundant pairs (e.g. ns1.cloudflare.com, ns2.cloudflare.com).",
            practicalUses = "• Directing global web traffic to your cloud host or email provider.",
            examples = "Example: ns1.google.com directing visitors to Google services.",
            referenceUrl = "https://en.wikipedia.org/wiki/Name_server"
        ),
        WordEntity(
            term = "DNS resolver",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The client-side or ISP server that acts as a researcher, querying root servers, TLD servers, and authoritative nameservers on your behalf to find an IP address.",
            keyPoints = "• First stop when your computer tries to look up an address.\n• Recursively traces the DNS hierarchy until the final IP is found.\n• Caches results locally to speed up future lookups for other users.",
            practicalUses = "• Translating web addresses rapidly for millions of residential subscribers.",
            examples = "Example: 1.1.1.1 (Cloudflare recursive resolver), 8.8.8.8 (Google recursive resolver).",
            referenceUrl = "https://en.wikipedia.org/wiki/Domain_Name_System#Recursive_and_caching_name_server"
        ),
        WordEntity(
            term = "DNS record types (A/AAAA/CNAME/MX/TXT/NS)",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Specific types of entries in a domain's DNS database: A maps to IPv4, AAAA maps to IPv6, CNAME creates aliases, MX directs email, TXT stores metadata/verification, NS delegates nameservers.",
            keyPoints = "• A: points domain to an IPv4 address (e.g. 192.0.2.1).\n• AAAA: points domain to an IPv6 address.\n• CNAME: canonical alias pointing one domain to another.\n• MX: mail exchanger priority list for receiving email.\n• TXT: verification tokens (Google Search Console, SPF, DKIM).\n• NS: authoritative nameserver delegations.",
            practicalUses = "• Setting up web hosting, email inboxes, subdomains, and domain ownership verification.",
            examples = "Example: A record: api.site.com -> 104.21.5.12.",
            referenceUrl = "https://en.wikipedia.org/wiki/List_of_DNS_record_types"
        ),
        WordEntity(
            term = "TLD",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Top-Level Domain: the rightmost suffix of a domain name (like .com, .org, .ai, .io, .gov) overseen globally by ICANN.",
            keyPoints = "• Generic TLDs (gTLDs): .com, .net, .org, .ai.\n• Country-code TLDs (ccTLDs): .uk, .de, .jp, .in.\n• Handled by dedicated registry operators (e.g., Verisign runs .com).",
            practicalUses = "• Categorizing websites by purpose or geographical jurisdiction.",
            examples = "Example: The '.ai' TLD is officially the country code for Anguilla, popularized by artificial intelligence companies.",
            referenceUrl = "https://en.wikipedia.org/wiki/Top-level_domain"
        ),
        WordEntity(
            term = "DNS caching",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Temporarily storing DNS lookup answers in memory (in your browser, operating system, or local router) so repeat requests don't need to query the internet again.",
            keyPoints = "• Governed by the TTL (Time to Live) value set on the DNS record.\n• Drastically cuts webpage load times by eliminating DNS lookup latency.\n• Can cause a delay when changing hosting providers until cache expires.",
            practicalUses = "• Accelerating daily web browsing and minimizing DNS root server strain.",
            examples = "Example: Flushing your OS DNS cache with 'sudo dscacheutil -flushcache' on macOS or 'ipconfig /flushdns' on Windows.",
            referenceUrl = "https://en.wikipedia.org/wiki/Domain_Name_System#DNS_caches"
        ),
        WordEntity(
            term = "DNS over HTTPS (DoH)",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A privacy-protecting security protocol that encrypts DNS queries inside standard HTTPS traffic, preventing ISPs and Wi-Fi snoopers from spying on which sites you visit.",
            keyPoints = "• Conceals DNS queries inside standard port 443 HTTPS streams.\n• Prevents DNS spoofing, eavesdropping, and ISP manipulation.\n• Supported natively by modern browsers (Chrome, Firefox) and mobile operating systems.",
            practicalUses = "• Privacy on public Wi-Fi networks and evading ISP tracking.",
            examples = "Example: Enabling Cloudflare 1.1.1.1 DoH in Firefox settings.",
            referenceUrl = "https://en.wikipedia.org/wiki/DNS_over_HTTPS"
        ),

        // Network security (12)
        WordEntity(
            term = "Certificate",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A cryptographically signed digital passport that binds a public encryption key to an organization's identity or domain name, verifying authenticity for HTTPS.",
            keyPoints = "• Contains public key, expiration date, issuing authority, and domain name (SAN).\n• Follows the X.509 standard.\n• Verified by browsers using a built-in root trust store.",
            practicalUses = "• Enabling HTTPS encryption and browser trust for secure transactions.",
            examples = "Example: Let's Encrypt automated 90-day SSL/TLS certificates.",
            referenceUrl = "https://en.wikipedia.org/wiki/Public_key_certificate"
        ),
        WordEntity(
            term = "Certificate authority (CA)",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A trusted third-party organization (like Let's Encrypt, DigiCert, Cloudflare) that cryptographically verifies ownership and signs digital certificates.",
            keyPoints = "• Operating systems and browsers maintain a pre-installed trust store of trusted Root CAs.\n• Issues intermediate certificates to minimize root key exposure.\n• Can revoke compromised certificates via CRL or OCSP.",
            practicalUses = "• Establishing the foundational chain of trust for internet commerce.",
            examples = "Example: Let's Encrypt, DigiCert, Sectigo, Google Trust Services.",
            referenceUrl = "https://en.wikipedia.org/wiki/Certificate_authority"
        ),
        WordEntity(
            term = "Public / private key",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Asymmetric cryptography pair: the public key is shared with the world to encrypt messages or verify signatures, while the private key is kept secret to decrypt or sign.",
            keyPoints = "• Math problem where one key reverses what the other key does (RSA, ECC, Ed25519).\n• Public key can be broadcast everywhere safely.\n• Never reveal the private key under any circumstances.",
            practicalUses = "• SSH server logins, TLS handshakes, Bitcoin/crypto wallets, digital signatures.",
            examples = "Example: Adding your id_ed25519.pub to GitHub to push code securely without passwords.",
            referenceUrl = "https://en.wikipedia.org/wiki/Public-key_cryptography"
        ),
        WordEntity(
            term = "mTLS",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Mutual TLS: two-way cryptographic authentication where both client and server verify each other's certificates before communicating, ensuring zero untrusted access.",
            keyPoints = "• Both server and client present digital X.509 certificates.\n• Prevents unauthorized API clients even if they discover an endpoint URL.\n• Core building block of Zero Trust service meshes (Istio, Linkerd).",
            practicalUses = "• Securing internal microservice-to-microservice communication in cloud platforms.",
            examples = "Example: Kubernetes microservices requiring mutual certificate verification for all internal RPCs.",
            referenceUrl = "https://en.wikipedia.org/wiki/Mutual_authentication#mTLS"
        ),
        WordEntity(
            term = "SSH",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Secure Shell: a cryptographic network protocol operating on port 22 used to log into and securely operate command-line terminals on remote Linux servers.",
            keyPoints = "• Encrypted terminal sessions replacing insecure telnet.\n• Authenticates using public-key cryptography or passwords.\n• Supports port forwarding, tunneling, and file transfers (SFTP/SCP).",
            practicalUses = "• Managing cloud Linux instances on AWS, GCP, DigitalOcean, or private home labs.",
            examples = "Example: ssh -i id_rsa ubuntu@198.51.100.2",
            referenceUrl = "https://en.wikipedia.org/wiki/Secure_Shell"
        ),
        WordEntity(
            term = "Zero trust",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A security philosophy based on 'never trust, always verify': every user, device, and API request must be authenticated, authorized, and encrypted even inside the private network.",
            keyPoints = "• Abandons the legacy 'castle-and-moat' perimeter security model.\n• Enforces continuous identity validation and least-privilege access.\n• Protects against lateral movement if an attacker breaches one server.",
            practicalUses = "• Enterprise identity access (Google BeyondCorp, Cloudflare Zero Trust, Tailscale).",
            examples = "Example: An employee must pass 2FA and device health checks for every single internal app, not just a one-time VPN login.",
            referenceUrl = "https://en.wikipedia.org/wiki/Zero_trust_security_model"
        ),
        WordEntity(
            term = "DDoS",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Distributed Denial of Service: a cyberattack where thousands of compromised computers (a botnet) flood a server with overwhelming junk traffic to knock it offline.",
            keyPoints = "• Can target Layer 3/4 (SYN flood, UDP amplification) or Layer 7 (HTTP floods).\n• Traffic volumes can reach multiple Terabits per second (Tbps).\n• Mitigated using edge CDN scrubbers and anycast traffic dispersal.",
            practicalUses = "• Defending critical public infrastructure, games, and web apps with Cloudflare or AWS Shield.",
            examples = "Example: A botnet blasting 50 million HTTP GET requests a second at a target website until its CPU freezes.",
            referenceUrl = "https://en.wikipedia.org/wiki/Denial-of-service_attack"
        ),
        WordEntity(
            term = "WAF",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Web Application Firewall: a security filter placed in front of websites to inspect HTTP traffic and block SQL injections, cross-site scripting (XSS), and malicious bots.",
            keyPoints = "• Operates at Layer 7 (Application Layer).\n• Analyzes request bodies, headers, and query strings against OWASP Top 10 rules.\n• Can challenge suspicious traffic with CAPTCHAs or rate-limiting.",
            practicalUses = "• Protecting web applications and REST APIs from automated vulnerability exploit scripts.",
            examples = "Example: AWS WAF or Cloudflare WAF instantly blocking a URL containing `SELECT * FROM users WHERE id=1; DROP TABLE users;`.",
            referenceUrl = "https://en.wikipedia.org/wiki/Web_application_firewall"
        ),
        WordEntity(
            term = "IDS / IPS",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Intrusion Detection / Prevention Systems: security systems that passively monitor network traffic for suspicious activity (IDS) or actively drop and block attacks in real time (IPS).",
            keyPoints = "• IDS alerts administrators of anomalies; IPS actively terminates malicious sessions.\n• Uses signature matching (known malware patterns) and anomaly detection.\n• Deployed at network perimeters or on individual host servers.",
            practicalUses = "• Snort, Suricata, or Zeek inspecting corporate network trunks for lateral malware spread.",
            examples = "Example: Snort IPS detecting an exploit payload and immediately injecting a TCP RST packet to kill the hacker's connection.",
            referenceUrl = "https://en.wikipedia.org/wiki/Intrusion_detection_system"
        ),
        WordEntity(
            term = "Encryption in transit",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Encrypting data while it is traveling across a network between devices, so anyone tapping the physical wire or Wi-Fi sees only unreadable scrambled ciphertext.",
            keyPoints = "• Achieved using TLS, HTTPS, SSH, IPsec, or WireGuard.\n• Complements 'encryption at rest' (which protects stored hard drive files).\n• Mandatory for SOC 2, HIPAA, and GDPR compliance.",
            practicalUses = "• Transmitting passwords, credit card numbers, and private AI chat messages safely.",
            examples = "Example: Sending data to OpenRouter over HTTPS encrypts your prompt before it leaves your phone.",
            referenceUrl = "https://en.wikipedia.org/wiki/Encryption"
        ),
        WordEntity(
            term = "Man-in-the-middle attack",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A cyberattack where a hacker secretly intercepts and relays communications between two parties who believe they are talking directly and privately to each other.",
            keyPoints = "• Attacker can eavesdrop on private messages or alter contents on the fly.\n• Often executed via ARP poisoning or fake rogue Wi-Fi access points.\n• Prevented by TLS certificate validation and certificate pinning.",
            practicalUses = "• Understanding why HTTPS certificate warnings should never be ignored.",
            examples = "Example: A hacker on airport Wi-Fi spoofing a bank website to capture plaintext credentials.",
            referenceUrl = "https://en.wikipedia.org/wiki/Man-in-the-middle_attack"
        ),
        WordEntity(
            term = "Port scanning",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Probing a server by sending requests to a range of port numbers to discover which services are active, open, and potentially vulnerable to attack.",
            keyPoints = "• Fundamental reconnaissance step in ethical penetration testing and hacking.\n• Nmap is the industry-standard port scanning tool.\n• Identifies running operating systems, web server versions, and listening services.",
            practicalUses = "• Auditing corporate network firewalls to verify no unauthorized ports are exposed to the internet.",
            examples = "Example: Running 'nmap -p 1-1000 192.168.1.1' to find open management ports.",
            referenceUrl = "https://en.wikipedia.org/wiki/Port_scanner"
        ),

        // Cloud and infrastructure networking (17)
        WordEntity(
            term = "Reverse proxy",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A server positioned in front of backend web servers that receives incoming internet traffic, handles SSL termination and caching, and routes requests to the right internal app.",
            keyPoints = "• Masks internal server IP addresses and architecture from the public.\n• Offloads SSL/TLS decryption so backend app servers run faster.\n• Centralizes rate limiting, authentication, and routing.",
            practicalUses = "• Nginx, Traefik, or Caddy sitting in front of Node.js, Python, or Go microservices.",
            examples = "Example: Nginx listening on port 443 and proxying traffic to localhost:3000.",
            referenceUrl = "https://en.wikipedia.org/wiki/Reverse_proxy"
        ),
        WordEntity(
            term = "Forward proxy",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A server positioned in front of client devices that intercepts outbound requests to the internet, enforcing security policies, logging, or caching.",
            keyPoints = "• Acts on behalf of clients (users) reaching out to external web servers.\n• Hides internal client IP addresses from destination websites.\n• Enforces corporate web browsing rules.",
            practicalUses = "• Corporate office proxies preventing employees from visiting phishing websites.",
            examples = "Example: Configuring Squid proxy on school computers to block inappropriate websites.",
            referenceUrl = "https://en.wikipedia.org/wiki/Proxy_server#Forward_proxies"
        ),
        WordEntity(
            term = "L4 vs. L7 load balancing",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "L4 load balancers route traffic purely based on IP and port (Layer 4) with raw blazing speed; L7 load balancers inspect HTTP URLs, headers, and cookies (Layer 7) for smart routing.",
            keyPoints = "• L4 (Transport): extremely high throughput, low CPU, agnostic to protocol payload (e.g. AWS NLB, HAProxy TCP mode).\n• L7 (Application): can route /api to backend A and /images to backend B (e.g. AWS ALB, Nginx).\n• L7 handles SSL termination and cookie-based sticky sessions.",
            practicalUses = "• Choosing between raw TCP gaming throughput (L4) vs. intelligent microservice routing (L7).",
            examples = "Example: Routing all /v1/ai requests to a dedicated GPU cluster using an L7 load balancer.",
            referenceUrl = "https://en.wikipedia.org/wiki/Load_balancing_(computing)#Layer_4_vs_Layer_7"
        ),
        WordEntity(
            term = "Round robin",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A straightforward load balancing algorithm that takes turns sending incoming requests sequentially to each server in a pool one after another.",
            keyPoints = "• Simple, predictable, and requires no state tracking.\n• Weighted Round Robin assigns more requests to powerful servers with more RAM/CPU.\n• Assumes all requests take roughly equal processing time.",
            practicalUses = "• Basic web server load balancing across identical EC2 instances.",
            examples = "Example: Request 1 -> Server A, Request 2 -> Server B, Request 3 -> Server C, Request 4 -> Server A.",
            referenceUrl = "https://en.wikipedia.org/wiki/Round-robin_DNS"
        ),
        WordEntity(
            term = "Sticky sessions",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A load balancer feature (session affinity) that binds a specific user's requests to the exact same backend server for the duration of their visit.",
            keyPoints = "• Usually tracked via a custom HTTP cookie set by the load balancer.\n• Helpful for legacy stateful apps that keep user session data in server RAM.\n• Inhibits perfect load distribution if one user makes thousands of requests.",
            practicalUses = "• Legacy eCommerce shopping carts stored in local server memory.",
            examples = "Example: User logging in gets assigned cookie SERVERID=srv02, so all their clicks stay on srv02.",
            referenceUrl = "https://en.wikipedia.org/wiki/Load_balancing_(computing)#Persistence"
        ),
        WordEntity(
            term = "Health check",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A regular automated ping or HTTP query sent by a load balancer or Kubernetes to verify that a backend server is alive, healthy, and ready to accept traffic.",
            keyPoints = "• Typically hits an endpoint like GET /health or GET /healthz every 5 to 10 seconds.\n• If a server returns 500 or times out 3 times consecutively, it is removed from the active pool.\n• Automatically reinstates servers once they pass consecutive health probes.",
            practicalUses = "• Zero-downtime rolling deployments and self-healing infrastructure.",
            examples = "Example: AWS ALB checking GET /healthz; if status is 200 OK, server receives traffic.",
            referenceUrl = "https://en.wikipedia.org/wiki/Heartbeat_(computing)"
        ),
        WordEntity(
            term = "Ingress",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Incoming network traffic entering a cloud network, cluster, or data center from the outside world.",
            keyPoints = "• Ingress controllers (in Kubernetes) route external traffic to internal cluster pods.\n• Ingress rules define SSL termination, path-based routing, and hostnames.\n• Cloud providers rarely charge fees for ingress bandwidth.",
            practicalUses = "• Exposing a web application or public API to users on the internet.",
            examples = "Example: Kubernetes Ingress resource mapping https://app.example.com to service 'web-frontend:80'.",
            referenceUrl = "https://en.wikipedia.org/wiki/Ingress_(Kubernetes)"
        ),
        WordEntity(
            term = "Egress",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Outgoing network traffic leaving a private cloud network, VPC, or data center out to the public internet or external services.",
            keyPoints = "• Cloud providers (AWS, GCP, Azure) heavily bill for outbound egress data transfer.\n• Egress firewalls restrict internal servers from talking to unauthorized external IPs.\n• Mitigates data exfiltration if a server is compromised.",
            practicalUses = "• Budgeting cloud bandwidth costs and locking down data privacy compliance.",
            examples = "Example: An internal database server prevented by egress rules from making outbound calls to the open web.",
            referenceUrl = "https://en.wikipedia.org/wiki/Egress_filtering"
        ),
        WordEntity(
            term = "VPC",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Virtual Private Cloud: an isolated private virtual network provisioned within a public cloud provider where you launch and securely run your servers and databases.",
            keyPoints = "• Complete control over IP address ranges, subnets, route tables, and gateways.\n• Isolated from other cloud tenants.\n• Can be connected to on-premises office networks via VPN or AWS Direct Connect.",
            practicalUses = "• Housing company cloud production infrastructure in AWS, GCP, or Azure.",
            examples = "Example: AWS VPC configured with 10.0.0.0/16 containing public and private subnets.",
            referenceUrl = "https://en.wikipedia.org/wiki/Virtual_private_cloud"
        ),
        WordEntity(
            term = "Security group",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A virtual stateful firewall that controls inbound and outbound traffic at the individual cloud virtual machine or database instance level.",
            keyPoints = "• Deny-all by default: you must explicitly create allow rules.\n• Stateful: if inbound traffic is allowed, outbound response is permitted automatically.\n• Rules specify protocol (TCP/UDP), port range, and source CIDR/Security Group.",
            practicalUses = "• Allowing port 22 SSH only from your home IP address; port 443 open to 0.0.0.0/0.",
            examples = "Example: AWS EC2 Security Group allowing TCP port 5432 only from the web server security group.",
            referenceUrl = "https://en.wikipedia.org/wiki/Amazon_Elastic_Compute_Cloud#Security_groups"
        ),
        WordEntity(
            term = "Public vs. private subnet",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A public subnet has a direct route to an Internet Gateway allowing direct public internet access; a private subnet has no public IPs and reaches the web only via a NAT gateway.",
            keyPoints = "• Public subnets host customer-facing load balancers and web servers.\n• Private subnets host critical databases, redis caches, and backend services.\n• Isolating sensitive data in private subnets is standard cloud security architecture.",
            practicalUses = "• Protecting PostgreSQL databases from direct internet exposure.",
            examples = "Example: Web frontend in public subnet 10.0.1.0/24; MySQL in private subnet 10.0.2.0/24.",
            referenceUrl = "https://en.wikipedia.org/wiki/Virtual_private_cloud"
        ),
        WordEntity(
            term = "NAT gateway",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A managed cloud networking service that lets servers in a private subnet initiate outbound connections to the internet (for software updates) while blocking inbound connections from the outside.",
            keyPoints = "• One-way outbound internet connectivity for private servers.\n• Translates private IP traffic through an assigned Elastic IP address.\n• Fully managed and highly available in modern clouds (e.g. AWS NAT Gateway).",
            practicalUses = "• Enabling private backend servers to download Linux security patches and API packages.",
            examples = "Example: An EC2 instance running 'apt update' in a private subnet routes out via the NAT Gateway.",
            referenceUrl = "https://en.wikipedia.org/wiki/Network_address_translation#One-to-many_NAT"
        ),
        WordEntity(
            term = "Bastion host",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A hardened 'jump box' server placed in a public subnet that administrators SSH into as a secure gateway before hopping into servers located in private subnets.",
            keyPoints = "• Single audited entry point into private network infrastructure.\n• Heavily monitored, locked down, and patched.\n• Reduces attack surface compared to giving every server a public IP address.",
            practicalUses = "• Secure remote administration of cloud databases and backend nodes.",
            examples = "Example: SSHing to bastion.company.com, then from there running ssh 10.0.2.15 to reach an internal database.",
            referenceUrl = "https://en.wikipedia.org/wiki/Bastion_host"
        ),
        WordEntity(
            term = "Service mesh",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A dedicated infrastructure layer (like Istio or Linkerd) using sidecar proxies to transparently handle service-to-service encryption, routing, tracing, and retries in microservices.",
            keyPoints = "• Injects lightweight sidecar proxies (Envoy) next to every application container.\n• Automates mutual TLS (mTLS) encryption across hundreds of microservices.\n• Provides granular telemetry, circuit breaking, and canary traffic splitting.",
            practicalUses = "• Managing complex Kubernetes clusters running hundreds of microservices.",
            examples = "Example: Istio automatically encrypting all internal pod traffic with mTLS certificates.",
            referenceUrl = "https://en.wikipedia.org/wiki/Service_mesh"
        ),
        WordEntity(
            term = "Port forwarding",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A router setting that takes incoming traffic arriving on a specific external port and redirects it straight to a designated device and port inside the local private network.",
            keyPoints = "• Punches a controlled hole through the router's NAT firewall.\n• Translates WAN port to a specific LAN IP:port.\n• Essential when hosting servers or games at home without a public IP per device.",
            practicalUses = "• Hosting a Minecraft server, Plex media server, or self-hosted web app at home.",
            examples = "Example: Forwarding incoming port 8080 on router to 192.168.1.100:80.",
            referenceUrl = "https://en.wikipedia.org/wiki/Port_forwarding"
        ),
        WordEntity(
            term = "Tunneling",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Encapsulating one network protocol payload inside the packets of another protocol so it can cross incompatible or untrusted intermediate networks securely.",
            keyPoints = "• Wraps original packets inside new carrier headers (e.g. GRE, SSH tunnels, WireGuard).\n• Can bypass restrictive corporate firewalls by tunneling traffic over port 443.\n• Unpacked and delivered by the endpoint tunnel gateway.",
            practicalUses = "• Creating VPN tunnels, exposing localhost servers to the public web (ngrok, Cloudflare Tunnel).",
            examples = "Example: 'ssh -L 8080:localhost:5432 user@remote' tunnels remote database port to local port.",
            referenceUrl = "https://en.wikipedia.org/wiki/Tunneling_protocol"
        ),
        WordEntity(
            term = "Edge network",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A globally distributed mesh of servers stationed in hundreds of major cities close to end users, running compute code and caching data right at the edge of the internet.",
            keyPoints = "• Drastically cuts latency by executing code near the client instead of traveling to a central data center.\n• Powers serverless edge functions (Cloudflare Workers, Vercel Edge Runtime).\n• Handles DDoS mitigation and asset caching.",
            practicalUses = "• A/B testing, authentication token verification, and content delivery under 10ms worldwide.",
            examples = "Example: Running an edge worker in Singapore to personalize web pages for Asian visitors instantly.",
            referenceUrl = "https://en.wikipedia.org/wiki/Edge_computing"
        ),

        // Other protocols (5)
        WordEntity(
            term = "FTP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "File Transfer Protocol: a legacy 1971 client-server protocol used to transfer files across networks, largely deprecated today because it sends passwords in plaintext.",
            keyPoints = "• Operates over ports 20 (data) and 21 (commands).\n• Unencrypted by default: credentials and data are visible to packet sniffers.\n• Replaced by SFTP and FTPS.",
            practicalUses = "• Legacy website file uploads on older shared hosting providers.",
            examples = "Example: Using FileZilla client over port 21 to upload HTML files.",
            referenceUrl = "https://en.wikipedia.org/wiki/File_Transfer_Protocol"
        ),
        WordEntity(
            term = "SFTP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "SSH File Transfer Protocol: a modern, fully encrypted protocol that provides secure file access, transfer, and management over an SSH connection (port 22).",
            keyPoints = "• Encrypted under standard SSH security; no passwords sent in plaintext.\n• Uses a single secure port (port 22) unlike FTP's messy dual-port setup.\n• Completely different protocol under the hood from legacy FTPS.",
            practicalUses = "• Securely uploading files, deploying server configurations, transferring data backups.",
            examples = "Example: Running 'sftp user@myserver.com' to transfer database dumps securely.",
            referenceUrl = "https://en.wikipedia.org/wiki/SSH_File_Transfer_Protocol"
        ),
        WordEntity(
            term = "SMTP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Simple Mail Transfer Protocol: the universal internet protocol used to send outgoing email messages from mail clients to servers and between mail servers.",
            keyPoints = "• Standard ports: 25 (server-to-server relay), 587 (client submission with TLS).\n• Text-based command protocol (HELO, MAIL FROM, RCPT TO, DATA).\n• Does not handle inbox downloading (handled by IMAP/POP3).",
            practicalUses = "• Sending transaction emails (password resets, notifications) via SendGrid or AWS SES.",
            examples = "Example: Your app connecting to smtp.gmail.com:587 to send verification emails.",
            referenceUrl = "https://en.wikipedia.org/wiki/Simple_Mail_Transfer_Protocol"
        ),
        WordEntity(
            term = "IMAP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Internet Message Access Protocol: an email retrieval protocol that lets email apps read and synchronize inbox messages directly on the mail server across multiple devices.",
            keyPoints = "• Synchronizes mail across phone, laptop, and webmail (port 993 with SSL).\n• Messages remain stored on the remote server unless deleted.\n• Replaced legacy POP3 which downloaded and deleted mail from the server.",
            practicalUses = "• Checking your Gmail or Outlook account on both your smartphone and desktop mail client.",
            examples = "Example: Marking an email as read on your phone instantly marks it read on your computer.",
            referenceUrl = "https://en.wikipedia.org/wiki/Internet_Message_Access_Protocol"
        ),
        WordEntity(
            term = "NTP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Network Time Protocol: a networking protocol operating over UDP port 123 that synchronizes computer clocks across the world to within milliseconds of atomic time.",
            keyPoints = "• Hierarchical stratum system (Stratum 0 atomic clocks down to Stratum 1/2 internet servers).\n• Accurate system clocks are mandatory for TLS certificate validation, Kerberos, and database replication timestamps.\n• Prevents clock drift on servers.",
            practicalUses = "• Keeping distributed databases and server logs perfectly synchronized in time.",
            examples = "Example: Linux chrony or ntpd daemon syncing with pool.ntp.org.",
            referenceUrl = "https://en.wikipedia.org/wiki/Network_Time_Protocol"
        ),

        // Networking tools (9)
        WordEntity(
            term = "curl",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A ubiquitous command-line tool and library for sending data and making HTTP, HTTPS, FTP, and WebSocket requests to test and consume APIs.",
            keyPoints = "• Built into almost all modern operating systems (Linux, macOS, Windows).\n• Supports headers, request bodies, authentication tokens, and cookies.\n• The gold standard command used in API documentation worldwide.",
            practicalUses = "• Testing REST endpoints, downloading files from terminals, debugging web servers.",
            examples = "Example: curl -X POST https://openrouter.ai/api/v1/chat/completions -H 'Authorization: Bearer KEY' -d '{\"model\": \"...\"}'",
            referenceUrl = "https://en.wikipedia.org/wiki/CURL"
        ),
        WordEntity(
            term = "dig",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Domain Information Groper: a flexible command-line network administration tool for interrogating DNS servers and troubleshooting DNS resolution.",
            keyPoints = "• Displays detailed DNS query response records, TTLs, and authority sections.\n• Can query specific nameservers directly (e.g. dig @8.8.8.8 example.com).\n• Much more powerful and informative than legacy nslookup.",
            practicalUses = "• Verifying if DNS changes, MX records, or SPF TXT records have propagated globally.",
            examples = "Example: 'dig MX google.com' prints Google's mail exchange server records.",
            referenceUrl = "https://en.wikipedia.org/wiki/Dig_(command)"
        ),
        WordEntity(
            term = "nslookup",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A classic command-line administrative tool for querying the Domain Name System to obtain domain name or IP address mapping info.",
            keyPoints = "• Available out of the box on Windows, macOS, and Linux.\n• Operates in interactive or non-interactive mode.\n• Simple utility for quick IP address lookups.",
            practicalUses = "• Quick cross-platform DNS resolution checks from command prompts.",
            examples = "Example: 'nslookup openai.com' displays the IP addresses assigned to the domain.",
            referenceUrl = "https://en.wikipedia.org/wiki/Nslookup"
        ),
        WordEntity(
            term = "netstat",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Network Statistics: a command-line tool that displays active network connections, listening ports, routing tables, and interface statistics on your operating system.",
            keyPoints = "• Reveals which programs are listening on which ports (e.g. netstat -tulpn on Linux).\n• Lists established TCP connections and foreign address states.\n• Replaced in modern Linux by the faster 'ss' (socket statistics) command.",
            practicalUses = "• Finding out which app is hogging port 8080 when your dev server fails to start.",
            examples = "Example: 'netstat -an | grep LISTEN' shows all open listening ports on the machine.",
            referenceUrl = "https://en.wikipedia.org/wiki/Netstat"
        ),
        WordEntity(
            term = "tcpdump",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A powerful command-line packet analyzer for Unix-like systems that captures and prints network traffic packets passing through a network card in real time.",
            keyPoints = "• Uses libpcap to capture raw network frames.\n• Offers sophisticated BPF (Berkeley Packet Filter) syntax to filter by port, host, or protocol.\n• Can save packet captures to .pcap files for inspection in Wireshark.",
            practicalUses = "• Deep packet analysis and diagnosing network issues on headless Linux production servers.",
            examples = "Example: 'sudo tcpdump -i eth0 -n port 80' captures all HTTP traffic on eth0.",
            referenceUrl = "https://en.wikipedia.org/wiki/Tcpdump"
        ),
        WordEntity(
            term = "Wireshark",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The world's foremost open-source graphical network protocol analyzer that lets you inspect micro-level packet contents and stream handshakes in deep visual detail.",
            keyPoints = "• Decodes hundreds of network protocols visually.\n• Allows following TCP and TLS streams to reconstruct raw conversations.\n• Powerful display filtering syntax (e.g. 'http.request.method == POST').",
            practicalUses = "• Forensic security analysis, protocol debugging, network engineering troubleshooting.",
            examples = "Example: Inspecting a TLS handshake step-by-step to see why a client rejected a certificate.",
            referenceUrl = "https://en.wikipedia.org/wiki/Wireshark"
        ),
        WordEntity(
            term = "Postman",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A popular graphical platform for building, testing, and documenting APIs, allowing developers to craft HTTP requests, inspect JSON responses, and automate test suites.",
            keyPoints = "• Visual UI for composing headers, auth tokens, and request bodies.\n• Environment variables for toggling between dev, staging, and prod.\n• Generates client code in curl, Python, JavaScript, and Kotlin.",
            practicalUses = "• Rapidly testing new API endpoints before writing mobile app code.",
            examples = "Example: Creating an OpenRouter API request collection in Postman with test assertions.",
            referenceUrl = "https://en.wikipedia.org/wiki/Postman_(software)"
        ),
        WordEntity(
            term = "ngrok",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A reverse proxy tunnel utility that securely exposes your local computer's localhost port to a public URL on the internet in seconds.",
            keyPoints = "• Creates an encrypted public tunnel (e.g. https://xyz.ngrok-free.app -> localhost:3000).\n• Bypasses NAT and home router firewalls without manual port forwarding.\n• Includes a web UI to inspect and replay live incoming HTTP requests.",
            practicalUses = "• Testing webhooks from Stripe or GitHub directly on your development laptop.",
            examples = "Example: Running 'ngrok http 3000' gives you a public HTTPS URL pointing to your local app.",
            referenceUrl = "https://en.wikipedia.org/wiki/Ngrok"
        ),
        WordEntity(
            term = "nmap",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Network Mapper: the standard open-source network scanner used for network discovery, vulnerability scanning, and finding open ports on remote computers.",
            keyPoints = "• Discovers live hosts, open ports, and running OS versions on subnets.\n• Uses advanced TCP SYN (stealth) scans, UDP scans, and service version probes.\n• Scripting engine (NSE) can test for known security vulnerabilities.",
            practicalUses = "• Network inventory audits, security penetration testing, firewall validation.",
            examples = "Example: 'nmap -sV -p 22,80,443 target.example.com' probes service versions on standard ports.",
            referenceUrl = "https://en.wikipedia.org/wiki/Nmap"
        )
    )
}
