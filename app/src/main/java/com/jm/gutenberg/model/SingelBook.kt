package com.jm.gutenberg.model

data class SingelBook (
    val id:Int,
    val title:String,
    val formats:Formats,
    val authors :List<Authors>
        )