package com.ulan.youtube.ui.fragments.playlist

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ulan.youtube.base.BaseFragment
import com.ulan.youtube.databinding.FragmentPlaylistBinding
import com.ulan.youtube.ui.adapters.PlaylistAdapter
import com.ulan.youtube.ui.utils.Resourse
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistFragment : BaseFragment<FragmentPlaylistBinding>() {
    private val viewModel: PlaylistViewModel by viewModel()
    private val adapter: PlaylistAdapter by lazy { PlaylistAdapter(requireContext(), this::click) }

    override fun getViewBinding(): FragmentPlaylistBinding {
        return FragmentPlaylistBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        binding.rvPlaylist.adapter = adapter
        viewModel.getPlaylist()

    }

    override fun launchObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            when(it) {
                is Resourse.Loading -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                is Resourse.Success -> adapter.submitList(it.data?.items)
                is Resourse.Error -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun click(id: String?, title: String?) {
        val bundle = Bundle()
        bundle.putString("title", title)
        requireActivity().supportFragmentManager.setFragmentResult("playlistTitle", bundle)
        findNavController()
            .navigate(
                PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment()
                    .setPlaylistId(id))
    }
}