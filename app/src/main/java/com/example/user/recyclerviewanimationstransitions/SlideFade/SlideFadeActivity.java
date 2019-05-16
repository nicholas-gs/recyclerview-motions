package com.example.user.recyclerviewanimationstransitions.SlideFade;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.recyclerviewanimationstransitions.R;

import java.util.ArrayList;

public class SlideFadeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<CardItemModel> items = new ArrayList<>();
    private SlideFadeRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_fade);

        setupData();
        recyclerView = findViewById(R.id.slidefade_rv);

        setupRV();
    }

    private void setupRV(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new SlideFadeRVAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }

    private void setupData(){
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));
        items.add(new CardItemModel("Title", "15/05/2019", getString(R.string.dummy_text),
                R.drawable.profile_pic));

    }
}
