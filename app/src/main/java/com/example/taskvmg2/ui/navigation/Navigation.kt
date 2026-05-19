package com.example.taskvmg2.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object TaskList
@Serializable
object TaskAdd
@Serializable
data class TaskDetail(
    val taskId : Int
)