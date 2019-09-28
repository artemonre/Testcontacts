package com.artemonre.testcontacts.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.R
import com.artemonre.testcontacts.base_classes.BaseActivity
import com.artemonre.testcontacts.base_classes.BaseFragment
import com.artemonre.testcontacts.controllers.ContactInformationFragmentController
import com.artemonre.testcontacts.utils.MyLog
import com.artemonre.testcontacts.utils.Utils
import java.lang.Exception

class ContactInformationFragment : BaseFragment() {

    private lateinit var backArrowButtonImageview: ImageView
    private lateinit var nameTextview: TextView
    private lateinit var phoneTextview: TextView
    private lateinit var temperamentTextview: TextView
    private lateinit var datesTextview: TextView
    private lateinit var descriptionTextview: TextView

    private var position = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_contact_information, null)

        this.container = container

        if(arguments != null && arguments!!.containsKey(ARGUMENT_KEY_POSITION)){
            position = arguments!!.getInt(ARGUMENT_KEY_POSITION, 0)
        }

        findViews()
        setListeners()
        setContactData()

        super.onCreateView(inflater, container, savedInstanceState)

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = ContactInformationFragmentController(this, activity!!)
        controller.setLifecycle(lifecycle, this)
    }

    private fun findViews(){
        backArrowButtonImageview = rootView.findViewById(R.id.toolbar_back_imageview)

        nameTextview = rootView.findViewById(R.id.fragment_description_name_textview)
        phoneTextview = rootView.findViewById(R.id.fragment_description_phonenumber_textview)
        temperamentTextview = rootView.findViewById(R.id.fragment_description_temperament_textview)
        datesTextview = rootView.findViewById(R.id.fragment_description_datesinterval_textview)
        descriptionTextview = rootView.findViewById(R.id.fragment_description_info_textview)
    }

    private fun setListeners(){
        backArrowButtonImageview.setOnClickListener{
            (context as BaseActivity).onBackPressed()
        }

        phoneTextview.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${(it as TextView).text}")
            startActivity(intent)
        }
    }

    private fun setContactData(){
        try{
            val contact = (controller as ContactInformationFragmentController).getContact(position)

            nameTextview.text = contact.name
            phoneTextview.text = contact.phone
            temperamentTextview.text = contact.temperament.title

            val dateStart = Utils.getNormalDateFromRaw(contact.educationPeriod.start)
            val dateEnd = Utils.getNormalDateFromRaw(contact.educationPeriod.end)

            val datesString = "$dateStart - ${dateEnd}"
            datesTextview.text = datesString
            descriptionTextview.text = contact.biography
        }catch (e: Exception){
            MyLog.d(MAIN_LOG, "set contact data exception", e)

            (context as BaseActivity).onBackPressed()
        }
    }

    companion object{
        private const val ARGUMENT_KEY_POSITION = "position"

        private var instance: ContactInformationFragment? = null

        fun getInstance(position: Int): ContactInformationFragment{
            instance = ContactInformationFragment()

            val arguments = Bundle()
            arguments.putInt(ARGUMENT_KEY_POSITION, position)
            instance!!.arguments = arguments

            return instance!!
        }
    }
}