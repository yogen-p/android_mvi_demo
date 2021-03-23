package com.yogenp.mvidemo.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogNetworkEntity(

    @SerializedName("pk")
    @Expose
    var id: Int,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("body")
    @Expose
    var body: String,

    @SerializedName("img")
    @Expose
    var img: String,

    @SerializedName("category")
    @Expose
    var category: String
)
