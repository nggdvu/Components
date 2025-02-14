package com.vund33.components.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vund33.components.R

data class UIComponent(
    val title: String,
    val image: ImageVector,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ComponentScreen(
    onBackClicked: () -> Unit,
    onAppBarCardClicked: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val state = rememberLazyGridState()
    val secondaryTextColor = MaterialTheme.colorScheme.onSecondaryContainer
    val appBar = stringResource(R.string.appBar)
    val badge = stringResource(R.string.badge)
    val bottomSheet = stringResource(R.string.bottomSheet)
    val button = stringResource(R.string.button)
    val card = stringResource(R.string.card)
    val checkbox = stringResource(R.string.checkbox)
    val chip = stringResource(R.string.chip)
    val dialog = stringResource(R.string.dialog)
    val drawer = stringResource(R.string.drawer)
    val datePicker = stringResource(R.string.datePicker)
    val list = stringResource(R.string.list)
    val menu = stringResource(R.string.menu)
    val navigationBar = stringResource(R.string.navigationBar)
    val navigationRail = stringResource(R.string.navigationRail)
    val progressIndicator = stringResource(R.string.progressIndicator)
    val searchBar = stringResource(R.string.searchBar)
    val slider = stringResource(R.string.slider)
    val snackbar = stringResource(R.string.snackbar)
    val switch = stringResource(R.string.switches)
    val tab = stringResource(R.string.tab)
    val textField = stringResource(R.string.textField)
    val timePicker = stringResource(R.string.timePicker)
    val tooltip = stringResource(R.string.tooltip)
    val component = remember {
        listOf(
            UIComponent(
                title = appBar,
                image = Icons.Filled.Create,
                onClick = {
                    onAppBarCardClicked()
                }
            ),
            UIComponent(
                title = badge,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = bottomSheet,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = button,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = card,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = checkbox,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = chip,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = dialog,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = drawer,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = datePicker,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = list,
                image = Icons.Filled.Create,
                onClick = {}
            ),

            UIComponent(
                title = menu,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = navigationBar,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = navigationRail,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = progressIndicator,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = searchBar,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = slider,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = snackbar,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = switch,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = tab,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = textField,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = timePicker,
                image = Icons.Filled.Create,
                onClick = {}
            ),
            UIComponent(
                title = tooltip,
                image = Icons.Filled.Create,
                onClick = {}
            )
        )
    }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .sharedBounds(
                sharedContentState = rememberSharedContentState(
                    key = "UI components"
                ),
                animatedVisibilityScope = animatedVisibilityScope
            ),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.componentTitle),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClicked()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyVerticalGrid(
                    state = state,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    columns = GridCells.Fixed(2)
                ) {
                    itemsIndexed(component) { _, component ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(
                                        key = component.title
                                    ),
                                    animatedVisibilityScope = animatedVisibilityScope
                                ),
                            shape = MaterialTheme.shapes.large,
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            onClick = {
                                component.onClick()
                            }
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Icon(
                                    imageVector = component.image,
                                    contentDescription = "Settings"
                                )
                                Text(
                                    text = component.title,
                                    color = secondaryTextColor,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ComponentItem(
    component: UIComponent,
    animatedVisibilityScope: AnimatedVisibilityScope
) {


}