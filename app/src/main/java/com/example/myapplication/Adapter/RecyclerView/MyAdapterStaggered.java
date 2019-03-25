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
        if (i%2==0){
            viewHolder.mIVUserHead.setImageResource(R.drawable.banner1);
        }else {
            viewHolder.mIVUserHead.setImageResource(R.drawable.adapter_loading_error);
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
        return 30;
    }

    public interface IOnStaggeredClickListener {
        void OnClick(View v, int position);
    }
}
