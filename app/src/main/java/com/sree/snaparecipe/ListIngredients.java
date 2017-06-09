package com.sree.snaparecipe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sree.snaparecipe.model.clarifai.*;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ListIngredients extends MyActivity implements
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
    private FloatingActionButton showMeRecipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG,"creting app listview");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ingredients);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

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

        //showMeRecipes = (FloatingActionButton)findViewById(R.id.showMeRecipes);
        showMeRecipes = getFab(this,lv);

        lv.addFooterView(showMeRecipes);
        showMeRecipes.setEnabled(adapter.getCount()>0);
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

        final FloatingActionButton addIngdntBtn = (FloatingActionButton)findViewById(R.id.addBtn);
        addIngdntBtn.setVisibility(View.INVISIBLE);
        final EditText newIngdnt = (EditText)findViewById(R.id.newIngdnt);
        newIngdnt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                addIngdntBtn.setEnabled(false);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addIngdntBtn.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

                addIngdntBtn.setEnabled(true);
                addIngdntBtn.setVisibility(View.VISIBLE);
            }
        });





        addIngdntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ingredientList.add(new Ingredient(newIngdnt.getText().toString(),1.0f));
                String ingName = newIngdnt.getText().toString();
                if(StringUtils.isEmpty(ingName)) {
                    Toast.makeText(getBaseContext(),  "enter an ingredient", Toast.LENGTH_SHORT).show();
                }else{
                    Ingredient i = new Ingredient(ingName, 1.0f);
                    //adapter.add(i);
                    adapter.insert(i, 0);
                    Log.v(TAG, "added new ing: " + i);
                    refreshListView();
                    newIngdnt.setText("");
                    Toast.makeText(getBaseContext(), i.name + " has been added", Toast.LENGTH_SHORT).show();
                    addIngdntBtn.setEnabled(false);
                    addIngdntBtn.setVisibility(View.INVISIBLE);
                    View view = ListIngredients.this.getCurrentFocus();
                    if (view != null) {

                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });

        numOfIngdnt=(TextView)findViewById(R.id.numOfIngredients);
        numOfIngdnt.setText(String.valueOf(ingredientList.size()));

        //Gets the Camera button
        FloatingActionButton imageButton = (FloatingActionButton) findViewById(R.id.floatingAddMoreButton);
        //and sets up the action to take on camera click.
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The action is to start a new intent called "ImageCapture"
                startActivity(new Intent(ListIngredients.this,ImageCapture.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent){
        Log.d(TAG, "in onNewIntent");
        if((Ingredients)intent.getParcelableExtra("Ingredients") !=null) {
            List<Ingredient> newIngredientList = ((Ingredients) intent.getParcelableExtra("Ingredients")).getIngredients();
            for (Ingredient i : retainWanted(newIngredientList)) {
                adapter.insert(i, 0);
                Log.v(TAG, "added new ing: " + i);
            }

            refreshListView();
        }
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
        showMeRecipes.setEnabled(adapter.getCount()>0);
        numOfIngdnt.setText(String.valueOf(adapter.getCount()));
    }

    public FloatingActionButton getFab(Context context, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return (FloatingActionButton) inflater.inflate(R.layout.showmerecipes, parent, false);
    }

}
