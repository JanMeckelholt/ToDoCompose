package de.janmeckelholt.todocompose.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import de.janmeckelholt.todocompose.R
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import de.janmeckelholt.todocompose.data.models.Priority
import de.janmeckelholt.todocompose.data.models.ToDoTask
import de.janmeckelholt.todocompose.utils.Action

@Composable
fun TaskAppBar(navigateToListScreen: (Action) -> Unit, selectedTask: ToDoTask?) {
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTaskAppBar(task = selectedTask, navigateToListScreen = navigateToListScreen)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = { BackAction(navigateToListScreen) },
        title = {
            Text(text = stringResource(id = R.string.add_task))
        },
        actions = { AddAction(onAddClicked = navigateToListScreen) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )

}

@Composable
fun BackAction(onBackClicked: (Action) -> Unit) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(id = R.string.back_icon_desc))
    }
}

@Composable
fun AddAction(onAddClicked: (Action) -> Unit) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(imageVector = Icons.Filled.Check, contentDescription = stringResource(id = R.string.add_icon_desc))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(task: ToDoTask, navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = { CloseAction(navigateToListScreen) },
        title = {
            Text(text = task.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        actions = {
            DeleteAction(navigateToListScreen)
            UpdateAction(navigateToListScreen)
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )

}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.Filled.Close, contentDescription = stringResource(id = R.string.close_icon_desc))
    }
}

@Composable
fun DeleteAction(onDeleteClicked: (Action) -> Unit) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
        Icon(imageVector = Icons.Filled.Delete, contentDescription = stringResource(id = R.string.delete_icon_desc))
    }
}

@Composable
fun UpdateAction(onUpdateClicked: (Action) -> Unit) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(imageVector = Icons.Filled.Check, contentDescription = stringResource(id = R.string.update_icon_desc))
    }
}

@Composable
@Preview
private fun NewTaskAppBarPreview() {
    NewTaskAppBar({})
}

@Composable
@Preview
private fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(ToDoTask(id = 3, description = "desc3", title = "title3", priority = Priority.HIGH), {})
}