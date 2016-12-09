package com.seg2105a.esther.cookhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Esther on 2016-11-29.
 */

public class ViewRecipeActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private RecipeSystem system;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_recipe_view);

        //loads the activity and populates the text view with information from the Recipe object

        system = system.getInstance();
        Intent i = getIntent();
        if(i.hasExtra("recipe_id")) {
            int index = i.getIntExtra("recipe_id", 0);
            recipe = system.getRecipe(index);
            recipe.setRecipeSystem(system);
            populateInfo(recipe);
        }

        editRecipe();
        deleteRecipe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.addCategory:
                Intent addCategory = new Intent(getApplicationContext(), EditAttributeActivity.class);
                addCategory.putExtra("typeAttr", "category");
                startActivity(addCategory);
                return true;
            case R.id.addType:
                Intent addType = new Intent(getApplicationContext(), EditAttributeActivity.class);
                addType.putExtra("typeAttr", "type");
                startActivity(addType);
                return true;
            case R.id.addIngredient:
                Intent addIngr = new Intent(getApplicationContext(), EditAttributeActivity.class);
                addIngr.putExtra("typeAttr", "ingredient");
                startActivity(addIngr);
                return true;
            case R.id.addRecipe:
                Intent addRecipe = new Intent(getApplicationContext(), EditRecipeActivity.class);
                startActivity(addRecipe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void populateInfo(Recipe r){
        TextView title = (TextView) findViewById(R.id.recipeTitle);
        ImageView image = (ImageView) findViewById(R.id.recipeImage);
        TextView calories = (TextView) findViewById(R.id.recipeCalories);
        TextView type = (TextView) findViewById(R.id.recipeType);
        TextView category = (TextView) findViewById(R.id.recipeCategory);
        TextView cookTime = (TextView) findViewById(R.id.recipeCookingTime);
        TextView serving = (TextView) findViewById(R.id.recipeServing);
        TextView description = (TextView) findViewById(R.id.descriptionContent);
        TextView ingrContent = (TextView) findViewById(R.id.ingredientContent);
        TextView recipeStep = (TextView) findViewById(R.id.recipeStepContent);
        RatingBar rating = (RatingBar) findViewById(R.id.recipeRatingBar);

        title.setText(r.getTitle());
        //image.setImageDrawable(null);
        calories.setText("Calories: " +r.getCalories());
        type.setText("Type: " + r.getRecipeType(0).getName());
        category.setText("Category: " +r.getCategory(0).getName());
        cookTime.setText("Time Needed: " + r.getCookingTime() + " min");
        serving.setText("Serves: " + r.getServing());
        description.setText(r.getDescription());

        ingrContent.setText(displayList(r.getIngredient()));
        recipeStep.setText(displayList(r.getRecipeSteps()));
        rating.setRating(r.getRating());
    }

    private String displayList(List l){
        int inc = 0;
        String output = "";

        while(inc < l.size()){
            output += l.get(inc).toString()+ "\n";
            inc++;
        }

        return output;
    }

    public void editRecipe(){
        // edits the valuesin the recipe by first going to the recipe forma and changing the values
        FloatingActionButton editButton = (FloatingActionButton) findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent editRecipe = new Intent(ViewRecipeActivity.this, EditRecipeActivity.class);
                editRecipe.putExtra("recipe_id", system.indexOfRecipe(recipe));
                startActivityForResult(editRecipe, 100);
                finish();
            }
        });
    }

    public void deleteRecipe(){
        // deletes the recipe from the Business Logic as well as the database

        FloatingActionButton deleteButton = (FloatingActionButton) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //delete Recipe
                system.removeRecipe(recipe);
                Toast.makeText(getApplicationContext(), "Your recipe has been safely deleted.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}