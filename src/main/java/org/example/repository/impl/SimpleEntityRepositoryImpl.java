package org.example.repository.impl;

import com.zaxxer.hikari.HikariDataSource;
import org.example.db.ConnectionManager;
import org.example.model.SimpleEntity;
import org.example.repository.SimpleEntityRepository;
import org.example.repository.mapper.SimpleResultSetMapper;

import java.sql.*;
import java.util.UUID;

public class SimpleEntityRepositoryImpl implements SimpleEntityRepository {
    private SimpleResultSetMapper resultSetMapper;
    private ConnectionManager connectionManager;
    private final HikariDataSource dataSource;
    public SimpleEntityRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SimpleEntity findById(UUID id) {
        // Здесь используем try with resources
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(UUID id) {
        return false;
    }

    @Override
    public SimpleEntity findAll() {
        return null;
    }

    @Override
    public SimpleEntity save(SimpleEntity simpleEntity) {
        return null;
    }
}
