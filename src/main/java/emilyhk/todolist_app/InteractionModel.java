package emilyhk.todolist_app;

import java.util.ArrayList;
import java.util.List;

public class InteractionModel {
    private List<TDSubscriber> subs;
    private double viewLeft, viewTop;
    private Task selected;

    public InteractionModel() {
        subs = new ArrayList<>();

        viewTop = TDView.BOX_PADDING;
        selected = null;
    }

    public Task getSelected() {
        return selected;
    }

    public void setSelected(Task selected) {
        this.selected = selected;
        notifySubscribers();
    }

    public void clearSelection() {
        selected = null;
        notifySubscribers();
    }

    public double getViewTop() {
        return viewTop;
    }

    public void setViewTop(double viewTop) {
        this.viewTop = viewTop;
        notifySubscribers();
    }

    public double getViewLeft() {
        return viewLeft;
    }

    public void setViewLeft(double viewLeft) {
        this.viewLeft = viewLeft;
        notifySubscribers();
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
