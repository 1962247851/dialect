package com.example.myapplication.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class MyAdapterDynamic extends RecyclerView.Adapter<MyAdapterDynamic.ViewHolder> {

    private static final String TAG = "DynamicAdapter----->";

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
    public void onBindViewHolder(@NonNull final MyAdapterDynamic.ViewHolder viewHolder, final int i) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnDynamicClickListener.OnClick(v);
                // TODO: 2019/3/16 评论和分享对话框
                switch (v.getId()) {
                    case R.id.imageButton_recycler_dynamic_comment:
                        Log.e(TAG, "onClick: comment");
                        break;
                    case R.id.imageButton_recycler_dynamic_good:
                        if (!viewHolder.Gooded) {
                            viewHolder.Gooded = true;
                            viewHolder.mTVGoodNum.setText(String.valueOf(Integer.valueOf(viewHolder.mTVGoodNum.getText()) + 1));
                        } else {
                            viewHolder.Gooded = false;
                            viewHolder.mTVGoodNum.setText(String.valueOf(Integer.valueOf(viewHolder.mTVGoodNum.getText()) - 1));
                        }
                        // TODO: 2019/3/16
                        //notifyDataSetChanged();
                        notifyItemChanged(i);
                        break;
                }
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
        viewHolder.mCV.setOnClickListener(onClickListener);
        viewHolder.mIV.setOnClickListener(onClickListener);

        viewHolder.mTVGoodNum.setText(i+"");
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton mIBUserHead, mIBComment, mIBGood, mIBShare;
        private TextView mTvUserName, mTvContent, mTvCommentNum;
        private TickerView mTVGoodNum;
        private ImageView mIV;
        private CardView mCV;
        private Boolean Gooded = false;

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
            mIV = itemView.findViewById(R.id.imageView_recycler_dynamic);
            mCV = itemView.findViewById(R.id.cardView_recycler_view_dynamic);
            mTVGoodNum.setCharacterLists(TickerUtils.provideNumberList());
        }
    }

    //总共的评论数
    @Override
    public int getItemCount() {
        return 5;
    }

    public interface IOnDynamicClickListener {
        void OnClick(View view);
    }

}
