
package br.com.wso.user.dao;

import br.com.wso.connection.dao.EntityDao;
import br.com.wso.user.converter.UserConverter;
import br.com.wso.user.entity.User;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO extends EntityDao<User> {

    public UserDAO() {
        super(User.class);
    }

    public User save(User user) {
        Map<String, Object> mapUser = new UserConverter().converterToMap(user);
        return new UserConverter().converterToUser(save(mapUser));
    }

    public void update(User oldUser, User newUser) {
        Map<String, Object> query = new UserConverter().converterToMap(oldUser);
        Map<String, Object> map = new UserConverter().converterToMap(newUser);
        update(query, map);
    }

    public void delete(User user) {
        Map<String, Object> mapUser = new UserConverter().converterToMap(user);
        delete(mapUser);
    }

    public User findUser(Map<String, Object> mapKeyValue) {
        DBObject dbObject = findOne(mapKeyValue);

        User user = new UserConverter().converterToUser(dbObject);

        return user;
    }

    public List<User> findUsers() {
        List<DBObject> dbObject = findAll();

        List<User> users = new ArrayList<>();

        for (DBObject dbo : dbObject) {
            User user = new UserConverter().converterToUser(dbo);
            users.add(user);

        }
        return users;
    }

    public List<User> findUsers(Map<String, Object> mapKeyValue) {
        List<DBObject> dbObject = findKeyValue(mapKeyValue);

        List<User> users = new ArrayList<>();

        for (DBObject dbo : dbObject) {
            User user = new UserConverter().converterToUser(dbo);
            users.add(user);

        }
        return users;
    }

}
