package com.haocent.android.viewpager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haocent.android.viewpager.R;
import com.haocent.android.viewpager.api.GitHubService;
import com.haocent.android.viewpager.data.AuthorBean;
import com.haocent.android.viewpager.glide.GlideApp;
import com.haocent.android.viewpager.retrofit.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author fragment
 *
 * Created by Tnno Wu on 2018/02/26.
 */

public class AuthorFragment extends Fragment {

    private static final String TAG = AuthorFragment.class.getSimpleName();

    private boolean isFirst = true;

    private GitHubService mService;

    private RelativeLayout mRelativeLayout;
    private ImageView mIvAvatar;
    private TextView mTvLogin, mTvName, mTvCompany, mTvBio, mTvCreated, mTvUpdated;
    private ProgressBar mProgressBar;

    public AuthorFragment() {
        // Requires empty public constructor
    }

    public static AuthorFragment newInstance() {
        return new AuthorFragment();
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
        View view = inflater.inflate(R.layout.fragment_author, container, false);

        initView(view);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && isFirst) {
            Log.d(TAG, "AuthorFragment 首次加载");

            isFirst = false;

            // 加载数据
            loadData();
        }
    }

    private void initService() {
        mService = new RetrofitClient().getService();
    }

    private void initView(View view) {
        mRelativeLayout = view.findViewById(R.id.rl);
        mIvAvatar = view.findViewById(R.id.iv_avatar);
        mTvLogin = view.findViewById(R.id.tv_login);
        mTvName = view.findViewById(R.id.tv_name);
        mTvCompany = view.findViewById(R.id.tv_company);
        mTvBio = view.findViewById(R.id.tv_bio);
        mTvCreated = view.findViewById(R.id.tv_created);
        mTvUpdated = view.findViewById(R.id.tv_updated);

        mProgressBar = view.findViewById(R.id.progressbar);
    }

    private void loadData() {
        Observable<AuthorBean> observable = mService.getAuthor();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuthorBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");

                        mProgressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(AuthorBean authorBean) {
                        Log.d(TAG, "onNext: ");

                        Log.d(TAG, "onNext: " + authorBean.getAvatar_url());
                        Log.d(TAG, "onNext: " + authorBean.getLogin());
                        Log.d(TAG, "onNext: " + authorBean.getName());
                        Log.d(TAG, "onNext: " + authorBean.getCompany());
                        Log.d(TAG, "onNext: " + authorBean.getBio());
                        Log.d(TAG, "onNext: " + authorBean.getCreated_at());
                        Log.d(TAG, "onNext: " + authorBean.getUpdated_at());

                        // Avatar
                        GlideApp.with(getActivity()).load(authorBean.getAvatar_url()).into(mIvAvatar);

                        // Login
                        mTvLogin.setText(authorBean.getLogin());

                        // Name
                        mTvName.setText(authorBean.getName());

                        // Company
                        mTvCompany.setText(authorBean.getCompany());

                        // Bio
                        mTvBio.setText(authorBean.getBio());

                        // Created
                        String dateCreated = authorBean.getCreated_at();
                        SimpleDateFormat dffCreated = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        SimpleDateFormat df1Created = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date date1 = dffCreated.parse(dateCreated);
                            String strCreated = df1Created.format(date1);

                            Log.d(TAG, "onNext: " + strCreated);

                            mTvCreated.setText(strCreated);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        // Updated
                        String dateUpdated = authorBean.getUpdated_at();
                        SimpleDateFormat dffUpdated = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        SimpleDateFormat df1Updated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date date1 = dffUpdated.parse(dateUpdated);
                            String strUpdated = df1Updated.format(date1);

                            Log.d(TAG, "onNext: " + strUpdated);

                            mTvUpdated.setText(strUpdated);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");

                        mProgressBar.setVisibility(View.GONE);
                        mRelativeLayout.setVisibility(View.VISIBLE);
                    }
                });
    }
}
