package com.artemonre.testcontacts.controllers

import android.content.Context
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.App
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.R
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.base_classes.BaseController
import com.artemonre.testcontacts.interfaces.Callback
import com.artemonre.testcontacts.interfaces.MainContract
import com.artemonre.testcontacts.interfaces.OnItemClickListener
import com.artemonre.testcontacts.recycler_adapters.ContactsAdapter
import com.artemonre.testcontacts.ui.ContactsListFragment
import com.artemonre.testcontacts.utils.MyLog
import com.artemonre.testcontacts.utils.Utils
import java.net.UnknownHostException

class ContactsListFragmentController(view: MainContract.View, context: Context) : BaseController(view, context), Callback, OnItemClickListener<Int> {

    private var contacts: List<Contact> = ArrayList()
    private var filteredContacts: List<Contact>? = null
    private lateinit var adapter: ContactsAdapter

    private var updateContactsHandler: Handler? = null

    override fun onStart() {
        super.onStart()

        if(!downloadContactsIfNeed(false)) {
            contacts = model.getContacts()!!
            adapter.setData(contacts as MutableList<Contact>)
            adapter.filter.filter("")
            showContactsList()
        }
    }

    fun downloadContactsIfNeed(isManualUpdate: Boolean) : Boolean{
        if(Utils.isEmpty(model.getContacts())
                || App.contactsOld
                || isManualUpdate){
            model.downloadContacts(this)

            hideContactsList()

            return true
        }

        return false
    }

    override fun setAdapter(recyclerView: RecyclerView) {
        adapter = ContactsAdapter()
        adapter.setData(contacts as MutableList<Contact>)
        adapter.onItemClickListener = this as OnItemClickListener<Any>

        recyclerView.adapter = adapter
    }

    override fun onItemClicked(arg: Int) {
        (view as ContactsListFragment).showContactInformation(arg)
    }

    fun filterContacts(searchText: String){
        adapter.filter.filter(searchText)
    }

    fun setEmptyFilter(){
        filteredContacts = null
        adapter.setData(contacts as MutableList<Contact>)
        adapter.filter.filter("")
    }

    override fun callback(arg: Any) {
        if(arg is List<*> && arg[0] is Contact){
            contacts = arg as List<Contact>
            adapter.setData(contacts as MutableList<Contact>)
            adapter.filter.filter("")

            showContactsList()

            App.contactsOld = false
            startUpdateTimer()
        }else if(arg is Throwable){
            if(arg.javaClass == UnknownHostException().javaClass)
                view.showSnackbar(R.string.error_ethernet_connection)
        }
    }

    private fun showContactsList(){
        (view as ContactsListFragment).setRecyclerVisibility(View.VISIBLE)
        (view as ContactsListFragment).setProgressVisibility(View.GONE)
    }

    private fun hideContactsList(){
        (view as ContactsListFragment).setRecyclerVisibility(View.GONE)
        (view as ContactsListFragment).setProgressVisibility(View.VISIBLE)
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
            App.contactsOld = true
        }
    }
}