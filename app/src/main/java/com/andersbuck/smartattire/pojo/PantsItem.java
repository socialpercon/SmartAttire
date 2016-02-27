package com.andersbuck.smartattire.pojo;

import com.orm.SugarRecord;

public class PantsItem extends SugarRecord {

    private String name;

    public PantsItem() {
    }

    public PantsItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
