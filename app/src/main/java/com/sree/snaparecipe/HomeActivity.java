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

public class HomeActivity extends AppCompatActivity implements  View.OnClickListener{

    // TAG is useful for labeling logs.
    private static final String TAG = "HomeActivity";


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
        //usual boiler plate code.
        super.onCreate(savedInstanceState);
        // sets the layout resource
        setContentView(R.layout.activity_home);
        /* stubbed mode is used to minimise API usages which costs real money.
         * This mode is controlled by the meta-data named "isStubbed" in AndroidManifest.xml
         */
        setStubbedMode();

        //Gets the Camera button
        FloatingActionButton imageButton = (FloatingActionButton) findViewById(R.id.floatingCameraButton);
        //and sets up the action to take on camera click.
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The action is to start a new intent called "ImageCapture"
                startActivity(new Intent(HomeActivity.this,ImageCapture.class));
            }
        });

        gallery:{
            mInflater = LayoutInflater.from(this);
            mGallery = (LinearLayout) findViewById(R.id.id_gallery);
            initData();
        }



    }

    private void setStubbedMode() {
        {
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
    }

    /*
    1. Fetches all images taken by this app, from the android gallery.
    2. Adds each image to the horizontal scrollview/linear layout.
     */
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

            /*
             * The images are being fetched in a seperate background thread because it takes sometime to read images.
             * During this time UI thread should not be blocked , otherwise it will seem as if the app has hanged
             * because app won't respond to UI gestures.
             */
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
                                // reusing transition name to hold file path of the image
                                img.setTransitionName(strUrls.get(i));
                                // sets the file path for the image to be displayed
                                img.setImageURI(mUrls[i]);
                                // on click a new intent is opened to show list of ingredients
                                img.setOnClickListener(HomeActivity.this);
                                TextView txt = (TextView) view
                                        .findViewById(R.id.id_index_gallery_item_text);
                                txt.setText(mNames[i]);
                                mGallery.addView(view);

                            }
                        }

                    } catch (Exception e) {
                    }
                    myProgressDialog.dismiss();
                }
            }.start();
        }
    }


    @Override
    public void onClick(View v) {

                // This will always be a ImageView as we are setting this up while loading the galley.
                ImageView iv = (ImageView)v;
                // Transition name has bben re-used to hold image file path.
                final String imageFilePath = iv.getTransitionName();
                final AsyncTask executeClarifai = new AsyncTask() {

                    @Override
                    protected Object doInBackground(Object[] params) {
                        Intent showIngredientView = new Intent(HomeActivity.this,ListIngredients.class);
                        /* delegates responsibility to fetch detected ingredients to Clarifai API via ClarifaiDelegate class.
                         * If isStubbed == true then ClarifaiDelegate returns stubbed response instead of making a real API call.
                         */
                        Ingredients detectedIngredients = ClarifaiDelegate.getDetectedIngredients(imageFilePath ,isStubbed);
                        showIngredientView.putExtra("Ingredients",detectedIngredients);
                        startActivity(showIngredientView);

                        Log.v(TAG,"started List Ingredeitnt activity");
                        return null;
                    }
                }.execute(new String[5]);
    }


}






