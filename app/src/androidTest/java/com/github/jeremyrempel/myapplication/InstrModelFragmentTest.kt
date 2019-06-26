package com.github.jeremyrempel.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.jeremyrempel.myapplication.fake.FakeViewModelFactory
import com.github.jeremyrempel.myapplication.viewmodel.ModelFragmentModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrModelFragmentTest {

    @MockK
    private lateinit var fakeViewModel: ModelFragmentModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `testFragmentUpdateData`() {

        every { fakeViewModel.isLoading() } returns MutableLiveData(false)
        every { fakeViewModel.getData() } returns MutableLiveData("Hello World from Mock")

        launchFragmentInContainer<MainFragment>(
            Bundle.EMPTY,
            R.style.FragmentScenarioEmptyFragmentActivityTheme,
            FragFactoryFake(fakeViewModel)
        )

        onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("Hello World from Mock")))
    }

    @Test
    fun `testFragmentShowLoader`() {
        every { fakeViewModel.isLoading() } returns MutableLiveData(true)
        every { fakeViewModel.getData() } returns MutableLiveData()

        launchFragmentInContainer<MainFragment>(
            Bundle.EMPTY,
            R.style.FragmentScenarioEmptyFragmentActivityTheme,
            FragFactoryFake(fakeViewModel)
        )

        onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(ViewMatchers.withId(R.id.loadingView))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    internal class FragFactoryFake(private val viewModel: ViewModel) : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            val fakeViewModelFactory = FakeViewModelFactory(viewModel)
            return MainFragment(fakeViewModelFactory)
        }
    }
}
