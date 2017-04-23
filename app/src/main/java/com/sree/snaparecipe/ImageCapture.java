package com.sree.snaparecipe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.sree.snaparecipe.model.clarifai.*;

import java.io.File;
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

public class ImageCapture extends AppCompatActivity {
    // tagging for logs
    private static final String TAG = "ImageCapture";
    // Save a file: path for use with ACTION_VIEW intents
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    ImageView mImageView;
    int targetW=200;
    int targetH=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture);

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
                dispatchTakePictureIntent();
    }

   /* public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mImageView = (ImageView) findViewById(R.id.imageView);
            Log.d(TAG, "width : " + mImageView.getWidth());
        }

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();

            /*final AsyncTask execute = new AsyncTask() {

                @Override
                protected Object doInBackground(Object[] params) {
                    Cloudinary cloudinary = new Cloudinary("cloudinary://492815673726876:0IjSkNjIO0JwG1DbkmSHZx8qkws@dccxbk5g8");
                    try {
                        cloudinary.uploader().upload(mCurrentPhotoPath, ObjectUtils.asMap("public_id", "sample_remote"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG,e.getMessage());
                    }
                    return null;
                }
            }.execute(new String[5]);*/

            final AsyncTask executeClarifai = new AsyncTask() {

                @Override
                protected Object doInBackground(Object[] params) {
                    ClarifaiClient client = new ClarifaiBuilder("IG6pHH9ByfOSwyUARZ_aahkWsVGk6fPrRh5_WVRu", "9KShzT4Su6ibkb1eFsas7MrQO4nhzFDXtecbxMsK")
                            //.client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
                            .buildSync(); // or use .build() to get a Future<ClarifaiClient>
                            // if a Client is registered as a default instance, it will be used
                            // automatically, without the user having to keep it around as a field.
                            // This can be omitted if you want to manually manage your instance
                            //.registerAsDefaultInstance();
                   /* ClarifaiResponse res = client.getDefaultModels().foodModel().predict()
                            .withInputs(ClarifaiInput.forImage(ClarifaiImage.of(new File(mCurrentPhotoPath)))
                            )
                            .executeSync();*/

                    PredictRequest<Concept> req =  client.getDefaultModels().foodModel().predict();
                    ClarifaiResponse<List<ClarifaiOutput<Concept>>> res = req.withInputs(ClarifaiInput.forImage(ClarifaiImage.of(new File(mCurrentPhotoPath)))
                    )
                            .executeSync();

                    res.get().get(0).data().get(0);

                    for(Concept c : res.get().get(0).data()){
                        Log.v(TAG,c.name()+" : "+c.value());
                    }


                    Log.v(TAG,res.getStatus().description());

                    Intent showIngredientView = new Intent(ImageCapture.this,ListIngredients.class);

                    Ingredients detectedIngredients = parseConcepts(res.get().get(0).data());
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

    private Ingredients parseConcepts(List<Concept> data) {
        Ingredients rtrn = new Ingredients();
        List<Ingredient> is = new ArrayList<>();
        for(Concept c: data){
            is.add(new Ingredient(c.name(),c.value()));
        }

        rtrn.setIngredients(is);
        return rtrn;
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
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

