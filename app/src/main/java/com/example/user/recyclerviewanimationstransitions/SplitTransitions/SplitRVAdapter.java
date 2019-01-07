package com.example.user.recyclerviewanimationstransitions.SplitTransitions;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.recyclerviewanimationstransitions.EmailModel;
import com.example.user.recyclerviewanimationstransitions.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplitRVAdapter extends RecyclerView.Adapter<SplitRVAdapter.SplitRVViewHolder> {

    private Context context;
    private ArrayList<EmailModel> emailModelArrayList;
    private onEmailClickedListener listener;

    public interface onEmailClickedListener {
        void onEmailClicked(int position, android.support.transition.Transition exitTransition);
    }

    public void setOnEmailClickedListener(onEmailClickedListener listener) {
        this.listener = listener;
    }

    public SplitRVAdapter(Context context, ArrayList<EmailModel> emailModelArrayList) {
        this.context = context;
        this.emailModelArrayList = emailModelArrayList;
    }

    public class SplitRVViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout relativeLayout;
        private CircleImageView profilePicImageView;
        private TextView fromTextView, subjectTextView, messageTextView, timeStampTextView;
        private ImageView starImageView;

        public SplitRVViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.email_item_container);
            profilePicImageView = itemView.findViewById(R.id.emailitem_imageview);
            fromTextView = itemView.findViewById(R.id.emailitem_from);
            subjectTextView = itemView.findViewById(R.id.emailitem_recipient);
            messageTextView = itemView.findViewById(R.id.emailitem_message);
            timeStampTextView = itemView.findViewById(R.id.emailitem_timeStamp);
            starImageView = itemView.findViewById(R.id.emailitem_star);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final android.support.transition.Transition exitTransition = exitTransition(v);
                    listener.onEmailClicked(getAdapterPosition(), exitTransition);
                }
            });

        }
    }

    @NonNull
    @Override
    public SplitRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.email_item_layout, viewGroup, false);
        return new SplitRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SplitRVViewHolder splitRVViewHolder, int i) {
        EmailModel currentEmailModel = emailModelArrayList.get(i);

        boolean isRead = currentEmailModel.getIsRead();
        boolean isImportant = currentEmailModel.getIsImportant();

        loadIsRead(isRead, splitRVViewHolder, currentEmailModel);
        loadIsImporant(isImportant, splitRVViewHolder);

        splitRVViewHolder.profilePicImageView.setImageResource(R.drawable.profile_pic);
        splitRVViewHolder.messageTextView.setText(currentEmailModel.getMessage());
    }

    @Override
    public int getItemCount() {
        return emailModelArrayList.size();
    }

    /**
     * Sets the text, as well as text appearance depending on *isRead* boolean
     *
     * @param isRead
     * @param splitRVViewHolder
     * @param currentEmailModel
     */
    private void loadIsRead(boolean isRead, SplitRVViewHolder splitRVViewHolder, EmailModel currentEmailModel) {
        if (isRead) {
            splitRVViewHolder.subjectTextView.setTypeface(Typeface.create(splitRVViewHolder.subjectTextView.getTypeface()
                    , Typeface.NORMAL), Typeface.NORMAL);
            splitRVViewHolder.subjectTextView.setText(currentEmailModel.getSubject());

            splitRVViewHolder.fromTextView.setTypeface(Typeface.create(splitRVViewHolder.fromTextView.getTypeface()
                    , Typeface.NORMAL), Typeface.NORMAL);
            splitRVViewHolder.fromTextView.setText(currentEmailModel.getFrom());

            splitRVViewHolder.timeStampTextView.setTypeface(Typeface.create(splitRVViewHolder.timeStampTextView.getTypeface()
                    , Typeface.NORMAL), Typeface.NORMAL);
            splitRVViewHolder.timeStampTextView.setTextColor(ContextCompat.getColor(context, R.color.colorMessage));
            splitRVViewHolder.timeStampTextView.setText(currentEmailModel.getTimeStamp());
        } else {
            splitRVViewHolder.subjectTextView.setTypeface(splitRVViewHolder.subjectTextView.getTypeface(), Typeface.BOLD);
            splitRVViewHolder.subjectTextView.setText(currentEmailModel.getSubject());

            splitRVViewHolder.fromTextView.setTypeface(splitRVViewHolder.fromTextView.getTypeface(), Typeface.BOLD);
            splitRVViewHolder.fromTextView.setText(currentEmailModel.getFrom());

            splitRVViewHolder.timeStampTextView.setTypeface(splitRVViewHolder.timeStampTextView.getTypeface(), Typeface.BOLD);
            splitRVViewHolder.timeStampTextView.setTextColor(ContextCompat.getColor(context, R.color.colorTimestamp));
            splitRVViewHolder.timeStampTextView.setText(currentEmailModel.getTimeStamp());
        }
    }

    /**
     * Sets the star imageview depending on *isImportant* boolean
     *
     * @param isImportant
     * @param splitRVViewHolder
     */
    private void loadIsImporant(boolean isImportant, SplitRVViewHolder splitRVViewHolder) {
        if (isImportant) {
            splitRVViewHolder.starImageView.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            splitRVViewHolder.starImageView.setImageResource(R.drawable.ic_star_border_grey_24dp);
        }
    }

    /**
     * Sets the exit animation for the clicked recyclerview item.
     * Without this, the exit transition will always start at the middle of the screen.
     * But with this, the explode transition starts at the position of the clicked item
     *
     * @param itemView
     * @return
     */
    private android.support.transition.Transition exitTransition(View itemView) {
        final Rect rect = new Rect();
        itemView.getGlobalVisibleRect(rect);
        rect.top = rect.bottom;

        android.support.transition.Explode explode = new android.support.transition.Explode();
        explode.setPropagation(null);
        explode.setInterpolator(new DecelerateInterpolator());
        explode.setEpicenterCallback(new android.support.transition.Transition.EpicenterCallback() {
            @Override
            public Rect onGetEpicenter(@NonNull android.support.transition.Transition transition) {
                return rect;
            }
        });
        return explode;
    }
}
