package br.com.wso.connection.converter;

import br.com.wso.entity.User;
import com.mongodb.DBObject;
import java.util.HashMap;
import java.util.Map;

public class UserConverter {

    public Map<String, Object> converterToMap(User user) {
        Map<String, Object> mapUser = new HashMap<>();
        mapUser.put("id", user.getId());
        mapUser.put("name", user.getName());
        mapUser.put("email", user.getEmail());
        mapUser.put("login", user.getLogin());
        mapUser.put("password", user.getPassword());
        mapUser.put("access", user.getAccess());
        return mapUser;
    }

    public User converterToUser(DBObject dbo) {
        if(dbo == null){
            return null;
        }
        User user = new User();
        user.setId(Long.parseLong(dbo.get("id").toString()));
        user.setName((String) dbo.get("name"));
        user.setEmail((String) dbo.get("email"));
        user.setLogin((String) dbo.get("login"));
        user.setPassword((String) dbo.get("password"));
        user.setAccess((int) dbo.get("access"));
        return user;
    }

}
