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

import com.example.myapplication.Adapter.RecyclerView.MyAdapterDiscover;
import com.example.myapplication.R;

@SuppressLint("ValidFragment")
public class FragmentDiscover extends Fragment {
    private static final String TAG = "FragmentDiscover----->";
    private RecyclerView mRV;
    private MyAdapterDiscover adapterDiscover;
    private IOnClickListener iOnClickListener;

    @SuppressLint("ValidFragment")
    public FragmentDiscover(IOnClickListener i) {
        this.iOnClickListener = i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
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
        adapterDiscover = new MyAdapterDiscover(getContext(), new MyAdapterDiscover.IOnDiscoverClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.imageButton_recycler_discover_user_head:
                        Log.e(TAG, "OnClick: user head");
                        break;
                    case R.id.imageView_fragment_discover:
                        Log.e(TAG, "OnClick: imageView");
                        break;
                    case R.id.textView_recycler_discover_title:
                        Log.e(TAG, "OnClick: title");
                        break;
                    case R.id.textView_recycler_discover_type:
                        Log.e(TAG, "OnClick: type");
                        break;
                    case R.id.textView_recycler_discover_user_name:
                        Log.e(TAG, "OnClick: user name");
                        break;
                    case R.id.textView_recycler_discover_date:
                        Log.e(TAG, "OnClick: data");
                        break;
                }
            }
        });
        mRV = view.findViewById(R.id.recyclerView_fragment_discover);
        mRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRV.setAdapter(adapterDiscover);
    }


    public interface IOnClickListener {
        void OnClick(View view);
    }
}
