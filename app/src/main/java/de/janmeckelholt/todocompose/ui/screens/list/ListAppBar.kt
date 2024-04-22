package de.janmeckelholt.todocompose.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import de.janmeckelholt.todocompose.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.janmeckelholt.todocompose.data.models.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import de.janmeckelholt.todocompose.components.PriorityItem
import de.janmeckelholt.todocompose.ui.theme.LARGE_PADDING
import de.janmeckelholt.todocompose.ui.viewmodels.SharedViewModel
import de.janmeckelholt.todocompose.utils.Constants
import de.janmeckelholt.todocompose.utils.SearchAppBarState
import de.janmeckelholt.todocompose.utils.TrailingIconState


@Composable
fun ListAppBar(sharedViewModel: SharedViewModel, searchAppBarState: SearchAppBarState, searchTextState: String) {
    var trailingIconState : TrailingIconState = TrailingIconState.READY_TO_DELETE
    if (searchTextState.isEmpty()) {
        trailingIconState = TrailingIconState.READY_TO_CLOSE
    }
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> DefaultListAppBar(
            onSearchClicked = {
                sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
            },
            onSortClicked = {},
            onDeleteClicked = {}
        )

        else -> SearchAppBar(
            text = sharedViewModel.searchTextState.value,
            onTextChange = {
                sharedViewModel.searchTextState.value = it
            },
            onCloseOrClearClicked = {
                when (trailingIconState) {
                    TrailingIconState.READY_TO_CLOSE -> sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    else -> sharedViewModel.searchTextState.value = ""
                }
            },
            onSearchClicked = {}
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteClicked: () -> Unit) {
    TopAppBar(title = { Text("Tasks") }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer), actions = {
        ListAppBarActions(onSearchClicked, onSortClicked, onDeleteClicked)
    })
}

@Composable
fun ListAppBarActions(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteClicked: () -> Unit) {
    SearchAction(onSearchClicked)
    SortAction(onSortClicked)
    DeleteAllAction(onDeleteClicked)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(id = R.string.search_icon_desc))
    }
}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(painter = painterResource(id = R.drawable.ic_filter_list), contentDescription = stringResource(id = R.string.sort_icon_desc))
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(text = { PriorityItem(priority = Priority.LOW) }, onClick = {
            expanded = false
            onSortClicked(Priority.LOW)
        })
        DropdownMenuItem(text = { PriorityItem(priority = Priority.HIGH) }, onClick = {
            expanded = false
            onSortClicked(Priority.HIGH)
        })
        DropdownMenuItem(text = { PriorityItem(priority = Priority.NONE) }, onClick = {
            expanded = false
            onSortClicked(Priority.NONE)
        })
    }
}

@Composable
fun DeleteAllAction(onDeleteClicked: () -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(painter = painterResource(id = R.drawable.ic_vertical_menu), contentDescription = stringResource(id = R.string.vertical_menu_icon_desc))
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(modifier = Modifier.padding(start = LARGE_PADDING), text = { Text(text = stringResource(id = R.string.delete_all)) }, onClick = {
            expanded = false
            onDeleteClicked()
        })
    }
}


@Composable
fun SearchAppBar(text: String, onTextChange: (String) -> Unit, onCloseOrClearClicked: () -> Unit, onSearchClicked: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(Constants.TOP_APP_BAR_HEIGHT),
        shadowElevation = 8.dp,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = { Text(modifier = Modifier.alpha(ContentAlpha.medium), text = stringResource(id = R.string.search_placeholder)) },
            textStyle = TextStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.disabled), onClick = {}) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(id = R.string.search_icon_desc))
                }
            },
            trailingIcon = {
                IconButton(onClick = { onCloseOrClearClicked() }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = stringResource(id = R.string.close_icon_desc))
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) })
        )
    }


}


@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(onSearchClicked = {}, onSortClicked = {}, onDeleteClicked = {})
}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(text = "", onTextChange = {}, onCloseOrClearClicked = {}, onSearchClicked = {})
}