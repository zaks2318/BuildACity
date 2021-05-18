package com.example.buildacity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        StringBuilder msg = new StringBuilder("Select the button to start \n");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String username = "User";

        if(b != null){

            if (b.containsKey(Constants.KEY_USER_NAME)){
                username = b.getString(Constants.KEY_USER_NAME);
            }

        }

        msg.append(username).append("\n");
        Log.i(TAG, new StringBuilder("").append(username).toString());

        textView.setText(msg);
    }

    public void finish(View view){
        finish();
    }
}