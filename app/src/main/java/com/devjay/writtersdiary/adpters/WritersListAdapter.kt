package com.devjay.writtersdiary.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.databinding.FragmentWritersBinding
import com.devjay.writtersdiary.databinding.WriterCardItemBinding

class WritersListAdapter(val clickListener: WriterListener): ListAdapter<Writer, WritersListAdapter.ViewHolder>(WriterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }

    // viewHolder
    class ViewHolder (val binding: WriterCardItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (item: Writer, clickListener: WriterListener){
            binding.writer = item
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

