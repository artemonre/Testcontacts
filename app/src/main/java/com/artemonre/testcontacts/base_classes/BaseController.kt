package com.artemonre.testcontacts.base_classes

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.interfaces.MainContract

open class BaseController : MainContract.Presenter{

    lateinit var owner: LifecycleOwner

    override fun setLifecycle(lifecycle: Lifecycle, owner: LifecycleOwner) {
        this.owner = owner
        lifecycle.addObserver(this)
    }

    override fun addFragment(containerId: Int, fragment: Fragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replaceFragment(containerId: Int, fragment: Fragment, popBackStack: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRecycler(recyclerView: RecyclerView, vertical: Boolean, hasFixedSize: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDivider(recyclerView: RecyclerView, divider: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}