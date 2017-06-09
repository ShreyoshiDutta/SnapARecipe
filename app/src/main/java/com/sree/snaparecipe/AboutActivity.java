package com.sree.snaparecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

public class AboutActivity extends MyActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_about);
        myToolbar.setTitle("About");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView foo = (TextView) findViewById(R.id.editTextAbout);
        foo.setText(Html.fromHtml(getString(R.string.about)));
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
