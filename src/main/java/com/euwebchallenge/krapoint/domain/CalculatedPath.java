package com.euwebchallenge.krapoint.domain;

/**
 * CalculatedPath Class
 *
 * @version 11/7/15
 */
public class CalculatedPath {
    private String pointA;
    private String pointB;
    private int estimation;

    public CalculatedPath() {
    }

    public CalculatedPath(String pointA, String pointB, int estimation) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.estimation = estimation;
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

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }
}
