package com.euwebchallenge.krapoint.domain;

import com.euwebchallenge.krapoint.domain.rest.PathRestRequest;

/**
 * PathRestRequest Class
 *
 * @version 11/7/15
 */
public class Path {
    private String id;
    private String pointA;
    private String pointB;
    //in minutes
    private int estimate;

    public Path() {
    }

    public Path(PathRestRequest entity) {
        this.pointA = entity.getPointA();
        this.pointB = entity.getPointB();
        this.estimate = entity.getEstimate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }
}
