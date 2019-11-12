package com.willlockwood.moshiretrofitexample

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseObject(
    var user: String,
    var likes: Int,
//    var largeImageURL: String,
    @field:Json(name = "largeImageURL") var url: String
) {
//    @PrimaryKey(autoGenerate=true)
//    var id: Int = 0
}