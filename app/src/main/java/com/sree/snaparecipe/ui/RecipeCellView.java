package com.sree.snaparecipe.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sree.snaparecipe.R;
import com.sree.snaparecipe.model.Recipe_;

import android.net.Uri;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by araksh on 30/04/2017.
 */

public class RecipeCellView extends ArrayAdapter<Recipe_> {
    private static final String TAG = "RecipeCellView";

    private static LayoutInflater inflater = null;

    public RecipeCellView(Context context, List<Recipe_> data) {
        super(context,0,data);
        // TODO Auto-generated constructor stub
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.list_item, null);

        Recipe_ currentRecipe = getItem(position);
        Log.d(TAG,currentRecipe.toString());
        TextView text = (TextView) vi.findViewById(R.id.recipeName);
        text.setText(currentRecipe.getTitle());

        /*TextView text1 = (TextView) vi.findViewById(R.id.recipeDetails);
        text1.setText(currentRecipe.getInstructions());*/

        TextView textV = (TextView) vi.findViewById(R.id.recipeV);
        textV.setText(currentRecipe.getVegan()==true?"Vegan":currentRecipe.getVegetarian()==true?"Vegetarian":"");

        TextView textT = (TextView) vi.findViewById(R.id.recipeReadyInMins);
        textT.setText(Integer.toString(currentRecipe.getReadyInMinutes()));


        ImageView img = (ImageView) vi.findViewById(R.id.recipeImage);

            img.setImageURI(Uri.parse(currentRecipe.getImage()));

        img.setContentDescription(currentRecipe.getImage());




        return vi;
    }


}
