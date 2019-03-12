package com.example.myapplication.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class MyAdapterDiscover extends RecyclerView.Adapter<MyAdapterDiscover.ViewHolder> {


    private Context context;
    private IOnDiscoverClickListener iOnDiscoverClickListener;

    public MyAdapterDiscover(Context context, IOnDiscoverClickListener i) {
        this.context = context;
        this.iOnDiscoverClickListener = i;
    }


    @NonNull
    @Override
    public MyAdapterDiscover.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_discover, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterDiscover.ViewHolder viewHolder, int i) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnDiscoverClickListener.OnClick(v);
            }
        };
        viewHolder.mIBUserHead.setOnClickListener(onClickListener);
        // TODO: 2019/3/12 可以不给图片设置点击事件动画效果不同
        viewHolder.mIV.setOnClickListener(onClickListener);
        viewHolder.mTvUserName.setOnClickListener(onClickListener);
        viewHolder.mTvType.setOnClickListener(onClickListener);
        viewHolder.mTvTitle.setOnClickListener(onClickListener);
        viewHolder.mCV.setOnClickListener(onClickListener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton mIBUserHead;
        private ImageView mIV;
        private TextView mTvType, mTvTitle, mTvUserName;
        private CardView mCV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIBUserHead = itemView.findViewById(R.id.imageButton_recycler_discover_user_head);
            mIV = itemView.findViewById(R.id.imageView_fragment_discover);
            mTvType = itemView.findViewById(R.id.textView_recycler_discover_type);
            mTvTitle = itemView.findViewById(R.id.textView_recycler_discover_title);
            mTvUserName = itemView.findViewById(R.id.textView_recycler_discover_user_name);
            mCV = itemView.findViewById(R.id.cardView_recycler_view_discover);
        }

    }

    //总共的评论数
    @Override
    public int getItemCount() {
        return 15;
    }

    public interface IOnDiscoverClickListener {
        void OnClick(View view);
    }


}
