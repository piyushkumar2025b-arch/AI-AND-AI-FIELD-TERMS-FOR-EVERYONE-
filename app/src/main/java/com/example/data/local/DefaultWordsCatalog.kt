package com.example.data.local

object DefaultWordsCatalog {
    fun getAllCatalogWords(): List<WordEntity> {
        val allRaw = mutableListOf<WordEntity>()
        allRaw.addAll(DefaultWordsPart1Networking.getTerms())
        allRaw.addAll(DefaultWordsPart1Extended.getTerms())
        allRaw.addAll(DefaultWordsPart1Advanced.getTerms())
        allRaw.addAll(DefaultWordsPart2Harnesses.getTerms())
        allRaw.addAll(DefaultWordsPart2Tools.getTerms())
        allRaw.addAll(DefaultWordsPart2Infra.getTerms())
        allRaw.addAll(DefaultWordsPart3AtoG.getTerms())
        allRaw.addAll(DefaultWordsPart3CtoG.getTerms())
        allRaw.addAll(DefaultWordsPart3DandE.getTerms())
        allRaw.addAll(DefaultWordsPart3FtoM.getTerms())
        allRaw.addAll(DefaultWordsPart3NtoR.getTerms())
        allRaw.addAll(DefaultWordsPart3StoZ.getTerms())

        // Deduplicate by term (case-insensitive), sorting alphabetically
        val dedupedMap = linkedMapOf<String, WordEntity>()
        for (item in allRaw) {
            val key = item.term.trim().lowercase()
            if (!dedupedMap.containsKey(key)) {
                dedupedMap[key] = item
            } else {
                // If existing entry has less content, replace it
                val existing = dedupedMap[key]!!
                if (item.humanMeaning.length > existing.humanMeaning.length ||
                    item.keyPoints.length > existing.keyPoints.length
                ) {
                    dedupedMap[key] = item
                }
            }
        }

        return dedupedMap.values.sortedBy { it.term.lowercase() }
    }
}
