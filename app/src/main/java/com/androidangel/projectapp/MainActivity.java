package com.androidangel.projectapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.androidangel.projectapp.adapter.StudentAdapter;
import com.androidangel.projectapp.database.StudentDbHelper;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private StudentDbHelper dbHelper;
    private StudentAdapter adapter;
    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the variables
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populateRecyclerView(filter);

        //Todo: MAKE THE SETTINGS PREFERENCES FOR ORDER BY

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String orderBy = sharedPreferences.getString(getString(R.string.settings_order_by_key), getString(R.string.settings_order_by_default));
    }

    private void populateRecyclerView(String filter){
        dbHelper = new StudentDbHelper(this);
        adapter = new StudentAdapter(dbHelper.studentList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.filterSpinner);
        Spinner spinner = (Spinner) item.getActionView();

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filterOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String filter = parent.getSelectedItem().toString();
                populateRecyclerView(filter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                populateRecyclerView(filter);
            }
        });


        spinner.setAdapter(adapter);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addMenu:
                goToAddUserActivity();
                return true;
            case R.id.action_settings:

                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void goToAddUserActivity(){
        Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}



