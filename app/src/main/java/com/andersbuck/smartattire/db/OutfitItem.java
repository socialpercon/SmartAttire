package com.andersbuck.smartattire.db;

import com.orm.SugarRecord;

public class OutfitItem extends SugarRecord {

    private String name;
    private ShirtItem shirt;
    private PantsItem pants;

    public OutfitItem() {
    }

    public OutfitItem(ShirtItem shirt, PantsItem pants) {
        this.shirt = shirt;
        this.pants = pants;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getId());
        sb.append(" : ");
        sb.append(shirt);
        sb.append(" - ");
        sb.append(pants);

        return sb.toString();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
