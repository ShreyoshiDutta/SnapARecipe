package com.sree.snaparecipe;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sree.snaparecipe.clarifai.ClarifaiDelegate;
import com.sree.snaparecipe.model.clarifai.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends MyActivity implements  View.OnClickListener{

    // TAG is useful for labeling logs.
    private static final String TAG = "HomeActivity";
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;


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
    private ProgressBar spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //usual boiler plate code.
        super.onCreate(savedInstanceState);
        // sets the layout resource
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_home);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();

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

        spinner = (ProgressBar)findViewById(R.id.progressBar2);
        spinner.setVisibility(View.INVISIBLE);




    }

    private void setStubbedMode() {
        isStubbed = getSharedPreferences(PreferencesActivity.PREFS_NAME, 0).getBoolean(PreferencesActivity.STUBBED_MODE,PreferencesActivity.STUBBED_MODE_VALUE);
    }

    /*
    1. Fetches all images taken by this app, from the android gallery.
    2. Adds each image to the horizontal scrollview/linear layout.
     */
    private void initData() {
        requestPermission();
        // URI is private to my app
        cc = this.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media.DESCRIPTION + " LIKE ?", new String[]{ImageCapture.ImageFilePrefix},
                null);
        Log.v(TAG,"number of images retrieved = "+cc.getCount());

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
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                public void run() {

                        cc.moveToFirst();
                        mUrls = new Uri[cc.getCount()];
                        //strUrls = new String[cc.getCount()];
                        mNames = new String[cc.getCount()];
                        for (int i = 0; i < cc.getCount(); i++) {
                            try{
                                cc.moveToPosition(i);
                                mUrls[i] = Uri.parse(cc.getString(1));
                                strUrls.add(cc.getString(1));
                                mNames[i] = cc.getString(3);
                                Log.d("initData : mNames[i]",mNames[i]+":"+cc.getColumnCount()+ " : " +cc.getString(3)+ " : " + mUrls[i].toString());
                                {
                                    final int THUMBSIZE = 128;
                                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(mUrls[i].toString()),
                                            THUMBSIZE, THUMBSIZE);
                                    Log.d(TAG,Integer.toString(thumbImage.getByteCount()));
                                    final View view = mInflater.inflate(R.layout.activity_gallery_item,
                                            mGallery, false);
                                    ImageView img = (ImageView) view
                                            .findViewById(R.id.id_index_gallery_item_image);
                                    // reusing transition name to hold file path of the image
                                    img.setTransitionName(strUrls.get(i));
                                    // sets the file path for the image to be displayed
                                    //img.setImageURI(mUrls[i]);

                                    img.setImageBitmap(thumbImage);
                                    img.setBackgroundResource(R.drawable.img_background_border);
                                    // on click a new intent is opened to show list of ingredients
                                    img.setOnClickListener(HomeActivity.this);
                                    TextView txt = (TextView) view
                                            .findViewById(R.id.id_index_gallery_item_text);
                                    txt.setText(mNames[i]);
                                    HomeActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mGallery.addView(view);
                                        }
                                    });


                                }
                            }catch (Exception e) {
                                Log.e(TAG,e.getMessage());
                            }
                        }


                    myProgressDialog.dismiss();
                }
            }.start();
        }
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
                // Starts spinner
                spinner.setVisibility(View.VISIBLE);

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

                        Log.v(TAG,"started List Ingredeitnt activity in stubbed mode?"+isStubbed);
                        return null;
                    }
                }.execute(new String[5]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }



}






