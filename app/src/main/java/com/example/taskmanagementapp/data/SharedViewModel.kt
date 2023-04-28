package com.example.taskmanagementapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagementapp.constant.RequestState
import com.example.taskmanagementapp.database.Note
import com.example.taskmanagementapp.database.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _allTaskInDate = MutableStateFlow<RequestState<Note>>(RequestState.Idle)
    val allTaskInDate: StateFlow<RequestState<Note>> = _allTaskInDate


    fun getAllTask() {
        _allTaskInDate.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getSelectedRow(Calendar.getInstance().time).collect(
                    collector = { _allTaskInDate.value = RequestState.Success(it) }
                )
            }
        } catch (e: java.lang.Exception) {
            _allTaskInDate.value = RequestState.Error(e)
        }
    }
}