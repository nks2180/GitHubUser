package com.app.gitevent.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.app.gitevent.R;
import com.app.gitevent.customViews.GitTextView;
import com.app.gitevent.model.LoginResponse;
import com.app.gitevent.utils.GitUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niranjan on 12/15/16.
 */

public class ProfileActivity extends AppCompatActivity {

    Context mContext;
    LoginResponse mUserAccount;
    @BindView(R.id.imgVw_avatar)
    ImageView imgVwAvatar;
    @BindView(R.id.txtVw_name)
    GitTextView txtVwName;
    @BindView(R.id.txtVw_userName)
    GitTextView txtVwUserName;
    @BindView(R.id.txtVw_location)
    GitTextView txtVwLocation;
    @BindView(R.id.txtVw_bio)
    GitTextView txtVwBio;
    @BindView(R.id.txtVw_email_company)
    GitTextView txtVwEmailCompany;
    @BindView(R.id.txtVw_blog)
    GitTextView txtVwBlog;
    @BindView(R.id.txtVw_following_followers_count)
    GitTextView txtVwFollowingFollowersCount;
    @BindView(R.id.txtVw_createdAt)
    GitTextView txtVwCreatedAt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Profile");
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        if (null != bundle && bundle.containsKey(GitUtils.EXTRA_ACOOUNT_OBJ))
            mUserAccount = (LoginResponse) bundle.getSerializable(GitUtils.EXTRA_ACOOUNT_OBJ);
        if (null != mUserAccount)
            loadDataIntoUI();


    }

    private void loadDataIntoUI() {
        if (!TextUtils.isEmpty(mUserAccount.getAvatarUrl()))
            GitUtils.loadRoundedImageThroughPicasso(mContext, mUserAccount.getAvatarUrl(), imgVwAvatar, R.drawable.ic_account);
        GitUtils.setTextIntoTextView(txtVwName, mUserAccount.getName());
        GitUtils.setTextIntoTextView(txtVwUserName, mUserAccount.getLogin());
        GitUtils.setTextIntoTextView(txtVwBio, mUserAccount.getBio());
        GitUtils.setTextIntoTextView(txtVwLocation, mUserAccount.getLocation());
        GitUtils.setTextIntoTextView(txtVwBlog, mUserAccount.getBlog());

        Date createDate = GitUtils.parseDateString(GitUtils.yyyyMMddhhmmZ, mUserAccount.getCreatedAt());
        GitUtils.setTextIntoTextView(txtVwCreatedAt, "Profile created on: " + GitUtils.formatDate(GitUtils.MMMMddyyyy, createDate));

        String mailAndCompanyName = "";
        String docBullet = getResources().getString(R.string.dot_bullet);
        if (!TextUtils.isEmpty(mUserAccount.getCompany()))
            mailAndCompanyName = mUserAccount.getCompany();
        if (!TextUtils.isEmpty(mUserAccount.getEmail())) {
            if (mailAndCompanyName.length() > 0)
                mailAndCompanyName = mailAndCompanyName + " " + docBullet + " " + mUserAccount.getEmail();
            else
                mailAndCompanyName = mUserAccount.getEmail();
        }
        GitUtils.setTextIntoTextView(txtVwEmailCompany, mailAndCompanyName);

        String followStats = "";
        followStats = "Followers: " + mUserAccount.getFollowers();
        if (followStats.length() > 0)
            followStats = followStats + " " + docBullet + " " + "Following: " + mUserAccount.getFollowing();
        else
            followStats = "Following: " + mUserAccount.getFollowing();
        GitUtils.setTextIntoTextView(txtVwFollowingFollowersCount, followStats);
    }
}
