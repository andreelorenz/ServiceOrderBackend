package br.com.wso.connection;


import br.com.wso.user.UserController;
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
    }

}