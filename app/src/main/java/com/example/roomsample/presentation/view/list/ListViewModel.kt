package com.example.roomsample.presentation.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomsample.domain.model.User
import com.example.roomsample.domain.repoInterfaces.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repo: UserRepo,
) : ViewModel() {

    fun deleteAllUsers(){
        viewModelScope.launch {
            repo.deleteAllUsers()
        }
    }

    val readAllData: LiveData<List<User>> = repo.readAllData.asLiveData()

}
