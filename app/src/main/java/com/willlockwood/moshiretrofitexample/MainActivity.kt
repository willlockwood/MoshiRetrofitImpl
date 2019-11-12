package com.willlockwood.moshiretrofitexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: BaseRecyclerAdapter
    private lateinit var compositeDisposable: CompositeDisposable

    private val moshiRetrofitApiService by lazy { MoshiRetrofitApiService.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

        initRecyclerView()

        apiCall()
    }

    private fun initRecyclerView() {
        recyclerView = recycler_view
        recyclerAdapter = BaseRecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter.also {
            it.setObjects(emptyList())
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.e("PageFragment", "error", throwable)
    }

    private fun handleResponse(response: RetrofitApiResponse) {
        recyclerAdapter.setObjects(response.hits)
    }

    private fun apiCall() {
        compositeDisposable.add(moshiRetrofitApiService.getRxPhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse) {
                handleError(it)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
