/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-abedcd4 modeling language!*/

package com.seg2105a.esther.cookhelper;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/


import java.util.*;

// line 57 "model.ump"
// line 94 "model.ump"
public class RecipeType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RecipeType Attributes
  private String name;

  //RecipeType Associations
  private List<Recipe> hasA;
  private RecipeSystem recipeSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RecipeType(String aName, RecipeSystem aRecipeSystem)
  {
    name = aName;
    hasA = new ArrayList<Recipe>();
    boolean didAddRecipeSystem = setRecipeSystem(aRecipeSystem);
    if (!didAddRecipeSystem)
    {
      throw new RuntimeException("Unable to create recipeType due to recipeSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Recipe getHasA(int index)
  {
    Recipe aHasA = hasA.get(index);
    return aHasA;
  }

  public List<Recipe> getHasA()
  {
    List<Recipe> newHasA = Collections.unmodifiableList(hasA);
    return newHasA;
  }

  public int numberOfHasA()
  {
    int number = hasA.size();
    return number;
  }

  public boolean hasHasA()
  {
    boolean has = hasA.size() > 0;
    return has;
  }

  public int indexOfHasA(Recipe aHasA)
  {
    int index = hasA.indexOf(aHasA);
    return index;
  }

  public RecipeSystem getRecipeSystem()
  {
    return recipeSystem;
  }

  public static int minimumNumberOfHasA()
  {
    return 0;
  }

  public boolean addHasA(Recipe aHasA)
  {
    boolean wasAdded = false;
    if (hasA.contains(aHasA)) { return false; }
    hasA.add(aHasA);
    if (aHasA.indexOfRecipeType(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aHasA.addRecipeType(this);
      if (!wasAdded)
      {
        hasA.remove(aHasA);
      }
    }
    return wasAdded;
  }

  public boolean removeHasA(Recipe aHasA)
  {
    boolean wasRemoved = false;
    if (!hasA.contains(aHasA))
    {
      return wasRemoved;
    }

    int oldIndex = hasA.indexOf(aHasA);
    hasA.remove(oldIndex);
    if (aHasA.indexOfRecipeType(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aHasA.removeRecipeType(this);
      if (!wasRemoved)
      {
        hasA.add(oldIndex,aHasA);
      }
    }
    return wasRemoved;
  }

  public boolean addHasAAt(Recipe aHasA, int index)
  {
    boolean wasAdded = false;
    if(addHasA(aHasA))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHasA()) { index = numberOfHasA() - 1; }
      hasA.remove(aHasA);
      hasA.add(index, aHasA);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHasAAt(Recipe aHasA, int index)
  {
    boolean wasAdded = false;
    if(hasA.contains(aHasA))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHasA()) { index = numberOfHasA() - 1; }
      hasA.remove(aHasA);
      hasA.add(index, aHasA);
      wasAdded = true;
    }
    else
    {
      wasAdded = addHasAAt(aHasA, index);
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
      existingRecipeSystem.removeRecipeType(this);
    }
    recipeSystem.addRecipeType(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Recipe> copyOfHasA = new ArrayList<Recipe>(hasA);
    hasA.clear();
    for(Recipe aHasA : copyOfHasA)
    {
      aHasA.removeRecipeType(this);
    }
    RecipeSystem placeholderRecipeSystem = recipeSystem;
    this.recipeSystem = null;
    placeholderRecipeSystem.removeRecipeType(this);
  }

  // line 62 "model.ump"
  public Recipe[] getAllRecipes(){
    return new Recipe[2];
  }


  public String toString()
  {
    return getName();
  }
}