package com.sree.snaparecipe;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sree.snaparecipe.dummy.DummyContent;

import com.sree.snaparecipe.model.Recipe_;

import java.io.InputStream;
import java.net.URL;

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
            /*CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getTitle());
            }*/
        }
        Log.d(ARG_ITEM_ID,mItem.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.recipe_detail)).setText(mItem.getInstructions());
            ImageView iv = (ImageView) rootView.findViewById(R.id.detailedRecipeImg);

            try {
                InputStream is = new URL( mItem.getImage() ).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream( is );
                iv.setImageBitmap(bitmap);
            }catch (Exception ex){
                Log.e(TAG,"TAG"+ex.getMessage());
            }

            ((TextView) rootView.findViewById(R.id.timetocook)).setText(mItem.getReadyInMinutes()+" mins");
            ((TextView) rootView.findViewById(R.id.healthScore)).setText(mItem.getWeightWatcherSmartPoints()+"/100");


            ((ImageView) rootView.findViewById(R.id.imageHealthy)).setVisibility(mItem.getVeryHealthy()?View.VISIBLE:View.INVISIBLE);
            ((ImageView) rootView.findViewById(R.id.imagePopular)).setVisibility(mItem.getVeryPopular()?View.VISIBLE:View.INVISIBLE);
            ((ImageView) rootView.findViewById(R.id.imageVegan)).setVisibility(mItem.getVegan()?View.VISIBLE:View.INVISIBLE);
            ((ImageView) rootView.findViewById(R.id.imageVege)).setVisibility(mItem.getVegetarian()?View.VISIBLE:View.INVISIBLE);






        }
        Log.d(ARG_ITEM_ID,mItem.toString());

        return rootView;
    }
}
