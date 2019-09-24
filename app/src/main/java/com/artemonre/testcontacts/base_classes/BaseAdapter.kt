package com.artemonre.testcontacts.base_classes

import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.App
import com.artemonre.testcontacts.interfaces.OnItemClickListener
import com.artemonre.testcontacts.utils.MyLog

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    open var onItemClickListener: OnItemClickListener<Any>? = null

    protected var items: MutableList<T>? = null

    open fun setData(items: MutableList<T>){
        this.items = items
        notifyDataSetChanged()
    }

    open fun deleteItem(position: Int) {
        MyLog.d(App.MAIN_LOG, "delete item, position $position", this);

        items!!.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
}