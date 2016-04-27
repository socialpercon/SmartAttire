package com.andersbuck.smartattire.clothesitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.andersbuck.smartattire.R;
import com.andersbuck.smartattire.db.IRecord;
import com.andersbuck.smartattire.db.PantsItem;
import com.andersbuck.smartattire.db.ShirtItem;
import com.andersbuck.smartattire.util.Constants;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class ClothesItemActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView itemListView;
    private EditText editText;
    private TextView textView;
    private IRecord item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Constants.APP_NAME, "ClothesItemActivity");
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_clothes_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.loadScreenViews();
        this.loadClothesSpinner();
        this.setListViewOnClick();
    }

    public void addItem(View view) {

        String selectedItem = (String) spinner.getSelectedItem();

        String itemName = editText.getText().toString();

        IRecord clothesItem = null;

        switch (selectedItem) {
            case "Shirt":
                Log.i(Constants.APP_NAME, "Saving Shirt Item");
                clothesItem = new ShirtItem(itemName);
                break;
            case "Pants":
                Log.i(Constants.APP_NAME, "Saving Pants Item");
                clothesItem = new PantsItem(itemName);
                break;
            default:
                break;
        }

        SugarRecord.save(clothesItem);

        this.loadClothesItemList(selectedItem);
    }

    public void deleteItem(View view) {

        SugarRecord.delete(item);

        String selectedItem = (String) spinner.getSelectedItem();
        this.loadClothesItemList(selectedItem);
    }

    private void setListViewOnClick() {

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(Constants.APP_NAME, "List view item clicked");
                IRecord selectedItem = (IRecord) itemListView.getItemAtPosition(position);

                TextView textView = (TextView) findViewById(R.id.textView3);

                textView.setText(selectedItem.getId().toString());

                ClothesItemActivity.this.item = selectedItem;
            }
        });
    }

    protected void loadClothesItemList(String selectedItem) {

        Class<? extends SugarRecord> clazz = null;
        List<? extends SugarRecord> items;

        switch (selectedItem) {
            case "Shirt":
                Log.i(Constants.APP_NAME, "Loading Shirt List");
                clazz = ShirtItem.class;
                break;
            case "Pants":
                Log.i(Constants.APP_NAME, "Loading Pants List");
                clazz = PantsItem.class;
                break;
            default:
                break;
        }

        items = SugarRecord.listAll(clazz);

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

    private void loadScreenViews() {

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
