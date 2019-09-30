package com.artemonre.testcontacts.model

import android.content.Context
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.interfaces.Callback
import com.artemonre.testcontacts.R
import com.artemonre.testcontacts.app_objects.EducationPeriod
import com.artemonre.testcontacts.app_objects.Temperament
import com.artemonre.testcontacts.http.ContactGson
import com.artemonre.testcontacts.http.RequestApiHelper
import com.artemonre.testcontacts.utils.CommonException
import com.artemonre.testcontacts.utils.MyLog
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import java.util.*


object Repository: Callback{

    private var requestApiHelper: RequestApiHelper

    private var contacts: List<Contact>? = null
    private var downloadContactsCallback: Callback? = null

    private var downloadRequests: List<String>? = null

    init {
        requestApiHelper = RequestApiHelper()
    }

    fun downloadContacts(callback: Callback, context: Context) {
        downloadContactsCallback = callback
        downloadRequests = ArrayList()
        contacts = ArrayList()

        val contactsUrls = context.getResources().getStringArray(R.array.contactsUrls)

        for(i in contactsUrls.indices){
            (downloadRequests as ArrayList).add(contactsUrls[i])
            requestApiHelper.downloadContacts(contactsUrls[i], this)
        }
    }

    fun getContacts(): List<Contact>? {
        return contacts
    }

    override fun callback(arg: Any) {
        if(arg is Pair<*,*>){
            val tempContacts = getContactsFromContactsGsons(arg.first as List<ContactGson>)

            synchronized(contacts!!){
                (contacts as ArrayList).addAll(tempContacts)
                (downloadRequests as ArrayList).remove(arg.second)
            }

            if(downloadRequests != null && downloadRequests!!.size == 0 && downloadContactsCallback != null){
                downloadContactsCallback!!.callback(contacts!!)
            }
        }else if(arg is Throwable){
            downloadContactsCallback!!.callback(arg)
        }
    }

    private fun getContactsFromContactsGsons(contactsGsons: List<ContactGson>): List<Contact>{
        val tempList = ArrayList<Contact>()
        var tempEducationPeriod: EducationPeriod

        for(contactGson in contactsGsons){
            tempEducationPeriod = EducationPeriod(contactGson.educationPeriod.start, contactGson.educationPeriod.end)

            tempList.add(Contact(contactGson.id, contactGson.name, contactGson.phone, contactGson.height,
                    contactGson.biography, Temperament.valueOf(contactGson.temperament), tempEducationPeriod))
        }

        return tempList
    }
}