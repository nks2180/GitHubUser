package com.app.gitevent.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.app.gitevent.R;
import com.app.gitevent.adapter.GitEventsAdapter;
import com.app.gitevent.component.ApplicationComponent;
import com.app.gitevent.model.GitEvent;
import com.app.gitevent.presenter.GitEventsPresenter;
import com.app.gitevent.presenter.GitEventsView;
import com.app.gitevent.utils.HtUtils;
import com.app.gitevent.utils.LoadMoreCallbacks;
import com.app.gitevent.utils.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by niranjan on 12/13/16.
 */

public class GitEventsActivity extends BaseViewPresenterActivity<GitEventsPresenter> implements GitEventsView, LoadMoreCallbacks, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    GitEventsPresenter eventsPresenter;

    @BindView(R.id.recyclerVw_events)
    RecyclerView recyclerVwEvents;
    @BindView(R.id.swipeRefreshLayout_events)
    SwipeRefreshLayout swipeRefreshLayoutEvents;
    @BindView(R.id.prgrs_loading)
    ProgressBar prgrsLoading;
    private RecyclerView.LayoutManager mLayoutManager;

    Context mContext;
    List<GitEvent> mEvents = new ArrayList<>();
    GitEventsAdapter mAdapter;


    @Override
    protected int getMainLayout() {
        return R.layout.activity_git_events;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setUpRecylerVw() {
        swipeRefreshLayoutEvents.setOnRefreshListener(this);
        HtUtils.setSwipeRefreshLayoutColor(swipeRefreshLayoutEvents);

        mAdapter = new GitEventsAdapter(mEvents, mContext, this);
        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerVwEvents.setLayoutManager(mLayoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_eight);
        recyclerVwEvents.addItemDecoration(new RecyclerItemDecoration(spacingInPixels));
        recyclerVwEvents.setAdapter(mAdapter);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void initializePresenter() {
        super.initializePresenter(eventsPresenter, this);
    }

    @Override
    public void setUpRecyclerView() {
        mContext = this;
        setUpRecylerVw();
    }

    @Override
    public void onEventsResponseCame(List<GitEvent> events, boolean isLoadMoreAllowed, boolean isSwipeRefresh) {
        if (null != events && events.size() > 0) {
            if (isSwipeRefresh)
                mEvents.clear();
            mEvents.addAll(events);
        }
        prgrsLoading.setVisibility(View.GONE);
        swipeRefreshLayoutEvents.setRefreshing(false);
        mAdapter.setIsLoadMoreFeeds(isLoadMoreAllowed);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        if (null != eventsPresenter)
            eventsPresenter.fetchEvents(false);
    }

    @Override
    public void onRefresh() {
        if (null != eventsPresenter)
            eventsPresenter.fetchEvents(true);
    }
}

