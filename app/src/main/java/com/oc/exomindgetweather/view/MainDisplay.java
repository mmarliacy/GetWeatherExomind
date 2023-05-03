package com.oc.exomindgetweather.view;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.fragment.LoadingFragment;
import com.oc.exomindgetweather.utils.ProgressBarAnimation;

public class MainDisplay extends AppCompatActivity {

    //-----------
    // VARIABLES
    //-----------
    private final int counter = 0;
    TextView percent;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);
        launchFragment(LoadingFragment.newInstance());
        configureProgressBar();
    }

    public void launchFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void configureIndicator() throws InterruptedException {
        TextView indicator = findViewById(R.id.indicator);
        String[] messageList = new String[]{
                getString(R.string.message_1),
                getString(R.string.message_2),
                getString(R.string.message_3)};
        for (String message : messageList) {
            indicator.setText(message);
        }
    }

    private void configureProgressBar() {
        percent = findViewById(R.id.percent_txt);
        progressBar = findViewById(R.id.progress_bar);
        ProgressBarAnimation anim = new ProgressBarAnimation(MainDisplay.this, progressBar, percent, 0, 100);
        anim.setDuration(60000);
        progressBar.startAnimation(anim);

    }
}