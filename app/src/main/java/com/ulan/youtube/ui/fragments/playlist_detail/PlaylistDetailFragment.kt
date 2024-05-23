package com.ulan.youtube.ui.fragments.playlist_detail

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.ulan.youtube.base.BaseFragment
import com.ulan.youtube.databinding.FragmentPlaylistDetailBinding
import com.ulan.youtube.ui.adapters.PlaylistVideoAdapter
import com.ulan.youtube.ui.utils.Resourse
import com.ulan.youtube.ui.utils.loadImageURL
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaylistDetailFragment : BaseFragment<FragmentPlaylistDetailBinding>(),
    AppBarLayout.OnOffsetChangedListener {
    private val viewModel: DetailPlaylistViewModel by viewModel()
    private val args: PlaylistDetailFragmentArgs by navArgs()
    private val adapter: PlaylistVideoAdapter by lazy { PlaylistVideoAdapter(requireContext(), this::click) }
    private val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f
    private val PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f
    private val ALPHA_ANIMATIONS_DURATION = 200
    private var title: String = "HellYeahPlay"

    private var mIsTheTitleVisible = false
    private var mIsTheTitleContainerVisible = true

    override fun getViewBinding() =
        FragmentPlaylistDetailBinding.inflate(layoutInflater)


    override fun initialize() {
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "playlistTitle", viewLifecycleOwner, object : FragmentResultListener{
                override fun onFragmentResult(requestKey: String, result: Bundle) {
                    title = result.getString("title").toString()
                        binding.tvPlaylistName.text = title
                }
            })
        binding.rvPlaylistVideo.adapter = adapter
        binding.mainAppbar.addOnOffsetChangedListener(this)
        startAlphaAnimation(binding.mainTextviewTitle, 0, View.INVISIBLE)
        binding.mainTextviewTitle.text = title
        args.playlistId?.let { viewModel.getPlaylistItem(id = it) }
    }

    override fun constructorListeners() {

    }

    override fun launchObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            when(it) {
                is Resourse.Loading -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                is Resourse.Success -> {
                    binding.imgPlaylist.loadImageURL(it.data?.items?.get(0)?.snippet?.thumbnails?.default?.url)
                    adapter.submitList(it.data?.items)
                }
                is Resourse.Error -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun click(id: String?) {
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, offset: Int) {
        val maxScroll = appBarLayout.totalScrollRange
        val percentage = Math.abs(offset).toFloat() / maxScroll.toFloat()

        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
    }

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(binding.mainTextviewTitle, ALPHA_ANIMATIONS_DURATION.toLong(), View.VISIBLE)
                mIsTheTitleVisible = true
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(binding.mainTextviewTitle, ALPHA_ANIMATIONS_DURATION.toLong(), View.INVISIBLE)
                mIsTheTitleVisible = false
            }
        }
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(
                    binding.mainLinearlayoutTitle,
                    ALPHA_ANIMATIONS_DURATION.toLong(),
                    View.INVISIBLE
                )
                mIsTheTitleContainerVisible = false
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(
                    binding.mainLinearlayoutTitle,
                    ALPHA_ANIMATIONS_DURATION.toLong(),
                    View.VISIBLE
                )
                mIsTheTitleContainerVisible = true
            }
        }
    }


    private fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
        val alphaAnimation =
            if (visibility == View.VISIBLE) AlphaAnimation(0f, 1f) else AlphaAnimation(1f, 0f)
        alphaAnimation.setDuration(duration)
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }

}