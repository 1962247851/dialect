package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Activity.MySelfActivity;
import com.example.myapplication.Activity.UserDetailsActivity;
import com.example.myapplication.Adapter.RecyclerView.MyAdapterStaggered;
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
 * {@link AuditionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AuditionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuditionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "CompareFragment----->";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button mBtnUserHead, mBtnSearch;
    private RecyclerView recyclerView;
    private EditText editText;
    private OnFragmentInteractionListener mListener;
    private IOnClickListener iOnClickListener;

    public AuditionFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public AuditionFragment(IOnClickListener i) {
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
    public static AuditionFragment newInstance(String param1, String param2) {
        AuditionFragment fragment = new AuditionFragment();
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
        return inflater.inflate(R.layout.fragment_audition, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView_fragment_audition);
        mBtnUserHead = view.findViewById(R.id.button_fragment_audition_user_head);
        mBtnSearch = view.findViewById(R.id.button_fragment_audition_search);
        editText = view.findViewById(R.id.editText_fragment_audition_search_input);
        MyOnClick myOnClick = new MyOnClick();
        mBtnSearch.setOnClickListener(myOnClick);
        mBtnUserHead.setOnClickListener(myOnClick);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MyAdapterStaggered(getContext(), new MyAdapterStaggered.IOnStaggeredClickListener() {
            @Override
            public void OnClick(View v, int position) {
                // TODO: 2019/3/25
                Intent intent = new Intent(getContext(), UserDetailsActivity.class);
                startActivity(intent);
                switch (v.getId()) {
                    case R.id.imageView_recycler_staggered:
                        Log.e(TAG, "OnClick: imageView" + position);
                        break;
                    case R.id.textView_recycler_staggered_title:
                        Log.e(TAG, "OnClick: title" + position);
                        break;
                    case R.id.cardView_recycler_view_staggered:
                        Log.e(TAG, "OnClick: cardView" + position);
                        break;
                }
            }
        }));
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
