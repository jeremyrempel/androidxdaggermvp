package com.github.jeremyrempel.myapplication.fake

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class FakeViewModelFactory(private val testviewModel: ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = testviewModel as T
}