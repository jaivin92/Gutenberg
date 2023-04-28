package com.jm.gutenberg.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jm.gutenberg.api.BaseRepository
import com.jm.gutenberg.api.Event
import com.jm.gutenberg.api.Resource
import com.jm.gutenberg.model.BookListModel
import com.jm.gutenberg.model.SingelBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(app: Application, private val repository: BaseRepository) : AndroidViewModel(app){

    private val _allBooks = MutableLiveData<Event<Resource<BookListModel<SingelBook>>>>()
    val allBooks : LiveData<Event<Resource<BookListModel<SingelBook>>>> = _allBooks

    fun getAllBooksResponse () = viewModelScope.launch {
            getAllBooks()
    }


    private suspend fun getAllBooks(){
        _allBooks.postValue(Event(Resource.Loading()))
        var response = repository.getAllBooks()
        _allBooks.postValue(getAllBooksEvent(response))
    }

    private fun getAllBooksEvent(response: Response<BookListModel<SingelBook>>) : Event<Resource<BookListModel<SingelBook>>> {
            if(response.isSuccessful){
                    response.body()?.let {
                        result -> return Event(Resource.Success(result))
                    }
            }
        return Event(Resource.Error(response.message()))
    }
}