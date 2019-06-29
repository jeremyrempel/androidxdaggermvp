package com.github.jeremyrempel.myapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.jeremyrempel.myapplication.viewmodel.MainFragmentViewModel
import javax.inject.Inject
import javax.inject.Provider

class MyViewModelFactory @Inject constructor(
    private val mainFragModel: Provider<MainFragmentViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainFragmentViewModel::class.java -> mainFragModel.get()
            else -> TODO("Missing viewModel $modelClass")
        } as T
    }
}