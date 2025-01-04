package controller.completedtask;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.CompletedTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CompletedTaskFormController implements Initializable {
    public JFXListView listView;

    private void setTasks() {
        ArrayList<CompletedTask> completedTaskArrayList = CompletedTaskController.getInstance().loadCompletedTask();
        completedTaskArrayList.forEach(completedTask -> {
            VBox vBox = new VBox();
            vBox.getStyleClass().add("vbox");

            Label taskName = new Label(completedTask.getTaskName());
            taskName.getStyleClass().add("task-name-label");

            Label taskAssignedLbl = new Label("Task Assigned Date : " + completedTask.getTaskAssignedDate());
            taskAssignedLbl.getStyleClass().add("date-label");

            Label taskCompleteLbl = new Label("Task Completed Date : " + completedTask.getTaskCompletedDate());
            taskCompleteLbl.getStyleClass().add("date-label");

            vBox.getChildren().addAll(taskName, taskAssignedLbl,taskCompleteLbl);

            listView.getItems().add(vBox);
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTasks();
    }
}
