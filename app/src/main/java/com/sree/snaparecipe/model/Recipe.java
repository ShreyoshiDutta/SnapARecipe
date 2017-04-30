
package com.sree.snaparecipe.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//import org.apache.commons.lang.builder.ToStringBuilder;

public class Recipe implements Parcelable
{

    public Recipe(List<Recipe_> recipes) {
        if (recipes==null){
            List<Recipe_> rs = new ArrayList<>();
            rs.add(new Recipe_("khichuri"));
            rs.add(new Recipe_("omlete"));
            rs.add(new Recipe_("rice"));

            Gson g = new Gson();
            rs=g.fromJson(def,Recipe.class).getRecipes();
            this.recipes=rs;
        }else {
            this.recipes = recipes;
        }
    }

    public Recipe() {
    }

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

    String def = "{\n" +
            "\t\"recipes\": [{\n" +
            "\t\t\"vegetarian\": true,\n" +
            "\t\t\"vegan\": false,\n" +
            "\t\t\"glutenFree\": false,\n" +
            "\t\t\"dairyFree\": false,\n" +
            "\t\t\"veryHealthy\": false,\n" +
            "\t\t\"cheap\": false,\n" +
            "\t\t\"veryPopular\": false,\n" +
            "\t\t\"sustainable\": false,\n" +
            "\t\t\"weightWatcherSmartPoints\": 20,\n" +
            "\t\t\"gaps\": \"no\",\n" +
            "\t\t\"lowFodmap\": false,\n" +
            "\t\t\"ketogenic\": false,\n" +
            "\t\t\"whole30\": false,\n" +
            "\t\t\"servings\": 6,\n" +
            "\t\t\"preparationMinutes\": 10,\n" +
            "\t\t\"cookingMinutes\": 0,\n" +
            "\t\t\"sourceUrl\": \"http://www.thekitchenmagpie.com/swiss-mushroom-asparagus-quiche\",\n" +
            "\t\t\"spoonacularSourceUrl\": \"https://spoonacular.com/swiss-mushroom-asparagus-quiche-570610\",\n" +
            "\t\t\"aggregateLikes\": 42,\n" +
            "\t\t\"spoonacularScore\": 58.0,\n" +
            "\t\t\"healthScore\": 7.0,\n" +
            "\t\t\"creditText\": \"The Kitchen Magpie\",\n" +
            "\t\t\"sourceName\": \"The Kitchen Magpie\",\n" +
            "\t\t\"pricePerServing\": 186.44,\n" +
            "\t\t\"extendedIngredients\": [{\n" +
            "\t\t\t\"id\": 11011,\n" +
            "\t\t\t\"aisle\": \"Produce\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/asparagus.png\",\n" +
            "\t\t\t\"name\": \"asparagus\",\n" +
            "\t\t\t\"amount\": 2.0,\n" +
            "\t\t\t\"unit\": \"cups\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cups\",\n" +
            "\t\t\t\"originalString\": \"2 cups asparagus\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1053,\n" +
            "\t\t\t\"aisle\": \"Milk, Eggs, Other Dairy\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/fluid-cream.jpg\",\n" +
            "\t\t\t\"name\": \"cream\",\n" +
            "\t\t\t\"amount\": 1.5,\n" +
            "\t\t\t\"unit\": \"cups\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cups\",\n" +
            "\t\t\t\"originalString\": \"1½ cups of cream\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1123,\n" +
            "\t\t\t\"aisle\": \"Milk, Eggs, Other Dairy\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/egg.jpg\",\n" +
            "\t\t\t\"name\": \"eggs\",\n" +
            "\t\t\t\"amount\": 4.0,\n" +
            "\t\t\t\"unit\": \"\",\n" +
            "\t\t\t\"unitShort\": \"\",\n" +
            "\t\t\t\"unitLong\": \"\",\n" +
            "\t\t\t\"originalString\": \"4 eggs\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 11215,\n" +
            "\t\t\t\"aisle\": \"Produce\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/garlic-roasted.jpg\",\n" +
            "\t\t\t\"name\": \"garlic\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"clove\",\n" +
            "\t\t\t\"unitShort\": \"clove\",\n" +
            "\t\t\t\"unitLong\": \"clove\",\n" +
            "\t\t\t\"originalString\": \"1 clove of garlic\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1026,\n" +
            "\t\t\t\"aisle\": \"Cheese\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/mozzarella-fresh.jpg\",\n" +
            "\t\t\t\"name\": \"mozzarella cheese\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cup\",\n" +
            "\t\t\t\"originalString\": \"1 cup of mozzarella cheese\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 11260,\n" +
            "\t\t\t\"aisle\": \"Produce\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/mushrooms.jpg\",\n" +
            "\t\t\t\"name\": \"mushrooms\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cup\",\n" +
            "\t\t\t\"originalString\": \"1 cup mushrooms\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 4053,\n" +
            "\t\t\t\"aisle\": \"Oil, Vinegar, Salad Dressing\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/olive-oil.jpg\",\n" +
            "\t\t\t\"name\": \"olive oil\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"tsp\",\n" +
            "\t\t\t\"unitShort\": \"tsp\",\n" +
            "\t\t\t\"unitLong\": \"teaspoon\",\n" +
            "\t\t\t\"originalString\": \"1 tsp olive oil\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 11282,\n" +
            "\t\t\t\"aisle\": \"Produce\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/brown-onion.jpg\",\n" +
            "\t\t\t\"name\": \"onion\",\n" +
            "\t\t\t\"amount\": 0.5,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cups\",\n" +
            "\t\t\t\"originalString\": \"½ cup diced onion\",\n" +
            "\t\t\t\"metaInformation\": [\"diced\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 18334,\n" +
            "\t\t\t\"aisle\": \"Refrigerated;Frozen;Baking\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/pie-crust.jpg\",\n" +
            "\t\t\t\"name\": \"pie shell\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"\",\n" +
            "\t\t\t\"unitShort\": \"\",\n" +
            "\t\t\t\"unitLong\": \"\",\n" +
            "\t\t\t\"originalString\": \"1 pre-made pie shell\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1040,\n" +
            "\t\t\t\"aisle\": \"Cheese\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/Swiss-cheese.jpg\",\n" +
            "\t\t\t\"name\": \"swiss cheese\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cup\",\n" +
            "\t\t\t\"originalString\": \"1 cup of swiss cheese\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 570610,\n" +
            "\t\t\"title\": \"Swiss Mushroom Asparagus Quiche\",\n" +
            "\t\t\"readyInMinutes\": 10,\n" +
            "\t\t\"image\": \"https://spoonacular.com/recipeImages/Swiss-Mushroom-Asparagus-Quiche-570610.jpg\",\n" +
            "\t\t\"imageType\": \"jpg\",\n" +
            "\t\t\"cuisines\": [\"mediterranean\", \"european\", \"french\"],\n" +
            "\t\t\"dishTypes\": [\"lunch\", \"main course\", \"morning meal\", \"brunch\", \"main dish\", \"breakfast\", \"dinner\"],\n" +
            "\t\t\"instructions\": \"Kick the tires and light the fires to 350 degrees.Take the olive oil and roast the veggies in the oven for 5-10 minutes. This prevents the quiche from getting soggy.Whip the eggs and cream together.Something else I tried was brushing the pie shell with egg white, then baking it for 5 minutes.The pie shell did shrink a bit, but it was not the least bit soggy in the end. It helps to keep the egg and milk from soaking in.Take your veggies and place them in the shell.Sprinkle the cheese over the top, then pour the cream egg mixture. You may or may not have egg mixture left, depending on how much the vegetables shrank. I had some left.Bake at 350 until cooked in the middle, all golden and crispy.\",\n" +
            "\t\t\"analyzedInstructions\": [{\n" +
            "\t\t\t\"name\": \"\",\n" +
            "\t\t\t\"steps\": [{\n" +
            "\t\t\t\t\"number\": 1,\n" +
            "\t\t\t\t\"step\": \"Kick the tires and light the fires to 350 degrees.Take the olive oil and roast the veggies in the oven for 5-10 minutes. This prevents the quiche from getting soggy.Whip the eggs and cream together.Something else I tried was brushing the pie shell with egg white, then baking it for 5 minutes.The pie shell did shrink a bit, but it was not the least bit soggy in the end. It helps to keep the egg and milk from soaking in.Take your veggies and place them in the shell.Sprinkle the cheese over the top, then pour the cream/egg mixture. You may or may not have egg mixture left, depending on how much the vegetables shrank. I had some left.\",\n" +
            "\t\t\t\t\"ingredients\": [{\n" +
            "\t\t\t\t\t\"id\": 4053,\n" +
            "\t\t\t\t\t\"name\": \"olive oil\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/olive-oil.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 18334,\n" +
            "\t\t\t\t\t\"name\": \"pie crust\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/pie-crust.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 1053,\n" +
            "\t\t\t\t\t\"name\": \"cream\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/fluid-cream.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 1123,\n" +
            "\t\t\t\t\t\"name\": \"egg\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/egg.jpg\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"equipment\": [{\n" +
            "\t\t\t\t\t\"id\": 404784,\n" +
            "\t\t\t\t\t\"name\": \"oven\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/oven.jpg\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"length\": {\n" +
            "\t\t\t\t\t\"number\": 15,\n" +
            "\t\t\t\t\t\"unit\": \"minutes\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"number\": 2,\n" +
            "\t\t\t\t\"step\": \"Bake at 350 until cooked in the middle, all golden and crispy.\",\n" +
            "\t\t\t\t\"ingredients\": [],\n" +
            "\t\t\t\t\"equipment\": []\n" +
            "\t\t\t}]\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"vegetarian\": true,\n" +
            "\t\t\"vegan\": true,\n" +
            "\t\t\"glutenFree\": true,\n" +
            "\t\t\"dairyFree\": true,\n" +
            "\t\t\"veryHealthy\": false,\n" +
            "\t\t\"cheap\": false,\n" +
            "\t\t\"veryPopular\": true,\n" +
            "\t\t\"sustainable\": false,\n" +
            "\t\t\"weightWatcherSmartPoints\": 5,\n" +
            "\t\t\"gaps\": \"no\",\n" +
            "\t\t\"lowFodmap\": false,\n" +
            "\t\t\"ketogenic\": false,\n" +
            "\t\t\"whole30\": true,\n" +
            "\t\t\"servings\": 6,\n" +
            "\t\t\"sourceUrl\": \"http://www.vegetariantimes.com/recipe/colorful-oven-fries/\",\n" +
            "\t\t\"spoonacularSourceUrl\": \"https://spoonacular.com/colorful-oven-fries-758989\",\n" +
            "\t\t\"aggregateLikes\": 392,\n" +
            "\t\t\"spoonacularScore\": 64.0,\n" +
            "\t\t\"healthScore\": 8.0,\n" +
            "\t\t\"creditText\": \"Vegetarian Times\",\n" +
            "\t\t\"sourceName\": \"Vegetarian Times\",\n" +
            "\t\t\"pricePerServing\": 54.84,\n" +
            "\t\t\"extendedIngredients\": [{\n" +
            "\t\t\t\"id\": 4582,\n" +
            "\t\t\t\"aisle\": \"Oil, Vinegar, Salad Dressing\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/vegetable-oil.jpg\",\n" +
            "\t\t\t\"name\": \"canola oil\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"Tbs\",\n" +
            "\t\t\t\"unitShort\": \"Tbs\",\n" +
            "\t\t\t\"unitLong\": \"Tb\",\n" +
            "\t\t\t\"originalString\": \"1 Tbs. canola oil\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 2010,\n" +
            "\t\t\t\"aisle\": \"Spices and Seasonings\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cinnamon.jpg\",\n" +
            "\t\t\t\"name\": \"ground cinnamon\",\n" +
            "\t\t\t\"amount\": 0.25,\n" +
            "\t\t\t\"unit\": \"tsp\",\n" +
            "\t\t\t\"unitShort\": \"tsp\",\n" +
            "\t\t\t\"unitLong\": \"teaspoons\",\n" +
            "\t\t\t\"originalString\": \"¼ tsp. ground cinnamon, optional\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1002030,\n" +
            "\t\t\t\"aisle\": \"Spices and Seasonings\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/pepper.jpg\",\n" +
            "\t\t\t\"name\": \"ground pepper\",\n" +
            "\t\t\t\"amount\": 0.5,\n" +
            "\t\t\t\"unit\": \"tsp\",\n" +
            "\t\t\t\"unitShort\": \"tsp\",\n" +
            "\t\t\t\"unitLong\": \"teaspoons\",\n" +
            "\t\t\t\"originalString\": \"½ tsp. ground black pepper\",\n" +
            "\t\t\t\"metaInformation\": [\"black\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 11413,\n" +
            "\t\t\t\"aisle\": \"Baking\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/white-powder.jpg\",\n" +
            "\t\t\t\"name\": \"potato starch\",\n" +
            "\t\t\t\"amount\": 2.0,\n" +
            "\t\t\t\"unit\": \"Tbs\",\n" +
            "\t\t\t\"unitShort\": \"Tbs\",\n" +
            "\t\t\t\"unitLong\": \"Tbs\",\n" +
            "\t\t\t\"originalString\": \"2 Tbs. potato starch\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 2047,\n" +
            "\t\t\t\"aisle\": \"Spices and Seasonings\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt.jpg\",\n" +
            "\t\t\t\"name\": \"salt\",\n" +
            "\t\t\t\"amount\": 0.5,\n" +
            "\t\t\t\"unit\": \"tsp\",\n" +
            "\t\t\t\"unitShort\": \"tsp\",\n" +
            "\t\t\t\"unitLong\": \"teaspoons\",\n" +
            "\t\t\t\"originalString\": \"½ tsp. salt\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 11507,\n" +
            "\t\t\t\"aisle\": \"Produce\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/sweet-potato.jpg\",\n" +
            "\t\t\t\"name\": \"sweet potatoes\",\n" +
            "\t\t\t\"amount\": 2.0,\n" +
            "\t\t\t\"unit\": \"lb\",\n" +
            "\t\t\t\"unitShort\": \"lb\",\n" +
            "\t\t\t\"unitLong\": \"pounds\",\n" +
            "\t\t\t\"originalString\": \"2 lb. assorted sweet potatoes, peeled and cut into ¼-inch-thick sticks\",\n" +
            "\t\t\t\"metaInformation\": [\"assorted\", \"peeled\", \"cut into ¼-inch-thick sticks\"]\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 758989,\n" +
            "\t\t\"title\": \"Colorful Oven Fries\",\n" +
            "\t\t\"readyInMinutes\": 45,\n" +
            "\t\t\"image\": \"https://spoonacular.com/recipeImages/colorful-oven-fries-758989.jpg\",\n" +
            "\t\t\"imageType\": \"jpg\",\n" +
            "\t\t\"cuisines\": [\"american\"],\n" +
            "\t\t\"dishTypes\": [\"side dish\"],\n" +
            "\t\t\"instructions\": \"1. Preheat oven to 450°F. Line 2 baking sheets with parchment paper, or coat with cooking spray. Set 1 oven rack in top position and 1 rack in bottom position.2. Toss together sweet potatoes, oil, salt, pepper, and cinnamon, if using. Sprinkle with potato starch; toss mixture once more to coat well. Arrange in single layer on prepared baking sheets. Bake 10 minutes.3. Turn potatoes with spatula, and rotate baking sheets. Bake 12 minutes more, or until golden brown.\",\n" +
            "\t\t\"analyzedInstructions\": [{\n" +
            "\t\t\t\"name\": \"\",\n" +
            "\t\t\t\"steps\": [{\n" +
            "\t\t\t\t\"number\": 1,\n" +
            "\t\t\t\t\"step\": \"Preheat oven to 450°F. Line 2 baking sheets with parchment paper, or coat with cooking spray. Set 1 oven rack in top position and 1 rack in bottom position.2. Toss together sweet potatoes, oil, salt, pepper, and cinnamon, if using. Sprinkle with potato starch; toss mixture once more to coat well. Arrange in single layer on prepared baking sheets.\",\n" +
            "\t\t\t\t\"ingredients\": [{\n" +
            "\t\t\t\t\t\"id\": 11507,\n" +
            "\t\t\t\t\t\"name\": \"sweet potato\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/sweet-potato.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 11413,\n" +
            "\t\t\t\t\t\"name\": \"potato starch\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/white-powder.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 2010,\n" +
            "\t\t\t\t\t\"name\": \"cinnamon\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cinnamon.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 1002030,\n" +
            "\t\t\t\t\t\"name\": \"pepper\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/pepper.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 2047,\n" +
            "\t\t\t\t\t\"name\": \"salt\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 4582,\n" +
            "\t\t\t\t\t\"name\": \"cooking oil\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/vegetable-oil.jpg\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"equipment\": [{\n" +
            "\t\t\t\t\t\"id\": 404770,\n" +
            "\t\t\t\t\t\"name\": \"baking paper\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/baking-paper.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 404727,\n" +
            "\t\t\t\t\t\"name\": \"baking sheet\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/baking-sheet.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 404784,\n" +
            "\t\t\t\t\t\"name\": \"oven\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/oven.jpg\",\n" +
            "\t\t\t\t\t\"temperature\": {\n" +
            "\t\t\t\t\t\t\"number\": 450.0,\n" +
            "\t\t\t\t\t\t\"unit\": \"Fahrenheit\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"number\": 2,\n" +
            "\t\t\t\t\"step\": \"Bake 10 minutes.3. Turn potatoes with spatula, and rotate baking sheets.\",\n" +
            "\t\t\t\t\"ingredients\": [],\n" +
            "\t\t\t\t\"equipment\": [{\n" +
            "\t\t\t\t\t\"id\": 404727,\n" +
            "\t\t\t\t\t\"name\": \"baking sheet\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/baking-sheet.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 404642,\n" +
            "\t\t\t\t\t\"name\": \"spatula\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/spatula-or-turner.jpg\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"length\": {\n" +
            "\t\t\t\t\t\"number\": 10,\n" +
            "\t\t\t\t\t\"unit\": \"minutes\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"number\": 3,\n" +
            "\t\t\t\t\"step\": \"Bake 12 minutes more, or until golden brown.\",\n" +
            "\t\t\t\t\"ingredients\": [],\n" +
            "\t\t\t\t\"equipment\": [],\n" +
            "\t\t\t\t\"length\": {\n" +
            "\t\t\t\t\t\"number\": 12,\n" +
            "\t\t\t\t\t\"unit\": \"minutes\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}]\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"vegetarian\": false,\n" +
            "\t\t\"vegan\": false,\n" +
            "\t\t\"glutenFree\": true,\n" +
            "\t\t\"dairyFree\": false,\n" +
            "\t\t\"veryHealthy\": false,\n" +
            "\t\t\"cheap\": false,\n" +
            "\t\t\"veryPopular\": true,\n" +
            "\t\t\"sustainable\": false,\n" +
            "\t\t\"weightWatcherSmartPoints\": 9,\n" +
            "\t\t\"gaps\": \"no\",\n" +
            "\t\t\"lowFodmap\": false,\n" +
            "\t\t\"ketogenic\": false,\n" +
            "\t\t\"whole30\": false,\n" +
            "\t\t\"servings\": 8,\n" +
            "\t\t\"preparationMinutes\": 10,\n" +
            "\t\t\"cookingMinutes\": 5,\n" +
            "\t\t\"sourceUrl\": \"http://www.aspicyperspective.com/2014/05/tex-mex-popcorn.html\",\n" +
            "\t\t\"spoonacularSourceUrl\": \"https://spoonacular.com/tex-mex-popcorn-552135\",\n" +
            "\t\t\"aggregateLikes\": 525,\n" +
            "\t\t\"spoonacularScore\": 46.0,\n" +
            "\t\t\"healthScore\": 4.0,\n" +
            "\t\t\"creditText\": \"A Spicy Perspective\",\n" +
            "\t\t\"sourceName\": \"A Spicy Perspective\",\n" +
            "\t\t\"pricePerServing\": 119.8,\n" +
            "\t\t\"extendedIngredients\": [{\n" +
            "\t\t\t\"id\": 1022009,\n" +
            "\t\t\t\"aisle\": \"Ethnic Foods;Spices and Seasonings\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/chili-powder.jpg\",\n" +
            "\t\t\t\"name\": \"ancho chile powder\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"tsp\",\n" +
            "\t\t\t\"unitShort\": \"tsp\",\n" +
            "\t\t\t\"unitLong\": \"teaspoon\",\n" +
            "\t\t\t\"originalString\": \"1 tsp. ancho chile powder\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 9037,\n" +
            "\t\t\t\"aisle\": \"Produce\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/avocado.jpg\",\n" +
            "\t\t\t\"name\": \"avocado\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"\",\n" +
            "\t\t\t\"unitShort\": \"\",\n" +
            "\t\t\t\"unitLong\": \"\",\n" +
            "\t\t\t\"originalString\": \"1 firm (but ripe) avocado, diced\",\n" +
            "\t\t\t\"metaInformation\": [\"diced\", \"firm\", \"ripe\", \"(but )\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1001,\n" +
            "\t\t\t\"aisle\": \"Milk, Eggs, Other Dairy\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/butter-sliced.jpg\",\n" +
            "\t\t\t\"name\": \"butter\",\n" +
            "\t\t\t\"amount\": 2.0,\n" +
            "\t\t\t\"unit\": \"Tb\",\n" +
            "\t\t\t\"unitShort\": \"Tb\",\n" +
            "\t\t\t\"unitLong\": \"Tbs\",\n" +
            "\t\t\t\"originalString\": \"2 Tb. melted butter\",\n" +
            "\t\t\t\"metaInformation\": [\"melted\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 7019,\n" +
            "\t\t\t\"aisle\": \"Meat\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/chorizo.jpg\",\n" +
            "\t\t\t\"name\": \"chorizo\",\n" +
            "\t\t\t\"amount\": 0.75,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cups\",\n" +
            "\t\t\t\"originalString\": \"3/4 cup cooked chorizo, crumbled\",\n" +
            "\t\t\t\"metaInformation\": [\"crumbled\", \"cooked\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1022020,\n" +
            "\t\t\t\"aisle\": \"Spices and Seasonings\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/garlic-powder.jpg\",\n" +
            "\t\t\t\"name\": \"garlic powder\",\n" +
            "\t\t\t\"amount\": 0.5,\n" +
            "\t\t\t\"unit\": \"tsp\",\n" +
            "\t\t\t\"unitShort\": \"tsp\",\n" +
            "\t\t\t\"unitLong\": \"teaspoons\",\n" +
            "\t\t\t\"originalString\": \"1/2 tsp. garlic powder\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1002014,\n" +
            "\t\t\t\"aisle\": \"Spices and Seasonings\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/ground-cumin.jpg\",\n" +
            "\t\t\t\"name\": \"ground cumin\",\n" +
            "\t\t\t\"amount\": 0.5,\n" +
            "\t\t\t\"unit\": \"tsp\",\n" +
            "\t\t\t\"unitShort\": \"tsp\",\n" +
            "\t\t\t\"unitLong\": \"teaspoons\",\n" +
            "\t\t\t\"originalString\": \"1/2 tsp. ground cumin\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 25002,\n" +
            "\t\t\t\"aisle\": \"Savory Snacks\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/popcorn-kernels.jpg\",\n" +
            "\t\t\t\"name\": \"popcorn kernels\",\n" +
            "\t\t\t\"amount\": 0.5,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cups\",\n" +
            "\t\t\t\"originalString\": \"1/2 cup popcorn kernels\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1228,\n" +
            "\t\t\t\"aisle\": \"Cheese\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/camembert.jpg\",\n" +
            "\t\t\t\"name\": \"queso fresco\",\n" +
            "\t\t\t\"amount\": 0.75,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cups\",\n" +
            "\t\t\t\"originalString\": \"3/4 cup queso fresco, crumbled\",\n" +
            "\t\t\t\"metaInformation\": [\"crumbled\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 2047,\n" +
            "\t\t\t\"aisle\": \"Spices and Seasonings\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt.jpg\",\n" +
            "\t\t\t\"name\": \"salt\",\n" +
            "\t\t\t\"amount\": 0.5,\n" +
            "\t\t\t\"unit\": \"tsp\",\n" +
            "\t\t\t\"unitShort\": \"tsp\",\n" +
            "\t\t\t\"unitLong\": \"teaspoons\",\n" +
            "\t\t\t\"originalString\": \"1/2 tsp. salt\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 11955,\n" +
            "\t\t\t\"aisle\": \"Canned and Jarred;Produce\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/sundried-tomatoes.png\",\n" +
            "\t\t\t\"name\": \"sun dried tomatoes\",\n" +
            "\t\t\t\"amount\": 0.5,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cups\",\n" +
            "\t\t\t\"originalString\": \"1/2 cup sun dried tomatoes, diced (dried vacuum sealed, not in oil)\",\n" +
            "\t\t\t\"metaInformation\": [\"dried\", \"diced\", \"in oil\", \"( vacuum sealed, not )\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 4513,\n" +
            "\t\t\t\"aisle\": \"Oil, Vinegar, Salad Dressing\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/vegetable-oil.jpg\",\n" +
            "\t\t\t\"name\": \"vegetable oil\",\n" +
            "\t\t\t\"amount\": 2.0,\n" +
            "\t\t\t\"unit\": \"Tb\",\n" +
            "\t\t\t\"unitShort\": \"Tb\",\n" +
            "\t\t\t\"unitLong\": \"Tbs\",\n" +
            "\t\t\t\"originalString\": \"2 Tb. vegetable oil\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 552135,\n" +
            "\t\t\"title\": \"Tex Mex Popcorn\",\n" +
            "\t\t\"readyInMinutes\": 15,\n" +
            "\t\t\"image\": \"https://spoonacular.com/recipeImages/Tex-Mex-Popcorn-552135.jpg\",\n" +
            "\t\t\"imageType\": \"jpg\",\n" +
            "\t\t\"cuisines\": [\"american\"],\n" +
            "\t\t\"dishTypes\": [\"fingerfood\", \"antipasti\", \"starter\", \"snack\", \"appetizer\", \"antipasto\", \"hor d'oeuvre\"],\n" +
            "\t\t\"instructions\": \"Place the oil and popcorn kernels in a large heavy bottomed stock pot. Cover, leaving the lid slightly offset to vent, and turn on medium heat. Listen to the popcorn. Once the popping stops, remove from the heat immediately and pour into a large bowl, about 5 minutes. Meanwhile prepare all other toppings.Mix the salt and spices together in a small bowl. Shake the larger bowl of popcorn while pouring the melted butter and spice blend over the top. Shake until thoroughly coated, then toss in the sun dried tomatoes.Scoop the popcorn into smaller serving bowls and top each bowl with crumbled chorizo, queso fresco, and diced avocado. Serve immediately!\",\n" +
            "\t\t\"analyzedInstructions\": [{\n" +
            "\t\t\t\"name\": \"\",\n" +
            "\t\t\t\"steps\": [{\n" +
            "\t\t\t\t\"number\": 1,\n" +
            "\t\t\t\t\"step\": \"Place the oil and popcorn kernels in a large heavy bottomed stock pot. Cover, leaving the lid slightly offset to vent, and turn on medium heat. Listen to the popcorn. Once the popping stops, remove from the heat immediately and pour into a large bowl, about 5 minutes. Meanwhile prepare all other toppings.\",\n" +
            "\t\t\t\t\"ingredients\": [{\n" +
            "\t\t\t\t\t\"id\": 25002,\n" +
            "\t\t\t\t\t\"name\": \"popcorn kernels\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/popcorn-kernels.jpg\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"equipment\": [{\n" +
            "\t\t\t\t\t\"id\": 404752,\n" +
            "\t\t\t\t\t\"name\": \"pot\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/stock-pot.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 404783,\n" +
            "\t\t\t\t\t\"name\": \"bowl\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/bowl.jpg\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"length\": {\n" +
            "\t\t\t\t\t\"number\": 5,\n" +
            "\t\t\t\t\t\"unit\": \"minutes\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"number\": 2,\n" +
            "\t\t\t\t\"step\": \"Mix the salt and spices together in a small bowl. Shake the larger bowl of popcorn while pouring the melted butter and spice blendover the top. Shake until thoroughly coated, then toss in the sun dried tomatoes.Scoop the popcorn into smaller serving bowls and top each bowl with crumbled chorizo, queso fresco, and diced avocado.\",\n" +
            "\t\t\t\t\"ingredients\": [{\n" +
            "\t\t\t\t\t\"id\": 11955,\n" +
            "\t\t\t\t\t\"name\": \"sun dried tomatoes\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/sundried-tomatoes.png\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 1228,\n" +
            "\t\t\t\t\t\"name\": \"queso fresco\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/camembert.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 9037,\n" +
            "\t\t\t\t\t\"name\": \"avocado\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/avocado.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 7019,\n" +
            "\t\t\t\t\t\"name\": \"chorizo\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/chorizo.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 1001,\n" +
            "\t\t\t\t\t\"name\": \"butter\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/butter-sliced.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 2047,\n" +
            "\t\t\t\t\t\"name\": \"salt\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt.jpg\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"equipment\": [{\n" +
            "\t\t\t\t\t\"id\": 404783,\n" +
            "\t\t\t\t\t\"name\": \"bowl\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/bowl.jpg\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"number\": 3,\n" +
            "\t\t\t\t\"step\": \"Serve immediately!\",\n" +
            "\t\t\t\t\"ingredients\": [],\n" +
            "\t\t\t\t\"equipment\": []\n" +
            "\t\t\t}]\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"vegetarian\": true,\n" +
            "\t\t\"vegan\": true,\n" +
            "\t\t\"glutenFree\": true,\n" +
            "\t\t\"dairyFree\": true,\n" +
            "\t\t\"veryHealthy\": false,\n" +
            "\t\t\"cheap\": false,\n" +
            "\t\t\"veryPopular\": false,\n" +
            "\t\t\"sustainable\": false,\n" +
            "\t\t\"weightWatcherSmartPoints\": 3,\n" +
            "\t\t\"gaps\": \"no\",\n" +
            "\t\t\"lowFodmap\": false,\n" +
            "\t\t\"ketogenic\": false,\n" +
            "\t\t\"whole30\": false,\n" +
            "\t\t\"servings\": 4,\n" +
            "\t\t\"preparationMinutes\": 10,\n" +
            "\t\t\"cookingMinutes\": 180,\n" +
            "\t\t\"sourceUrl\": \"http://simple-nourished-living.com/2013/10/slow-cooker-acorn-squash-mango-chutney-coconut/\",\n" +
            "\t\t\"spoonacularSourceUrl\": \"https://spoonacular.com/slow-cooker-acorn-squash-with-mango-chutney-and-coconut-525192\",\n" +
            "\t\t\"aggregateLikes\": 168,\n" +
            "\t\t\"spoonacularScore\": 54.0,\n" +
            "\t\t\"healthScore\": 5.0,\n" +
            "\t\t\"creditText\": \"Simple Nourished Living\",\n" +
            "\t\t\"sourceName\": \"Simple Nourished Living\",\n" +
            "\t\t\"pricePerServing\": 43.83,\n" +
            "\t\t\"extendedIngredients\": [{\n" +
            "\t\t\t\"id\": 11482,\n" +
            "\t\t\t\"aisle\": \"Produce\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/acorn-squash.jpg\",\n" +
            "\t\t\t\"name\": \"acorn squash\",\n" +
            "\t\t\t\"amount\": 1.0,\n" +
            "\t\t\t\"unit\": \"\",\n" +
            "\t\t\t\"unitShort\": \"\",\n" +
            "\t\t\t\"unitLong\": \"\",\n" +
            "\t\t\t\"originalString\": \"1 large acorn squash\",\n" +
            "\t\t\t\"metaInformation\": [\"large\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 12104,\n" +
            "\t\t\t\"aisle\": \"Produce\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/coconut.jpg\",\n" +
            "\t\t\t\"name\": \"coconut\",\n" +
            "\t\t\t\"amount\": 0.25,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cups\",\n" +
            "\t\t\t\"originalString\": \"¼ cup flaked coconut\",\n" +
            "\t\t\t\"metaInformation\": [\"flaked\"]\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 10119297,\n" +
            "\t\t\t\"aisle\": \"Ethnic Foods\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/mango-chutney.png\",\n" +
            "\t\t\t\"name\": \"mango chutney\",\n" +
            "\t\t\t\"amount\": 0.25,\n" +
            "\t\t\t\"unit\": \"cup\",\n" +
            "\t\t\t\"unitShort\": \"cup\",\n" +
            "\t\t\t\"unitLong\": \"cups\",\n" +
            "\t\t\t\"originalString\": \"¼ cup mango chutney\",\n" +
            "\t\t\t\"metaInformation\": []\n" +
            "\t\t}, {\n" +
            "\t\t\t\"id\": 1102047,\n" +
            "\t\t\t\"aisle\": \"Spices and Seasonings\",\n" +
            "\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt-and-pepper.jpg\",\n" +
            "\t\t\t\"name\": \"salt and pepper\",\n" +
            "\t\t\t\"amount\": 4.0,\n" +
            "\t\t\t\"unit\": \"servings\",\n" +
            "\t\t\t\"unitShort\": \"servings\",\n" +
            "\t\t\t\"unitLong\": \"servings\",\n" +
            "\t\t\t\"originalString\": \"Salt and pepper to taste\",\n" +
            "\t\t\t\"metaInformation\": [\"to taste\"]\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 525192,\n" +
            "\t\t\"title\": \"Slow Cooker Acorn Squash with Mango Chutney and Coconut\",\n" +
            "\t\t\"readyInMinutes\": 190,\n" +
            "\t\t\"image\": \"https://spoonacular.com/recipeImages/Slow-Cooker-Acorn-Squash-with-Mango-Chutney-and-Coconut-525192.jpg\",\n" +
            "\t\t\"imageType\": \"jpg\",\n" +
            "\t\t\"cuisines\": [],\n" +
            "\t\t\"dishTypes\": [\"side dish\"],\n" +
            "\t\t\"instructions\": \"Cut the squash in quarters.Remove and discard the seeds and stringy pulp.Spray the slow cooker with non stick spray (I used my 6-Quart)Snuggle the squash quarters into the slow cooker.Stir together the chutney, coconut and divide among the centers of the squash quarters.Sprinkle with salt and pepper.Cover and Cook on LOW for 2 to 3 hours, until tender when pierced with a fork.\",\n" +
            "\t\t\"analyzedInstructions\": [{\n" +
            "\t\t\t\"name\": \"\",\n" +
            "\t\t\t\"steps\": [{\n" +
            "\t\t\t\t\"number\": 1,\n" +
            "\t\t\t\t\"step\": \"Cut the squash in quarters.\",\n" +
            "\t\t\t\t\"ingredients\": [],\n" +
            "\t\t\t\t\"equipment\": []\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"number\": 2,\n" +
            "\t\t\t\t\"step\": \"Remove and discard the seeds and stringy pulp.Spray the slow cooker with non stick spray (I used my 6-Quart)Snuggle the squash quarters into the slow cooker.Stir together the chutney, coconut and divide among the centers of the squash quarters.Sprinkle with salt and pepper.Cover and Cook on LOW for 2 to 3 hours, until tender when pierced with a fork.\",\n" +
            "\t\t\t\t\"ingredients\": [{\n" +
            "\t\t\t\t\t\"id\": 1102047,\n" +
            "\t\t\t\t\t\"name\": \"salt and pepper\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salt-and-pepper.jpg\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"id\": 12104,\n" +
            "\t\t\t\t\t\"name\": \"coconut\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/ingredients_100x100/coconut.jpg\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"equipment\": [{\n" +
            "\t\t\t\t\t\"id\": 404718,\n" +
            "\t\t\t\t\t\"name\": \"slow cooker\",\n" +
            "\t\t\t\t\t\"image\": \"https://spoonacular.com/cdn/equipment_100x100/slow-cooker.jpg\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t}]\n" +
            "\t\t}]\n" +
            "\t}]\n" +
            "}";
}
