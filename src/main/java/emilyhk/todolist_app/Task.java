package emilyhk.todolist_app;

import javafx.scene.paint.Color;

public class Task {
    private String title;
    private String description;
    private boolean completed;
    private Color defaultColor;
    private Color hoverColor;
    private double x, y;

    public Task(String title, String description, double x, double y) {
        this.title = title;
        this.description = description;
        this.completed = false;

        this.x = x;
        this.y = y;

        defaultColor = Color.rgb(218,218,218);
        hoverColor = Color.rgb(193,193,193);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean b) {
        this.completed = b;
    }

    public boolean contains(double x, double y) {
        return x >= this.x && x <= this.x + TDView.TASK_WIDTH && y >= this.y && y <= this.y + TDView.TASK_HEIGHT;
    }

    public boolean checkboxContains(double x, double y) {
        if (x >= TDView.TASK_WIDTH - TDView.BOX_PADDING && x <= TDView.TASK_WIDTH - TDView.BOX_PADDING + TDView.CHECKBOX_WIDTH) {
            return y >= this.y + (TDView.TASK_HEIGHT / 2) && y <= this.y + (TDView.TASK_HEIGHT / 2) + TDView.CHECKBOX_WIDTH;
        }
        return false;
    }
}
