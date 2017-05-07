package com.sree.snaparecipe.spoonacular;

import android.util.Log;

import com.cloudinary.utils.StringUtils;
import com.sree.snaparecipe.model.Recipe;
import com.sree.snaparecipe.model.Recipe_;
import com.sree.snaparecipe.RecipeListActivity;
import com.sree.snaparecipe.model.ShortRecipe;
import com.sree.snaparecipe.model.clarifai.Ingredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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


    public static void requestRecipies(final RecipeListActivity callback, boolean isStubbed, List<Ingredient> ingredients,int howmany){
        if(isStubbed) requestRecipies(callback,isStubbed);
        else{
            Map<String,String> queryParams = new HashMap<>();
            //fillIngredients=false&ingredients=apples%2Cflour%2Csugar&limitLicense=false&number=5&ranking=1
            queryParams.put("fillIngredients","false");
            queryParams.put("ingredients", StringUtils.join(ingredients.toArray(), ","));
            queryParams.put("limitLicense","false");
            queryParams.put("number",String.valueOf(howmany));
            queryParams.put("ranking",String.valueOf(1));
            Log.v(TAG, org.apache.commons.lang3.StringUtils.join(queryParams,","));
            spoonacularService.getRecipesByIngredients(queryParams,"lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN").enqueue(new Callback<List<ShortRecipe>>() {
                @Override
                public void onResponse(Call<List<ShortRecipe>> call, Response<List<ShortRecipe>> response) {

                    //https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/informationBulk?ids=456%2C987%2C321&includeNutrition=false'
                    Map<String,String> f = new HashMap<String, String>();
                    f.put("ids",StringUtils.join(response.body().toArray(),","));
                    f.put("includeNutrition","false");
                    Log.v(TAG, org.apache.commons.lang3.StringUtils.join(f,","));
                    spoonacularService.getRecipesByIdBulk(f,"lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN").enqueue(callback);
                }

                @Override
                public void onFailure(Call<List<ShortRecipe>> call, Throwable t) {

                }
            });
        }

    }

    /*public static void requestRecipies(final ListRecipes callback, boolean isStubbed){
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
*/
    // from master-detailed flow
    public static void requestRecipies(final RecipeListActivity callback, boolean isStubbed){
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
                                    callback.onResponse(null,Response.success(new Recipe(null).getRecipes()));
                                }
                            });

                        }
                    }).start();
            //callback.onResponse(null,Response.success(new Recipe(null)));
        }else{
            spoonacularService.listRandomRecipes(4,"lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN").enqueue(new Callback<Recipe>() {
                @Override
                public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                    callback.onResponse(null,Response.success(response.body().getRecipes()));
                }

                @Override
                public void onFailure(Call<Recipe> call, Throwable t) {

                }
            });
        }

    }


    public  interface SpoonacularService {
        @GET("recipes/random")
        Call<Recipe> listRandomRecipes(@Query("number") int count, @Header("X-Mashape-Key") String token);
        @GET("recipes/findByIngredients")
        Call<List<ShortRecipe>> getRecipesByIngredients(@QueryMap(encoded = true) Map<String, String> filters , @Header("X-Mashape-Key") String token);
        @GET("recipes/informationBulk")
        Call<List<Recipe_>> getRecipesByIdBulk(@QueryMap(encoded = true) Map<String, String> filters,@Header("X-Mashape-Key") String token);

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

    /*
    // These code snippets use an open-source library. http://unirest.io/java
HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=apples%2Cflour%2Csugar&limitLicense=false&number=5&ranking=1")
.header("X-Mashape-Key", "lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN")
.header("Accept", "application/json")
.asJson();
     */

    /*
    curl --get --include 'https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/informationBulk?ids=456%2C987%2C321&includeNutrition=false' \
  -H 'X-Mashape-Key: lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN' \
  -H 'Accept: application/json'
     */
}
