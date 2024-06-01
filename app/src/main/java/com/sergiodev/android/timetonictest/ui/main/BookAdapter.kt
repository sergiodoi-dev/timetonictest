package com.sergiodev.android.timetonictest.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sergiodev.android.timetonictest.R
import com.sergiodev.android.timetonictest.data.model.Book
import com.sergiodev.android.timetonictest.databinding.ItemBookBinding

class BookAdapter(private val books: List<Book>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {


    class BookViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.book = book
            Glide.with(itemView.context).load("https://timetonic.com${book.ownerPrefs.oCoverImg}").into(binding.bookImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_book, parent, false) as ItemBookBinding
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }
}