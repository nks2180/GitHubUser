package com.app.gitevent.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.app.gitevent.executor.ParsingExecutor;
import com.app.gitevent.model.GitEvent;
import com.app.gitevent.retrofit.ApiController;
import com.app.gitevent.retrofit.ApiDataReceiveCallback;
import com.app.gitevent.retrofit.NetworkConstants;
import com.app.gitevent.retrofit.RequestBuilder;
import com.app.gitevent.utils.GitUtils;
import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by niranjan on 12/13/16.
 */

public class GitEventsPresenter extends BasePresenterImpl<GitEventsView> implements ApiDataReceiveCallback {

    @Inject
    ApiController apiController;

    @Inject
    ParsingExecutor parsingExecutor;

    private int mPageCount = 1;
    private boolean isLoadMoreAllowed = true;
    boolean mIsSwipeRefresh = false;
    String mUserName = "";

    @Inject
    GitEventsPresenter(Context baseContext) {
        super(baseContext);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public void fetchEvents(boolean isSwipeRefresh) {
        if (!GitUtils.isConnected(baseContext)) {
            view.onEventsResponseCame(null, isLoadMoreAllowed, isSwipeRefresh);
            return;
        }
        mIsSwipeRefresh =  isSwipeRefresh;
        if (mIsSwipeRefresh)
            mPageCount = 1;
        RequestBuilder requestBuilder = new RequestBuilder(NetworkConstants.API_FETCH_GIT_EVENTS);
        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("page", String.valueOf(mPageCount));
        requestParams.put("per_page", String.valueOf(GitUtils.RESULT_PER_PAGE_COUNT));
        requestParams.put(GitUtils.STR_USER_NAME, mUserName);
        requestBuilder.setExtraParameters(requestParams);
        apiController.hitApi(requestBuilder, this);
    }

    @Override
    public void onDataReceived(String response, int type) {
        switch (type) {
            case NetworkConstants.API_FETCH_GIT_EVENTS:
                if (!TextUtils.isEmpty(response)) {
                    boolean isLoadMoreAllowed = false;
                    List<GitEvent> events = parseResponse(response);
                    if (events.size() >= GitUtils.RESULT_PER_PAGE_COUNT) {
                        mPageCount++;
                        isLoadMoreAllowed = true;
                    }
                    view.onEventsResponseCame(parseResponse(response), isLoadMoreAllowed, mIsSwipeRefresh);

                }
                else
                    view.onEventsResponseCame(null, isLoadMoreAllowed, mIsSwipeRefresh);

                break;

        }

    }

    @Override
    public void onError(int type) {
        view.onEventsResponseCame(null, isLoadMoreAllowed, mIsSwipeRefresh);
    }

    private List<GitEvent> parseResponse(String response) {
        try {
            return LoganSquare.parseList(response, GitEvent.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
