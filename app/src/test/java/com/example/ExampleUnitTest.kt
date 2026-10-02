package com.example

import com.example.data.local.DefaultWordsCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun catalog_loadsCorrectly_andHasComprehensiveWords() {
        val words = DefaultWordsCatalog.getAllCatalogWords()
        assertTrue("Catalog should contain hundreds of terms", words.size >= 250)

        // Verify terms are sorted alphabetically
        for (i in 0 until words.size - 1) {
            val current = words[i].term.lowercase()
            val next = words[i + 1].term.lowercase()
            assertTrue("Words should be alphabetically sorted: $current <= $next", current <= next)
        }

        // Verify key terms exist and have human meaning
        val termsToVerify = listOf("IP", "DNS", "TCP", "UDP", "Claude Code", "Cursor", "MCP", "RAG", "Transformer")
        for (term in termsToVerify) {
            val found = words.find { it.term.equals(term, ignoreCase = true) }
            assertNotNull("Term $term should be present in catalog", found)
            assertTrue("Term $term should have human meaning", found!!.humanMeaning.isNotBlank())
            assertTrue("Term $term should have key points", found.keyPoints.isNotBlank())
        }
    }
}
