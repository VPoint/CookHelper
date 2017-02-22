package com.seg2105a.esther.cookhelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by eraji on 2/16/2017.
 */

public abstract class Attribute {
    protected String name;
    protected List<Recipe> recipes;
    protected RecipeSystem recipeSystem;

    public static enum AttributeType {
        Unknown, Ingredient, Category, RecipeType
    }

    protected Attribute(String aName, RecipeSystem aRecipeSystem){
        name = aName;
        boolean didAddRecipeSystem = setRecipeSystem(aRecipeSystem);
        if (!didAddRecipeSystem)
        {
            throw new RuntimeException("Unable to create ingredient due to recipeSystem");
        }
        recipes = new ArrayList<Recipe>();
    }

    public boolean setName(String aName){
        boolean wasSet = false;
        name = aName.toLowerCase();
        wasSet = true;
        return wasSet;
    }
    public String getName()
    {
        return name;
    }

    public Recipe getRecipe(int index)
    {
        Recipe aRecipe = recipes.get(index);
        return aRecipe;
    }

    public List<Recipe> getRecipe()
    {
        List<Recipe> newRecipe = Collections.unmodifiableList(recipes);
        return newRecipe;
    }

    public int numberOfRecipe()
    {
        int number = recipes.size();
        return number;
    }

    public boolean hasRecipe()
    {
        boolean has = recipes.size() > 0;
        return has;
    }

    public int indexOfRecipe(Recipe aRecipe)
    {
        int index = recipes.indexOf(aRecipe);
        return index;
    }

    public RecipeSystem getRecipeSystem()
    {
        return recipeSystem;
    }

    public static int minimumNumberOfRecipe()
    {
        return 0;
    }

    // TODO: Maybe? Add an index of Attribute
    abstract public boolean addRecipe(Recipe aRecipe);

    // TODO: Maybe? Add an index of Attribute
    abstract public boolean removeRecipe(Recipe aRecipe);

    public boolean addRecipeAt(Recipe aRecipe, int index)
    {
        boolean wasAdded = false;
        if(addRecipe(aRecipe))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfRecipe()) { index = numberOfRecipe() - 1; }
            recipes.remove(aRecipe);
            recipes.add(index, aRecipe);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveRecipeAt(Recipe aRecipe, int index)
    {
        boolean wasAdded;
        if(recipes.contains(aRecipe))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfRecipe()) { index = numberOfRecipe() - 1; }
            recipes.remove(aRecipe);
            recipes.add(index, aRecipe);
            wasAdded = true;
        }
        else
        {
            wasAdded = addRecipeAt(aRecipe, index);
        }
        return wasAdded;
    }

    //TODO: Standardize this....
    abstract public boolean setRecipeSystem(RecipeSystem aRecipeSystem);

    //TODO: Standardize this
    abstract public void delete();

    public AttributeType getAttributeType(){
        return AttributeType.Unknown;
    }

    public String toString()
    {
        return getName();
    }
}