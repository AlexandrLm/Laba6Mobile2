package com.example.laba6mobile2

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var firstFragment : FirstFragment

    private lateinit var buttons: List<Button>
    private val numbers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        firstFragment = supportFragmentManager
//            .findFragmentById(R.id.fragmentContainerView) as FirstFragment? ?:FirstFragment()
//        if (savedInstanceState != null) {
//            firstFragment = supportFragmentManager.getFragment(savedInstanceState, "fragment1") as FirstFragment
//        }
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainerView, firstFragment, "fragment1")
//            .commit()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //supportFragmentManager.putFragment(outState,"firstFragment",firstFragment)
    }

}