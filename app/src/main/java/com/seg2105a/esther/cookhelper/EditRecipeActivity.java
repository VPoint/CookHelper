package com.seg2105a.esther.cookhelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

/**
 * Created by Esther on 2016-11-29.
 */

public class EditRecipeActivity extends AppCompatActivity {
    private RecipeSystem system;
    private Recipe recipe;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_form);

        system = system.getInstance();

        // build the category and recipe type spinners
        Spinner type = (Spinner) findViewById(R.id.formType);
        Spinner category = (Spinner) findViewById(R.id.formCategory);

        String[] categoryArray = new String[system.numberOfCategories()];
        for(Category c: system.getCategories()){
            categoryArray[system.indexOfCategory(c)] = c.getName();
        }

        String[] typeArray = new String[system.numberOfRecipeTypes()];
        for(RecipeType rt: system.getRecipeTypes()){
            typeArray[system.indexOfRecipeType(rt)] = rt.getName();
        }

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categoryArray);
        category.setAdapter(adapterCategory);

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, typeArray);
        type.setAdapter(adapterType);

        // if information is being edited, first populate fields
        Intent i = getIntent();

        if(i.hasExtra("recipe_id")) {
            index = i.getIntExtra("recipe_id", 0);
            recipe = system.getRecipe(index);
            recipe.setRecipeSystem(system);
            populateInfo(recipe);
        }

        cancel();
        save();
    }

    private void populateInfo(Recipe r){
        //ArrayList<String> timeUnits = R.id.unit_time_array;//{"Days","Hours", "Minutes", "Seconds"};

        EditText title = (EditText) findViewById(R.id.formTitle);
        EditText desc = (EditText) findViewById(R.id.formDescription);
        EditText cooking = (EditText) findViewById(R.id.formCookingTime);
        Spinner cookingUnits = (Spinner) findViewById(R.id.formCookingUnits);
        EditText serving = (EditText) findViewById(R.id.formServing);
        Spinner type = (Spinner) findViewById(R.id.formType);
        Spinner category = (Spinner) findViewById(R.id.formCategory);
        EditText calories = (EditText) findViewById(R.id.formCalories);
        EditText ingred = (EditText) findViewById(R.id.addIngredientInput);
        EditText recStep = (EditText) findViewById(R.id.addRecipeStepInput);
        RatingBar rating = (RatingBar) findViewById(R.id.addRating);

        title.setText("" + r.getTitle());
        desc.setText("" + r.getDescription());
        cooking.setText(r.getCookingTime() + "");
        Context context=getApplicationContext();
        //'ArrayList<S>[] rt = context.getResources().getStringArray(R.array.unit_time_array);
        //cookingUnits.setSelection(r.getCookingTimeUnits());
        serving.setText("" + r.getServing());
        type.setSelection(system.indexOfRecipeType(r.getRecipeType(0)));
        category.setSelection(system.indexOfCategory(r.getCategory(0)));
        calories.setText("" + r.getCalories());
        rating.setRating(r.getRating());
        ingred.setText("" + r.getIngredient(0).getName());
        recStep.setText("" + r.getRecipeStep(0).getDescription());
    }

    private Recipe retrieveRecipeInfo(){
        EditText title = (EditText) findViewById(R.id.formTitle);
        EditText desc = (EditText) findViewById(R.id.formDescription);
        EditText cooking = (EditText) findViewById(R.id.formCookingTime);
        Spinner cookingUnits = (Spinner) findViewById(R.id.formCookingUnits);
        EditText serving = (EditText) findViewById(R.id.formServing);
        Spinner type = (Spinner) findViewById(R.id.formType);
        Spinner category = (Spinner) findViewById(R.id.formCategory);
        EditText calories = (EditText) findViewById(R.id.formCalories);
        EditText ingred = (EditText) findViewById(R.id.addIngredientInput);
        EditText recStep = (EditText) findViewById(R.id.addRecipeStepInput);
        RatingBar rating = (RatingBar) findViewById(R.id.addRating);

        //Recipe(aTitle,aDescription, double aCookingTime, aImage, int aServing, int aCalories, RecipeSystem aRecipeSystem, Ingredient... allIngredient
        String[] ingredients = ingred.getText().toString().split(",");
        String[] recipeSteps = recStep.getText().toString().split(",");

        Recipe temp = new Recipe(title.getText()+"", desc.getText()+"", Double.parseDouble(cooking.getText()+""), "",
                Integer.parseInt(serving.getText() + ""), Integer.parseInt(calories.getText() + ""), system,new Ingredient(ingredients[0], system));
        temp.addCategory(new Category(category.getSelectedItem()+"", system));
        temp.addRecipeType(new RecipeType(type.getSelectedItem()+"", system));
        //temp.addRecipeStep(0);
        temp.setRating(rating.getRating());
        temp.setCookingTimeUnits(cookingUnits.getSelectedItem()+"");
        int inc = 1;
        while(inc < ingredients.length){
            temp.addIngredient(new Ingredient(ingredients[inc].trim(), system));
            inc++;
        }

        int incr = 0;
        while(incr < recipeSteps.length){
            temp.addRecipeStep(incr, recipeSteps[incr], 8, false);
            incr++;
        }

        temp.setId(system.indexOfRecipe(temp));
        return temp;
    }

    public void save() {
        // save changes to database
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recipe != null && index != -1){
                    system.removeRecipe(recipe);
                    recipe = retrieveRecipeInfo();
                    system.addRecipeAt(recipe, index);
                } else {
                    recipe = retrieveRecipeInfo();
                    system.addRecipe(recipe);
                }
                Toast.makeText(getApplicationContext(), "Your recipe was saved.", Toast.LENGTH_LONG).show();
                Intent viewRecipe = new Intent(EditRecipeActivity.this, ViewRecipeActivity.class);
                viewRecipe.putExtra("recipe_id", system.indexOfRecipe(recipe));
                startActivity(viewRecipe);
                finish();
            }
        });
    }

    public void cancel() {
        // cancel changes and return to home screen....
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(EditRecipeActivity.this, MainActivity.class);
                startActivity(goHome);
                finish();
            }
        });
    }
}