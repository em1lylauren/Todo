package emilyhk.todolist_app;

import javafx.scene.paint.Color;

public class TextField {
    private String text;
    private double x, y;
    private double width, height;
    private Color defaultColor;
    private Color hoverColor;
    private boolean selected;

    public TextField(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.text = "";
        this.selected = false;

        defaultColor = Color.rgb(218,218,218);
        hoverColor = Color.rgb(193,193,193);
    }

    public void addText(String text) {
        this.text = this.text.concat(text);
    }

    public void removeText() {
        if (!this.text.isEmpty()) this.text = this.text.substring(0, this.text.length() - 1);
    }

    public void clearText() {
        this.text = "";
    }

    public String getRawText() {
        return text;
    }

    public String getText() {
        if (selected) {
            return text + "|";
        } else return text;
    }

    public void setSelected(boolean b) {
        this.selected = b;
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

    public Color getDefaultColor() {
        return defaultColor;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public boolean contains(double x, double y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }
}
