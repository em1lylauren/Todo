package emilyhk.todolist_app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public class TDView extends StackPane implements TDSubscriber {
    private TaskModel model;
    private InteractionModel iModel;
    private GraphicsContext gc;
    private Canvas canvas;

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
        // Draw stuff here lol
    }

    @Override
    public void update() {
        draw();
    }
}
