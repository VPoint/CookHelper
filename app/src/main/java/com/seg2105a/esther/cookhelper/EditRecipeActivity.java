package com.seg2105a.esther.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by Esther on 2016-11-29.
 */

public class EditRecipeActivity extends AppCompatActivity {
    private RecipeSystem system;
    private Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_form);

        system = system.getInstance();
        // if information is being edited, first populate fields
        Intent i = getIntent();

        if(i.hasExtra("recipe_id")) {
            Integer indx = Integer.parseInt(i.getStringExtra("recipe_id"));
            recipe = system.getHasA(indx);
            populateInfo(recipe);
        }

        browseImages();
        cancel();
        save();
    }

    private void populateInfo(Recipe r){
        EditText title = (EditText) findViewById(R.id.formTitle);
        EditText desc = (EditText) findViewById(R.id.formDescription);
        EditText cooking = (EditText) findViewById(R.id.formCookingTime);
        EditText serving = (EditText) findViewById(R.id.formServing);
        EditText type = (EditText) findViewById(R.id.formType);
        EditText category = (EditText) findViewById(R.id.formCategory);
        EditText calories = (EditText) findViewById(R.id.formCalories);
        EditText ingred = (EditText) findViewById(R.id.addIngredientInput);
        EditText recStep = (EditText) findViewById(R.id.addRecipeStepInput);
        RatingBar rating = (RatingBar) findViewById(R.id.addRating);

        title.setText(r.getTitle());
        desc.setText(r.getDescription());
        cooking.setText(r.getCookingTime() + "");
        serving.setText(r.getServing());
        type.setText(r.getRecipeType(0).getName());
        category.setText(r.getCategory(0).getName());
        calories.setText(r.getCalories());
        rating.setRating(r.getRating());
        ingred.setText(r.getUsedIn(0).getName());
        recStep.setText(r.getRecipeStep(0).getDescription());
    }

    private Recipe retrieveRecipeInfo(){
        EditText title = (EditText) findViewById(R.id.formTitle);
        EditText desc = (EditText) findViewById(R.id.formDescription);
        EditText cooking = (EditText) findViewById(R.id.formCookingTime);
        EditText serving = (EditText) findViewById(R.id.formServing);
        EditText type = (EditText) findViewById(R.id.formType);
        EditText category = (EditText) findViewById(R.id.formCategory);
        EditText calories = (EditText) findViewById(R.id.formCalories);
        EditText ingred = (EditText) findViewById(R.id.addIngredientInput);
        EditText recStep = (EditText) findViewById(R.id.addRecipeStepInput);
        RatingBar rating = (RatingBar) findViewById(R.id.addRating);

        //Recipe(aTitle,aDescription, double aCookingTime, aImage, int aServing, int aCalories, RecipeSystem aRecipeSystem, Ingredient... allUsedIn
        Recipe temp = new Recipe(title.getText()+"", desc.getText()+"", Double.parseDouble(cooking.getText()+""), "",
                Integer.parseInt(serving.getText() + ""), Integer.parseInt(calories.getText() + ""), system, null);
        temp.addCategory(new Category(category.getText()+"", system));
        temp.addRecipeType(new RecipeType(type.getText()+"", system));
        //temp.addRecipeStep(0);
        temp.setRating(rating.getRating());
        temp.addUsedIn(new Ingredient(ingred.getText()+"", system));
        temp.addRecipeStep(0, recStep.getText()+"",10, false);
        temp.setId(system.indexOfHasA(temp));
        return temp;
    }

    public void save() {
        // save changes to database
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recipe != null){
                    int indx = system.indexOfHasA(recipe);
                    system.removeHasA(recipe);
                    system.addHasAAt(retrieveRecipeInfo(), indx);
                } else {
                    recipe = retrieveRecipeInfo();
                }
                Intent viewRecipe = new Intent(EditRecipeActivity.this, ViewRecipeActivity.class);
                viewRecipe.putExtra("recipe_id", system.indexOfHasA(recipe));
                startActivity(viewRecipe);
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
            }
        });
    }

    public void browseImages() {
        // looks in file system for any images the useer wants to use, saves in database using the Recipe ID....
        Button browseButton = (Button) findViewById(R.id.browseButton);
        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open gallery intent and select one image
                // save path in edit text
                //Intent goSearch = new Intent(EditRecipeActivity.this, SearchActivity.class);
                //startActivity(goSearch);
            }
        });
    }
}