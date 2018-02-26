package com.haocent.android.viewpager.retrofit;

import com.haocent.android.viewpager.api.GitHubService;
import com.haocent.android.viewpager.data.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tnno Wu on 2018/02/26.
 */

public class RetrofitClient {

    public GitHubService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(GitHubService.class);
    }
}
