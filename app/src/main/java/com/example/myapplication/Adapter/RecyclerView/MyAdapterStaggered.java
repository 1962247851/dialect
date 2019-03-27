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

public class MyAdapterStaggered extends RecyclerView.Adapter<MyAdapterStaggered.ViewHolder> {

    private Context context;
    private IOnStaggeredClickListener iOnStaggeredClickListener;

    public MyAdapterStaggered(Context context, IOnStaggeredClickListener i) {
        this.context = context;
        this.iOnStaggeredClickListener = i;
    }

    @NonNull
    @Override
    public MyAdapterStaggered.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_staggered, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterStaggered.ViewHolder viewHolder, final int i) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnStaggeredClickListener.OnClick(v, i);
            }
        };
        viewHolder.mIVUserHead.setOnClickListener(onClickListener);
        viewHolder.mTvTitle.setOnClickListener(onClickListener);
        viewHolder.cardView.setOnClickListener(onClickListener);
        viewHolder.mTvTitle.setText(GlobalUtil.TITLES[i]);
        switch (i){
            case 0:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover1);
                break;
            case 1:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover2);
                break;
            case 2:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover3);
                break;
            case 3:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover4);
                break;
            case 4:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover5);
                break;
            case 5:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover6);
                break;
            case 6:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover7);
                break;
            case 7:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover8);
                break;
            case 8:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover9);
                break;
            case 9:
                viewHolder.mIVUserHead.setImageResource(R.drawable.cover10);
                break;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIVUserHead;
        private TextView mTvTitle;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIVUserHead = itemView.findViewById(R.id.imageView_recycler_staggered);
            mTvTitle = itemView.findViewById(R.id.textView_recycler_staggered_title);
            cardView = itemView.findViewById(R.id.cardView_recycler_view_staggered);
        }
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public interface IOnStaggeredClickListener {
        void OnClick(View v, int position);
    }
}
