package www.jinke.com.charmhome.ui.lock;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.DeviceListBean;
import www.jinke.com.charmhome.presenter.lock.DevicePassPresenter;
import www.jinke.com.charmhome.ui.dialog.LockBluetoothDialog;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.utils.PasswdUtils;
import www.jinke.com.charmhome.view.lock.IDevicePassView;
import www.jinke.com.charmhome.widget.PayPsdInputView;

/**
 * Created by root on 17-12-12.
 * 修改设备密码
 */

public class DevicePassActivity extends BaseActivity implements IDevicePassView,
        View.OnClickListener, LockBluetoothDialog.onIKnowListener {

    private DevicePassPresenter presenter;
    private PayPsdInputView ed_unlock_pass;
    private Button btn_lock_pass_sure;
    private Button btn_lock_pass_clear;
    private ACache aCache;
    private DeviceListBean deviceListBean;
    private boolean isOver;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_device_pass;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.charmHome_lock_device_pass_title));
        setRightVisibility(isOver ? "跳过" : "");
        setBackVisibility(isOver ? false : true);
        if (LockAddDeviceActivity.addDevice != null) {
            LockAddDeviceActivity.addDevice.finish();
        }
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        aCache = ACache.get(this);
        deviceListBean = (DeviceListBean) aCache.getAsObject("deviceBean");
        isOver = getIntent().getBooleanExtra("isOver", false);
        presenter = new DevicePassPresenter(this);
        ed_unlock_pass = findViewById(R.id.ed_unlock_pass);
        btn_lock_pass_sure = findViewById(R.id.btn_lock_pass_sure);
        btn_lock_pass_clear = findViewById(R.id.btn_lock_pass_clear);
        btn_lock_pass_clear.setOnClickListener(this);
        btn_lock_pass_sure.setOnClickListener(this);
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
        if (view.getId() == R.id.btn_lock_pass_sure) {
            String pwd = ed_unlock_pass.getPasswordString();
            if (pwd.length() != 6 || deviceListBean == null) {
                showToast(getString(R.string.charmHome_lock_device_pass_sex));
                return;
            }

            if (!PasswdUtils.equalStr(pwd) && !PasswdUtils.isOrderNumeric(pwd)&&!PasswdUtils.isOrderNumeric_(pwd)) {
                LockBluetoothDialog dialog = new LockBluetoothDialog(this);
                dialog.setOnIKnowListener(this);
                dialog.show();
            } else {
                showToast(getString(R.string.charmHome_lock_device_pass_simple));
                return;
            }
        } else if (view.getId() == R.id.btn_lock_pass_clear) {
            ed_unlock_pass.setText("");
        }
    }

    @Override
    public void IKowBack() {
        showLoading("正在更新设备密码");
        presenter.OpenPwdUpdateSimple(deviceListBean.getLockmac(), "", deviceListBean.getLockPasswordstate() + "", ed_unlock_pass.getPasswordString());
    }

    @Override
    public void onSuccess(String success) {
        hideDialog();
        showToast(success);
        if (isOver && LockAddDeviceActivity.addDevice != null) {
            LockAddDeviceActivity.addDevice.finish();
        }
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isOver) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showMsg(String s) {
        showToast(s);
        hideDialog();
    }

}
