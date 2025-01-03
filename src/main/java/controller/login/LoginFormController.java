package controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import animatefx.animation.*;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.Optional;

public class LoginFormController {

    @FXML
    private ImageView btnBack;

    @FXML
    private JFXButton btnGetStarted;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignup;

    @FXML
    private Pane pnlSignIn;

    @FXML
    private Pane pnlSignUp;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtNewPassword;

    @FXML
    private JFXTextField txtNewUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void btnBack(MouseEvent event) {
        if (event.getSource().equals(btnBack)) {
            new ZoomIn(pnlSignIn).play();
            pnlSignIn.toFront();
        }
    }

    @FXML
    void btnGetStartedOnAction(ActionEvent event) {
        if (LoginController.getInstance().registerUser(new User(null, txtEmail.getText(), txtNewUserName.getText(), txtNewPassword.getText()))) {
            Optional<ButtonType> okButton = new Alert(Alert.AlertType.INFORMATION, "User Registered Successful", ButtonType.OK).showAndWait();
            ButtonType buttonType = okButton.orElse(ButtonType.CANCEL);
            if (buttonType == ButtonType.OK) {
                if (event.getSource().equals(btnGetStarted)) {
                    new ZoomOut(pnlSignIn).play();
                    pnlSignIn.toFront();
                }
            }
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        if (LoginController.getInstance().authenticateUser(txtUserName.getText(), txtPassword.getText())) {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/todolist_form.fxml")));
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please Create Account and After Login...").show();
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        if (event.getSource().equals(btnSignup)) {
            new ZoomIn(pnlSignUp).play();
            pnlSignUp.toFront();
        }
    }
}
