package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {

    private String sql;
    private Connection connection;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        sql = "CREATE TABLE IF NOT EXISTS User (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)";
        connection = Util.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Создали таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.disconnection();
        }
    }

    public void dropUsersTable() {
        sql = "DROP TABLE IF EXISTS User";
        connection = Util.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
            System.out.println("Удалили таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.disconnection();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        connection = Util.getConnection();
        sql = "INSERT INTO User(name, lastName, age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.printf("User c именем - %s добавлен в базу данных \n", name);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            Util.disconnection();
        }
    }

    public void removeUserById(long id) {
        connection = Util.getConnection();
        sql = "DELETE FROM User WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.printf("User с id %s был удален \n", id);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            Util.disconnection();
        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();
        List<User> listUser = new ArrayList<>();
        sql = "SELECT * FROM User";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.disconnection();
        }
        return listUser;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            sql = "DELETE  FROM User";
            preparedStatement.executeUpdate(sql);
            connection.commit();
            System.out.println("Очистили таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            Util.disconnection();
        }
    }
}
