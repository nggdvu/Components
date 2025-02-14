package com.vund33.components.ui.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vund33.components.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Catalog(
    val title: String,
    val content: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    onNavigateToComponentScreen: () -> Unit,
    onNavigateToAnimationScreen: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val normalFontWeight = FontWeight.Normal
    val secondaryTextColor = MaterialTheme.colorScheme.onSecondaryContainer
    val componentTitle = stringResource(R.string.componentTitle)
    val animationTitle = stringResource(R.string.animationTitle)
    val sensorTitle = stringResource(R.string.sensorTitle)
    val infoTitle = stringResource(R.string.infoTitle)
    val miscellaneousTitle = stringResource(R.string.miscellaneous)
    val componentContent = stringResource(R.string.componentCardDescription)
    val animationContent = stringResource(R.string.animationCardDescription)
    val sensorContent = stringResource(R.string.sensorDescription)
    val infoContent = stringResource(R.string.infoDescription)
    val miscellaneousContent = stringResource(R.string.miscellaneousDescription)
    val catalog = remember {
        listOf(
            Catalog(
                title = componentTitle,
                content = componentContent,
                icon = Icons.Filled.Create,
                onClick = onNavigateToComponentScreen
            ),
            Catalog(
                title = animationTitle,
                content = animationContent,
                icon = Icons.Filled.PlayArrow,
                onClick = onNavigateToAnimationScreen
            ),
            Catalog(
                title = sensorTitle,
                content = sensorContent,
                icon = Icons.Filled.Settings,
                onClick = onNavigateToComponentScreen
            ),
            Catalog(
                title = infoTitle,
                content = infoContent,
                icon = Icons.Filled.Info,
                onClick = onNavigateToComponentScreen
            ),
            Catalog(
                title = miscellaneousTitle,
                content = miscellaneousContent,
                icon = Icons.Filled.Build,
                onClick = onNavigateToComponentScreen
            )

        )
    }

    Scaffold(
        floatingActionButton = {
            HomeFloatingActionButton(
                onShowBottomSheet = {
                    showBottomSheet = true
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                state = rememberLazyListState(),
            ) {
                item {
                    HomeScreenTexts()
                }
                itemsIndexed(catalog) { _, catalog ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState(
                                    key = catalog.title
                                ),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            .padding(top = 16.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        onClick = {
                            catalog.onClick()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = catalog.icon,
                                contentDescription = "Settings"
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = catalog.title,
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = catalog.content,
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = normalFontWeight,
                                    maxLines = 1,
                                    modifier = Modifier.basicMarquee()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showBottomSheet) {
        HomeBottomSheet(
            onHideBottomSheet = {
                showBottomSheet = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(
    onHideBottomSheet: () -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState()
    val viewModel = HomeViewModel()
    val context = LocalContext.current
    val secondaryTextStyle = MaterialTheme.typography.bodyLarge
    val secondaryTextColor = MaterialTheme.colorScheme.onSecondaryContainer
    val boldFontWeight = FontWeight.Bold
    val languageOptions = listOf("English", "Tiếng Việt")
    val scope = rememberCoroutineScope()
    val languageCodes = listOf("en", "vi")
    val currentLanguageCode = viewModel.getCurrentLanguageCode(context)
    var languageSelectedIndex by remember {
        mutableIntStateOf(languageCodes.indexOf(currentLanguageCode).coerceAtLeast(0))
    }
    var showAnimation by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    onHideBottomSheet()
                }
            }
        },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = BottomSheetDefaults.Elevation,
        scrimColor = BottomSheetDefaults.ScrimColor,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.theme),
                style = secondaryTextStyle,
                color = secondaryTextColor,
                fontWeight = boldFontWeight
            )

            LazyRow {

            }


            Text(
                text = stringResource(R.string.language),
                style = secondaryTextStyle,
                color = secondaryTextColor,
                fontWeight = boldFontWeight
            )

            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                languageOptions.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = languageOptions.size
                        ),
                        onClick = {
                            languageSelectedIndex = index
                            scope.launch {
                                delay(300)
                                showAnimation = true
                                sheetState.hide()
                                viewModel.setLanguage(
                                    context,
                                    languageCodes[languageSelectedIndex]
                                )
                            }
                        },
                        selected = index == languageSelectedIndex
                    ) {
                        Text(
                            text = label,
                            style = secondaryTextStyle,
                            color = secondaryTextColor,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeFloatingActionButton(
    onShowBottomSheet: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onShowBottomSheet()
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings"
            )
            Text(
                text = stringResource(R.string.customize),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun HomeScreenTexts(
) {
    Column {
        Text(
            text = stringResource(R.string.homeTitle),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayMedium,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.homePlaceholderText),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}