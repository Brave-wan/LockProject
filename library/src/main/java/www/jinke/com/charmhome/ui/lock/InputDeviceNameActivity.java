package www.jinke.com.charmhome.ui.lock;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.LockRequestAddDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;
import www.jinke.com.charmhome.presenter.lock.InputDeviceNamePresenter;
import www.jinke.com.charmhome.ui.dialog.LockBluetoothDialog;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;
import www.jinke.com.charmhome.view.lock.IInputDeviceNameView;

/**
 * Created by root on 17-12-12.
 * <p>
 * 输入设备名称
 */

public class InputDeviceNameActivity extends BaseActivity implements IInputDeviceNameView, View.OnClickListener, LockBluetoothDialog.onIKnowListener {
    EditText ed_input_device_name;
    TextView tx_lock_device_add_sure;
    InputDeviceNamePresenter presenter;
    private ACache aCache;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_input_device_name;
    }

    @Override
    protected void initView() {
        presenter = new InputDeviceNamePresenter(this, this);
        setTitleText(getString(R.string.charmHome_lock_add_device_title));
        aCache = ACache.get(this);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        ed_input_device_name = findViewById(R.id.ed_input_device_name);
        tx_lock_device_add_sure = findViewById(R.id.tx_lock_device_add_sure);
        tx_lock_device_add_sure.setOnClickListener(this);
        ed_input_device_name.setSelection(ed_input_device_name.getText().toString().trim().length());
    }

    @Override
    public void showLoading(String msg) {
        showDialog(msg, true);
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    public void getAddDevice(String roomName) {
        LockUserBean userBean = (LockUserBean) aCache.getAsObject("UserBean");
        if (userBean != null) {
            LockRequestAddDeviceBean bean = new LockRequestAddDeviceBean();
            //登录用户手机号码
            bean.setLoginUserMobile(userBean.getAccount());
            //登录用户昵称
            bean.setLoginUserNickName(userBean.getNickname());
            //设备名称
            bean.setLocknameString(roomName);
            //设备mac地址
            bean.setLockmacString((String) SharedPreferencesUtils.init(this).get("lock_mac", ""));
            //设备管理密码，后期废弃
            bean.setManagepwdString((String) SharedPreferencesUtils.init(this).get("lock_pwd", ""));
            //设备固件类型，例如T700_0
            bean.setMetertype((String) SharedPreferencesUtils.init(this).get("lock_Meter_type", ""));
            bean.setDevicetype((String) SharedPreferencesUtils.init(this).get("lock_type", ""));
            //latitude - 设备所在维度
            bean.setLatitude("");
            //longitude - 设备所在经度
            bean.setLongitude("");
            presenter.getAddDevice(bean);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tx_lock_device_add_sure) {
            if (!StringUtils.isEmpty(ed_input_device_name.getText().toString())) {
                LockBluetoothDialog dialog = new LockBluetoothDialog(this);
                dialog.setOnIKnowListener(this);
                dialog.show();
                dialog.setContentImage(R.drawable.icon_lock_bluetooth_dialog,
                        getString(R.string.charmHome_lock_dialog_one),
                        getString(R.string.charmHome_lock_dialog_two));
            } else {
                showToast(getString(R.string.charmHome_lock_toast_input_device_name));
            }
        }
    }

    @Override
    public void addDeviceFinish() {
        //回到首页
        if (ScanningLockCodeActivity.instance != null) {
            ScanningLockCodeActivity.instance.finish();
        }
        finish();
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void startInputFinger() {
        Intent intent = new Intent(this, InputFingerActivity.class);
        intent.putExtra("isOver", true);
        startActivity(intent);
        finish();
    }

    @Override
    public void IKowBack() {
        getAddDevice(ed_input_device_name.getText().toString());
    }
}
