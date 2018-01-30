package www.jinke.com.charmhome.ui.lock;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 17-12-11.
 * 添加门锁设备
 */

public class LockAddDeviceActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout rl_lock_device;
    public static Activity addDevice;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_lock_add_device;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.charmHome_add_device));
        addDevice = this;
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        rl_lock_device = findViewById(R.id.rl_add_lock_device);
        rl_lock_device.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_add_lock_device) {
            startActivity(new Intent(LockAddDeviceActivity.this, ScanningLockCodeActivity.class));

        }

    }
}
