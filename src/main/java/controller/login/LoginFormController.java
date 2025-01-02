package controller.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import animatefx.animation.*;

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
        if(event.getSource().equals(btnBack)){
            new ZoomIn(pnlSignIn).play();
            pnlSignIn.toFront();
        }
    }

    @FXML
    void btnGetStartedOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        if(event.getSource().equals(btnSignup)){
            new ZoomIn(pnlSignUp).play();
            pnlSignUp.toFront();
        }

    }

}
