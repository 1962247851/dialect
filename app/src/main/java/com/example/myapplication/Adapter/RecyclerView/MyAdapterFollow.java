package com.example.myapplication.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class MyAdapterFollow extends RecyclerView.Adapter<MyAdapterFollow.ViewHolder> {

    private Context context;
    private IOnFollowClickListener iOnFollowClickListener;

    public MyAdapterFollow(Context context, IOnFollowClickListener i) {
        this.context = context;
        this.iOnFollowClickListener = i;
    }

    @NonNull
    @Override
    public MyAdapterFollow.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_follow, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterFollow.ViewHolder viewHolder, final int i) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnFollowClickListener.OnClick(v,i);
            }
        };
        viewHolder.mIVUserHead.setOnClickListener(onClickListener);
        switch (i) {
            case 0:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead5);
                break;
            case 1:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead3);
                break;
            case 2:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead2);
                break;
            case 3:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead1);
                break;
            case 4:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead4);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView mIVUserHead;
        private View mVNew;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIVUserHead = itemView.findViewById(R.id.roundedImageView_recycler_follow_user_head);
            mVNew = itemView.findViewById(R.id.view_recycler_follow_new);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public interface IOnFollowClickListener {
        void OnClick(View v,int position);
    }
}
