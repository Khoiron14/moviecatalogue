package com.khoiron14.moviecatalogue.ui.favorite.tvshow


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
import com.khoiron14.moviecatalogue.databinding.FragmentTvshowFavoriteBinding
import com.khoiron14.moviecatalogue.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowFavoriteFragment : Fragment() {

    private var fragmentTvshowFavoriteBinding: FragmentTvshowFavoriteBinding? = null
    private val binding get() = fragmentTvshowFavoriteBinding
    private lateinit var viewModel: TvShowFavoriteViewModel
    private lateinit var adapter: TvShowFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvshowFavoriteBinding = FragmentTvshowFavoriteBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvListTvshow)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]
            adapter = TvShowFavoriteAdapter()
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShowList ->
                adapter.submitList(tvShowList)
            })

            binding?.rvListTvshow?.layoutManager = LinearLayoutManager(context)
            binding?.rvListTvshow?.setHasFixedSize(true)
            binding?.rvListTvshow?.adapter = adapter
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
                val tvShowEntity = adapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setFavorite(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    tvShowEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}
