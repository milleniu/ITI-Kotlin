package dev.hugozammit.roomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendListAdapter( private val dataset: List<Friend> ) :
    RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {

    class ViewHolder( val textView: TextView ) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_friend_list, parent, false) as TextView

        return ViewHolder( textView )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataset[position].firstName
    }

    override fun getItemCount() = dataset.size
}