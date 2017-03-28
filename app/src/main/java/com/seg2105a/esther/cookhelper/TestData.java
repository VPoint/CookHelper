package com.seg2105a.esther.cookhelper;

import java.util.Random;

/**
 * Created by eraji on 3/1/2017.
 */

public class TestData {

    private static RecipeSystem system;

    public static void generate() {
        system = system.getInstance();

        String[] categoryNames = {"mexican", "canadian", "japanese", "italian"};
        String[] recipeTypeNames = {"main dish", "dessert", "brunch", "appetizer"};
        String[] ingredientNames = {"kale", "candy", "cheese", "tomatoes"};
        String[] recipeNames = {"Chili Salsa", "Pasta", "Cake"};

        Random random = new Random();

        for (String name : categoryNames) {
            system.addCategory(name);
        }

        for (String name : recipeTypeNames) {
            system.addRecipeType(name);
        }

        for (String name : ingredientNames) {
            system.addIngredient(name);
        }

/*        int i= 0;
        for(String name: recipeNames){
            system.addRecipe(name, "There needs to be something here..", 4.5, "", 6, 900, system.getIngredient(random.nextInt(4)));
            system.getRecipe(i).addRecipeStep(0, "Cut Stuff Up", 6.0, false);
            system.getRecipe(i).addCategory(system.getCategory(random.nextInt(4)));
            system.getRecipe(i).addRecipeType(system.getRecipeType(random.nextInt(4)));
            system.getRecipe(i).setRating(random.nextInt(5));
            i++;
        }*/

        system.addRecipe("Chili Salsa", "There needs to be something here..", 4.5, "", 6, 900, system.getIngredient(0));
        system.getRecipe(0).addRecipeStep(0, "Cut Stuff Up", 6.0, false);
        system.getRecipe(0).addCategory(system.getCategory(1));
        system.getRecipe(0).addRecipeType(system.getRecipeType(2));
        system.getRecipe(0).setRating(3);

        system.addRecipe("Pasta", "There needs to be something here..", 7.2, "", 234, 99838, system.getIngredient(3));
        system.getRecipe(1).addRecipeStep(0, "Cut Stuff Up", 6.0, false);
        system.getRecipe(1).addCategory(system.getCategory(0));
        system.getRecipe(1).addRecipeType(system.getRecipeType(3));
        system.getRecipe(1).setRating(43 / 5);

        system.addRecipe("Cake", "There needs to be something here..", 33.4, "", 1234, 77, system.getIngredient(1));
        system.getRecipe(2).addRecipeStep(0, "Cut Stuff Up", 6.0, false);
        system.getRecipe(2).addCategory(system.getCategory(0));
        system.getRecipe(2).addRecipeType(system.getRecipeType(2));
        system.getRecipe(2).setRating(1);
        //
        //system.addRecipe("Pasta","khjhkhjkhjkh jkhjkhjkhkjhhkj hjkhkjkh", 40.2, "");
        //system.addRecipe("Pizza","khjhkhldskjf sdkjfljsd fsldkmksdklffsdlf dkhkjkh", 90909.2, "");
        //system.addRecipe("Pastry","khjhkhjkhjkh aaasaasnnsadad adadka da a da hjkhkjkh", 8.2, "");
    }

    public static RecipeSystem getSystem() {
        return system;
    }
}
