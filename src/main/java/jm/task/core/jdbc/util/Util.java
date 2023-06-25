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
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection is ON.");
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection is OFF.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void disconnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection is OFF.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
