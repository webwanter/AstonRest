package org.example.service.impl;

import org.example.model.Person;
import org.example.repository.PersonDAO;
import org.example.service.PersonService;
import org.example.servlet.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonServiceImpl implements PersonService {
    private final PersonDAO personDAO;
    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public void save(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        personDAO.save(person);
    }

    @Override
    public PersonDTO findById(UUID uuid) {
        Person person = personDAO.findById(uuid);
        if (person == null) {
            return null;
        }
        PersonDTO personDTO = new PersonDTO();
        personDTO.setUuid(person.getId());
        personDTO.setName(person.getName());
        return personDTO;
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> persons = (List<Person>) personDAO.findAll();
        List<PersonDTO> personDTOs = new ArrayList<>();
        for (Person person : persons) {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setUuid(person.getId());
            personDTO.setName(person.getName());
            personDTOs.add(personDTO);
        }
        return personDTOs;
    }

    @Override
    public boolean deleteById(UUID uuid) {
        try {
            personDAO.deleteById(uuid);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
