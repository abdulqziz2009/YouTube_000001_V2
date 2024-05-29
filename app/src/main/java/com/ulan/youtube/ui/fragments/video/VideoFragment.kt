/*

package com.ulan.youtube.ui.fragments.video

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

        viewModel.liveData.observe(viewLifecycleOwner){

        }
    }


}






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
package com.ulan.youtube.ui.fragments.video

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ulan.youtube.base.BaseFragment
import com.ulan.youtube.databinding.FragmentVideoBinding

class VideoFragment : BaseFragment<FragmentVideoBinding>() {
    private val args: VideoFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentVideoBinding =
        FragmentVideoBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            binding.webYoutubePlayer?.restoreState(savedInstanceState)
        } else {
            val videoID = args.videoID
            val videoURL = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$videoID?autoplay=1\" title=\"YouTube video player\" frameborder=\"1\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
            binding.webYoutubePlayer?.loadData(videoURL, "text/html", "utf-8")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webYoutubePlayer?.saveState(outState)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initialize() {
        binding.tvTitleVideo?.text = args.videoTitle
        binding.txtDesc?.text = args.videoDesc

        binding.webYoutubePlayer?.settings?.javaScriptEnabled = true
        binding.webYoutubePlayer?.webChromeClient = WebChromeClient()
    }

    override fun constructorListeners() {
        binding.btnBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

