package www.jinke.com.charmhome.ui.activity;

import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 17-11-3.
 */

public class GatewayActivity extends BaseActivity implements View.OnClickListener {

    Button btn_netWork_connect;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_gateway;
    }

    @Override
    protected void findViewById() {
        btn_netWork_connect = findViewById(R.id.btn_netWork_connect);
    }

    @Override
    protected void initView() {
        setTitleText("热点连接");
        setRightVisibility(false);
        btn_netWork_connect.setOnClickListener(this);

    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_netWork_connect) {
//            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
//            startActivity(intent);
            startActivity(new Intent(GatewayActivity.this,AddDeviceActivity.class));
        }
    }
}
