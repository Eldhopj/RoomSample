package com.example.roomsample.presentation.view.add

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eldhopj.android_extensions.setOnSafeClickListener
import com.eldhopj.android_extensions.toast
import com.eldhopj.android_extensions.value
import com.example.roomsample.databinding.FragmentAddBinding
import com.example.roomsample.domain.model.Address
import com.example.roomsample.domain.model.ContactDetails
import com.example.roomsample.domain.model.User
import com.example.roomsample.utils.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

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
            val firstName = firstNameEt.value
            val lastName = lastName.value
            val age = age.value.toInt()
            val phoneNumber = phoneNumber.value
            val email = email.value
            val residentAddress = residentAddress.value
            val streetAddress = streetAddress.value
            val address = Address(residentAddress, streetAddress)
            val contactDetails = ContactDetails(Date(12), address, email, phoneNumber)
            User(
                firstName,
                lastName,
                age,
                contactDetails
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
