package com.devjay.writtersdiary.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.databinding.WriterTaskCardBinding

class WriterTaskListAdapter(val clickListener: WriterTaskListener): ListAdapter<WriterTask, WriterTaskListAdapter.ViewHolder>(WriterTaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }

    // viewHolder
    class ViewHolder (val binding: WriterTaskCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (item: WriterTask, clickListener: WriterTaskListener){
            binding.writerTask = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WriterTaskCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class WriterTaskDiffCallback: DiffUtil.ItemCallback<WriterTask>(){
        override fun areItemsTheSame(oldItem: WriterTask, newItem: WriterTask): Boolean {
            return oldItem.taskID == newItem.taskID
        }

        override fun areContentsTheSame(oldItem: WriterTask, newItem: WriterTask): Boolean {
            return oldItem == newItem
        }

    }

}

class WriterTaskListener (val clickListener: (writerTaskId: Long) -> Unit){
    fun onClick(writerTask: WriterTask) = clickListener(writerTask.taskID)
}


