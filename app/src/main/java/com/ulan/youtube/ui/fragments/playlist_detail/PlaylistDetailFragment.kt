package com.ulan.youtube.ui.fragments.playlist_detail

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.ulan.youtube.base.BaseFragment
import com.ulan.youtube.databinding.FragmentPlaylistDetailBinding
import com.ulan.youtube.ui.adapters.PlaylistVideoAdapter
import com.ulan.youtube.ui.utils.Resourse
import com.ulan.youtube.ui.utils.loadImageURL
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaylistDetailFragment : BaseFragment<FragmentPlaylistDetailBinding>() {
    private val viewModel: DetailPlaylistViewModel by viewModel()
    private val args: PlaylistDetailFragmentArgs by navArgs()
    private val adapter: PlaylistVideoAdapter by lazy { PlaylistVideoAdapter(requireContext(), this::click) }
    private var title:String = "Huy"
    override fun getViewBinding() =
        FragmentPlaylistDetailBinding.inflate(layoutInflater)


    override fun initialize() {
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "playlistTitle", viewLifecycleOwner, object : FragmentResultListener{
                override fun onFragmentResult(requestKey: String, result: Bundle) {
                    title = result.getString("title").toString()
                    binding.tvTitlePlaylist.text = title
                }
            })
        binding.rvPlaylistVideo.adapter = adapter
        args.playlistId?.let { viewModel.getPlaylistItem(id = it) }
    }

    override fun constructorListeners() {
        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }

    }

    override fun launchObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.tvDescPlaylist.text = it?.data?.items?.get(0)?.contentDetails?.itemCount?.toString()
            when(it) {
                is Resourse.Loading -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                is Resourse.Success -> {
                    binding.playlistsPreview.loadImageURL(it.data?.items?.get(0)?.snippet?.thumbnails?.default?.url)
                    adapter.submitList(it.data?.items)
                }
                is Resourse.Error -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun click(id: String?,title:String?,desc:String?) {
        findNavController().navigate(PlaylistDetailFragmentDirections
            .actionPlaylistDetailFragmentToVideoFragment().setVideoID(id).setVideoTitle(title).setVideoDesc(desc))
    }
}