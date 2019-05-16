package com.example.user.recyclerviewanimationstransitions.SlideFade;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.recyclerviewanimationstransitions.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SlideFadeRVAdapter extends RecyclerView.Adapter<SlideFadeRVAdapter.SlideFadeViewHolder> {

    private Context context;
    private ArrayList<CardItemModel> items;

    public SlideFadeRVAdapter(Context context, ArrayList<CardItemModel> items) {
        this.context = context;
        this.items = items;
    }

    public class SlideFadeViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView imageView;
        private RelativeLayout relativeLayout;
        private TextView title, datetime, message;

        public SlideFadeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_item_imageview);
            title = itemView.findViewById(R.id.card_item_title);
            datetime = itemView.findViewById(R.id.card_item_datetime);
            message = itemView.findViewById(R.id.card_item_message);
            relativeLayout = itemView.findViewById(R.id.card_item_relativelayout);
        }
    }

    @NonNull
    @Override
    public SlideFadeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, viewGroup, false);
        return new SlideFadeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideFadeViewHolder slideFadeViewHolder, int i) {
        CardItemModel current = items.get(i);
        slideFadeViewHolder.title.setText(current.getTitle());
        slideFadeViewHolder.datetime.setText(current.getDatatime());
        slideFadeViewHolder.message.setText(current.getMessage());
        slideFadeViewHolder.imageView.setImageResource(current.getImage());

        slideFadeViewHolder.imageView.setAnimation(AnimationUtils.loadAnimation(context,
                R.anim.fade_transition));
        slideFadeViewHolder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(context,
                R.anim.fade_scale));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
