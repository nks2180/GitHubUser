package com.app.gitevent.viewHolders;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.app.gitevent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niranjan on 12/14/16.
 */

public class LoadMoreViewHolder extends RecyclerView.ViewHolder {
    public final static int VIEW_TYPE_LOAD_MORE = 3000;
    Context mContext;
    @BindView(R.id.prgrs_loadMore)
    ProgressBar prgrsLoadMore;

    public static
    @LayoutRes
    int getLayoutResource() {
        return R.layout.layout_load_more;
    }

    public LoadMoreViewHolder(View v, Context context) {
        super(v);
        this.mContext = context;
        ButterKnife.bind(this, v);
    }

    public void toggleLoadMoreVisibility(boolean showLoadMore) {
        if (showLoadMore)
            prgrsLoadMore.setVisibility(View.VISIBLE);
        else
            prgrsLoadMore.setVisibility(View.GONE);
    }
}
