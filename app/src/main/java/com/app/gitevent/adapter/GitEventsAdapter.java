package com.app.gitevent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    Context mContext;
    boolean isLoadingFeeds = false;
    private LoadMoreCallbacks mLoadMoreCallbacks;

    public GitEventsAdapter(List<GitEvent> events, Context context, LoadMoreCallbacks loadMoreCallbacks) {
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
