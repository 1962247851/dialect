package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.Util.SoftKeyBoardListener;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";
    private ImageButton mIBMore, mIBSend;
    private EditText mET;
    private LinearLayout mLLMore;
    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initListener();
    }

    private class MySoftKeyBoardListener implements SoftKeyBoardListener.OnSoftKeyBoardChangeListener {

        @Override
        public void keyBoardShow(int height) {
            mLLMore.getLayoutParams().height = height;
            mLLMore.setVisibility(View.GONE);
        }

        @Override
        public void keyBoardHide(int height) {
        }
    }

    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageButton_chat_more:
                    hideInput();
                    Log.e(TAG, "onClick: " + mLLMore.getVisibility() + "  " + mLLMore.getLayoutParams().height);
                    if (mLLMore.getVisibility() == View.GONE) {
                        mLLMore.setVisibility(View.VISIBLE);
                    } else {
                        mLLMore.setVisibility(View.GONE);
                    }
                    break;
                case R.id.imageButton_chat_send:
                    // TODO: 2019/3/12 重写发送事件
                    Log.e(TAG, "onClick: send");
                    break;
                case R.id.editText_chat_input_message:
                    Log.e(TAG, "onClick: edit text");
                    break;
            }
        }

    }

    private void hideInput() {
        if (inputMethodManager == null) {
            inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        }
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(mET.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_chat_call:
                Log.e(TAG, "onOptionsItemSelected: call");
                return true;
            case R.id.menu_chat_user_head:
                Log.e(TAG, "onOptionsItemSelected: user head");
                Intent intent = new Intent(ChatActivity.this, UserDetailsActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    private void initView() {
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar_chat);
        mIBMore = findViewById(R.id.imageButton_chat_more);
        mIBSend = findViewById(R.id.imageButton_chat_send);
        mLLMore = findViewById(R.id.linearLayout_chat_more);
        mET = findViewById(R.id.editText_chat_input_message);
        // TODO: 2019/3/12
        toolbar.setTitle("啊嚏uu");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initListener() {
        MyOnClick myOnClick = new MyOnClick();
        MySoftKeyBoardListener mySoftKeyBoardListener = new MySoftKeyBoardListener();
        mIBMore.setOnClickListener(myOnClick);
        mIBSend.setOnClickListener(myOnClick);
        SoftKeyBoardListener.setListener(this, mySoftKeyBoardListener);
    }
}
