package br.com.wso.connection.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityDao<T> implements IDao {

    private Class<T> persistentClass;

    private DBCollection dbCollection;

    private DBCollection dbCounters;

    private static final String COUNTER = "counters";

    public EntityDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        this.dbCollection = MongoConnection.getInstance().getDB().getCollection(persistentClass.getSimpleName());
        this.dbCounters = MongoConnection.getInstance().getDB().getCollection(COUNTER);
    }

    protected DBCollection getDbCollection() {
        return dbCollection;
    }

    @Override
    public DBObject save(Map<String, Object> mapEntity) {
        mapEntity.put("id", getNextSequence(persistentClass.getSimpleName(), dbCounters));
        BasicDBObject document = new BasicDBObject(mapEntity);
        dbCollection.save(document);
        
        return dbCollection.findOne(document);
    }

    @Override
    public void update(Map<String, Object> mapQuery, Map<String, Object> mapEntity) {
        BasicDBObject query = new BasicDBObject(mapQuery);

        BasicDBObject entity = new BasicDBObject(mapEntity);

        dbCollection.update(query, entity);

    }

    @Override
    public void delete(Map<String, Object> mapEntity) {
        BasicDBObject entity = new BasicDBObject(mapEntity);

        dbCollection.remove(entity);
    }

    @Override
    public DBObject findOne(Map<String, Object> mapEntity) {
        BasicDBObject entity = new BasicDBObject(mapEntity);

        return dbCollection.findOne(entity);
    }

    @Override
    public List<DBObject> findAll() {
        List<DBObject> list = new ArrayList<>();

        DBCursor cursor = dbCollection.find();

        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list;
    }

    @Override
    public List<DBObject> findKeyValue(Map<String, Object> keyValue) {
        List<DBObject> list = new ArrayList<>();

        DBCursor cursor = dbCollection.find(new BasicDBObject(keyValue));

        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list;
    }

    @Override
    public Object getNextSequence(String name, DBCollection dbCounters) {
        BasicDBObject find = new BasicDBObject();
        find.put("_id", name);
        BasicDBObject update = new BasicDBObject();
        update.put("$inc", new BasicDBObject("seq", 1));
        DBObject obj = dbCounters.findAndModify(find, update);
        return obj.get("seq");
    }

}
