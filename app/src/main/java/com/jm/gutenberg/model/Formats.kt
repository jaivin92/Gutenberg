package com.jm.gutenberg.model

import com.google.gson.annotations.SerializedName


data class Formats(
    //@Json(name = "image/jpeg")
    @SerializedName("image/jpeg")
    val imageJPEG:String,

    @SerializedName("text/plain; charset=utf-8")
    val html:String,
    )


