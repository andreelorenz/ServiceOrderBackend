package br.com.wso.service;


import br.com.wso.person.controller.PersonController;
import br.com.wso.user.controller.UserController;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("service-order")
public class WebServiceOrder extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resource = new HashSet<>();
        configure(resource);
        return resource;
    }

    public void configure(Set<Class<?>> resource) {
        resource.add(UserController.class);
        resource.add(PersonController.class);
    }

}