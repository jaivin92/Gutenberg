package com.jm.gutenberg

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jm.gutenberg.databinding.BookcardBinding
import com.jm.gutenberg.model.SingelBook
import com.squareup.picasso.Picasso
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.net.URL

class BookListView ( private val listdata : List<SingelBook>, private  val  context: Context) : RecyclerView.Adapter<BookListView.BookViewHolder>(){
    inner class BookViewHolder(val bookcardBinding: BookcardBinding): RecyclerView.ViewHolder(bookcardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(BookcardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        with(holder){
            with(listdata[position]){
                bookcardBinding.bookname.setText(this.title)
                if(this.formats.imageJPEG != null){
                    Log.e("taggg", "onBindViewHolder: " + this.formats.imageJPEG )
                    Glide.with(context).load(this.formats.imageJPEG).into(bookcardBinding.bookimage)

                    bookcardBinding.bookimage.setOnClickListener {
                        val intent : Intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("page", this.formats.html)
                        context.startActivity(intent)
                    }

                    //Glide.with(context).load(this.formats.imageJPEG).error(R.drawable.pattern).into(bookcardBinding.bookimage)
                    //Picasso.get().load(this.formats.imageJPEG).into(bookcardBinding.bookimage)

                }
            }

        }
    }
}