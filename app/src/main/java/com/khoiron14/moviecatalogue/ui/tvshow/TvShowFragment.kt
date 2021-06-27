package com.khoiron14.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.databinding.FragmentTvshowBinding
import com.khoiron14.moviecatalogue.utils.showLoading
import com.khoiron14.moviecatalogue.viewmodel.ViewModelFactory
import com.khoiron14.moviecatalogue.vo.Resource
import com.khoiron14.moviecatalogue.vo.Status

/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvshowBinding
    private lateinit var adapter: TvShowAdapter
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true, fragmentTvShowBinding.progressBar)

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            adapter = TvShowAdapter()
            viewModel.getTvShows().observe(viewLifecycleOwner, getTvShowList)

            with (fragmentTvShowBinding.rvListTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    private val getTvShowList =
        Observer<Resource<List<TvShowEntity>>> { tvShowList ->
            if (tvShowList != null) {
                when (tvShowList.status) {
                    Status.LOADING -> showLoading(true, fragmentTvShowBinding.progressBar)
                    Status.SUCCESS -> {
                        showLoading(false, fragmentTvShowBinding.progressBar)
                        adapter.setData(tvShowList.data)
                        adapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        showLoading(false, fragmentTvShowBinding.progressBar)
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
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
