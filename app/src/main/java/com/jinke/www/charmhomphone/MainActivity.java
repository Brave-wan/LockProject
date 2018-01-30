package com.jinke.www.charmhomphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.ui.activity.CharmHomeActivity;
import www.jinke.com.charmhome.ui.activity.ScannerCodeActivity;
import www.jinke.com.charmhome.ui.activity.StartPageActivity;
import www.jinke.com.charmhome.ui.lock.DeviceAttributesActivity;
import www.jinke.com.charmhome.ui.lock.InputFingerActivity;
import www.jinke.com.charmhome.ui.lock.InputFingerFinishActivity;
import www.jinke.com.charmhome.ui.lock.LockAddFailActivity;
import www.jinke.com.charmhome.ui.lock.LockAddFinishActivity;
import www.jinke.com.charmhome.ui.lock.LockDeviceInfoActivity;

public class MainActivity extends AppCompatActivity {
    Button tx_hello_word;
    EditText ed_login_user;
    EditText ed_login_pwd;
    EditText ed_login_house_name, ed_login_house_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx_hello_word = findViewById(R.id.tx_hello_word);
        ed_login_pwd = findViewById(R.id.ed_login_pwd);
        ed_login_user = findViewById(R.id.ed_login_user);
        ed_login_house_name = findViewById(R.id.ed_login_house_name);
        ed_login_house_id = findViewById(R.id.ed_login_house_id);

        tx_hello_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = ed_login_user.getText().toString().trim();
                String userPass = ed_login_pwd.getText().toString().trim();

                if (!StringUtils.isEmpty(userName)) {
                    LuMiConfig.ACCOUNT = userName;
                    LuMiConfig.PASSWORD = userPass;
                    LuMiConfig.HOURSEID = ed_login_house_id.getText().toString().trim();
                    LuMiConfig.HOURSENAME = ed_login_house_name.getText().toString().trim();
                    startActivity(new Intent(MainActivity.this, StartPageActivity.class));
                } else {
                    ToastUtils.showShort("用户名或密码不能为空!");
                }
            }
        });
    }
}
