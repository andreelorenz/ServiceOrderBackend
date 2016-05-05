package br.com.wso.person.controller;

import br.com.wso.person.dao.PersonDAO;
import br.com.wso.person.entity.Person;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRepository {

    public List<Person> findAllPerson() {
        return new PersonDAO().findPeople();
    }

    public Person findPerson(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return new PersonDAO().findPerson(map);
    }

    public Person addPerson(Person person) {
        return new PersonDAO().save(person);
    }

    public void editPerson(Person person) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", person.getId());
        Person query = new PersonDAO().findPerson(map);
        new PersonDAO().update(query, person);

    }
    
    public void removePerson(Person person){
        new PersonDAO().delete(person);
    }

}
