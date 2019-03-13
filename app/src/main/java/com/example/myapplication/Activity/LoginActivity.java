package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.youth.xframe.widget.XLoadingDialog;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity----->";

    private Button mBtnLogin, mBtnRegister, mBtnForgetPassWord;
    private ImageButton mIBExit, mIBQQ, mIBWeChat, mIBWeiBo;
    private MaterialEditText mMETUserName, mMETUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        MyOnClick myOnClick = new MyOnClick();
        mBtnLogin.setOnClickListener(myOnClick);
        mBtnRegister.setOnClickListener(myOnClick);
        mBtnForgetPassWord.setOnClickListener(myOnClick);
        mIBExit.setOnClickListener(myOnClick);
        mMETUserName.setOnClickListener(myOnClick);
        mMETUserPassword.setOnClickListener(myOnClick);
        mIBQQ.setOnClickListener(myOnClick);
        mIBWeChat.setOnClickListener(myOnClick);
        mIBWeiBo.setOnClickListener(myOnClick);

    }

    private void initView() {
        setContentView(R.layout.activity_login);
        mBtnLogin = findViewById(R.id.button_login_login);
        mBtnRegister = findViewById(R.id.button_login_register);
        mBtnForgetPassWord = findViewById(R.id.button_login_forget_password);
        mIBExit = findViewById(R.id.imageButton_login_exit);
        mMETUserName = findViewById(R.id.materialEditText_login_user_name);
        mMETUserPassword = findViewById(R.id.materialEditText_login_user_password);
        mIBQQ = findViewById(R.id.imageButton_login_qq);
        mIBWeChat = findViewById(R.id.imageButton_login_weChat);
        mIBWeiBo = findViewById(R.id.imageButton_login_wei_bo);
    }

    class MyOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO: 2019/3/13 重写点击事件
            switch (v.getId()) {
                case R.id.button_login_login:
                    final XLoadingDialog loadingDialog = new XLoadingDialog(LoginActivity.this);
                    final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismiss();
                            startActivity(intent);
                        }
                    };
                    handler.postDelayed(runnable, 2000);
                    loadingDialog.setCancelable(false);
                    loadingDialog.show();
                    Log.e(TAG, "onClick: login");
                    break;
                case R.id.button_login_register:
                    Log.e(TAG, "onClick: register");
                    break;
                case R.id.button_login_forget_password:
                    Log.e(TAG, "onClick: forget password");
                    break;
                case R.id.imageButton_login_exit:
                    Log.e(TAG, "onClick: exit");
                    finish();
                    break;
                case R.id.materialEditText_login_user_name:
                    Log.e(TAG, "onClick: user name");
                    break;
                case R.id.materialEditText_login_user_password:
                    Log.e(TAG, "onClick: user password");
                    break;
                case R.id.imageButton_login_qq:
                    Log.e(TAG, "onClick: qq");
                    break;
                case R.id.imageButton_login_weChat:
                    Log.e(TAG, "onClick: weChat");
                    break;
                case R.id.imageButton_login_wei_bo:
                    Log.e(TAG, "onClick: wei bo");
                    break;
            }
        }
    }
}
