package de.janmeckelholt.todocompose.navigation.desinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import de.janmeckelholt.todocompose.ui.viewmodels.SharedViewModel
import de.janmeckelholt.todocompose.utils.Action
import de.janmeckelholt.todocompose.utils.Constants



fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action) -> Unit, sharedViewModel: SharedViewModel
){
    composable(Constants.TASK_SCREEN, listOf(navArgument(Constants.TASK_ARGUMENT_KEY){
        type = NavType.IntType
    })){

    }
}