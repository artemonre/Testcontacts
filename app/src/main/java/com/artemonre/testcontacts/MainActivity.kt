package com.artemonre.testcontacts

import android.os.Bundle
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.base_classes.BaseActivity
import com.artemonre.testcontacts.ui.ContactInformationFragment
import com.artemonre.testcontacts.ui.ContactsListFragment
import com.artemonre.testcontacts.utils.MyLog
import java.util.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = MainController(this)

        if(savedInstanceState == null){
            controller.addFragment(R.id.main_fragment_container_layout, ContactsListFragment())
        }
    }

    fun showContactInformation(position: Int){
        val fragment = ContactInformationFragment.getInstance(position)

        controller.replaceFragment(R.id.main_fragment_container_layout, fragment, true)
    }
}