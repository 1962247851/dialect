package com.example.myapplication.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.myapplication.R;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class MyAdapterDynamic extends RecyclerView.Adapter<MyAdapterDynamic.ViewHolder> {


    private Context context;
    private IOnDynamicClickListener iOnDynamicClickListener;

    public MyAdapterDynamic(Context context, IOnDynamicClickListener i) {
        this.context = context;
        this.iOnDynamicClickListener = i;
    }


    @NonNull
    @Override
    public MyAdapterDynamic.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_dynamic, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterDynamic.ViewHolder viewHolder, int i) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnDynamicClickListener.OnClick(v);
            }
        };
        viewHolder.mIBUserHead.setOnClickListener(onClickListener);
        viewHolder.mIBComment.setOnClickListener(onClickListener);
        viewHolder.mIBGood.setOnClickListener(onClickListener);
        viewHolder.mIBShare.setOnClickListener(onClickListener);
        viewHolder.mTvUserName.setOnClickListener(onClickListener);
        viewHolder.mTvContent.setOnClickListener(onClickListener);
        viewHolder.mTVGoodNum.setOnClickListener(onClickListener);
        viewHolder.mTvCommentNum.setOnClickListener(onClickListener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton mIBUserHead, mIBComment, mIBGood, mIBShare;
        private TextView mTvUserName, mTvContent, mTvCommentNum;
        private TickerView mTVGoodNum;
        private VideoView mVV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIBUserHead = itemView.findViewById(R.id.imageButton_recycler_dynamic_user_head);
            mIBComment = itemView.findViewById(R.id.imageButton_recycler_dynamic_comment);
            mIBGood = itemView.findViewById(R.id.imageButton_recycler_dynamic_good);
            mIBShare = itemView.findViewById(R.id.imageButton_recycler_dynamic_share);
            mTvUserName = itemView.findViewById(R.id.textView_recycler_dynamic_user_name);
            mTVGoodNum = itemView.findViewById(R.id.tickerView_recycler_dynamic_good_num);
            mTvContent = itemView.findViewById(R.id.textView_recycler_dynamic_user_content);
            mTvCommentNum = itemView.findViewById(R.id.textView_recycler_dynamic_comment_num);
            mVV = itemView.findViewById(R.id.videoView_recycler_dynamic);
            mTVGoodNum.setCharacterLists(TickerUtils.provideNumberList());
        }
    }

    //总共的评论数
    @Override
    public int getItemCount() {
        return 15;
    }

    public interface IOnDynamicClickListener {
        void OnClick(View view);
    }

}
