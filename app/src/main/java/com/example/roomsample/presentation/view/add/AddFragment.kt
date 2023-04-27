package com.example.roomsample.presentation.view.add

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eldhopj.android_extensions.setOnSafeClickListener
import com.eldhopj.android_extensions.toast
import com.eldhopj.android_extensions.value
import com.example.roomsample.utils.bases.BaseFragment
import com.example.roomsample.databinding.FragmentAddBinding
import com.example.roomsample.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>(FragmentAddBinding::inflate) {

    private val viewModel : AddViewModel by viewModels()

    override fun viewCreated(binding: FragmentAddBinding) {
        binding.addBtn.setOnSafeClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val user = binding?.run {
            val firstName = addFirstNameEt.value
            val lastName = addLastNameEt.value
            val age = addAgeEt.value.toInt()
            User(
                firstName,
                lastName,
                age
            )
        }
        if (user != null) {
            viewModel.addUser(user)
        }
        successDataAddingHandling()
    }

    private fun successDataAddingHandling() {
        toast("Successfully added!")
        findNavController().popBackStack()
    }

}
