package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Dima","Domestos",(byte) 33);
        userDao.saveUser("Petr","Kotov",(byte) 27);
        userDao.saveUser("Laura","Kotova", (byte) 25);
        userDao.saveUser("Kolya","Dmitriev",(byte) 24);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
