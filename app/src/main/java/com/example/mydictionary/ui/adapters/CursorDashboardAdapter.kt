package com.example.mydictionary.ui.adapters

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydictionary.data.model.WordModel
import com.example.mydictionary.data.source.local.entities.toWordModel
import com.example.mydictionary.databinding.WordItemBinding
import com.example.mydictionary.extensions.toColoredSpannable

class CursorDashboardAdapter : RecyclerView.Adapter<CursorDashboardAdapter.Holder>() {
    private var cursor: Cursor? = null
    private var query: String? = null
    private lateinit var listener: (WordModel) -> Unit
    fun setOnItemClickListener(l: (WordModel) -> Unit) {
        listener = l
    }

    fun submitCursor(cursor: Cursor, query: String? = null) {
        this.cursor = cursor
        this.query = query
        notifyDataSetChanged()
    }

    inner class Holder(private val wordItemBinding: WordItemBinding) :
        RecyclerView.ViewHolder(wordItemBinding.root) {
        fun bind(wordModel: WordModel) {
            cursor?.let {
                it.moveToPosition(absoluteAdapterPosition)
                wordItemBinding.word.text = it.getString(it.getColumnIndexOrThrow("en_word"))
//                    .toColoredSpannable(query.toString())
            }

            itemView.setOnClickListener {
                listener.invoke(wordModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(WordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (!cursor!!.moveToPosition(position)) return
        val item = cursor!!.let {
            WordModel(
                it.getInt(0),
                it.getString(1),
                it.getString(2),
                it.getString(3),
                it.getString(4),
                it.getString(5)
            )
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        cursor?.let { return it.count }
        return 0
    }
}