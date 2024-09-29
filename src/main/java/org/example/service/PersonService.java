package org.example.service;

import org.example.model.Person;
import org.example.servlet.dto.PersonDTO;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    void save(PersonDTO personDTO);
    PersonDTO findById(UUID uuid);
    List<PersonDTO> findAll();
    PersonService deleteById(UUID uuid);
}
