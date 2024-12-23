package emilyhk.todolist_app;


import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class TDController {
    private TaskModel model;
    private InteractionModel iModel;

    private enum state {
        READY,
        PRESSED
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
        double adjustedX = e.getX() - iModel.getViewLeft();
        double adjustedY = e.getY() - iModel.getViewTop();

        switch (currentState) {
            case READY -> {
                if (model.buttonContains(adjustedX, adjustedY)) {
                    iModel.setPressed(model.getAddButton());
                    currentState = state.PRESSED;
                } else iModel.clearButtonPressed();
            }
        }
    }

    public void handleReleased(MouseEvent e) {
        double adjustedX = e.getX() - iModel.getViewLeft();
        double adjustedY = e.getY() - iModel.getViewTop();

        switch (currentState) {
            case READY -> {
                if (iModel.getSelected() != null && iModel.getSelected().checkboxContains(adjustedX, adjustedY)) {
                    iModel.getSelected().setCompleted(!iModel.getSelected().isCompleted());
                }
            }

            case PRESSED -> {
                double baseline = TDView.BOX_PADDING + 10 + TDView.TF_HEIGHT + TDView.BOX_PADDING + (model.getTasks().size() * (TDView.TASK_HEIGHT + TDView.BOX_PADDING));
                model.addTask("Task", "This is a description", 0, baseline);
                iModel.clearButtonPressed();
                currentState = state.READY;
            }
        }
    }

    public void handleDragged(MouseEvent e) {

    }

    public void handleMoved(MouseEvent e) {
        double adjustedX = e.getX() - iModel.getViewLeft();
        double adjustedY = e.getY() - iModel.getViewTop();

        switch (currentState) {
            case READY -> {
                if (model.taskContains(adjustedX, adjustedY)) {
                    iModel.setSelected(model.whichTaskContains(e.getX() - iModel.getViewLeft(), e.getY() - iModel.getViewTop()));
                } else iModel.clearTaskSelection();

                if (model.buttonContains(adjustedX, adjustedY)) {
                    iModel.setSelected(model.getAddButton());
                } else iModel.clearButtonSelection();
            }
        }
    }

    public void handleScrolled(ScrollEvent e) {
        iModel.setViewTop(iModel.getViewTop() + e.getDeltaY());
    }
}

