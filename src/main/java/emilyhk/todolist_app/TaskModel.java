package emilyhk.todolist_app;

import java.util.ArrayList;
import java.util.List;

public class TaskModel {
    private List<TDSubscriber> subs;
    private List<Task> tasks;

    public TaskModel() {
        subs = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public void addTask(String title, String description, double x, double baseline) {
        Task t = new Task(title, description, x, baseline);
        tasks.add(t);
        notifySubscribers();
    }

    public void changeTitle(Task t, String title) {
        t.setTitle(title);
        notifySubscribers();
    }

    public void changeDescription(Task t, String description) {
        t.setDescription(description);
        notifySubscribers();
    }

    public void completeTask(Task t) {
        t.setCompleted(true);
        notifySubscribers();
    }

    public void uncompleteTask(Task t) {
        t.setCompleted(false);
        notifySubscribers();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean contains(double x, double y) {
        for (Task t : tasks) {
            if (t.contains(x, y)) return true;
        }
        return false;
    }

    public Task whichContains(double x, double y) {
        for (Task t : tasks) {
            if (t.contains(x, y)) return t;
        }
        return null;
    }

    public void addSubscriber(TDSubscriber sub) {
        subs.add(sub);
    }

    public void notifySubscribers() {
        for (TDSubscriber sub : subs) {
            sub.update();
        }
    }
}
