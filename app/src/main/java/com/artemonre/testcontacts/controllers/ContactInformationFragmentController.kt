package com.artemonre.testcontacts.controllers

import android.content.Context
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.base_classes.BaseController
import com.artemonre.testcontacts.interfaces.MainContract
import com.artemonre.testcontacts.utils.CommonException

class ContactInformationFragmentController(view: MainContract.View, context: Context) : BaseController(view, context){

    @Throws(CommonException::class)
    fun getContact(position: Int): Contact {
        return model.getContact(position)
    }
}