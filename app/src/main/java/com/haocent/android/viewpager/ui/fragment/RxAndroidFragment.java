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
import com.haocent.android.viewpager.data.RxAndroidContributorsBean;
import com.haocent.android.viewpager.retrofit.RetrofitClient;
import com.haocent.android.viewpager.ui.adapter.RxAndroidAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * RxAndroid fragment
 *
 * Created by Tnno Wu on 2018/02/26.
 */

public class RxAndroidFragment extends Fragment {

    private static final String TAG = RxAndroidFragment.class.getSimpleName();

    private boolean isFirst = true;

    private GitHubService mService;

    private ProgressBar mProgressBar;

    private RxAndroidAdapter mAdapter;

    public RxAndroidFragment() {
        // Requires empty public constructor
    }

    public static RxAndroidFragment newInstance() {
        return new RxAndroidFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxandroid, container, false);

        initView(view);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && isFirst) {
            Log.d(TAG, "RxAndroidFragment 首次加载");

            isFirst = false;

            // 加载数据
            loadData();
        }
    }

    private void initService() {
        mService = new RetrofitClient().getService();
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rcv_rxandroid);
        mProgressBar = view.findViewById(R.id.progressbar);

        mAdapter = new RxAndroidAdapter(getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        Observable<List<RxAndroidContributorsBean>> observable = mService.getRxAndroidContributors();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RxAndroidContributorsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");

                        mProgressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(List<RxAndroidContributorsBean> rxAndroidContributorsBeans) {
                        Log.d(TAG, "onNext: ");

                        mAdapter.setDataList(rxAndroidContributorsBeans);
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
