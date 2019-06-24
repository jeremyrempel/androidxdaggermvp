package com.github.jeremyrempel.myapplication.viewmodel

import androidx.lifecycle.LiveData

interface IModelFragmentModel {
    fun getData(): LiveData<String>
}