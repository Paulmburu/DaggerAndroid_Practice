package tk.paulmburu.daggerandroid_practice.ui.main

import android.os.Bundle
import tk.paulmburu.daggerandroid_practice.BaseActivity
import tk.paulmburu.daggerandroid_practice.R

class MainActivity : BaseActivity() {

    override val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}