package dev.hugozammit.mvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.hugozammit.mvvm.R
import dev.hugozammit.mvvm.model.ProductFamily

class ProductDetailAdapter(private val productFamily: ProductFamily)
    : RecyclerView.Adapter<ProductDetailAdapter.ViewHolder>() {

    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, id: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup?.context).inflate(R.layout.adapter_products_details, viewGroup, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return productFamily.products.size;
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name?.text = productFamily.products[position].name
        viewHolder.count?.text = productFamily.products[position].description
        val imageUrl = "http://mobcategories.s3-website-eu-west-1.amazonaws.com" + productFamily.products[position].url
        Glide.with(viewHolder.imageView.context).load(imageUrl).into(viewHolder.imageView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvName)!!
        val count = itemView.findViewById<TextView>(R.id.tvCount)!!
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)!!

        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(itemView, 0)
            }
        }
    }

    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}