
package br.com.wso.person.dao;

import br.com.wso.connection.dao.EntityDao;
import br.com.wso.person.converter.PersonConverter;
import br.com.wso.person.entity.Person;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonDAO extends EntityDao<Person> {

    public PersonDAO() {
        super(Person.class);
    }

    public Person save(Person person) {
        Map<String, Object> mapPerson = new PersonConverter().converterToMap(person);
        return new PersonConverter().converterToObject(save(mapPerson));
    }

    public void update(Person oldPerson, Person newPerson) {
        Map<String, Object> query = new PersonConverter().converterToMap(oldPerson);
        Map<String, Object> map = new PersonConverter().converterToMap(newPerson);
        update(query, map);
    }

    public void delete(Person person) {
        Map<String, Object> mapPerson = new PersonConverter().converterToMap(person);
        delete(mapPerson);
    }

    public Person findPerson(Map<String, Object> mapKeyValue) {
        DBObject dbObject = findOne(mapKeyValue);

        Person person = new PersonConverter().converterToObject(dbObject);

        return person;
    }

    public List<Person> findPeople() {
        List<DBObject> dbObject = findAll();

        List<Person> people = new ArrayList<>();

        for (DBObject dbo : dbObject) {
            Person person = new PersonConverter().converterToObject(dbo);
            people.add(person);

        }
        return people;
    }

    public List<Person> findPeople(Map<String, Object> mapKeyValue) {
        List<DBObject> dbObject = findKeyValue(mapKeyValue);

        List<Person> people = new ArrayList<>();

        for (DBObject dbo : dbObject) {
            Person person = new PersonConverter().converterToObject(dbo);
            people.add(person);

        }
        return people;
    }

}
