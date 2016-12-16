package com.app.gitevent.activity;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.app.gitevent.R;
import com.app.gitevent.customViews.GitTextView;
import com.app.gitevent.model.GitEvent;
import com.app.gitevent.utils.GitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niranjan on 12/16/16.
 */

public class GitEventDetailActivity extends AppCompatActivity {
    Context mContext;
    GitEvent mEvent;
    @BindView(R.id.txtVw_initial)
    GitTextView txtVwInitial;
    @BindView(R.id.txtVw_eventName)
    GitTextView txtVwEventName;
    @BindView(R.id.txtVw_branch)
    GitTextView txtVwBranch;
    @BindView(R.id.txtVw_dateTime)
    GitTextView txtVwDateTime;
    @BindView(R.id.txtVw_description)
    GitTextView txtVwDescription;
    @BindView(R.id.txtVw_actor)
    GitTextView txtVwActor;
    @BindView(R.id.txtVw_repoName)
    GitTextView txtVwRepoName;
    @BindView(R.id.txtVw_repoId)
    GitTextView txtVwRepoId;
    @BindView(R.id.txtVw_repoIsPublic)
    GitTextView txtVwRepoIsPublic;
    @BindView(R.id.txtVw_actorUrl)
    GitTextView txtVwActorUrl;
    @BindView(R.id.txtVw_payloadReference)
    GitTextView txtVwPayloadReference;
    @BindView(R.id.txtVw_payloadHead)
    GitTextView txtVwPayloadHead;
    @BindView(R.id.txtVw_pushId)
    GitTextView txtVwPushId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Event Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        if (null != bundle && bundle.containsKey(GitUtils.EXTRA_EVENT_OBJ))
            mEvent = (GitEvent) bundle.getSerializable(GitUtils.EXTRA_EVENT_OBJ);
        if (null != mEvent)
            loadDataIntoUI();


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

    private void loadDataIntoUI() {
        txtVwDateTime.setText(GitUtils.getTimeAgo(GitUtils.yyyyMMddhhmmZ, mEvent.getCreatedAt()));
        txtVwBranch.setText(mEvent.getRepo().getName());
        GitUtils.setTitleDetailText("Actor Id:", mEvent.getActor().getLogin(), txtVwActor);
        GitUtils.setTitleDetailText("Repo Id:", String.valueOf(mEvent.getRepo().getId()), txtVwRepoId);
        GitUtils.setTitleDetailText("Repo Name:", mEvent.getRepo().getName(), txtVwRepoName);
        GitUtils.setTitleDetailText("Is Repo Public:", String.valueOf(mEvent.isPublicX()), txtVwRepoIsPublic);
        GitUtils.setTitleDetailText("Actor Url:", mEvent.getActor().getUrl(), txtVwActorUrl);
        GitUtils.setTitleDetailText("Payload Reference:", String.valueOf(mEvent.getPayload().getRef()), txtVwPayloadReference);
        GitUtils.setTitleDetailText("Payload Head Id:", String.valueOf(mEvent.getPayload().getHead()), txtVwPayloadHead);
        GitUtils.setTitleDetailText("Push Id:", String.valueOf(mEvent.getPayload().getPushId()), txtVwPushId);

        if (!TextUtils.isEmpty(mEvent.getType())) {
            txtVwEventName.setText(mEvent.getType());
            Character initial = mEvent.getType().charAt(0);
            GradientDrawable drawable = (GradientDrawable) txtVwInitial.getBackground();
            drawable.setColor(GitUtils.getColorForName(mContext, initial));
            txtVwInitial.setText(String.valueOf(initial));
        }

        txtVwDescription.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(mEvent.getPayload().getDescription()))
            txtVwDescription.setText(mEvent.getPayload().getDescription());
        else if (null != mEvent.getPayload().getIssue() && !TextUtils.isEmpty(mEvent.getPayload().getIssue().getTitle()))
            txtVwDescription.setText(mEvent.getPayload().getIssue().getTitle());
        else if (null != mEvent.getPayload() && null != mEvent.getPayload().getCommits() && mEvent.getPayload().getCommits().size() > 0)
            GitUtils.setTextIntoTextView(txtVwDescription, mEvent.getPayload().getCommits().get(0).getMessage());
        else
            txtVwDescription.setVisibility(View.GONE);
    }
}
