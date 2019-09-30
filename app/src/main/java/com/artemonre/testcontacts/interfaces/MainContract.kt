package com.artemonre.testcontacts.interfaces

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.app_objects.Contact

interface MainContract{

    interface View{
        fun getContext() : Context
        fun showSnackbar(text: String)
        fun showSnackbar(stringResource: Int)
    }

    interface Presenter : LifecycleObserver{
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate(){}

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart(){}

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume(){}

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause(){}

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop(){}

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(){}

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onChange(){}

        fun setLifecycle(lifecycle: Lifecycle, owner: LifecycleOwner)

        fun addFragment(containerId: Int, fragment: Fragment)
        fun replaceFragment(containerId: Int, fragment: Fragment, popBackStack: Boolean)

        fun setRecycler(recyclerView: RecyclerView, vertical: Boolean, hasFixedSize: Boolean)
        fun setDivider(recyclerView: RecyclerView, divider: Int)
        fun setAdapter(recyclerView: RecyclerView){}
    }

    interface Model{
        fun downloadContacts(callback: Callback)
        fun getContacts(): List<Contact>?
        fun getContact(position: Int): Contact
    }
}