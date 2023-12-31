package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userDao = new UserServiceImpl();
        userDao.createUsersTable();
        userDao.saveUser("Dima", "Domestos", (byte) 33);
        userDao.saveUser("Petr", "Kotov", (byte) 27);
        userDao.saveUser("Laura", "Kotova", (byte) 25);
        userDao.saveUser("Kolya", "Dmitriev", (byte) 24);
        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
