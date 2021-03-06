package com.example.myapplication.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.myapplication.R;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DubbingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class DubbingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "DubbingFragment----->";


    private ImageButton mIBBack, mIBReStart, mIBStart, mIBFinish;
    private TickerView mTVCountDownNum;
    private VideoView mVV;
    private IOnClickListener iOnClickListener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @SuppressLint("ValidFragment")
    public DubbingFragment(IOnClickListener i) {
        this.iOnClickListener = i;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DubbingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DubbingFragment newInstance(String param1, String param2) {
        DubbingFragment fragment = new DubbingFragment(null);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dubbing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MyOnClick myOnClick = new MyOnClick();
        mTVCountDownNum = view.findViewById(R.id.tickerView_dubbing_count_down_num);
        mTVCountDownNum.setCharacterLists(TickerUtils.provideNumberList());
        mIBBack = view.findViewById(R.id.imageButton_dubbing_back);
        mIBReStart = view.findViewById(R.id.imageButton_dubbing_restart);
        mIBStart = view.findViewById(R.id.imageButton_dubbing_start);
        mIBFinish = view.findViewById(R.id.imageButton_dubbing_finish);
        mVV = view.findViewById(R.id.videoView_dubbing);

        mTVCountDownNum.setOnClickListener(myOnClick);
        mIBBack.setOnClickListener(myOnClick);
        mIBReStart.setOnClickListener(myOnClick);
        mIBStart.setOnClickListener(myOnClick);
        mIBFinish.setOnClickListener(myOnClick);

    }

    public interface IOnClickListener {
        // TODO: 2019/3/11 改变参数
        void OnClick(View view);
    }

    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            iOnClickListener.OnClick(v);
        }
    }

}
