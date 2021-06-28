package com.zak.datauses.network

import android.content.Context
import android.util.Log
import com.zak.datauses.BuildConfig
import com.zak.datauses.data.response.DatastoreResponse
import com.zak.datauses.data.response.NetworkErrors
import com.zak.datauses.network.action.OnDatastoreResponse
import com.zak.datauses.utilities.Config
import com.zak.datauses.utilities.Utils.checkInternetConnection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.net.ConnectException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIClient @Inject constructor(var context: Context) {
    private val apiInterface: APIInterface
    private val retrofit: Retrofit
    fun getMobileDataUsage(onDatastoreResponse: OnDatastoreResponse) {
        val resourceId = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f"
        val limit: Int? = null
        val datastoreResponse = apiInterface.getMobileDataUsage(resourceId, limit)
        datastoreResponse!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<DatastoreResponse?>() {
                    override fun onSuccess(datastoreResponse: DatastoreResponse) {
                        if (datastoreResponse.success == true) {
                            Log.d(TAG, "onSuccess: " + datastoreResponse.success)
                            onDatastoreResponse.onSuccessDatastoreResponse(datastoreResponse)
                        } else {
                            Log.e(TAG, "onSuccess: " + datastoreResponse.success)
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "onError: " + e.message, e)
                        onDatastoreResponse.onErrorResponse(handleErrors(e))
                    }
                })
    }

    fun handleErrors(error: Throwable?): String? {
        return if (error is HttpException) {
            val body = error.response().errorBody()
            handleError(body)
        } else if (error is ConnectException) {
            "Connection error"
        } else {
            "Error occurred"
        }
    }

    private fun handleError(errorBody: ResponseBody?): String? {
        val errorConverter = retrofit.responseBodyConverter<NetworkErrors>(NetworkErrors::class.java, arrayOfNulls(0))
        var networkErrors: NetworkErrors? = null
        try {
            networkErrors = errorConverter.convert(errorBody) // Convert the error body into custom Error type.
            Log.e("Error occurred", networkErrors.status.toString() + " " + networkErrors.message)
            return when (networkErrors.status) {
                Config.AUTHENTICATION_ERROR -> "Error occurred while authenticating"
                Config.INTERNAL_SERVER_ERROR -> "Internal server error"
                else -> "Unexpected error occurred"
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return networkErrors!!.message
    }

    companion object {
        private const val TAG = "APIClient"
    }

    init {
        // use 10MB cache
        val cacheSize = 10 * 1024 * 1024
        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())
        val client = OkHttpClient.Builder().cache(cache).addNetworkInterceptor { chain: Interceptor.Chain ->
            val response = chain.proceed(chain.request())
            val maxAge = 60 // read from cache for 60 seconds even if there is internet connection
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Pragma")
                    .build()
        }.addInterceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            if (!checkInternetConnection(context)) {
                val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .removeHeader("Pragma")
                        .build()
            }
            chain.proceed(request)
        }.build()
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        apiInterface = retrofit.create(APIInterface::class.java)
    }
}