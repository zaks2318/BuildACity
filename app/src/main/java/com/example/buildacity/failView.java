package com.example.buildacity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class failView extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail_layout);
    }

    public void goBack(View view){
        Intent intent5 = new Intent(this,MainActivity.class);
        startActivity(intent5);
    }
}
