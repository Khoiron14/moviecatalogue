package com.khoiron14.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.databinding.FragmentMovieBinding
import com.khoiron14.moviecatalogue.utils.showLoading
import com.khoiron14.moviecatalogue.viewmodel.ViewModelFactory
import com.khoiron14.moviecatalogue.vo.Status


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieFragment : Fragment() {

    private var fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = fragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showLoading(true, binding?.progressBar!!)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val adapter = MovieAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, { movieList ->
                if (movieList != null) {
                    when (movieList.status) {
                        Status.LOADING -> showLoading(true, binding?.progressBar!!)
                        Status.SUCCESS -> {
                            showLoading(false, binding?.progressBar!!)
                            adapter.submitList(movieList.data)
                        }
                        Status.ERROR -> {
                            showLoading(false, binding?.progressBar!!)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with (binding?.rvListMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
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
