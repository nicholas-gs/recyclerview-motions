package com.example.user.recyclerviewanimationstransitions.SplitTransitions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.user.recyclerviewanimationstransitions.DataRetriever;
import com.example.user.recyclerviewanimationstransitions.EmailModel;
import com.example.user.recyclerviewanimationstransitions.R;

import java.util.ArrayList;

public class SplitHomeFragment extends Fragment implements SplitRVAdapter.onEmailClickedListener {

    private RecyclerView recyclerView;
    private SplitRVAdapter adapter;
    private Context context;
    private ArrayList<EmailModel> emailModelArrayList = new ArrayList<>();
    private String url;
    private RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activitysplit_home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        url = context.getString(R.string.email_json_url);
        requestQueue = Volley.newRequestQueue(context);
        recyclerView = view.findViewById(R.id.activitysplit_main_recyclerview);

        setUpRecyclerView();
        adapter.setOnEmailClickedListener(this);

    }

    private void setUpRecyclerView() {
        Log.d("MYTAG", "setUpRecyclerView: Called");
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        adapter = new SplitRVAdapter(context, emailModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        DataRetriever.parseJsonToAdapter(url, adapter, emailModelArrayList, requestQueue,null);
    }

    @Override
    public void onEmailClicked(int position, android.support.transition.Transition exitTransition) {
        EmailModel currentEmailModel = emailModelArrayList.get(position);
        setBundle(currentEmailModel);
        exitTransition.setDuration(400);
        this.setExitTransition(exitTransition);
        //this.setReenterTransition(new Slide());
    }

    /**
     * Pass attributes of email *currentEmailModel* object to the emailDetail fragment
     *
     * @param currentEmailModel
     */
    private void setBundle(EmailModel currentEmailModel) {
        Log.d("MYTAG", "setBundle: Method called");
        boolean isImportant = currentEmailModel.getIsImportant();
        String from = currentEmailModel.getFrom();
        String subject = currentEmailModel.getSubject();
        String message = currentEmailModel.getMessage();
        String timeStamp = currentEmailModel.getTimeStamp();

        EmailDetailFragment emailDetailFragment = new EmailDetailFragment();
        Bundle bundle = new Bundle();

        bundle.putBoolean("isImportant", isImportant);
        bundle.putString("from", from);
        bundle.putString("subject", subject);
        bundle.putString("message", message);
        bundle.putString("timeStamp", timeStamp);

        emailDetailFragment.setArguments(bundle);

        setEnterTransition(emailDetailFragment);

        // Commit fragment transaction
        getFragmentManager().beginTransaction().setReorderingAllowed(true)
                .replace(R.id.splitactivity_layout_container,
                        emailDetailFragment).addToBackStack("x").commit();
    }

    /**
     * Set the animations/transition for entering fragment
     *
     * @param enterTransition
     */
    private void setEnterTransition(Fragment enterTransition) {
        android.support.transition.Slide slide = new android.support.transition.Slide();
        android.support.transition.Fade fade = new android.support.transition.Fade();

        slide.setPropagation(null);
        fade.setPropagation(null);
        android.support.transition.TransitionSet enterTransitionSet = new android.support.transition.TransitionSet();
        enterTransitionSet.addTransition(slide);
        enterTransitionSet.addTransition(fade);
        enterTransitionSet.setInterpolator(new DecelerateInterpolator());
        enterTransitionSet.setDuration(400);
        enterTransition.setEnterTransition(enterTransitionSet);
        enterTransition.setReturnTransition(null);
    }

}
