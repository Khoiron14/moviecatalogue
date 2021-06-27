package com.khoiron14.moviecatalogue.ui.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khoiron14.moviecatalogue.BuildConfig
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.databinding.ItemMovieBinding
import com.khoiron14.moviecatalogue.ui.detail.TvShowDetailActivity

/**
 * Created by khoiron14 on 7/28/2019.
 */
class TvShowFavoriteAdapter : PagedListAdapter<TvShowEntity, TvShowFavoriteAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var tvShows = ArrayList<TvShowEntity>()

    fun setData(items: List<TvShowEntity>?) {
        if (items == null) return
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(itemMovieBinding)
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val tvShow = tvShows[p1]
        p0.bind(tvShow)
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShow.name
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_PATH_URL + tvShow.posterPath)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                    intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, tvShow.tvShowId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}