package controller.login;

import model.User;

public interface LoginService {
    boolean registerUser(User newUser);

    String generateuserId();

    boolean authenticateUser(String userName, String password);
}
