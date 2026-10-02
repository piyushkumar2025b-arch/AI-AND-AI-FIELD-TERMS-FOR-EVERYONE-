package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
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

    val showAddDialog by viewModel.showAddWordDialog.collectAsState()
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

                            // Add Word Button
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
                        onAddWordClick = { viewModel.openAddWordDialog() }
                    )
                } else {
                    // Comprehensive Word Meaning View
                    WordMeaningView(
                        word = selectedWord!!,
                        onBack = { viewModel.selectWord(null) },
                        onBookmarkToggle = { viewModel.toggleBookmark(it) },
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
                        }
                    )
                }
            }
        }
    }

    // Add Word Dialog
    if (showAddDialog) {
        AddWordDialog(
            onDismiss = { viewModel.closeAddWordDialog() },
            onSave = { term, category, meaning, points, uses, examples, link ->
                viewModel.addCustomWord(term, category, meaning, points, uses, examples, link)
            }
        )
    }

    // OpenRouter Settings Dialog
    if (showSettingsDialog) {
        OpenRouterSettingsDialog(
            currentApiKey = openRouterKey,
            currentModel = openRouterModel,
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

// Main Word List in Alphabetical Order
@Composable
private fun WordListView(
    words: List<WordEntity>,
    onWordClick: (WordEntity) -> Unit,
    onBookmarkToggle: (WordEntity) -> Unit,
    onAddWordClick: () -> Unit
) {
    if (words.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "No matching words found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))
                // Borderless button to add custom word
                Button(
                    onClick = onAddWordClick,
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Add Custom Word", style = MaterialTheme.typography.labelSmall)
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
                    onBookmarkToggle = { onBookmarkToggle(word) }
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
    onBookmarkToggle: () -> Unit
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = word.term,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(8.dp))
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

// Meaning Detail View (Full Space dedicated to word and humanized meanings)
@Composable
private fun WordMeaningView(
    word: WordEntity,
    onBack: () -> Unit,
    onBookmarkToggle: (WordEntity) -> Unit,
    onDeleteClick: (WordEntity) -> Unit,
    onAskOpenRouter: (WordEntity) -> Unit,
    openRouterLoading: Boolean,
    openRouterResult: String?,
    openRouterError: String?,
    onClearOpenRouter: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenLink: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

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

            // Right Mini Buttons (No outlines, compact)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Mini OpenRouter AI Helper Button
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = AiAccent.copy(alpha = 0.15f),
                    modifier = Modifier
                        .clickable(enabled = !openRouterLoading) { onAskOpenRouter(word) }
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

                // Reference Link Button (Wikipedia or Source)
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

            // Category & Part Pills
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
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

            // 5. OpenRouter AI Helper Panel (When triggered)
            if (openRouterLoading || openRouterResult != null || openRouterError != null) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
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
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "OpenRouter AI Deep Dive",
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Row {
                                if (openRouterResult != null) {
                                    IconButton(
                                        onClick = {
                                            clipboardManager.setText(AnnotatedString(openRouterResult))
                                            Toast.makeText(context, "Copied AI response", Toast.LENGTH_SHORT).show()
                                        },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ContentCopy,
                                            contentDescription = "Copy AI response",
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }
                                IconButton(
                                    onClick = onClearOpenRouter,
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Dismiss AI output",
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        if (openRouterLoading) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 12.dp)
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    strokeWidth = 2.dp,
                                    color = AiAccent
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Synthesizing deep analogies & nuances via OpenRouter...",
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
                                TextButton(
                                    onClick = onOpenSettings,
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        text = "Open Settings to configure API Key",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        } else if (openRouterResult != null) {
                            Text(
                                text = openRouterResult,
                                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
            }

            // Reference Source Card
            if (word.referenceUrl.isNotBlank()) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenLink(word.referenceUrl) }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Source & Encyclopedia Reference",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = word.referenceUrl,
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
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

// Add Custom Word Dialog
@Composable
private fun AddWordDialog(
    onDismiss: () -> Unit,
    onSave: (term: String, category: String, meaning: String, points: String, uses: String, examples: String, link: String) -> Unit
) {
    var term by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var meaning by remember { mutableStateOf("") }
    var points by remember { mutableStateOf("") }
    var uses by remember { mutableStateOf("") }
    var examples by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }

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
                    text = "Added words are automatically sorted in alphabetical order and stored offline.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                TextField(
                    value = term,
                    onValueChange = { term = it },
                    label = { Text("Word / Term *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("add_word_term_input")
                )

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
                        onSave(term, category, meaning, points, uses, examples, link)
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

// OpenRouter AI Configuration Dialog
@Composable
private fun OpenRouterSettingsDialog(
    currentApiKey: String,
    currentModel: String,
    onDismiss: () -> Unit,
    onSave: (apiKey: String, model: String) -> Unit
) {
    var apiKey by remember { mutableStateOf(currentApiKey) }
    var selectedModel by remember { mutableStateOf(currentModel) }

    val models = listOf(
        "google/gemini-2.5-flash",
        "anthropic/claude-3.5-haiku",
        "openai/gpt-4o-mini",
        "meta-llama/llama-3.3-70b-instruct",
        "deepseek/deepseek-r1-distill-llama-70b"
    )

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
                    text = "Enter your OpenRouter API key to enable the mini AI deep dive button. Your key is stored securely on your phone.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

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

                Text(
                    text = "Select AI Model:",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    for (m in models) {
                        val isChosen = selectedModel == m
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = if (isChosen) AiAccent.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedModel = m }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = m,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 11.sp
                                    ),
                                    color = if (isChosen) AiAccent else MaterialTheme.colorScheme.onSurface
                                )
                                if (isChosen) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = AiAccent,
                                        modifier = Modifier.size(14.dp)
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
