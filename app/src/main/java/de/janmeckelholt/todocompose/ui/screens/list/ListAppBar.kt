package de.janmeckelholt.todocompose.ui.screens.list

import de.janmeckelholt.todocompose.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import de.janmeckelholt.todocompose.components.PriorityItem


@Composable
fun ListAppBar() {
    DefaultListAppBar(onSearchClicked = {}, onSortClicked = {})
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit) {
    TopAppBar(
        title = { Text("Tasks") },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        actions = {
            ListAppBarActions(onSearchClicked, onSortClicked)
        }
    )
}

@Composable
fun ListAppBarActions(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit) {
    SearchAction(onSearchClicked)
    SortAction(onSortClicked)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(id = R.string.action_search_desc))
    }
}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(painter = painterResource(id = R.drawable.ic_filter_list), contentDescription = stringResource(id = R.string.action_sort_desc))
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.LOW) },
            onClick = {
                expanded = false
                onSortClicked(Priority.LOW)
            }
        )
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.MEDIUM) },
            onClick = {
                expanded = false
                onSortClicked(Priority.MEDIUM)
            }
        )
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.HIGH) },
            onClick = {
                expanded = false
                onSortClicked(Priority.HIGH)
            }
        )
    }
}


@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar({},{})
}