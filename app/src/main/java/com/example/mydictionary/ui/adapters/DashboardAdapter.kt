package com.example.mydictionary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mydictionary.data.model.WordModel
import com.example.mydictionary.data.source.local.entities.WordEntity
import com.example.mydictionary.data.source.local.entities.toWordModel
import com.example.mydictionary.databinding.WordItemBinding


class DashboardAdapter : ListAdapter<WordEntity, DashboardAdapter.Holder>(WordCallBack) {
    private lateinit var listener: (WordModel) -> Unit
    fun setOnItemClickListener(l: (WordModel) -> Unit) {
        listener = l
    }

    object WordCallBack : DiffUtil.ItemCallback<WordEntity>() {
        override fun areItemsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
            return oldItem.word == newItem.word
        }
    }

    inner class Holder(private val wordItemBinding: WordItemBinding) :
        RecyclerView.ViewHolder(wordItemBinding.root) {
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            wordItemBinding.word.text = item.word
        }

        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(absoluteAdapterPosition).toWordModel())
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        WordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}