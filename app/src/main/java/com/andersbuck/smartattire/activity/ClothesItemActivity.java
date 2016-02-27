package com.andersbuck.smartattire.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.andersbuck.smartattire.R;
import com.andersbuck.smartattire.pojo.ShirtItem;
import com.orm.SugarContext;

import java.util.List;

public class ClothesItemActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView itemListView;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("SmartAttire", "ClothesItemActivity");
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_clothes_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.loadScreenComponents();
        this.loadClothesSpinner();
        this.loadClothesItemList();
    }

    public void addItem(View view) {

        String shirtName = editText.getText().toString();
        ShirtItem shirtItem = new ShirtItem();
        shirtItem.setName(shirtName);
        ShirtItem.save(shirtItem);

        loadClothesItemList();
    }

    private void loadClothesSpinner() {

        ClothesSpinnerListener clothesSpinnerListener = new ClothesSpinnerListener(textView);

        ArrayAdapter<CharSequence> spnrAdapter = ArrayAdapter.createFromResource(this,
                R.array.clothes_item_array, android.R.layout.simple_spinner_item);
        spnrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spnrAdapter);
        spinner.setOnItemSelectedListener(clothesSpinnerListener);
    }

    private void loadClothesItemList() {

        List<ShirtItem> shirtItems = ShirtItem.listAll(ShirtItem.class);

        ArrayAdapter<ShirtItem> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shirtItems);
        itemListView.setAdapter(listAdapter);
    }

    private void loadScreenComponents() {

        spinner = (Spinner) findViewById(R.id.spnClothesItem);
        itemListView = (ListView) findViewById(R.id.itemListView);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }
}
