package com.devjay.writtersdiary.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.databinding.ClientTaskCardBinding

class ClientTaskListAdapter: ListAdapter<ClientTask, ClientTaskListAdapter.ViewHolder>(ClientTaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    // viewHolder
    class ViewHolder (val binding: ClientTaskCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (item: ClientTask){
            binding.clientTask = item
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ClientTaskCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ClientTaskDiffCallback: DiffUtil.ItemCallback<ClientTask>(){
        override fun areItemsTheSame(oldItem: ClientTask, newItem: ClientTask): Boolean {
            return oldItem.taskID == newItem.taskID
        }

        override fun areContentsTheSame(oldItem: ClientTask, newItem: ClientTask): Boolean {
            return oldItem == newItem
        }

    }

}


