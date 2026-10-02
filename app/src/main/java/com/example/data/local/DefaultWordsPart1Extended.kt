package com.example.data.local

object DefaultWordsPart1Extended {
    fun getTerms(): List<WordEntity> = listOf(
        // Devices and routing (16)
        WordEntity(
            term = "Router",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A specialized networking device that reads IP addresses and forwards data packets between different networks to guide them to their destination.",
            keyPoints = "• Operates at Layer 3 (Network Layer).\n• Maintains routing tables to calculate the fastest path.\n• Connects local home networks to the broader internet.",
            practicalUses = "• Your home Wi-Fi router connecting your devices to your ISP fiber/cable line.",
            examples = "Example: Cisco enterprise routers or home ASUS routers directing internet packets.",
            referenceUrl = "https://en.wikipedia.org/wiki/Router_(computing)"
        ),
        WordEntity(
            term = "Switch",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A device in a local network that connects wired computers and forwards data frames directly to the intended destination device using MAC addresses.",
            keyPoints = "• Operates at Layer 2 (Data Link Layer).\n• Learns MAC addresses connected to each physical port.\n• Avoids network congestion by transmitting packets only to the target port, unlike legacy hubs.",
            practicalUses = "• Interconnecting servers in a data center rack; wiring office workstation cubicles.",
            examples = "Example: A 24-port Gigabit Ethernet switch in an office server room.",
            referenceUrl = "https://en.wikipedia.org/wiki/Network_switch"
        ),
        WordEntity(
            term = "Modem",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Modulator-Demodulator: converts digital signals from your computer into analog signals suitable for telephone, cable, or fiber lines and vice versa.",
            keyPoints = "• Converts between computer digital 1s/0s and physical transmission frequencies.\n• Provided by your Internet Service Provider (ISP).\n• Placed between the street utility cable and your home router.",
            practicalUses = "• Bringing broadband internet into homes and commercial buildings.",
            examples = "Example: A DOCSIS 3.1 cable modem or fiber ONT terminating the optical connection.",
            referenceUrl = "https://en.wikipedia.org/wiki/Modem"
        ),
        WordEntity(
            term = "Access point",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A hardware device that broadcasts a wireless Wi-Fi radio signal, allowing wireless laptops and phones to connect to a wired network.",
            keyPoints = "• Extends the wireless coverage area of an existing wired network.\n• Supports Wi-Fi roaming across multiple access points without dropped connections.\n• Broadcasts an SSID (network name).",
            practicalUses = "• Providing seamless Wi-Fi coverage across large offices, hotels, and university campuses.",
            examples = "Example: Ubiquiti UniFi ceiling-mounted access points throughout an office building.",
            referenceUrl = "https://en.wikipedia.org/wiki/Wireless_access_point"
        ),
        WordEntity(
            term = "Hop",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "One intermediate transit step as a data packet travels from one router or gateway to the next along its journey across the internet.",
            keyPoints = "• Each router a packet passes through is counted as 1 hop.\n• The IP header's TTL (Time To Live) is decremented by 1 at every hop.\n• Prevents infinite routing loops if a packet gets lost.",
            practicalUses = "• Measuring network path complexity and diagnosing routing slowdowns.",
            examples = "Example: Pinging a server across the ocean might take 14 hops across various ISP routers.",
            referenceUrl = "https://en.wikipedia.org/wiki/Hop_(networking)"
        ),
        WordEntity(
            term = "Routing table",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "An internal electronic cheat-sheet or database stored in a router or operating system listing network destinations and the next hop interface to reach them.",
            keyPoints = "• Maps destination CIDR subnets to specific gateway IPs and interface cards.\n• Uses longest-prefix match algorithm to pick the most specific route.\n• Can be statically configured by admins or dynamically learned via routing protocols.",
            practicalUses = "• Directing traffic to local subnets, VPN tunnels, or the public default gateway.",
            examples = "Example: Run 'netstat -rn' or 'ip route' in terminal to view your computer's active routing table.",
            referenceUrl = "https://en.wikipedia.org/wiki/Routing_table"
        ),
        WordEntity(
            term = "BGP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Border Gateway Protocol: the postal routing protocol of the global internet that enables major Autonomous Systems (ISPs, tech giants) to exchange routing paths.",
            keyPoints = "• Path-vector protocol operating at the edge of the internet.\n• Directs packets across thousands of distinct telecommunication networks.\n• BGP misconfigurations can accidentally take down worldwide websites like Facebook or Cloudflare.",
            practicalUses = "• Directing global internet traffic between ISPs, cloud providers, and telecom backbones.",
            examples = "Example: Cloudflare uses BGP Anycast to direct traffic to their nearest worldwide data center.",
            referenceUrl = "https://en.wikipedia.org/wiki/Border_Gateway_Protocol"
        ),
        WordEntity(
            term = "OSPF",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Open Shortest Path First: an interior routing protocol used inside a single organization or company network to calculate the fastest path between routers.",
            keyPoints = "• Link-state routing protocol based on Dijkstra's shortest-path algorithm.\n• Rapidly converges when a network link or cable is severed.\n• Operates within an Autonomous System (IGP - Interior Gateway Protocol).",
            practicalUses = "• Routing traffic efficiently across large corporate campuses and multi-building networks.",
            examples = "Example: A hospital network routing patient telemetry data through the lowest-latency router path.",
            referenceUrl = "https://en.wikipedia.org/wiki/Open_Shortest_Path_First"
        ),
        WordEntity(
            term = "ICMP",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Internet Control Message Protocol: a diagnostic and error-reporting protocol used by network devices to communicate connection issues and echo tests.",
            keyPoints = "• Operates at Layer 3 alongside IP.\n• Sends error alerts like 'Destination Unreachable' or 'Time to Live Exceeded'.\n• Powers utility tools like ping and traceroute.",
            practicalUses = "• Diagnostic health checks and latency measurement across network links.",
            examples = "Example: ICMP Type 8 (Echo Request) and Type 0 (Echo Reply) form the basis of the ping command.",
            referenceUrl = "https://en.wikipedia.org/wiki/Internet_Control_Message_Protocol"
        ),
        WordEntity(
            term = "Ping",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A simple terminal utility command that sends an echo request to a remote host to test whether it is reachable and how quickly it responds.",
            keyPoints = "• Uses ICMP Echo Request and Echo Reply packets.\n• Reports round-trip time in milliseconds and packet loss percentage.\n• First line of defense when testing network connectivity.",
            practicalUses = "• Verifying if a server, router, or website is online.",
            examples = "Example: Running 'ping 8.8.8.8' in terminal verifies you have working internet access.",
            referenceUrl = "https://en.wikipedia.org/wiki/Ping_(networking_utility)"
        ),
        WordEntity(
            term = "Traceroute",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A diagnostic tool that traces the exact hop-by-hop path packets take across routers to reach a destination server, highlighting where delays occur.",
            keyPoints = "• Sends packets with incrementally increasing TTL values (1, 2, 3...).\n• Each router drops the packet when TTL reaches 0 and sends back an ICMP 'Time Exceeded' message.\n• Reveals intermediate routers and geographical routing paths.",
            practicalUses = "• Locating which specific network provider or hop is dropping packets or causing high ping.",
            examples = "Example: Running 'traceroute google.com' or 'tracert google.com' on Windows.",
            referenceUrl = "https://en.wikipedia.org/wiki/Traceroute"
        ),
        WordEntity(
            term = "Packet",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A small, formatted chunk of data packaged with source and destination IP addresses so it can travel across a digital network independently.",
            keyPoints = "• Operates at Layer 3 (Network Layer).\n• Contains a header (metadata like IPs, TTL, protocol) and a payload (actual message data).\n• Large files are split into thousands of packets and reassembled upon arrival.",
            practicalUses = "• Every message, image, and webpage sent over the internet travels as packets.",
            examples = "Example: An image is broken down into 1,500-byte IP packets to cross internet fiber cables.",
            referenceUrl = "https://en.wikipedia.org/wiki/Network_packet"
        ),
        WordEntity(
            term = "Frame",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The unit of data at Layer 2 (Data Link Layer) that wraps an IP packet inside source and destination physical MAC addresses for transmission across local Ethernet or Wi-Fi.",
            keyPoints = "• Adds an Ethernet header and a frame check sequence (CRC) for error detection.\n• Stripped and recreated by routers at every hop.\n• Maximum size is typically 1,518 bytes on standard Ethernet.",
            practicalUses = "• Direct hardware delivery between your computer and local network switch/router.",
            examples = "Example: An Ethernet frame delivering an IPv4 packet to your local router's MAC address.",
            referenceUrl = "https://en.wikipedia.org/wiki/Ethernet_frame"
        ),
        WordEntity(
            term = "Segment",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The unit of data at Layer 4 (Transport Layer) managed by TCP, containing source/destination ports, sequence numbers, and payload chunks.",
            keyPoints = "• Manages end-to-end data sequencing and reliability.\n• Numbered sequentially so the receiver can reassemble them in order.\n• Corresponds to 'Datagram' in UDP.",
            practicalUses = "• Chopping a 5 MB photo into bite-sized byte streams for reliable TCP transmission.",
            examples = "Example: A TCP segment with sequence number 1000 containing 1460 bytes of HTTP data.",
            referenceUrl = "https://en.wikipedia.org/wiki/Transmission_Control_Protocol#TCP_segment_structure"
        ),
        WordEntity(
            term = "MTU",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Maximum Transmission Unit: the largest packet or frame size (in bytes) that a network interface can transmit without needing to fragment it.",
            keyPoints = "• Standard Ethernet MTU is 1,500 bytes.\n• Jumbo frames (common in data centers) support MTUs up to 9,000 bytes.\n• Packets exceeding MTU must be fragmented, causing latency and CPU overhead.",
            practicalUses = "• Tuning VPN tunnels (like WireGuard) to 1,420 bytes to prevent fragmentation.",
            examples = "Example: Setting MTU to 1500 on eth0 network interface.",
            referenceUrl = "https://en.wikipedia.org/wiki/Maximum_transmission_unit"
        ),
        WordEntity(
            term = "Anycast",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A network routing technique where multiple physical servers around the world share the exact same IP address, and BGP routes clients to the closest server.",
            keyPoints = "• One-to-nearest communication model.\n• BGP automatically selects the topologically nearest data center.\n• If one server location fails, traffic automatically reroutes to the next closest one.",
            practicalUses = "• DNS root servers, Cloudflare edge CDN, Google Public DNS (8.8.8.8).",
            examples = "Example: Pinging 1.1.1.1 reaches Cloudflare's server in your local city rather than one across the world.",
            referenceUrl = "https://en.wikipedia.org/wiki/Anycast"
        ),

        // Transport behavior & Web protocols
        WordEntity(
            term = "Socket",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A software endpoint for sending or receiving data across a network, defined by the combination of an IP address, port number, and protocol.",
            keyPoints = "• Uniquely identified as IP:Port (e.g. 127.0.0.1:8080).\n• Standard programming API in Unix/POSIX for network communication.\n• Enables bidirectional stream (TCP) or datagram (UDP) data transfers.",
            practicalUses = "• Foundation of all backend web servers, API clients, and network applications.",
            examples = "Example: A client socket connected to 142.250.190.46:443 to load a webpage over TLS.",
            referenceUrl = "https://en.wikipedia.org/wiki/Network_socket"
        ),
        WordEntity(
            term = "Three-way handshake",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The 3-step synchronization greeting (SYN, SYN-ACK, ACK) used by TCP to establish a reliable, verified connection before sending real data.",
            keyPoints = "• Step 1: Client sends SYN (synchronize sequence numbers).\n• Step 2: Server responds with SYN-ACK (acknowledgment & server sync).\n• Step 3: Client replies with ACK (acknowledged, connection established).\n• Guarantees both sides are listening and agree on starting packet sequences.",
            practicalUses = "• Starting every HTTP/1.1 and HTTP/2 web connection.",
            examples = "Example: When loading a website, your browser sends SYN to port 443 before sending the HTTP GET request.",
            referenceUrl = "https://en.wikipedia.org/wiki/Handshaking#TCP_three-way_handshake"
        ),
        WordEntity(
            term = "SYN / ACK",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The flag signals used in TCP packets: SYN initiates synchronization of sequence numbers, while ACK acknowledges receipt of transmitted data.",
            keyPoints = "• Embedded as 1-bit flags inside the TCP header.\n• FIN flag initiates graceful termination; RST forcefully resets a broken connection.\n• SYN flood attacks overwhelm servers by sending fake SYNs without sending final ACKs.",
            practicalUses = "• Reliable state tracking and connection lifecycle management in TCP.",
            examples = "Example: In Wireshark, the second packet of a connection shows [SYN, ACK].",
            referenceUrl = "https://en.wikipedia.org/wiki/Transmission_Control_Protocol#TCP_segment_structure"
        ),
        WordEntity(
            term = "Congestion control",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Algorithms built into TCP (like BBR, Cubic, Reno) that prevent senders from flooding network links with more data than the intermediate routers can handle.",
            keyPoints = "• Dynamically adjusts the sender's congestion window (cwnd).\n• Uses slow start, congestion avoidance, fast retransmit, and fast recovery.\n• Modern BBR (Bottleneck Bandwidth and RTT) optimizes throughput based on physical link capacity.",
            practicalUses = "• Preventing global internet gridlock and bufferbloat under heavy video traffic.",
            examples = "Example: YouTube and Netflix streaming adapt their transmission speed smoothly using TCP congestion control.",
            referenceUrl = "https://en.wikipedia.org/wiki/TCP_congestion_control"
        ),
        WordEntity(
            term = "Flow control",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A mechanism that ensures a fast sender does not overwhelm a slow receiver's local memory buffer with more data than it can process.",
            keyPoints = "• The receiver advertises its 'receive window' (rwnd) size in every TCP ACK.\n• The sender never transmits more bytes than the receiver currently has space for.\n• If rwnd drops to 0, sender pauses until the receiver drains its buffer.",
            practicalUses = "• Preventing slow mobile phones from dropping packets sent by high-speed fiber servers.",
            examples = "Example: A phone downloading a file signals a smaller receive window when CPU is busy.",
            referenceUrl = "https://en.wikipedia.org/wiki/Flow_control_(data)"
        ),
        WordEntity(
            term = "Packet loss",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "When one or more data packets travelling across a computer network fail to reach their destination, caused by buffer overflows, Wi-Fi interference, or faulty cables.",
            keyPoints = "• Measured as a percentage of total transmitted packets.\n• TCP detects lost packets and retransmits them, causing latency spikes.\n• Even 2% packet loss can degrade voice call quality or ruin online gaming.",
            practicalUses = "• Diagnostic health indicator for ISPs, Wi-Fi connections, and cloud networks.",
            examples = "Example: Running ping shows '4 packets transmitted, 3 received, 25% packet loss'.",
            referenceUrl = "https://en.wikipedia.org/wiki/Packet_loss"
        ),
        WordEntity(
            term = "Jitter",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The variation in packet arrival time delays. If some packets take 20ms and others take 150ms, the jitter is high, causing stutter in real-time media.",
            keyPoints = "• Caused by network congestion, router queue fluctuations, and route changes.\n• Managed in voice/video apps using jitter buffers.\n• Low jitter is essential for smooth real-time voice and video conversations.",
            practicalUses = "• Crucial quality-of-service (QoS) metric in VoIP and live game streaming.",
            examples = "Example: A video call sounding robotic or stuttering during high network jitter.",
            referenceUrl = "https://en.wikipedia.org/wiki/Jitter#Packet_jitter_in_computer_networks"
        ),
        WordEntity(
            term = "Round-trip time (RTT)",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The time it takes (in milliseconds) for a data signal to be sent from the origin, travel to the remote destination, and return with an acknowledgment.",
            keyPoints = "• Primary metric of network responsiveness.\n• Heavily influenced by geographic distance and fiber optic transit speed (~5ms per 1000 km).\n• Directly impacts the speed of TCP handshakes and API response times.",
            practicalUses = "• Selecting optimal cloud server regions close to end users.",
            examples = "Example: RTT from New York to London is approximately 65ms over transatlantic subsea cables.",
            referenceUrl = "https://en.wikipedia.org/wiki/Round-trip_time"
        ),
        WordEntity(
            term = "Throughput",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The actual rate at which successful data is delivered over a network communication channel, as opposed to theoretical maximum bandwidth.",
            keyPoints = "• Always lower than theoretical bandwidth due to packet headers, TCP overhead, and retransmissions.\n• Measured in bits or bytes per second (e.g. MB/s or Gbps).\n• Reflects real-world download and upload performance.",
            practicalUses = "• Measuring database replication speed, cloud storage upload rates, model weight downloads.",
            examples = "Example: On a 100 Mbps internet plan, your actual file download throughput might be 11.2 MB/s.",
            referenceUrl = "https://en.wikipedia.org/wiki/Throughput"
        ),
        WordEntity(
            term = "Keep-alive",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A technique or header that keeps an established TCP connection open between requests so multiple files can be fetched without repeating the 3-way handshake.",
            keyPoints = "• Default in HTTP/1.1 and later protocols.\n• Saves significant CPU and round-trip latency by reusing existing TLS connections.\n• Closed by an idle timeout or explicit 'Connection: close' header.",
            practicalUses = "• Loading web pages with dozens of CSS, JS, and image assets over a single TCP socket.",
            examples = "Example: HTTP header 'Connection: keep-alive' allows loading 30 images without 30 separate handshakes.",
            referenceUrl = "https://en.wikipedia.org/wiki/Keepalive"
        ),
        WordEntity(
            term = "Connection pooling",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A cache of pre-established, reusable network connections (e.g., to a database or API) kept alive in memory so requests don't waste time opening new sockets.",
            keyPoints = "• Replaces expensive connection creation/teardown cycles with instant check-out and return.\n• Prevents overwhelming databases with thousands of simultaneous TCP handshakes.\n• Standard practice in high-performance web backends.",
            practicalUses = "• PostgreSQL and MySQL database connection pools (HikariCP, PgBouncer); HTTP client connection managers.",
            examples = "Example: An Android OkHttpClient keeps a connection pool of up to 5 idle connections per host for 5 minutes.",
            referenceUrl = "https://en.wikipedia.org/wiki/Connection_pool"
        ),
        WordEntity(
            term = "Multiplexing",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Sending multiple independent data streams or requests simultaneously over a single shared physical or network connection without mixing them up.",
            keyPoints = "• Core breakthrough of HTTP/2 and HTTP/3: requests and responses interleave in binary frames.\n• Eliminates the need for browsers to open 6 separate TCP connections per domain.\n• Greatly improves page load speed on high-latency mobile networks.",
            practicalUses = "• HTTP/2 web serving, gRPC microservice streaming, fiber optic wave-division multiplexing.",
            examples = "Example: In HTTP/2, 50 API calls travel concurrently inside one single TCP stream.",
            referenceUrl = "https://en.wikipedia.org/wiki/Multiplexing"
        ),
        WordEntity(
            term = "Head-of-line blocking",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A performance bottleneck where a single delayed or dropped packet at the front of a line stalls all subsequent packets waiting behind it.",
            keyPoints = "• In HTTP/1.1, one slow image response blocks subsequent requests on that connection.\n• In TCP-based HTTP/2, a single dropped packet stalls all multiplexed streams until retransmitted.\n• Solved by QUIC and HTTP/3 by implementing independent streams over UDP.",
            practicalUses = "• Understanding why tech companies migrated to HTTP/3 and QUIC for mobile apps.",
            examples = "Example: Like a customer writing a check at a grocery checkout line holding up 10 people behind them.",
            referenceUrl = "https://en.wikipedia.org/wiki/Head-of-line_blocking"
        ),
        WordEntity(
            term = "QUIC",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Quick UDP Internet Connections: Google's modern transport protocol built on top of UDP that replaces TCP, bringing faster handshakes and zero head-of-line blocking.",
            keyPoints = "• Powers HTTP/3.\n• Combines transport and TLS 1.3 encryption in 0-RTT or 1-RTT connection setup.\n• Handles Wi-Fi to cellular mobile network handoffs seamlessly without dropping sessions.",
            practicalUses = "• Accelerated browsing on Chrome, YouTube streaming, mobile app API connections.",
            examples = "Example: When your phone leaves home Wi-Fi and switches to 5G, QUIC maintains your YouTube video without restarting.",
            referenceUrl = "https://en.wikipedia.org/wiki/QUIC"
        ),

        // Web and API protocols (18)
        WordEntity(
            term = "HTTP/1.1",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The venerable plaintext web protocol introduced in 1997 that standardized persistent Keep-Alive connections and chunked transfer encoding.",
            keyPoints = "• Uses plaintext headers and requests (e.g. GET /index.html HTTP/1.1).\n• Suffers from Head-of-line blocking: only one request/response per connection at a time.\n• Standardized caching headers like ETag and Cache-Control.",
            practicalUses = "• Legacy web servers, simple IoT devices, basic webhooks.",
            examples = "Example: curl -I --http1.1 https://example.com shows standard plaintext HTTP headers.",
            referenceUrl = "https://en.wikipedia.org/wiki/HTTP/1.1"
        ),
        WordEntity(
            term = "HTTP/2",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A major 2015 upgrade to HTTP that introduced binary framing, full request multiplexing over a single TCP connection, and HPACK header compression.",
            keyPoints = "• Replaced plaintext with binary framing.\n• Allowed dozens of concurrent requests without opening multiple TCP sockets.\n• Introduced server push and stream prioritization.",
            practicalUses = "• Modern web applications, microservices, gRPC transport layer.",
            examples = "Example: Loading a modern web dashboard with 80 icons and assets in a fraction of a second over 1 connection.",
            referenceUrl = "https://en.wikipedia.org/wiki/HTTP/2"
        ),
        WordEntity(
            term = "HTTP/3",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The newest iteration of HTTP that runs over QUIC and UDP instead of TCP, eliminating TCP head-of-line blocking and accelerating mobile performance.",
            keyPoints = "• Replaces TCP with QUIC (built on UDP).\n• Built-in TLS 1.3 encryption by default.\n• Dramatically faster connection establishment (0-RTT reconnection).",
            practicalUses = "• Global web traffic on Cloudflare, Google, Facebook, and modern mobile apps.",
            examples = "Example: Opening google.com in modern Chrome loads over HTTP/3 (protocol h3).",
            referenceUrl = "https://en.wikipedia.org/wiki/HTTP/3"
        ),
        WordEntity(
            term = "REST",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Representational State Transfer: an architectural design pattern for web APIs that models data as resources accessed via standard HTTP verbs (GET, POST, PUT, DELETE).",
            keyPoints = "• Stateless client-server communication.\n• Resources identified by clean URLs (e.g. /api/users/42).\n• Standard JSON payloads and HTTP status codes.",
            practicalUses = "• 90%+ of modern public cloud and SaaS web APIs (Stripe, GitHub, OpenRouter).",
            examples = "Example: GET /v1/models returns a list of available AI models in JSON.",
            referenceUrl = "https://en.wikipedia.org/wiki/REST"
        ),
        WordEntity(
            term = "GraphQL",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A query language for APIs created by Meta that allows clients to request exactly the fields they need in a single round-trip, avoiding over-fetching.",
            keyPoints = "• Single endpoint (typically /graphql) handling POST requests.\n• Strongly typed schema defining queries, mutations, and subscriptions.\n• Client specifies exact nested response shape.",
            practicalUses = "• Complex mobile applications, GitHub GraphQL API, Shopify storefronts.",
            examples = "Example: query { user(id: 1) { name email posts { title } } } returns only requested fields.",
            referenceUrl = "https://en.wikipedia.org/wiki/GraphQL"
        ),
        WordEntity(
            term = "gRPC",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Google Remote Procedure Call: a high-performance open-source RPC framework that uses Protocol Buffers and HTTP/2 for ultra-fast microservice communication.",
            keyPoints = "• Compact binary serialization (Protobuf) instead of bulky JSON text.\n• Native bi-directional streaming.\n• Automatic client and server code generation across 10+ programming languages.",
            practicalUses = "• Internal backend microservice-to-microservice communication in Kubernetes clusters.",
            examples = "Example: A user authentication service communicating with a billing service via gRPC.",
            referenceUrl = "https://en.wikipedia.org/wiki/GRPC"
        ),
        WordEntity(
            term = "SSE (server-sent events)",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A lightweight HTTP standard allowing a server to push real-time text updates down to a client over a persistent connection, perfect for streaming AI text responses.",
            keyPoints = "• One-way streaming from server to client over standard HTTP.\n• Simple text/event-stream format with automatic client reconnection.\n• Much simpler to deploy than full WebSockets for read-heavy feeds.",
            practicalUses = "• Streaming token-by-token completions from OpenAI, Anthropic, and OpenRouter LLM APIs.",
            examples = "Example: data: {\"choices\": [{\"delta\": {\"content\": \"Hello\"}}]} streaming into your app.",
            referenceUrl = "https://en.wikipedia.org/wiki/Server-sent_events"
        ),
        WordEntity(
            term = "Long polling",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "A technique where a client requests data and the server holds the HTTP connection open until new data arrives before responding, mimicking push notifications.",
            keyPoints = "• Predecessor to WebSockets and SSE.\n• Client immediately sends another request as soon as the previous one responds.\n• Works through strict corporate firewalls that block WebSockets.",
            practicalUses = "• Legacy chat clients, simple push notification fallbacks.",
            examples = "Example: Calling GET /api/messages/poll and the server waits 30 seconds until a new message is posted.",
            referenceUrl = "https://en.wikipedia.org/wiki/Push_technology#Long_polling"
        ),
        WordEntity(
            term = "WebRTC",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Web Real-Time Communication: an open protocol standard that enables peer-to-peer audio, video, and data streaming between browsers without plugins.",
            keyPoints = "• Real-time peer-to-peer connections using UDP, SRTP, and SCTP.\n• Uses STUN/TURN servers to traverse NATs and firewalls.\n• Powers sub-100ms low-latency audio/video calling.",
            practicalUses = "• Google Meet, Discord voice channels, real-time interactive AI voice assistants.",
            examples = "Example: Browser video calls connecting two users directly without proxying video through an origin server.",
            referenceUrl = "https://en.wikipedia.org/wiki/WebRTC"
        ),
        WordEntity(
            term = "Webhooks",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Automated HTTP POST notifications sent from one server to another whenever an event happens (an 'event-driven reverse API').",
            keyPoints = "• Eliminates the need for clients to continuously poll APIs for status changes.\n• Delivers JSON event payloads to a registered webhook URL.\n• Secured with cryptographic HMAC signatures.",
            practicalUses = "• Stripe payment confirmations, GitHub commit pushes triggering CI/CD, Slack bot alerts.",
            examples = "Example: When a customer pays, Stripe immediately sends a POST request with 'payment_intent.succeeded' to your server.",
            referenceUrl = "https://en.wikipedia.org/wiki/Webhook"
        ),
        WordEntity(
            term = "HTTP methods (GET/POST/PUT/DELETE)",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The standard action verbs of HTTP that tell the server what operation to perform on a resource: GET reads, POST creates, PUT replaces, DELETE removes.",
            keyPoints = "• GET is safe and idempotent (should not alter server state).\n• POST creates new resources or executes actions.\n• PUT replaces an entire resource; PATCH modifies selected fields.\n• DELETE removes the target resource.",
            practicalUses = "• Designing clean, intuitive REST APIs.",
            examples = "Example: POST /api/words to add a word; GET /api/words/42 to read it; DELETE /api/words/42 to remove it.",
            referenceUrl = "https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods"
        ),
        WordEntity(
            term = "Status codes",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "3-digit standard response numbers returned by a server indicating whether an HTTP request succeeded, redirected, or encountered an error.",
            keyPoints = "• 2xx Success (200 OK, 201 Created)\n• 3xx Redirection (301 Moved Permanently, 304 Not Modified)\n• 4xx Client Error (400 Bad Request, 401 Unauthorized, 404 Not Found)\n• 5xx Server Error (500 Internal Error, 502 Bad Gateway, 503 Service Unavailable).",
            practicalUses = "• Programmatic error handling in mobile apps and API clients.",
            examples = "Example: Receiving 401 means your API key is invalid; 200 means success.",
            referenceUrl = "https://en.wikipedia.org/wiki/List_of_HTTP_status_codes"
        ),
        WordEntity(
            term = "Headers",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Key-value metadata pairs sent along with HTTP requests and responses to pass authentication tokens, content types, caching rules, and user agents.",
            keyPoints = "• Colon-separated key-value pairs (e.g. 'Authorization: Bearer xyz').\n• Request headers pass client information and credentials.\n• Response headers communicate server capabilities, security policies, and cookies.",
            practicalUses = "• Passing API keys to OpenRouter, enforcing CORS, controlling browser cache duration.",
            examples = "Example: 'Authorization: Bearer sk-or-v1-...' informs the API who is making the call.",
            referenceUrl = "https://en.wikipedia.org/wiki/List_of_HTTP_header_fields"
        ),
        WordEntity(
            term = "Cookies",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Small snippets of state data sent by a website and stored by your browser that are automatically returned with subsequent requests to keep you logged in.",
            keyPoints = "• Stored locally by the browser per domain.\n• Secured using flags: HttpOnly (blocks JavaScript theft), Secure (HTTPS only), SameSite (prevents CSRF).\n• Session cookies expire on browser close; persistent cookies have an expiration date.",
            practicalUses = "• Keeping user login sessions active, remembering shopping carts and site preferences.",
            examples = "Example: 'Set-Cookie: session_id=abc123xyz; Secure; HttpOnly'.",
            referenceUrl = "https://en.wikipedia.org/wiki/HTTP_cookie"
        ),
        WordEntity(
            term = "CORS",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Cross-Origin Resource Sharing: a browser security mechanism that allows or restricts web applications on one domain from requesting resources on another domain.",
            keyPoints = "• Enforced strictly by web browsers to prevent malicious websites from stealing your data.\n• Uses preflight OPTIONS requests to check server permissions.\n• Controlled by response headers like 'Access-Control-Allow-Origin: *'.",
            practicalUses = "• Allowing a frontend hosted on myapp.com to call an API hosted on api.myapp.com.",
            examples = "Example: If a frontend on localhost:3000 calls api.com without proper CORS headers, the browser blocks the response.",
            referenceUrl = "https://en.wikipedia.org/wiki/Cross-origin_resource_sharing"
        ),
        WordEntity(
            term = "URL / URI",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "Uniform Resource Identifier / Locator: the global standardized string address used to name and locate any resource or page on the web.",
            keyPoints = "• URI is the general identifier; URL is a specific URI that also explains how to locate it.\n• Anatomy: scheme (https://), host (api.openrouter.ai), port (:443), path (/v1/chat/completions), query (?key=val), fragment (#top).\n• Must be percent-encoded for special characters.",
            practicalUses = "• Navigating web pages, specifying API endpoints, linking web resources.",
            examples = "Example: https://openrouter.ai/api/v1/chat/completions?model=gemini",
            referenceUrl = "https://en.wikipedia.org/wiki/URL"
        ),
        WordEntity(
            term = "Request body / payload",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "The actual data or content payload carried inside an HTTP POST, PUT, or PATCH request beneath the headers (usually encoded as JSON, XML, or binary).",
            keyPoints = "• Transmits the data being created or processed by the server.\n• Paired with a Content-Type header explaining how to parse it.\n• Can be plain JSON, multipart form data (file uploads), or binary protobufs.",
            practicalUses = "• Sending user login credentials, submitting form data, passing prompt messages to AI APIs.",
            examples = "Example: {\"model\": \"google/gemini-2.5-flash\", \"messages\": [{\"role\": \"user\", \"content\": \"Explain DNS\"}]}",
            referenceUrl = "https://en.wikipedia.org/wiki/HTTP_message_body"
        ),
        WordEntity(
            term = "Content-Type",
            category = "Networking",
            part = "Part 1: Networking",
            humanMeaning = "An HTTP header that specifies the media format (MIME type) of the payload data so the receiving app knows exactly how to decode it.",
            keyPoints = "• Formatted as type/subtype (e.g. application/json, text/html, image/png).\n• Often includes character set encoding (e.g. charset=UTF-8).\n• Essential for parsing JSON APIs and handling file uploads properly.",
            practicalUses = "• Informs Retrofit, OkHttp, or browser whether to parse data as JSON or render HTML.",
            examples = "Example: 'Content-Type: application/json; charset=utf-8'.",
            referenceUrl = "https://en.wikipedia.org/wiki/Media_type"
        )
    )
}
