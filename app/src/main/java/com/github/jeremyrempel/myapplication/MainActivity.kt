package com.github.jeremyrempel.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // init dagger graph
        val dagger = DaggerMyComponent.create()

        // needs to be called after super.onCreate
        supportFragmentManager.fragmentFactory = dagger.fragFactory()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                val frag = supportFragmentManager.fragmentFactory.instantiate(
                    classLoader,
                    MainFragment::class.java.canonicalName
                )
                this.add(R.id.frag_container, frag)
                commitNow()
            }
        }
    }
}