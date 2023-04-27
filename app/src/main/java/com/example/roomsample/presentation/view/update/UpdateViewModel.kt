package com.example.roomsample.presentation.view.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsample.domain.model.User
import com.example.roomsample.domain.repoInterfaces.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val repo: UserRepo,
) : ViewModel() {

    fun updateUser(user: User){
        viewModelScope.launch {
            repo.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch {
            repo.deleteUser(user)
        }
    }

}
