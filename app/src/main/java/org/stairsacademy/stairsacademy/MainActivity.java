package org.stairsacademy.stairsacademy;

import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.annotation.NonNull;
//import android.support.design.widget.BottomNavigationView;
//import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private DemoDatabase data = new DemoDatabase();
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Bottom Navigation object
        //BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        //bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //List Of classes
        ListView classList = (ListView) findViewById(R.id.class_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data.classes);
        classList.setAdapter(adapter);
        classActions(classList);

        //Open home fragment when the app starts
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


        //get preference
        sp = getSharedPreferences("login", MODE_PRIVATE);
    }

    public void classActions(ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, data.classes.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.log_off_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_log_off){
            goToLoginActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                    Fragment selectedFragment = null;
//
//                    switch (menuItem.getItemId()){
//                        case R.id.nav_home:
//                            selectedFragment = new HomeFragment();
//                            break;
//                        case R.id.nav_school:
//                            selectedFragment = new SchoolFragment();
//                            break;
//                        case R.id.nav_grades:
//                            selectedFragment = new GradesFragment();
//                            break;
//                    }
//
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
//                    return true;
//                }
//            };

    private void goToLoginActivity(){
        sp.edit().putBoolean("logged", false).apply();
        Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(mainIntent);
        Toast.makeText(MainActivity.this, "Logout was successful", Toast.LENGTH_LONG).show();
        finish();
    }

}
