package com.sree.snaparecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sree.snaparecipe.model.Recipe_;


/**
 * An activity representing a single Recipe detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RecipeListActivity}.
 */
public class RecipeDetailActivity extends AppCompatActivity {

    private  static String TAG = "RecipeDetailActivity";

    private Recipe_ mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mItem = (Recipe_) getIntent().getExtras().getParcelable(RecipeDetailFragment.ARG_ITEM_ID);
        Log.d(TAG,mItem.getClass().getCanonicalName()+": "+mItem.getTitle());

       /* {
            View nsv = findViewById(R.id.recipe_detail_container);
            View rootView = getLayoutInflater().inflate(R.layout.recipe_detail,(ViewGroup) nsv);

            // Show the dummy content as text in a TextView.
            if (mItem != null) {
                ((TextView) findViewById(R.id.recipe_detail)).setText(mItem.getInstructions());
            }
            ((ViewGroup) nsv).addView(rootView);
        }*/
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
       /* if (savedInstanceState == null)*/ {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(RecipeDetailFragment.ARG_ITEM_ID,
                    getIntent().getParcelableExtra(RecipeDetailFragment.ARG_ITEM_ID));
            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, RecipeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View nsv = findViewById(R.id.recipe_detail_container);
        View rootView = getLayoutInflater().inflate(R.layout.recipe_detail,(ViewGroup) nsv);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.recipe_detail)).setText(mItem.getInstructions());
        }

        return rootView;
    }*/
}
