package com.passion.hindismsm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.passion.hindismsm.Locally.MyDatabase


class MainActivity : AppCompatActivity() {


    private lateinit var myDatabase: MyDatabase

    private val MYSMS = "hindisms"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDatabase = MyDatabase(this, MYSMS, null, 1)
    }

}
