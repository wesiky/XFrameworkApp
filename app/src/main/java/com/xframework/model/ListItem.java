package com.xframework.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ListItem implements Serializable {
    private String Text;
    private String Value;

    public ListItem(String text, String value) {
        this.Text = text;
        this.Value = value;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        this.Text = text;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        this.Value = value;
    }

    @NonNull
    @Override
    public String toString(){
        return Text;
    }
}
