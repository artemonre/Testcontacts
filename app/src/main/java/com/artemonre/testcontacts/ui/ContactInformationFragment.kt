package com.artemonre.testcontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemonre.testcontacts.R
import com.artemonre.testcontacts.base_classes.BaseFragment

class ContactInformationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_contact_information, null)

        this.container = container

        findViews()

        super.onCreateView(inflater, container, savedInstanceState)

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller.setLifecycle(lifecycle, this)
    }

    private fun findViews(){
    }
}