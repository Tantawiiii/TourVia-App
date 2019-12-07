package com.example.tourism.Data;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.R;
import com.squareup.picasso.Picasso;

public class FirebaseViewDataHolder extends RecyclerView.ViewHolder {

    private View mView;
    private FirebaseViewDataHolder.ClickListener mClick;

    public FirebaseViewDataHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClick.onItemClick(view, getAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClick.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context ctx, String name, String image, String description) {

        TextView mName = mView.findViewById(R.id.nameforitem);
        TextView mDescription = mView.findViewById(R.id.descriptionforitem);
        ImageView mImage = mView.findViewById(R.id.imageforitem);

        mName.setText(name);
        mDescription.setText(description);

        Picasso.with(ctx).load(image).into(mImage);

    }

    public void setOnClickListener(FirebaseViewDataHolder.ClickListener mClickListener) {

        mClick = mClickListener;
    }

    public interface ClickListener {
        void onItemClick(View view, int postion);

        void onItemLongClick(View view, int postion);
    }

}





















