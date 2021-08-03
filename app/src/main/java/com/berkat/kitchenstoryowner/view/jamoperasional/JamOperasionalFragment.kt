package com.berkat.kitchenstoryowner.view.jamoperasional

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berkat.kitchenstoryowner.R

class JamOperasionalFragment : Fragment() {

    companion object {
        fun newInstance() = JamOperasionalFragment()
    }

    private lateinit var viewModel: JamOperasionalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jam_operasional_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JamOperasionalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}