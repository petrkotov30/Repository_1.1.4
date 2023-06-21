package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory factory = Util.getSessionFactory();
    private static String sqlCommand;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            sqlCommand = "CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";
            session.createNativeQuery(sqlCommand, User.class).executeUpdate();
            transaction.commit();
            System.out.println("Create Table");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            sqlCommand = "DROP TABLE IF EXISTS User";
            session.createNativeQuery(sqlCommand, User.class).executeUpdate();
            transaction.commit();
            System.out.println("Delete table");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
            System.out.println("SaveUser");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
            System.out.println("success remove");
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list;
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            sqlCommand = "SELECT id ,name, lastName, age FROM User";
            list = session.createNativeQuery(sqlCommand, User.class).list();
            transaction.commit();
            System.out.println("return list");
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            sqlCommand = "DELETE FROM User";
            session.createNativeQuery(sqlCommand, User.class).executeUpdate();
            transaction.commit();
            System.out.println("clear table");
        }
    }
}
