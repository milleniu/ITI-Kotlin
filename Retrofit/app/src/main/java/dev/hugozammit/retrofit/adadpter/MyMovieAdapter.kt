package dev.hugozammit.retrofit.adadpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.hugozammit.retrofit.R
import dev.hugozammit.retrofit.model.Movie
import kotlinx.android.synthetic.main.layout_movie_item.view.*

class MyMovieAdapter(private val context: Context, private val movieList: MutableList<Movie>)
    : RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>() {

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var image : ImageView
        var txt_team : TextView
        var txt_createdby : TextView
        var txt_name : TextView

        init {
            image = itemView.image_movie
            txt_name = itemView.txt_name
            txt_team = itemView.txt_team
            txt_createdby = itemView.txt_createdby
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.layout_movie_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(movieList[position].imageurl).into(holder.image)
        holder.txt_createdby.text = movieList[position].createdby
        holder.txt_name.text = movieList[position].name
        holder.txt_team.text = movieList[position].team
    }
}