package org.example.dao;
import org.example.model.Person;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO {

    private DataSource dataSource;

    public PersonDAO() {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/PostgresDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void save(Person person) {
        String sql = "INSERT INTO person (name, surname) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getSurname());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Person findById(Long id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Person(rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}