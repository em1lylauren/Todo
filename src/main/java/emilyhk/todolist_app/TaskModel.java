package emilyhk.todolist_app;

import java.util.ArrayList;
import java.util.List;

public class TaskModel {
    List<TDSubscriber> subs;

    public TaskModel() {
        subs = new ArrayList<TDSubscriber>();
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
