package com.app.gitevent.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

/**
 * Created by niranjan on 12/16/16.
 */
@JsonObject
public class Author implements Serializable {
    @JsonField(name = "email")
    private String email;
    @JsonField(name = "name")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

