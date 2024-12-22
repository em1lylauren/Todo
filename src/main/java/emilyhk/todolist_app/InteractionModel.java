package emilyhk.todolist_app;

import java.util.ArrayList;
import java.util.List;

public class InteractionModel {
    private List<TDSubscriber> subs;

    public InteractionModel() {
        subs = new ArrayList<>();
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
