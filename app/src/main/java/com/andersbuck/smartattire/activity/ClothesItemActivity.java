package com.andersbuck.smartattire.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.andersbuck.smartattire.R;
import com.andersbuck.smartattire.pojo.ShirtItem;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

public class ClothesItemActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView itemListView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("SmartAttire", "ClothesItemActivity");
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_clothes_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ClothesSpinnerActivity clothesSpinnerActivity = new ClothesSpinnerActivity();

        spinner = (Spinner) findViewById(R.id.spnClothesItem);

        ArrayAdapter<CharSequence> spnrAdapter = ArrayAdapter.createFromResource(this,
                R.array.clothes_item_array, android.R.layout.simple_spinner_item);

        spnrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spnrAdapter);
        spinner.setOnItemSelectedListener(clothesSpinnerActivity);


        itemListView = (ListView) findViewById(R.id.itemListView);
        loadClothesItemList();

        editText = (EditText) findViewById(R.id.editText);

    }

    private void loadClothesItemList() {

        List<ShirtItem> shirtItems = ShirtItem.listAll(ShirtItem.class);

        List<String> shirts = new ArrayList<>();

        for (ShirtItem shirtItem : shirtItems) {
            shirts.add(shirtItem.getId() + " : " + shirtItem.getName());
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shirts);
        itemListView.setAdapter(listAdapter);
    }

    public void addItem(View view) {

        String shirtName = editText.getText().toString();

        ShirtItem shirtItem = new ShirtItem();

        shirtItem.setName(shirtName);

        ShirtItem.save(shirtItem);
        loadClothesItemList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }
}
