package com.dmilut;

public class Letter {
    private String value;
    private boolean isVisible;

    public Letter(String value, boolean isVisible) {
        this.value = value;
        this.isVisible = isVisible;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
