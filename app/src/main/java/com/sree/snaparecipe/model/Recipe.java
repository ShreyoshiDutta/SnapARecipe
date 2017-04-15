
package com.sree.snaparecipe.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//import org.apache.commons.lang.builder.ToStringBuilder;

public class Recipe implements Parcelable
{

    @SerializedName("recipes")
    @Expose
    private List<Recipe_> recipes = null;
    public final static Creator<Recipe> CREATOR = new Creator<Recipe>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Recipe createFromParcel(Parcel in) {
            Recipe instance = new Recipe();
            in.readList(instance.recipes, (com.sree.snaparecipe.model.Recipe_.class.getClassLoader()));
            return instance;
        }

        public Recipe[] newArray(int size) {
            return (new Recipe[size]);
        }

    }
    ;

    public List<Recipe_> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe_> recipes) {
        this.recipes = recipes;
    }

    /*@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }*/

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(recipes);
    }

    public int describeContents() {
        return  0;
    }

}
