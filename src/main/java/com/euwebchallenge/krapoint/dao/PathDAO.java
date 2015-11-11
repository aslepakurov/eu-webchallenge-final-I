package com.euwebchallenge.krapoint.dao;

import com.euwebchallenge.krapoint.domain.Path;
import com.euwebchallenge.krapoint.domain.rest.PathRestRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * PathDAO Class
 *
 * @version 11/7/15
 */
@Component
public class PathDAO implements IPathDAO {
    private static final Logger LOGGER = Logger.getLogger(PathDAO.class);

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public String create(PathRestRequest entity) {
        if (!collectionExist()) {
            LOGGER.info("Created collection for Path.class");
            mongoOperations.createCollection(Path.class);
        }
        Path path = new Path(entity);
        String id = UUID.randomUUID().toString();
        path.setId(id);
        mongoOperations.save(path);
        path.setId(UUID.randomUUID().toString());
        path.setPointA(entity.getPointB());
        path.setPointB(entity.getPointA());
        mongoOperations.save(path);
        LOGGER.info(String.format("Saved entity from point %s to point %s...", entity.getPointA(), entity.getPointB()));
        return id;
    }

    @Override
    public Path get(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, Path.class);
    }

    @Override
    public Path get(String pointA, String pointB) {
        Query query = new Query(Criteria.where("pointA").is(pointA).andOperator(Criteria.where("pointB").is(pointB)));
        return mongoOperations.findOne(query, Path.class);
    }

    @Override
    public boolean delete(String id) {
        mongoOperations.remove(new Query(Criteria.where("id").is(id)));
        return get(id) == null;
    }

    @Override
    public boolean delete(String pointA, String pointB) {
        mongoOperations.remove(new Query(Criteria.where("pointA").is(pointA).andOperator(Criteria.where("pointB").is(pointB))), Path.class);
        mongoOperations.remove(new Query(Criteria.where("pointB").is(pointA).andOperator(Criteria.where("pointA").is(pointB))), Path.class);
        return get(pointA, pointB) == null;
    }

    @Override
    public void update(String pointA, String pointB, int estimate) {
        mongoOperations.updateFirst(new Query(Criteria.where("pointA").is(pointA).andOperator(Criteria.where("pointB").is(pointB))), Update.update("estimate", estimate), Path.class);
        mongoOperations.updateFirst(new Query(Criteria.where("pointB").is(pointA).andOperator(Criteria.where("pointA").is(pointB))), Update.update("estimate", estimate), Path.class);
    }

    @Override
    public Path[] getAll() {
        List<Path> allPaths = mongoOperations.findAll(Path.class);
        Path[] pathArray = new Path[allPaths.size()];
        pathArray = allPaths.toArray(pathArray);
        return pathArray;
    }

    private boolean collectionExist() {
        return mongoOperations.collectionExists(Path.class);
    }

}
