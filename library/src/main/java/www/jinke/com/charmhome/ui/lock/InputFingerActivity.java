package www.jinke.com.charmhome.ui.lock;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.DeviceListBean;
import www.jinke.com.charmhome.bean.FingerListBean;
import www.jinke.com.charmhome.bean.LockUserDeviceListBean;
import www.jinke.com.charmhome.bean.RequestFingerInputBean;
import www.jinke.com.charmhome.presenter.lock.InputFingerPresenter;
import www.jinke.com.charmhome.ui.dialog.LockBluetoothDialog;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;
import www.jinke.com.charmhome.view.lock.IInputFingerView;

/**
 * Created by root on 17-12-12.
 */

public class InputFingerActivity extends BaseActivity implements IInputFingerView,
        View.OnClickListener, LockBluetoothDialog.onIKnowListener {
    private Button btn_finger_start;
    private InputFingerPresenter presenter;
    private DeviceListBean deviceListBean;
    private ACache aCache;
    private ImageView img_input_finger;
    private boolean isOver;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_input_finger;
    }

    @Override
    protected void initView() {
        presenter = new InputFingerPresenter(this, this);
        setTitleText(getString(R.string.charmHome_lock_device_input_finger_title));
        setRightVisibility(isOver ? "跳过" : "");
        setBackVisibility(isOver ? false : true);
        if (deviceListBean != null) {
            presenter.getLoadUserListOnDevice(deviceListBean.getLockmac());
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
        Intent intent = new Intent(InputFingerActivity.this, DevicePassActivity.class);
        intent.putExtra("isOver", true);
        startActivity(intent);
        finish();
    }

    @Override
    protected void findViewById() {
        aCache = ACache.get(this);
        isOver = getIntent().getBooleanExtra("isOver", false);
        btn_finger_start = findViewById(R.id.btn_finger_start);
        btn_finger_start.setOnClickListener(this);
        deviceListBean = (DeviceListBean) aCache.getAsObject("deviceBean");
        img_input_finger = findViewById(R.id.img_input_finger);
    }


    LockBluetoothDialog bluetoothDialog;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_finger_start) {
            bluetoothDialog = new LockBluetoothDialog(this);
            bluetoothDialog.setOnIKnowListener(this);
            bluetoothDialog.show();
        }
    }

    @Override
    public void IKowBack() {
        //开始录制指纹
        if (bluetoothDialog != null) {
            bluetoothDialog.dismiss();
        }
        //添加指纹信息
        if (lockUserDeviceListBean != null && deviceListBean != null) {
            getAddFinger(lockUserDeviceListBean, deviceListBean);
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

    private LockUserDeviceListBean lockUserDeviceListBean;

    @Override
    public void onLoadUserListOnDevice(List<LockUserDeviceListBean> lockUserDeviceListBeans) {
        if (lockUserDeviceListBeans.size() > 0 && deviceListBean != null) {
            lockUserDeviceListBean = lockUserDeviceListBeans.get(0);
            presenter.getBLEOpenEnableDisable(deviceListBean.getLockmac(), deviceListBean.getManageaccount());
        }
    }

    @Override
    public void onInputStart(String msg) {
        showToast(msg);
        num = 0;
    }

    int num = 0;

    @Override
    public void onCollectting(int number) {
        num += number;
        switch (num) {
            case 1:
                img_input_finger.setBackgroundResource(R.drawable.icon_lock_fingerprint_one);
                break;
            case 2:
                img_input_finger.setBackgroundResource(R.drawable.icon_lock_fingerprint_one);
                break;
            case 4:
                img_input_finger.setBackgroundResource(R.drawable.icon_lock_fingerprint_five);
                break;
            case 6:
                img_input_finger.setBackgroundResource(R.drawable.icon_lock_fingerprint_two);
                break;
            case 8:
                img_input_finger.setBackgroundResource(R.drawable.icon_lock_fingerprint_four);
                break;
            case 9:
                img_input_finger.setBackgroundResource(R.drawable.icon_lock_fingerprint_four);
                break;
            case 10:
                img_input_finger.setBackgroundResource(R.drawable.icon_lock_fingerprint_four);
                break;
        }
    }

    @Override
    public void onCollectFinish(String msg) {

    }

    @Override
    public void onInputSuccessOffline(List<FingerListBean> fingerListBeans) {
        if (fingerListBeans.size() > 0) {
            FingerListBean listBean = fingerListBeans.get(0);
            Intent intent = new Intent(this, InputFingerFinishActivity.class);
            intent.putExtra("listBean", listBean);
            intent.putExtra("isOver", isOver);
            startActivity(intent);
            finish();
        }

        if (!isOver) {

        } else {
            Intent intent = new Intent(InputFingerActivity.this, InputFingerFinishActivity.class);
//            Intent intent = new Intent(InputFingerActivity.this, DevicePassActivity.class);
//            intent.putExtra("isOver", true);
//            startActivity(intent);
//            finish();
        }
    }

    @Override
    public void showMsg(String s) {
        showToast(s);
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

    public void getAddFinger(LockUserDeviceListBean lockUserDeviceListBean, DeviceListBean deviceListBean) {
        RequestFingerInputBean inputBean = new RequestFingerInputBean();
        inputBean.setMacAddress(deviceListBean.getLockmac());//设备mac地址
        inputBean.setSoftwareVersion(deviceListBean.getSoftwareVersion());//设备软件版本号
        String meterType = (String) SharedPreferencesUtils.init(this).get("lock_Meter_type", "");
        inputBean.setMeterType(meterType);//设备型号
        inputBean.setUsedFinderNum(deviceListBean.getFingeruserdnum() + "");//设备上已经使用的指纹数量
        inputBean.setLoginUserMobile(lockUserDeviceListBean.getManageaccount());//登录用户的账号
        inputBean.setUserAccount(lockUserDeviceListBean.getUseraccount());//指纹归属用户的账号
        inputBean.setUserNickName(lockUserDeviceListBean.getNickname());//指纹归属用户的昵称
        inputBean.setUserTimeRange(lockUserDeviceListBean.getUsertimeset());//指纹归属用户的时效
        inputBean.setUserLoveAlarmFlag(lockUserDeviceListBean.getLoveAccountFlag() + "");//指纹归属用户的亲情报警标志
        inputBean.setUserOpenType(lockUserDeviceListBean.getUseropentype() + "");//指纹归属用户的开门方式(非手机开门方式)
        presenter.getFingerInput(inputBean);
    }
}
