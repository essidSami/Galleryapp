package com.sami.linxogalleryapp.presentation.album_list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sami.linxogalleryapp.R
import com.sami.linxogalleryapp.databinding.FragmentAlbumListBinding
import com.sami.linxogalleryapp.presentation.photo_list.PhotoListFragment
import com.sami.linxogalleryapp.util.configureHomeToolBar
import com.sami.linxogalleryapp.util.replaceFragment

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumListFragment] factory method to
 * create an instance of this fragment.
 */
class AlbumListFragment : Fragment(R.layout.fragment_album_list) {

    private var _binding: FragmentAlbumListBinding? = null
    private val binding: FragmentAlbumListBinding get() = requireNotNull(_binding)

    private lateinit var listViewModel: AlbumListViewModel
    lateinit var albumAdapter: AlbumListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentAlbumListBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        listViewModel = ViewModelProvider(requireActivity()).get(AlbumListViewModel::class.java)

        configureHomeToolBar(
            activity = activity as AppCompatActivity,
            toolbarTitle = getString(R.string.txt_title_home),
            isBackButtonVisible = false
        )

        listViewModel.stateAlbums.observe(viewLifecycleOwner, Observer { state ->

            binding.progressBar.visibility =
                if (state.isLoading) View.VISIBLE
                else View.GONE

            if (state.error.isNotBlank()) {
                binding.txtError.visibility = View.VISIBLE
                binding.txtError.text = state.error
            }

            if (state.albums.isNotEmpty()) {
                albumAdapter = AlbumListAdapter(state.albums) {
                    replaceFragment(
                        (activity as AppCompatActivity).supportFragmentManager,
                        PhotoListFragment.newInstance(it),
                        true
                    )
                }

                binding.rescyclerViewAlbum.apply {
                    adapter = albumAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}