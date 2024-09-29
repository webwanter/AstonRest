package org.example.repository.impl;
import com.zaxxer.hikari.HikariDataSource;
import org.example.model.Person;
import org.example.repository.PersonDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PersonDaoImpl implements PersonDAO {

    private final HikariDataSource dataSource;

    public PersonDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createPersonsTable() {
        String sql = "CREATE TABLE persons " +
                "(id SERIAL, name VARCHAR(30), surname VARCHAR(30), age INTEGER, orders VARCHAR(200))";
        try (Connection connection = dataSource.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Person findById(UUID id) {
        String sql = "SELECT * FROM persons WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Person(rs.getObject("id", UUID.class), rs.getString("name"), rs.getString("surname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        String sql = "DELETE FROM persons WHERE id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1,  id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Person findAll() {
        return null;
    }

    @Override
    public Person save(Person person) {
        String sql = "INSERT INTO persons (id, name, surname) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setObject(1, person.getId());
            stmt.setString(2, person.getName());
            stmt.setString(3, person.getSurname());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
}