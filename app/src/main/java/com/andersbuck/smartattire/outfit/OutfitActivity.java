package com.andersbuck.smartattire.outfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.andersbuck.smartattire.R;
import com.andersbuck.smartattire.db.PantsItem;
import com.andersbuck.smartattire.db.ShirtItem;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

public class OutfitActivity extends AppCompatActivity {

    private Spinner spnrShirt;
    private Spinner spnrPants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_outfit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.loadScreenViews();
        this.loadClothesSpinner();

    }

    private void loadClothesSpinner() {

        OutfitSpinnerListener outfitSpinnerListener = new OutfitSpinnerListener(this);

//        ArrayAdapter<CharSequence> spnrAdapter = ArrayAdapter.createFromResource(this,
//                R.array.clothes_item_array, android.R.layout.simple_spinner_item);

        List<PantsItem> pantsItemList = SugarRecord.listAll(PantsItem.class);
        List<ShirtItem> shirtItemList = SugarRecord.listAll(ShirtItem.class);
        
        ArrayAdapter<ShirtItem> spnrShirtAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, shirtItemList);
        spnrShirtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnrShirt.setAdapter(spnrShirtAdapter);

        ArrayAdapter<ShirtItem> spnrPantsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, pantsItemList);
        spnrPantsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnrPants.setAdapter(spnrPantsAdapter);
//        spnrShirt.setOnItemSelectedListener(clothesSpinnerListener);
    }

    private void loadScreenViews() {

        spnrShirt = (Spinner) findViewById(R.id.spnrShirt);
        spnrPants = (Spinner) findViewById(R.id.spnrPants);
//        itemListView = (ListView) findViewById(R.id.itemListView);
//        editText = (EditText) findViewById(R.id.editText);
//        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }
}
