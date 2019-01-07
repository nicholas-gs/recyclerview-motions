package com.example.user.recyclerviewanimationstransitions.ItemAnimations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.recyclerviewanimationstransitions.EmailModel;
import com.example.user.recyclerviewanimationstransitions.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAnimRVAdapter extends RecyclerView.Adapter<ItemAnimRVAdapter.ItemAnimViewHolder> {

    public interface cardViewClickedListener{
        void onCardViewClicked(int position);
    }

    private cardViewClickedListener listener;

    public void setCardViewClickedListener(cardViewClickedListener listener){
        this.listener = listener;
    }

    private Context context;
    private ArrayList<EmailModel> emailModelArrayList;

    public ItemAnimRVAdapter(Context context, ArrayList<EmailModel> emailModelArrayList) {
        this.context = context;
        this.emailModelArrayList = emailModelArrayList;
    }

    public class ItemAnimViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private CircleImageView profilePicImageView;
        private ImageView starImageView;
        private TextView fromTextView, subjectTextView, messageTextView, timeStampTextView;

        public ItemAnimViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.emailcardview_container);
            profilePicImageView = itemView.findViewById(R.id.emailcardview_imageview);
            starImageView = itemView.findViewById(R.id.emailcardview_star);
            fromTextView = itemView.findViewById(R.id.emailcardview_from);
            subjectTextView = itemView.findViewById(R.id.emailcardview_subject);
            messageTextView = itemView.findViewById(R.id.emailcardview_message);
            timeStampTextView = itemView.findViewById(R.id.emailcardview_timestamp);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCardViewClicked(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemAnimViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.email_cardview_layout,viewGroup,false);
        return new ItemAnimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAnimViewHolder itemAnimViewHolder, int i) {
        EmailModel currentEmail = emailModelArrayList.get(i);

        itemAnimViewHolder.profilePicImageView.setImageResource(R.drawable.profile_pic);

        itemAnimViewHolder.fromTextView.setText(currentEmail.getFrom());
        itemAnimViewHolder.subjectTextView.setText(currentEmail.getSubject());
        itemAnimViewHolder.messageTextView.setText(currentEmail.getMessage());
        itemAnimViewHolder.timeStampTextView.setText(currentEmail.getTimeStamp());

        if(currentEmail.getIsImportant()){
            itemAnimViewHolder.starImageView.setImageResource(R.drawable.ic_star_yellow_24dp);
        }
        else{
            itemAnimViewHolder.starImageView.setImageResource(R.drawable.ic_star_border_grey_24dp);
        }

    }

    @Override
    public int getItemCount() {
        return emailModelArrayList.size();
    }
}
