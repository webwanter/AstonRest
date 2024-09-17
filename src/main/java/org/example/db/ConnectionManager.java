package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/mydatabase";
        String username = "myuser";
        String password = "mypassword";

        try {
            // Регистрация драйвера PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Подключение к базе данных
            Connection connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Успешное подключение к базе данных PostgreSQL!");
            } else {
                System.out.println("Ошибка подключения к базе данных PostgreSQL.");
            }

            // Выполнение запросов и другие операции с базой данных

            // Закрытие соединения
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC драйвер не найден.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных PostgreSQL.");
            e.printStackTrace();
        }
    }
}