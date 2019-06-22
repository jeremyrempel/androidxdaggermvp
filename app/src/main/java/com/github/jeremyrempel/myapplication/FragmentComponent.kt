package com.github.jeremyrempel.myapplication

import dagger.Component

@Component
interface FragmentComponent {
    fun modelFrag(): ModelFragment
}