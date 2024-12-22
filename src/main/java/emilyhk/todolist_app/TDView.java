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

    private double TASK_PADDING = 20;
    private double TASK_WIDTH = 400;
    private double TASK_HEIGHT = 200;
    private double BOX_PADDING = 50;
    private double CHECKBOX_WIDTH = 20;
    private double TEXT_SIZE = 15;

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
    }

    public void initEvents(TDController controller) {
        setOnMouseReleased(controller::handleReleased);
        setOnMousePressed(controller::handlePressed);
        setOnMouseDragged(controller::handleDragged);
    }

    public void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.save();
        gc.translate((canvas.getHeight() / 2) - (TASK_HEIGHT / 2), BOX_PADDING);

        double baseline = 0;
        for (Task t : model.getTasks()) {
            drawTask(t, 0, baseline);
            baseline += BOX_PADDING + TASK_HEIGHT;
        }

        gc.restore();
    }

    public void drawTask(Task t, double x, double y) {
        gc.save();
        gc.translate(x, y);

        // Draw task box
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.LIGHTGRAY);
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
