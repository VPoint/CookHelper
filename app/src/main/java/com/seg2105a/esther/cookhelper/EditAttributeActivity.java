package com.seg2105a.esther.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Esther on 2016-11-29.
 *
 * Generic Attribute page, changes depending on incoming data.
 */

public class EditAttributeActivity extends AppCompatActivity {
    private RecipeSystem system;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_add_attribute);

        system = system.getInstance();
        Intent i = getIntent();
        type = i.getStringExtra("typeAttr");
        int num;
        String label, buttonText;
        final List<?> list;
        switch(type){
            case "type":
                num = system.numberOfRecipeTypes();
                list = system.getRecipeTypes();
                label = "Name of Recipe Type:";
                buttonText = "Add Recipe Type";
                break;

            case "category":
                num = system.numberOfCategories();
                list = system.getCategories();
                label = "Name of Category:";
                buttonText = "Add Category";
                break;

            case "ingredient":
                num = system.numberOfIngredients();
                list = system.getIngredients();
                label = "Name of Ingredient:";
                buttonText = "Add Ingredient";
                break;

            default:
                num = system.numberOfCategories();
                list = system.getCategories();
                label = "Name of Category:";
                buttonText = "Add Category";
                break;
        }

        TextView attrLabel = (TextView) findViewById(R.id.attributeLabel);
        attrLabel.setText(label);

        Button attrButton = (Button) findViewById(R.id.addAttribute);
        attrButton.setText(buttonText);

        // list all of the variables of this attribute type underneath the search bar
        ListView listView = (ListView) findViewById(R.id.attributeList);

        String[] values = new String[num];
        //Converting Array to ArrayList
        for (int v = 0; v < values.length; ++v) {
            values[v] = list.get(v).toString();
        }
        //Create an ArrayAdapter and Set it on the ListView
        ArrayAdapter adapter = new BasicListAdapter(this, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                //Do something with the string that you just got!
                Intent goSearch = new Intent(getApplicationContext(), SearchActivity.class);

                goSearch.putExtra("type", type);
                goSearch.putExtra("query", item);
                startActivity(goSearch);

            }
        });

        save();
    }

    public void save(){
        // this changes the data in the business logic & therefore the database....
        Button saveButton = (Button) findViewById(R.id.addAttribute);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.attributeInput);
                switch(type){
                    case "type":
                        system.addRecipeType(input.getText()+"");
                        recreate();
                        break;

                    case "category":
                        system.addCategory(input.getText()+"");
                        recreate();
                        break;

                    case "ingredient":
                        system.addIngredient(input.getText()+"");
                        recreate();
                        break;

                    default:
                        system.addCategory(input.getText()+"");
                        recreate();
                        break;
                }
            }
        });

    }
}