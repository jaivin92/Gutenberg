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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.fiction.setOnClickListener(this)
        binding.drama.setOnClickListener(this)
        binding.humor.setOnClickListener (this)
        binding.politics.setOnClickListener (this)
        binding.philosophy.setOnClickListener (this)
        binding.history.setOnClickListener (this)
        binding.adventure.setOnClickListener (this)
    }



    override fun onClick(view: View?) {
            when(view?.id){
                R.id.fiction->{
                    openActivity(baseContext.getString(R.string.fiction))
                }
                R.id.drama->{
                    openActivity(baseContext.getString(R.string.drama))
                }
                R.id.humor->{
                    openActivity(baseContext.getString(R.string.humor))
                }
                R.id.politics->{
                    openActivity(baseContext.getString(R.string.politics))
                }
                R.id.philosophy->{
                    openActivity(baseContext.getString(R.string.philosophy))
                }
                R.id.history->{
                    openActivity(baseContext.getString(R.string.history))
                }
                R.id.adventure->{
                    openActivity(baseContext.getString(R.string.adventure))
                }
            }
    }

    private fun openActivity (header : String){
        val intent : Intent = Intent(this, MainActivity2::class.java);
        intent.putExtra("header", header)
        startActivity(intent)
    }
}