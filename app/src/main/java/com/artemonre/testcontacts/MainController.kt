package com.artemonre.testcontacts

import android.content.Context
import com.artemonre.testcontacts.base_classes.BaseController
import com.artemonre.testcontacts.interfaces.MainContract

class MainController(view : MainContract.View) : BaseController(view, (view as Context)){

}