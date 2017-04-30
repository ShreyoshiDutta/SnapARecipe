package com.sree.snaparecipe.spoonacular;

import android.app.Activity;
import android.util.Log;

import com.sree.snaparecipe.ListRecipes;
import com.sree.snaparecipe.model.Recipe;
import com.sree.snaparecipe.model.Recipe_;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sonai on 14/4/17.
 */

public class SpoonacularDelegate {
    private static final String TAG = "SpoonacularDelegate";
    /*public interface GitHubService {
        @GET("users/{user}/repos")
        Call<List<Recipe>> listRepos(@Path("user") String user);
    }
    public static final GitHubService service = new Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GitHubService.class);*/


    public static void requestRecipies(final ListRecipes callback, boolean isStubbed){
        if (isStubbed){
             Log.d(TAG,"using stubbed");
            new Thread(
            new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        Log.e(TAG,e.getMessage());
                    }
                    callback.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResponse(null,Response.success(new Recipe(null)));
                        }
                    });

                }
            }).start();
            //callback.onResponse(null,Response.success(new Recipe(null)));
        }else{
            spoonacularService.listRandomRecipes(4,"lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN").enqueue(callback);
        }

    }
    public  interface SpoonacularService {
        @GET("recipes/random")
        Call<Recipe> listRandomRecipes(@Query("number") int count, @Header("X-Mashape-Key") String token);

    }
    static OkHttpClient.Builder client = new OkHttpClient.Builder();
    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
    }
    public static final SpoonacularService spoonacularService = new Retrofit.Builder()
            .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(SpoonacularService.class);


    /*
    curl --get --include 'https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=false&number=1&tags=vegetarian%2Cdessert' \
  -H 'X-Mashape-Key: lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN' \
  -H 'Accept: application/json'
     */
}
