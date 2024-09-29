package org.example.servlet.dto;

import java.util.Objects;
import java.util.UUID;

public class PersonDTO {
    private UUID uuid;
    private String name;
    private String surname;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public PersonDTO() {
    }

    public PersonDTO(UUID uuid, String name, String surname) {
        this.uuid = uuid;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) object;
        return Objects.equals(uuid, personDTO.uuid) && Objects.equals(name, personDTO.name) && Objects.equals(surname, personDTO.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, surname);
    }
}
