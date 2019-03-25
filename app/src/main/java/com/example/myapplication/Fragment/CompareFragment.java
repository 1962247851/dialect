package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Activity.DubbingDetailsActivity;
import com.example.myapplication.Activity.MySelfActivity;
import com.example.myapplication.Adapter.ViewPager.MyFragmentPagerAdapterCompare;
import com.example.myapplication.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompareFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CompareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompareFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "CompareFragment----->";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private Button mBtnUserHead, mBtnSearch;
    private EditText editText;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private OnFragmentInteractionListener mListener;
    private IOnClickListener iOnClickListener;

    public CompareFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public CompareFragment(IOnClickListener i) {
        this.iOnClickListener = i;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompareFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompareFragment newInstance(String param1, String param2) {
        CompareFragment fragment = new CompareFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compare, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.viewPager_fragment_compare);
        slidingTabLayout = view.findViewById(R.id.tabLayout_fragment_compare);
        mBtnUserHead = view.findViewById(R.id.button_fragment_compare_user_head);
        mBtnSearch = view.findViewById(R.id.button_fragment_compare_search);
        editText = view.findViewById(R.id.editText_fragment_compare_search_input);
        MyOnClick myOnClick = new MyOnClick();
        mBtnSearch.setOnClickListener(myOnClick);
        mBtnUserHead.setOnClickListener(myOnClick);
        //设置fragment的监听器
        FragmentStaggeredNewest.IStaggeredListeners iStaggeredListeners1 = new FragmentStaggeredNewest.IStaggeredListeners() {
            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
                    case Refreshing:
//                        Handler handler = new Handler();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                refreshLayout.finishRefresh(true);
//                            }
//                        };
//                        handler.postDelayed(runnable, 1000);
                        refreshLayout.finishRefresh(1000, true);
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        FragmentStaggeredPopular.IStaggeredListeners iStaggeredListeners2 = new FragmentStaggeredPopular.IStaggeredListeners() {
            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
                    case Refreshing:
//                        Handler handler = new Handler();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                refreshLayout.finishRefresh(true);
//                            }
//                        };
//                        handler.postDelayed(runnable, 1000);
                        refreshLayout.finishRefresh(1000, true);
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        FragmentStaggeredMovie.IStaggeredListeners iStaggeredListeners3 = new FragmentStaggeredMovie.IStaggeredListeners() {
            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
                    case Refreshing:
//                        Handler handler = new Handler();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                refreshLayout.finishRefresh(true);
//                            }
//                        };
//                        handler.postDelayed(runnable, 1000);
                        refreshLayout.finishRefresh(1000, true);
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        FragmentStaggeredComic.IStaggeredListeners iStaggeredListeners4 = new FragmentStaggeredComic.IStaggeredListeners() {
            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
                    case Refreshing:
//                        Handler handler = new Handler();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                refreshLayout.finishRefresh(true);
//                            }
//                        };
//                        handler.postDelayed(runnable, 1000);
                        refreshLayout.finishRefresh(1000, true);
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        FragmentStaggeredTV.IStaggeredListeners iStaggeredListeners5 = new FragmentStaggeredTV.IStaggeredListeners() {
            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
                    case Refreshing:
//                        Handler handler = new Handler();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                refreshLayout.finishRefresh(true);
//                            }
//                        };
//                        handler.postDelayed(runnable, 1000);
                        refreshLayout.finishRefresh(1000, true);
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        FragmentStaggeredCartoon.IStaggeredListeners iStaggeredListeners6 = new FragmentStaggeredCartoon.IStaggeredListeners() {
            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
                    case Refreshing:
//                        Handler handler = new Handler();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                refreshLayout.finishRefresh(true);
//                            }
//                        };
//                        handler.postDelayed(runnable, 1000);
                        refreshLayout.finishRefresh(1000, true);
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        FragmentStaggeredOriginal.IStaggeredListeners iStaggeredListeners7 = new FragmentStaggeredOriginal.IStaggeredListeners() {
            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
                    case Refreshing:
//                        Handler handler = new Handler();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                refreshLayout.finishRefresh(true);
//                            }
//                        };
//                        handler.postDelayed(runnable, 1000);
                        refreshLayout.finishRefresh(1000, true);
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        FragmentStaggeredNewest fragmentStaggeredNewest = new FragmentStaggeredNewest(iStaggeredListeners1);
        FragmentStaggeredPopular fragmentStaggeredPopular = new FragmentStaggeredPopular(iStaggeredListeners2);
        FragmentStaggeredMovie fragmentStaggeredMovie = new FragmentStaggeredMovie(iStaggeredListeners3);
        FragmentStaggeredComic fragmentStaggeredComic = new FragmentStaggeredComic(iStaggeredListeners4);
        FragmentStaggeredTV fragmentStaggeredTV = new FragmentStaggeredTV(iStaggeredListeners5);
        FragmentStaggeredCartoon fragmentStaggeredCartoon = new FragmentStaggeredCartoon(iStaggeredListeners6);
        FragmentStaggeredOriginal fragmentStaggeredOriginal = new FragmentStaggeredOriginal(iStaggeredListeners7);

        fragments.add(fragmentStaggeredNewest);
        fragments.add(fragmentStaggeredPopular);
        fragments.add(fragmentStaggeredMovie);
        fragments.add(fragmentStaggeredComic);
        fragments.add(fragmentStaggeredTV);
        fragments.add(fragmentStaggeredCartoon);
        fragments.add(fragmentStaggeredOriginal);
        viewPager.setAdapter(new MyFragmentPagerAdapterCompare(getChildFragmentManager(), fragments));
        slidingTabLayout.setViewPager(viewPager);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface IOnClickListener {
        // TODO: 2019/3/25 改变参数
        void OnClick(View view);
    }

    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_fragment_compare_user_head:
                    // TODO: 2019/3/25
                    Log.e(TAG, "onClick: User Head");
                    Intent intent = new Intent(getContext(), MySelfActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_fragment_compare_search:
                    // TODO: 2019/3/25
                    Log.e(TAG, "onClick: Search");
                    Toast.makeText(getContext(), editText.getText(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
