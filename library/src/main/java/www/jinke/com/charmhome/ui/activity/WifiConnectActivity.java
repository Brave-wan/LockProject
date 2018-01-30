package www.jinke.com.charmhome.ui.activity;

import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.presenter.WifiConnectPresenter;
import www.jinke.com.charmhome.utils.WifiAdmin;
import www.jinke.com.charmhome.view.IWifiConnectView;
import www.jinke.com.charmhome.widget.SpotsDialog;

/**
 * Created by root on 17-11-2.
 */

public class WifiConnectActivity extends BaseActivity implements View.OnClickListener, IWifiConnectView {
    private TextView tx_wifi_name;
    private EditText ed_wifi_password;
    private Button btnNetWork;
    private SpotsDialog dialog;
    private WifiConnectPresenter presenter;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_connect_wifi;
    }

    @Override
    protected void findViewById() {
        tx_wifi_name = findViewById(R.id.tx_wifi_name);
        ed_wifi_password = findViewById(R.id.ed_wifi_password);
        btnNetWork = findViewById(R.id.btn_device_netWork);
    }

    @Override
    protected void initView() {
        setRightVisibility("",R.drawable.icon_dh_add);
        tx_wifi_name.setOnClickListener(this);
        btnNetWork.setOnClickListener(this);
        presenter = new WifiConnectPresenter(this, this);
        WifiAdmin admin = new WifiAdmin(this);
        wifiConfig(admin);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void wifiConfig(WifiAdmin admin) {
        tx_wifi_name.setText(admin.getSSID() + "");
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tx_wifi_name) {//跳转设置wifi页面
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);
        } else if (view.getId() == R.id.btn_device_netWork) {
            if (authState) {
                startActivity(new Intent(WifiConnectActivity.this, GatewayActivity.class));
            } else {
                showToast("先给网关授权入网");
            }
        }
    }

    @Override
    public void showLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new SpotsDialog(this);
        dialog.show();

    }

    @Override
    public void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private boolean authState = false;

    @Override
    public void authState(boolean state) {
        authState = state;
    }
}
