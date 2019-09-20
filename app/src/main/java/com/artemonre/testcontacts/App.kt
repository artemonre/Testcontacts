package com.artemonre.testcontacts

import android.app.Application

class App : Application(){

    override fun onCreate() {
        super.onCreate()
    }

    companion object{
        const val MAIN_LOG = "mainLog"
    }
}