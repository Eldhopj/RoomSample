package com.example.roomsample.presentation.view.update

import android.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eldhopj.android_extensions.setOnSafeClickListener
import com.eldhopj.android_extensions.toast
import com.eldhopj.android_extensions.value
import com.example.roomsample.R
import com.example.roomsample.databinding.FragmentUpdateBinding
import com.example.roomsample.domain.model.Address
import com.example.roomsample.domain.model.ContactDetails
import com.example.roomsample.domain.model.User
import com.example.roomsample.utils.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class UpdateFragment : BaseFragment<FragmentUpdateBinding>(FragmentUpdateBinding::inflate) {

    private val args by navArgs<UpdateFragmentArgs>()

    private val viewModel: UpdateViewModel  by viewModels()

    override fun viewCreated(binding: FragmentUpdateBinding) {
        setValues(binding)
        binding.updateBtn.setOnSafeClickListener {
            updateItem()
        }
    }

    private fun setValues(binding: FragmentUpdateBinding) {
        binding.apply {
            firstNameEt.setText(args.currentUser.firstName)
            lastName.setText(args.currentUser.lastName)
            age.setText(args.currentUser.age.toString())
        }
    }

    private fun updateItem() {
        val user = binding?.run {
            val firstName = firstNameEt.value
            val lastName = lastName.value
            val age = age.value.toInt()
            val phoneNumber = phoneNumber.value
            val email = email.value
            val residentAddress = residentAddress.value
            val streetAddress = streetAddress.value
            val address = Address(residentAddress, streetAddress)
            val contactDetails = ContactDetails(Date(12324309), address, email, phoneNumber)
            User(
                firstName,
                lastName,
                age,
                contactDetails
            )
        }
        if (user != null) {
            viewModel.updateUser(user)
        }
        successDataUpdateHandling()
    }

    private fun successDataUpdateHandling() {
        toast("Updated Successfully!")
        findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}
