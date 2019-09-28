package com.artemonre.testcontacts.model

import android.content.Context
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.interfaces.Callback
import com.artemonre.testcontacts.interfaces.MainContract
import com.artemonre.testcontacts.utils.CommonException

class Model(var context: Context) : MainContract.Model{

    override fun downloadContacts(callback: Callback) {
        Repository.downloadContacts(callback, context)
    }

    override fun getContacts(): List<Contact> {
        return Repository.getContacts()
    }

    @Throws(CommonException::class)
    override fun getContact(position: Int): Contact {
        return Repository.getContacts()[position]
    }
}