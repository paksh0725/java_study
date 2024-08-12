package com.seonpt.researcher.common.entity;

public class SimpleUser {

    public final long id;

    public final String name;

    public SimpleUser(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SimpleUser(String name) {
        this(0, name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
