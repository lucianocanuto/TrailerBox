package com.btcodans.trailerbox.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.btcodans.trailerbox.databinding.ItemMovieBinding
import com.btcodans.trailerbox.data.models.Movie

class MovieAdapter(
    private val onClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position))

    inner class VH(private val b: ItemMovieBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(movie: Movie) {
            b.title.text = movie.title
            val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            Glide.with(b.poster).load(posterUrl).into(b.poster)
            b.root.setOnClickListener { onClick(movie) }
        }
    }
}
