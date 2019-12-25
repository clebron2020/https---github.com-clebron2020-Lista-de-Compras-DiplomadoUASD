package com.diplomadouasd.buylistapp.View;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public abstract class BaseActivity extends AppCompatActivity {
    protected @MenuRes Integer ActivityMenuRes;
    public ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    protected abstract void InitializeView();
    protected abstract void SetEvents();

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (ActivityMenuRes != null)
            getMenuInflater().inflate(ActivityMenuRes,menu);
        return true;
    }

    public void GoToActivity(Class<?> activity){
        Intent intent = new Intent(getApplicationContext(),activity);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public View myLayouInflater(@LayoutRes int resource){
        View v = LayoutInflater.from(getApplicationContext()).inflate(resource,null,false);
        return v;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
