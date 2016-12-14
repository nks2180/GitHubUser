package com.app.gitevent.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by niranjan on 12/14/16.
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    int space;

    public RecyclerItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.right = 0;
        outRect.left = 0;
        outRect.top = space / 2;
        outRect.bottom = space / 2;
    }
}