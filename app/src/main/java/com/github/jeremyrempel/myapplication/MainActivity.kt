package com.github.jeremyrempel.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.jeremyrempel.myapplication.factory.FragFactory
import com.github.jeremyrempel.myapplication.viewmodel.ModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val dagger = DaggerFragmentComponent.create()

        // needs to be called after super.onCreate
        val fragFactory = FragFactory(dagger)
        supportFragmentManager.fragmentFactory = fragFactory

        super.onCreate(savedInstanceState)

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
