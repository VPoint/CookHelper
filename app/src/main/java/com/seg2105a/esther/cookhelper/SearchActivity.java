package com.seg2105a.esther.cookhelper;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecipeSystem system;
    private Integer[] recipe_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_search_result);

        system = system.getInstance();

        Button search = (Button) findViewById(R.id.searchButton);
        Button searchADV = (Button) findViewById(R.id.advancedSearchButton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        searchADV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advSearch();
            }
        });
        // Get ListView object from xml layout
        ListView listView = (ListView) findViewById(R.id.recipeList);
        //Defining Array values to show in ListView
//        String[] values = new String[] {
//                "Spaghetti","Lasagna","Meatballs","Pasta Sauce","Raviolli","Cheese Sauce","Item 07","Item 08"
//        };
        //Converting Array to ArrayList
        List<Recipe> list;
        Intent intent = getIntent();

        if (intent.hasExtra("query") && !intent.getStringExtra("query").isEmpty()) {
            Toast.makeText(getApplicationContext(), intent.getStringExtra("query"), Toast.LENGTH_LONG).show();
            String type = intent.getStringExtra("type");
            String queryBasic = intent.getStringExtra("query");

            switch(type) {
                case "By Type":
                    list = system.searchQuery(null, queryBasic, null);
                    if(list.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Your query was not found", Toast.LENGTH_LONG).show();
                    }
                    break;

                case "By Category":
                    list = system.searchQuery(queryBasic, null, null);
                    if(list.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Your query was not found", Toast.LENGTH_LONG).show();
                    }
                    break;

                case "By Ingredient":
                    list = system.searchQuery(null, null, queryBasic);
                    if(list.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Your query was not found", Toast.LENGTH_LONG).show();
                    }
                    break;

                default:
                    list = system.searchQuery(null, null, null);
                    if(list.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Your query was not found", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
/*            switch(type){
                case "By Type":
                    dx = system.findRecipeType(queryBasic);
                    if (dx == -1) {
                        Toast.makeText(getApplicationContext(), "Your query was not found", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        list = system.getRecipeType(dx).getHasA();
                    }
                    break;

                case "By Category":
                    dx = system.findCategory(queryBasic);
                    if (dx == -1) {
                        Toast.makeText(getApplicationContext(), "Your query was not found", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        list = system.getCategory(dx).getHasA();
                    }
                    break;

                case "By Ingredient":
                    dx = system.findIngredient(queryBasic);
                    if (dx == -1) {
                        Toast.makeText(getApplicationContext(), "Your query was not found", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        list = system.getIngredient(dx).getRecipes();
                    }
                    break;

                default:
                    dx = system.findCategory(queryBasic);
                    if (dx == -1) {
                        Toast.makeText(getApplicationContext(), "Your query was not found", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        list = system.getCategory(dx).getHasA();
                    }
                    break;
            }*/
        } else if ((intent.hasExtra("queryCategory") && intent.hasExtra("queryIngredient") && intent.hasExtra("queryType")) &&
                (!intent.getStringExtra("queryCategory").isEmpty() || !intent.getStringExtra("queryIngredient").isEmpty() ||
                 !intent.getStringExtra("queryType").isEmpty())){
            String queryCategory = intent.getStringExtra("queryCategory");;
            String queryType = intent.getStringExtra("queryType");;
            String queryIngredient = intent.getStringExtra("queryIngredient");;

            Toast.makeText(getApplicationContext(), queryCategory + " : " + queryType + " : " + queryIngredient, Toast.LENGTH_LONG).show();

            list = system.searchQuery(queryCategory, queryType, queryIngredient);
            if(list.isEmpty()){
                Toast.makeText(getApplicationContext(), "Your query was not found", Toast.LENGTH_LONG).show();
            }
        } else {
            list = system.getRecipes();
        }

        String[] values = new String[list.size()];
        recipe_id = new Integer[list.size()];
        String[] tagline = new String[list.size()];

        for (int i = 0; i < values.length; ++i) {
            values[i] = list.get(i).getTitle();
            tagline[i] = list.get(i).getCategory(0).toString() + " , " + list.get(i).getRecipeType(0).toString();
            recipe_id[i] = list.get(i).getId();
        }
        //Create an ArrayAdapter and Set it on the ListView
        ArrayAdapter adapter = new RecipeAdapter(this, values, tagline);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                //Do something with the string that you just got!
                Intent viewRecipe = new Intent(SearchActivity.this, ViewRecipeActivity.class);

                viewRecipe.putExtra("recipe_id" , recipe_id[position]);
                startActivity(viewRecipe);
                finish();
            }
        });

        //search();
        addRecipe();
        changeSwitch();
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

    public void search() {
        EditText query = (EditText) findViewById(R.id.searchBox);
        Spinner type = (Spinner) findViewById(R.id.searchType);

        Intent goSearch = new Intent(getApplicationContext(), SearchActivity.class);

        goSearch.putExtra("type", type.getSelectedItem().toString());
        goSearch.putExtra("query", query.getText()+ "");
        startActivity(goSearch);
        finish();
    }

    public void advSearch() {
        EditText queryCategory = (EditText) findViewById(R.id.categoryQuery);
        EditText queryIngredient = (EditText) findViewById(R.id.ingredientQuery);
        EditText queryType = (EditText) findViewById(R.id.typeQuery);

        ToggleButton toggleCategory = (ToggleButton) findViewById(R.id.toggleCategory);
        ToggleButton toggleType = (ToggleButton) findViewById(R.id.toggleType);

        Intent goAdvSearch = new Intent(getApplicationContext(), SearchActivity.class);

        goAdvSearch.putExtra("queryCategory", queryCategory.getText() + " " + toggleCategory.getText());
        goAdvSearch.putExtra("queryIngredient", queryIngredient.getText() + "");
        goAdvSearch.putExtra("queryType", queryType.getText() + " " + toggleType.getText());
        startActivity(goAdvSearch);
        finish();
    }

    public void addRecipe(){
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addRecipeButton);
        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent addRecipe = new Intent(SearchActivity.this, EditRecipeActivity.class);
                startActivity(addRecipe);
                finish();
            }
        });

    }
}
