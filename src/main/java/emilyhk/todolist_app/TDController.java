package emilyhk.todolist_app;


import javafx.scene.input.MouseEvent;

public class TDController {
    private TaskModel model;
    private InteractionModel iModel;

    private enum state {
        READY
    }
    private state currentState = state.READY;

    public TDController() {}

    public void setModel(TaskModel model) {
        this.model = model;
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void handlePressed(MouseEvent e) {

    }

    public void handleReleased(MouseEvent e) {

    }

    public void handleDragged(MouseEvent e) {

    }
}

