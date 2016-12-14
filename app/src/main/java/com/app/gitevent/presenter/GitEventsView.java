package com.app.gitevent.presenter;

import com.app.gitevent.model.GitEvent;

import java.util.List;

/**
 * Created by niranjan on 12/13/16.
 */

public interface GitEventsView  extends BaseView{

    void setUpRecyclerView();
    void onEventsResponseCame(List<GitEvent> events, boolean isLoadMoreAllowed, boolean isSwipeRefresh);
}
