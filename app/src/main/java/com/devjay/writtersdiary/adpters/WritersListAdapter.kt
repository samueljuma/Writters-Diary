package com.devjay.writtersdiary.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.databinding.FragmentWritersBinding
import com.devjay.writtersdiary.databinding.WriterCardItemBinding
import com.devjay.writtersdiary.viewmodels.WritersListViewModel

class WritersListAdapter(val clickListener: WriterListener, val  viewModel: WritersListViewModel): ListAdapter<Writer, WritersListAdapter.ViewHolder>(WriterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener,viewModel)
    }

    // viewHolder
    class ViewHolder (val binding: WriterCardItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (item: Writer, clickListener: WriterListener, viewModel: WritersListViewModel){
            binding.writer = item
            binding.viewModel = viewModel
            binding.clickListener =clickListener
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WriterCardItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class WriterDiffCallback: DiffUtil.ItemCallback<Writer>(){
        override fun areItemsTheSame(oldItem: Writer, newItem: Writer): Boolean {
            return oldItem.writerID == newItem.writerID
        }

        override fun areContentsTheSame(oldItem: Writer, newItem: Writer): Boolean {
            return oldItem == newItem
        }

    }

}

class WriterListener (val clickListener: (writerId: Long) -> Unit){
    fun onClick(writer: Writer) = clickListener(writer.writerID)
}

