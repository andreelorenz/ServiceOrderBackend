package br.com.wso.person.converter;

import br.com.wso.person.entity.Person;
import com.mongodb.DBObject;
import java.util.HashMap;
import java.util.Map;

public class PersonConverter {

    public Map<String, Object> converterToMap(Person person) {
        Map<String, Object> mapPerson = new HashMap<>();
        mapPerson.put("id", person.getId());
        mapPerson.put("name", person.getName());
        mapPerson.put("gender", person.getGender());
        mapPerson.put("dateBirth", person.getDateBirth());
        mapPerson.put("address", person.getAddress());
        mapPerson.put("number", person.getNumber());
        mapPerson.put("country", person.getCountry());
        mapPerson.put("state", person.getState());
        return mapPerson;
    }

    public Person converterToObject(DBObject dbo) {
        if (dbo == null) {
            return null;
        }
        Person person = new Person();
        person.setId(Long.parseLong(dbo.get("id").toString()));
        person.setName((String) dbo.get("name"));
        person.setGender((String) dbo.get("gender"));
        person.setDateBirth((String) dbo.get("dateBirth"));
        person.setAddress((String) dbo.get("address"));
        person.setNumber((String) dbo.get("number"));
        person.setCountry((String) dbo.get("country"));
        person.setState((String) dbo.get("state"));
        return person;
    }

}
