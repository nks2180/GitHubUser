package com.app.gitevent.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

/**
 * Created by niranjan on 12/13/16.
 */

@JsonObject
public class GitEvent implements Serializable {

    @JsonField(name = "id")
    private String id;
    @JsonField(name = "type")
    private String type;
    @JsonField(name = "actor")
    private GitActor actor;
    @JsonField(name = "repo")
    private GitRepo repo;
    @JsonField(name = "payload")
    private GitPayload payload;
    @JsonField(name = "public")
    private boolean publicX;
    @JsonField(name = "created_at")
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GitActor getActor() {
        return actor;
    }

    public void setActor(GitActor actor) {
        this.actor = actor;
    }

    public GitRepo getRepo() {
        return repo;
    }

    public void setRepo(GitRepo repo) {
        this.repo = repo;
    }

    public GitPayload getPayload() {
        return payload;
    }

    public void setPayload(GitPayload payload) {
        this.payload = payload;
    }

    public boolean isPublicX() {
        return publicX;
    }

    public void setPublicX(boolean publicX) {
        this.publicX = publicX;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
