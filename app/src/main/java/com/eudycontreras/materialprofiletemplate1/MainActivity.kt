package com.eudycontreras.materialprofiletemplate1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            val intent = Intent(this@MainActivity, UserProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.nothing_animation, R.anim.nothing_animation)
        }
    }

}
