package com.example.taskvmg2.ui.repository

import com.example.taskvmg2.ui.model.Task

class TaskRepository {
    companion object {
        private val tasks = mutableListOf<Task>(
            Task(1, "Task 1", false),
            Task(2, "Task 2", true),
            Task(3, "Task 3", false),
            Task(4, "Task 4", true),
            Task(5, "Task 5", false)
        )
    }

    // 🔹 Obtener todas las tareas
    fun getTasks(): List<Task> = tasks

    // 🔹 Obtener una tarea específica por ID
    fun getTaskById(id: Int): Task? = tasks.find { it.id == id }

    // 🔹 Agregar nueva tarea
    fun addTask(task: Task) = tasks.add(task)

    // 🔹 Eliminar tarea
    fun removeTask(task: Task) = tasks.remove(task)

    // 🔹 Actualizar tarea existente
    fun updateTask(updatedTask: Task) {
        val index = tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            tasks[index] = updatedTask
        }
    }

    // 🔹 Alternar estado de completado
    fun toggleTask(id: Int) {
        val index = tasks.indexOfFirst { it.id == id }
        if (index != -1) {
            val task = tasks[index]
            tasks[index] = task.copy(completed = !task.completed)
        }
    }
}
