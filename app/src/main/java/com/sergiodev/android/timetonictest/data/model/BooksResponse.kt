package com.sergiodev.android.timetonictest.data.model

data class BooksResponse(
    val status: String,
    val allBooks: AllBooks,
)

data class AllBooks(
    val books: List<Book>,
    val nbBooks: Int,
)
