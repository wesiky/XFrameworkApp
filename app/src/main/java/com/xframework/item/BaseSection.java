package com.xframework.item;

public class BaseSection {
    private boolean first;

    private Object tag;

    public BaseSection(boolean first,Object tag)
    {
        this.first = first;
        this.tag = tag;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public boolean isFirst() {
        return first;
    }


    public void setFirst(boolean first) {
        this.first = first;
    }
}
