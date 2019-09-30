package com.artemonre.testcontacts.recycler_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artemonre.testcontacts.R
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.base_classes.BaseAdapter
import com.artemonre.testcontacts.utils.Utils


class ContactsAdapter : BaseAdapter<Contact, ContactsAdapter.ViewHolder>(), Filterable {

    private var filteredContacts: List<Contact>? = null

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
            if (onItemClickListener != null) {
                onItemClickListener!!.onItemClicked(items!!.indexOf(filteredContacts!![vh.adapterPosition]))
            }
        }

        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = filteredContacts!![position].name
        holder.phoneNumberText.text = filteredContacts!![position].phone
        holder.heightText.text = filteredContacts!![position].height.toString()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemCount(): Int {
        return if (filteredContacts == null)
            0
        else
            filteredContacts!!.size
    }

    override fun setData(items: MutableList<Contact>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filteredContacts = items
                } else {
                    val filteredList = ArrayList<Contact>()
                    for(row in items!!) {

                        val rawPhone = Utils.getRawPhoneNumber(row.phone)

                        if (row.name.toLowerCase().contains(charString.toLowerCase()) || rawPhone.contains(charSequence)) {
                            filteredList.add(row)
                        }
                    }

                    filteredContacts = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = filteredContacts
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredContacts = filterResults.values as ArrayList<Contact>
                notifyDataSetChanged()
            }
        }
    }
}