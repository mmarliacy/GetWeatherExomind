package com.oc.exomindgetweather.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.databinding.ActivityHomeMenuBinding;

public class HomeMenu extends AppCompatActivity {

    //-----------
    // VARIABLES
    //-----------
    ActivityHomeMenuBinding fHomeMenuBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fHomeMenuBinding = ActivityHomeMenuBinding.inflate(getLayoutInflater());
        setContentView(fHomeMenuBinding.getRoot());
        startLoadingActivity();
    }

    // 1 -- Display loading fragment -->
    private void startLoadingActivity(){
        Button varGoBtn = findViewById(R.id.go_btn);
        varGoBtn.setOnClickListener(v-> startActivity(new Intent(this, MainDisplay.class)));
    }
}