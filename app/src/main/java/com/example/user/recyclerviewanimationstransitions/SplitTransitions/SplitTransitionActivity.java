package com.example.user.recyclerviewanimationstransitions.SplitTransitions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.user.recyclerviewanimationstransitions.R;

public class SplitTransitionActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        frameLayout = findViewById(R.id.splitactivity_layout_container);

        if(savedInstanceState == null){
            Log.d("MYTAG", "onCreate: Commit");
            getSupportFragmentManager().beginTransaction().replace(R.id.splitactivity_layout_container,
                    new SplitHomeFragment()).commit();
        }
    }
}
