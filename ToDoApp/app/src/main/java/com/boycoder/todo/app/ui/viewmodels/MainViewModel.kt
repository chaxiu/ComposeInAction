package com.boycoder.todo.app.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boycoder.todo.app.data.entity.Task
import com.boycoder.todo.app.data.repo.ToDoRepository
import com.boycoder.todo.app.util.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @Author: zhutao
 * @desc:
 */
class MainViewModel(
    private val repository: ToDoRepository = ToDoRepository()
) : ViewModel() {

    private val currentTaskId: MutableState<Int> = mutableStateOf(0)
    val currentTaskTitle: MutableState<String> = mutableStateOf("")
    val currentTaskDesc: MutableState<String> = mutableStateOf("")
    val isCurrentTaskDone: MutableState<Boolean> = mutableStateOf(false)

    private val _allTasks =
        MutableStateFlow<RequestState<List<Task>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<Task>>> = _allTasks

    private val _currentTask: MutableStateFlow<Task?> = MutableStateFlow(null)
    val currentTask: StateFlow<Task?> = _currentTask

    init {
        loadAllTasks()
    }

    private fun loadAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks
                .onStart { _allTasks.value = RequestState.Loading }
                .catch { _allTasks.value = RequestState.Error(it) }
                .collect {
                    _allTasks.value = RequestState.Success(it)
                }
        }
    }

    fun searchTask(taskId: Int) {
        viewModelScope.launch {
            repository.searchTask(taskId = taskId)
                .catch { _currentTask.value = null }
                .collect { task ->
                _currentTask.value = task
            }
        }
    }

    fun createTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                title = currentTaskTitle.value,
                desc = currentTaskDesc.value,
                isDone = false
            )
            repository.insertTask(task = task)
        }
    }

    fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                id = currentTaskId.value,
                title = currentTaskTitle.value,
                desc = currentTaskDesc.value,
                isDone = isCurrentTaskDone.value
            )
            repository.updateTask(task = task)
        }
    }

    fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                id = currentTaskId.value,
                title = currentTaskTitle.value,
                desc = currentTaskDesc.value,
                isDone = isCurrentTaskDone.value
            )
            repository.deleteTask(task = task)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }

    fun updateCurrentTaskFields(currentTask: Task?) {
        if (currentTask != null) {
            currentTaskId.value = currentTask.id
            currentTaskTitle.value = currentTask.title
            currentTaskDesc.value = currentTask.desc
            isCurrentTaskDone.value = currentTask.isDone
        } else {
            currentTaskId.value = 0
            currentTaskTitle.value = ""
            currentTaskDesc.value = ""
            isCurrentTaskDone.value = false
        }
    }

    fun updateTitle(newTitle: String) {
        currentTaskTitle.value = newTitle
    }

    fun isCurrentTaskValid(): Boolean {
        return currentTaskTitle.value.isNotEmpty() && currentTaskDesc.value.isNotEmpty()
    }
}