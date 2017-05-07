package com.sree.snaparecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sree.snaparecipe.clarifai.ClarifaiDelegate;
import com.sree.snaparecipe.model.clarifai.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private static final String TAG = "MainActivity";

    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    private HorizontalScrollView horizontalScrollView;

    private Cursor cc = null;
    private ProgressDialog myProgressDialog = null;
    private static Uri[] mUrls = null;
    private static final List<String> strUrls = new ArrayList<>();
    private String[] mNames = null;
    private boolean isStubbed=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_test_home);

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
        /*Button getRecipe = (Button)findViewById(R.id.button);

        getRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this,ListRecipes.class));
                startActivity(new Intent(MainActivity.this,RecipeListActivity.class));

            }
        });*/

        //Gets the Camera button and sets up the action to take on camera click.
        FloatingActionButton imageButton = (FloatingActionButton) findViewById(R.id.floatingCameraButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ImageCapture.class));
            }
        });

        gallery:{
            mInflater = LayoutInflater.from(this);
            mGallery = (LinearLayout) findViewById(R.id.id_gallery);
            initData();
            //initView();
        }



    }

    private void initData() {


        // URI is private to my app
        cc = this.getContentResolver().query(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI, null, null, null,
                null);


        if (cc != null) {

            myProgressDialog = new ProgressDialog(this);
            myProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            myProgressDialog.setMessage(getResources().getString(R.string.pls_wait_txt));
            //myProgressDialog.setIcon(R.drawable.blind);
            myProgressDialog.show();

            new Thread() {
                public void run() {
                    try {
                        cc.moveToFirst();
                        mUrls = new Uri[cc.getCount()];
                        //strUrls = new String[cc.getCount()];
                        mNames = new String[cc.getCount()];
                        for (int i = 0; i < cc.getCount(); i++) {
                            cc.moveToPosition(i);
                            mUrls[i] = Uri.parse(cc.getString(1));
                            strUrls.add(cc.getString(1));
                            mNames[i] = cc.getString(3);
                            Log.e("initData : mNames[i]",mNames[i]+":"+cc.getColumnCount()+ " : " +cc.getString(3));
                            {
                                View view = mInflater.inflate(R.layout.activity_gallery_item,
                                        mGallery, false);
                                ImageView img = (ImageView) view
                                        .findViewById(R.id.id_index_gallery_item_image);
                                img.setTransitionName(strUrls.get(i));

                                img.setImageURI(mUrls[i]);
                                TextView txt = (TextView) view
                                        .findViewById(R.id.id_index_gallery_item_text);
                                txt.setText(mNames[i]);
                                mGallery.addView(view);
                                img.setOnClickListener(MainActivity.this);
                            }
                        }

                    } catch (Exception e) {
                    }
                    myProgressDialog.dismiss();
                }
            }.start();
        }
    }

    private void initView()
    {
        mGallery = (LinearLayout) findViewById(R.id.id_gallery);

        for (int i = 0; i < mUrls.length; i++)
        {

            View view = mInflater.inflate(R.layout.activity_gallery_item,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);

            img.setImageURI(mUrls[i]);
            TextView txt = (TextView) view
                    .findViewById(R.id.id_index_gallery_item_text);
            txt.setText("info "+i);
            mGallery.addView(view);

        }
    }

    @Override
    public void onClick(View v) {


                ImageView iv = (ImageView)v;
                final String imageFilePath = iv.getTransitionName();
                final AsyncTask executeClarifai = new AsyncTask() {

                    @Override
                    protected Object doInBackground(Object[] params) {
                        Intent showIngredientView = new Intent(MainActivity.this,ListIngredients.class);

                        Ingredients detectedIngredients = ClarifaiDelegate.getDetectedIngredients(imageFilePath ,isStubbed);
                        showIngredientView.putExtra("Ingredients",detectedIngredients);
                        startActivity(showIngredientView);

                        Log.v(TAG,"started List Ingredeitnt activity");
                        return null;
                    }
                }.execute(new String[5]);
    }


}






