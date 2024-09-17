package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private Long id;
    private String name;
    private String surname;

    // Связь OneToMany (с классом Order)
    private List<Order> orders = new ArrayList<>();


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }



    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Order> getOrders() {
        return orders;
    }


    public Person(Long id, String name, String surname) {
        this.surname = surname;
        this.name = name;
        this.id = id;
    }
}
