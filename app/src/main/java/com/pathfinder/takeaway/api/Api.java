package com.pathfinder.takeaway.api;

import com.pathfinder.takeaway.components.retrofit.FastJsonConverterFactory;
import com.pathfinder.takeaway.components.retrofit.RequestHelper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class Api {

  static final String BASE_URL = "http://qyb2c.qianyansoft.com/";

  private ApiService mGameService;
  private RequestHelper mRequestHelper;

  public Api(RequestHelper mRequestHelper, OkHttpClient mOkHttpClient) {
    this.mRequestHelper = mRequestHelper;
    Retrofit retrofit =
        new Retrofit.Builder().addConverterFactory(FastJsonConverterFactory.create())
            .client(mOkHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    mGameService = retrofit.create(ApiService.class);
  }

}
