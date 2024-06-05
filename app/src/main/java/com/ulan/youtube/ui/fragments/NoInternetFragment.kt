package com.ulan.youtube.ui.fragments

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ulan.youtube.R
import com.ulan.youtube.base.BaseFragment
import com.ulan.youtube.databinding.FragmentNoInternetBinding
import com.ulan.youtube.ui.utils.isOnline

class NoInternetFragment: BaseFragment<FragmentNoInternetBinding>() {
    override fun getViewBinding(): FragmentNoInternetBinding {
        return FragmentNoInternetBinding.inflate(layoutInflater)
    }

    override fun constructorListeners() {
        binding.btnCheck.setOnClickListener {
            if (isOnline(requireContext()) != null){
                findNavController().navigateUp()
            }else{
                Toast.makeText(requireContext(),"Все еще нет интернета",Toast.LENGTH_SHORT).show()
            }
        }
    }
}