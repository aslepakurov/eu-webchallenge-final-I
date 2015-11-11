package com.euwebchallenge.krapoint.service;

import com.euwebchallenge.krapoint.domain.CalculatedPath;
import com.euwebchallenge.krapoint.domain.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CalculatePathService Class
 *
 * @version 11/7/15
 */
@Component
public class CalculatePathService implements ICalculatePathService {

    @Autowired
    private IPathService pathService;

    @Override
    public CalculatedPath calculate(String pointA, String pointB) {
        Graph graph = new Graph(pathService.getAll());
        graph.dijkstra(pointA);
        int estimationTime = graph.printPath(pointB);
        return new CalculatedPath(pointA, pointB, estimationTime);
    }
}
