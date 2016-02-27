package com.andersbuck.smartattire.activity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class ClothesSpinnerListener implements AdapterView.OnItemSelectedListener {

    private TextView textView;

    public ClothesSpinnerListener(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selectedItem = (String) parent.getItemAtPosition(position);

        Log.d("SmartAttire", "Selected Item: " + selectedItem);

        StringBuilder sb = new StringBuilder();
        sb.append(selectedItem);
        sb.append(" List");

        textView.setText(sb.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
