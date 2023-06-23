package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dima", "Domestos", (byte) 33);
        userService.saveUser("Petr", "Kotov", (byte) 27);
        userService.saveUser("Laura", "Kotova", (byte) 25);
        userService.saveUser("Kolya", "Dmitriev", (byte) 24);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
