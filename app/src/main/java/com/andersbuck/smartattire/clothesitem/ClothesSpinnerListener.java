package com.andersbuck.smartattire.clothesitem;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.andersbuck.smartattire.pojo.PantsItem;
import com.andersbuck.smartattire.pojo.ShirtItem;
import com.andersbuck.smartattire.util.Const;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class ClothesSpinnerListener implements AdapterView.OnItemSelectedListener {

    private ClothesItemActivity clothesItemActivity;

    public ClothesSpinnerListener(ClothesItemActivity clothesItemActivity) {
        this.clothesItemActivity = clothesItemActivity;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selectedItem = (String) parent.getItemAtPosition(position);
        Log.d(Const.APP_NAME, "Selected Item: " + selectedItem);

        this.loadClothesItemList(selectedItem);

        StringBuilder sb = new StringBuilder();
        sb.append(selectedItem);
        sb.append(" List");

        TextView textView = clothesItemActivity.getTextView();
        textView.setText(sb.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i(Const.APP_NAME, "Nothing selected");
    }

    protected void loadClothesItemList(String selectedItem) {

        List<? extends SugarRecord> items = null;

        switch (selectedItem) {
            case "Shirt":
                Log.i(Const.APP_NAME, "Loaded Shirt List");
                items = ShirtItem.listAll(ShirtItem.class);
                break;
            case "Pants":
                Log.i(Const.APP_NAME, "Loaded Pants List");
                items = PantsItem.listAll(PantsItem.class);
                break;
            default:
                break;
        }

        if (items == null) {
            items = new ArrayList<>();
        }
        ListView itemListView = clothesItemActivity.getItemListView();
        ArrayAdapter<? extends SugarRecord> listAdapter = new ArrayAdapter<>(clothesItemActivity, android.R.layout.simple_list_item_1, items);
        itemListView.setAdapter(listAdapter);
    }
}
