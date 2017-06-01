package com.sree.snaparecipe;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import static android.support.v4.content.ContextCompat.*;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.sree.snaparecipe.clarifai.ClarifaiDelegate;
import com.sree.snaparecipe.model.clarifai.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.api.request.model.PredictRequest;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

//import org.apache.commons.lang.ObjectUtils;
//import org.apache.commons.lang3.ObjectUtils;

//import static

public class ImageCapture extends AppCompatActivity {
    // tagging for logs

    private static final String TAG = "ImageCapture";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 11;
    private boolean isStubbed=true;
    public static String ImageFilePrefix="JPEG_SNAPARECIPE";

    // Save a file: path for use with ACTION_VIEW intents
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    ImageView mImageView;
    int targetW=200;
    int targetH=200;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture);
        requestPermToWriteFile();
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

        //shows image (might not keep it anymore)
        mImageView = (ImageView) findViewById(R.id.imageView);
        ViewTreeObserver vto = mImageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                     @Override
                                     public boolean onPreDraw() {
                                         Log.v(TAG,"in predraw : "+targetH+"-"+targetW);
                                         mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                                         targetH = mImageView.getMeasuredHeight();
                                         targetW = mImageView.getMeasuredWidth();
                                         return true;
                                     }
                                 });

    }



   //on camera successfully capturing an image this method is called
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();


            writeToGallery();


//makes an asynchronous call clarifai API
            final AsyncTask executeClarifai = new AsyncTask() {

                @Override
                protected Object doInBackground(Object[] params) {
                    Intent showIngredientView = new Intent(ImageCapture.this,ListIngredients.class);
                    showIngredientView.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    showIngredientView.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    Ingredients detectedIngredients = ClarifaiDelegate.getDetectedIngredients(mCurrentPhotoPath,isStubbed);
                    showIngredientView.putExtra("Ingredients",detectedIngredients);
                    startActivity(showIngredientView);

                    Log.v(TAG,"started List Ingredeitnt activity");
                    return null;
                }
            }.execute(new String[5]);



            /*Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);*/
        }
    }



//creates file to save images in gallery
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = ImageFilePrefix + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e(TAG, ex.getMessage());


            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.sree.snaparecipe",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public  void requestPermToWriteFile() {
        Log.v(TAG,"addImageToGallery");
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.MEDIA_CONTENT_CONTROL)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults){
        dispatchTakePictureIntent();
        writeToGallery();
    }

    private void writeToGallery(){
        Log.v(TAG,"writing to gallery");
        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DESCRIPTION,ImageCapture.ImageFilePrefix);
        values.put(MediaStore.MediaColumns.DATA, mCurrentPhotoPath);

        this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


    }

    private void setPic() {
        // Get the dimensions of the View
         /*targetW = mImageView.getWidth();
         targetH = mImageView.getHeight();*/

        Log.v(TAG,targetH+"-"+targetW);
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);


    }
}

