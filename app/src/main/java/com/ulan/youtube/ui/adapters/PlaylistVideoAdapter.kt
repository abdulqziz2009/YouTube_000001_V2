package com.ulan.youtube.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ulan.youtube.data.model.ItemPlayList
import com.ulan.youtube.databinding.ItemVideosBinding
import com.ulan.youtube.ui.utils.loadImageURL

class PlaylistVideoAdapter(
    private val context:Context,
    private val click: (id: String?,title:String?,desc:String?) -> Unit
) : ListAdapter<ItemPlayList, PlaylistVideoAdapter.PlaylistVideoViewHolder>(PlaylistVideoDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistVideoViewHolder {
        return PlaylistVideoViewHolder(
            ItemVideosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistVideoViewHolder, position: Int) {
        val model = getItem(position)
        holder.onBind(model)
        holder.itemView.setOnClickListener {
            click(model?.contentDetails?.videoId,model?.snippet?.title,model?.snippet?.description)
        }
    }

    inner class PlaylistVideoViewHolder(private val binding: ItemVideosBinding) : ViewHolder(binding.root) {

        fun onBind(model: ItemPlayList) {
            binding.itemVideoImg.loadImageURL(model.snippet?.thumbnails?.default?.url)
            binding.itemTvVideoTitle.text = model.snippet?.title
            binding.itemTvDur.text = model.contentDetails?.endAt
        }
    }
}
class PlaylistVideoDiffUtil : DiffUtil.ItemCallback<ItemPlayList>() {
    override fun areItemsTheSame(oldItem: ItemPlayList, newItem: ItemPlayList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemPlayList, newItem: ItemPlayList): Boolean {
        return oldItem == newItem
    }

}
