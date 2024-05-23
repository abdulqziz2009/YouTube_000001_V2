package com.ulan.youtube.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ulan.youtube.R
import com.ulan.youtube.data.model.ItemPlayList
import com.ulan.youtube.databinding.ItemPlaylistBinding
import com.ulan.youtube.ui.utils.loadImageURL

class PlaylistAdapter(
    val context: Context,
    private val click: (id: String?, title: String?) -> Unit
) : ListAdapter<ItemPlayList, PlaylistAdapter.PlaylistViewHolder>(PlaylistDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val model = getItem(position)
        holder.onBind(model)
        holder.itemView.setOnClickListener {
            click(model?.id, model?.snippet?.title)
        }
    }

   inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) : ViewHolder(binding.root) {

        fun onBind(model: ItemPlayList) {
            binding.itemImg.loadImageURL(model.snippet?.thumbnails?.default?.url)
            binding.itemTvTitle.text = model.snippet?.title
            val count = model.contentDetails?.itemCount
            binding.itemTvCount.text = context.getString(R.string.videos_count, count)
        }
    }
}
class PlaylistDiffUtil : DiffUtil.ItemCallback<ItemPlayList>() {
    override fun areItemsTheSame(oldItem: ItemPlayList, newItem: ItemPlayList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemPlayList, newItem: ItemPlayList): Boolean {
        return oldItem == newItem
    }

}
