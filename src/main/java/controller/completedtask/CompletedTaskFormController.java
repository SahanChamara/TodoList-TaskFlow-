package controller.completedtask;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CompletedTaskFormController implements Initializable {
    public JFXListView listView;

    private void setTasks(){
        for (int i=0; i<10; i++){
            VBox vBox = new VBox();
            vBox.getStyleClass().add("vbox");

            Label taskName = new Label("Complete the JavaFX Project");
            taskName.getStyleClass().add("task-name-label");

            Label date = new Label("2025/01/05");
            date.getStyleClass().add("date-label");

            vBox.getChildren().addAll(taskName,date);

            listView.getItems().add(vBox);
        }
//        listView.getItems().add(vBox);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTasks();
    }
}
