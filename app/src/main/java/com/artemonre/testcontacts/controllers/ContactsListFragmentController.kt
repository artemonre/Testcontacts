package com.artemonre.testcontacts.controllers

import android.content.Context
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.App
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.MainActivity
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.base_classes.BaseActivity
import com.artemonre.testcontacts.base_classes.BaseController
import com.artemonre.testcontacts.interfaces.Callback
import com.artemonre.testcontacts.interfaces.MainContract
import com.artemonre.testcontacts.interfaces.OnItemClickListener
import com.artemonre.testcontacts.recycler_adapters.ContactsAdapter
import com.artemonre.testcontacts.ui.ContactsListFragment
import com.artemonre.testcontacts.utils.MyLog
import com.artemonre.testcontacts.utils.Utils

class ContactsListFragmentController(view: MainContract.View, context: Context) : BaseController(view, context), Callback, OnItemClickListener<Int> {

    private var contacts: List<Contact> = ArrayList()
    private var filteredContacts: List<Contact>? = null
    private lateinit var adapter: ContactsAdapter

    private var updateContactsHandler: Handler? = null
    private var filterContactsThread: Thread? = null

    override fun onStart() {
        super.onStart()

        downloadContactsIfNeed(false)
    }

    fun downloadContactsIfNeed(isManualUpdate: Boolean){
        if(Utils.isEmpty(model.getContacts())
                || App.contactsIsOld
                || isManualUpdate){
            model.downloadContacts(this)
        }
    }

    override fun setAdapter(recyclerView: RecyclerView) {
//        TODO do it normal if possible
        adapter = ContactsAdapter()
        adapter.setData(contacts as MutableList<Contact>)
        adapter.onItemClickListener = this as OnItemClickListener<Any>

        recyclerView.adapter = adapter
    }

    override fun onItemClicked(arg: Int) {
        (view as ContactsListFragment).showContactInformation(arg)
    }

    fun filterContacts(searchText: String, context: Context){
        if(filterContactsThread != null && filterContactsThread!!.isAlive){
            filterContactsThread!!.interrupt()
        }

        filterContactsThread = Thread(FilterContactsRunnable(context))
        filterContactsThread!!.start()
    }

    private fun setFilteredContacts(){
        if(filteredContacts != null)
            adapter.setData(filteredContacts as MutableList<Contact>)
    }

    override fun callback(arg: Any) {
        MyLog.d(MAIN_LOG, "contacts loaded", this)
        if(arg is List<*> && arg[0] is Contact){
            adapter.setData(arg as MutableList<Contact>)

            (view as ContactsListFragment).setRecyclerVisibility(View.VISIBLE)
            (view as ContactsListFragment).setProgressVisibility(View.GONE)

            App.contactsIsOld = false
            startUpdateTimer()
        }
    }

    private fun startUpdateTimer(){
        if(updateContactsHandler != null){
            updateContactsHandler!!.removeCallbacksAndMessages(null)
        }

        updateContactsHandler = Handler()
        updateContactsHandler!!.postDelayed(TimerUpdateContacts(), App.UPDATE_CONTACTS_DELAY_MILLIS)
    }

    inner class TimerUpdateContacts : Runnable{
        override fun run() {
            App.contactsIsOld = true
        }
    }

    inner class FilterContactsRunnable(val context: Context) : Runnable{

        override fun run() {


            (context as BaseActivity).runOnUiThread{setFilteredContacts()}
        }
    }
}