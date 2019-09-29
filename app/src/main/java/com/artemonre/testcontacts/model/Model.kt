package com.artemonre.testcontacts.model

import android.content.Context
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.interfaces.Callback
import com.artemonre.testcontacts.interfaces.MainContract
import com.artemonre.testcontacts.utils.CommonException
import com.artemonre.testcontacts.utils.Utils

class Model(var context: Context) : MainContract.Model{

    override fun downloadContacts(callback: Callback) {
        Repository.downloadContacts(callback, context)
    }

    override fun getContacts(): List<Contact>? {
        return Repository.getContacts()
    }

    @Throws(CommonException::class)
    override fun getContact(position: Int): Contact {
        val contacts = Repository.getContacts()

        if(!Utils.isEmpty(contacts))
            return contacts!![position]
        else
            throw CommonException()
    }
}