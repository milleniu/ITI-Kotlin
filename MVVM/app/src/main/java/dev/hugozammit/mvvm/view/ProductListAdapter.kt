package dev.hugozammit.mvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.hugozammit.mvvm.R
import dev.hugozammit.mvvm.model.ProductFamily

class ProductListAdapter(private val productList: List<ProductFamily>)
    : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, id: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_product_list, viewGroup, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name?.text = productList[position].name
        viewHolder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(viewHolder.itemView, position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvName)!!
    }


    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}