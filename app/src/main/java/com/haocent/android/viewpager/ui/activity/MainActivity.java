package com.haocent.android.viewpager.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.haocent.android.viewpager.R;
import com.haocent.android.viewpager.ui.fragment.GlideFragment;
import com.haocent.android.viewpager.ui.fragment.RetrofitFragment;
import com.haocent.android.viewpager.ui.fragment.AuthorFragment;
import com.haocent.android.viewpager.ui.fragment.RxJavaFragment;
import com.haocent.android.viewpager.ui.fragment.RxAndroidFragment;

import java.util.ArrayList;

/**
 * Created by Tnno Wu on 2018/02/26.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();

        initView();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();

        RetrofitFragment retrofitFragment = RetrofitFragment.newInstance();
        RxJavaFragment rxJavaFragment = RxJavaFragment.newInstance();
        RxAndroidFragment rxAndroidFragment = RxAndroidFragment.newInstance();
        GlideFragment glideFragment = GlideFragment.newInstance();
        AuthorFragment authorFragment = AuthorFragment.newInstance();

        mFragments.add(retrofitFragment);
        mFragments.add(rxJavaFragment);
        mFragments.add(rxAndroidFragment);
        mFragments.add(glideFragment);
        mFragments.add(authorFragment);
    }

    private void initView() {
        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        final ViewPager viewPager = findViewById(R.id.viewpager);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        viewPager.setOffscreenPageLimit(mFragments.size());

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: ");

                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected: ");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: ");
            }
        });
    }
}
