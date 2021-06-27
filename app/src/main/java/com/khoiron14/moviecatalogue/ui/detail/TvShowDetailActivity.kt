package com.khoiron14.moviecatalogue.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.khoiron14.moviecatalogue.BuildConfig
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.databinding.ActivityTvshowDetailBinding
import com.khoiron14.moviecatalogue.utils.*
import com.khoiron14.moviecatalogue.viewmodel.ViewModelFactory
import com.khoiron14.moviecatalogue.vo.Status

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    private lateinit var activityTvShowDetailBinding: ActivityTvshowDetailBinding
    private lateinit var viewModel: TvShowDetailViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_detail)

        activityTvShowDetailBinding = ActivityTvshowDetailBinding.inflate(layoutInflater)

        supportActionBar?.title = ""
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[TvShowDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_TVSHOW)

            viewModel.setTvShow(id)
            viewModel.getTvShow().observe(this, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> showLoading(true, activityTvShowDetailBinding.progressBar)
                        Status.SUCCESS -> {
                            showLoading(false, activityTvShowDetailBinding.progressBar)
                            loadTvShowDetail(tvShow.data!!)
                        }
                        Status.ERROR -> {
                            showLoading(false, activityTvShowDetailBinding.progressBar)
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        viewModel.getTvShow().observe(this, { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> showLoading(true, activityTvShowDetailBinding.progressBar)
                    Status.SUCCESS -> {
                        showLoading(false, activityTvShowDetailBinding.progressBar)
                        val state = tvShow.data!!.favorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        showLoading(false, activityTvShowDetailBinding.progressBar)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                viewModel.setFavoriteTvShow()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadTvShowDetail(tvShow: TvShowEntity) {
        activityTvShowDetailBinding.tvName.text = tvShow.name
//        rating_bar.rating = movie.rating.toFloat() / 2
//        tv_release.text = convertDate(movie.releaseDate)
//        tv_overview.text = movie.overview
//        if (movie.backdropPath != null) {
//            Glide.with(this)
//                .load(BuildConfig.BASE_IMAGE_PATH_URL + movie.backdropPath)
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(img_cover)
//        } else {
        Glide.with(this)
            .load(BuildConfig.BASE_IMAGE_PATH_URL + tvShow.posterPath)
            .placeholder(R.drawable.ic_launcher_background)
            .into(activityTvShowDetailBinding.imgCover)
//        }
        Glide.with(this)
            .load(BuildConfig.BASE_IMAGE_PATH_URL + tvShow.posterPath)
            .placeholder(R.drawable.ic_launcher_background)
            .into(activityTvShowDetailBinding.imgPoster)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.add_to_favorite)
        if (state)
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        else
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
    }
}
