package controller.login;

import DBConnection.DBConnection;
import controller.todolist.TodoListController;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController implements LoginService {
    private static LoginController instance;

    // using the Singleton Design Pattern to getThe OneObject for anytime an FormController
    private LoginController() {
    }

    public static LoginController getInstance() {
        return instance == null ? instance = new LoginController() : instance;
    }

    @Override
    public boolean registerUser(User newUser) {
        try {
            PreparedStatement prepareStm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO user VALUES(?,?,?,?)");
            prepareStm.setString(1, generateuserId());
            prepareStm.setString(2, newUser.getEmail());
            prepareStm.setString(3, newUser.getUserName());
            prepareStm.setString(4, newUser.getPassword());
            return prepareStm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean authenticateUser(String userName, String password) {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT UserId, UserName,Password FROM user WHERE UserName='" + userName + "' AND Password='" + password + "'");
            if (rst.next()) {
                TodoListController.getInstance().setUserId(rst.getString(1),rst.getString("UserName"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Generating User ID
    @Override
    public String generateuserId() {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT userId from user ORDER BY userId DESC LIMIT 1");
            if (rst.next()) {
                String existId = rst.getString(1);
                return String.format("U%04d", Integer.parseInt(existId.substring(1)) + 1);
            } else {
                return "U0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
