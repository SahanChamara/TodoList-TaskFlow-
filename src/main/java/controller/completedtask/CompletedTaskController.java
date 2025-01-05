package controller.completedtask;

import DBConnection.DBConnection;
import com.google.protobuf.ApiProto;
import controller.todolist.TodoListController;
import model.CompletedTask;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompletedTaskController implements CompletedTaskService {
    private static CompletedTaskService instance;

    private CompletedTaskController() {
    }

    public static CompletedTaskService getInstance() {
        return instance == null ? instance = new CompletedTaskController() : instance;
    }

    @Override
    public boolean completedTask(String taskName, String date) {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT TaskId,userId FROM newtask WHERE TaskName='" + taskName + "'");
            if (rst.next()) {
                PreparedStatement prepareStm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO completedtask VALUES(?,?,?,?)");
                prepareStm.setString(1, generateId());
                prepareStm.setString(2, taskName);
                prepareStm.setString(3, rst.getString("UserId"));
                prepareStm.setString(4, date);
                return prepareStm.executeUpdate() > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<CompletedTask> loadCompletedTask() {
        ArrayList<CompletedTask>completedTaskArrayList = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT TaskName,completeddate FROM completedtask WHERE userid='"+ TodoListController.getInstance().getUserId() +"'");
            while (rst.next()){
                completedTaskArrayList.add(new CompletedTask(rst.getString(1),null,rst.getString(2)));
            }
            return completedTaskArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateId() {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT completeId FROM completedtask ORDER BY completeId DESC LIMIT 1");
            if (rst.next()) {
                String existId = rst.getString(1);
                return String.format("C%04d", Integer.parseInt(existId.substring(1)) + 1);
            } else {
                return "C0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
