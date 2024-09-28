package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Person {
    private UUID id;
    private String name;
    private String surname;

    // Связь ManyToMany (с классом Order)
    private List<Order> orders = new ArrayList<>();

    public Person() {

    }


    public UUID getId() {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Person(UUID id, String name, String surname) {
        this.surname = surname;
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(orders, person.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, orders);
    }
}
