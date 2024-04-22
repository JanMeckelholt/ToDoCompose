package de.janmeckelholt.todocompose.navigation.desinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import de.janmeckelholt.todocompose.ui.screens.list.ListScreen
import de.janmeckelholt.todocompose.ui.viewmodels.SharedViewModel
import de.janmeckelholt.todocompose.utils.Constants

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen : (Int) -> Unit, sharedViewModel: SharedViewModel
){
    composable(Constants.LIST_SCREEN, listOf(navArgument(Constants.LIST_ARGUMENT_KEY){
        type = NavType.StringType
    })){
        ListScreen(navigateToTaskScreen = navigateToTaskScreen, sharedViewModel)
    }
}