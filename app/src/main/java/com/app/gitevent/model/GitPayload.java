package com.app.gitevent.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

/**
 * Created by niranjan on 12/13/16.
 */

@JsonObject
public class GitPayload implements Serializable{
    @JsonField(name = "ref")
    private String ref;
    @JsonField(name = "ref_type")
    private String refType;
    @JsonField(name = "master_branch")
    private String masterBranch;
    @JsonField(name = "description")
    private String description;
    @JsonField(name = "pusher_type")
    private String pusherType;
    @JsonField(name = "action")
    private String action;
    @JsonField(name = "issue")
    private Issue issue;


    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getMasterBranch() {
        return masterBranch;
    }

    public void setMasterBranch(String masterBranch) {
        this.masterBranch = masterBranch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPusherType() {
        return pusherType;
    }

    public void setPusherType(String pusherType) {
        this.pusherType = pusherType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

}
