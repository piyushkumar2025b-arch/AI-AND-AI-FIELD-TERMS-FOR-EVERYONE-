package com.example.data.knowledge

enum class AiMode(val id: String, val title: String, val iconDescription: String, val subtitle: String) {
    DEEP_ANALOGY(
        id = "ANALOGY",
        title = "Intuitive Analogy",
        iconDescription = "Analogy",
        subtitle = "Memorable real-life comparison and simple mental model"
    ),
    CODE_LAB(
        id = "CODE",
        title = "Code & CLI Lab",
        iconDescription = "Code",
        subtitle = "Hands-on implementation snippet, configuration, or CLI command"
    ),
    STAFF_ENGINEER(
        id = "STAFF",
        title = "Staff Architecture",
        iconDescription = "Architecture",
        subtitle = "Production trade-offs, bottleneck analysis & failure modes"
    ),
    INTERVIEW_QUIZ(
        id = "QUIZ",
        title = "Interview Quiz",
        iconDescription = "Quiz",
        subtitle = "Common interview questions and high-scoring model answers"
    ),
    CUSTOM_QNA(
        id = "QNA",
        title = "Ask Custom AI",
        iconDescription = "Question",
        subtitle = "Ask any specific question or scenario about this concept"
    )
}
