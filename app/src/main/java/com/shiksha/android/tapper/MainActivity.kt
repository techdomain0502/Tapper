package com.shiksha.android.tapper

import android.os.BaseBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shiksha.android.tapper.screens.MainScreenFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.frameContainer, MainScreenFragment.newInstance())
                .commit()
    }
}