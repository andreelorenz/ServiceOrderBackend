package br.com.wso.connection.dao;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import java.util.List;
import java.util.Map;

public interface IDao {
    
    DBObject save(Map<String, Object> mapEntity);
    
    void update(Map<String, Object> mapQuery, Map<String, Object> mapEntity);
    
    void delete(Map<String, Object> mapEntity);
    
    DBObject findOne(Map<String, Object> mapEntity);
    
    List<DBObject> findAll();
    
    List<DBObject> findKeyValue(Map<String, Object> keyValue);
    
    Object getNextSequence(String name, DBCollection dbCounters);
    
}
