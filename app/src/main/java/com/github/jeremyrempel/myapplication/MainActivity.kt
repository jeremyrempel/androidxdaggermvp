package com.github.jeremyrempel.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragFactory = FragFactory()
        supportFragmentManager.fragmentFactory = fragFactory

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                val frag = fragFactory.instantiate(
                    classLoader,
                    ModelFragment::class.java.canonicalName
                )
                this.add(R.id.frag_container, frag)
                commitNow()
            }
        }
    }
}
