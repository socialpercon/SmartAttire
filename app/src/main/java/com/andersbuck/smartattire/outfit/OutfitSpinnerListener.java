package com.andersbuck.smartattire.outfit;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.andersbuck.smartattire.util.Const;

public class OutfitSpinnerListener implements AdapterView.OnItemSelectedListener {

    private OutfitActivity outfitActivity;

    public OutfitSpinnerListener(OutfitActivity outfitActivity) {
        this.outfitActivity = outfitActivity;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selectedItem = (String) parent.getItemAtPosition(position);
        Log.d(Const.APP_NAME, "Selected Item: " + selectedItem);

//        clothesItemActivity.loadClothesItemList(selectedItem);

//        StringBuilder sb = new StringBuilder();
//        sb.append(selectedItem);
//        sb.append(" List");
//
//        TextView textView = outfitActivity.getTextView();
//        textView.setText(sb.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i(Const.APP_NAME, "Nothing selected");
    }
}
