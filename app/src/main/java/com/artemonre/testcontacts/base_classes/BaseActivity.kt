package com.artemonre.testcontacts.base_classes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.artemonre.testcontacts.interfaces.MainContract

open class BaseActivity : AppCompatActivity(), MainContract.View{

    protected lateinit var controller : BaseController

    override fun getContext(): Context {
        return this
    }
}