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
import com.haocent.android.viewpager.data.GlideContributorsBean;
import com.haocent.android.viewpager.glide.GlideApp;

import java.util.List;

/**
 * Created by Tnno Wu on 2018/02/26.
 */

public class GlideAdapter extends RecyclerView.Adapter<GlideAdapter.GlideViewHolder> {

    private static final String TAG = GlideAdapter.class.getSimpleName();

    private Context mContext;

    private List<GlideContributorsBean> mList;

    public GlideAdapter(Context context) {
        mContext = context;
    }

    public void setDataList(List<GlideContributorsBean> list) {
        mList = list;

        Log.d(TAG, "setDataList: " + mList);

        notifyDataSetChanged();
    }

    @Override
    public GlideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_glide, parent, false);
        return new GlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GlideViewHolder holder, int position) {
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

    public class GlideViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAvatar;
        TextView tvLogin, tvHtmlUrl;

        public GlideViewHolder(View itemView) {
            super(itemView);

            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvLogin = itemView.findViewById(R.id.tv_login);
            tvHtmlUrl = itemView.findViewById(R.id.tv_html_url);
        }
    }
}
