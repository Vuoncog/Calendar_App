package com.example.taskmanagementapp.ui

import com.example.taskmanagementapp.database.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val repository: Repository
) {

}