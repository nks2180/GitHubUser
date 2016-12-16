package com.app.gitevent.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.gitevent.R;
import com.app.gitevent.adapter.GitEventsAdapter;
import com.app.gitevent.component.ApplicationComponent;
import com.app.gitevent.model.GitEvent;
import com.app.gitevent.model.LoginResponse;
import com.app.gitevent.presenter.GitEventsPresenter;
import com.app.gitevent.presenter.GitEventsView;
import com.app.gitevent.utils.GitUtils;
import com.app.gitevent.utils.LoadMoreCallbacks;
import com.app.gitevent.utils.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.GONE;

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
    @BindView(R.id.fab_account)
    ImageView fabAccount;
    @BindView(R.id.frmLyt_events)
    RelativeLayout frmLytEvents;
    private RecyclerView.LayoutManager mLayoutManager;

    Context mContext;
    List<GitEvent> mEvents = new ArrayList<>();
    GitEventsAdapter mAdapter;
    LoginResponse mUserAccount;

    @Override
    protected int getMainLayout() {
        return R.layout.activity_git_events;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        Bundle bundle = getIntent().getExtras();
        if (null != bundle && bundle.containsKey(GitUtils.EXTRA_ACOOUNT_OBJ))
            mUserAccount = (LoginResponse) bundle.getSerializable(GitUtils.EXTRA_ACOOUNT_OBJ);

        setUpRecyclerView();
        eventsPresenter.setUserName(mUserAccount.getLogin());
        eventsPresenter.fetchEvents(true);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               loadProfileImage();
            }
        }, 500);
    }

    private void loadProfileImage(){
        if (null != mUserAccount && !TextUtils.isEmpty(mUserAccount.getAvatarUrl())) {
            GitUtils.loadRoundedImageThroughPicasso(mContext, mUserAccount.getAvatarUrl(), fabAccount, R.drawable.ic_fab_account);

        }
    }

    private void setUpRecylerVw() {
        swipeRefreshLayoutEvents.setOnRefreshListener(this);
        GitUtils.setSwipeRefreshLayoutColor(swipeRefreshLayoutEvents);

        mAdapter = new GitEventsAdapter(mEvents, this, this);
        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerVwEvents.setLayoutManager(mLayoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_eight);
        recyclerVwEvents.addItemDecoration(new RecyclerItemDecoration(spacingInPixels));
        recyclerVwEvents.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        else{
                Toast.makeText(mContext, "Something went wrong..", Toast.LENGTH_SHORT).show();
        }
        prgrsLoading.setVisibility(GONE);
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

    @OnClick(R.id.fab_account)
    public void onSubmitBtnTapped() {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra(GitUtils.EXTRA_ACOOUNT_OBJ, mUserAccount);
        Pair<View, String> pairOne = Pair.create(fabAccount, getResources().getString(R.string.transition_fab_avatar));
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairOne);
        startActivity(profileIntent, options.toBundle());

    }

    @Override
    public void onBackPressed() {
        fabAccount.setVisibility(View.GONE);
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fabAccount.setVisibility(View.VISIBLE);
    }
}

