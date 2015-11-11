package com.euwebchallenge.krapoint.domain.rest;

/**
 * PathRestRequest Class
 *
 * @version 11/7/15
 */
public class PathRestRequest {
    private String pointA;
    private String pointB;
    private int estimate;          //in seconds
    private boolean calculate;

    public PathRestRequest() {
    }

    public PathRestRequest(String pointA, String pointB, int estimate, boolean calculate) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.estimate = estimate;
        this.calculate = calculate;
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

    public boolean isCalculate() {
        return calculate;
    }

    public void setCalculate(boolean calculate) {
        this.calculate = calculate;
    }
}
