package com.sree.snaparecipe.spoonacular;

import com.sree.snaparecipe.model.Recipe;
import com.sree.snaparecipe.model.Recipe_;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sonai on 14/4/17.
 */

public class ApiDelegate {

    public interface GitHubService {
        @GET("users/{user}/repos")
        Call<List<Recipe>> listRepos(@Path("user") String user);
    }
    public static final GitHubService service = new Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GitHubService.class);


    public  interface SpoonacularService {
        @GET("recipes/random")
        Call<List<Recipe_>> listRandomRecipes(@Query("number") int count, @Header("Authorization") String token);

    }
    public static final SpoonacularService spoonacularService = new Retrofit.Builder().baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(SpoonacularService.class);
    /*
    curl --get --include 'https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=false&number=1&tags=vegetarian%2Cdessert' \
  -H 'X-Mashape-Key: lIQwnxhTt8mshrspQjiOj9uYDVs5p1K8otZjsncetRKjGas2oN' \
  -H 'Accept: application/json'
     */
}
