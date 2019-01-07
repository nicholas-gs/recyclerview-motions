package com.example.user.recyclerviewanimationstransitions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.user.recyclerviewanimationstransitions.ItemAnimations.ItemAnimActivity;
import com.example.user.recyclerviewanimationstransitions.SplitTransitions.SplitTransitionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button splitButton, dropDownButton, slideLeftButton, slideRightButton, pushUpButton,
            slideDownButton, slideUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splitButton = findViewById(R.id.main_btn_splittransition);
        dropDownButton = findViewById(R.id.main_btn_item_dropdown_anim);
        slideLeftButton = findViewById(R.id.main_btn_item_slideleft_anim);
        slideRightButton = findViewById(R.id.main_btn_item_slideright_anim);
        pushUpButton = findViewById(R.id.main_btn_item_pushup_anim);
        slideDownButton = findViewById(R.id.main_btn_item_slidedown_anim);
        slideUpButton = findViewById(R.id.main_btn_item_slideup_anim);

        splitButton.setOnClickListener(this);
        dropDownButton.setOnClickListener(this);
        slideLeftButton.setOnClickListener(this);
        slideRightButton.setOnClickListener(this);
        pushUpButton.setOnClickListener(this);
        slideDownButton.setOnClickListener(this);
        slideUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.main_btn_splittransition:
                intent = new Intent(this, SplitTransitionActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn_item_dropdown_anim:
                intent = new Intent(this, ItemAnimActivity.class);
                intent.putExtra(getString(R.string.transition_key), getString(R.string.transition_dropdown_value));
                startActivity(intent);
                break;
            case R.id.main_btn_item_slideleft_anim:
                intent = new Intent(this, ItemAnimActivity.class);
                intent.putExtra(getString(R.string.transition_key), getString(R.string.transition_slideleft_value));
                startActivity(intent);
                break;
            case R.id.main_btn_item_slideright_anim:
                intent = new Intent(this, ItemAnimActivity.class);
                intent.putExtra(getString(R.string.transition_key), getString(R.string.transition_slideright_value));
                startActivity(intent);
                break;
            case R.id.main_btn_item_pushup_anim:
                intent = new Intent(this, ItemAnimActivity.class);
                intent.putExtra(getString(R.string.transition_key), getString(R.string.transition_pushup_value));
                startActivity(intent);
                break;
            case R.id.main_btn_item_slidedown_anim:
                intent = new Intent(this, ItemAnimActivity.class);
                intent.putExtra(getString(R.string.transition_key), getString(R.string.transition_slidedown_value));
                startActivity(intent);
                break;
            case R.id.main_btn_item_slideup_anim:
                intent = new Intent(this, ItemAnimActivity.class);
                intent.putExtra(getString(R.string.transition_key), getString(R.string.transition_slideup_value));
                startActivity(intent);
                break;
        }
    }
}
