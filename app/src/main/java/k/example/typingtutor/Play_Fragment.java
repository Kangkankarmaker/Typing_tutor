package k.example.typingtutor;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
public class Play_Fragment extends Fragment {

    private static final long START_TIME_IN_MILLIS = 6000;
    private TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    final static int RQS_1 = 1;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private Button btnSeeRes;
    TextView txtView1,txtView2,txtView3,txtView4;
    EditText extText1,extText2,extText3,extText4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtView1=view.findViewById(R.id.txt1);
        txtView2=view.findViewById(R.id.txt2);
        txtView3=view.findViewById(R.id.txt3);
        txtView4=view.findViewById(R.id.txt4);
        extText1=view.findViewById(R.id.ext_input_1);
        extText2=view.findViewById(R.id.ext_input_2);
        extText3=view.findViewById(R.id.ext_input_3);
        extText4=view.findViewById(R.id.ext_input_4);


        btnSeeRes= view.findViewById(R.id.btn_seeResult);
        btnSeeRes.setOnClickListener(v -> {
            String one=extText1.getText().toString().trim();
            String two=extText2.getText().toString().trim();
            String three=extText3.getText().toString().trim();
            String four=extText4.getText().toString().trim();

            Bundle args = new Bundle();
            args.putString("one", one);
            args.putString("two", two);
            args.putString("three", three);
            args.putString("four", four);
            Result_Fragment result_fragment = new Result_Fragment();
            result_fragment .setArguments(args);

            Navigation.findNavController(view).navigate(R.id.action_play_Fragment_to_result_Fragment,args);
        });

        mTextViewCountDown =view. findViewById(R.id.text_view_countdown);
        startTimer();

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
                startAlarm();
                btnSeeRes.setVisibility(View.VISIBLE);
                txtView1.setVisibility(View.GONE);
                txtView2.setVisibility(View.GONE);
                txtView3.setVisibility(View.GONE);
                txtView4.setVisibility(View.GONE);
                extText1.setVisibility(View.GONE);
                extText2.setVisibility(View.GONE);
                extText3.setVisibility(View.GONE);
                extText4.setVisibility(View.GONE);
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void startAlarm() {
        Intent intent = new Intent(getContext(), MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), RQS_1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
    }



}