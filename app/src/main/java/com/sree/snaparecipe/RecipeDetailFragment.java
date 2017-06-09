package com.sree.snaparecipe;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.sree.snaparecipe.dummy.DummyContent;

import com.sree.snaparecipe.model.Recipe_;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */
public class RecipeDetailFragment extends Fragment {
    private static String TAG = "RecipeDetailFragment";
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Recipe_ mItem;

    ImageView iv;
    ProgressBar spinner;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = (Recipe_) (getArguments().getParcelable(ARG_ITEM_ID));

            Activity activity = this.getActivity();


        }
        Log.d(ARG_ITEM_ID,mItem.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_detail, container, false);
       /* Toolbar mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar_recipe_detail);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);*/


        ScrollView scv = ((ScrollView)container);

        /*container.setFocusableInTouchMode(true);
        ((ScrollView)container).setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
*/

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.recipe_detail)).setText(mItem.getInstructions());
            iv = (ImageView) rootView.findViewById(R.id.detailedRecipeImg);
            Log.d(TAG,mItem.getImage());

            spinner = (ProgressBar) rootView.findViewById(R.id.spinner_recipe_detail);
            spinner.setVisibility(View.VISIBLE);
            new RetrieveRecipeImage().execute(mItem.getImage());

            ((TextView) rootView.findViewById(R.id.timetocook)).setText(mItem.getReadyInMinutes()+" mins");
            ((TextView) rootView.findViewById(R.id.healthScore)).setText(Integer.toString(mItem.getWeightWatcherSmartPoints()));


            ((ImageView) rootView.findViewById(R.id.imageHealthy)).setVisibility(mItem.getVeryHealthy()?View.VISIBLE:View.INVISIBLE);
            ((ImageView) rootView.findViewById(R.id.imagePopular)).setVisibility(mItem.getVeryPopular()?View.VISIBLE:View.INVISIBLE);
            ((ImageView) rootView.findViewById(R.id.imageVegan)).setVisibility(mItem.getVegan()?View.VISIBLE:View.INVISIBLE);
            ((ImageView) rootView.findViewById(R.id.imageVege)).setVisibility(mItem.getVegetarian()?View.VISIBLE:View.INVISIBLE);






        }
        Log.d(ARG_ITEM_ID,mItem.toString());

        scv.pageScroll(View.FOCUS_DOWN);

        return rootView;
    }

    class RetrieveRecipeImage extends AsyncTask<String, Void, Bitmap> {

        private Exception exception;

        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            try {
                Log.d(TAG,mItem.getImage());
                URLConnection con = new URL(urls[0]).openConnection();
                con.setReadTimeout(1000);
                con.setConnectTimeout(500);
                InputStream is = con.getInputStream();
                bitmap = BitmapFactory.decodeStream( is );

                Log.d(TAG,Integer.toString(bitmap.getDensity()));
            }catch (Exception ex){
                Log.e(TAG,"TAG"+ex.getMessage());

            }
            return bitmap;
        }

        protected void onPostExecute(final Bitmap bitmap) {
            if(bitmap!=null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        spinner.setVisibility(View.INVISIBLE);
                        iv.setImageBitmap(bitmap);
                    }
                });

            }else{
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        spinner.setVisibility(View.INVISIBLE);
                        iv.setImageResource(R.drawable.noimageavailable);
                    }
                });
            }
        }
    }
}
