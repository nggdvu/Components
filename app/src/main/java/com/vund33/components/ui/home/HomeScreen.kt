package com.vund33.components.ui.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vund33.components.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(
    onNavigateToComponentScreen: () -> Unit = {}
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()

    val viewModel = HomeViewModel()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val primaryTextStyle = MaterialTheme.typography.bodyLarge
    val primaryTextColor = MaterialTheme.colorScheme.onPrimaryContainer
    val normalFontWeight = FontWeight.Normal

    val secondaryTextStyle = MaterialTheme.typography.bodyLarge
    val secondaryTextColor = MaterialTheme.colorScheme.onSecondaryContainer
    val boldFontWeight = FontWeight.Bold

    val languageOptions = listOf("English", "Tiếng Việt")
    val languageCodes = listOf("en", "vi")
    val currentLanguageCode = viewModel.getCurrentLanguageCode(context)
    var languageSelectedIndex by remember {
        mutableIntStateOf(languageCodes.indexOf(currentLanguageCode).coerceAtLeast(0))
    }

    var showAnimation by remember { mutableStateOf(false) }

    val themeOptions = listOf(
        stringResource(R.string.dynamic),
        stringResource(R.string.monochrome),
        stringResource(R.string.android)
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet = true
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
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        onClick = {
                            onNavigateToComponentScreen()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Create,
                                contentDescription = "Settings"
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.componentTitle),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = stringResource(R.string.componentCardDescription),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = normalFontWeight
                                )
                            }
                        }
                    }

                }

                item {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        onClick = {
                            onNavigateToComponentScreen()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PlayArrow,
                                contentDescription = "Animation"
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.animationTitle),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    modifier = Modifier
                                        .basicMarquee(),
                                    text = stringResource(R.string.animationCardDescription),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = normalFontWeight,
                                    maxLines = 1
                                )
                            }
                        }
                    }

                }

                item {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        onClick = {
                            onNavigateToComponentScreen()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Sensors"
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.sensorTitle),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    modifier = Modifier
                                        .basicMarquee(),
                                    text = stringResource(R.string.sensorDescription),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = normalFontWeight,
                                    maxLines = 1
                                )
                            }
                        }
                    }

                }

                item {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        onClick = {
                            onNavigateToComponentScreen()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info"
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.infoTitle),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    modifier = Modifier
                                        .basicMarquee(),
                                    text = stringResource(R.string.infoDescription),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = normalFontWeight,
                                    maxLines = 1
                                )
                            }
                        }
                    }

                }

                item {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        onClick = {
                            onNavigateToComponentScreen()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Build,
                                contentDescription = "Miscellaneous"
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.miscellaneous),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    modifier = Modifier
                                        .basicMarquee(),
                                    text = stringResource(R.string.miscellaneousDescription),
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = normalFontWeight,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }

//    if (showAnimation) {
//        ExpandingCircleAnimation(triggerAnimation = true) {
//            scope.launch {
//                sheetState.hide()
//                viewModel.setLanguage(context, languageCodes[languageSelectedIndex])
//            }
//        }
//    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
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
}

@Composable
fun HomeScreenTexts(
) {
    Column {
        Text(
            text = stringResource(R.string.homeTitle),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayMedium,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.homePlaceholderText),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ExpandingCircleAnimation(
    triggerAnimation: Boolean,
    onAnimationEnd: () -> Unit
) {
    val radius = remember { Animatable(0f) }

    LaunchedEffect(triggerAnimation) {
        if (triggerAnimation) {
            radius.animateTo(
                targetValue = 2000f,
                animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
            )
            onAnimationEnd()
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        drawCircle(
            color = Color.Black,
            radius = radius.value,
            center = Offset(size.width / 2, size.height / 2)
        )
    }
}
