package com.github.jeremyrempel.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ModelFragmentTest {

    class MyFragmentFactory : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            val frag =  super.instantiate(classLoader, className)

            return frag
        }
    }

    @Test
    fun `test fragment`() {
        val scenario = launchFragmentInContainer<ModelFragment>(
            Bundle.EMPTY,
            R.style.FragmentScenarioEmptyFragmentActivityTheme,
            MyFragmentFactory())

        scenario.onFragment {

            val view = it.view!!.findViewById<TextView>(R.id.textView)

            val result1 = view.text
            assertEquals("ViewModel", result1)

            view.text = "Banana 2"
            val result2 = view.text
            assertEquals("Banana 2", result2)

        }

        scenario.recreate()

        onView(
            withId(R.id.textView)
        ).check(matches(withText("ViewModel")))

        scenario.onFragment {
            val result = it.requireView().findViewById<TextView>(R.id.textView).text
            assertEquals("ViewModel", result)
        }
    }
}