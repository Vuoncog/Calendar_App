package com.example.taskmanagementapp.data

import androidx.lifecycle.ViewModel
import com.example.taskmanagementapp.database.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

}