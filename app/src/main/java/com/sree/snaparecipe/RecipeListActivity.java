package com.sree.snaparecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;


import com.sree.snaparecipe.model.Recipe;
import com.sree.snaparecipe.model.Recipe_;
import com.sree.snaparecipe.spoonacular.SpoonacularDelegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Recipes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeListActivity extends AppCompatActivity implements Callback<Recipe> {
    private static final String TAG = "RecipeListActivity";
    private boolean isStubbed=true;
    private SimpleItemRecyclerViewAdapter adapter;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.recipe_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.recipe_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        intializeRecipes();
        this.adapter=new SimpleItemRecyclerViewAdapter(new ArrayList<Recipe_>());
        recyclerView.setAdapter(this.adapter);
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


        //ms.getRecipes().get(0).writeToParcel(new Parcel());
        adapter.clear();
        adapter.addAll(ms.getRecipes());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onFailure(Call<Recipe> call, Throwable t) {


    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Recipe_> mValues;


        public SimpleItemRecyclerViewAdapter(List<Recipe_> items) {
            mValues = items;
        }

        /**
         * Remove all elements from the list.
         */
        public void clear() {
            synchronized (this) {
                mValues.clear();
            }
            notifyDataSetChanged();
        }

        public void addAll(@NonNull List<Recipe_> collection) {
            synchronized (this) {

                    mValues.addAll(collection);

            }
            notifyDataSetChanged();
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            final Recipe_ currentRecipe = holder.mItem = mValues.get(position);
            Log.v(TAG,"current recipe : "+currentRecipe.toString());
            holder.mIdView.setText(currentRecipe.getTitle());
            holder.mContentView.setText(currentRecipe.getVegan()==true?"Vegan":currentRecipe.getVegetarian()==true?"Vegetarian":"");

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putParcelable(RecipeDetailFragment.ARG_ITEM_ID, holder.mItem);
                        RecipeDetailFragment fragment = new RecipeDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.recipe_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, RecipeDetailActivity.class);
                        intent.putExtra(RecipeDetailFragment.ARG_ITEM_ID, currentRecipe);
                        //Recipe_.CREATOR.createFromParcel(currentRecipe.);
                        Log.d(TAG,holder.mItem.toString());
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Recipe_ mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
