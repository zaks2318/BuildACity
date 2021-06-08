package com.example.buildacity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.String.valueOf;

public class gameActivity extends AppCompatActivity {
    TimePickerDialog _timePickerDialog;
    EditText timeInput;
    String time;
    int hour, mints;
    long goalTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        timeInput = findViewById(R.id.timeInputL);

        timeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_TimePickerDialog();
            }
        });

    }

    public void onButtonSelectTimeClick(View view) {
        open_TimePickerDialog();
    }

    private void open_TimePickerDialog(){
        int hourOfDay=2;
        int minute=2;
        boolean is24HourView=true;

        _timePickerDialog = new TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                //*Return values
                timeInput.setText(i + ":" + i1);
                hour = i;
                mints = i1;
                Toast.makeText(gameActivity.this, "hour = " + i + "minuts" + i1, Toast.LENGTH_SHORT).show();

            }
        },hourOfDay,minute,is24HourView);
        _timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _timePickerDialog.setTitle("Select a Time");
        _timePickerDialog.show();
    }

    public boolean checkTime() {
        String timeText = timeInput.getText().toString().trim();
        if (timeText.isEmpty()) {
            timeInput.setError("Email can't be empty");
            return false;
        } else {
            timeInput.setError(null);
            return true;
        }
    }

    public void goProgress(View view) {
        boolean isTimeValid = checkTime();
        if (!isTimeValid) {
            return;
        }
        Intent intent3 = new Intent(this,process.class);
        goalTime = (hour * 3600000) + (mints * 60000);
        time = valueOf(goalTime);
        intent3.putExtra(Constants.KEY_TIMEGOAL, time);
        startActivity(intent3);
    }
}
