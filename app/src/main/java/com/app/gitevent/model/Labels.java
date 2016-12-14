package com.app.gitevent.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by niranjan on 12/14/16.
 */
@JsonObject
public class Labels {
    @JsonField(name = "id")
    private int id;
    @JsonField(name = "url")
    private String url;
    @JsonField(name = "name")
    private String name;
    @JsonField(name = "color")
    private String color;
    @JsonField(name = "default")
    private boolean defaultX;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isDefaultX() {
        return defaultX;
    }

    public void setDefaultX(boolean defaultX) {
        this.defaultX = defaultX;
    }
}
