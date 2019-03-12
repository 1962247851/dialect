package com.example.myapplication.Activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.myapplication.R;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";
    private ImageButton mIBMore, mIBSend;
    private EditText mET;
    private LinearLayout mLLMore;
    private InputMethodManager inputMethodManager;
    private int height = 200;
    private int bottomHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        MyOnClick myOnClick = new MyOnClick();
        mIBMore.setOnClickListener(myOnClick);
        mIBSend.setOnClickListener(myOnClick);
        mET.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                ChatActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int screenHeight = ChatActivity.this.getWindow().getDecorView().getRootView().getHeight();
                int heightDifference = screenHeight - rect.bottom;
                Log.e(TAG, "onGlobalLayout: " + heightDifference);
                //刚进入界面获取底部高度
                if (heightDifference < 500) {
                    bottomHeight = heightDifference;
                }
                //大于本来的高度
                if (heightDifference > 200) {
                    height = heightDifference - bottomHeight;
                    mLLMore.getLayoutParams().height = height;
                }
            }
        });

    }

    private void hideInput() {
        if (inputMethodManager == null) {
            inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        }
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(mET.getWindowToken(), 0);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar_chat);
        mIBMore = findViewById(R.id.imageButton_chat_more);
        mIBSend = findViewById(R.id.imageButton_chat_send);
        mLLMore = findViewById(R.id.linearLayout_chat_more);
        mET = findViewById(R.id.editText_chat_input_message);
        // TODO: 2019/3/12
        toolbar.setTitle("用户名");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                return true;
        }
        return false;
    }

    private class MyOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageButton_chat_more:
                    hideInput();
                    mLLMore.getLayoutParams().height = height;
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
            }
        }
    }
}
