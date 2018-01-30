package www.jinke.com.charmhome.ui.lock;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dsm.xiaodi.biz.sdk.servercore.ServerUnit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.bean.CharmHomeLoginBean;
import www.jinke.com.charmhome.bean.LockUserBean;
import www.jinke.com.charmhome.http.CharmHomeMethods;
import www.jinke.com.charmhome.http.HttpResult;
import www.jinke.com.charmhome.http.ProgressSubscriber;
import www.jinke.com.charmhome.http.SubscriberOnNextListener;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;

/**
 * Created by root on 17-12-14.
 */

public class InputLoginPassActivity extends BaseActivity {
    TextView tx_input_login_sure;
    EditText ed_lock_input_login_pass;
    private String account;

    private ACache aCache;
    private int resultCode;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_input_login_password;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.charmHome_lock_device_input_login_password));
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        //进入输入密码页面,但是没有登陆成功就点击返回按钮离开此页面
        Intent intent = new Intent();
        intent.putExtra("result", LuMiConfig.input_pass_result_fail);
        setResult(resultCode, intent);
        saveLoginBean("", "");
        finish();
    }

    CharmHomeLoginBean loginBean;

    @Override
    protected void findViewById() {
        aCache = ACache.get(this);
        loginBean = (CharmHomeLoginBean) aCache.getAsObject("charmHomeLogin");
        account = getIntent().getStringExtra("account");
        resultCode = getIntent().getIntExtra("code", 0);
        tx_input_login_sure = findViewById(R.id.tx_input_login_sure);
        ed_lock_input_login_pass = findViewById(R.id.ed_lock_input_login_pass);
        tx_input_login_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!StringUtils.isEmpty(ed_lock_input_login_pass.getText().toString().trim()) && loginBean != null) {
                    getLockLogin(account, ed_lock_input_login_pass.getText().toString().trim());
                    showDialog("登陆中,请稍候");
                }
            }
        });
    }

    public void getLockLogin(final String account, final String pwd) {
        ServerUnit.getInstance().login(account, pwd, null, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                LogUtils.i("login:" + list.toString());
                Type type = new TypeToken<List<LockUserBean>>() {
                }.getType();
                List<LockUserBean> userBean = new Gson().fromJson(list.get(0).toString(), type);
                if (userBean.size() > 0) {
                    SharedPreferencesUtils.init(InputLoginPassActivity.this).put("user_nickName", userBean.get(0).getNickname());
                    SharedPreferencesUtils.init(InputLoginPassActivity.this).put("user_name", userBean.get(0).getName());
                    SharedPreferencesUtils.init(InputLoginPassActivity.this).put("user_account", userBean.get(0).getAccount());
                    SharedPreferencesUtils.init(InputLoginPassActivity.this).put("user_baiduchannelid", userBean.get(0).getBaiduchannelid());
                    SharedPreferencesUtils.init(InputLoginPassActivity.this).put("user_password", userBean.get(0).getPassword());
                    SharedPreferencesUtils.init(InputLoginPassActivity.this).put("user_token", userBean.get(0).getToken());
                    aCache.put("UserBean", userBean.get(0), ACache.MAX_SIZE);
                }
                getUpdatePsw(account, pwd);
            }

            @Override
            public void failure(String s, int i) {
                ToastUtils.showShort("密码输入错误,请重新输入!");
                hideDialog();
            }
        });
    }

    public void getUpdatePsw(final String account, final String pwd) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<CharmHomeLoginBean>() {
            @Override
            public void onNext(CharmHomeLoginBean bean) {
                LogUtils.i("更新密码操作" + bean.getPhone());
                hideDialog();
                saveLoginBean(account, pwd);
                //进入如输入密码页面，完成正常登陆第三平台流程并更新服务器密码
                Intent intent = new Intent();
                intent.putExtra("result", LuMiConfig.input_pass_result_success);
                setResult(resultCode, intent);
                finish();
            }

            @Override
            public void onError(String Code, String Msg) {
                LogUtils.i("msg");
                ToastUtils.showShort(Msg);
                hideDialog();
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("phone", account);
        map.put("sessionId", loginBean.getSessionid());
        map.put("passWord", pwd);
        CharmHomeMethods.getInstance().getUpdatePsw(new ProgressSubscriber<HttpResult<CharmHomeLoginBean>>(onNextListener, this), map);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("result", LuMiConfig.input_pass_result_fail);
            setResult(resultCode, intent);
            saveLoginBean("", "");
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void saveLoginBean(String account, String pwd) {
        loginBean.setLockUser(account);
        loginBean.setLockPsw(pwd);
        aCache.put("charmHomeLogin", loginBean, ACache.MAX_SIZE);
    }
}
