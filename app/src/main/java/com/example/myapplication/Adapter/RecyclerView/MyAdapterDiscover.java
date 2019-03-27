package com.example.myapplication.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Util.GlobalUtil;
import com.makeramen.roundedimageview.RoundedImageView;

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
        viewHolder.mIVUserHead.setOnClickListener(onClickListener);
        // TODO: 2019/3/12 可以不给图片设置点击事件动画效果不同
        viewHolder.mIV.setOnClickListener(onClickListener);
        viewHolder.mTvUserName.setOnClickListener(onClickListener);
        viewHolder.mTvType.setOnClickListener(onClickListener);
        viewHolder.mTvTitle.setOnClickListener(onClickListener);
        viewHolder.mCV.setOnClickListener(onClickListener);

        viewHolder.mTvUserName.setText(GlobalUtil.USER_NAMES[i]);
        viewHolder.mTvTitle.setText(GlobalUtil.TITLES[i]);
        switch (i) {
            case 0:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead1);
                viewHolder.mIV.setImageResource(R.drawable.cover1);
                break;
            case 1:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead2);
                viewHolder.mIV.setImageResource(R.drawable.cover2);
                break;
            case 2:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead3);
                viewHolder.mIV.setImageResource(R.drawable.cover3);
                break;
            case 3:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead4);
                viewHolder.mIV.setImageResource(R.drawable.cover4);
                break;
            case 4:
                viewHolder.mIVUserHead.setImageResource(R.drawable.userhead5);
                viewHolder.mIV.setImageResource(R.drawable.cover5);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView mIVUserHead;
        private ImageView mIV;
        private TextView mTvType, mTvTitle, mTvUserName;
        private CardView mCV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIVUserHead = itemView.findViewById(R.id.roundedImageButton_recycler_discover_user_head);
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
        return 5;
    }

    public interface IOnDiscoverClickListener {
        void OnClick(View view);
    }


}
