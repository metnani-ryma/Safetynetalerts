package com.safetynet.alert.dto;


import com.safetynet.alert.model.Person;

import java.util.List;

public class PeopleByFirestationNumber {

    private List<Person> person;
    private int amountOfAdults;
    private int amountOfMinors;

    public PeopleByFirestationNumber(List<Person> person, int amountOfAdults, int amountOfMinors) {
        this.person = person;
        this.amountOfAdults = amountOfAdults;
        this.amountOfMinors = amountOfMinors;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public int getAmountOfAdults() {
        return amountOfAdults;
    }

    public void setAmountOfAdults(int amountOfAdults) {
        this.amountOfAdults = amountOfAdults;
    }

    public int getAmountOfMinors() {
        return amountOfMinors;
    }

    public void setAmountOfMinors(int amountOfMinors) {
        this.amountOfMinors = amountOfMinors;
    }
}