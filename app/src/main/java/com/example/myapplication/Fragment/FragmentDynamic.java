package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.RecyclerView.MyAdapterDynamic;
import com.example.myapplication.R;

@SuppressLint("ValidFragment")
public class FragmentDynamic extends Fragment {
    private static final String TAG = "FragmentDynamic----->";
    private RecyclerView mRV;
    private MyAdapterDynamic adapterDynamic;
    private IOnClickListener iOnClickListener;

    @SuppressLint("ValidFragment")
    public FragmentDynamic(IOnClickListener i) {
        this.iOnClickListener = i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickListener.OnClick(v);
            }
        };
        adapterDynamic = new MyAdapterDynamic(getContext(), new MyAdapterDynamic.IOnDynamicClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.imageButton_recycler_dynamic_user_head:
                        Log.e(TAG, "OnClick: user head");
                        break;
                    case R.id.textView_recycler_dynamic_user_name:
                        Log.e(TAG, "OnClick: user name");
                        break;
                    case R.id.textView_recycler_dynamic_user_content:
                        Log.e(TAG, "OnClick: content");
                        break;
                    case R.id.imageButton_recycler_dynamic_comment:
                        Log.e(TAG, "OnClick: comment");
                        break;
                    case R.id.textView_recycler_dynamic_comment_num:
                        Log.e(TAG, "OnClick: comment num");
                        break;
                    case R.id.imageButton_recycler_dynamic_good:
                        Log.e(TAG, "OnClick: good");
                        break;
                    case R.id.tickerView_recycler_dynamic_good_num:
                        Log.e(TAG, "OnClick: good num");
                        break;
                    case R.id.imageButton_recycler_dynamic_share:
                        Log.e(TAG, "OnClick: share");
                        break;
                }
            }
        });
        mRV = view.findViewById(R.id.recyclerView_fragment_dynamic);
        mRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRV.setAdapter(adapterDynamic);
    }


    public interface IOnClickListener {
        void OnClick(View view);
    }
}
