package emilyhk.todolist_app;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class TDController {
    private TaskModel model;
    private InteractionModel iModel;

    private enum state {
        READY,
        PRESSED,
        TYPING
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

                if (iModel.getSelectedField() != null) {
                    iModel.setFieldSelected(true);
                    currentState = state.TYPING;
                }
            }

            case TYPING -> {
                if (model.buttonContains(adjustedX, adjustedY)) {
                    iModel.setPressed(model.getAddButton());
                    iModel.setFieldSelected(false);
                    iModel.clearSelectedField();
                    currentState = state.PRESSED;

                } else if (iModel.getSelected() == null) {
                    iModel.setFieldSelected(false);
                    iModel.clearSelectedField();
                    currentState = state.READY;
                }
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
                String title = model.getTaskField().getRawText();
                model.addTask(title, "This is a description", 0, baseline);
                iModel.clearButtonPressed();
                model.clearText(model.getTaskField());
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

                if (model.taskFieldContains(adjustedX, adjustedY)) {
                    iModel.setSelectedField(model.getTaskField());
                } else iModel.clearSelectedField();
            }

            case TYPING -> {
                if (model.taskContains(adjustedX, adjustedY)) {
                    iModel.setSelected(model.whichTaskContains(e.getX() - iModel.getViewLeft(), e.getY() - iModel.getViewTop()));
                } else iModel.clearTaskSelection();

                if (model.buttonContains(adjustedX, adjustedY)) {
                    iModel.setSelected(model.getAddButton());
                } else iModel.clearButtonSelection();
            }
        }
    }

    public void handleKeyPressed(KeyEvent e) {
        switch (currentState) {
            case TYPING -> {
                if (e.getCode() == KeyCode.BACK_SPACE) {
                    model.removeText(model.getTaskField());
                }
            }
        }
    }

    public void handleKeyReleased(KeyEvent e) {
        switch (currentState) {
            case TYPING -> {
                if (e.getCode().isLetterKey() || e.getCode().isDigitKey() || e.getCode() == KeyCode.SPACE) {
                    model.addText(model.getTaskField(), e.getText());
                }
            }
        }
    }

    public void handleScrolled(ScrollEvent e) {
        iModel.setViewTop(iModel.getViewTop() + e.getDeltaY());
    }
}

