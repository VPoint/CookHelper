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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Switch;

public class SearchActivity extends AppCompatActivity {
    private RecipeSystem system;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_search_result);

        system = system.getInstance();

        // Get ListView object from xml layout
        ListView listView = (ListView) findViewById(R.id.recipeList);
        //Defining Array values to show in ListView
        String[] values = new String[] {
                "Spaghetti","Lasagna","Meatballs","Pasta Sauce","Raviolli","Cheese Sauce","Item 07","Item 08"
        };
        //Converting Array to ArrayList
        /*final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }*/
        //Create an ArrayAdapter and Set it on the ListView
        ArrayAdapter adapter = new RecipeAdapter(this, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                //Do something with the string that you just got!
                Intent viewRecipe = new Intent(SearchActivity.this, ViewRecipeActivity.class);
                startActivity(viewRecipe);

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


    public void search(View v) {
        Intent goSearch = new Intent(SearchActivity.this, SearchActivity.class);
        startActivity(goSearch);
    }

    public void advSearch(View v) {
        Intent goAdvSearch = new Intent(SearchActivity.this, SearchActivity.class);
        startActivity(goAdvSearch);
    }

    public void addRecipe(){
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addRecipeButton);
        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent addRecipe = new Intent(SearchActivity.this, EditRecipeActivity.class);
                startActivity(addRecipe);
            }
        });

    }
}
