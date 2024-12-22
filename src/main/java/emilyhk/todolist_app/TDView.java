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

    private double TF_WIDTH = 310;
    private double TF_HEIGHT = 80;
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
        setOnScroll(controller::handleScrolled);
    }

    public void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.save();
        gc.translate((canvas.getHeight() / 2) - (TASK_HEIGHT / 2) + iModel.getViewLeft(), BOX_PADDING + iModel.getViewTop());

        // Draw text field header
        drawHeader();

        // Draw tasks
        double baseline = TF_HEIGHT + TASK_PADDING + BOX_PADDING;
        for (Task t : model.getTasks()) {
            drawTask(t, 0, baseline);
            baseline += BOX_PADDING + TASK_HEIGHT;
        }

        gc.restore();

        // Debug: viewleft and viewtop values
        gc.setStroke(Color.BLACK);
        gc.strokeText("Viewleft: " + iModel.getViewLeft(), 20, 20);
        gc.strokeText("Viewtop: " + iModel.getViewTop(), 20, 40);
    }

    public void drawHeader() {
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.LIGHTBLUE);

        gc.strokeText("Add a task...", 0, 0);
        gc.fillRect(0, 10, TF_WIDTH, TF_HEIGHT);
        gc.strokeRect(0, 10, TF_WIDTH, TF_HEIGHT);

        // Drawing the plus box (to add a task)
        double width = TASK_WIDTH - TF_WIDTH - TASK_PADDING;
        gc.setFill(Color.LIGHTPINK);
        gc.fillRect(TF_WIDTH + TASK_PADDING, 10, width, TF_HEIGHT);
        gc.strokeRect(TF_WIDTH + TASK_PADDING, 10, width, TF_HEIGHT);

        double addWidth = TF_WIDTH + TASK_PADDING + (width / 2);
        double addHeight = (TF_HEIGHT / 2);
        gc.setStroke(Color.DEEPPINK);
        gc.strokeLine(addWidth, addHeight + 25, addWidth, addHeight - 5);
        gc.strokeLine(addWidth + 15, addHeight + 10, addWidth - 15, addHeight + 10);

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
