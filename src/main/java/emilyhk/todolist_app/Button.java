package emilyhk.todolist_app;

import javafx.scene.paint.Color;

public class Button {
    private double x, y;
    private double width, height;
    public enum ButtonType {
        ADD,
        DELETE,
        EDIT
    }
    private ButtonType type;
    private Color defaultColor;
    private Color hoverColor;
    private Color pressedColor;

    public Button(double x, double y, double width, double height, ButtonType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;

        switch (type) {
            case ADD:
                defaultColor = Color.rgb(252, 199, 217);
                hoverColor = Color.rgb(227, 163, 185);
                pressedColor = Color.rgb(219, 129, 160);
        }
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public ButtonType getType() {
        return type;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public Color getPressedColor() {
        return pressedColor;
    }

    public boolean contains(double x, double y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }
}
