package com.example.buildacity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.welcome);
    }

    public void goToMain(View view) {
        Intent intent = new Intent(WelcomeView.this, SignUpActivity.class);
        startActivity(intent);
    }
}

