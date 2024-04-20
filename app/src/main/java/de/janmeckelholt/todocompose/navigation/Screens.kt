package de.janmeckelholt.todocompose.navigation

import androidx.navigation.NavHostController
import de.janmeckelholt.todocompose.utils.Action
import de.janmeckelholt.todocompose.utils.Constants

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(Constants.LIST_SCREEN) { inclusive = true }
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}