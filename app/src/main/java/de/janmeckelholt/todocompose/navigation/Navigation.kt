package de.janmeckelholt.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import de.janmeckelholt.todocompose.navigation.desinations.listComposable
import de.janmeckelholt.todocompose.navigation.desinations.taskComposable
import de.janmeckelholt.todocompose.utils.Constants

@Composable
fun SetupNavigation(navController: NavHostController){
    val screen = remember(navController){
        Screens(navController = navController)
    }
    NavHost(navController = navController, startDestination = Constants.LIST_SCREEN) {
        listComposable(navigateToTaskScreen = screen.task)
        taskComposable(navigateToListScreen = screen.list)
    }
}