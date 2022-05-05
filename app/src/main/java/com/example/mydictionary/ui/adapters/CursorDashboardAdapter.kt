package com.example.mydictionary.ui.adapters

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydictionary.databinding.WordItemBinding
import com.example.mydictionary.extensions.toColoredSpannable

class CursorDashboardAdapter : RecyclerView.Adapter<CursorDashboardAdapter.Holder>() {
    private var cursor: Cursor? = null
    private var query: String? = null

    fun submitCursor(cursor: Cursor, query: String? = null) {
        this.cursor = cursor
        this.query = query
        notifyDataSetChanged()
    }

    inner class Holder(private val wordItemBinding: WordItemBinding) :
        RecyclerView.ViewHolder(wordItemBinding.root) {
        fun bind() {
            cursor?.let {
                it.moveToPosition(absoluteAdapterPosition)
                wordItemBinding.word.text = it.getString(it.getColumnIndexOrThrow("en_word"))
                    .toColoredSpannable(query.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(WordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()

    override fun getItemCount(): Int {
        cursor?.let { return it.count }
        return 0
    }
}