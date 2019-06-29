package com.github.jeremyrempel.myapplication

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.github.jeremyrempel.myapplication.factory.MyFragmentFactory
import com.github.jeremyrempel.myapplication.factory.MyViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Fragment, ViewModel factor do not need interfaces.
 * Only FragmentFactory which is root
 */
@Component(modules = [MyFactoryModule::class])
interface MyComponent {
    fun fragFactory(): FragmentFactory
}

/**
 * ModelFactory and FragmentFactory only need modules because
 * they types extend
 */
@Module
class MyFactoryModule {
    @Provides
    fun providesModelFactory(modelFactory: MyViewModelFactory): ViewModelProvider.Factory = modelFactory

    @Provides
    fun providesFragFactory(fragFactory: MyFragmentFactory): FragmentFactory = fragFactory
}