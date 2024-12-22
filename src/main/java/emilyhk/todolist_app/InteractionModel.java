package emilyhk.todolist_app;

import java.util.ArrayList;
import java.util.List;

public class InteractionModel {
    List<TDSubscriber> subs;

    public InteractionModel() {
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
