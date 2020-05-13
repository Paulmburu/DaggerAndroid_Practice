package tk.paulmburu.daggerandroid_practice.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import tk.paulmburu.daggerandroid_practice.BaseActivity
import tk.paulmburu.daggerandroid_practice.R
import tk.paulmburu.daggerandroid_practice.ui.main.posts.PostsFragment
import tk.paulmburu.daggerandroid_practice.ui.main.profile.ProfileFragment

class MainActivity : BaseActivity() {

    override val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testFragment()
    }
    fun testFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,PostsFragment())
            .commit()
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