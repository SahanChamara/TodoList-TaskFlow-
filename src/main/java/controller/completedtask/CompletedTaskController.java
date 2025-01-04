package controller.completedtask;

import DBConnection.DBConnection;
import com.google.protobuf.ApiProto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                prepareStm.setString(2, rst.getString("TaskId"));
                prepareStm.setString(3, rst.getString(" UserId"));
                prepareStm.setString(4, date);
                return prepareStm.executeUpdate() > 0;
            }
            return false;
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
