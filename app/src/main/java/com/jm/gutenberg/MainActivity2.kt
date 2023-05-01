package com.jm.gutenberg

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
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
        binding.search.addTextChangedListener(textWatcher)
        binding.headerTitle.setText(intent.getStringExtra("header"))
        mainActivityViewModel.getAllBooksResponse()
        binding.back!!.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        allbooksObservable()
    }

    private val textWatcher = object :TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //TODO("Not yet implemented")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //TODO("Not yet implemented")
//            binding.search.setText(s.toString())
            if(binding.search.text?.length!! > 0) {
                binding.search.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this@MainActivity2, R.drawable.wearch), null, ContextCompat.getDrawable(this@MainActivity2, R.drawable.cancel), null)
            } else{
                binding.search.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this@MainActivity2, R.drawable.wearch),null, null,  null)
            }
        }

        override fun afterTextChanged(s: Editable?) {
            //TODO("Not yet implemented")
            mainActivityViewModel.getSearchBook(s.toString())
            searchObservable()
        }

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

    private  fun searchObservable(){
        mainActivityViewModel.search.observe(this@MainActivity2){
                event ->
            event.getContentIfNotHandled()?.let {
                    resource ->
                when (resource){
                    is Resource.Success -> {
                        try {
                        Log.d(TAG, "allbooksObservable: " + resource.data?.results!![1] )
                        binding.listview.adapter = BookListView(resource.data?.results,this)
                        } catch (e:Exception){
                            Log.e(TAG, "searchObservable: " + e.message )
                        }
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        newConfig.orientation
    }
}