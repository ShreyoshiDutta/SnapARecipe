package com.sree.snaparecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


class MyActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this,PreferencesActivity.class));
                return true;

            case R.id.help:
                startActivity(new Intent(this,HelpActivity.class));
                return true;

            case R.id.about:
                startActivity(new Intent(this,AboutActivity.class));
                return true;

            case R.id.action_go_home:
                startActivity(new Intent(MyActivity.this,HomeActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
