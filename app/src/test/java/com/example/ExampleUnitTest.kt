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

    @Test
    fun catalog_wordsHaveValidLevels_andImportantMarkings() {
        val words = DefaultWordsCatalog.getAllCatalogWords()

        val basicWords = words.filter { it.level == "BASIC" }
        val intermediateWords = words.filter { it.level == "INTERMEDIATE" }
        val advancedWords = words.filter { it.level == "ADVANCED" }
        val importantWords = words.filter { it.isImportant }

        assertTrue("Should contain Basic level words", basicWords.isNotEmpty())
        assertTrue("Should contain Intermediate level words", intermediateWords.isNotEmpty())
        assertTrue("Should contain Advanced level words", advancedWords.isNotEmpty())
        assertTrue("Should contain Important marked words", importantWords.isNotEmpty())

        // Basic important terms
        val ip = words.find { it.term.equals("IP", ignoreCase = true) }
        assertNotNull(ip)
        assertTrue("IP should be marked important", ip!!.isImportant)

        // Intermediate important terms
        val rag = words.find { it.term.equals("RAG", ignoreCase = true) }
        assertNotNull(rag)
        assertTrue("RAG should be marked important", rag!!.isImportant)
        assertTrue("RAG should be Intermediate", rag.level == "INTERMEDIATE")

        // Advanced important terms
        val bgp = words.find { it.term.equals("BGP", ignoreCase = true) }
        assertNotNull("BGP should be present in catalog", bgp)
        assertTrue("BGP should be Advanced", bgp!!.level == "ADVANCED")
        assertTrue("BGP should be marked important", bgp.isImportant)
    }

    @Test
    fun classifyWord_assignsExpectedLevelsAndImportance() {
        val (basicLvl, basicImp) = DefaultWordsCatalog.classifyWord("DNS", "Networking", "Part 1")
        assertEquals("BASIC", basicLvl)
        assertTrue(basicImp)

        val (interLvl, interImp) = DefaultWordsCatalog.classifyWord("Transformer", "AI Core", "Part 1")
        assertEquals("INTERMEDIATE", interLvl)
        assertTrue(interImp)

        val (advLvl, advImp) = DefaultWordsCatalog.classifyWord("Speculative Decoding", "Inference", "Part 1 Advanced")
        assertEquals("ADVANCED", advLvl)
        assertTrue(advImp)
    }
}
