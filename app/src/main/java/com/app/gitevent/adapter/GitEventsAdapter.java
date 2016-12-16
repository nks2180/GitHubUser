package com.app.gitevent.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gitevent.R;
import com.app.gitevent.activity.GitEventDetailActivity;
import com.app.gitevent.model.GitEvent;
import com.app.gitevent.utils.GitUtils;
import com.app.gitevent.utils.LoadMoreCallbacks;
import com.app.gitevent.viewHolders.EventsViewHolder;
import com.app.gitevent.viewHolders.LoadMoreViewHolder;

import java.util.List;

/**
 * Created by niranjan on 12/13/16.
 */

public class GitEventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<GitEvent> mEvents;
    Activity mContext;
    boolean isLoadingFeeds = false;
    private LoadMoreCallbacks mLoadMoreCallbacks;

    public GitEventsAdapter(List<GitEvent> events, Activity context, LoadMoreCallbacks loadMoreCallbacks) {
        this.mEvents = events;
        this.mContext = context;
        this.mLoadMoreCallbacks = loadMoreCallbacks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case GitUtils.VIEW_TYPE_EVENTS:
                View eventsView = LayoutInflater.from(parent.getContext())
                        .inflate(EventsViewHolder.getLayoutResource(), parent, false);
                return new EventsViewHolder(eventsView, mContext);
            case GitUtils.VIEW_TYPE_LOAD_MORE:
                View loadMoreView = LayoutInflater.from(parent.getContext())
                        .inflate(LoadMoreViewHolder.getLayoutResource(), parent, false);
                return new LoadMoreViewHolder(loadMoreView, mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (mEvents.size() > 2 && position == mEvents.size() - 2 && null != mLoadMoreCallbacks) {

            mLoadMoreCallbacks.onLoadMore();
        }

        switch (viewHolder.getItemViewType()) {
            case GitUtils.VIEW_TYPE_EVENTS:
                GitEvent event = mEvents.get(position);
                EventsViewHolder eventsViewHolder = (EventsViewHolder) viewHolder;
                eventsViewHolder.loadDataIntoUI(eventsViewHolder, event);
                eventsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, GitEventDetailActivity.class);
                        intent.putExtra(GitUtils.EXTRA_EVENT_OBJ, event);
                        Pair<View, String> pairOne = Pair.create(eventsViewHolder.txtVwInitial, mContext.getResources().getString(R.string.transition_detail_initials));
                        ActivityOptionsCompat options =  ActivityOptionsCompat.makeSceneTransitionAnimation(mContext,pairOne);
                        mContext.startActivity(intent, options.toBundle());
                    }
                });
                break;
            case GitUtils.VIEW_TYPE_LOAD_MORE:
                LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) viewHolder;
                loadMoreViewHolder.toggleLoadMoreVisibility(isLoadingFeeds);
                break;
        }

    }

    @Override
    public int getItemCount() {
        if (mEvents.size() > 0)
            return mEvents.size() + 1;
        else
            return 0;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == mEvents.size())
            return GitUtils.VIEW_TYPE_LOAD_MORE;
        else
            return GitUtils.VIEW_TYPE_EVENTS;
    }

    public void setIsLoadMoreFeeds(boolean isLoading) {
        isLoadingFeeds = isLoading;
    }
}
