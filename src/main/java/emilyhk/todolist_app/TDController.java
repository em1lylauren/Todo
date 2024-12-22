package emilyhk.todolist_app;


import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class TDController {
    private TaskModel model;
    private InteractionModel iModel;

    private enum state {
        READY
    }
    private state currentState = state.READY;

    public TDController() {
    }

    public void setModel(TaskModel model) {
        this.model = model;
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void handlePressed(MouseEvent e) {

    }

    public void handleReleased(MouseEvent e) {
        double baseline = TDView.BOX_PADDING + 10 + TDView.TF_HEIGHT + TDView.BOX_PADDING + (model.getTasks().size() * (TDView.TASK_HEIGHT + TDView.BOX_PADDING));
        model.addTask("Task", "This is a description", 0, baseline);
    }

    public void handleDragged(MouseEvent e) {

    }

    public void handleMoved(MouseEvent e) {
        if (model.contains(e.getX() - iModel.getViewLeft(), e.getY() - iModel.getViewTop())) {
            iModel.setSelected(model.whichContains(e.getX() - iModel.getViewLeft(), e.getY() - iModel.getViewTop()));
        } else iModel.clearSelection();
    }

    public void handleScrolled(ScrollEvent e) {
        iModel.setViewTop(iModel.getViewTop() + e.getDeltaY());
    }
}

