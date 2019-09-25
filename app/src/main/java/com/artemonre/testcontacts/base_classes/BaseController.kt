package com.artemonre.testcontacts.base_classes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.interfaces.MainContract
import com.artemonre.testcontacts.model.Model
import com.artemonre.testcontacts.utils.MyLog

open class BaseController(protected var view: MainContract.View, context: Context) : MainContract.Presenter{

    protected var model: MainContract.Model
    protected lateinit var owner: LifecycleOwner

    protected var fragmentManager: FragmentManager
    protected var fragmentTransaction: FragmentTransaction? = null

    protected var adapter: BaseAdapter<Any, *>? = null

    private var layoutManager: RecyclerView.LayoutManager? = null

    init {
        model = Model(context)
        fragmentManager = (context as AppCompatActivity).supportFragmentManager
    }

    override fun setLifecycle(lifecycle: Lifecycle, owner: LifecycleOwner) {
        this.owner = owner
        lifecycle.addObserver(this)
    }

    override fun addFragment(containerId: Int, fragment: Fragment) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction!!
                .add(containerId, fragment)
                .commit()
    }

    override fun replaceFragment(containerId: Int, fragment: Fragment, popBackStack: Boolean) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction!!.replace(containerId, fragment)

        if (popBackStack)
            fragmentTransaction!!.addToBackStack(null)

        fragmentTransaction!!.commit()
    }

    override fun setRecycler(recyclerView: RecyclerView, vertical: Boolean, hasFixedSize: Boolean) {
        if (vertical)
            layoutManager = LinearLayoutManager(view.getContext())
        else
            layoutManager = LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView.layoutManager = layoutManager

        if (hasFixedSize)
            recyclerView.setHasFixedSize(true)
    }

    override fun setDivider(recyclerView: RecyclerView, divider: Int) {
        val context = view.getContext()

        val verticalDividerDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val horizontalDivider = ContextCompat.getDrawable(context, divider)
        try {
            verticalDividerDecorator.setDrawable(horizontalDivider!!)
            recyclerView.addItemDecoration(verticalDividerDecorator)
        } catch (e: Exception) {
            MyLog.d(MAIN_LOG, "divider exception", e)
        }
    }
}