package com.example.data.local

object DefaultWordsPart1Networking {
    fun getTerms(): List<WordEntity> = listOf(
        // Core (16)
        WordEntity(
            term = "IP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Internet Protocol: the fundamental digital postal address system of the internet that identifies where connected devices live so data can be delivered to them.",
            keyPoints = "• Assigns a unique numerical label to every device on a network.\n• Operates at Layer 3 (Network Layer) of the OSI model.\n• Routes individual packets across interconnected networks regardless of physical hardware.",
            practicalUses = "• Routing web traffic from your browser to remote servers.\n• Differentiating devices on your home Wi-Fi network.",
            examples = "Example: Your public home IP might look like 203.0.113.45 (IPv4) or 2001:db8::1 (IPv6).",
            referenceUrl = "https://en.wikipedia.org/wiki/Internet_Protocol"
        ),
        WordEntity(
            term = "DNS",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Domain Name System: the phonebook of the internet. It translates human-friendly website names into machine-readable IP addresses.",
            keyPoints = "• Converts domains like google.com into numeric IP addresses like 142.250.190.46.\n• Distributed, hierarchical database spread across global root, TLD, and authoritative servers.\n• Employs multi-tiered caching to resolve queries in milliseconds.",
            practicalUses = "• Typing web addresses instead of memorizing 12-digit numbers.\n• Directing email traffic using MX DNS records.",
            examples = "Example: In a terminal, running 'dig openai.com +short' returns the IP address 104.18.7.192.",
            referenceUrl = "https://en.wikipedia.org/wiki/Domain_Name_System"
        ),
        WordEntity(
            term = "TCP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Transmission Control Protocol: a reliable delivery protocol that guarantees all data packets arrive completely, in correct order, and without corruption.",
            keyPoints = "• Connection-oriented protocol using a 3-way handshake (SYN, SYN-ACK, ACK).\n• Detects packet loss and retransmits missing pieces automatically.\n• Reassembles disordered packets in their exact initial sequence.",
            practicalUses = "• Web browsing (HTTP/HTTPS), file downloads, database transactions, email sending.",
            examples = "Example: Downloading a PDF or sending a git commit relies on TCP so not a single byte is lost.",
            referenceUrl = "https://en.wikipedia.org/wiki/Transmission_Control_Protocol"
        ),
        WordEntity(
            term = "UDP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "User Datagram Protocol: a lightweight, fast, connectionless protocol that sends data immediately without handshakes or delivery confirmation.",
            keyPoints = "• Fire-and-forget: does not wait for acknowledgments or retransmit lost packets.\n• Minimal latency and low packet overhead.\n• Order is not guaranteed, making speed the top priority.",
            practicalUses = "• Live video streaming, multiplayer gaming, VoIP phone calls, DNS queries.",
            examples = "Example: In a live Zoom call or competitive online game, losing a few audio frames is better than pausing the stream to re-download them.",
            referenceUrl = "https://en.wikipedia.org/wiki/User_Datagram_Protocol"
        ),
        WordEntity(
            term = "Port",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A virtual communication endpoint or door number on a computer that directs incoming network traffic to the specific app or service running there.",
            keyPoints = "• 16-bit integer ranging from 0 to 65,535.\n• Well-known ports (0–1023) reserved for standard system services.\n• Combines with an IP address to form a network socket (e.g. 192.168.1.5:8080).",
            practicalUses = "• Hosting multiple servers (web, database, SSH) on a single physical machine.",
            examples = "Example: Port 80 for HTTP, 443 for HTTPS, 22 for SSH, 5432 for PostgreSQL.",
            referenceUrl = "https://en.wikipedia.org/wiki/Port_(computer_networking)"
        ),
        WordEntity(
            term = "HTTP/HTTPS",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "HyperText Transfer Protocol (and its Secure encrypted twin): the lingua franca protocol of the World Wide Web used to transfer text, images, and API payloads.",
            keyPoints = "• Request-response protocol initiated by clients (browsers/apps) to servers.\n• HTTPS encrypts the entire stream with TLS/SSL to prevent eavesdropping and tampering.\n• Stateless by design; state is maintained with tokens, headers, or cookies.",
            practicalUses = "• Loading web pages, consuming RESTful APIs, downloading assets.",
            examples = "Example: Opening https://api.github.com sends an encrypted GET request to fetch repository data.",
            referenceUrl = "https://en.wikipedia.org/wiki/HTTPS"
        ),
        WordEntity(
            term = "TLS/SSL",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Transport Layer Security (modern successor to SSL): cryptographic protocols that secure and encrypt internet communications between clients and servers.",
            keyPoints = "• Provides privacy through symmetric encryption.\n• Authenticates the server's identity using digital certificates issued by trusted CAs.\n• Ensures message integrity to detect tampering in flight.",
            practicalUses = "• Green lock padlock in browsers, protecting online banking, securing API tokens in transit.",
            examples = "Example: During a TLS 1.3 handshake, client and server negotiate keys in one round trip before sending encrypted data.",
            referenceUrl = "https://en.wikipedia.org/wiki/Transport_Layer_Security"
        ),
        WordEntity(
            term = "WebSocket",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A persistent, bi-directional, full-duplex communication channel opened over a single TCP connection, allowing real-time data flow without polling.",
            keyPoints = "• Upgrades from an initial HTTP handshake to an ongoing TCP connection.\n• Either client or server can send messages at any time without waiting for a request.\n• Much lower latency and overhead than repeated HTTP polling.",
            practicalUses = "• Real-time chat apps, financial ticker updates, collaborative doc editing, live agent output streams.",
            examples = "Example: In a live AI coding agent interface, code edits stream continuously via WebSocket.",
            referenceUrl = "https://en.wikipedia.org/wiki/WebSocket"
        ),
        WordEntity(
            term = "Proxy",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "An intermediary server that acts on behalf of a client or server, forwarding requests and responses while hiding true identities or applying rules.",
            keyPoints = "• Intercepts network calls to filter, inspect, cache, or anonymize traffic.\n• Forward proxies protect and anonymize client devices.\n• Reverse proxies shield backend servers and balance incoming load.",
            practicalUses = "• Corporate content filtering, web scraping rotation, bypass geo-restrictions.",
            examples = "Example: Configuring your browser to use a corporate proxy at 10.0.0.1:8080 to inspect outbound traffic.",
            referenceUrl = "https://en.wikipedia.org/wiki/Proxy_server"
        ),
        WordEntity(
            term = "Load balancer",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A traffic cop that distributes incoming network or web requests across a pool of backend servers to prevent any single machine from getting overwhelmed.",
            keyPoints = "• Increases application capacity and concurrent user concurrency.\n• Provides fault tolerance by automatically routing away from failed servers.\n• Supports strategies like Round Robin, Least Connections, or IP Hash.",
            practicalUses = "• Handling millions of simultaneous requests for big services like Google or Netflix.",
            examples = "Example: AWS ALB or Nginx receiving traffic on port 443 and dividing it across 10 EC2 instances.",
            referenceUrl = "https://en.wikipedia.org/wiki/Load_balancing_(computing)"
        ),
        WordEntity(
            term = "CDN",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Content Delivery Network: a worldwide mesh of distributed edge servers that cache content close to users so sites load lightning-fast.",
            keyPoints = "• Serves static assets (images, CSS, JS, videos) from the nearest geographical PoP (Point of Presence).\n• Drastically reduces latency and server origin load.\n• Helps absorb massive traffic spikes and DDoS attacks.",
            practicalUses = "• Cloudflare, Fastly, CloudFront delivering images and web apps worldwide in under 20ms.",
            examples = "Example: A user in Tokyo loads image assets from a Tokyo CDN node rather than fetching from a server in Virginia.",
            referenceUrl = "https://en.wikipedia.org/wiki/Content_delivery_network"
        ),
        WordEntity(
            term = "Firewall",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A digital security barrier that monitors and filters incoming and outgoing network traffic based on predetermined security rules.",
            keyPoints = "• Blocks unauthorized access while permitting legitimate communications.\n• Can be hardware-based, software-based, or cloud-native.\n• Inspects packet headers (IP, port) and state (stateful firewall).",
            practicalUses = "• Protecting home Wi-Fi from malicious internet probes; locking down cloud servers to port 22 only from VPN.",
            examples = "Example: An iptables or AWS Security Group rule allowing inbound traffic only on ports 80 and 443.",
            referenceUrl = "https://en.wikipedia.org/wiki/Firewall_(computing)"
        ),
        WordEntity(
            term = "VPN",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Virtual Private Network: an encrypted tunnel across the public internet that securely connects your device to a remote private network.",
            keyPoints = "• Encrypts all outgoing traffic so local Wi-Fi snoopers cannot see what you do.\n• Masks your real IP address with the VPN server's IP address.\n• Gives remote employees access to internal company servers as if physically in the office.",
            practicalUses = "• Secure remote work, private browsing on public coffee shop Wi-Fi, accessing regional content.",
            examples = "Example: WireGuard or OpenVPN connecting your phone to your home network.",
            referenceUrl = "https://en.wikipedia.org/wiki/Virtual_private_network"
        ),
        WordEntity(
            term = "Latency",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The time delay it takes for a data packet to travel from its source to its destination across a network, typically measured in milliseconds (ms).",
            keyPoints = "• Governed by the speed of light in fiber, physical distance, router hops, and queuing delays.\n• Distinct from bandwidth: latency is delay (speed of individual packet), bandwidth is capacity.\n• Critical metric for user responsiveness and real-time AI generation.",
            practicalUses = "• Optimizing API response times, online gaming matchmaking, interactive voice agents.",
            examples = "Example: Ping time of 15ms feels instant; latency of 400ms causes noticeable lag in voice conversation.",
            referenceUrl = "https://en.wikipedia.org/wiki/Latency_(engineering)"
        ),
        WordEntity(
            term = "Bandwidth",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The maximum volume of data that can be transmitted through a network connection in a given amount of time, usually measured in Mbps or Gbps.",
            keyPoints = "• Represents pipe thickness, not packet speed.\n• High bandwidth lets many users stream video at the same time.\n• Bottlenecks occur when data demand exceeds available bandwidth.",
            practicalUses = "• Sizing internet plans, video streaming bitrates, transferring large AI model weights.",
            examples = "Example: A 1 Gbps fiber connection can download a 10 GB file in around 80 seconds.",
            referenceUrl = "https://en.wikipedia.org/wiki/Bandwidth_(computing)"
        ),
        WordEntity(
            term = "NAT",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Network Address Translation: a technique where a router maps multiple private local IP addresses onto a single public IP address when accessing the internet.",
            keyPoints = "• Conserves the scarce pool of public IPv4 addresses.\n• Hides internal network device IPs from external observation.\n• Uses port translation (PAT) to track which internal device initiated each connection.",
            practicalUses = "• Allowing 50 phones and laptops on home Wi-Fi to share one public IP from the ISP.",
            examples = "Example: Your phone (192.168.1.15) sends a packet; the router replaces the source IP with 73.189.20.101.",
            referenceUrl = "https://en.wikipedia.org/wiki/Network_address_translation"
        ),

        // Addressing and layers (14)
        WordEntity(
            term = "OSI model",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Open Systems Interconnection model: a 7-layer theoretical blueprint explaining how data moves from software applications down to physical cables and back.",
            keyPoints = "• Layer 7: Application (HTTP, DNS)\n• Layer 6: Presentation (TLS, JSON)\n• Layer 5: Session (RPC)\n• Layer 4: Transport (TCP, UDP)\n• Layer 3: Network (IP, ICMP)\n• Layer 2: Data Link (Ethernet, MAC)\n• Layer 1: Physical (cables, radio waves).",
            practicalUses = "• Troubleshooting network issues methodically layer by layer.",
            examples = "Example: If Wi-Fi is connected (Layer 1/2) and IP is assigned (Layer 3) but web pages fail, check DNS (Layer 7).",
            referenceUrl = "https://en.wikipedia.org/wiki/OSI_model"
        ),
        WordEntity(
            term = "TCP/IP model",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The practical 4-layer architecture that actually powers the modern internet: Application, Transport, Internet, and Network Access.",
            keyPoints = "• Streamlined real-world counterpart to the theoretical 7-layer OSI model.\n• Application Layer merges OSI layers 5, 6, and 7.\n• The foundation of the Internet Engineering Task Force (IETF) standards.",
            practicalUses = "• Real-world software engineering and network stack implementation.",
            examples = "Example: In Linux networking, socket APIs map directly to TCP/IP model layers.",
            referenceUrl = "https://en.wikipedia.org/wiki/Internet_protocol_suite"
        ),
        WordEntity(
            term = "MAC address",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Media Access Control address: a unique hardware fingerprint burned into your network card by the manufacturer to identify it on a local subnet.",
            keyPoints = "• 48-bit hexadecimal number formatted like 00:1A:2B:3C:4D:5E.\n• Operates strictly at Layer 2 (Data Link Layer).\n• Never leaves the local network (routers replace MAC addresses at each hop).",
            practicalUses = "• Local Wi-Fi device filtering, DHCP IP reservations, switch forwarding tables.",
            examples = "Example: Your router uses MAC addresses to know which specific smartphone requested the data.",
            referenceUrl = "https://en.wikipedia.org/wiki/MAC_address"
        ),
        WordEntity(
            term = "IPv4",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Internet Protocol version 4: the dominant 32-bit addressing system used since 1983, providing approximately 4.3 billion unique addresses.",
            keyPoints = "• Written as four decimal numbers separated by dots (e.g. 192.0.2.1).\n• Depleted due to the massive global explosion of internet-connected smartphones and servers.\n• Extended in longevity through NAT (Network Address Translation).",
            practicalUses = "• Standard IP format supported by virtually every legacy system and network device.",
            examples = "Example: 8.8.8.8 (Google Public DNS).",
            referenceUrl = "https://en.wikipedia.org/wiki/IPv4"
        ),
        WordEntity(
            term = "IPv6",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Internet Protocol version 6: the modern 128-bit addressing format created to provide an essentially limitless pool of 340 undecillion unique internet addresses.",
            keyPoints = "• Written as 8 groups of 4 hexadecimal digits separated by colons (e.g. 2001:0db8::1).\n• Eliminates the need for NAT; allows end-to-end direct connectivity.\n• Built-in security and streamlined routing header format.",
            practicalUses = "• 5G mobile networks, cloud deployments, modern ISP connections.",
            examples = "Example: 2607:f8b0:4005:805::200e (Google search server).",
            referenceUrl = "https://en.wikipedia.org/wiki/IPv6"
        ),
        WordEntity(
            term = "Subnet",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A logically partitioned division of an IP network that groups related devices together for security, performance, and organizational efficiency.",
            keyPoints = "• Divides a larger network into smaller, isolated broadcast domains.\n• Devices in the same subnet can talk directly without going through a router.\n• Enforces traffic control boundaries in cloud VPCs.",
            practicalUses = "• Separating public web servers from private database clusters in AWS or GCP.",
            examples = "Example: Subnet 192.168.1.0/24 hosts office desktop computers; 192.168.2.0/24 hosts guest Wi-Fi.",
            referenceUrl = "https://en.wikipedia.org/wiki/Subnetwork"
        ),
        WordEntity(
            term = "Subnet mask",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A 32-bit number used by network devices to determine which part of an IP address refers to the network and which part refers to the host device.",
            keyPoints = "• Typically looks like 255.255.255.0 for standard home networks.\n• Binary 1s represent the network portion; binary 0s represent available host slots.\n• Bitwise AND operation between IP and mask identifies the subnet address.",
            practicalUses = "• Deciding whether to send a packet directly over local Ethernet or forward it to the default gateway.",
            examples = "Example: IP 192.168.1.50 with mask 255.255.255.0 means 192.168.1 is the network, .50 is the device.",
            referenceUrl = "https://en.wikipedia.org/wiki/Subnetwork#Subnet_mask"
        ),
        WordEntity(
            term = "CIDR",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Classless Inter-Domain Routing: a compact notation for IP addresses that appends a slash followed by the count of network prefix bits (e.g. /24).",
            keyPoints = "• Replaced the rigid legacy Class A, B, C system with flexible network sizing.\n• /24 equals 256 addresses (254 usable devices).\n• /32 specifies a single individual host; /0 specifies the entire internet.",
            practicalUses = "• Configuring firewall rules, cloud VPC routing, IP allocation.",
            examples = "Example: 10.0.0.0/16 provides 65,536 addresses for a company's cloud infrastructure.",
            referenceUrl = "https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing"
        ),
        WordEntity(
            term = "Default gateway",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The exit router address on your local network where packets are sent whenever their destination IP is outside the local subnet.",
            keyPoints = "• The front door of your local network.\n• Typically assigned the first or last IP address in a subnet (e.g. 192.168.1.1).\n• Forwards traffic across WAN interfaces toward remote destinations.",
            practicalUses = "• Essential configuration parameter for any computer needing internet access.",
            examples = "Example: In a home router setup, the default gateway is almost always 192.168.1.1 or 192.168.0.1.",
            referenceUrl = "https://en.wikipedia.org/wiki/Default_gateway"
        ),
        WordEntity(
            term = "DHCP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Dynamic Host Configuration Protocol: an automated network server service that hands out IP addresses, subnet masks, and DNS settings to new devices joining a network.",
            keyPoints = "• Uses the 4-step DORA process: Discover, Offer, Request, Acknowledge.\n• Prevents accidental IP conflicts by tracking assigned leases.\n• Automatically reclaims addresses when devices disconnect.",
            practicalUses = "• Seamlessly connecting your phone or laptop to a Wi-Fi hotspot without manual setup.",
            examples = "Example: When you connect to Starbucks Wi-Fi, DHCP assigns your laptop 172.16.4.120 with an 8-hour lease.",
            referenceUrl = "https://en.wikipedia.org/wiki/Dynamic_Host_Configuration_Protocol"
        ),
        WordEntity(
            term = "ARP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Address Resolution Protocol: a local network protocol that translates an IP address into a physical hardware MAC address.",
            keyPoints = "• Broadcasts 'Who has IP 192.168.1.20? Tell 192.168.1.1'.\n• The target device responds with its MAC address.\n• Results are cached in an ARP table to avoid repeated broadcasts.",
            practicalUses = "• Enabling local Layer 2 frame delivery across Ethernet and Wi-Fi networks.",
            examples = "Example: Run 'arp -a' in your terminal to see local IP-to-MAC mappings known by your operating system.",
            referenceUrl = "https://en.wikipedia.org/wiki/Address_Resolution_Protocol"
        ),
        WordEntity(
            term = "Localhost / loopback (127.0.0.1)",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A special reserved hostname and IP address that routes network traffic back to the very same local computer without leaving the machine.",
            keyPoints = "• Standard IPv4 loopback is 127.0.0.1; IPv6 is ::1.\n• Packets bypass the physical network interface entirely in software.\n• Essential environment for developing and testing software locally.",
            practicalUses = "• Testing local web servers, running local AI models (Ollama on localhost:11434).",
            examples = "Example: Navigating to http://localhost:3000 in your browser tests your React or Vite web app.",
            referenceUrl = "https://en.wikipedia.org/wiki/Localhost"
        ),
        WordEntity(
            term = "Public vs. private IP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Public IPs are globally unique and routable on the open internet; private IPs are reserved for internal local networks and cannot be contacted directly from the outside.",
            keyPoints = "• Private ranges defined by RFC 1918: 10.0.0.0/8, 172.16.0.0/12, 192.168.0.0/16.\n• Public IPs are assigned by regional internet registries (RIRs) and ISPs.\n• Routers block private IPs from crossing public internet backbones.",
            practicalUses = "• Keeping internal servers secure from random internet hackers.",
            examples = "Example: Your laptop's private IP is 192.168.1.12; your external public IP seen by Google is 73.24.110.5.",
            referenceUrl = "https://en.wikipedia.org/wiki/Private_network"
        ),
        WordEntity(
            term = "Static vs. dynamic IP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Static IPs are permanently configured and never change; dynamic IPs are leased temporarily by DHCP servers and may change periodically.",
            keyPoints = "• Static IPs are mandatory for web servers, DNS servers, and VPN endpoints that clients must consistently locate.\n• Dynamic IPs save costs and simplify home and mobile consumer internet management.\n• ISPs frequently charge extra for static public IP addresses.",
            practicalUses = "• Setting up a static IP for an office printer or production cloud database.",
            examples = "Example: Production web server has static IP 203.0.113.10; your smartphone gets a dynamic IP that changes after rebooting.",
            referenceUrl = "https://en.wikipedia.org/wiki/IP_address#Dynamic_addressing"
        )
    )
}
