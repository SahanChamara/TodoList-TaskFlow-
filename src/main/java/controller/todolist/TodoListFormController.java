package controller.todolist;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.TodoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TodoListFormController {

    public DatePicker dateTodo;
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

        // Task Name Label
        Label taskName = new Label(txtAddTask.getText());
        taskName.getStyleClass().add("task-label");

        //Check Box
        CheckBox done = new CheckBox();
        done.getStyleClass().addAll("check-box","check-box .box","check-box:selected .box","check-box:selected .mark");

        // Date
        LocalDate date = dateTodo.getValue();
        Label lblDate = new Label(date.toString());

        // Remove the selected Task in Table and newTask Table in Database
        done.setOnAction(actionEvent -> {
            if (done.isSelected()) {
                vboxTaskList.getChildren().remove(hBox);
            }
        });

        // Adding the input Task in UI Component
        hBox.getChildren().addAll(taskName, done,lblDate);
        vboxTaskList.getChildren().add(hBox);

        // Send Task to the database
        if(TodoListController.getInstance().addTask(new TodoList(null,txtAddTask.getText(),date.toString(),null))){
            new Alert(Alert.AlertType.INFORMATION,"Task Added Successful");
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Task Added Failed");
        }
        txtAddTask.clear();
    }

    @FXML
    void txtAddOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }
}
