package com.artemonre.testcontacts.recycler_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.R
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.base_classes.BaseAdapter
import com.artemonre.testcontacts.utils.MyLog

class ContactsAdapter : BaseAdapter<Contact, ContactsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView
        var phoneNumberText: TextView
        var heightText: TextView

        init {
            this.nameText = view.findViewById(R.id.item_contact_name_textview)
            this.phoneNumberText = view.findViewById(R.id.item_contact_phonenumber_textview)
            this.heightText = view.findViewById(R.id.item_contact_height_textview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)

        val vh = ViewHolder(v)

        v.setOnClickListener { view ->
            MyLog.d(MAIN_LOG, "on item click", MenuScreenAdapter@this);
            if (onItemClickListener != null) {
                MyLog.d(MAIN_LOG, "on item click !null", MenuScreenAdapter@this);
                onItemClickListener!!.onItemClicked(vh.adapterPosition)
            }
        }

        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = items!![position].name
        holder.phoneNumberText.text = items!![position].phone
        holder.heightText.text = items!![position].height.toString()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemCount(): Int {
        return if (items == null)
            0
        else
            items!!.size
    }

    override fun setData(items: MutableList<Contact>) {
        this.items = items
        notifyDataSetChanged()
    }
}