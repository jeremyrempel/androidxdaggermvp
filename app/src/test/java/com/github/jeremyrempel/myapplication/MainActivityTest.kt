package com.github.jeremyrempel.myapplication

import android.widget.TextView
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun `test activity`() {
        val scenario = launch(MainActivity::class.java)

        scenario.onActivity {
            val result = it.findViewById<TextView>(R.id.textView).text
            assertEquals("Banana", result)
        }
    }

    @Test
    fun `test fragment`() {

        val scenario = FragmentScenario.launchInContainer(MainFragment::class.java)

        scenario.onFragment {
            val result = it.view!!.findViewById<TextView>(R.id.textView).text
            assertEquals("Banana", result)
        }
    }
}
