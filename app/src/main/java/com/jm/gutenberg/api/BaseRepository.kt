package com.jm.gutenberg.api

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseRepository @Inject constructor(
    @ApplicationContext val context: Context,
){

    suspend fun getAllBooks() = RetrofitHelper.connectAllData.getAllBooks()

    suspend fun getSearchBooks(search:String ) = RetrofitHelper.connectAllData.searchBooks(search = search)

}