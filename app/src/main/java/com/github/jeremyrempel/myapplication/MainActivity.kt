package com.github.jeremyrempel.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // init dagger graph
        val dagger: MyComponent = DaggerMyComponent.create()

        // needs to be called after super.onCreate
        supportFragmentManager.fragmentFactory = dagger.fragFactory()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                val frag = supportFragmentManager.fragmentFactory.instantiate(
                    classLoader,
                    MainFragment::class.java.canonicalName
                )
                add(R.id.frag_container, frag)
            }
        }
    }
}