package com.example.myapplication.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Util.GlobalUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class MyAdapterDiscuss extends RecyclerView.Adapter<MyAdapterDiscuss.ViewHolder> {

    private Context context;
    private IOnDiscussClickListener iOnDiscussClickListener;
    private Boolean isDiscuss = true;

    public MyAdapterDiscuss(Context context, IOnDiscussClickListener i, Boolean isDiscuss) {
        this.context = context;
        this.iOnDiscussClickListener = i;
        this.isDiscuss = isDiscuss;
    }

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
    public void onBindViewHolder(@NonNull final MyAdapterDiscuss.ViewHolder viewHolder, final int i) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnDiscussClickListener.OnClick(v);
                switch (v.getId()) {
                    case R.id.imageButton_recycler_discuss_good:
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
        viewHolder.mRIVUserHead.setOnClickListener(onClickListener);
        viewHolder.mIBGood.setOnClickListener(onClickListener);
        viewHolder.mTvUserName.setOnClickListener(onClickListener);
        viewHolder.mTvUserComment.setOnClickListener(onClickListener);
        viewHolder.mTVGoodNum.setOnClickListener(onClickListener);
        viewHolder.mTVGoodNum.setCharacterLists(TickerUtils.provideNumberList());
        viewHolder.mCV.setOnClickListener(onClickListener);

        viewHolder.mTVGoodNum.setText(i + "");
        viewHolder.mTvUserName.setText(GlobalUtil.USER_NAMES[4 - i]);
        if (isDiscuss) {
            viewHolder.mTvUserComment.setText(GlobalUtil.USER_COMMENTS_DISCUSS[i]);
        } else {
            viewHolder.mTvUserComment.setText(GlobalUtil.USER_COMMENTS[i]);
        }
        switch (i) {
            case 0:
                viewHolder.mRIVUserHead.setImageResource(R.drawable.userhead1);
                break;
            case 1:
                viewHolder.mRIVUserHead.setImageResource(R.drawable.userhead2);
                break;
            case 2:
                viewHolder.mRIVUserHead.setImageResource(R.drawable.userhead3);
                break;
            case 3:
                viewHolder.mRIVUserHead.setImageResource(R.drawable.userhead4);
                break;
            case 4:
                viewHolder.mRIVUserHead.setImageResource(R.drawable.userhead5);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton mIBGood;
        private RoundedImageView mRIVUserHead;
        private TextView mTvUserName, mTvUserComment;
        private TickerView mTVGoodNum;
        private CardView mCV;
        private Boolean Gooded = false;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRIVUserHead = itemView.findViewById(R.id.roundedImageButton_recycler_discuss_user_head);
            mIBGood = itemView.findViewById(R.id.imageButton_recycler_discuss_good);
            mTvUserName = itemView.findViewById(R.id.textView_recycler_discuss_title);
            mTvUserComment = itemView.findViewById(R.id.textView_recycler_discuss_user_comment);
            mTVGoodNum = itemView.findViewById(R.id.tickerView_recycler_discuss_good_num);
            mCV = itemView.findViewById(R.id.cardView_recycler_view_discuss);
        }
    }

    //总共的评论数
    @Override
    public int getItemCount() {
        return 5;
    }

    public interface IOnDiscussClickListener {
        void OnClick(View view);
    }
}
