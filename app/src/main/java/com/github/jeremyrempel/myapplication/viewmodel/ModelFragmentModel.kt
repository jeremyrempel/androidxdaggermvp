package com.github.jeremyrempel.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jeremyrempel.myapplication.service.MyService
import javax.inject.Inject

class ModelFragmentModel @Inject constructor(
    myService: MyService
) : ViewModel() {

    private val data: MutableLiveData<String> = MutableLiveData()
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        // call async service with callbacks
        myService.getData(data::setValue, ::handleError)
    }

    fun getData(): LiveData<String> = data
    fun isLoading(): LiveData<Boolean> = isLoading

    private fun handleError(error: Throwable) {

    }
}
