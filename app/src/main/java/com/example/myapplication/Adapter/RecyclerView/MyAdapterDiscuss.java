package com.example.myapplication.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class MyAdapterDiscuss extends RecyclerView.Adapter<MyAdapterDiscuss.ViewHolder> {

    private Context context;
    private IOnDiscussClickListener iOnDiscussClickListener;

    public MyAdapterDiscuss(Context context, IOnDiscussClickListener i) {
        this.context = context;
        this.iOnDiscussClickListener = i;
    }


    @NonNull
    @Override
    public MyAdapterDiscuss.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_discuss, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterDiscuss.ViewHolder viewHolder, int i) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnDiscussClickListener.OnClick(v);
            }
        };
        viewHolder.mIBUserHead.setOnClickListener(onClickListener);
        viewHolder.mIBGood.setOnClickListener(onClickListener);
        viewHolder.mTvUserName.setOnClickListener(onClickListener);
        viewHolder.mTvUserComment.setOnClickListener(onClickListener);
        viewHolder.mTVGoodNum.setOnClickListener(onClickListener);
        viewHolder.mTVGoodNum.setCharacterLists(TickerUtils.provideNumberList());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton mIBUserHead, mIBGood;
        private TextView mTvUserName, mTvUserComment;
        private TickerView mTVGoodNum;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIBUserHead = itemView.findViewById(R.id.imageButton_recycler_discover_user_head);
            mIBGood = itemView.findViewById(R.id.imageButton_recycler_discuss_good);
            mTvUserName = itemView.findViewById(R.id.textView_recycler_discover_title);
            mTvUserComment = itemView.findViewById(R.id.textView_recycler_discuss_user_comment);
            mTVGoodNum = itemView.findViewById(R.id.tickerView_recycler_discuss_good_num);
        }
    }

    //总共的评论数
    @Override
    public int getItemCount() {
        return 30;
    }

    public interface IOnDiscussClickListener {
        void OnClick(View view);
    }
}
