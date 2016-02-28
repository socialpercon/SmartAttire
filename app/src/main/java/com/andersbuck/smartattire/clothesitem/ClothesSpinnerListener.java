package com.andersbuck.smartattire.clothesitem;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.andersbuck.smartattire.util.Const;

public class ClothesSpinnerListener implements AdapterView.OnItemSelectedListener {

    private ClothesItemActivity clothesItemActivity;

    public ClothesSpinnerListener(ClothesItemActivity clothesItemActivity) {
        this.clothesItemActivity = clothesItemActivity;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selectedItem = (String) parent.getItemAtPosition(position);
        Log.d(Const.APP_NAME, "Selected Item: " + selectedItem);

        clothesItemActivity.loadClothesItemList(selectedItem);

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
}
