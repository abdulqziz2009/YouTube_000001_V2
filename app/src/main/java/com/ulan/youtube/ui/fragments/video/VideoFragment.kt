

package com.ulan.youtube.ui.fragments.video

import android.annotation.SuppressLint
import android.content.Intent
import android.webkit.WebChromeClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ulan.youtube.base.BaseFragment
import com.ulan.youtube.databinding.FragmentVideoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class VideoFragment : BaseFragment<FragmentVideoBinding>() {
    private val args: VideoFragmentArgs by navArgs()
    private val viewModel : VideoPlayerViewModel by viewModel()
    override fun getViewBinding(): FragmentVideoBinding =
        FragmentVideoBinding.inflate(layoutInflater)


    @SuppressLint("SetJavaScriptEnabled")
    override fun initialize() {
        binding.tvTitleVideo?.text = args.videoTitle
        binding.txtDesc?.text = args.videoDesc
        val videoID = args.videoID
        val videoURL =
            "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$videoID?autoplay=1\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        binding.webYoutubePlayer.loadData(videoURL, "text/html", "utf-8")
        binding.webYoutubePlayer.settings.javaScriptEnabled = true
        binding.webYoutubePlayer.webChromeClient = WebChromeClient()



    }

    override fun launchObserver() {
        binding.tvTitleVideo?.text = args.videoTitle
        binding.txtDesc?.text = args.videoDesc
    }

    override fun constructorListeners() {
        binding.btnBack?.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnSh?.setOnClickListener {
            share()
        }
    }
    private fun share(){
        val videoID = args.videoID
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Я поделился с тобой этим видео " +
                "https://youtu.be/$videoID")
        startActivity(Intent.createChooser(intent,"Поделился с тобой этим видео"))
    }




}
/*






*//*package com.ulan.youtube.ui.fragments.video
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import androidx.navigation.fragment.navArgs
import com.ulan.youtube.base.BaseFragment
import com.ulan.youtube.databinding.FragmentVideoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoFragment : BaseFragment<FragmentVideoBinding>() {
    private val args: VideoFragmentArgs by navArgs()
    private val viewModel: VideoPlayerViewModel by viewModel()
    private var isVideoLoaded = false

    override fun getViewBinding(): FragmentVideoBinding =
        FragmentVideoBinding.inflate(layoutInflater)

    @SuppressLint("SetJavaScriptEnabled")
    override fun initialize() {
        binding.tvTitleVideo?.text = args.videoTitle
        binding.txtDesc?.text = args.videoDesc


        if (!isVideoLoaded) {
            val videoID = args.videoID
            val videoURL = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$videoID?autoplay=true\" title=\"YouTube video player\" frameborder=\"1\" allow=\"accelerometer; autoplay ; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen = 1></iframe>"
            binding.webYoutubePlayer.loadData(videoURL, "text/html", "utf-8")
            isVideoLoaded = true
        }

        binding.webYoutubePlayer.settings.javaScriptEnabled = true
        binding.webYoutubePlayer.webChromeClient = WebChromeClient()
    }

    override fun launchObserver() {
        binding.tvTitleVideo?.text = args.videoTitle
        binding.txtDesc?.text = args.videoDesc

        viewModel.liveData.observe(viewLifecycleOwner) {

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webYoutubePlayer.saveState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            binding.webYoutubePlayer.restoreState(savedInstanceState)
            isVideoLoaded = true
        }
    }
}
*/
/*
package com.ulan.youtube.ui.fragments.video

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.widget.MediaController
import android.widget.VideoView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ulan.youtube.R
import com.ulan.youtube.base.BaseFragment
import com.ulan.youtube.databinding.FragmentVideoBinding

class VideoFragment : BaseFragment<FragmentVideoBinding>() {
    private val args: VideoFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentVideoBinding =
        FragmentVideoBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videoView = binding.videoPlayer
            val mediaController =MediaController(requireContext())
            mediaController.setAnchorView(videoView)
            val videoID = args.videoID
            val videoURLOffline = Uri.parse("android.resource://${R.raw.video_test}")
            val videoURL = Uri.parse("https://www.youtube.com/embed/$videoID")
            videoView?.setVideoURI(videoURLOffline)
            videoView?.setMediaController(mediaController)
            videoView?.requestFocus()
            videoView?.start()

    }



    @SuppressLint("SetJavaScriptEnabled")
    override fun initialize() {
        binding.tvTitleVideo?.text = args.videoTitle
        binding.txtDesc?.text = args.videoDesc
    }

    override fun constructorListeners() {
        binding.btnBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
*/
/*
package com.ulan.youtube.ui.fragments.video

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.widget.MediaController
import android.widget.VideoView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayer
import com.ulan.youtube.R
import com.ulan.youtube.base.BaseFragment
import com.ulan.youtube.databinding.FragmentVideoBinding
class VideoFragment : BaseFragment<FragmentVideoBinding>() {
    private val args: VideoFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentVideoBinding =
        FragmentVideoBinding.inflate(layoutInflater)


    @SuppressLint("SetJavaScriptEnabled")
    override fun initialize() {
        binding.tvTitleVideo?.text = args.videoTitle
        binding.txtDesc?.text = args.videoDesc
    }
    override fun constructorListeners() {
        binding.btnBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

*/


/*
class VideoFragment : BaseFragment<FragmentVideoBinding>() {
    private val args: VideoFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentVideoBinding =
        FragmentVideoBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializePlayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        releasePlayer()
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(requireContext()).build()
        playerView.player = player

        val videoId = args.videoID
        val videoUrl = "https://www.youtube.com/watch?v=$videoId"

        val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    private fun releasePlayer() {
        player.release()
    }

    override fun initialize() {
        binding.tvTitleVideo?.text = args.videoTitle
        binding.txtDesc?.text = args.videoDesc
    }

    override fun constructorListeners() {
        binding.btnBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

*/
