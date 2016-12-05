package com.seg2105a.esther.cookhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Esther on 2016-11-29.
 */

public class MainActivity extends AppCompatActivity {
    // Popup that displays the help menu on how to use CookHelper
    private FloatingActionButton help;
    private PopupWindow popUpWindow;
    private LayoutInflater layoutInflater;
    private LinearLayout linearLayout;

    private ActionBar actionBar;
    private RecipeSystem system;
    private Recipe feature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        system = system.getInstance();

        system.addCategory("Mexican");
        system.addCategory("Canadian");
        system.addCategory("Japanese");
        system.addCategory("Italian");

        system.addRecipeType("main dish");
        system.addRecipeType("dessert");
        system.addRecipeType("brunch");
        system.addRecipeType("appetizer");

        system.addIngredient("kale");
        system.addIngredient("candy");
        system.addIngredient("cheese");
        system.addIngredient("tomatos");

        system.addHasA("Tomato Salsa", "There needs to be something here..", 4.5, "", 6, 900, system.getIngredient(0));
        system.getHasA(0).addRecipeStep(0, "Cut Stuff Up", 6.0, false);
        system.getHasA(0).addCategory(system.getCategory(1));
        system.getHasA(0).addRecipeType(system.getRecipeType(2));
        //
        //system.addRecipe("Pasta","khjhkhjkhjkh jkhjkhjkhkjhhkj hjkhkjkh", 40.2, "");
        //system.addRecipe("Pizza","khjhkhldskjf sdkjfljsd fsldkmksdklffsdlf dkhkjkh", 90909.2, "");
        //system.addRecipe("Pastry","khjhkhjkhjkh aaasaasnnsadad adadka da a da hjkhkjkh", 8.2, "");

        //feature = system.getRecipe(0).addCategory(system.getCategory(1));

        changeSwitch();
        //search();
        helpButton();
        recipeSelect();
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

    private void setFeatured(){
        // set the featured item information
    }

    private void helpButton(){
        // Jessica Zhan

        help = (FloatingActionButton) findViewById(R.id.helpButton);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        help.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.activity_help_popup, null);
                popUpWindow = new PopupWindow(container,880,1100,true);
                popUpWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY, 100,400);

                //Tapping outside of popup window closes it
                container.setOnTouchListener(new View.OnTouchListener(){

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent){
                        popUpWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }

    private void changeSwitch(){
        // this should inflate the advanced search page with all the components
        Switch advancedSearch = (Switch) findViewById(R.id.searchSwitch);
        advancedSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                LinearLayout advanced = (LinearLayout) findViewById(R.id.advancedSearch);
                LinearLayout basic = (LinearLayout) findViewById(R.id.basicSearch);
                if(isChecked){
                    advanced.setVisibility(View.VISIBLE);
                    basic.setVisibility(View.GONE);
                } else {
                    basic.setVisibility(View.VISIBLE);
                    advanced.setVisibility(View.GONE);
                }
            }
        });
    }

    public void recipeSelect(){
        // this is the action that represents the "Recipe" of the day being selected. The unique ID of the recipe is sent
        // to the recipe view page directly
        LinearLayout featureSection = (LinearLayout) findViewById(R.id.layoutFeatured);
        featureSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView feature = (TextView) findViewById(R.id.titleFeatured);
                Intent recipeView = new Intent(getApplicationContext(), ViewRecipeActivity.class);
                recipeView.putExtra("recipe_id", 0);
                startActivity(recipeView);
            }
        });
    }

    public void search(View v) {
        EditText query = (EditText) findViewById(R.id.searchBox);
        Spinner type = (Spinner) findViewById(R.id.searchType);

        Intent goSearch = new Intent(getApplicationContext(), SearchActivity.class);

        goSearch.putExtra("type", type.getSelectedItem().toString());
        goSearch.putExtra("query", query.getText());
        startActivity(goSearch);
    }

    public void advSearch(View v) {
        EditText queryCategory = (EditText) findViewById(R.id.categoryQuery);
        EditText queryIngredient = (EditText) findViewById(R.id.ingredientQuery);
        EditText queryType = (EditText) findViewById(R.id.typeQuery);

        ToggleButton toggleCategory = (ToggleButton) findViewById(R.id.toggleCategory);
        ToggleButton toggleType = (ToggleButton) findViewById(R.id.toggleType);

        Intent goAdvSearch = new Intent(getApplicationContext(), SearchActivity.class);

        goAdvSearch.putExtra("queryCategory", queryCategory.getText() + "" + toggleCategory.getText());
        goAdvSearch.putExtra("queryIngredient", queryIngredient.getText());
        goAdvSearch.putExtra("queryType", queryType.getText() + "" + toggleType.getText());
        startActivity(goAdvSearch);
    }
}
