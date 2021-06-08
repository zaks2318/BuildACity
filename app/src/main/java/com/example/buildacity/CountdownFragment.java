package com.example.buildacity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class CountdownFragment extends Fragment {
    private TextView mTextViewCountDown;
    static final String TIMEGOAL = "timegoal";

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis, oneFour, twoFour, threeFour;
    private String time, achievement;
    int quarter, quarterS, half, halfS, three4, three4S;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);
        time = getArguments().getString(TIMEGOAL);

        RelativeLayout rlayout = (RelativeLayout) view.findViewById(R.id.mainlayout);
        rlayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goFail();
            }

        });

        mTimeLeftInMillis = Long.valueOf(time);
        oneFour = (long)(mTimeLeftInMillis * 0.75);
        twoFour = (long)(mTimeLeftInMillis * 0.5);
        threeFour = (long)(mTimeLeftInMillis * 0.25);
        mTextViewCountDown = view.findViewById(R.id.text_view_countdown);
        imageView = view.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.images);
        startTimer();
        return  view;
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        quarter = (int) (oneFour / 1000) / 60;
        quarterS = (int) (oneFour / 1000) % 60;
        half = (int) (twoFour / 1000) / 60;
        halfS = (int) (twoFour / 1000) % 60;
        three4 = (int) (threeFour / 1000) / 60;
        three4S= (int) (threeFour / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);

        if(minutes == quarter & seconds == quarterS){
            imageView.setImageResource(R.drawable.begin);
        }

        if(minutes == half  & seconds == halfS){
            imageView.setImageResource(R.drawable.middle);
        }

        if(minutes == three4 & seconds == three4S){
            imageView.setImageResource(R.drawable.almost);
        }

        if(minutes == 00 & seconds == 00){
            Toast.makeText(this.getContext(),"Good Job! Well Done!!", Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.wellplay);
            goback();
        }
    }

    private void goback() {
        Intent intent4 = new Intent(this.getContext(),MainActivity.class);
        startActivity(intent4);
    }

    private  void goFail(){
        Intent intent6 = new Intent(this.getContext(),failView.class);
        startActivity(intent6);
    }
}