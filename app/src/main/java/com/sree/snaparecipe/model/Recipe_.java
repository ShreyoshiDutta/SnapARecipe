
package com.sree.snaparecipe.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//import org.apache.commons.lang.builder.ToStringBuilder;

public class Recipe_ implements Parcelable
{

    @SerializedName("vegetarian")
    @Expose
    private Boolean vegetarian;
    @SerializedName("vegan")
    @Expose
    private Boolean vegan;
    @SerializedName("glutenFree")
    @Expose
    private Boolean glutenFree;
    @SerializedName("dairyFree")
    @Expose
    private Boolean dairyFree;
    @SerializedName("veryHealthy")
    @Expose
    private Boolean veryHealthy;
    @SerializedName("cheap")
    @Expose
    private Boolean cheap;
    @SerializedName("veryPopular")
    @Expose
    private Boolean veryPopular;
    @SerializedName("sustainable")
    @Expose
    private Boolean sustainable;
    @SerializedName("weightWatcherSmartPoints")
    @Expose
    private Integer weightWatcherSmartPoints;
    @SerializedName("gaps")
    @Expose
    private String gaps;
    @SerializedName("lowFodmap")
    @Expose
    private Boolean lowFodmap;
    @SerializedName("ketogenic")
    @Expose
    private Boolean ketogenic;
    @SerializedName("whole30")
    @Expose
    private Boolean whole30;
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("preparationMinutes")
    @Expose
    private Integer preparationMinutes;
    @SerializedName("cookingMinutes")
    @Expose
    private Integer cookingMinutes;
    @SerializedName("sourceUrl")
    @Expose
    private String sourceUrl;
    @SerializedName("spoonacularSourceUrl")
    @Expose
    private String spoonacularSourceUrl;
    @SerializedName("aggregateLikes")
    @Expose
    private Integer aggregateLikes;
    @SerializedName("creditText")
    @Expose
    private String creditText;
    @SerializedName("sourceName")
    @Expose
    private String sourceName;
    @SerializedName("extendedIngredients")
    @Expose
    private List<ExtendedIngredient> extendedIngredients = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("readyInMinutes")
    @Expose
    private Integer readyInMinutes;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageType")
    @Expose
    private String imageType;
    @SerializedName("instructions")
    @Expose
    private String instructions;
    public final static Creator<Recipe_> CREATOR = new Creator<Recipe_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Recipe_ createFromParcel(Parcel in) {
            Recipe_ instance = new Recipe_();
            instance.vegetarian = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.vegan = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.glutenFree = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.dairyFree = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.veryHealthy = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.cheap = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.veryPopular = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.sustainable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.weightWatcherSmartPoints = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.gaps = ((String) in.readValue((String.class.getClassLoader())));
            instance.lowFodmap = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.ketogenic = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.whole30 = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.servings = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.preparationMinutes = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.cookingMinutes = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.sourceUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.spoonacularSourceUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.aggregateLikes = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.creditText = ((String) in.readValue((String.class.getClassLoader())));
            instance.sourceName = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.extendedIngredients, (com.sree.snaparecipe.model.ExtendedIngredient.class.getClassLoader()));
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.readyInMinutes = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.image = ((String) in.readValue((String.class.getClassLoader())));
            instance.imageType = ((String) in.readValue((String.class.getClassLoader())));
            instance.instructions = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Recipe_[] newArray(int size) {
            return (new Recipe_[size]);
        }

    }
    ;

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public Boolean getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(Boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public Boolean getDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(Boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public Boolean getVeryHealthy() {
        return veryHealthy;
    }

    public void setVeryHealthy(Boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    public Boolean getCheap() {
        return cheap;
    }

    public void setCheap(Boolean cheap) {
        this.cheap = cheap;
    }

    public Boolean getVeryPopular() {
        return veryPopular;
    }

    public void setVeryPopular(Boolean veryPopular) {
        this.veryPopular = veryPopular;
    }

    public Boolean getSustainable() {
        return sustainable;
    }

    public void setSustainable(Boolean sustainable) {
        this.sustainable = sustainable;
    }

    public Integer getWeightWatcherSmartPoints() {
        return weightWatcherSmartPoints;
    }

    public void setWeightWatcherSmartPoints(Integer weightWatcherSmartPoints) {
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
    }

    public String getGaps() {
        return gaps;
    }

    public void setGaps(String gaps) {
        this.gaps = gaps;
    }

    public Boolean getLowFodmap() {
        return lowFodmap;
    }

    public void setLowFodmap(Boolean lowFodmap) {
        this.lowFodmap = lowFodmap;
    }

    public Boolean getKetogenic() {
        return ketogenic;
    }

    public void setKetogenic(Boolean ketogenic) {
        this.ketogenic = ketogenic;
    }

    public Boolean getWhole30() {
        return whole30;
    }

    public void setWhole30(Boolean whole30) {
        this.whole30 = whole30;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Integer getPreparationMinutes() {
        return preparationMinutes;
    }

    public void setPreparationMinutes(Integer preparationMinutes) {
        this.preparationMinutes = preparationMinutes;
    }

    public Integer getCookingMinutes() {
        return cookingMinutes;
    }

    public void setCookingMinutes(Integer cookingMinutes) {
        this.cookingMinutes = cookingMinutes;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public Integer getAggregateLikes() {
        return aggregateLikes;
    }

    public void setAggregateLikes(Integer aggregateLikes) {
        this.aggregateLikes = aggregateLikes;
    }

    public String getCreditText() {
        return creditText;
    }

    public void setCreditText(String creditText) {
        this.creditText = creditText;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(Integer readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /*@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }*/

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(vegetarian);
        dest.writeValue(vegan);
        dest.writeValue(glutenFree);
        dest.writeValue(dairyFree);
        dest.writeValue(veryHealthy);
        dest.writeValue(cheap);
        dest.writeValue(veryPopular);
        dest.writeValue(sustainable);
        dest.writeValue(weightWatcherSmartPoints);
        dest.writeValue(gaps);
        dest.writeValue(lowFodmap);
        dest.writeValue(ketogenic);
        dest.writeValue(whole30);
        dest.writeValue(servings);
        dest.writeValue(preparationMinutes);
        dest.writeValue(cookingMinutes);
        dest.writeValue(sourceUrl);
        dest.writeValue(spoonacularSourceUrl);
        dest.writeValue(aggregateLikes);
        dest.writeValue(creditText);
        dest.writeValue(sourceName);
        dest.writeList(extendedIngredients);
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(readyInMinutes);
        dest.writeValue(image);
        dest.writeValue(imageType);
        dest.writeValue(instructions);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public Recipe_(String t){
        this.title=t;
    }

    public  Recipe_(){
        super();
    }
}
