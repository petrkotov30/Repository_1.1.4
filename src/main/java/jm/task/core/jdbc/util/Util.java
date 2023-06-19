package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/MydbTest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "rootroot";
    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            System.out.println("Соединение выполнено");
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Соединение не выполнилось");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
