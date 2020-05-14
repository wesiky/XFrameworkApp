package com.xframework.model;

import androidx.annotation.NonNull;
import java.io.Serializable;

public class ListItem implements Serializable {
    private String Text;

    private String Value;

    public ListItem(String Text, String Value) {
        this.Text = Text;
        this.Value = Value;
    }

    public String getText() {
        return this.Text;
    }

    public String getValue() {
        return this.Value;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    @NonNull
    public String toString() {
        return this.Text;
    }
}
