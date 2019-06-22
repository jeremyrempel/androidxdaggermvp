package com.github.jeremyrempel.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class FragFactory(
    private val dagger: FragmentComponent = DaggerFragmentComponent.create()
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ModelFragment::class.java.canonicalName -> dagger.modelFrag()
            else -> TODO("Missing fragment $className")
        }
    }
}