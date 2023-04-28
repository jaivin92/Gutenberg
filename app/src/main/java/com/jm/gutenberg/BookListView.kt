package com.jm.gutenberg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jm.gutenberg.databinding.BookcardBinding
import com.jm.gutenberg.model.SingelBook

class BookListView ( private val listdata : List<SingelBook>) : RecyclerView.Adapter<BookListView.BookViewHolder>(){

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
                Glide.with(holder.itemView.context).load(this.formats.imageJPEG).into(bookcardBinding.bookimage)
            }

        }
    }
}