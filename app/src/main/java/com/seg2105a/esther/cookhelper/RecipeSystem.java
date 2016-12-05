/*Esther Raji */
/*This code was generated using the UMPLE 1.24.0-abedcd4 modeling language!*/
package com.seg2105a.esther.cookhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.FileInputStream;
import java.util.*;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 74 "model.ump"
public class RecipeSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RecipeSystem Attributes
  private Object database;

  //RecipeSystem Associations
  private List<Recipe> hasA;
  private List<Category> categories;
  private List<RecipeType> recipeTypes;
  private List<Ingredient> ingredients;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  private static RecipeSystem instance = null;

  protected RecipeSystem(Object aDatabase)
  {
    database = aDatabase;
    hasA = new ArrayList<Recipe>();
    categories = new ArrayList<Category>();
    recipeTypes = new ArrayList<RecipeType>();
    ingredients = new ArrayList<Ingredient>();
  }

  public static RecipeSystem getInstance() {
    if(instance == null){
      instance = new RecipeSystem(null);
    }
    return instance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDatabase(Object aDatabase)
  {
    boolean wasSet = false;
    database = aDatabase;
    wasSet = true;
    return wasSet;
  }

  public Object getDatabase()
  {
    return database;
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

  public Category getCategory(int index)
  {
    Category aCategory = categories.get(index);
    return aCategory;
  }

  public List<Category> getCategories()
  {
    List<Category> newCategories = Collections.unmodifiableList(categories);
    return newCategories;
  }

  public int numberOfCategories()
  {
    int number = categories.size();
    return number;
  }

  public boolean hasCategories()
  {
    boolean has = categories.size() > 0;
    return has;
  }

  public int indexOfCategory(Category aCategory)
  {
    int index = categories.indexOf(aCategory);
    return index;
  }

  public RecipeType getRecipeType(int index)
  {
    RecipeType aRecipeType = recipeTypes.get(index);
    return aRecipeType;
  }

  public List<RecipeType> getRecipeTypes()
  {
    List<RecipeType> newRecipeTypes = Collections.unmodifiableList(recipeTypes);
    return newRecipeTypes;
  }

  public int numberOfRecipeTypes()
  {
    int number = recipeTypes.size();
    return number;
  }

  public boolean hasRecipeTypes()
  {
    boolean has = recipeTypes.size() > 0;
    return has;
  }

  public int indexOfRecipeType(RecipeType aRecipeType)
  {
    int index = recipeTypes.indexOf(aRecipeType);
    return index;
  }

  public Ingredient getIngredient(int index)
  {
    Ingredient aIngredient = ingredients.get(index);
    return aIngredient;
  }

  public List<Ingredient> getIngredients()
  {
    List<Ingredient> newIngredients = Collections.unmodifiableList(ingredients);
    return newIngredients;
  }

  public int numberOfIngredients()
  {
    int number = ingredients.size();
    return number;
  }

  public boolean hasIngredients()
  {
    boolean has = ingredients.size() > 0;
    return has;
  }

  public int indexOfIngredient(Ingredient aIngredient)
  {
    int index = ingredients.indexOf(aIngredient);
    return index;
  }

  public static int minimumNumberOfHasA()
  {
    return 0;
  }

  public Recipe addHasA(String aTitle, String aDescription, double aCookingTime, String aImage, int aServing, int aId, int aCalories, Ingredient... allUsedIn)
  {
    return new Recipe(aTitle, aDescription, aCookingTime, aImage, aServing, aId, aCalories, this, allUsedIn);
  }

  public boolean addHasA(Recipe aHasA)
  {
    boolean wasAdded = false;
    if (hasA.contains(aHasA)) { return false; }
    RecipeSystem existingRecipeSystem = aHasA.getRecipeSystem();
    boolean isNewRecipeSystem = existingRecipeSystem != null && !this.equals(existingRecipeSystem);
    if (isNewRecipeSystem)
    {
      aHasA.setRecipeSystem(this);
    }
    else
    {
      hasA.add(aHasA);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHasA(Recipe aHasA)
  {
    boolean wasRemoved = false;
    //Unable to remove aHasA, as it must always have a recipeSystem
    if (!this.equals(aHasA.getRecipeSystem()))
    {
      hasA.remove(aHasA);
      wasRemoved = true;
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

  public static int minimumNumberOfCategories()
  {
    return 0;
  }

  public Category addCategory(String aName)
  {
    return new Category(aName, this);
  }

  public boolean addCategory(Category aCategory)
  {
    boolean wasAdded = false;
    if (categories.contains(aCategory)) { return false; }
    RecipeSystem existingRecipeSystem = aCategory.getRecipeSystem();
    boolean isNewRecipeSystem = existingRecipeSystem != null && !this.equals(existingRecipeSystem);
    if (isNewRecipeSystem)
    {
      aCategory.setRecipeSystem(this);
    }
    else
    {
      categories.add(aCategory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCategory(Category aCategory)
  {
    boolean wasRemoved = false;
    //Unable to remove aCategory, as it must always have a recipeSystem
    if (!this.equals(aCategory.getRecipeSystem()))
    {
      categories.remove(aCategory);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCategoryAt(Category aCategory, int index)
  {
    boolean wasAdded = false;
    if(addCategory(aCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
      categories.remove(aCategory);
      categories.add(index, aCategory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCategoryAt(Category aCategory, int index)
  {
    boolean wasAdded = false;
    if(categories.contains(aCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
      categories.remove(aCategory);
      categories.add(index, aCategory);
      wasAdded = true;
    }
    else
    {
      wasAdded = addCategoryAt(aCategory, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfRecipeTypes()
  {
    return 0;
  }

  public RecipeType addRecipeType(String aName)
  {
    return new RecipeType(aName, this);
  }

  public boolean addRecipeType(RecipeType aRecipeType)
  {
    boolean wasAdded = false;
    if (recipeTypes.contains(aRecipeType)) { return false; }
    RecipeSystem existingRecipeSystem = aRecipeType.getRecipeSystem();
    boolean isNewRecipeSystem = existingRecipeSystem != null && !this.equals(existingRecipeSystem);
    if (isNewRecipeSystem)
    {
      aRecipeType.setRecipeSystem(this);
    }
    else
    {
      recipeTypes.add(aRecipeType);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRecipeType(RecipeType aRecipeType)
  {
    boolean wasRemoved = false;
    //Unable to remove aRecipeType, as it must always have a recipeSystem
    if (!this.equals(aRecipeType.getRecipeSystem()))
    {
      recipeTypes.remove(aRecipeType);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRecipeTypeAt(RecipeType aRecipeType, int index)
  {
    boolean wasAdded = false;
    if(addRecipeType(aRecipeType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipeTypes()) { index = numberOfRecipeTypes() - 1; }
      recipeTypes.remove(aRecipeType);
      recipeTypes.add(index, aRecipeType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRecipeTypeAt(RecipeType aRecipeType, int index)
  {
    boolean wasAdded = false;
    if(recipeTypes.contains(aRecipeType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipeTypes()) { index = numberOfRecipeTypes() - 1; }
      recipeTypes.remove(aRecipeType);
      recipeTypes.add(index, aRecipeType);
      wasAdded = true;
    }
    else
    {
      wasAdded = addRecipeTypeAt(aRecipeType, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfIngredients()
  {
    return 0;
  }

  public Ingredient addIngredient(String aName)
  {
    return new Ingredient(aName, this);
  }

  public boolean addIngredient(Ingredient aIngredient)
  {
    boolean wasAdded = false;
    if (ingredients.contains(aIngredient)) { return false; }
    RecipeSystem existingRecipeSystem = aIngredient.getRecipeSystem();
    boolean isNewRecipeSystem = existingRecipeSystem != null && !this.equals(existingRecipeSystem);
    if (isNewRecipeSystem)
    {
      aIngredient.setRecipeSystem(this);
    }
    else
    {
      ingredients.add(aIngredient);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeIngredient(Ingredient aIngredient)
  {
    boolean wasRemoved = false;
    //Unable to remove aIngredient, as it must always have a recipeSystem
    if (!this.equals(aIngredient.getRecipeSystem()))
    {
      ingredients.remove(aIngredient);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addIngredientAt(Ingredient aIngredient, int index)
  {
    boolean wasAdded = false;
    if(addIngredient(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveIngredientAt(Ingredient aIngredient, int index)
  {
    boolean wasAdded = false;
    if(ingredients.contains(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    }
    else
    {
      wasAdded = addIngredientAt(aIngredient, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=hasA.size(); i > 0; i--)
    {
      Recipe aHasA = hasA.get(i - 1);
      aHasA.delete();
    }
    for(int i=categories.size(); i > 0; i--)
    {
      Category aCategory = categories.get(i - 1);
      aCategory.delete();
    }
    for(int i=recipeTypes.size(); i > 0; i--)
    {
      RecipeType aRecipeType = recipeTypes.get(i - 1);
      aRecipeType.delete();
    }
    for(int i=ingredients.size(); i > 0; i--)
    {
      Ingredient aIngredient = ingredients.get(i - 1);
      aIngredient.delete();
    }
  }

  // line 9 "model.ump"
  private String parseQuery(){
    return "";
  }

  // line 13 "model.ump"
  private Recipe fetchRecipe(){
    return new Recipe("", "",0 , "", 0,0,0,this, null);
  }

  // line 17 "model.ump"
  private void sendToDatabase(){

  }

  // line 21 "model.ump"
  private Recipe[] formatFromDatabase(){
    return new Recipe[2];
  }

  // line 25 "model.ump"
  public void search(String query){

  }

  // line 28 "model.ump"
  public Recipe[] orderRecipes(Recipe [] rArray){
    return new Recipe[2];
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "database" + "=" + (getDatabase() != null ? !getDatabase().equals(this)  ? getDatabase().toString().replaceAll("  ","    ") : "this" : "null")
            + outputString;
  }
}