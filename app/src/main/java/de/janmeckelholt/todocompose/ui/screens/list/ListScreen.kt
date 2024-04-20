package de.janmeckelholt.todocompose.ui.screens.list


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import de.janmeckelholt.todocompose.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun ListScreen(navigateToTaskScreen: (taskId: Int) -> Unit){
    Timber.i("ListScreen")
    Scaffold(
        topBar = {
            ListAppBar()
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

@Composable
@Preview
private fun ListScreenPreview(){
    ListScreen {}
}