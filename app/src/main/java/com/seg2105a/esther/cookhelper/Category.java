/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-abedcd4 modeling language!*/

package com.seg2105a.esther.cookhelper;
import java.util.*;

public class Category
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Category Attributes
  private String name;

  //Category Associations
  private List<Recipe> recipe;
  private RecipeSystem recipeSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Category(String aName, RecipeSystem aRecipeSystem)
  {
    name = aName.toLowerCase();
    recipe = new ArrayList<Recipe>();
    boolean didAddRecipeSystem = setRecipeSystem(aRecipeSystem);
    if (!didAddRecipeSystem)
    {
      throw new RuntimeException("Unable to create category due to recipeSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
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
    Recipe aRecipe = recipe.get(index);
    return aRecipe;
  }

  public List<Recipe> getRecipe()
  {
    List<Recipe> newRecipe = Collections.unmodifiableList(recipe);
    return newRecipe;
  }

  public int numberOfRecipe()
  {
    int number = recipe.size();
    return number;
  }

  public boolean hasRecipe()
  {
    boolean has = recipe.size() > 0;
    return has;
  }

  public int indexOfRecipe(Recipe aRecipe)
  {
    int index = recipe.indexOf(aRecipe);
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

  public boolean addRecipe(Recipe aRecipe)
  {
    boolean wasAdded = false;
    if (recipe.contains(aRecipe)) { return false; }
    recipe.add(aRecipe);
    if (aRecipe.indexOfCategory(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRecipe.addCategory(this);
      if (!wasAdded)
      {
        recipe.remove(aRecipe);
      }
    }
    return wasAdded;
  }

  public boolean removeRecipe(Recipe aRecipe)
  {
    boolean wasRemoved = false;
    if (!recipe.contains(aRecipe))
    {
      return wasRemoved;
    }

    int oldIndex = recipe.indexOf(aRecipe);
    recipe.remove(oldIndex);
    if (aRecipe.indexOfCategory(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRecipe.removeCategory(this);
      if (!wasRemoved)
      {
        recipe.add(oldIndex,aRecipe);
      }
    }
    return wasRemoved;
  }

  public boolean addRecipeAt(Recipe aRecipe, int index)
  {
    boolean wasAdded = false;
    if(addRecipe(aRecipe))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipe()) { index = numberOfRecipe() - 1; }
      recipe.remove(aRecipe);
      recipe.add(index, aRecipe);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRecipeAt(Recipe aRecipe, int index)
  {
    boolean wasAdded = false;
    if(recipe.contains(aRecipe))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipe()) { index = numberOfRecipe() - 1; }
      recipe.remove(aRecipe);
      recipe.add(index, aRecipe);
      wasAdded = true;
    }
    else
    {
      wasAdded = addRecipeAt(aRecipe, index);
    }
    return wasAdded;
  }

  public boolean setRecipeSystem(RecipeSystem aRecipeSystem)
  {
    boolean wasSet = false;
    if (aRecipeSystem == null)
    {
      return wasSet;
    }

    RecipeSystem existingRecipeSystem = recipeSystem;
    recipeSystem = aRecipeSystem;
    if (existingRecipeSystem != null && !existingRecipeSystem.equals(aRecipeSystem))
    {
      existingRecipeSystem.removeCategory(this);
    }
    recipeSystem.addCategory(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Recipe> copyOfRecipe = new ArrayList<Recipe>(recipe);
    recipe.clear();
    for(Recipe aRecipe : copyOfRecipe)
    {
      aRecipe.removeCategory(this);
    }
    RecipeSystem placeholderRecipeSystem = recipeSystem;
    this.recipeSystem = null;
    placeholderRecipeSystem.removeCategory(this);
  }


  public String toString()
  {
    return getName();
  }
}