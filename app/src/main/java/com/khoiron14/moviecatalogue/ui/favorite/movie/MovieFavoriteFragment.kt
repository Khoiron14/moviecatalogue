package com.khoiron14.moviecatalogue.ui.favorite.movie


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.khoiron14.moviecatalogue.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 *
 */
class MovieFavoriteFragment : Fragment() {

    private lateinit var fragmentMovieFavoriteBinding: FragmentMovieFavoriteBinding
    private lateinit var adapter: MovieFavoriteAdapter
    private lateinit var viewModel: MovieFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieFavoriteBinding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentMovieFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]
            adapter = MovieFavoriteAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, getMovieList)

            with (fragmentMovieFavoriteBinding.rvListMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    private val getMovieList =
        Observer<List<MovieEntity>> { movieList ->
            if (movieList != null) {
                adapter.setData(movieList)
                adapter.notifyDataSetChanged()
            }
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.search)

        if (searchItem != null) {
            val searchView: SearchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
            searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean = true

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    return true
                }
            })
        }
    }
}
