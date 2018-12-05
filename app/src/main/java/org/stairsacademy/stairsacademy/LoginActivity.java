package org.stairsacademy.stairsacademy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    private DemoDatabase ddb = new DemoDatabase();

    // UI references.
    private EditText mPassword, mUserName;
    private View mProgressView;
    private View mLoginFormView;
    private DemoDatabase data = new DemoDatabase();
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //set up sharedPreferences
        sp = getSharedPreferences("login", MODE_PRIVATE);

        //Check if logged in
        if(sp.getBoolean("logged", false)){
            goToMainActivity(1);
        }

        // Set up the login form.
        mUserName = (EditText) findViewById(R.id.txtUserName);
        mPassword = (EditText) findViewById(R.id.txtPassword);


        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get data from user
                String userName = mUserName.getText().toString();
                String pswd = mPassword.getText().toString();

                //if valid, then move to main activity
                int i;
                for(i = 0; i < data.user.size(); i++){
                    if(userName.equalsIgnoreCase(data.user.get(i)) && pswd.equals(data.password.get(i))){
                        //sharedPreference
                        goToMainActivity(0);
                        sp.edit().putBoolean("logged", true).apply();
                        break;
                    }
                }
                if(i == data.user.size()){
                    Toast.makeText(LoginActivity.this, "Please try again", Toast.LENGTH_LONG).show();
                    i = 0;
                }

            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void goToMainActivity(int flag){
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        if(flag == 0){
            Toast.makeText(LoginActivity.this, "Login was successful", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}

