package www.jinke.com.charmhome.ui.lock;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.DeviceListBean;
import www.jinke.com.charmhome.presenter.lock.LockDeviceInfoPresenter;
import www.jinke.com.charmhome.ui.dialog.LockBluetoothDialog;
import www.jinke.com.charmhome.ui.dialog.LogoutDialog;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.view.lock.ILockDeviceInfoView;

/**
 * Created by root on 17-12-11.
 * 设备信息
 */

public class LockDeviceInfoActivity extends BaseActivity implements ILockDeviceInfoView, View.OnClickListener,
        LogoutDialog.onLogoutListener, LockBluetoothDialog.onIKnowListener {
    DeviceListBean deviceListBean;
    TextView tx_remaining_finger;
    TextView tx_lock_fingerprint;
    TextView tx_lock_version;
    TextView tx_lock_name;
    TextView tx_lock_device_version_up;
    TextView tx_lock_device_logout;
    LockDeviceInfoPresenter presenter;
    ACache aCache;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_lock_device_info;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.charmHome_lock_device_title));
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        aCache = ACache.get(this);
        deviceListBean = (DeviceListBean) aCache.getAsObject("deviceBean");
        presenter = new LockDeviceInfoPresenter(this, this);
        deviceListBean = (DeviceListBean) getIntent().getSerializableExtra("deviceBean");
        tx_remaining_finger = findViewById(R.id.tx_lock_device_remaining_finger);
        tx_lock_fingerprint = findViewById(R.id.tx_lock_device_fingerprint);
        tx_lock_version = findViewById(R.id.tx_lock_device_version);
        tx_lock_name = findViewById(R.id.tx_lock_name);
        tx_lock_device_logout = findViewById(R.id.tx_lock_device_logout);
        tx_lock_device_version_up = findViewById(R.id.tx_lock_device_version_up);
        tx_lock_device_logout.setOnClickListener(this);
        tx_lock_device_version_up.setOnClickListener(this);

        if (deviceListBean != null) {
            tx_lock_name.setText(deviceListBean.getLockname());
            tx_lock_version.setText(deviceListBean.getSoftwareVersion());
            tx_lock_fingerprint.setText(deviceListBean.getFingeruserdnum() + "");
            tx_remaining_finger.setText(deviceListBean.getFingerunuserdnum() + "");
        }
    }

    @Override
    public void showLoading(String msg) {
        showDialog(msg);
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tx_lock_device_logout) {//注销设备
            LogoutDialog dialog = new LogoutDialog(this);
            dialog.setOnLogoutListener(this);
            dialog.show();
        }
    }

    @Override
    public void logout() {
        LockBluetoothDialog bluetoothDialog = new LockBluetoothDialog(this);
        bluetoothDialog.setOnIKnowListener(this);
        bluetoothDialog.show();
    }

    @Override
    public void IKowBack() {
        if (deviceListBean != null) {
            presenter.logoutDevice(deviceListBean.getLockmac(), deviceListBean.getMetertype(), deviceListBean.getSoftwareVersion());
        }
    }

    @Override
    public void onLogOutSuccess(String s) {
        showToast(s);
        finish();
    }

    @Override
    public void onLogOutFail(String s) {
        showToast(s);
    }

}
