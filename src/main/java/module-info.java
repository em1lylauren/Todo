module emilyhk.todolist_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens emilyhk.todolist_app to javafx.fxml;
    exports emilyhk.todolist_app;
}