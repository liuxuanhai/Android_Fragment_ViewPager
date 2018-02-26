package com.haocent.android.viewpager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haocent.android.viewpager.R;
import com.haocent.android.viewpager.data.RxJavaContributorsBean;
import com.haocent.android.viewpager.glide.GlideApp;

import java.util.List;

/**
 * Created by Tnno Wu on 2018/02/26.
 */

public class RxJavaAdapter extends RecyclerView.Adapter<RxJavaAdapter.RxJavaViewHolder> {

    private static final String TAG = RxJavaAdapter.class.getSimpleName();

    private Context mContext;

    private List<RxJavaContributorsBean> mList;

    public RxJavaAdapter(Context context) {
        mContext = context;
    }

    public void setDataList(List<RxJavaContributorsBean> list) {
        mList = list;

        Log.d(TAG, "setDataList: " + mList);

        notifyDataSetChanged();
    }

    @Override
    public RxJavaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rxjava, parent, false);
        return new RxJavaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RxJavaViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + mList.get(position).getAvatar_url());
        Log.d(TAG, "onBindViewHolder: " + mList.get(position).getLogin());
        Log.d(TAG, "onBindViewHolder: " + mList.get(position).getHtml_url());

        // Avatar
        GlideApp.with(mContext).load(mList.get(position).getAvatar_url()).into(holder.ivAvatar);

        // Login
        holder.tvLogin.setText(mList.get(position).getLogin());

        // Html url
        holder.tvHtmlUrl.setText(mList.get(position).getHtml_url());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class RxJavaViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAvatar;
        TextView tvLogin, tvHtmlUrl;

        public RxJavaViewHolder(View itemView) {
            super(itemView);

            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvLogin = itemView.findViewById(R.id.tv_login);
            tvHtmlUrl = itemView.findViewById(R.id.tv_html_url);
        }
    }
}
