
package com.sree.snaparecipe.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//import org.apache.commons.lang.builder.ToStringBuilder;

public class ExtendedIngredient implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("aisle")
    @Expose
    private String aisle;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("unitShort")
    @Expose
    private String unitShort;
    @SerializedName("unitLong")
    @Expose
    private String unitLong;
    @SerializedName("originalString")
    @Expose
    private String originalString;
    @SerializedName("metaInformation")
    @Expose
    private List<String> metaInformation = null;
    public final static Creator<ExtendedIngredient> CREATOR = new Creator<ExtendedIngredient>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ExtendedIngredient createFromParcel(Parcel in) {
            ExtendedIngredient instance = new ExtendedIngredient();
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.aisle = ((String) in.readValue((String.class.getClassLoader())));
            instance.image = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.amount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.unit = ((String) in.readValue((String.class.getClassLoader())));
            instance.unitShort = ((String) in.readValue((String.class.getClassLoader())));
            instance.unitLong = ((String) in.readValue((String.class.getClassLoader())));
            instance.originalString = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.metaInformation, (String.class.getClassLoader()));
            return instance;
        }

        public ExtendedIngredient[] newArray(int size) {
            return (new ExtendedIngredient[size]);
        }

    }
    ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }

    public String getOriginalString() {
        return originalString;
    }

    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }

    public List<String> getMetaInformation() {
        return metaInformation;
    }

    public void setMetaInformation(List<String> metaInformation) {
        this.metaInformation = metaInformation;
    }

    /*@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
*/
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(aisle);
        dest.writeValue(image);
        dest.writeValue(name);
        dest.writeValue(amount);
        dest.writeValue(unit);
        dest.writeValue(unitShort);
        dest.writeValue(unitLong);
        dest.writeValue(originalString);
        dest.writeList(metaInformation);
    }

    public int describeContents() {
        return  0;
    }

}
