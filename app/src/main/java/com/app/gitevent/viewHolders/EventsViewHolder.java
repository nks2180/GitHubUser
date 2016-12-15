package com.app.gitevent.viewHolders;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.app.gitevent.R;
import com.app.gitevent.customViews.GitTextView;
import com.app.gitevent.model.GitEvent;
import com.app.gitevent.utils.GitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niranjan on 12/13/16.
 */

public class EventsViewHolder extends RecyclerView.ViewHolder {

    Context mContext;

    @BindView(R.id.txtVw_dateTime)
    GitTextView txtVwDateTime;
    @BindView(R.id.txtVw_eventName)
    GitTextView txtVwEventName;
    @BindView(R.id.txtVw_branch)
    GitTextView txtVwBranch;
    @BindView(R.id.txtVw_description)
    GitTextView txtVwDescription;
    @BindView(R.id.imgVw_avatar)
    ImageView imgVwAvatar;

    public static
    @LayoutRes
    int getLayoutResource() {
        return R.layout.row_events;
    }

    public EventsViewHolder(View v, Context context) {
        super(v);
        this.mContext = context;
        ButterKnife.bind(this, v);
    }

    public void loadDataIntoUI(EventsViewHolder eventsViewHolder, GitEvent event) {
        txtVwEventName.setText(event.getType());
        eventsViewHolder.txtVwDateTime.setText(GitUtils.getTimeAgo(GitUtils.yyyyMMddhhmmZ, event.getCreatedAt()));
        GitUtils.loadRoundedImageThroughPicasso(mContext, event.getActor().getAvatarUrl(), eventsViewHolder.imgVwAvatar, R.drawable.ic_account);

        eventsViewHolder.txtVwBranch.setText(event.getRepo().getName());

        eventsViewHolder.txtVwDescription.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(event.getPayload().getDescription()))
            eventsViewHolder.txtVwDescription.setText(event.getPayload().getDescription());
        else {
            if (null != event.getPayload().getIssue() && !TextUtils.isEmpty(event.getPayload().getIssue().getTitle()))
                eventsViewHolder.txtVwDescription.setText(event.getPayload().getIssue().getTitle());
            else
                eventsViewHolder.txtVwDescription.setVisibility(View.GONE);
        }
    }

}
