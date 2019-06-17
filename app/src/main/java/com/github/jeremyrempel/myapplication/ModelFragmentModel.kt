package com.github.jeremyrempel.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModelFragmentModel : ViewModel() {

    val data: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        data.value = "ViewModel"
    }
}
