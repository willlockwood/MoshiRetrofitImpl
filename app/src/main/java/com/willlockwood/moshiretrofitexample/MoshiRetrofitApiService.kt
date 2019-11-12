package com.willlockwood.moshiretrofitexample

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoshiRetrofitApiService {

    companion object {

        const val API_KEY = "12995248-024645e7fdef25f159d1c27fa"
        const val photos_per_page = 10
        const val BASE_URL = "https://pixabay.com/api/"

//        val moshi = Moshi.Builder().build()

        fun create(): MoshiRetrofitApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(MoshiRetrofitApiService::class.java)
        }
    }

    @GET("http://pixabay.com/api/?image_type=photo")
    fun getPhotos(@Query("per_page") per_page: Int,
                  @Query("key") key: String):
            Call<RetrofitApiResponse>

    @GET("http://pixabay.com/api/?image_type=photo")
    fun getRxPhotos(@Query("per_page") per_page: Int = photos_per_page,
                    @Query("key") key: String = API_KEY
    ):
            Observable<RetrofitApiResponse>

}