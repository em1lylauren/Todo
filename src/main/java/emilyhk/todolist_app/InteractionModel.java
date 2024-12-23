package emilyhk.todolist_app;

import java.util.ArrayList;
import java.util.List;

public class InteractionModel {
    private List<TDSubscriber> subs;
    private double viewLeft, viewTop;
    private Task selected;
    private TextField selectedField;
    private Button selectedButton;
    private Button pressedButton;

    public InteractionModel() {
        subs = new ArrayList<>();
        viewTop = TDView.BOX_PADDING;
        selected = null;
        selectedField = null;
        selectedButton = null;
        pressedButton = null;
    }

    public Task getSelected() {
        return selected;
    }

    public TextField getSelectedField() {
        return selectedField;
    }

    public Button getSelectedButton() {
        return selectedButton;
    }

    public Button getPressedButton() {
        return pressedButton;
    }

    public void setSelected(Task selected) {
        this.selected = selected;
        notifySubscribers();
    }

    public void setSelectedField(TextField selectedField) {
        this.selectedField = selectedField;
        notifySubscribers();
    }

    public void setFieldSelected(boolean b) {
        this.selectedField.setSelected(b);
        notifySubscribers();
    }

    public void setSelected(Button b) {
        this.selectedButton = b;
        notifySubscribers();
    }

    public void setPressed(Button b) {
        this.pressedButton = b;
        notifySubscribers();
    }

    public void clearTaskSelection() {
        selected = null;
        notifySubscribers();
    }

    public void clearSelectedField() {
        selectedField = null;
        notifySubscribers();
    }

    public void clearButtonSelection() {
        selectedButton = null;
        notifySubscribers();
    }

    public void clearButtonPressed() {
        pressedButton = null;
        notifySubscribers();
    }

    public double getViewTop() {
        return viewTop;
    }

    public void setViewTop(double viewTop) {
        if (viewTop <= 50) {
            this.viewTop = viewTop;
        } else this.viewTop = 50;
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
