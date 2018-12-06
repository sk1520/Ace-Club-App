package org.stairsacademy.aceclub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    //Test Data base
    private DemoDatabase data = new DemoDatabase();
    //Shared Preference object for log out activity
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Create Bottom Navigation object
        Bottom Navigation was created using
        Source: https://www.youtube.com/watch?v=tPV8xA7m-iw&t=332s
         */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //Open home fragment when the app starts
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        /*
        Get existing preference called login
        which was created in LoginActivity.java
         */
        sp = getSharedPreferences("login", MODE_PRIVATE);
    }

    /*
    Display logout button on top menu bar (looks like power button)
    Check onOptionsItemSelected(...) method.
    Source: https://www.youtube.com/watch?v=5MSKuVO2hV4
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.log_off_button, menu);
        return true;
    }

    /*
    When power button is clicked, this method is called.
    We "log out" by going to login activity.
    Check goToLoginActivity() method.
    Source: https://developer.android.com/training/appbar/actions
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_log_off){
            goToLoginActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Set existing boolean shared preference variable
    called logged to false so when app opens it goes to
    LoginActivity.
    Create intent to move from MainActivity to LoginActivity.
    Start intent.
    Let user know logout was successful with a toast.
    Destroy activity using finish() method.
     */
    private void goToLoginActivity(){
        sp.edit().putBoolean("logged", false).apply();
        Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(mainIntent);
        Toast.makeText(MainActivity.this, "Logout was successful", Toast.LENGTH_LONG).show();
        finish();
    }

    /*
    Display bottom navigation fragment depending on which one is clicked.
    Uses MenuItem's getItemId method to retrieve id of fragment.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            //classDisplay();
                            break;
                        case R.id.nav_school:
                            selectedFragment = new SchoolFragment();
                            break;
                        case R.id.nav_grades:
                            selectedFragment = new GradesFragment();
                            break;
                    }

                    //Display the clicked fragment.
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

}
