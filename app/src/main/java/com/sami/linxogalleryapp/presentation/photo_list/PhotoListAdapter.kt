package com.sami.linxogalleryapp.presentation.photo_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sami.linxogalleryapp.databinding.RowItemPhotoBinding
import com.sami.linxogalleryapp.domain.model.Photo
import com.squareup.picasso.Picasso

class PhotoListAdapter(
    private val items: List<Photo>,
    private val listener: (Photo) -> Unit
) : RecyclerView.Adapter<PhotoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {

        val itemBinding =
            RowItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) =
        holder.bind(items[position], listener)

    override fun getItemCount(): Int =
        items.size
}

/**
 * View holder for Stories [View]
 */
class PhotoHolder(private val itemBinding: RowItemPhotoBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(
        photo: Photo,
        listener: (Photo) -> Unit
    ) {
        Log.d("***Photo", photo.thumbnailUrl)
        with(itemView) {

            Picasso.get().load(photo.thumbnailUrl).into(itemBinding.imgPhoto)

        }
    }
}