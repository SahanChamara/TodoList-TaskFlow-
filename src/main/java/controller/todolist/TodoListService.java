package controller.todolist;

import model.TodoList;

import java.util.ArrayList;

public interface TodoListService {
    boolean addTask(TodoList task);
    ArrayList<TodoList> loadTasks();
    void setUserId(String userId);
    String generateTaskId();
    boolean completedTask();
}
