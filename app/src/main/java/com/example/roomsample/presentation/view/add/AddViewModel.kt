package com.example.roomsample.presentation.view.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsample.domain.model.User
import com.example.roomsample.domain.repoInterfaces.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repo: UserRepo,
) : ViewModel() {

    fun addUser(user: User) {
        viewModelScope.launch {
            repo.addUser(user)
        }
    }

}
