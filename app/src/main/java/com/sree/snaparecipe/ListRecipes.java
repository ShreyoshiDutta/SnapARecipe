package com.sree.snaparecipe;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sree.snaparecipe.model.Recipe;
import com.sree.snaparecipe.model.Recipe_;
import com.sree.snaparecipe.spoonacular.SpoonacularDelegate;
import com.sree.snaparecipe.ui.RecipeCellView;

import static com.sree.snaparecipe.spoonacular.SpoonacularDelegate.spoonacularService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRecipes extends AppCompatActivity implements
        AdapterView.OnItemLongClickListener , AdapterView.OnItemClickListener , Callback<Recipe>{
    private static final String TAG = "ListRecipes";
    private boolean isStubbed=true;

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

        setStubbedMode:{
            try {
                ApplicationInfo ai = getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
                Bundle bundle = ai.metaData;
                isStubbed = bundle.getBoolean("isStubbed");
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
            } catch (NullPointerException e) {
                Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
            }
        }


        recipeList = intializeRecipes();
        //adapter = new ArrayAdapter(this,R.layout.list_item,R.id.name,recipeList);
        adapter = new RecipeCellView(this,recipeList);

        lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(this);
    }
    List<Recipe_> intializeRecipes() {
        List<Recipe_> rtrn = new ArrayList<>();

        Log.v(TAG,"Calling spponacular");
        SpoonacularDelegate.requestRecipies(this,isStubbed);
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
        //ms.getRecipes().get(0).writeToParcel(new Parcel());
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
