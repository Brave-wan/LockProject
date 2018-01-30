package www.jinke.com.charmhome.ui.lock;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.DeviceListBean;
import www.jinke.com.charmhome.presenter.lock.DeviceAttributesPresenter;
import www.jinke.com.charmhome.view.lock.IDeviceAttributesView;

/**
 * Created by root on 17-12-11.
 */

public class DeviceAttributesActivity extends BaseActivity implements View.OnClickListener, IDeviceAttributesView {
    RelativeLayout rl_lock_device_details, rl_lock_device_pass;
    RelativeLayout rl_lock_finger_manger;

    EditText ed_lock_device_name;
    DeviceAttributesPresenter presenter;
    DeviceListBean deviceListBean;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_device_attributes;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.charmHome_lock_device_smart_lock_title));
        setRightVisibility(getString(R.string.charmHome_lock_device_attributes_finish));
        presenter = new DeviceAttributesPresenter(this, this);
        ed_lock_device_name.setSelection(ed_lock_device_name.getText().toString().trim().length());
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        if (!StringUtils.isEmpty(ed_lock_device_name.getText().toString().trim()) && deviceListBean != null) {
            presenter.updateDeviceName(deviceListBean.getLockmac(), ed_lock_device_name.getText().toString().trim());
        } else {
            showToast(getString(R.string.charmHome_lock_toast_input_device_name));
        }
    }

    @Override
    protected void findViewById() {
        deviceListBean = (DeviceListBean) getIntent().getSerializableExtra("deviceBean");
        rl_lock_device_details = findViewById(R.id.rl_lock_device_details);
        rl_lock_device_pass = findViewById(R.id.rl_lock_device_pass);
        rl_lock_finger_manger = findViewById(R.id.rl_lock_finger_manger);
        ed_lock_device_name = findViewById(R.id.ed_lock_device_name);
        rl_lock_finger_manger.setOnClickListener(this);
        rl_lock_device_pass.setOnClickListener(this);
        rl_lock_device_details.setOnClickListener(this);
        if (deviceListBean != null) {
            ed_lock_device_name.setText(deviceListBean.getLockname());
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_lock_device_details) {//设备信息
            Intent intent = new Intent(this, LockDeviceInfoActivity.class);
            intent.putExtra("deviceBean", deviceListBean);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.rl_lock_device_pass) {//修改开锁密码密码
            Intent intent = new Intent(this, DevicePassActivity.class);
            intent.putExtra("isOver", false);
            startActivity(intent);
        } else if (view.getId() == R.id.rl_lock_finger_manger) {//指纹管理
            Intent intent = new Intent(this, FingerMangerActivity.class);
            intent.putExtra("deviceBean", deviceListBean);
            startActivity(intent);
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
    public void onUpDeviceName() {
//        showToast("更新设备名更新成功!");
    }

    @Override
    public void showMsg(String s) {
        showToast(s);
    }
}
