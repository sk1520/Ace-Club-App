package org.stairsacademy.aceclub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    /*
    LoginActivity was created using
    Source: https://www.youtube.com/watch?v=uV037mLG_Ps

    LoginActivity was made the starting point of the app in the
    manifests/AndroidManifest.xml file.
    Source: https://stackoverflow.com/questions/3631982/change-applications-starting-activity
     */

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    private DemoDatabase ddb = new DemoDatabase();

    // UI references.
    private EditText mPassword, mUserName;
    private View mProgressView;
    private View mLoginFormView;
    //Dummy Database
    private DemoDatabase data = new DemoDatabase();

    /*
    Shared preference for log in
    Source: https://medium.com/@prakharsrivastava_219/keep-the-user-logged-in-android-app-5fb6ce29ed65
     */
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //First set up of sharedPreferences
        sp = getSharedPreferences("login", MODE_PRIVATE);

        /*
        Check if logged in by checking value of the
        variable "logged", if it's true, then
        go to MainActivity otherwise skip.
        Check goToMainActivity() method for flag explanation.
         */
        if(sp.getBoolean("logged", false)){
            goToMainActivity(1);
        }

        // Set up the login form.
        mUserName = (EditText) findViewById(R.id.txtUserName);
        mPassword = (EditText) findViewById(R.id.txtPassword);

        /*
        Sign in button object is created.
        When button is clicked, the onClick method
        is invoked.
         */
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get data from user
                String userName = mUserName.getText().toString();
                String pswd = mPassword.getText().toString();

                /*
                Check if the username and password entered by the
                user matches any user  and pass in the database using a for loop.
                If it matches goToMainActivity() method is invoked with flag = 0 and
                shared preference variable "logged" is set to true and we break out of loop.
                 */
                int i;
                for(i = 0; i < data.user.size(); i++){
                    if(userName.equalsIgnoreCase(data.user.get(i)) && pswd.equals(data.password.get(i))){
                        //sharedPreference
                        goToMainActivity(0);
                        sp.edit().putBoolean("logged", true).apply();
                        break;
                    }
                }
                /*
                If there is no match and goToMainActivity() is not invoked,
                then we check if we reached the end of out database list
                to ensure that user has failed.
                We make a toast in which we ask user to try again and we set i back to 0
                to start again.
                 */
                if(i == data.user.size()){
                    Toast.makeText(LoginActivity.this, "Please try again", Toast.LENGTH_LONG).show();
                    i = 0;
                }

            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /*
    Create intent to move from LoginActivity to MainActivity.
    Start the intent.
    If the flag argument is 0 then login was successful is displayed.
    Purpose of flag is to only display "Login was successful" when we log in rather than
    displaying it every time the app is opened.
     */
    private void goToMainActivity(int flag){
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        if(flag == 0){
            Toast.makeText(LoginActivity.this, "Login was successful", Toast.LENGTH_LONG).show();
        }
        /*
        Prevent app from going back to LoginActivity when back button is tapped.
        Source: https://stackoverflow.com/questions/8631095/how-to-prevent-going-back-to-the-previous-activity
         */
        finish();
    }
}

