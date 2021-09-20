package com.sami.linxogalleryapp.presentation.photo_list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sami.linxogalleryapp.R
import com.sami.linxogalleryapp.databinding.FragmentPhotoListBinding
import com.sami.linxogalleryapp.domain.model.Album
import com.sami.linxogalleryapp.util.Constants.PARAM_ALBUM_SELECTED
import com.sami.linxogalleryapp.util.configureHomeToolBar

/**
 * A simple [Fragment] subclass.
 * Use the [PhotoListFragment] factory method to
 * create an instance of this fragment.
 */
class PhotoListFragment : Fragment(R.layout.fragment_photo_list) {

    private var _binding: FragmentPhotoListBinding? = null
    private val binding: FragmentPhotoListBinding get() = requireNotNull(_binding)

    private lateinit var viewModel: PhotoViewModel
    lateinit var photoAdapter: PhotoListAdapter
    private var album : Album? = null

    companion object {
        fun newInstance(album: Album): PhotoListFragment {
            val fragment = PhotoListFragment()
            val args = Bundle()
            args.putSerializable(PARAM_ALBUM_SELECTED, album)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        if (args != null) {
            album = args.getSerializable(PARAM_ALBUM_SELECTED) as Album
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPhotoListBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(PhotoViewModel::class.java)

        configureHomeToolBar(
            activity = activity as AppCompatActivity,
            toolbarTitle = album?.title.toString(),
            isBackButtonVisible = true
        )

        viewModel.getPhotoList(album?.id!!)
        viewModel.statePhotos.observe(viewLifecycleOwner, Observer { state ->

            binding.progressBar.visibility =
                if (state.isLoading) View.VISIBLE
                else View.GONE

            if (state.error.isNotBlank()) {
                binding.txtError.visibility = View.VISIBLE
                binding.txtError.text = state.error
            }

            if (state.photos.isNotEmpty()) {
                photoAdapter = PhotoListAdapter(state.photos) {

                }

                binding.recyclerViewPhoto.adapter = photoAdapter
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}