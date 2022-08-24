package manager;

import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class UserManager {
    private Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "Insert into user(name,surname,email,event_Id) Values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getEventId());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            user.setId(id);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM user";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<User> users = new LinkedList<>();
        while (resultSet.next()) {
            users.add(getUserFromResultSet(resultSet));
        }
        return users;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setEventId(resultSet.getInt("event_Id"));
        return user;
    }

    public static void showAllUser() throws SQLException {
        UserManager userManager = new UserManager();
        List<User> allUsers = userManager.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }
}
