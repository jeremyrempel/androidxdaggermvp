package com.github.jeremyrempel.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.jeremyrempel.myapplication.viewmodel.ModelFactory
import com.github.jeremyrempel.myapplication.viewmodel.ModelFragmentModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ModelFragmentTest {

    @MockK
    private lateinit var fakeViewModel: ModelFragmentModel

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `test fragment update data`() {

        every { fakeViewModel.isLoading() } returns MutableLiveData(false)
        every { fakeViewModel.getData() } returns MutableLiveData("Hello World from Mock")

        val scenario = launchFragmentInContainer<ModelFragment>(
            Bundle.EMPTY,
            R.style.FragmentScenarioEmptyFragmentActivityTheme,
            FragFactoryFake(fakeViewModel)
        )

        scenario.onFragment {
            val result1 = it.requireView().findViewById<TextView>(R.id.textView).text
            assertEquals("Hello World from Mock", result1)
        }
    }

    @Test
    fun `test fragment show loader`() {
        every { fakeViewModel.isLoading() } returns MutableLiveData(true)
        every { fakeViewModel.getData() } returns MutableLiveData()

        val scenario = launchFragmentInContainer<ModelFragment>(
            Bundle.EMPTY,
            R.style.FragmentScenarioEmptyFragmentActivityTheme,
            FragFactoryFake(fakeViewModel)
        )

        scenario.onFragment {
            val textViewVisibilityResult = it.requireView().findViewById<TextView>(R.id.textView).visibility
            val loaderVisibilityResult = it.requireView().findViewById<View>(R.id.loadingView).visibility

            assertEquals(View.VISIBLE, textViewVisibilityResult)
            assertEquals(View.GONE, loaderVisibilityResult)
        }
    }

    internal class FragFactoryFake(private val viewModel: ViewModel) : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String) =
            ModelFragment(ModelFactoryFake(viewModel))
    }

    internal class ModelFactoryFake(private val viewModel: ViewModel) : ModelFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModel as T
    }
}