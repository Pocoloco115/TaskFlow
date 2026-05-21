package com.example.taskvmg2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskvmg2.ui.navigation.TaskAdd
import com.example.taskvmg2.ui.navigation.TaskDetail
import com.example.taskvmg2.ui.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(TaskAdd)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar tarea"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Lista de tareas",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (viewModel.tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay tareas registradas")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(viewModel.tasks.size) { index ->
                        val task = viewModel.tasks[index]
                        Card(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth(),
                            onClick = {
                                navController.navigate(TaskDetail(taskId = task.id))
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "ID: ${task.id}",
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                    Text(
                                        text = task.title,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None,
                                            color = if (task.completed)
                                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                            else
                                                MaterialTheme.colorScheme.onSurface
                                        )
                                    )
                                }
                                Checkbox(
                                    checked = task.completed,
                                    onCheckedChange = { viewModel.toggleTask(task.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
