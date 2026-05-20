package com.example.taskvmg2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskvmg2.ui.repository.TaskRepository
import com.example.taskvmg2.ui.model.Task

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    var tasks by mutableStateOf(listOf<Task>())
        private set

    var id by mutableStateOf("")
        private set
    var title by mutableStateOf("")
        private set
    var completed by mutableStateOf(false)
        private set

    init {
        loadTask()
    }

    private fun loadTask() {
        tasks = repository.getTasks().toList()
    }

    fun loadTask(taskId: Int?) {
        if (taskId != null) {
            val task = repository.getTaskById(taskId)
            if (task != null) {
                id = task.id.toString()
                title = task.title
                completed = task.completed
            }
        }
    }

    fun toggleTask(id: Int) {
        repository.toggleTask(id)
        loadTask()
    }

    fun clearForm() {
        id = ""
        title = ""
        completed = false
    }

    fun onIdChange(newId: String) {
        id = newId
    }

    fun onTitleChange(newTitle: String) {
        title = newTitle
    }

    fun onCompletedChange(newCompleted: Boolean) {
        completed = newCompleted
    }

    fun addTask(task: Task) {
        repository.addTask(task)
        loadTask()
        clearForm()
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
        loadTask()
    }
}
