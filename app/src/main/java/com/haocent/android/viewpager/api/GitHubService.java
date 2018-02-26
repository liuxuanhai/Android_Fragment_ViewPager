package com.haocent.android.viewpager.api;

import com.haocent.android.viewpager.data.AuthorBean;
import com.haocent.android.viewpager.data.GlideContributorsBean;
import com.haocent.android.viewpager.data.RetrofitContributorsBean;
import com.haocent.android.viewpager.data.RxAndroidContributorsBean;
import com.haocent.android.viewpager.data.RxJavaContributorsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Tnno Wu on 2018/02/26.
 */

public interface GitHubService {

    /**
     * Retrofit contributors
     *
     * https://api.github.com/repos/square/retrofit/contributors
     */
    @GET("/repos/square/retrofit/contributors")
    Observable<List<RetrofitContributorsBean>> getRetrofitContributors();

    /**
     * RxJava contributors
     *
     * https://api.github.com/repos/ReactiveX/RxJava/contributors
     */
    @GET("/repos/ReactiveX/RxJava/contributors")
    Observable<List<RxJavaContributorsBean>> getRxJavaContributors();

    /**
     * RxAndroid contributors
     *
     * https://api.github.com/repos/ReactiveX/RxAndroid/contributors
     */
    @GET("/repos/ReactiveX/RxAndroid/contributors")
    Observable<List<RxAndroidContributorsBean>> getRxAndroidContributors();

    /**
     * Glide contributors
     *
     * https://api.github.com/repos/bumptech/glide/contributors
     */
    @GET("/repos/bumptech/glide/contributors")
    Observable<List<GlideContributorsBean>> getGlideContributors();

    /**
     * Author
     *
     * https://api.github.com/users/cnwutianhao
     */
    @GET("/users/cnwutianhao")
    Observable<AuthorBean> getAuthor();
}
