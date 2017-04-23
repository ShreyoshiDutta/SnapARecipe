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
        AdapterView.OnItemLongClickListener , AdapterView.OnItemClickListener , Callback<Recipe>{
    private static final String TAG = "ListRecipes";

    private List<Recipe_> recipeList;

    // Listview Adapter
    private ArrayAdapter adapter;
    // List view
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG,"creting app listview");
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
    List<Recipe_> intializeRecipes() {
        List<Recipe_> rtrn = new ArrayList<>();
        rtrn.add(new Recipe_("khichuri"));
        Log.v(TAG,"Calling spponacular");
        //spoonacularService.listRandomRecipes(4,"lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN").enqueue(this);
        return rtrn;
    }

    @Override
    public void onResponse(Call<Recipe> call, Response<Recipe> response) {
        Recipe ms = response.body();
        Log.v(TAG, "size=" + ms.getRecipes().size());

        for(Recipe_ m : ms.getRecipes()){
            Log.v(TAG,m.toString());
        }

        recipeList=ms.getRecipes();
        adapter.clear();
        adapter.addAll(ms.getRecipes());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onFailure(Call<Recipe> call, Throwable t) {
        Log.e(TAG,t.getMessage());
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
