

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
    private Connection connection = Util.getConnection();
    private String sql;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        sql = "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() throws SQLException {
        sql = "DROP TABLE IF EXISTS User";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(sql);
        System.out.println("Успешно удалили таблицу");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        sql = "INSERT INTO User(name, lastName, age) VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        preparedStatement.executeUpdate();
        System.out.printf("User с именем – %s добавлен в базу данных \n", name);
    }

    public void removeUserById(long id) throws SQLException {
        sql = "DELETE FROM User WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        sql = "SELECT id ,name, lastName, age FROM User";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        while(resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("ID"));
            user.setName(resultSet.getString("NAME"));
            user.setLastName(resultSet.getString("LASTNAME"));
            user.setAge(resultSet.getByte("AGE"));
            listUser.add(user);
        }

        return listUser;
    }

    public void cleanUsersTable() throws SQLException {
        sql = "DELETE FROM User";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(sql);
        System.out.println("Успешно очистили таблицу");
    }
}
