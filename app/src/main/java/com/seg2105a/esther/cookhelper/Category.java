/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-abedcd4 modeling language!*/

package com.seg2105a.esther.cookhelper;

import java.util.*;

public class Category extends Attribute {

    //------------------------
    // MEMBER VARIABLES
    //------------------------


    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Category(String aName, RecipeSystem aRecipeSystem) {
        super(aName, aRecipeSystem);
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean addRecipe(Recipe aRecipe) {
        boolean wasAdded = false;
        if (recipes.contains(aRecipe)) {
            return false;
        }
        recipes.add(aRecipe);
        if (aRecipe.indexOfCategory(this) != -1) {
            wasAdded = true;
        } else {
            wasAdded = aRecipe.addCategory(this);
            if (!wasAdded) {
                recipes.remove(aRecipe);
            }
        }
        return wasAdded;
    }

    public boolean removeRecipe(Recipe aRecipe) {
        boolean wasRemoved = false;
        if (!recipes.contains(aRecipe)) {
            return wasRemoved;
        }

        int oldIndex = recipes.indexOf(aRecipe);
        recipes.remove(oldIndex);
        if (aRecipe.indexOfCategory(this) == -1) {
            wasRemoved = true;
        } else {
            wasRemoved = aRecipe.removeCategory(this);
            if (!wasRemoved) {
                recipes.add(oldIndex, aRecipe);
            }
        }
        return wasRemoved;
    }

    public boolean setRecipeSystem(RecipeSystem aRecipeSystem) {
        boolean wasSet = false;
        if (aRecipeSystem == null) {
            return wasSet;
        }

        RecipeSystem existingRecipeSystem = recipeSystem;
        recipeSystem = aRecipeSystem;
        if (existingRecipeSystem != null && !existingRecipeSystem.equals(aRecipeSystem)) {
            existingRecipeSystem.removeCategory(this);
        }
        recipeSystem.addCategory(this);
        wasSet = true;
        return wasSet;
    }

    public void delete() {
        ArrayList<Recipe> copyOfRecipe = new ArrayList<Recipe>(recipes);
        recipes.clear();
        for (Recipe aRecipe : copyOfRecipe) {
            aRecipe.removeCategory(this);
        }
        RecipeSystem placeholderRecipeSystem = recipeSystem;
        this.recipeSystem = null;
        placeholderRecipeSystem.removeCategory(this);
    }

    public AttributeType getAttributeType() {
        return AttributeType.Category;
    }
}