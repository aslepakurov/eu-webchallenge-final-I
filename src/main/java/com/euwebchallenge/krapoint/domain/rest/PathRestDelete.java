package com.euwebchallenge.krapoint.domain.rest;

/**
 * PathRestDelete Class
 *
 * @version 11/7/15
 */
public class PathRestDelete {
    private String pointA;
    private String pointB;

    public PathRestDelete() {
    }

    public PathRestDelete(String pointA, String pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public String getPointA() {
        return pointA;
    }

    public void setPointA(String pointA) {
        this.pointA = pointA;
    }

    public String getPointB() {
        return pointB;
    }

    public void setPointB(String pointB) {
        this.pointB = pointB;
    }
}
