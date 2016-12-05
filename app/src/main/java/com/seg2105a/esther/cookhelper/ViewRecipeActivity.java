package com.seg2105a.esther.cookhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import org.w3c.dom.Text;

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
        Integer indx = Integer.parseInt(i.getStringExtra("recipe_id"));
        recipe = system.getHasA(indx);

        populateInfo(recipe);

        playRecipeStep();
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
        TextView ingrContent = (TextView) findViewById(R.id.ingredientContent);
        TextView recipeStep = (TextView) findViewById(R.id.recipeStepContent);
        RatingBar rating = (RatingBar) findViewById(R.id.recipeRatingBar);

        title.setText(r.getTitle());
        //image.setImageDrawable(null);
        calories.setText(r.getCalories());
        type.setText(r.getRecipeType(0).getName());
        category.setText(r.getCategory(0).getName());
        cookTime.setText("" + r.getCookingTime());
        //ingrContent.setText(r.getUsedIn().toString());
        recipeStep.setText(r.getRecipeSteps().toString());
    }

    public void playRecipeStep(){
        //goes to the RecipeStep
        FloatingActionButton playButton = (FloatingActionButton) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent recipeStep = new Intent(ViewRecipeActivity.this, RecipeStepActivity.class);
                recipeStep.putExtra("recipe_id", system.indexOfHasA(recipe));
                startActivity(recipeStep);
            }
        });
    }

    public void editRecipe(){
        // edits the valuesin the recipe by first going to the recipe forma and changing the values
        FloatingActionButton editButton = (FloatingActionButton) findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent editRecipe = new Intent(ViewRecipeActivity.this, EditRecipeActivity.class);
                editRecipe.putExtra("recipe_id", system.indexOfHasA(recipe));
                startActivity(editRecipe);
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
                Intent editRecipe = new Intent(ViewRecipeActivity.this, EditRecipeActivity.class);
                editRecipe.putExtra("recipe_id", system.indexOfHasA(recipe));
                startActivity(editRecipe);
            }
        });
    }
}