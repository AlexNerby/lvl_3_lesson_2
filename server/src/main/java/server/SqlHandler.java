package server;

import java.sql.*;

public class SqlHandler {

    private static Connection connection;
    private static PreparedStatement registration;
    private static PreparedStatement getNickName;
    private static PreparedStatement rename;


//    @Override
    public static boolean connectDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            allStatements();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
//
    private static void allStatements() throws SQLException {
        getNickName = connection.prepareStatement(
                "SELECT nickname FROM users WHERE login = ? AND password = ?;");
        registration = connection.prepareStatement(
                "INSERT INTO users(login, password, nickname) VALUES (? ,? ,? );");
        rename = connection.prepareStatement(
                "UPDATE users SET nickname = ? WHERE nickname = ?;");
    }

//    @Override
    public static String getNicknameByLoginAndPassword(String login, String password) {
        String nickName = null;
        try {
            getNickName.setString(1, login);
            getNickName.setString(2, password);
            ResultSet resultSet = getNickName.executeQuery();
            if (resultSet.next()) {
                nickName = resultSet.getString(1);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nickName;
    }

//    @Override
    public static boolean registration(String login, String password, String nickname) {
        try {
            registration.setString(1, login);
            registration.setString(2, password);
            registration.setString(3, nickname);
            registration.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean rename(String oldNickname, String newNickname) {
        try {
            rename.setString(1, newNickname);
            rename.setString(2, oldNickname);
            rename.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


}
