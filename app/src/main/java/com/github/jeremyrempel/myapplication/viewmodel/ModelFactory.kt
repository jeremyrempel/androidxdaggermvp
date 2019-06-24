package com.github.jeremyrempel.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.jeremyrempel.myapplication.DaggerFragmentComponent
import com.github.jeremyrempel.myapplication.FragmentComponent
import javax.inject.Inject

open class ModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val dagger = getDagger()
        return when (modelClass) {
            ModelFragmentModel::class.java -> dagger.modelFragmentModel()
            else -> TODO("not implemented")
        } as T
    }

    private fun getDagger(): FragmentComponent = DaggerFragmentComponent.create()
}