package com.github.jeremyrempel.myapplication.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.jeremyrempel.myapplication.FragmentComponent
import com.github.jeremyrempel.myapplication.ModelFragment

class FragFactory(
    private val dagger: FragmentComponent
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ModelFragment::class.java.canonicalName -> dagger.modelFrag()
            else -> TODO("Missing fragment $className")
        }
    }
}