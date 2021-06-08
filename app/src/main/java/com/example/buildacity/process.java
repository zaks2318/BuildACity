package com.example.buildacity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.example.buildacity.CountdownFragment.TIMEGOAL;

public class process extends AppCompatActivity {
    FragmentManager manager;
    String timeGoal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        manager = getSupportFragmentManager();

        if(b != null){

            if (b.containsKey(Constants.KEY_TIMEGOAL)){
                timeGoal = b.getString(Constants.KEY_TIMEGOAL);
            }

        }

        Bundle bundle = new Bundle();
        bundle.putString(TIMEGOAL, timeGoal);

        CountdownFragment fragment = new CountdownFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, "fragA");
        transaction.commit();
    }

    public void finish(View view) {
        finish();
    }
}
