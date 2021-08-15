package com.devjay.writtersdiary.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.databinding.ClientTaskCardBinding

class ClientTaskListAdapter(val clickListener: ClientTaskListener): ListAdapter<ClientTask, ClientTaskListAdapter.ViewHolder>(ClientTaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    // viewHolder
    class ViewHolder (val binding: ClientTaskCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (item: ClientTask, clickListener: ClientTaskListener){
            binding.clientTask = item
            binding.clickListener = clickListener
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

class ClientTaskListener (val clickListener: (clientTaskId: Long) -> Unit){
    fun onClick(clientTask: ClientTask) = clickListener(clientTask.taskID)
}


