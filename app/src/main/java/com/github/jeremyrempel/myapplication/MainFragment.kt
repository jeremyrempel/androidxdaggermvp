package com.github.jeremyrempel.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.jeremyrempel.myapplication.viewmodel.MainFragmentViewModel
import javax.inject.Inject

/**
 * MainFragment with factory
 */
class MainFragment
@Inject constructor(private val factory: ViewModelProvider.Factory) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainFragmentViewModel> { factory }

        viewModel.getData().observe(this, Observer {
            requireView().findViewById<TextView>(R.id.textView).text = it
        })

        viewModel.isLoading().observe(this, Observer {
            requireView().findViewById<View>(R.id.loadingView).visibility = if (!it) View.GONE else View.VISIBLE
            requireView().findViewById<TextView>(R.id.textView).visibility = if (it) View.GONE else View.VISIBLE
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}