package com.khoiron14.moviecatalogue.ui.favorite.tvshow


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.databinding.FragmentTvshowFavoriteBinding
import com.khoiron14.moviecatalogue.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowFavoriteFragment : Fragment() {

    private lateinit var fragmentTvshowFavoriteBinding: FragmentTvshowFavoriteBinding
    private lateinit var adapter: TvShowFavoriteAdapter
    private lateinit var viewModel: TvShowFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvshowFavoriteBinding = FragmentTvshowFavoriteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentTvshowFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]
            adapter = TvShowFavoriteAdapter()
            viewModel.getTvShows().observe(viewLifecycleOwner, getTvShowList)

            with (fragmentTvshowFavoriteBinding.rvListTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    private val getTvShowList =
        Observer<List<TvShowEntity>> { tvShowList ->
            if (tvShowList != null) {
                adapter.setData(tvShowList)
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
