package dev.hugozammit.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendListAdapter( friendList: List<Friend> ) :
    RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {

    private var dataset: MutableList<Friend> = friendList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_friend_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = dataset[position]
        holder.tvName.setText( friend.firstName )
        holder.tvRating.setText( friend.rating.toString() )

        holder.bEdit.setOnClickListener {
            val editFriend = Friend(
                uid = dataset[position].uid,
                firstName = holder.tvName.text.toString(),
                rating = holder.tvRating.text.toString().toInt()
            )
            eventListener?.onFriendEdit(editFriend)
        }

        holder.bRemove.setOnClickListener { eventListener?.onFriendDelete(dataset[position]) }
    }

    fun updateData( friendList: List<Friend> ) {
        dataset.clear()
        dataset.addAll(friendList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataset.size

    private var eventListener: EventListener? = null
    fun setEventListener(eventListener: EventListener) {
        this.eventListener = eventListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<EditText>(R.id.tvName)!!
        val tvRating = view.findViewById<EditText>(R.id.tvRating)!!
        val bEdit = view.findViewById<Button>(R.id.bEdit)!!
        val bRemove = view.findViewById<Button>(R.id.bRemove)!!
    }

    interface EventListener {
        fun onFriendEdit(friend: Friend)
        fun onFriendDelete(friend: Friend)
    }
}