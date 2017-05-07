package com.sree.snaparecipe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sree.snaparecipe.model.clarifai.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ListIngredients extends AppCompatActivity implements
        AdapterView.OnItemLongClickListener , AdapterView.OnItemClickListener {
    private static final String TAG = "ListIngredients";

    public static final String INTENT_PUT_INGREDIENTS = "INTENT_PUT_INGREDIENTS";

    private List<Ingredient> ingredientList;
    private List<Ingredient> acceptedIngredientList;

    // Listview Adapter
    private ArrayAdapter adapter;
    // List view
    private ListView lv;

    private TextView numOfIngdnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG,"creting app listview");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ingredients);


        Intent caller = getIntent();
        //fetches the ingradients from the indent which came from HomeActivity
        ingredientList = ((Ingredients)caller.getParcelableExtra("Ingredients")).getIngredients();
        acceptedIngredientList = retainWanted(ingredientList);

        adapter = new ArrayAdapter(this,R.layout.ingredient_cell,R.id.ingredientName, acceptedIngredientList);

        lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(this);

        Button showMeRecipes = (Button)findViewById(R.id.showMeRecipes);
        //FloatingActionButton showMeRecipes = (FloatingActionButton)findViewById(R.id.showMeRecipes);
        showMeRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getRecipesIntent = new Intent(ListIngredients.this,RecipeListActivity.class);
                //List<Ingredient> wantedIngredients = retainWanted(ingredientList);//.stream().filter(i -> i.value > 0.92f);
                getRecipesIntent.putExtra(INTENT_PUT_INGREDIENTS,new Ingredients(acceptedIngredientList));
                startActivity(getRecipesIntent);
            }


        });

        final EditText newIngdnt = (EditText)findViewById(R.id.newIngdnt);
        Button addIngdntBtn = (Button)findViewById(R.id.addBtn);
        addIngdntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ingredientList.add(new Ingredient(newIngdnt.getText().toString(),1.0f));
                Ingredient i = new Ingredient(newIngdnt.getText().toString(),1.0f);
                //adapter.add(i);
                adapter.insert(i,0);
                Log.v(TAG,"added new ing: "+i);
                refreshListView();
                newIngdnt.setText("");
                Toast.makeText(getBaseContext(), i.name+" has been added", Toast.LENGTH_SHORT).show();
            }
        });

        numOfIngdnt=(TextView)findViewById(R.id.numOfIngredients);
        numOfIngdnt.setText(String.valueOf(ingredientList.size()));
    }

    List<Ingredient> retainWanted(List<Ingredient> ii){
        //List<Ingredient> toReturn =   Collections.synchronizedList(ii);
        Iterator<Ingredient> iter = ii.iterator();
        while(iter.hasNext()){
            Ingredient i = iter.next();
            if(i.value<0.95f) iter.remove();
        }
        return ii;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(view.getContext());
        builder.setTitle("Remove Ingredient?");
        final Ingredient toRemove = ingredientList.get(position);
        builder.setMessage(String.format("Are you sure you wish to remove this %s?",toRemove.name));
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Remove ingredeint and Update ListView

                adapter.remove(toRemove);
                refreshListView();
                Toast.makeText(getBaseContext(), toRemove.name+" has been deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.create().show();
        return false;
    }

    private void refreshListView() {
        numOfIngdnt.setText(String.valueOf(adapter.getCount()));
    }


}
