package com.example.roomsample.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsample.databinding.CustomRowBinding
import com.example.roomsample.domain.model.User
import logcat.logcat

class UserListAdapter(private var listener: ((User) -> Unit)) :
    ListAdapter<User, UserListAdapter.UserVH>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        return UserVH(
            CustomRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class UserVH(
        private val binding: CustomRowBinding,
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var item: User? = null
        private val mainView by lazy { binding.root }
        private val id by lazy { binding.idTxt }
        private val firstName by lazy { binding.firstNameTxt }
        private val lastName by lazy { binding.lastNameTxt }
        private val age by lazy { binding.ageTxt }

        init {
            mainView.setOnClickListener(this)
        }

        /**
         * Binding of data happens in here
         *
         * @param item
         */
        internal fun bindData(currentItem: User) {
            this.item = currentItem
            id.text = currentItem.id.toString()
            firstName.text = currentItem.firstName
            lastName.text = currentItem.lastName
            age.text = currentItem.age.toString()
        }

        override fun onClick(v: View?) {
            val position = adapterPosition // Get the index of the view holder
            if (position == RecyclerView.NO_POSITION) { // Makes sure this position is still valid
                return
            }
            item?.let {
                listener.invoke(it)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem

    }
}
