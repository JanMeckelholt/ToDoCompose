package de.janmeckelholt.todocompose.ui.screens.list


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import de.janmeckelholt.todocompose.R
import androidx.compose.ui.res.stringResource
import de.janmeckelholt.todocompose.ui.viewmodels.SharedViewModel
import de.janmeckelholt.todocompose.utils.SearchAppBarState
import timber.log.Timber

@Composable
fun ListScreen(navigateToTaskScreen: (taskId: Int) -> Unit, sharedViewModel: SharedViewModel){
    Timber.i("ListScreen")
    val searchAppBarState : SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextSTate : String by sharedViewModel.searchTextState
    Scaffold(
        topBar = {
            ListAppBar(sharedViewModel = sharedViewModel, searchAppBarState = searchAppBarState, searchTextState = searchTextSTate )
        },
        content = {},
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(onFabClicked: (taskId: Int) -> Unit){
    FloatingActionButton(onClick = { onFabClicked(-1) }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.btn_add_desc),
            //tint = Color.Black
        )
    }
}
