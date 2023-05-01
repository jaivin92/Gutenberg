package com.jm.gutenberg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.jm.gutenberg.api.Resource
import com.jm.gutenberg.base.BaseActivity
import com.jm.gutenberg.databinding.ActivityMainBinding
import com.jm.gutenberg.viewmodel.MainActivityViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() , View.OnClickListener {
    override fun createBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private val mainActivityViewModel  by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mainActivityViewModel.getAllBooksResponse()
        //allbooksObservable()
        //Glide.with(this).load("http://www.gutenberg.org/cache/epub/345/pg345.cover.medium.jpg").into(binding.image)
//        binding.fiction.setOnClickListener {
//            startActivity(Intent(this, MainActivity2::class.java))
//        }
        binding.fiction.setOnClickListener(this)
        binding.drama.setOnClickListener(this)
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

    override fun onClick(view: View?) {
            when(view?.id){
                R.id.fiction->{
                    openActivity(baseContext.getString(R.string.fiction))
                }
                R.id.drama->{
                    openActivity(baseContext.getString(R.string.drama))
                }
            }
    }

    private fun openActivity (header : String){
        val intent : Intent = Intent(this, MainActivity2::class.java);
        intent.putExtra("header", header)
        startActivity(intent)
    }
}