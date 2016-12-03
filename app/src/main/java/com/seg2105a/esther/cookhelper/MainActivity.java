package com.seg2105a.esther.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;

/**
 * Created by Esther on 2016-11-29.
 */

public class MainActivity extends AppCompatActivity {
    // Popup that displays the help menu on how to use CookHelper
    private FloatingActionButton help;
    private PopupWindow popUpWindow;
    private LayoutInflater layoutInflater;
    private LinearLayout linearLayout;

    public Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        help = (FloatingActionButton) findViewById(R.id.helpButton);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        help.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.help_popup, null);
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

   // private void advancedSearch(){
        // this should inflate the advanced search page with all the components
       // Switch advancedSearch = (Switch) findViewById(R.id.switch1);
       // advancedSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
           // public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
               // startActivity(new Intent(MainActivity.this, AdvancedSearch.class));

          //  }


       // });
   // }

    public void recipeSelect(){
        // this is the action that represents the "Recipe" of the day being selected. The unique ID of the recipe is sent
        // to the recipe view page directly

    }

    public void search(View view) {
        // go to the search result page with the information about what word to search for and in which scope
        Intent goSearch = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(goSearch);
    }
}
