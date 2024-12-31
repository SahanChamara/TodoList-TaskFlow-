package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class TodoListHomeController {
    public VBox txtVBox;
    public Label txtHbox;
    private Pane pane;


    private void setLabel() {
        Label label = new Label("test label");
        label.setFont(new Font(15));
        txtVBox.getChildren().add(label);
    }

    public void btncClick(ActionEvent actionEvent) {
        setLabel();
    }
}
