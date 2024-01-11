package com.safetynet.alert.repository;



import com.safetynet.alert.model.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> findPeopleByFireStationAddress(String addressCovereByFirestation);

    List<Person> findPeopleByAddress(String address);

    List<Person> findPeopleByName(String firstName, String lastName);

    List<Person> findPeopleByCity(String city);

    Person addPerson(Person personToAdd);

    Person updatePerson(Person personToEdit);

    Person removePerson(String firstName, String lastName);
}