package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Person {
    private UUID uuid;
    private String name;
    private String surname;

    // Связь ManyToMany (с классом Order)
    private List<Order> orders = new ArrayList<>();

    public Person() {

    }


    public UUID getId() {
        return uuid;
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
        this.uuid = uuid;
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
        this.uuid = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(uuid, person.uuid) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(orders, person.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, surname, orders);
    }
}
