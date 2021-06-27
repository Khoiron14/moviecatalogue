package com.khoiron14.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khoiron14.moviecatalogue.BuildConfig
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.databinding.ItemMovieBinding
import com.khoiron14.moviecatalogue.ui.detail.MovieDetailActivity

/**
 * Created by khoiron14 on 7/3/2019.
 */
class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies = ArrayList<MovieEntity>()

    fun setData(items: List<MovieEntity>?) {
        if (items == null) return
        this.movies.clear()
        this.movies.addAll(movies)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(itemMovieBinding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val movie = movies[p1]
        p0.bind(movie)
    }

    class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_PATH_URL + movie.posterPath)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}