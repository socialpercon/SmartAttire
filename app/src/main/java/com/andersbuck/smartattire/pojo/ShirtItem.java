package com.andersbuck.smartattire.pojo;

import com.orm.SugarRecord;

public class ShirtItem extends SugarRecord {

    private String name;

    public ShirtItem() {
    }

    public ShirtItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
