package emilyhk.todolist_app;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;

public class MainUI extends StackPane {
    public MainUI() {
        TDView view = new TDView();
        TaskModel model = new TaskModel();
        TDController controller = new TDController();
        InteractionModel iModel = new InteractionModel();

        // Debug stuff
        model.addTask("Test1", "This is a description.");
        model.addTask("Test2", "This is a description.");
        model.addTask("Test3", "This is a description.");
        model.completeTask(model.getTasks().get(1));

        controller.setModel(model);
        controller.setInteractionModel(iModel);

        model.addSubscriber(view);
        iModel.addSubscriber(view);

        view.initEvents(controller);
        view.setModel(model);
        view.setInteractionModel(iModel);

        view.draw();

        Platform.runLater(view::requestFocus);
        this.getChildren().add(view);
    }
}
