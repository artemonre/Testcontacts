package com.artemonre.testcontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ProgressBar
import com.artemonre.testcontacts.R
import com.artemonre.testcontacts.base_classes.BaseFragment
import com.artemonre.testcontacts.controllers.ContactsListFragmentController

class ContactsListFragment : BaseFragment() {

    private lateinit var searchButton: ImageButton
    private lateinit var deleteSearchTextButton: ImageButton
    private lateinit var searchEdittext: EditText
    private lateinit var progressLayout: FrameLayout
    private lateinit var loadingProgressbar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_contacts_list, null)

        this.container = container

        findViews()
        controller.setRecycler(recyclerView!!, true, true)
        controller.setDivider(recyclerView!!, R.drawable.divider_thin)

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
}