package com.andersbuck.smartattire.clothesitem;

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
import com.andersbuck.smartattire.pojo.PantsItem;
import com.andersbuck.smartattire.pojo.ShirtItem;
import com.andersbuck.smartattire.util.Const;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class ClothesItemActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView itemListView;
    private EditText editText;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Const.APP_NAME, "ClothesItemActivity");
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_clothes_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.loadScreenComponents();
        this.loadClothesSpinner();
    }

    public void addItem(View view) {

        String selectedItem = (String) spinner.getSelectedItem();

        String itemName = editText.getText().toString();

        switch (selectedItem) {
            case "Shirt":
                ShirtItem shirtItem = new ShirtItem(itemName);
                shirtItem.save();
                break;
            case "Pants":
                PantsItem pantsItem = new PantsItem(itemName);
                pantsItem.save();
                break;
            default:
                break;
        }

        this.loadClothesItemList(selectedItem);
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

        ArrayAdapter<? extends SugarRecord> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemListView.setAdapter(listAdapter);
    }

    private void loadClothesSpinner() {

        ClothesSpinnerListener clothesSpinnerListener = new ClothesSpinnerListener(this);

        ArrayAdapter<CharSequence> spnrAdapter = ArrayAdapter.createFromResource(this,
                R.array.clothes_item_array, android.R.layout.simple_spinner_item);
        spnrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spnrAdapter);
        spinner.setOnItemSelectedListener(clothesSpinnerListener);
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

    public Spinner getSpinner() {
        return spinner;
    }

    public ListView getItemListView() {
        return itemListView;
    }

    public EditText getEditText() {
        return editText;
    }

    public TextView getTextView() {
        return textView;
    }
}
