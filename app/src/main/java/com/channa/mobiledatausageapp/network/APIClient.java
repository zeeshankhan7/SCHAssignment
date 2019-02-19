package com.channa.mobiledatausageapp.network;

import android.content.Context;
import android.util.Log;

import com.channa.mobiledatausageapp.BuildConfig;
import com.channa.mobiledatausageapp.data.response.DatastoreResponse;
import com.channa.mobiledatausageapp.network.action.OnDatastoreResponse;
import com.channa.mobiledatausageapp.utility.Utils;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class APIClient {

    private static final String TAG = "APIClient";

    private APIInterface apiInterface;
    private Retrofit retrofit;

    Context context;

    @Inject
    public APIClient(Context context) {
        this.context = context;
        // use 10MB cache
        int cacheSize = 10 * 1024 * 1024;
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder().cache(cache).addNetworkInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            int maxAge = 60; // read from cache for 60 seconds even if there is internet connection
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        }).addInterceptor(chain -> {
            Request request = chain.request();
            if (!Utils.checkInternetConnection(context)) {
                int maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return chain.proceed(request);
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        this.apiInterface = retrofit.create(APIInterface.class);
    }

    public void getMobileDataUsage(OnDatastoreResponse onDatastoreResponse) {
        String resourceId = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f";
        Integer limit = null;

        Single<DatastoreResponse> datastoreResponse = apiInterface.getMobileDataUsage(resourceId, limit);
        datastoreResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<DatastoreResponse>() {
                    @Override
                    public void onSuccess(DatastoreResponse datastoreResponse) {
                        if (datastoreResponse.getSuccess()) {
                            Log.d(TAG, "onSuccess: " + datastoreResponse.getSuccess());

                            onDatastoreResponse.onSuccessDatastoreResponse(datastoreResponse);

                        } else {
                            Log.e(TAG, "onSuccess: " + datastoreResponse.getSuccess());
                            onDatastoreResponse.onErrorResponse(new Exception("Datastore response not successful"));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                        onDatastoreResponse.onErrorResponse(e);
                    }
                });
    }

}

