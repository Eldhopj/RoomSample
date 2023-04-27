package com.example.roomsample.presentation.view.list

import android.app.AlertDialog
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eldhopj.android_extensions.setOnSafeClickListener
import com.example.roomsample.R
import com.example.roomsample.databinding.FragmentListBinding
import com.example.roomsample.domain.model.User
import com.example.roomsample.presentation.adapters.UserListAdapter
import com.example.roomsample.utils.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel : ListViewModel  by viewModels()

    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter { user ->
            navigateToUpdate(user)
        }
    }

    override fun viewCreated(binding: FragmentListBinding) {
        setHasOptionsMenu(true)
        initRecyclerView(binding)
        binding.floatingActionButton.setOnSafeClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }
        observeUserData()
    }

    private fun observeUserData() {
        viewModel.readAllData.observe(viewLifecycleOwner) { userList ->
            userListAdapter.submitList(userList)
        }
    }

    private fun initRecyclerView(binding: FragmentListBinding) {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            adapter = userListAdapter
        }
    }

    private fun navigateToUpdate(user: User) {
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(user)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteAllUsers()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }
}
