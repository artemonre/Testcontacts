package com.artemonre.testcontacts.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.MainActivity
import com.artemonre.testcontacts.R
import com.artemonre.testcontacts.base_classes.BaseFragment
import com.artemonre.testcontacts.controllers.ContactsListFragmentController
import com.artemonre.testcontacts.utils.MyLog
import com.artemonre.testcontacts.utils.Utils

class ContactsListFragment : BaseFragment() {

    private lateinit var deleteSearchTextButton: ImageButton
    private lateinit var searchEdittext: EditText
    private lateinit var refreshLayout: SwipeRefreshLayout
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

    override fun onStart() {
        super.onStart()

        searchEdittext.setText("")
        recyclerView!!.scrollToPosition(actualPosition)
    }

    private fun findViews(){
        searchEdittext = rootView.findViewById(R.id.toolbar_search_edittext)
        deleteSearchTextButton = rootView.findViewById(R.id.toolbar_search_delete_text_imagebutton)

        refreshLayout = rootView.findViewById(R.id.fragment_list_contacts_swiperefreshlayout)
        recyclerView = rootView.findViewById(R.id.fragment_list_contacts_recyclerview)

        loadingProgressbar = rootView.findViewById(R.id.fragment_list_loading_progressbar)
    }

    private fun setListeners(){
        searchEdittext.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(s != null){
                    if(!Utils.isEmpty(s.toString()))
                        (controller as ContactsListFragmentController).filterContacts(s.toString())
                    else
                        (controller as ContactsListFragmentController).setEmptyFilter()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        deleteSearchTextButton.setOnClickListener{searchEdittext.setText("")}

        refreshLayout.setOnRefreshListener{
            refreshLayout.isRefreshing = false
            (controller as ContactsListFragmentController).downloadContactsIfNeed(true)
        }
    }

    fun setRecyclerVisibility(visibility: Int){
        refreshLayout.visibility = visibility
    }

    fun setProgressVisibility(visibility: Int){
        loadingProgressbar.visibility = visibility
    }

    fun showContactInformation(position: Int){
        (context as MainActivity).showContactInformation(position)
        actualPosition = position
    }
}