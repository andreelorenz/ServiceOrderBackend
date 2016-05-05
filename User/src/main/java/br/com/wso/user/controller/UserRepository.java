package br.com.wso.user.controller;

import br.com.wso.user.entity.User;
import br.com.wso.user.dao.UserDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    public List<User> findAllUser() {
        return new UserDAO().findUsers();
    }

    public User findUser(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return new UserDAO().findUser(map);
    }

    public User addUser(User user) {
        return new UserDAO().save(user);
    }

    public void editUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        User query = new UserDAO().findUser(map);
        new UserDAO().update(query, user);

    }
    
    public void remove(User user){
        new UserDAO().delete(user);
    }

}
