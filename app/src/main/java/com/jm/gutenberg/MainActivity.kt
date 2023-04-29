package com.jm.gutenberg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.jm.gutenberg.api.Resource
import com.jm.gutenberg.base.BaseActivity
import com.jm.gutenberg.databinding.ActivityMainBinding
import com.jm.gutenberg.viewmodel.MainActivityViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun createBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private val mainActivityViewModel  by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mainActivityViewModel.getAllBooksResponse()
        //allbooksObservable()
        Glide.with(this).load("http://www.gutenberg.org/cache/epub/345/pg345.cover.medium.jpg").into(binding.image)
        binding.fiction.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    private  fun allbooksObservable(){
        mainActivityViewModel.allBooks.observe(this@MainActivity){
            event ->
            event.getContentIfNotHandled()?.let {
                    resource ->
                    when (resource){
                            is Resource.Success -> {
                                Log.d(TAG, "allbooksObservable: " + resource.data?.results!![0].title )
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