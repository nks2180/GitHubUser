package com.app.gitevent.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niranjan on 12/14/16.
 */
@JsonObject
public class Issue implements Serializable{
    @JsonField(name = "url")
    private String url;
    @JsonField(name = "repository_url")
    private String repositoryUrl;
    @JsonField(name = "labels_url")
    private String labelsUrl;
    @JsonField(name = "comments_url")
    private String commentsUrl;
    @JsonField(name = "events_url")
    private String eventsUrl;
    @JsonField(name = "html_url")
    private String htmlUrl;
    @JsonField(name = "id")
    private int id;
    @JsonField(name = "number")
    private int number;
    @JsonField(name = "title")
    private String title;
    @JsonField(name = "user")
    private User user;
    @JsonField(name = "state")
    private String state;
    @JsonField(name = "locked")
    private boolean locked;
    @JsonField(name = "milestone")
    private Object milestone;
    @JsonField(name = "comments")
    private int comments;
    @JsonField(name = "created_at")
    private String createdAt;
    @JsonField(name = "updated_at")
    private String updatedAt;
    @JsonField(name = "closed_at")
    private String closedAt;
    @JsonField(name = "body")
    private String body;
    @JsonField(name = "labels")
    private List<Labels> labels;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }


    public Object getMilestone() {
        return milestone;
    }

    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Labels> getLabels() {
        return labels;
    }

    public void setLabels(List<Labels> labels) {
        this.labels = labels;
    }
}
