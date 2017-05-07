package com.sree.snaparecipe.model.clarifai;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by araksh on 23/04/2017.
 */

public class Ingredient implements Parcelable {

    public String name;
    // confidence level for this ingredient. How confident Clarifai is that this is indeed correctly identified.
    public float value;

    public Ingredient(String name, float value) {
        this.name = name;
        this.value = value;
    }

    protected Ingredient(Parcel in) {
        name = in.readString();
        value = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(value);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public String toString() {
        return name ;
    }
}