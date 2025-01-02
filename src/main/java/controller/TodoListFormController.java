package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TodoListFormController {

    @FXML
    private JFXTextField txtAddTask;

    @FXML
    private VBox vboxTaskList;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        HBox hBox = new HBox();
        hBox.setSpacing(30);

        vboxTaskList.getStyleClass().add("vbox-task-list");
        hBox.getStyleClass().add("task-hbox");

        Label taskName = new Label(txtAddTask.getText());
        taskName.getStyleClass().add("task-label");
        CheckBox done = new CheckBox();
        done.getStyleClass().addAll("check-box","check-box .box","check-box:selected .box","check-box:selected .mark");

        done.setOnAction(actionEvent -> {
            if (done.isSelected()) {
                vboxTaskList.getChildren().remove(hBox);
            }
        });

        hBox.getChildren().addAll(taskName, done);
        vboxTaskList.getChildren().add(hBox);
    }

    @FXML
    void txtAddOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }
}
