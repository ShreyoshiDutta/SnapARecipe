package com.sree.snaparecipe;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PreferencesActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String NUMBER_OF_RECIPES = "NUMBER_OF_RECIPES";
    public static final String STUBBED_MODE = "STUBBED_MODE";
    public static final String TAG = "PreferencesActivity";
    public static final boolean STUBBED_MODE_VALUE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_pref);
        myToolbar.setTitle("Settings");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();

        final EditText numOfRecipes = (EditText) findViewById(R.id.numberOfRecipes);
        numOfRecipes.setText(String.valueOf(settings.getInt(NUMBER_OF_RECIPES,3)));
        numOfRecipes.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d(TAG,v.getText().toString());
                Integer.parseInt(v.getText().toString());
                // Restore preferences

                editor.putInt(NUMBER_OF_RECIPES, Integer.parseInt(v.getText().toString()));
                editor.commit();

                return false;
            }
        });


        /*ToggleButton toggle = (ToggleButton) findViewById(R.id.stubMode);
        toggle.setChecked(settings.getBoolean(STUBBED_MODE,true));

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean(STUBBED_MODE, true);

                } else {
                    editor.putBoolean(STUBBED_MODE, false);
                }
                editor.commit();
            }
        });*/

        Button save = (Button) findViewById(R.id.savePref);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,numOfRecipes.getText().toString());
                Integer.parseInt(numOfRecipes.getText().toString());
                // Restore preferences

                editor.putInt(NUMBER_OF_RECIPES, Integer.parseInt(numOfRecipes.getText().toString()));
                editor.commit();
                onSupportNavigateUp();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
