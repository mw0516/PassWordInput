package com.ldoublem.passWordInput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ldoublem.passWordInput.view.PwdGestureView;
import com.ldoublem.passWordInput.view.PwdInputView;

public class MainActivity extends AppCompatActivity {
    PwdGestureView mPwdGestureView;
    PwdInputView mPwdInputView;
    TextView tv_pwd, tv_input_pwd;
    Switch mSw_line, mSw_show;
    RadioButton rb_icon, rb_text, rb_pwd;
    RadioGroup rg_RadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPwdGestureView = (PwdGestureView) findViewById(R.id.pwd_view);
        mPwdInputView = (PwdInputView) findViewById(R.id.pwd_input_view);
        tv_pwd = (TextView) findViewById(R.id.tv_pwd);
        tv_input_pwd = (TextView) findViewById(R.id.tv_input_pwd);
        mSw_line = (Switch) findViewById(R.id.sw_line);
        mSw_show = (Switch) findViewById(R.id.sw_show);
        rb_icon = (RadioButton) findViewById(R.id.rb_icon);
        rb_text = (RadioButton) findViewById(R.id.rb_text);
        rb_pwd = (RadioButton) findViewById(R.id.rb_pwd);
        rg_RadioGroup = (RadioGroup) findViewById(R.id.rg_RadioGroup);

        mPwdGestureView.setIsDrawLine(mSw_line.isChecked());

        mSw_line.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPwdGestureView.setIsDrawLine(isChecked);

            }
        });
        mSw_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rb_icon.setEnabled(isChecked);
                rb_text.setEnabled(isChecked);
                rb_pwd.setEnabled(isChecked);
                showPwd(isChecked);
            }
        });
        rb_text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showPwd(mSw_show.isChecked());
            }
        });
        rb_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showPwd(mSw_show.isChecked());
            }
        });
        rb_icon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showPwd(mSw_show.isChecked());
            }
        });

        mPwdGestureView.startWork(new PwdGestureView.GetPwd() {
            @Override
            public void onGetPwd(String pwd) {
                tv_pwd.setText(pwd);
            }
        });
        mPwdInputView.setShadowPasswords(mSw_show.isChecked());
        mPwdInputView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_input_pwd.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void showPwd(boolean show) {

        if (show) {
            if (rb_icon.isChecked()) {
                mPwdInputView.setShadowPasswords(show, R.mipmap.icon_pwd);
            } else if (rb_text.isChecked()) {
                mPwdInputView.setShadowPasswords(show, "密");
            } else if (rb_pwd.isChecked()) {
                mPwdInputView.setShadowPasswords(show);
            }
        } else {
            mPwdInputView.setShadowPasswords(show);

        }


    }


}
