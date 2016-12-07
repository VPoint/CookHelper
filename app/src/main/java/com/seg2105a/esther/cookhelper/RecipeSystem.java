/*Esther Raji */
/*This code was generated using the UMPLE 1.24.0-abedcd4 modeling language!*/
package com.seg2105a.esther.cookhelper;

import android.widget.Toast;

import java.util.*;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-fcfceb9 modeling language!*/

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
  private List<Recipe> recipes;
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
    recipes = new ArrayList<Recipe>();
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

  public Recipe getRecipe(int index)
  {
    Recipe aRecipe = recipes.get(index);
    return aRecipe;
  }

  public List<Recipe> getRecipes()
  {
    List<Recipe> newRecipe= Collections.unmodifiableList(recipes);
    return newRecipe;
  }

  public int numberOfRecipes()
  {
    int number = recipes.size();
    return number;
  }

  public boolean hasRecipes()
  {
    boolean has = recipes.size() > 0;
    return has;
  }

  public int indexOfRecipe(Recipe aRecipe)
  {
    int index = recipes.indexOf(aRecipe);
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

  public static int minimumNumberOfRecipe()
  {
    return 0;
  }

  public Recipe addRecipe(String aTitle, String aDescription, double aCookingTime, String aImage, int aServing, int aCalories, Ingredient... allUsedIn)
  {
    return new Recipe(aTitle, aDescription, aCookingTime, aImage, aServing, aCalories, this, allUsedIn);
  }

  public boolean addRecipe(Recipe aRecipe)
  {
    boolean wasAdded = false;
    if (recipes.contains(aRecipe)) { return false; }
    RecipeSystem existingRecipeSystem = aRecipe.getRecipeSystem();
    boolean isNewRecipeSystem = existingRecipeSystem != null && !this.equals(existingRecipeSystem);
    if (isNewRecipeSystem)
    {
      aRecipe.setRecipeSystem(this);
    }
    else
    {
      recipes.add(aRecipe);
      aRecipe.setId(recipes.indexOf(aRecipe));
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRecipe(Recipe aRecipe)
  {
    boolean wasRemoved = false;
    //Unable to remove aRecipe, as it must always have a recipeSystem
    if (!this.equals(aRecipe.getRecipeSystem())){}
    else{
      recipes.remove(aRecipe);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRecipeAt(Recipe aRecipe, int index)
  {
    boolean wasAdded = false;
    if(addRecipe(aRecipe))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipes()) { index = numberOfRecipes() - 1; }
      recipes.remove(aRecipe);
      recipes.add(index, aRecipe);
      aRecipe.setId(index);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRecipeAt(Recipe aRecipe, int index)
  {
    boolean wasAdded = false;
    if(recipes.contains(aRecipe))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipes()) { index = numberOfRecipes() - 1; }
      recipes.remove(aRecipe);
      recipes.add(index, aRecipe);
      aRecipe.setId(index);
      wasAdded = true;
    }
    else
    {
      wasAdded = addRecipeAt(aRecipe, index);
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
    for(int i = recipes.size(); i > 0; i--)
    {
      Recipe aRecipe = recipes.get(i - 1);
      aRecipe.delete();
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

  private String[] parseQuery(String str){
      // seperates the query inputted by the user into parsable parts
      // if AND, OR and NOT are written out, it is treated differently from the symbols
      str = str.toUpperCase();
      str = str.replace(",", " AND ").replace(";", " OR ").replace("!", " NOT ");
      str = str.replace("(", "").replace(")", "");
      return str.split(" ");
  }

  public int findIngredient(String n){
    if(n == null){
      return -1;
    }
    List<Ingredient> in = getIngredients();
    int ingr;
    for ( ingr = 0; ingr < in.size(); ingr++){
      if(in.get(ingr).getName().equals(n.toLowerCase().trim())){
        return ingr;
      }
    }
    return -1;
  }

  public int findRecipeType(String n){
    if(n == null){
      return -1;
    }
    List<RecipeType> in = getRecipeTypes();
    int ingr;
    for ( ingr = 0; ingr < in.size(); ingr++){
      if(in.get(ingr).getName().equals(n.toLowerCase().trim())){
        return ingr;
      }
    }
    return -1;
  }

  public int findCategory(String n){
    if(n == null){
      return -1;
    }
    List<Category> in = getCategories();
    int ingr;
    for ( ingr = 0; ingr < in.size(); ingr++){
      if(in.get(ingr).getName().equals(n.toLowerCase().trim())){
        return ingr;
      }
    }
    return -1;
  }

  private List<Recipe> searchItem(String queryBasic, String type){
      List<Recipe> list = new ArrayList<Recipe>();
      int dx = -1;
      switch(type) {
          case "By Type":
              dx = findRecipeType(queryBasic);
              if (dx == -1) {
                  return new ArrayList<Recipe>();
              } else {
                  list = getRecipeType(dx).getHasA();
              }
              break;

          case "By Category":
              dx = findCategory(queryBasic);
              if (dx == -1) {
                  return new ArrayList<Recipe>();
              } else {
                  list = getCategory(dx).getHasA();
              }
              break;

          case "By Ingredient":
              dx = findIngredient(queryBasic);
              if (dx == -1) {
                  return new ArrayList<Recipe>();
              } else {
                  list = getIngredient(dx).getRecipes();
              }
              break;

          default:
              dx = findCategory(queryBasic);
              if (dx == -1) {
                  return new ArrayList<Recipe>();
              } else {
                  list = getCategory(dx).getHasA();
              }
              break;
      }

      return list;
  }

    private List<Recipe> searchType(String[] q, String type){
        List<Recipe> recipeResult = new ArrayList<Recipe>();
        if (q.length == 1) {
            recipeResult = searchItem(q[0], type);
        } else {
            int i = 0;
            while(i < q.length - 1){
                switch(q[i]){
                    case "AND":
                        recipeResult.retainAll(searchItem(q[i + 1],type));
                        i = i+2;
                        break;

                    case "OR":
                        recipeResult.removeAll(searchItem(q[i + 1],type));
                        recipeResult.addAll(searchItem(q[i + 1],type));
                        i = i+2;
                        break;

                    case "NOT":
                        if(i == 0) {recipeResult = getRecipes(); }
                        recipeResult.removeAll(searchItem(q[i + 1],type));
                        i = i+2;
                        break;

                    default:
                        recipeResult.addAll(searchItem(q[i],type));
                        i++;
                        break;
                }
            }
        }
        return recipeResult;
    }
    public List<Recipe> searchQuery(String queryCategory, String queryType, String queryIngredient){
        List<Recipe> recipeResult = new ArrayList<Recipe>();
        String connectors = "";
        if(queryCategory != null && !queryCategory.isEmpty()){
             String[] q = parseQuery(queryCategory);
            recipeResult = searchType(q, "By Category");
             connectors = q[q.length - 1];
        }

        if(queryType != null && !queryType.isEmpty()){
            String[] q = parseQuery(queryType);
            switch(connectors){
                case "AND":
                    recipeResult.retainAll(searchType(q, "By Type"));
                    break;

                case "OR":
                    recipeResult.removeAll(searchType(q, "By Type"));
                    recipeResult.addAll(searchType(q, "By Type"));
                    break;

                default:
                    recipeResult = searchType(q, "By Type");
                    break;
            }
            connectors = q[q.length - 1];
        }

        if(queryIngredient != null && !queryIngredient.isEmpty()){
            String[] q = parseQuery(queryIngredient);
            switch(connectors){
                case "AND":
                    recipeResult.retainAll(searchType(q, "By Type"));
                    break;

                case "OR":
                    recipeResult.removeAll(searchType(q, "By Type"));
                    recipeResult.addAll(searchType(q, "By Type"));
                    break;

                default:
                    recipeResult = searchType(q, "By Type");
                    break;
            }
        }
        return recipeResult;
    }

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