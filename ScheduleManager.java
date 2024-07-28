
package experiment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;

    private ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public boolean addTask(Task task) {
        if (isConflict(task)) {
            System.out.println("Error: Task conflicts with existing tasks.");
            return false;
        }
        tasks.add(task);
        System.out.println("Task added successfully.");
        return true;
    }

    public boolean removeTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                tasks.remove(task);
                System.out.println("Task removed successfully.");
                return true;
            }
        }
        System.out.println("Error: Task not found.");
        return false;
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        Collections.sort(tasks, Comparator.comparing(Task::getStartTime));
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private boolean isConflict(Task newTask) {
        for (Task task : tasks) {
            if (timeOverlap(task.getStartTime(), task.getEndTime(), newTask.getStartTime(), newTask.getEndTime())) {
                System.out.println("Error: Task conflicts with existing task \"" + task.getDescription() + "\".");
                return true;
            }
        }
        return false;
    }

    private boolean timeOverlap(String start1, String end1, String start2, String end2) {
        return (start1.compareTo(end2) < 0) && (start2.compareTo(end1) < 0);
    }
}