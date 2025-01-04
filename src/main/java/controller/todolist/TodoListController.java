package controller.todolist;

import DBConnection.DBConnection;
import model.TodoList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoListController implements  TodoListService{
    private static TodoListController instance;
    private String userId;

    private TodoListController() {
    }
    public static TodoListController getInstance(){
        return instance==null?instance=new TodoListController():instance;
    }

    @Override
    public boolean addTask(TodoList task) {
        try {
            PreparedStatement prepareStm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO newtask VALUES(?,?,?,?) ");
            prepareStm.setString(1,generateTaskId());
            prepareStm.setString(2,task.getTaskName());
            prepareStm.setString(3,task.getDate());
            prepareStm.setString(4,userId);
            return prepareStm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<TodoList> loadTasks() {
        ArrayList<TodoList> todoListArrayList = new ArrayList<>();
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM newtask WHERE userId='"+userId+"'");
            while(rst.next()){
                todoListArrayList.add(new TodoList(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
            }
            return todoListArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setUserId(String userId) {
        this.userId=userId;
    }

    @Override
    public String generateTaskId() {
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT TaskId FROM newtask ORDER BY TaskId DESC LIMIT 1");
            if(rst.next()){
                String existId = rst.getString(1);
                return String.format("T%04d",Integer.parseInt(existId.substring(1))+1);
            }else{
                return "T0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean completedTask() {
        return false;
    }
}
