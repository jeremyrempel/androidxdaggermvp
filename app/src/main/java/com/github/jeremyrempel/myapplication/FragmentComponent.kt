package com.github.jeremyrempel.myapplication

import com.github.jeremyrempel.myapplication.viewmodel.ModelFactory
import com.github.jeremyrempel.myapplication.viewmodel.ModelFragmentModel
import dagger.Component

@Component
interface FragmentComponent {
    fun modelFrag(): ModelFragment
    fun modelFragmentModel(): ModelFragmentModel
    fun modelViewModel(modelFactory: ModelFactory)
}