package tk.paulmburu.daggerandroid_practice.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import tk.paulmburu.daggerandroid_practice.BaseActivity
import tk.paulmburu.daggerandroid_practice.R

class MainActivity : BaseActivity() {

    override val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                sessionManager.logout()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}