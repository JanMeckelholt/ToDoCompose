package de.janmeckelholt.todocompose.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import de.janmeckelholt.todocompose.data.models.ToDoTask
import de.janmeckelholt.todocompose.utils.Action


@Composable
fun TaskScreen(navigateToListScreen: (Action) -> Unit, selectedTask: ToDoTask?) {
    Scaffold(
        topBar={
            TaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask)
        },
        content = {}
    )
}