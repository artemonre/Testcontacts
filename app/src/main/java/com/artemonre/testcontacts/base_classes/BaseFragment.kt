package com.artemonre.testcontacts.base_classes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.interfaces.MainContract

open class BaseFragment : Fragment(), MainContract.View {

    protected lateinit var controller: MainContract.Presenter
    protected lateinit var rootView: View

    protected var listener: View.OnClickListener? = null
    protected var recyclerView: RecyclerView? = null

    var container: ViewGroup? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return rootView
    }

    override fun getContext(): Context {
        return rootView.context
    }

    override fun showSnackbar(text: String) {
        (context as MainContract.View).showSnackbar(text)
    }

    override fun showSnackbar(stringResource: Int) {
        (context as MainContract.View).showSnackbar(stringResource)
    }

    fun onBackPressed(): Boolean {
        return true
    }
}