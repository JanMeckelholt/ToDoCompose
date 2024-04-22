package de.janmeckelholt.todocompose.utils

import androidx.compose.ui.unit.dp

object Constants {
    const val DATABASE_TABLE = "todo_table"
    const val DATABASE_NAME = "todo_database"
    const val LIST_SCREEN = "list/{action}"
    const val TASK_SCREEN = "task/{taskId}"

    const val LIST_ARGUMENT_KEY = "action"
    const val TASK_ARGUMENT_KEY = "taskId"

    val TOP_APP_BAR_HEIGHT = 56.dp
}