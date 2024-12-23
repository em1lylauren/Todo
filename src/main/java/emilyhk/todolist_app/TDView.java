package emilyhk.todolist_app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TDView extends StackPane implements TDSubscriber {
    private TaskModel model;
    private InteractionModel iModel;
    private GraphicsContext gc;
    private Canvas canvas;

    public static final double TF_WIDTH = 310;
    public static final double TF_HEIGHT = 80;
    public static final double TASK_PADDING = 20;
    public static final double TASK_WIDTH = 400;
    public static final double TASK_HEIGHT = 200;
    public static final double BOX_PADDING = 50;
    public static final double CHECKBOX_WIDTH = 20;
    public static final double TEXT_SIZE = 15;

    public TDView() {
        canvas = new Canvas(1000, 800);
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
    }

    public void setModel(TaskModel model) {
        this.model = model;
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
        iModel.setViewLeft((canvas.getHeight() / 2) - (TASK_HEIGHT / 2));
    }

    public void initEvents(TDController controller) {
        setOnMouseReleased(controller::handleReleased);
        setOnMousePressed(controller::handlePressed);
        setOnMouseDragged(controller::handleDragged);
        setOnMouseMoved(controller::handleMoved);
        setOnScroll(controller::handleScrolled);
    }

    public void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.save();
        gc.translate(iModel.getViewLeft(), iModel.getViewTop());

        // Draw text field header
        drawHeader();

        // Draw tasks
        for (Task t : model.getTasks()) {
            drawTask(t);
        }

        gc.restore();

        // Debug
        gc.setStroke(Color.BLACK);
        gc.strokeText("Viewtop: " + iModel.getViewTop(), 20, 20);
    }

    public void drawHeader() {
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.LIGHTBLUE);

        gc.strokeText("Add a task...", 0, 0);
        gc.fillRect(0, 10, TF_WIDTH, TF_HEIGHT);
        gc.strokeRect(0, 10, TF_WIDTH, TF_HEIGHT);

        // Drawing the button to add a task
        drawButton(model.getAddTaskButton());
    }

    public void drawButton(Button b) {
        if (iModel.getPressedButton() == b) gc.setFill(b.getPressedColor());
        else if (iModel.getSelectedButton() == b) gc.setFill(b.getHoverColor());
        else gc.setFill(b.getDefaultColor());

        gc.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        gc.strokeRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

        double addWidth = TF_WIDTH + TASK_PADDING + (b.getWidth() / 2);
        double addHeight = (TF_HEIGHT / 2);
        gc.setStroke(b.getPressedColor());
        gc.strokeLine(addWidth, addHeight + 25, addWidth, addHeight - 5);
        gc.strokeLine(addWidth + 15, addHeight + 10, addWidth - 15, addHeight + 10);
    }

    public void drawTask(Task t) {
        gc.save();
        gc.translate(t.getX(), t.getY());

        // Draw task box
        gc.setStroke(Color.BLACK);
        if (iModel.getSelected() == t) gc.setFill(t.getHoverColor());
        else gc.setFill(t.getDefaultColor());
        gc.fillRect(0, 0, TASK_WIDTH, TASK_HEIGHT);
        gc.strokeRect(0, 0, TASK_WIDTH, TASK_HEIGHT);

        // Draw task title and description
        gc.strokeText(t.getTitle(), 0 + TASK_PADDING, 20 + TASK_PADDING, TASK_WIDTH);
        gc.strokeText(t.getDescription(), 0 + TASK_PADDING, 40 + TASK_PADDING, TASK_WIDTH);

        // Draw checkbox
        gc.translate(TASK_WIDTH - BOX_PADDING, TASK_HEIGHT / 2);
        drawCheckbox(t.isCompleted());

        gc.restore();
    }

    public void drawCheckbox(boolean checked) {
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CHECKBOX_WIDTH, CHECKBOX_WIDTH);
        gc.strokeRect(0, 0, CHECKBOX_WIDTH, CHECKBOX_WIDTH);

        // Draw checkmark
        if (checked) {
            gc.setStroke(Color.GREEN);
            gc.strokeLine(4, 4, 10, 15);
            gc.strokeLine(10, 15, 35, -10);
        }
    }

    @Override
    public void update() {
        draw();
    }
}
