package de.janmeckelholt.todocompose.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import de.janmeckelholt.todocompose.data.models.Priority
import de.janmeckelholt.todocompose.data.models.ToDoTask
import de.janmeckelholt.todocompose.ui.theme.LARGE_PADDING
import de.janmeckelholt.todocompose.ui.theme.PRIORITY_INDICATOR_SIZE
import de.janmeckelholt.todocompose.ui.theme.TASK_ITEM_ELEVATION
import de.janmeckelholt.todocompose.utils.RequestState

@Composable
fun ListContent(toDoTasks: RequestState<List<ToDoTask>>, navigateToTaskScreen: (taskId: Int) -> Unit) {
    if (toDoTasks is RequestState.Success){
        if (toDoTasks.data.isEmpty()) {
            EmptyContent()
        } else {
            LazyColumn {
                items(
                    items = toDoTasks.data,
                    key = { todoTask -> todoTask.id },
                ) { todoTask ->
                    TaskItem(toDoTask = todoTask, navigateToTaskScreen = navigateToTaskScreen)


                }
            }
        }
    }

}

@Composable
fun TaskItem(toDoTask: ToDoTask, navigateToTaskScreen: (taskId: Int) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        shadowElevation = TASK_ITEM_ELEVATION,
        onClick = { navigateToTaskScreen(toDoTask.id) }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row() {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), contentAlignment = Alignment.BottomEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .width(PRIORITY_INDICATOR_SIZE)
                            .height(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(color = toDoTask.priority.color)
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(toDoTask = ToDoTask(2, "title", "desc", Priority.MEDIUM)) {

    }
}

@Composable
@Preview
fun ListContentPreview() {
    ListContent(
        toDoTasks = RequestState.Success(listOf(
            ToDoTask(2, "title", "desc", Priority.MEDIUM),
            ToDoTask(3, "title3", "desc3", Priority.HIGH)
        )
    )) {

    }
}