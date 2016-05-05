package br.com.wso.user.converter;

import br.com.wso.user.entity.User;
import com.mongodb.DBObject;
import java.util.HashMap;
import java.util.Map;

public class UserConverter {

    public Map<String, Object> converterToMap(User user) {
        Map<String, Object> mapUser = new HashMap<>();
        mapUser.put("id", user.getId());
        mapUser.put("email", user.getEmail());
        mapUser.put("login", user.getLogin());
        mapUser.put("password", user.getPassword());
        mapUser.put("idPerson", user.getIdPerson());
        mapUser.put("access", user.getAccess());
        return mapUser;
    }

    public User converterToUser(DBObject dbo) {
        if(dbo == null){
            return null;
        }
        User user = new User();
        user.setId(Long.parseLong(dbo.get("id").toString()));
        user.setEmail((String) dbo.get("email"));
        user.setLogin((String) dbo.get("login"));
        user.setPassword((String) dbo.get("password"));
        user.setIdPerson(Long.parseLong(dbo.get("idPerson").toString()));
        user.setAccess((int) dbo.get("access"));
        return user;
    }

}
