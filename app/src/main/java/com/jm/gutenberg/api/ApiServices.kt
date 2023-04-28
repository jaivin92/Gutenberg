package com.jm.gutenberg.api

import com.jm.gutenberg.model.BookListModel
import com.jm.gutenberg.model.SingelBook
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("/books")
    suspend fun  getAllBooks() : Response<BookListModel<SingelBook>>
}