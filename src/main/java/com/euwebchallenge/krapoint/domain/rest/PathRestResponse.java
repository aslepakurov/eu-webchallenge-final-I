package com.euwebchallenge.krapoint.domain.rest;

/**
 * PathRestResponse Class
 *
 * @version 11/7/15
 */
public class PathRestResponse {
    private ResponseType type;
    private String message;

    public PathRestResponse(ResponseType type, String message) {
        this.type = type;
        this.message = message;
    }

    public PathRestResponse() {
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
