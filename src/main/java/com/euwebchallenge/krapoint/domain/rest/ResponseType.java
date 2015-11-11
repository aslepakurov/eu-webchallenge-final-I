package com.euwebchallenge.krapoint.domain.rest;

/**
 * ResponseType Class
 *
 * @version 11/7/15
 */
public enum ResponseType {
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted"),
    NOTDELETED("notdeleted"),
    ERROR("error"),
    ESTIMATE("estimate");

    private String name;

    ResponseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
