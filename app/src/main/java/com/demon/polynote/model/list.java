package com.demon.polynote.model;

public class list {
    private String name;
    private String sub;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public list(String name, String sub) {

        this.name = name;
        this.sub = sub;
    }
}
