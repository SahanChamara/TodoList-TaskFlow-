package controller.todolist;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import controller.completedtask.CompletedTaskController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.TodoList;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TodoListFormController implements Initializable {

    public DatePicker dateTodo;
    public ScrollPane scrollPane;
    public JFXButton btnAddTaskView;
    public JFXButton btnViewCompletedTask;
    public Pane pnlCompletedTask;
    public AnchorPane pnlMainView;
    public ImageView btnCompletedTaskView;
    public JFXListView listViewTask;
    @FXML
    private JFXTextField txtAddTask;

    @FXML
    private VBox vboxTaskList;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        HBox hBox = new HBox();
        hBox.setSpacing(30);
        hBox.getStyleClass().add("hbox-task");

        // Task Name Label
        Label taskName = new Label(txtAddTask.getText());
        taskName.getStyleClass().add("task-label");

        //Check Box
        CheckBox done = new CheckBox("Completed");
        done.getStyleClass().add("check-box");

        // Date
        LocalDate date = dateTodo.getValue();
        Label lblDate = new Label(date.toString());
        lblDate.getStyleClass().add("date-label");

        // Remove the selected Task in Table and newTask Table in Database
        done.setOnMouseClicked(actionEvent -> {
            if (done.isSelected()) {
                if (CompletedTaskController.getInstance().completedTask(taskName.getText(), new SimpleDateFormat("yyyy/MM/dd").format(new Date()))) {
                    new Alert(Alert.AlertType.INFORMATION, "Task Finish...").show();
                }
                listViewTask.getItems().remove(hBox);
            }
        });

        // Adding the input Task in UI Component
        hBox.getChildren().addAll(taskName, done, lblDate);
        listViewTask.getItems().add(hBox);

        // Send Task to the database
        if (TodoListController.getInstance().addTask(new TodoList(null, txtAddTask.getText(), date.toString(), null))) {
            new Alert(Alert.AlertType.INFORMATION, "Task Added Successful").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Task Added Failed");
        }
        txtAddTask.clear();
    }

    @FXML
    void txtAddOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }

    void loadExistingTasktoUI() {
        ArrayList<TodoList> todoListArrayList = TodoListController.getInstance().loadTasks();
        todoListArrayList.forEach(todoList -> {
            HBox hBox = new HBox();
            hBox.setSpacing(30);
            hBox.getStyleClass().add("hbox-task");
            listViewTask.getStyleClass().add("list-view");

            // Task Name Label
            Label taskName = new Label(todoList.getTaskName());
            taskName.getStyleClass().add("task-label");

            //Check Box
            CheckBox done = new CheckBox("Completed");
            done.getStyleClass().add("check-box");

            // Date
            Label lblDate = new Label(todoList.getDate());
            lblDate.getStyleClass().add("date-label");

            // Adding the input Task in UI Component
            hBox.getChildren().addAll(taskName, done, lblDate);
            listViewTask.getItems().add(hBox);

            // Remove the selected Task in Table and newTask Table in Database
            done.setOnMouseClicked(actionEvent -> {
                if (done.isSelected()) {
                    if (CompletedTaskController.getInstance().completedTask(taskName.getText(), new SimpleDateFormat("yyyy/MM/dd").format(new Date()))) {
                        new Alert(Alert.AlertType.INFORMATION, "Task Finish...").show();
                    }
                    listViewTask.getItems().remove(hBox);
                }
            });
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadExistingTasktoUI();
    }

    public void btnAddTaskViewOnAction(ActionEvent actionEvent) {
    }

    public void btnViewCompletedTaskOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/completed_task_form.fxml")));
        scene.getStylesheets().add(getClass().getResource("/css/completedTaskStyles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void btnCompletedViewBackMouseClick(MouseEvent mouseEvent) {

    }
}
