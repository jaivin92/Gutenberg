package com.jm.gutenberg.api

import com.jm.gutenberg.model.BookListModel
import com.jm.gutenberg.model.SingelBook
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {

    @GET("/books")
    suspend fun  getAllBooks() : Response<BookListModel<SingelBook>>

    //http://skunkworks.ignitesol.com:8000/books/?search=sam
    @POST("/books")
    suspend fun  searchBooks(@Query("search") search:String) : Response<BookListModel<SingelBook>>
}