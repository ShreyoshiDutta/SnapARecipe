package com.sree.snaparecipe.clarifai;

import android.util.Log;

import com.sree.snaparecipe.model.clarifai.Ingredient;
import com.sree.snaparecipe.model.clarifai.Ingredients;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.api.request.model.PredictRequest;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

/**
 * Here we are using Clarifai library to interact with clarifai. The library is included in this project using
 * the following gradle coordinate:     compile 'com.clarifai.clarifai-api2:core:2.0.2'

 */

public class ClarifaiDelegate {
    private static final String TAG = "ClarifaiDelegate";

    // if in stubbed mode do not make real api call. Simply return hardcoded ingredients.
    public static Ingredients getDetectedIngredients(String photoPath, boolean isStubbed){
        if(!isStubbed){
            // to real api call
            return getDetectedIngredients(photoPath);
        }else{
            List<Ingredient> is = new ArrayList<>();
            is.add(new Ingredient("Carrot",.98f));
            is.add(new Ingredient("Capsicum",.95f));
            is.add(new Ingredient("Ginger",.92f));
            is.add(new Ingredient("fish",.93f));


            return new Ingredients(is);

        }
    }

    public static Ingredients getDetectedIngredients(String photoPath){
        Ingredients rtrn;
        // This is the api client
        ClarifaiClient client = new ClarifaiBuilder("IG6pHH9ByfOSwyUARZ_aahkWsVGk6fPrRh5_WVRu", "9KShzT4Su6ibkb1eFsas7MrQO4nhzFDXtecbxMsK")
                //.client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
                .buildSync(); // or use .build() to get a Future<ClarifaiClient>
                // if a Client is registered as a default instance, it will be used
                // automatically, without the user having to keep it around as a field.
                // This can be omitted if you want to manually manage your instance
                //.registerAsDefaultInstance();


        //preparing the request for the API call
        PredictRequest<Concept> req =  client.getDefaultModels().foodModel().predict();

        /* creates file from image filePath
        *  calls the api with the image file as an input
        *  For simplicity this is an synchronous call as identified by "executeSync".
        *  So screen freezes until response is returned. If time permits I will use async mode.
         */
        ClarifaiResponse<List<ClarifaiOutput<Concept>>> res = req.withInputs(ClarifaiInput.forImage(ClarifaiImage.of(new File(photoPath)))
        ).executeSync();

        // logging the response
        for(Concept c : res.get().get(0).data()){
            Log.v(TAG,c.name()+" : "+c.value());
        }
        Log.v(TAG,res.getStatus().description());
        // parses the json response and converts in into a Java bean.
        rtrn = parseConcepts(res.get().get(0).data());
        return rtrn;
    }

    private static Ingredients parseConcepts() {
        return parseConcepts(new ArrayList<Concept>());
    }

    private static Ingredients parseConcepts(List<Concept> data) {
        Ingredients rtrn = new Ingredients();
        List<Ingredient> is = new ArrayList<>();
        for(Concept c: data){
            is.add(new Ingredient(c.name(),c.value()));
        }

        rtrn.setIngredients(is);
        return rtrn;
    }
}
