package com.andersbuck.smartattire.activity;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

public class ClothesSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selectedItem = (String) parent.getItemAtPosition(position);

        Log.d("SmartAttire", "Selected Item: " + selectedItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
