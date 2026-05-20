package com.example.taskvmg2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskvmg2.ui.model.Task
import com.example.taskvmg2.ui.viewmodel.TaskViewModel

@Composable
fun TaskAddScreen(
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {
    // Limpiar el formulario al entrar
    LaunchedEffect(Unit) {
        viewModel.clearForm()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Registrar Nueva Tarea",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Campo ID
                OutlinedTextField(
                    value = viewModel.id,
                    onValueChange = { viewModel.onIdChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("ID") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Tag,
                            contentDescription = null
                        )
                    },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Título
                OutlinedTextField(
                    value = viewModel.title,
                    onValueChange = { viewModel.onTitleChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Título") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Checkbox para marcar completada
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = viewModel.completed,
                        onCheckedChange = { viewModel.onCompletedChange(it) }
                    )
                    Text("¿Completada?")
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cancelar")
                    }

                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            val taskId = viewModel.id.toIntOrNull() ?: 0
                            viewModel.addTask(
                                Task(
                                    id = taskId,
                                    title = viewModel.title,
                                    completed = viewModel.completed
                                )
                            )
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Registrar")
                    }
                }
            }
        }
    }
}
