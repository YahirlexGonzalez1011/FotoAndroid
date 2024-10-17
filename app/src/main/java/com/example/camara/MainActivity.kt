package com.example.camara

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    } //onCreate
    fun lector(view: View?) {
        val lector_act = Intent(applicationContext,
            LectorActivity::class.java)
        startActivity(lector_act)
    } //lector
    fun camara(view: View?) {
        val foto_act = Intent(applicationContext, FotoActivity::class.java)
        startActivity(foto_act)
    } //camara
} //class