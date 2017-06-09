package com.sree.snaparecipe;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_help);
        myToolbar.setTitle("Help");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView foo = (TextView) findViewById(R.id.editText);
        foo.setText(Html.fromHtml(getString(R.string.help)));
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
