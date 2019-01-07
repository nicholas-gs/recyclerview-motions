package com.example.user.recyclerviewanimationstransitions.SplitTransitions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.recyclerviewanimationstransitions.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmailDetailFragment extends Fragment {

    // Widgets
    private Toolbar toolbar;
    private CircleImageView profilePic;
    private TextView fromTextView, subjectTextView, timeStampTextView, messageTextView;
    private ImageView starImageView;

    //Incoming bundle parameters
    private boolean isImportant;
    private String subject, from, message, timeStamp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getIncomingBundle();
        return inflater.inflate(R.layout.email_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Override the activity toolbar's menu with the fragments own menu
        setHasOptionsMenu(true);

        initialiseWidgets(view);
        setupToolbar();

        setWidgets();
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        /**
         * Detect when the entering transition has been completed
         */
        android.support.transition.Transition transitionSet = (android.support.transition.TransitionSet) getEnterTransition();
        if (transitionSet != null) {

            transitionSet.addListener(new android.support.transition.Transition.TransitionListener() {
                @Override
                public void onTransitionStart(@NonNull android.support.transition.Transition transition) {

                }

                @Override
                public void onTransitionEnd(@NonNull android.support.transition.Transition transition) {
                    transition.removeListener(this);
                    // FadeIn animation for the message textview
                    Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                    fadeIn.setDuration(750);
                    messageTextView.setText(message);
                    messageTextView.setAnimation(fadeIn);
                }

                @Override
                public void onTransitionCancel(@NonNull android.support.transition.Transition transition) {

                }

                @Override
                public void onTransitionPause(@NonNull android.support.transition.Transition transition) {

                }

                @Override
                public void onTransitionResume(@NonNull android.support.transition.Transition transition) {

                }
            });
        }
    }

    private void initialiseWidgets(View view) {
        toolbar = view.findViewById(R.id.emaildetail_toolbar);
        profilePic = view.findViewById(R.id.emaildetail_imageview);
        fromTextView = view.findViewById(R.id.emaildetail_from);
        subjectTextView = view.findViewById(R.id.emaildetail_subject);
        timeStampTextView = view.findViewById(R.id.emaildetail_timestamp);
        starImageView = view.findViewById(R.id.emaildetail_star);
        messageTextView = view.findViewById(R.id.emaildetail_message);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.email_detail_menu, menu);
    }

    /**
     * Setup toolbar
     * Add navigate back functionality
     */
    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).setTitle(null);
        toolbar.setNavigationIcon(R.drawable.ic_close_grey_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    /**
     * Get parameters from incoming bundle
     */
    private void getIncomingBundle() {
        Log.d("MYTAG", "getIncomingBundle: Method called");
        try {
            isImportant = getArguments().getBoolean("isImportant");
            Log.d("MYTAG", "getIncomingBundle: isImportant" + isImportant);
            subject = getArguments().getString("subject");
            Log.d("MYTAG", "getIncomingBundle: subject" + subject);
            from = getArguments().getString("from");
            message = getArguments().getString("message");
            timeStamp = getArguments().getString("timeStamp");

        } catch (Exception e) {
            Log.d("MYTAG", "getIncomingBundle: exception called");
            Log.d("MYTAG", "getIncomingBundle: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Set widgets with variables
     */
    private void setWidgets() {
        Log.d("MYTAG", "setWidgets: Method called");
        if (isImportant) {
            starImageView.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            starImageView.setImageResource(R.drawable.ic_star_border_grey_24dp);
        }

        subjectTextView.setText(subject);
        fromTextView.setText(from);
        timeStampTextView.setText(timeStamp);

        //Message is animated above in the onTransitionEnd() method
        /* messageTextView.setText(message);*/
    }

}
