package dev.hugozammit.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendListAdapter( friendList: List<Friend> ) :
    RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {

    var dataset: MutableList<Friend> = friendList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_friend_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = dataset[position]
        holder.tvName.text = friend.firstName
        holder.tvRating.text = friend.rating.toString()
        holder.bButton.setOnClickListener {
            onItemClickListener?.onFriendDelete(holder.itemView, position)
        }
    }

    fun updateData( friendList: List<Friend> ) {
        dataset.clear()
        dataset.addAll(friendList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataset.size

    private var onItemClickListener: ItemClickListener? = null
    fun setItemClickListener(clickItemListener: ItemClickListener) {
        onItemClickListener = clickItemListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tvName)!!
        val tvRating = view.findViewById<TextView>(R.id.tvRating)!!
        val bButton = view.findViewById<Button>(R.id.bRemove)!!
    }

    interface ItemClickListener {
        fun onFriendDelete(view: View, position: Int)
    }
}