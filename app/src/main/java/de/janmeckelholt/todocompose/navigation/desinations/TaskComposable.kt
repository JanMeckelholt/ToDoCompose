package de.janmeckelholt.todocompose.navigation.desinations

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import de.janmeckelholt.todocompose.ui.screens.task.TaskScreen
import de.janmeckelholt.todocompose.ui.viewmodels.SharedViewModel
import de.janmeckelholt.todocompose.utils.Action
import de.janmeckelholt.todocompose.utils.Constants


fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action) -> Unit, sharedViewModel: SharedViewModel
){
    composable(Constants.TASK_SCREEN, listOf(navArgument(Constants.TASK_ARGUMENT_KEY){
        type = NavType.IntType
    })){ navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(Constants.TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        TaskScreen(navigateToListScreen = navigateToListScreen, selectedTask)

    }
}