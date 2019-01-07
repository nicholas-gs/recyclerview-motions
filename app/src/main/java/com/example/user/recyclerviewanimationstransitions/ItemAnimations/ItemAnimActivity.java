package com.example.user.recyclerviewanimationstransitions.ItemAnimations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.user.recyclerviewanimationstransitions.DataRetriever;
import com.example.user.recyclerviewanimationstransitions.EmailModel;
import com.example.user.recyclerviewanimationstransitions.R;

import java.util.ArrayList;

public class ItemAnimActivity extends AppCompatActivity implements ItemAnimRVAdapter.cardViewClickedListener {

    private RecyclerView recyclerView;
    private ItemAnimRVAdapter adapter;
    private ArrayList<EmailModel> emailModelArrayList = new ArrayList<>();
    private RequestQueue requestQueue;
    private String animType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_animations);

        Intent incomingIntent = getIntent();
        animType = incomingIntent.getStringExtra(getString(R.string.transition_key));

        recyclerView = findViewById(R.id.activityitemanim_recyclerview);
        requestQueue = Volley.newRequestQueue(this);
        setupRecyclerView();
        setupRecyclerViewAnimations();
        DataRetriever.parseJsonToAdapter(getString(R.string.email_json_url), adapter, emailModelArrayList,
                requestQueue, recyclerView);
    }

    private void setupRecyclerView() {
        adapter = new ItemAnimRVAdapter(this, emailModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setCardViewClickedListener(this);
    }


    @Override
    public void onCardViewClicked(int position) {
        Toast.makeText(this, "Index: " + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * Setup the animations for RecyclerView
     * The trigger is in DataRetriever.parseJsonToAdapter(...) -- Must pass RecyclerView parameter to method
     */
    private void setupRecyclerViewAnimations() {

        if (animType.equals(getString(R.string.transition_dropdown_value))) {
            LayoutAnimationController animationController = android.view.animation.AnimationUtils.loadLayoutAnimation(
                    this, R.anim.layout_anim_fall_down
            );
            recyclerView.setLayoutAnimation(animationController);
        } else if (animType.equals(getString(R.string.transition_slideleft_value))) {
            LayoutAnimationController animationController = android.view.animation.AnimationUtils
                    .loadLayoutAnimation(this, R.anim.layout_anim_slide_left);
            recyclerView.setLayoutAnimation(animationController);
        } else if (animType.equals(getString(R.string.transition_slideright_value))) {
            LayoutAnimationController animationController = android.view.animation.AnimationUtils
                    .loadLayoutAnimation(this, R.anim.layout_anim_slide_right);
            recyclerView.setLayoutAnimation(animationController);
        } else if (animType.equals(getString(R.string.transition_pushup_value))) {
            LayoutAnimationController animationController = android.view.animation.AnimationUtils
                    .loadLayoutAnimation(this, R.anim.layout_anim_push_up);
            recyclerView.setLayoutAnimation(animationController);
        } else if (animType.equals(getString(R.string.transition_slideup_value))) {
            LayoutAnimationController animationController = android.view.animation.AnimationUtils
                    .loadLayoutAnimation(this, R.anim.layout_anim_slide_up);
            recyclerView.setLayoutAnimation(animationController);
        } else if (animType.equals(getString(R.string.transition_slidedown_value))) {
            LayoutAnimationController animationController = android.view.animation.AnimationUtils
                    .loadLayoutAnimation(this, R.anim.layout_anim_slide_down);
            recyclerView.setLayoutAnimation(animationController);
        }
    }
}
