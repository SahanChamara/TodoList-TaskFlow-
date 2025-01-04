package controller.completedtask;

import model.CompletedTask;

import java.util.ArrayList;

public interface CompletedTaskService {
    boolean completedTask(String taskName, String date);
    ArrayList<CompletedTask> loadCompletedTask();
}
