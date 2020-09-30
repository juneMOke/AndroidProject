package com.example.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUserName;
    private int mScore;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private static final String USER_NAME_KEY = "firstName";
    private  static final String USER_SCORE_KEY = "score";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_text);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mPlayButton.setEnabled(false);

        setPreferences();


        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() > 0);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName = new User (mNameInput.getText().toString());
                Intent intent = new Intent(MainActivity.this, Display2Activity.class);
                //startActivity(intent);
                startActivityForResult(intent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    private void setPreferences() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String firstname = preferences.getString(USER_NAME_KEY,null);
        int score = preferences.getInt(USER_SCORE_KEY,0);
        if (firstname != null) {
            mGreetingText.setText("welcome Back " + firstname + " ! Will you do better? your last score was : " + score);
            mNameInput.setText(firstname);
            mPlayButton.setEnabled(true);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            mScore = data.getIntExtra(Display2Activity.SCORE_KEY_VALUE,0);
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(USER_NAME_KEY,mUserName.getFirstName());
            editor.putInt(USER_SCORE_KEY, mScore);
            editor.apply();
            setPreferences();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onStart() {
        System.out.println("MainActivity : onStart() ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        System.out.println("MainActivity : OnResume() ");
        super.onResume();
    }

    @Override
    protected void onStop() {
        System.out.println("MainActivity : onStop() ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("MainActivity : onDestroy() ");
        super.onDestroy();

    }
}