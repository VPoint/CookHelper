/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-abedcd4 modeling language!*/

package com.seg2105a.esther.cookhelper;

import java.util.*;

public class RecipeType extends Attribute {

    public RecipeType(String aName, RecipeSystem aRecipeSystem) {
        super(aName, aRecipeSystem);
    }

  //------------------------
  // INTERFACE
  //------------------------
  public boolean addRecipe(Recipe aRecipe)
  {
    boolean wasAdded = false;
    if (recipes.contains(aRecipe)) { return false; }
    recipes.add(aRecipe);
    if (aRecipe.indexOfRecipeType(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRecipe.addRecipeType(this);
      if (!wasAdded)
      {
        recipes.remove(aRecipe);
      }
    }
    return wasAdded;
  }

  public boolean removeRecipe(Recipe aRecipe)
  {
    boolean wasRemoved = false;
    if (!recipes.contains(aRecipe))
    {
      return wasRemoved;
    }

    int oldIndex = recipes.indexOf(aRecipe);
    recipes.remove(oldIndex);
    if (aRecipe.indexOfRecipeType(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRecipe.removeRecipeType(this);
      if (!wasRemoved)
      {
        recipes.add(oldIndex,aRecipe);
      }
    }
    return wasRemoved;
  }

  public boolean setRecipeSystem(RecipeSystem aRecipeSystem)
  {
    if (aRecipeSystem == null)
    {
      return false;
    }

    RecipeSystem existingRecipeSystem = recipeSystem;
    recipeSystem = aRecipeSystem;
    if (existingRecipeSystem != null && !existingRecipeSystem.equals(aRecipeSystem))
    {
      existingRecipeSystem.removeRecipeType(this);
    }
    recipeSystem.addRecipeType(this);
    return true;
  }

  public void delete()
  {
    ArrayList<Recipe> copyOfRecipe = new ArrayList<Recipe>(recipes);
    recipes.clear();
    for(Recipe aRecipe : copyOfRecipe)
    {
      aRecipe.removeRecipeType(this);
    }
    RecipeSystem placeholderRecipeSystem = recipeSystem;
    this.recipeSystem = null;
    placeholderRecipeSystem.removeRecipeType(this);
  }

    public AttributeType getAttributeType(){
        return AttributeType.RecipeType;
    }

}