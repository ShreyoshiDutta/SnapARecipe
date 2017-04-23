package com.sree.snaparecipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.sree.snaparecipe.model.clarifai.*;

import com.sree.snaparecipe.model.Recipe;
import com.sree.snaparecipe.model.Recipe_;

import java.util.ArrayList;
import java.util.List;

import clarifai2.dto.prediction.Concept;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sree.snaparecipe.spoonacular.ApiDelegate.spoonacularService;

public class ListIngredients extends AppCompatActivity implements
        AdapterView.OnItemLongClickListener , AdapterView.OnItemClickListener {
    private static final String TAG = "ListIngredients";

    private List<Ingredient> conceptList;

    // Listview Adapter
    private ArrayAdapter adapter;
    // List view
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG,"creting app listview");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ingredients);


        Intent caller = getIntent();
        conceptList = ((Ingredients)caller.getParcelableExtra("Ingredients")).getIngredients();

        adapter = new ArrayAdapter(this,R.layout.list_item,R.id.name, conceptList);

        lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }


}
