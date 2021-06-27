package com.khoiron14.moviecatalogue.ui.favorite.movie


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.khoiron14.moviecatalogue.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 *
 */
class MovieFavoriteFragment : Fragment() {

    private var fragmentMovieFavoriteBinding: FragmentMovieFavoriteBinding? = null
    private val binding get() = fragmentMovieFavoriteBinding
    private lateinit var viewModel: MovieFavoriteViewModel
    private lateinit var adapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieFavoriteBinding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvListMovie)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]
            adapter = MovieFavoriteAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, { movieList ->
                adapter.submitList(movieList)
            })

            binding?.rvListMovie?.layoutManager = LinearLayoutManager(context)
            binding?.rvListMovie?.setHasFixedSize(true)
            binding?.rvListMovie?.adapter = adapter
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

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = adapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    movieEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}
