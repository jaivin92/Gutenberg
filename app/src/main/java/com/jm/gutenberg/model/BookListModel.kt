package com.jm.gutenberg.model

data class BookListModel<T> (
    val count : Int,
    val next : String,
    val previous : String,
    val results :List<T>
)