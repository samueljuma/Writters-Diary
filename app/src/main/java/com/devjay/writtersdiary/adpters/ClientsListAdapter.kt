package com.devjay.writtersdiary.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devjay.writtersdiary.data.entities.Client
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.databinding.ClientCardItemBinding

class ClientsListAdapter(val clickListener: ClientListener): ListAdapter<Client, ClientsListAdapter.ViewHolder>(ClientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }

    // viewHolder
    class ViewHolder (val binding: ClientCardItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (item: Client, clickListener:ClientListener){
            binding.client = item
            binding.clickListener = clickListener
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
class ClientListener (val clickListener: (clientId: Long) -> Unit){
    fun onClick(client: Client) = clickListener(client.clientID)
}


