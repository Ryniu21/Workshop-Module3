package pl.coderslab.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;


public class UserDao {
    public static Connection connect(String url, String user, String password){
        try{
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    private static final String REMOVE_USER_QUERY =
            "DELETE FROM users where id = ?";

    private static final String SHOW_USERS_QUERY =
            "SELECT * FROM users";

    private static final String SHOW_ONE_USER_QUERY =
            "SELECT * FROM users WHERE id = ?";

    private static final String UPDATE_USERS_QUERY =
            "UPDATE users SET email = ?, username = ?, password=? WHERE id=?";


    public static User create (User user){
        try (Connection connect = DbUtil.getConnection()){
            try(PreparedStatement preStatement = connect.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)){
                preStatement.setString(1, user.getUserName());
                preStatement.setString(2, user.getEmail());
                String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                preStatement.setString(3,password);
                ResultSet resultSet = preStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
                preStatement.executeUpdate();
            }
            return user;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void remove (User user){
        try (Connection connect = DBUtil.getConnection()){
            try(PreparedStatement preStatement = connect.prepareStatement(REMOVE_USER_QUERY)){
                preStatement.setInt(1, user.getId());
                preStatement.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



    public static void update(User updateUser){
        try (Connection connect = DBUtil.getConnection()){
            try(PreparedStatement preStatement = connect.prepareStatement(UPDATE_USERS_QUERY)){
                int userId = updateUser.getId();
                preStatement.setString(1, updateUser.getEmail());
                preStatement.setString(2, updateUser.getUserName());
                preStatement.setString(3, BCrypt.hashpw(updateUser.getPassword(), BCrypt.gensalt()));
                preStatement.setInt(4, userId);
                preStatement.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void findAll(){
        try (Connection connect = DBUtil.getConnection()){
            try(PreparedStatement preStatement = connect.prepareStatement(SHOW_USERS_QUERY)){
                ResultSet resultSet = preStatement.executeQuery();
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String userName = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    System.out.println(id + " " +  email + " " + userName + " " + password);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();

        }
    }
    public static User[] findAll2() {
        try (Connection conn = DbUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(SHOW_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User read(int idToShow) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SHOW_ONE_USER_QUERY);
            statement.setInt(1, idToShow);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }
}
