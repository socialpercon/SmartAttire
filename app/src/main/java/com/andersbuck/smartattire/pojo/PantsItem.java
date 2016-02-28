package com.andersbuck.smartattire.pojo;

import com.orm.SugarRecord;

public class PantsItem extends SugarRecord {

    private String name;

    public PantsItem() {
    }

    public PantsItem(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getId());
        sb.append(" : ");
        sb.append(name);

        return sb.toString();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
