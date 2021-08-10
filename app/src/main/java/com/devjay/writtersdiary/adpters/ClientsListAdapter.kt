package com.devjay.writtersdiary.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devjay.writtersdiary.data.entities.Client
import com.devjay.writtersdiary.databinding.ClientCardItemBinding

class ClientsListAdapter: ListAdapter<Client, ClientsListAdapter.ViewHolder>(ClientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    // viewHolder
    class ViewHolder (val binding: ClientCardItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (item: Client){
            binding.client = item
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ClientCardItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ClientDiffCallback: DiffUtil.ItemCallback<Client>(){
        override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem.clientID == newItem.clientID
        }

        override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem == newItem
        }

    }

}


