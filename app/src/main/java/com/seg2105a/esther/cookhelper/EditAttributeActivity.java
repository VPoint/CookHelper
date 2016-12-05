package com.seg2105a.esther.cookhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Esther on 2016-11-29.
 *
 * Generic Attribute page, changes depending on incoming data.
 */

public class EditAttributeActivity extends AppCompatActivity {
    private RecipeSystem system;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attribute);

        system = system.getInstance();
        // list all of the variables of this attribute type underneath the search bar
    }

    public void save(){
        // this changes the data in the business logic & therefore the database....

    }

    public void selectAttribute(){
        // searches database for recipes having attribute with selected name and returns to the search results activity

    }
}