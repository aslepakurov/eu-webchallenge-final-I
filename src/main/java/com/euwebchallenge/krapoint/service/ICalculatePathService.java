package com.euwebchallenge.krapoint.service;

import com.euwebchallenge.krapoint.domain.CalculatedPath;

/**
 * ICalculatePathService Class
 *
 * @version 11/7/15
 */
public interface ICalculatePathService {
    CalculatedPath calculate(String pointA, String pointB);
}
