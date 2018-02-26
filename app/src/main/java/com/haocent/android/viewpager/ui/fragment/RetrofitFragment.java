package com.haocent.android.viewpager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.haocent.android.viewpager.R;
import com.haocent.android.viewpager.api.GitHubService;
import com.haocent.android.viewpager.data.RetrofitContributorsBean;
import com.haocent.android.viewpager.retrofit.RetrofitClient;
import com.haocent.android.viewpager.ui.adapter.RetrofitAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Retrofit fragment
 *
 * Created by Tnno Wu on 2018/02/26.
 */

public class RetrofitFragment extends Fragment {

    private static final String TAG = RetrofitFragment.class.getSimpleName();

    private GitHubService mService;

    private ProgressBar mProgressBar;

    private RetrofitAdapter mAdapter;

    public RetrofitFragment() {
        // Requires empty public constructor
    }

    public static RetrofitFragment newInstance() {
        return new RetrofitFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retrofit, container, false);

        initView(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initService();

        // 加载数据
        loadData();
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rcv_retrofit);
        mProgressBar = view.findViewById(R.id.progressbar);

        mAdapter = new RetrofitAdapter(getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
    }

    private void initService() {
        mService = new RetrofitClient().getService();
    }

    private void loadData() {
        Observable<List<RetrofitContributorsBean>> observable = mService.getRetrofitContributors();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RetrofitContributorsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");

                        mProgressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(List<RetrofitContributorsBean> retrofitContributorsBeans) {
                        Log.d(TAG, "onNext: ");

                        mAdapter.setDataList(retrofitContributorsBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");

                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }
}
