package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import com.example.data.knowledge.AiMode
import com.example.data.knowledge.KnowledgeSourceItem
import com.example.data.knowledge.KnowledgeSourceRegistry
import com.example.data.knowledge.KnowledgeSourceType
import com.example.data.network.WikipediaSummary
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.WordEntity
import com.example.data.network.OpenRouterService
import com.example.ui.theme.AiAccent
import com.example.ui.theme.BookmarkGold
import com.example.ui.viewmodel.DictionaryViewModel
import kotlinx.coroutines.launch

@Composable
fun DictionaryScreen(
    viewModel: DictionaryViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val words by viewModel.filteredWords.collectAsState()
    val selectedWord by viewModel.selectedWord.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isSearchVisible by viewModel.isSearchVisible.collectAsState()
    val isTopBarCollapsed by viewModel.isTopBarCollapsed.collectAsState()
    val selectedLetter by viewModel.selectedLetter.collectAsState()
    val selectedFilter by viewModel.selectedFilter.collectAsState()
    val themeMode by viewModel.themeMode.collectAsState()

    val openRouterLoading by viewModel.openRouterLoading.collectAsState()
    val openRouterResult by viewModel.openRouterResult.collectAsState()
    val openRouterError by viewModel.openRouterError.collectAsState()
    val openRouterKey by viewModel.openRouterApiKey.collectAsState()
    val openRouterModel by viewModel.openRouterModel.collectAsState()

    val activeAiMode by viewModel.activeAiMode.collectAsState()
    val customAiQuestion by viewModel.customAiQuestion.collectAsState()
    val isAiKeptSavedSuccess by viewModel.isAiKeptSavedSuccess.collectAsState()
    val autoKeepAiInApp by viewModel.autoKeepAiInApp.collectAsState()
    val wikipediaSummary by viewModel.wikipediaSummary.collectAsState()
    val wikipediaLoading by viewModel.wikipediaLoading.collectAsState()
    val knowledgeSources by viewModel.knowledgeSources.collectAsState()

    val showAddDialog by viewModel.showAddWordDialog.collectAsState()
    val showAiAddDialog by viewModel.showAiAddDialog.collectAsState()
    val isAiGeneratingWord by viewModel.isAiGeneratingWord.collectAsState()
    val aiWordError by viewModel.aiWordError.collectAsState()
    val showSettingsDialog by viewModel.showSettingsDialog.collectAsState()

    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    // Handle system back navigation when a word is selected
    BackHandler(enabled = selectedWord != null) {
        viewModel.selectWord(null)
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding, bottom = bottomPadding)
        ) {
            // Header Bar (Collapsible to give 100% space to dictionary reading)
            AnimatedVisibility(
                visible = !isTopBarCollapsed,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    // Minimal Top Bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Title & Word Count
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { viewModel.selectWord(null) }
                        ) {
                            Text(
                                text = "AI Lexicon",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant
                            ) {
                                Text(
                                    text = "${words.size}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        // Compact Action Buttons (Borderless)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            // Search toggle button
                            IconButton(
                                onClick = { viewModel.toggleSearchVisible() },
                                modifier = Modifier
                                    .size(34.dp)
                                    .testTag("search_toggle_button")
                            ) {
                                Icon(
                                    imageVector = if (isSearchVisible) Icons.Default.Close else Icons.Default.Search,
                                    contentDescription = "Search Words",
                                    tint = if (isSearchVisible) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            // AI Add Word Button (OpenRouter Free Model)
                            IconButton(
                                onClick = { viewModel.openAiAddDialog() },
                                modifier = Modifier
                                    .size(34.dp)
                                    .testTag("ai_add_word_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = "Add Word with AI",
                                    tint = AiAccent,
                                    modifier = Modifier.size(19.dp)
                                )
                            }

                            // Add Word Button (Manual form)
                            IconButton(
                                onClick = { viewModel.openAddWordDialog() },
                                modifier = Modifier
                                    .size(34.dp)
                                    .testTag("add_word_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Word",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            // Settings Button (OpenRouter API config)
                            IconButton(
                                onClick = { viewModel.openSettingsDialog() },
                                modifier = Modifier
                                    .size(34.dp)
                                    .testTag("settings_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "AI Settings",
                                    tint = if (openRouterKey.isNotBlank()) AiAccent else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            // Dark / Light Mode Switch
                            IconButton(
                                onClick = { viewModel.toggleTheme() },
                                modifier = Modifier
                                    .size(34.dp)
                                    .testTag("theme_toggle_button")
                            ) {
                                Icon(
                                    imageVector = if (themeMode == "DARK") Icons.Default.LightMode else Icons.Default.DarkMode,
                                    contentDescription = "Toggle Dark/Light Mode",
                                    tint = if (themeMode == "DARK") BookmarkGold else MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            // Collapse top bar button (to maximize reading area)
                            IconButton(
                                onClick = { viewModel.toggleTopBarCollapsed() },
                                modifier = Modifier
                                    .size(34.dp)
                                    .testTag("collapse_header_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ExpandLess,
                                    contentDescription = "Collapse Header",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    // Compact Search Bar (takes minimal space at the top)
                    AnimatedVisibility(
                        visible = isSearchVisible,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val focusManager = LocalFocusManager.current
                            TextField(
                                value = searchQuery,
                                onValueChange = { viewModel.setSearchQuery(it) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                                    .testTag("search_text_field"),
                                placeholder = {
                                    Text(
                                        text = "Search terms, meanings, points...",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(6.dp),
                                textStyle = MaterialTheme.typography.bodySmall,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                    unfocusedBorderColor = androidx.compose.ui.graphics.Color.Transparent
                                ),
                                trailingIcon = {
                                    if (searchQuery.isNotEmpty()) {
                                        IconButton(
                                            onClick = { viewModel.setSearchQuery("") },
                                            modifier = Modifier.size(28.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Clear,
                                                contentDescription = "Clear Search",
                                                modifier = Modifier.size(14.dp)
                                            )
                                        }
                                    }
                                },
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            // Small Close Button for search
                            IconButton(
                                onClick = { viewModel.closeSearch() },
                                modifier = Modifier.size(34.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close Search",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    // Compact Category Filters Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CategoryFilterChip(
                            label = "All",
                            isSelected = selectedFilter == "ALL",
                            onClick = { viewModel.selectFilter("ALL") }
                        )
                        CategoryFilterChip(
                            label = "⭐ Important",
                            isSelected = selectedFilter == "IMPORTANT",
                            onClick = { viewModel.selectFilter("IMPORTANT") }
                        )
                        CategoryFilterChip(
                            label = "🤖 AI Kept",
                            isSelected = selectedFilter == "AI_KEPT",
                            onClick = { viewModel.selectFilter("AI_KEPT") }
                        )
                        CategoryFilterChip(
                            label = "🟢 Basic",
                            isSelected = selectedFilter == "LEVEL_BASIC",
                            onClick = { viewModel.selectFilter("LEVEL_BASIC") }
                        )
                        CategoryFilterChip(
                            label = "🟡 Intermediate",
                            isSelected = selectedFilter == "LEVEL_INTERMEDIATE",
                            onClick = { viewModel.selectFilter("LEVEL_INTERMEDIATE") }
                        )
                        CategoryFilterChip(
                            label = "🟣 Advanced",
                            isSelected = selectedFilter == "LEVEL_ADVANCED",
                            onClick = { viewModel.selectFilter("LEVEL_ADVANCED") }
                        )
                        CategoryFilterChip(
                            label = "Networking",
                            isSelected = selectedFilter == "NETWORKING",
                            onClick = { viewModel.selectFilter("NETWORKING") }
                        )
                        CategoryFilterChip(
                            label = "Harnesses & Tools",
                            isSelected = selectedFilter == "HARNESSES",
                            onClick = { viewModel.selectFilter("HARNESSES") }
                        )
                        CategoryFilterChip(
                            label = "A-Z Master",
                            isSelected = selectedFilter == "MASTER_LIST",
                            onClick = { viewModel.selectFilter("MASTER_LIST") }
                        )
                        CategoryFilterChip(
                            label = "★ Saved",
                            isSelected = selectedFilter == "BOOKMARKED",
                            onClick = { viewModel.selectFilter("BOOKMARKED") }
                        )
                        CategoryFilterChip(
                            label = "Custom",
                            isSelected = selectedFilter == "CUSTOM",
                            onClick = { viewModel.selectFilter("CUSTOM") }
                        )
                    }

                    // Compact A to Z Quick Jump Strip
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 12.dp, vertical = 3.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val alphabet = ('A'..'Z').map { it.toString() }
                        for (letter in alphabet) {
                            val isSelected = selectedLetter == letter
                            Surface(
                                shape = RoundedCornerShape(3.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                modifier = Modifier
                                    .clickable { viewModel.selectLetter(letter) }
                                    .testTag("alphabet_jump_$letter")
                            ) {
                                Text(
                                    text = letter,
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }

                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        thickness = 0.5.dp
                    )
                }
            }

            // Minimal banner when top bar is collapsed (allows 1-tap restore)
            if (isTopBarCollapsed) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedWord?.term ?: "AI Lexicon",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { viewModel.toggleTopBarCollapsed() },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ExpandMore,
                                contentDescription = "Expand Header",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
                HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.surfaceVariant)
            }

            // Main Content Area: Word List or Word Meaning Detail View
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                if (selectedWord == null) {
                    // Alphabetical Word List
                    WordListView(
                        words = words,
                        onWordClick = { viewModel.selectWord(it) },
                        onBookmarkToggle = { viewModel.toggleBookmark(it) },
                        onImportanceToggle = { viewModel.toggleImportance(it) },
                        onAddWordClick = { viewModel.openAddWordDialog() },
                        onAiAddClick = { viewModel.openAiAddDialog() }
                    )
                } else {
                    // Comprehensive Word Meaning View
                    WordMeaningView(
                        word = selectedWord!!,
                        onBack = { viewModel.selectWord(null) },
                        onBookmarkToggle = { viewModel.toggleBookmark(it) },
                        onImportanceToggle = { viewModel.toggleImportance(it) },
                        onDeleteClick = { viewModel.deleteWord(it) },
                        onAskOpenRouter = { viewModel.askOpenRouter(it) },
                        openRouterLoading = openRouterLoading,
                        openRouterResult = openRouterResult,
                        openRouterError = openRouterError,
                        onClearOpenRouter = { viewModel.clearOpenRouterOutput() },
                        onOpenSettings = { viewModel.openSettingsDialog() },
                        onOpenLink = { url ->
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Cannot open URL: $url", Toast.LENGTH_SHORT).show()
                            }
                        },
                        activeAiMode = activeAiMode,
                        onSelectAiMode = { viewModel.setActiveAiMode(it) },
                        customAiQuestion = customAiQuestion,
                        onCustomAiQuestionChange = { viewModel.setCustomAiQuestion(it) },
                        onAskAiWithMode = { w, mode, q, offline ->
                            viewModel.askAiWithMode(w, mode, q, offline)
                        },
                        onKeepAiInApp = { w, content ->
                            viewModel.keepAiOutputInApp(w, content)
                            Toast.makeText(context, "Saved to App! AI knowledge kept on this word.", Toast.LENGTH_SHORT).show()
                        },
                        onApplyAiAsMain = { w, content ->
                            viewModel.applyAiAsMainDefinition(w, content)
                            Toast.makeText(context, "Main Plain English definition updated!", Toast.LENGTH_SHORT).show()
                        },
                        onClearAiNotes = { w ->
                            viewModel.clearAiNotesFromWord(w)
                            Toast.makeText(context, "AI notes cleared from word", Toast.LENGTH_SHORT).show()
                        },
                        onSaveCustomNotes = { w, notes ->
                            viewModel.saveCustomAiNotes(w, notes)
                        },
                        isAiKeptSavedSuccess = isAiKeptSavedSuccess,
                        autoKeepAiInApp = autoKeepAiInApp,
                        onToggleAutoKeepAi = { viewModel.toggleAutoKeepAi(it) },
                        wikipediaSummary = wikipediaSummary,
                        wikipediaLoading = wikipediaLoading,
                        knowledgeSources = knowledgeSources,
                        onRefreshWikipedia = { viewModel.fetchWikipediaKnowledge(it) },
                        currentModel = openRouterModel
                    )
                }
            }
        }
    }

    // AI Instant Add Word Dialog (OpenRouter Free Model)
    if (showAiAddDialog) {
        AiAddWordDialog(
            isGenerating = isAiGeneratingWord,
            errorMessage = aiWordError,
            currentModel = openRouterModel,
            apiKey = openRouterKey,
            onDismiss = { viewModel.closeAiAddDialog() },
            onGenerateAndAdd = { term ->
                viewModel.generateAndAddWordWithAi(term)
            },
            onOpenSettings = {
                viewModel.closeAiAddDialog()
                viewModel.openSettingsDialog()
            }
        )
    }

    // Add Word Dialog (Manual + AI Auto-Fill)
    if (showAddDialog) {
        AddWordDialog(
            onDismiss = { viewModel.closeAddWordDialog() },
            onAutoFillWithAi = { term, onGenerated, onError ->
                viewModel.generateWordForForm(term, onGenerated, onError)
            },
            onSave = { term, category, meaning, points, uses, examples, link, level, isImportant ->
                viewModel.addCustomWord(term, category, meaning, points, uses, examples, link, level, isImportant)
            }
        )
    }

    // OpenRouter Settings Dialog
    if (showSettingsDialog) {
        OpenRouterSettingsDialog(
            currentApiKey = openRouterKey,
            currentModel = openRouterModel,
            autoKeepAi = autoKeepAiInApp,
            onToggleAutoKeepAi = { viewModel.toggleAutoKeepAi(it) },
            onDismiss = { viewModel.closeSettingsDialog() },
            onSave = { key, model ->
                viewModel.saveSettings(key, model)
            }
        )
    }
}

// Category filter chip with no button outline
@Composable
private fun CategoryFilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
        modifier = Modifier
            .clickable(onClick = onClick)
            .testTag("filter_$label")
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

// Level indicator badge
@Composable
fun LevelBadge(
    level: String,
    modifier: Modifier = Modifier
) {
    val label: String
    val containerColor: Color
    val textColor: Color
    when (level.uppercase()) {
        WordEntity.LEVEL_ADVANCED -> {
            label = "Advanced"
            containerColor = Color(0xFF9333EA).copy(alpha = 0.15f)
            textColor = Color(0xFF9333EA)
        }
        WordEntity.LEVEL_INTERMEDIATE -> {
            label = "Intermediate"
            containerColor = Color(0xFFF59E0B).copy(alpha = 0.18f)
            textColor = Color(0xFFD97706)
        }
        else -> {
            label = "Basic"
            containerColor = Color(0xFF10B981).copy(alpha = 0.15f)
            textColor = Color(0xFF059669)
        }
    }

    Surface(
        shape = RoundedCornerShape(4.dp),
        color = containerColor,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .background(textColor, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = textColor
            )
        }
    }
}

// Important concept badge
@Composable
fun ImportantBadge(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = Color(0xFFEF4444).copy(alpha = 0.15f),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFEF4444),
                modifier = Modifier.size(11.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "Important",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFFEF4444)
            )
        }
    }
}

// AI Kept in App badge
@Composable
fun AiKeptBadge(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = Color(0xFF0EA5E9).copy(alpha = 0.15f),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = Color(0xFF0284C7),
                modifier = Modifier.size(10.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "AI Kept",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF0284C7)
            )
        }
    }
}

// Main Word List in Alphabetical Order
@Composable
private fun WordListView(
    words: List<WordEntity>,
    onWordClick: (WordEntity) -> Unit,
    onBookmarkToggle: (WordEntity) -> Unit,
    onImportanceToggle: (WordEntity) -> Unit,
    onAddWordClick: () -> Unit,
    onAiAddClick: () -> Unit = {}
) {
    if (words.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = "No matching words found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Generate & Add with AI (Free Model)
                    Button(
                        onClick = onAiAddClick,
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = AiAccent),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Add with AI (Free Model)", style = MaterialTheme.typography.labelSmall)
                    }

                    // Add manually
                    FilledTonalButton(
                        onClick = onAddWordClick,
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Add Manually", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    } else {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .testTag("words_lazy_column")
        ) {
            items(
                items = words,
                key = { it.id }
            ) { word ->
                WordListItem(
                    word = word,
                    onClick = { onWordClick(word) },
                    onBookmarkToggle = { onBookmarkToggle(word) },
                    onImportanceToggle = { onImportanceToggle(word) }
                )
                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                )
            }
        }
    }
}

// Single Word Item in the List (Compact, borderless)
@Composable
private fun WordListItem(
    word: WordEntity,
    onClick: () -> Unit,
    onBookmarkToggle: () -> Unit,
    onImportanceToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .testTag("word_item_${word.term}"),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = word.term,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Surface(
                    shape = RoundedCornerShape(3.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = word.category,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                    )
                }
                LevelBadge(level = word.level)
                if (word.isImportant) {
                    ImportantBadge()
                }
                if (word.hasAiNotes || word.savedAiNotes.isNotBlank()) {
                    AiKeptBadge()
                }
            }
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = word.humanMeaning,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Important Star Toggle Button
            IconButton(
                onClick = onImportanceToggle,
                modifier = Modifier
                    .size(32.dp)
                    .testTag("importance_toggle_${word.term}")
            ) {
                Icon(
                    imageVector = if (word.isImportant) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = if (word.isImportant) "Unmark Important" else "Mark Important",
                    tint = if (word.isImportant) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.35f),
                    modifier = Modifier.size(18.dp)
                )
            }

            // Bookmark Toggle Icon Button
            IconButton(
                onClick = onBookmarkToggle,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = if (word.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = if (word.isBookmarked) "Unbookmark" else "Bookmark",
                    tint = if (word.isBookmarked) BookmarkGold else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

// Meaning Detail View (Full Space dedicated to word, multi-source knowledge, and AI assistant)
@Composable
private fun WordMeaningView(
    word: WordEntity,
    onBack: () -> Unit,
    onBookmarkToggle: (WordEntity) -> Unit,
    onImportanceToggle: (WordEntity) -> Unit,
    onDeleteClick: (WordEntity) -> Unit,
    onAskOpenRouter: (WordEntity) -> Unit,
    openRouterLoading: Boolean,
    openRouterResult: String?,
    openRouterError: String?,
    onClearOpenRouter: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenLink: (String) -> Unit,
    activeAiMode: AiMode,
    onSelectAiMode: (AiMode) -> Unit,
    customAiQuestion: String,
    onCustomAiQuestionChange: (String) -> Unit,
    onAskAiWithMode: (word: WordEntity, mode: AiMode, customQuestion: String?, forceOffline: Boolean) -> Unit,
    onKeepAiInApp: (word: WordEntity, aiContent: String) -> Unit,
    onApplyAiAsMain: (word: WordEntity, aiContent: String) -> Unit,
    onClearAiNotes: (word: WordEntity) -> Unit,
    onSaveCustomNotes: (word: WordEntity, notes: String) -> Unit,
    isAiKeptSavedSuccess: Boolean,
    autoKeepAiInApp: Boolean,
    onToggleAutoKeepAi: (Boolean) -> Unit,
    wikipediaSummary: WikipediaSummary?,
    wikipediaLoading: Boolean,
    knowledgeSources: List<KnowledgeSourceItem>,
    onRefreshWikipedia: (WordEntity) -> Unit,
    currentModel: String
) {
    val scrollState = rememberScrollState()
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    var showEditNotesDialog by remember { mutableStateOf(false) }
    var editedNotesText by remember(word.savedAiNotes) { mutableStateOf(word.savedAiNotes) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Sticky Header inside Meaning View (Back button, mini AI helper, Bookmark, Reference)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back/Close Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable(onClick = onBack)
                    .padding(end = 8.dp)
                    .testTag("meaning_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to List",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Words",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Right Mini Buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Mini AI Assistant Trigger Button
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = AiAccent.copy(alpha = 0.15f),
                    modifier = Modifier
                        .clickable(enabled = !openRouterLoading) {
                            onAskAiWithMode(word, activeAiMode, if (activeAiMode == AiMode.CUSTOM_QNA) customAiQuestion else null, false)
                        }
                        .testTag("openrouter_mini_button")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (openRouterLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(12.dp),
                                strokeWidth = 1.5.dp,
                                color = AiAccent
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = AiAccent,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "AI Deepen",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp
                            ),
                            color = AiAccent
                        )
                    }
                }

                // Reference Link Button
                if (word.referenceUrl.isNotBlank()) {
                    IconButton(
                        onClick = { onOpenLink(word.referenceUrl) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = "Open Source Link",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Star / Important Toggle Icon Button
                IconButton(
                    onClick = { onImportanceToggle(word) },
                    modifier = Modifier
                        .size(32.dp)
                        .testTag("detail_importance_toggle")
                ) {
                    Icon(
                        imageVector = if (word.isImportant) Icons.Default.Star else Icons.Default.StarBorder,
                        contentDescription = if (word.isImportant) "Unmark Important" else "Mark Important",
                        tint = if (word.isImportant) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Bookmark Icon Button
                IconButton(
                    onClick = { onBookmarkToggle(word) },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (word.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark Word",
                        tint = if (word.isBookmarked) BookmarkGold else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Delete custom word if created by user
                if (word.isCustom) {
                    IconButton(
                        onClick = { onDeleteClick(word) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Delete Custom Word",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.surfaceVariant)

        // Scrollable Meaning View Area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            // Word Title & Tags
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = word.term,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.testTag("word_title_detail")
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Category, Part, Level, Important & AI Kept Pills
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = word.category,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = word.part,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                    )
                }
                LevelBadge(level = word.level)
                if (word.isImportant) {
                    ImportantBadge()
                }
                if (word.hasAiNotes || word.savedAiNotes.isNotBlank()) {
                    AiKeptBadge()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 1. Human-Explained Meaning
            MeaningSection(
                title = "In Plain English",
                content = word.humanMeaning,
                highlight = true
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 2. Key Points Explained
            if (word.keyPoints.isNotBlank()) {
                MeaningSection(
                    title = "Key Points & Mechanism",
                    content = word.keyPoints
                )
                Spacer(modifier = Modifier.height(14.dp))
            }

            // 3. Practical Uses
            if (word.practicalUses.isNotBlank()) {
                MeaningSection(
                    title = "Real-World Uses",
                    content = word.practicalUses
                )
                Spacer(modifier = Modifier.height(14.dp))
            }

            // 4. Concrete Examples
            if (word.examples.isNotBlank()) {
                MeaningSection(
                    title = "Concrete Examples",
                    content = word.examples,
                    isCodeStyle = true
                )
                Spacer(modifier = Modifier.height(14.dp))
            }

            // 5. SAVED AI KNOWLEDGE NOTEBOOK (KEPT IN APP)
            if (word.savedAiNotes.isNotBlank()) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF0284C7).copy(alpha = 0.08f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF0284C7).copy(alpha = 0.3f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = Color(0xFF0284C7),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "AI Knowledge Notebook (Kept in App)",
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = Color(0xFF0284C7)
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { showEditNotesDialog = true },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = "Edit Notes",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        clipboardManager.setText(AnnotatedString(word.savedAiNotes))
                                        Toast.makeText(context, "Copied AI notebook notes", Toast.LENGTH_SHORT).show()
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ContentCopy,
                                        contentDescription = "Copy Notes",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                                IconButton(
                                    onClick = { onClearAiNotes(word) },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DeleteOutline,
                                        contentDescription = "Clear Notes",
                                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color(0xFF0284C7).copy(alpha = 0.15f),
                            modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
                        ) {
                            Text(
                                text = "✓ Stored Locally in App • Offline Accessible",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.SemiBold),
                                color = Color(0xFF0284C7),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        Text(
                            text = word.savedAiNotes,
                            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
            }

            // 6. MULTI-SOURCE KNOWLEDGE & CITATIONS SECTION
            Text(
                text = "KNOWLEDGE SOURCES & CITATIONS",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    fontSize = 10.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))

            // Wikipedia Live Encyclopedia Card
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.MenuBook,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Wikipedia Encyclopedia",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        if (wikipediaLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(14.dp),
                                strokeWidth = 1.5.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            TextButton(
                                onClick = { onRefreshWikipedia(word) },
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text("Refresh", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }

                    if (wikipediaSummary != null) {
                        if (!wikipediaSummary.description.isNullOrBlank()) {
                            Text(
                                text = wikipediaSummary.description,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        Text(
                            text = wikipediaSummary.extract,
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = { onOpenLink(wikipediaSummary.pageUrl) },
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.OpenInNew,
                                    contentDescription = null,
                                    modifier = Modifier.size(13.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Read on Wikipedia", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    } else if (!wikipediaLoading) {
                        Text(
                            text = "Reference URL: ${word.referenceUrl.ifBlank { "https://en.wikipedia.org/wiki/${word.term.replace(" ", "_")}" }}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        TextButton(
                            onClick = { onRefreshWikipedia(word) },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("Fetch live Wikipedia summary", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Curated Standards, RFCs, and Research Papers
            if (knowledgeSources.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    for (source in knowledgeSources) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onOpenLink(source.url) }
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Surface(
                                            shape = RoundedCornerShape(3.dp),
                                            color = when (source.type) {
                                                KnowledgeSourceType.STANDARD -> Color(0xFF10B981).copy(alpha = 0.15f)
                                                KnowledgeSourceType.RESEARCH_PAPER -> Color(0xFF8B5CF6).copy(alpha = 0.15f)
                                                KnowledgeSourceType.OFFICIAL_DOCS -> Color(0xFF3B82F6).copy(alpha = 0.15f)
                                                else -> MaterialTheme.colorScheme.surfaceVariant
                                            }
                                        ) {
                                            Text(
                                                text = source.type.displayName,
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                color = when (source.type) {
                                                    KnowledgeSourceType.STANDARD -> Color(0xFF059669)
                                                    KnowledgeSourceType.RESEARCH_PAPER -> Color(0xFF7C3AED)
                                                    KnowledgeSourceType.OFFICIAL_DOCS -> Color(0xFF2563EB)
                                                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                                                },
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                            )
                                        }
                                        Text(
                                            text = source.name,
                                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(
                                        text = source.description,
                                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        lineHeight = 15.sp
                                    )
                                    Text(
                                        text = "Citation: ${source.citation}",
                                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Default.OpenInNew,
                                    contentDescription = "Open Source Link",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 7. INTERACTIVE AI ASSISTANT & KNOWLEDGE ENGINE
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                border = androidx.compose.foundation.BorderStroke(1.dp, AiAccent.copy(alpha = 0.25f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = AiAccent,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Interactive AI Knowledge Engine",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Text(
                        text = "Synthesizes intuitive analogies, runnable code, interview quizzes, and architectural breakdowns.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
                    )

                    // Mode Selector Chips
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val modes = AiMode.entries.toTypedArray()
                        for (mode in modes) {
                            val isSelected = activeAiMode == mode
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = if (isSelected) AiAccent else MaterialTheme.colorScheme.surface,
                                modifier = Modifier.clickable { onSelectAiMode(mode) }
                            ) {
                                Text(
                                    text = mode.title,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                    ),
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // If Custom Q&A mode is selected, show question input field
                    if (activeAiMode == AiMode.CUSTOM_QNA) {
                        TextField(
                            value = customAiQuestion,
                            onValueChange = onCustomAiQuestionChange,
                            placeholder = { Text("e.g., How does this compare to WebSocket?", fontSize = 12.sp) },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(6.dp)
                        )
                    }

                    // Generation Action Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                onAskAiWithMode(
                                    word,
                                    activeAiMode,
                                    if (activeAiMode == AiMode.CUSTOM_QNA) customAiQuestion else null,
                                    false
                                )
                            },
                            enabled = !openRouterLoading,
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = AiAccent),
                            modifier = Modifier.weight(1f)
                        ) {
                            if (openRouterLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(14.dp),
                                    strokeWidth = 2.dp,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Thinking...")
                            } else {
                                Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Ask AI")
                            }
                        }

                        FilledTonalButton(
                            onClick = {
                                onAskAiWithMode(
                                    word,
                                    activeAiMode,
                                    if (activeAiMode == AiMode.CUSTOM_QNA) customAiQuestion else null,
                                    true
                                )
                            },
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text("Offline Engine", style = MaterialTheme.typography.labelSmall)
                        }
                    }

                    // Auto-Keep AI Answers in App Toggle Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Auto-keep AI answers in app",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Automatically save all generated knowledge to this word's offline notebook",
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = autoKeepAiInApp,
                            onCheckedChange = onToggleAutoKeepAi,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    // Display AI Output Box
                    if (openRouterLoading || openRouterResult != null || openRouterError != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                if (openRouterLoading) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(16.dp),
                                            strokeWidth = 2.dp,
                                            color = AiAccent
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = "Consulting AI with grounded knowledge sources...",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                } else if (openRouterError != null) {
                                    Column {
                                        Text(
                                            text = openRouterError,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.error
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                            TextButton(
                                                onClick = onOpenSettings,
                                                contentPadding = PaddingValues(0.dp)
                                            ) {
                                                Text("Open Settings", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                                            }
                                            TextButton(
                                                onClick = {
                                                    onAskAiWithMode(
                                                        word,
                                                        activeAiMode,
                                                        if (activeAiMode == AiMode.CUSTOM_QNA) customAiQuestion else null,
                                                        true
                                                    )
                                                },
                                                contentPadding = PaddingValues(0.dp)
                                            ) {
                                                Text("Use Offline Engine", style = MaterialTheme.typography.labelSmall, color = AiAccent)
                                            }
                                        }
                                    }
                                } else if (openRouterResult != null) {
                                    Text(
                                        text = openRouterResult,
                                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))
                                    HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.surfaceVariant)
                                    Spacer(modifier = Modifier.height(8.dp))

                                    // Action Toolbar for AI Output: KEEP IN APP, UPDATE PLAIN ENGLISH, COPY, DISMISS
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        // "KEEP IN APP" Action Button
                                        Button(
                                            onClick = { onKeepAiInApp(word, openRouterResult) },
                                            shape = RoundedCornerShape(6.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = if (isAiKeptSavedSuccess || word.savedAiNotes.contains(openRouterResult)) Color(0xFF059669) else Color(0xFF0284C7)
                                            ),
                                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Icon(
                                                imageVector = if (isAiKeptSavedSuccess || word.savedAiNotes.contains(openRouterResult)) Icons.Default.Check else Icons.Default.AutoAwesome,
                                                contentDescription = null,
                                                modifier = Modifier.size(13.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = if (isAiKeptSavedSuccess || word.savedAiNotes.contains(openRouterResult)) "Kept in App ✓" else "Keep in App",
                                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                                            )
                                        }

                                        // "Replace Plain English" Action Button
                                        FilledTonalButton(
                                            onClick = { onApplyAiAsMain(word, openRouterResult) },
                                            shape = RoundedCornerShape(6.dp),
                                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Text("Set Meaning", style = MaterialTheme.typography.labelSmall)
                                        }

                                        // Copy Button
                                        IconButton(
                                            onClick = {
                                                clipboardManager.setText(AnnotatedString(openRouterResult))
                                                Toast.makeText(context, "Copied AI output", Toast.LENGTH_SHORT).show()
                                            },
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ContentCopy,
                                                contentDescription = "Copy AI response",
                                                modifier = Modifier.size(14.dp)
                                            )
                                        }

                                        // Dismiss Button
                                        IconButton(
                                            onClick = onClearOpenRouter,
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Dismiss AI output",
                                                modifier = Modifier.size(14.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    // Dialog for editing saved AI notes manually
    if (showEditNotesDialog) {
        AlertDialog(
            onDismissRequest = { showEditNotesDialog = false },
            title = { Text("Edit Saved AI Notes", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)) },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Customize or expand the AI knowledge kept in the app for ${word.term}:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = editedNotesText,
                        onValueChange = { editedNotesText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp, max = 280.dp),
                        label = { Text("AI Notes & Personal Insights") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSaveCustomNotes(word, editedNotesText)
                        showEditNotesDialog = false
                    },
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("Save Notes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditNotesDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

// Reusable Meaning Section block
@Composable
private fun MeaningSection(
    title: String,
    content: String,
    highlight: Boolean = false,
    isCodeStyle: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                fontSize = 10.sp
            ),
            color = if (highlight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = if (highlight) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = content,
                style = if (isCodeStyle) {
                    MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily.Monospace,
                        lineHeight = 18.sp
                    )
                } else {
                    MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp)
                },
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
            )
        }
    }
}

// Add Custom Word Dialog (with AI auto-fill capability)
@Composable
private fun AddWordDialog(
    onDismiss: () -> Unit,
    onAutoFillWithAi: ((term: String, onGenerated: (WordEntity) -> Unit, onError: (String) -> Unit) -> Unit)? = null,
    onSave: (term: String, category: String, meaning: String, points: String, uses: String, examples: String, link: String, level: String, isImportant: Boolean) -> Unit
) {
    var term by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var meaning by remember { mutableStateOf("") }
    var points by remember { mutableStateOf("") }
    var uses by remember { mutableStateOf("") }
    var examples by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }
    var level by remember { mutableStateOf(WordEntity.LEVEL_BASIC) }
    var isImportant by remember { mutableStateOf(false) }

    var isAutofilling by remember { mutableStateOf(false) }
    var autofillError by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Add Word to Dictionary",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Words are arranged in alphabetical order and stored offline. Type manually or use AI auto-fill.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                TextField(
                    value = term,
                    onValueChange = {
                        term = it
                        autofillError = null
                    },
                    label = { Text("Word / Term *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("add_word_term_input")
                )

                // AI Auto-Fill helper button
                if (onAutoFillWithAi != null) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = AiAccent.copy(alpha = 0.15f),
                            modifier = Modifier
                                .clickable(enabled = term.isNotBlank() && !isAutofilling) {
                                    isAutofilling = true
                                    autofillError = null
                                    onAutoFillWithAi(
                                        term,
                                        { generated ->
                                            isAutofilling = false
                                            if (category.isBlank()) category = generated.category
                                            meaning = generated.humanMeaning
                                            points = generated.keyPoints
                                            uses = generated.practicalUses
                                            examples = generated.examples
                                            link = generated.referenceUrl
                                            level = generated.level
                                            isImportant = generated.isImportant
                                        },
                                        { error ->
                                            isAutofilling = false
                                            autofillError = error
                                        }
                                    )
                                }
                                .testTag("autofill_with_ai_button")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (isAutofilling) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(12.dp),
                                        strokeWidth = 1.5.dp,
                                        color = AiAccent
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        "Generating...",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = AiAccent
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = AiAccent,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        "Auto-Fill with AI (Free Model)",
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                        color = AiAccent
                                    )
                                }
                            }
                        }
                    }

                    if (autofillError != null) {
                        Text(
                            text = autofillError!!,
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                // Level Selector
                Text(
                    text = "Difficulty Level:",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val levels = listOf(
                        WordEntity.LEVEL_BASIC to "🟢 Basic",
                        WordEntity.LEVEL_INTERMEDIATE to "🟡 Intermediate",
                        WordEntity.LEVEL_ADVANCED to "🟣 Advanced"
                    )
                    for ((lvlCode, lvlLabel) in levels) {
                        val isSelected = level == lvlCode
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .weight(1f)
                                .clickable { level = lvlCode }
                        ) {
                            Text(
                                text = lvlLabel,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 6.dp, horizontal = 4.dp)
                            )
                        }
                    }
                }

                // Important Star Checkbox
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = if (isImportant) Color(0xFFEF4444).copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isImportant = !isImportant }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (isImportant) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = null,
                            tint = if (isImportant) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isImportant) "Marked as Important / Core Concept ⭐" else "Mark as Important Concept",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = if (isImportant) FontWeight.Bold else FontWeight.Normal
                            ),
                            color = if (isImportant) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                TextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category (e.g. AI Core, Networking)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = meaning,
                    onValueChange = { meaning = it },
                    label = { Text("Human Meaning (Plain English) *") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth().testTag("add_word_meaning_input")
                )

                TextField(
                    value = points,
                    onValueChange = { points = it },
                    label = { Text("Key Points (bullet points)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = uses,
                    onValueChange = { uses = it },
                    label = { Text("Practical Uses") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = examples,
                    onValueChange = { examples = it },
                    label = { Text("Examples / Analogy") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = link,
                    onValueChange = { link = it },
                    label = { Text("Reference Link (Wikipedia / Doc URL)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (term.isNotBlank() && meaning.isNotBlank()) {
                        onSave(term, category, meaning, points, uses, examples, link, level, isImportant)
                    }
                },
                enabled = term.isNotBlank() && meaning.isNotBlank(),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.testTag("save_custom_word_button")
            ) {
                Text("Save to Dictionary")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

// AI Instant Add Word Dialog (OpenRouter Free Model)
@Composable
private fun AiAddWordDialog(
    isGenerating: Boolean,
    errorMessage: String?,
    currentModel: String,
    apiKey: String,
    onDismiss: () -> Unit,
    onGenerateAndAdd: (term: String) -> Unit,
    onOpenSettings: () -> Unit
) {
    var termInput by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    AlertDialog(
        onDismissRequest = { if (!isGenerating) onDismiss() },
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = AiAccent,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Add Word with AI",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Enter any term or concept. The free OpenRouter AI model will research, format, and add it with full human meaning, key points, uses, examples, and Wikipedia link in the standard dictionary format.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Model Indicator badge
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = AiAccent.copy(alpha = 0.12f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Model: $currentModel (Free)",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                            color = AiAccent
                        )
                    }
                }

                // Word / Term input field
                TextField(
                    value = termInput,
                    onValueChange = { termInput = it },
                    label = { Text("Word / Term *") },
                    placeholder = { Text("e.g. Speculative Decoding, LoRA, WireGuard") },
                    singleLine = true,
                    enabled = !isGenerating,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            if (termInput.isNotBlank() && !isGenerating) {
                                onGenerateAndAdd(termInput)
                            }
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("ai_add_word_input")
                )

                // Quick suggestions chips
                Text(
                    text = "Quick suggestions:",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val suggestions = listOf("Speculative Decoding", "LoRA", "KV Cache", "BGP", "WireGuard", "Diffusion Models", "Direct Preference Optimization")
                    for (s in suggestions) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                            modifier = Modifier.clickable(enabled = !isGenerating) {
                                termInput = s
                            }
                        ) {
                            Text(
                                text = s,
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                if (isGenerating) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = AiAccent
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Generating structured dictionary entry with OpenRouter free model...",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                if (errorMessage != null) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = errorMessage,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                            if (apiKey.isBlank()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                TextButton(
                                    onClick = onOpenSettings,
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text("Open Settings to enter free API key", style = MaterialTheme.typography.labelSmall, color = AiAccent)
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (termInput.isNotBlank() && !isGenerating) {
                        onGenerateAndAdd(termInput)
                    }
                },
                enabled = termInput.isNotBlank() && !isGenerating,
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AiAccent),
                modifier = Modifier.testTag("submit_ai_add_word_button")
            ) {
                if (isGenerating) {
                    Text("Generating...")
                } else {
                    Text("Generate & Add")
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                enabled = !isGenerating
            ) {
                Text("Cancel")
            }
        }
    )
}

// OpenRouter AI Configuration Dialog (Configured with free models)
@Composable
private fun OpenRouterSettingsDialog(
    currentApiKey: String,
    currentModel: String,
    autoKeepAi: Boolean,
    onToggleAutoKeepAi: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    onSave: (apiKey: String, model: String) -> Unit
) {
    var apiKey by remember { mutableStateOf(currentApiKey) }
    var selectedModel by remember { mutableStateOf(currentModel) }
    val context = LocalContext.current

    val freeModels = OpenRouterService.FREE_MODELS

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = AiAccent,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "OpenRouter AI Settings",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Configure your OpenRouter API key to power AI term deep dives and automatic word generation using 100% free models.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Link to get free key
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = AiAccent.copy(alpha = 0.12f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://openrouter.ai/keys"))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Visit openrouter.ai/keys in your browser", Toast.LENGTH_SHORT).show()
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Get free key at openrouter.ai/keys",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = AiAccent
                        )
                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = null,
                            tint = AiAccent,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                TextField(
                    value = apiKey,
                    onValueChange = { apiKey = it },
                    label = { Text("OpenRouter API Key") },
                    placeholder = { Text("sk-or-v1-...") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("openrouter_key_input")
                )

                // Auto-Keep in App Setting Switch
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Auto-keep AI answers in app",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Keeps analogies, code labs & quiz results permanently in offline dictionary notes",
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = autoKeepAi,
                            onCheckedChange = onToggleAutoKeepAi
                        )
                    }
                }

                Text(
                    text = "Select Free AI Model:",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    for ((modelId, modelLabel) in freeModels) {
                        val isChosen = selectedModel == modelId
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = if (isChosen) AiAccent.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedModel = modelId }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Surface(
                                            shape = RoundedCornerShape(3.dp),
                                            color = AiAccent.copy(alpha = 0.25f)
                                        ) {
                                            Text(
                                                text = "FREE",
                                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold),
                                                color = AiAccent,
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = modelLabel,
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal,
                                                fontSize = 11.sp
                                            ),
                                            color = if (isChosen) AiAccent else MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    Text(
                                        text = modelId,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontFamily = FontFamily.Monospace,
                                            fontSize = 9.5.sp
                                        ),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                if (isChosen) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = AiAccent,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(apiKey, selectedModel) },
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AiAccent),
                modifier = Modifier.testTag("save_settings_button")
            ) {
                Text("Save Settings")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}
