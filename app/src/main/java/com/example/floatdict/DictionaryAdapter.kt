package com.example.floatdict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DictionaryViewholder(view: View) : RecyclerView.ViewHolder(view) {

    val indexField = view.findViewById<TextView>(R.id.textPosition)
    val wordField = view.findViewById<TextView>(R.id.textMeaning)
}

class DictionaryAdapter(private var things: List<DictionaryWord>) :
    RecyclerView.Adapter<DictionaryViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wordrow, parent, false)
        return DictionaryViewholder((view))
    }

    override fun getItemCount(): Int {
        return things.size
    }

    override fun onBindViewHolder(holder: DictionaryViewholder, position: Int) {
        if (things[position].malayalam_definition == "") {
            holder.indexField.text = "*"
            holder.wordField.text = "Not found"
        } else {
            holder.indexField.text = (position + 1).toString()
            holder.wordField.text = things[position].malayalam_definition
        }

    }

    fun newdata(feed: List<DictionaryWord>) {
        things = feed
        notifyDataSetChanged()
    }
}