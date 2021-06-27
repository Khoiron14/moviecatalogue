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
import com.khoiron14.moviecatalogue.R.menu.detail_menu
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.databinding.ActivityMovieDetailBinding
import com.khoiron14.moviecatalogue.utils.*
import com.khoiron14.moviecatalogue.viewmodel.ViewModelFactory
import com.khoiron14.moviecatalogue.vo.Status

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var activityMovieDetailBinding: ActivityMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        activityMovieDetailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)

        supportActionBar?.title = ""
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_MOVIE)

            viewModel.setMovie(id)
            viewModel.getMovie().observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> showLoading(true, activityMovieDetailBinding.progressBar)
                        Status.SUCCESS -> {
                            showLoading(false, activityMovieDetailBinding.progressBar)
                            loadMovieDetail(movie.data!!)
                        }
                        Status.ERROR -> {
                            showLoading(false, activityMovieDetailBinding.progressBar)
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        this.menu = menu
        viewModel.getMovie().observe(this, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> showLoading(true, activityMovieDetailBinding.progressBar)
                    Status.SUCCESS -> {
                        showLoading(false, activityMovieDetailBinding.progressBar)
                        val state = movie.data!!.favorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        showLoading(false, activityMovieDetailBinding.progressBar)
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
                viewModel.setFavoriteMovie()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadMovieDetail(movie: MovieEntity) {
        activityMovieDetailBinding.tvTitle.text = movie.title
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
                .load(BuildConfig.BASE_IMAGE_PATH_URL + movie.posterPath)
                .placeholder(R.drawable.ic_launcher_background)
                .into(activityMovieDetailBinding.imgCover)
//        }
        Glide.with(this)
            .load(BuildConfig.BASE_IMAGE_PATH_URL + movie.posterPath)
            .placeholder(R.drawable.ic_launcher_background)
            .into(activityMovieDetailBinding.imgPoster)
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