package com.github.jeremyrempel.myapplication

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.jeremyrempel.myapplication.viewmodel.ModelFactory
import com.github.jeremyrempel.myapplication.viewmodel.ModelFragmentModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JvmModelFragmentTest {

    @MockK
    private lateinit var fakeViewModel: ModelFragmentModel

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `test fragment update data`() {

        every { fakeViewModel.isLoading() } returns MutableLiveData(false)
        every { fakeViewModel.getData() } returns MutableLiveData("Hello World from Mock")

        launchFragmentInContainer<ModelFragment>(
            Bundle.EMPTY,
            R.style.FragmentScenarioEmptyFragmentActivityTheme,
            FragFactoryFake(fakeViewModel)
        )

        onView(withId(R.id.textView)).check(matches(withText("Hello World from Mock")))
    }

    @Test
    fun `test fragment show loader`() {
        every { fakeViewModel.isLoading() } returns MutableLiveData(true)
        every { fakeViewModel.getData() } returns MutableLiveData()

        launchFragmentInContainer<ModelFragment>(
            Bundle.EMPTY,
            R.style.FragmentScenarioEmptyFragmentActivityTheme,
            FragFactoryFake(fakeViewModel)
        )

        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.loadingView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    internal class FragFactoryFake(private val viewModel: ViewModel) : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String) =
            ModelFragment(ModelFactoryFake(viewModel))
    }

    internal class ModelFactoryFake(private val viewModel: ViewModel) : ModelFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModel as T
    }
}