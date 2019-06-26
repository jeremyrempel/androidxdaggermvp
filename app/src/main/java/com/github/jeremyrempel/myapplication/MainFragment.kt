package com.github.jeremyrempel.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.jeremyrempel.myapplication.factory.MyModelFactory
import com.github.jeremyrempel.myapplication.viewmodel.ModelFragmentModel
import javax.inject.Inject

class MainFragment
@Inject constructor(
    private val factory: ViewModelProvider.Factory
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(this, factory).get(ModelFragmentModel::class.java)

        model.getData().observe(this, Observer {
            requireView().findViewById<TextView>(R.id.textView).text = it
        })

        model.isLoading().observe(this, Observer {
            val visibility = if(it) View.GONE else View.VISIBLE
            requireView().findViewById<View>(R.id.loadingView).visibility = visibility
        })
    }
}