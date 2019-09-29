package com.artemonre.testcontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ProgressBar
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.MainActivity
import com.artemonre.testcontacts.R
import com.artemonre.testcontacts.base_classes.BaseFragment
import com.artemonre.testcontacts.controllers.ContactsListFragmentController
import com.artemonre.testcontacts.utils.MyLog

class ContactsListFragment : BaseFragment() {

    private lateinit var searchButton: ImageButton
    private lateinit var deleteSearchTextButton: ImageButton
    private lateinit var searchEdittext: EditText
    private lateinit var progressLayout: FrameLayout
    private lateinit var loadingProgressbar: ProgressBar

    private var actualPosition = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_contacts_list, null)

        this.container = container

        findViews()
        controller.setRecycler(recyclerView!!, true, true)
        controller.setDivider(recyclerView!!, R.drawable.divider_thin)
        controller.setAdapter(recyclerView!!)
        setListeners()

        super.onCreateView(inflater, container, savedInstanceState)

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = ContactsListFragmentController(this, activity!!)
        controller.setLifecycle(lifecycle, this)
    }

    private fun findViews(){
        searchButton = rootView.findViewById(R.id.toolbar_search_imagebutton)
        searchEdittext = rootView.findViewById(R.id.toolbar_search_edittext)
        deleteSearchTextButton = rootView.findViewById(R.id.toolbar_search_delete_text_imagebutton)

        recyclerView = rootView.findViewById(R.id.fragment_list_contacts_recyclerview)

        progressLayout = rootView.findViewById(R.id.fragment_list_progress_layout)
        loadingProgressbar = rootView.findViewById(R.id.fragment_list_loading_progressbar)
    }

    private fun setListeners(){

    }

    fun setRecyclerVisibility(visibility: Int){
        if(recyclerView != null)
            recyclerView!!.visibility = visibility
    }

    fun setProgressVisibility(visibility: Int){
        progressLayout.visibility = visibility
    }

    fun showContactInformation(position: Int){
        (context as MainActivity).showContactInformation(position)
        actualPosition = position
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        MyLog.d(MAIN_LOG, "save instance state", this)
    }
}