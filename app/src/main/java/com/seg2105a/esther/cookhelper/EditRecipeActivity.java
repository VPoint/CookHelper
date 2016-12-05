package com.seg2105a.esther.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Esther on 2016-11-29.
 */

public class EditRecipeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_form);

        // if information is being edited, first populate fields

        // else treat like add activity & create a new Recipe Object to place in the Database

        browseImages();
        cancel();
        save();
    }

    public void save() {
        // save changes to database
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewRecipe = new Intent(EditRecipeActivity.this, ViewRecipeActivity.class);
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