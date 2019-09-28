package com.artemonre.testcontacts.controllers

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.MainActivity
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.base_classes.BaseController
import com.artemonre.testcontacts.interfaces.Callback
import com.artemonre.testcontacts.interfaces.MainContract
import com.artemonre.testcontacts.interfaces.OnItemClickListener
import com.artemonre.testcontacts.recycler_adapters.ContactsAdapter
import com.artemonre.testcontacts.ui.ContactsListFragment
import com.artemonre.testcontacts.utils.MyLog

class ContactsListFragmentController(view: MainContract.View, context: Context) : BaseController(view, context), Callback, OnItemClickListener<Int> {

    private var contacts: List<Contact> = ArrayList()
    private lateinit var adapter: ContactsAdapter

    override fun onResume() {
        super.onResume()

        model.downloadContacts(this)
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

    override fun callback(arg: Any) {
        MyLog.d(MAIN_LOG, "contacts loaded", this)
        if(arg is List<*> && arg[0] is Contact){
            adapter.setData(arg as MutableList<Contact>)

            (view as ContactsListFragment).setRecyclerVisibility(View.VISIBLE)
            (view as ContactsListFragment).setProgressVisibility(View.GONE)
        }
    }
}