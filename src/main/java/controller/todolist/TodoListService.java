package controller.todolist;

import model.TodoList;

public interface TodoListService {
    boolean addTask(TodoList task);
    TodoList loadTasks();
    void setUserId(String userId);
    String generateTaskId();
    boolean completedTask();
}
