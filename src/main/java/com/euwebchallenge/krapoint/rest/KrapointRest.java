package com.euwebchallenge.krapoint.rest;

import com.euwebchallenge.krapoint.domain.CalculatedPath;
import com.euwebchallenge.krapoint.domain.rest.PathRestDelete;
import com.euwebchallenge.krapoint.domain.rest.PathRestRequest;
import com.euwebchallenge.krapoint.domain.rest.PathRestResponse;
import com.euwebchallenge.krapoint.domain.rest.ResponseType;
import com.euwebchallenge.krapoint.service.ICalculatePathService;
import com.euwebchallenge.krapoint.service.IPathService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

/**
 * ExampleRest Class
 *
 * @version 11/7/15
 */
@RestController
@RequestMapping("/krapoint")
public class KrapointRest {
    private static final Logger LOGGER = Logger.getLogger(KrapointRest.class);

    @Autowired
    private IPathService pathService;
    @Autowired
    private ICalculatePathService calculatePathService;
    @Autowired
    private ResourceBundle messages;

    @RequestMapping(value = "/path/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PathRestResponse> submit(@RequestBody PathRestRequest entity) {
        ResponseType type;
        String message;
        HttpStatus status;
        if (entity.getPointA() != null && entity.getPointB() != null && (entity.getEstimate() != 0 || entity.isCalculate())) {
            if (entity.isCalculate()) {
                CalculatedPath calculatedPath = calculatePathService.calculate(entity.getPointA(), entity.getPointB());
                if (calculatedPath.getEstimation() == -1) {
                    type = ResponseType.ERROR;
                    message = String.format(messages.getString(type.getName()), String.format(messages.getString("error.nopath"), calculatedPath.getPointA(), calculatedPath.getPointB()));
                    status = HttpStatus.BAD_REQUEST;
                } else {
                    type = ResponseType.ESTIMATE;
                    message = String.format(messages.getString(type.getName()), calculatedPath.getPointA(), calculatedPath.getPointB(), calculatedPath.getEstimation());
                    status = HttpStatus.OK;
                }
            } else {
                if (pathService.get(entity.getPointA(), entity.getPointB()) == null) {
                    String id = pathService.create(entity);
                    type = ResponseType.CREATED;
                    message = String.format(messages.getString(type.getName()), entity.getPointA(), entity.getPointB(), entity.getEstimate(), id);
                    status = HttpStatus.OK;
                } else {
                    pathService.update(entity.getPointA(), entity.getPointB(), entity.getEstimate());
                    type = ResponseType.UPDATED;
                    message = String.format(messages.getString(type.getName()), entity.getPointA(), entity.getPointB(), entity.getEstimate());
                    status = HttpStatus.OK;
                }
            }
        } else {
            type = ResponseType.ERROR;
            message = String.format(messages.getString(type.getName()), messages.getString("error.nonfull"));
            status = HttpStatus.BAD_REQUEST;
        }
        if (type.equals(ResponseType.ERROR)) {
            LOGGER.error(message);
        } else {
            LOGGER.info(message);
        }
        return ResponseEntity
                .status(status)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .body(new PathRestResponse(type, message));
    }

    @RequestMapping(value = "/path/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PathRestResponse> delete(@RequestBody PathRestDelete entity) {
        ResponseType type;
        String message;
        String pointA = entity.getPointA();
        String pointB = entity.getPointB();
        if (StringUtils.hasText(pointA) && StringUtils.hasText(pointB)) {
            if (pathService.delete(pointA, pointB)) {
                type = ResponseType.DELETED;
            } else {
                type = ResponseType.NOTDELETED;
            }
            message = String.format(messages.getString(type.getName()), pointA, pointB);
        } else {
            type = ResponseType.ERROR;
            message = String.format(messages.getString(type.getName()), messages.getString("error.nonfullparam"));
        }

        if (type.equals(ResponseType.ERROR)) {
            LOGGER.error(message);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(new PathRestResponse(type, message));
        } else {
            LOGGER.info(message);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(new PathRestResponse(type, message));
        }
    }
}