package com.sree.snaparecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sree.snaparecipe.model.Recipe;
import com.sree.snaparecipe.model.Recipe_;

import static com.sree.snaparecipe.spoonacular.ApiDelegate.spoonacularService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRecipes extends AppCompatActivity implements
        AdapterView.OnItemLongClickListener , AdapterView.OnItemClickListener , Callback<List<Recipe_>>{
    private static final String TAG = "ListRecipes";

    private List<Recipe> recipeList;

    // Listview Adapter
    private ArrayAdapter adapter;
    // List view
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipes);

        recipeList = intializeRecipes();
        adapter = new ArrayAdapter(this,R.layout.list_item,R.id.name,recipeList);

        lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(this);
    }
    List<Recipe> intializeRecipes() {
        List<Recipe> rtrn = new ArrayList<>();
        spoonacularService.listRandomRecipes(4,"lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN").enqueue(this);
        return rtrn;
    }

    @Override
    public void onResponse(Call<List<Recipe_>> call, Response<List<Recipe_>> response) {
        List<Recipe_> ms = response.body();
        Log.v(TAG, "size=" + ms.size());

        for(Recipe_ m : ms){
            Log.v(TAG,m.toString());
        }

    }

    @Override
    public void onFailure(Call<List<Recipe_>> call, Throwable t) {

    }
   /* List<Recipe> intializeRecipes(){
        List<Recipe> rtrn =  new ArrayList<>();

            service.listRepos("ArnabRaxit").enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    List<Recipe> ms = response.body();
                    Log.v(TAG, "size=" + ms.size());

                    for(Recipe m : ms){
                        Log.v(TAG,m.toString());
                    }

                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {

                }
            });

        rtrn.add(new Recipe("Maggi"));




        return rtrn;
    }*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }


}
