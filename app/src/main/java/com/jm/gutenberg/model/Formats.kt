package com.jm.gutenberg.model

import net.pwall.json.annotation.JSONName

data class Formats(
    //@Json(name = "image/jpeg")
    @JSONName("image/jpeg")
    val imageJPEG:String)
