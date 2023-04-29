package com.jm.gutenberg

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.jm.gutenberg.api.Resource
import com.jm.gutenberg.base.BaseActivity
import com.jm.gutenberg.databinding.ActivityMain2Binding
import com.jm.gutenberg.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : BaseActivity<ActivityMain2Binding> () {

    override fun createBinding(): ActivityMain2Binding = ActivityMain2Binding.inflate(layoutInflater)
    private val mainActivityViewModel  by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityViewModel.getAllBooksResponse()
        allbooksObservable()
    }
    private  fun allbooksObservable(){
        mainActivityViewModel.allBooks.observe(this@MainActivity2){
                event ->
            event.getContentIfNotHandled()?.let {
                    resource ->
                when (resource){
                    is Resource.Success -> {
                        Log.d(TAG, "allbooksObservable: " + resource.data?.results!![1] )
                        binding.listview.adapter = BookListView(resource.data?.results,this)
                    }
                    is Resource.Error -> {
                        Log.e(TAG, "allbooksObservable: " + resource.message )
                    }
                    is Resource.ErrorBody -> {
                        Log.e(TAG, "allbooksObservable: " + resource.message  )
                    }
                    is Resource.Loading -> {
                        Log.e(TAG, "allbooksObservable: "+ "Start" )
                    }
                }
            }
        }
    }

}