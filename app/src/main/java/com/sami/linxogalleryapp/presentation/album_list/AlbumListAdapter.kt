package com.sami.linxogalleryapp.presentation.album_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sami.linxogalleryapp.databinding.RowItemAlbumBinding
import com.sami.linxogalleryapp.domain.model.Album
import java.util.*

class AlbumListAdapter(
    private val items: List<Album>,
    private val listener: (Album) -> Unit
) : RecyclerView.Adapter<AlbumHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {

        val itemBinding =
            RowItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) =
        holder.bind(items[position], listener)

    override fun getItemCount(): Int =
        items.size
}

/**
 * View holder for Stories [View]
 */
class AlbumHolder(private val itemBinding: RowItemAlbumBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(
        album: Album,
        listener: (Album) -> Unit
    ) {
        itemBinding.txtAlbumName.text = album.title.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
        itemBinding.txtUserName.text = album.userName

        itemView.setOnClickListener{
            listener(album)
        }
    }
}