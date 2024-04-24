package de.janmeckelholt.todocompose.ui.screens.list


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import de.janmeckelholt.todocompose.R
import androidx.compose.ui.res.stringResource
import de.janmeckelholt.todocompose.ui.viewmodels.SharedViewModel
import de.janmeckelholt.todocompose.utils.RequestState
import de.janmeckelholt.todocompose.utils.SearchAppBarState
import timber.log.Timber

@Composable
fun ListScreen(navigateToTaskScreen: (taskId: Int) -> Unit, sharedViewModel: SharedViewModel) {
    Timber.i("ListScreen")
    LaunchedEffect(key1 = true) {
        Timber.i("launchedEffect triggered")
        sharedViewModel.getAllTasks()
    }
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextSTate: String by sharedViewModel.searchTextState
    val allTasks by sharedViewModel.allTasks.collectAsState()
    Timber.i("${((allTasks as? RequestState.Success)?.data?.size ?: 0)} tasks (${allTasks::class.simpleName})")
    Scaffold(
        topBar = {
            ListAppBar(sharedViewModel = sharedViewModel, searchAppBarState = searchAppBarState, searchTextState = searchTextSTate)
        },
        content = {
            Column(Modifier.padding(it)) {
                ListContent(toDoTasks = allTasks, navigateToTaskScreen = navigateToTaskScreen)
            }
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(onFabClicked: (taskId: Int) -> Unit) {
    FloatingActionButton(onClick = { onFabClicked(-1) }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.btn_add_desc),
            //tint = Color.Black
        )
    }
}
