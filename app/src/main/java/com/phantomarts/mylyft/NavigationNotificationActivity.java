package com.phantomarts.mylyft;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NavigationNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_notification);
        initActionBar();
    }

    private void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.notification);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
