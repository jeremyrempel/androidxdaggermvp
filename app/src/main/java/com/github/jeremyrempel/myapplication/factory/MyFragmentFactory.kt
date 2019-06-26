package com.github.jeremyrempel.myapplication.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.jeremyrempel.myapplication.MainFragment
import javax.inject.Inject
import javax.inject.Provider

class MyFragmentFactory @Inject constructor(
    private val mainFragProvider: Provider<MainFragment>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            MainFragment::class.java.canonicalName -> mainFragProvider.get()
            else -> TODO("Missing fragment $className")
        }
    }
}