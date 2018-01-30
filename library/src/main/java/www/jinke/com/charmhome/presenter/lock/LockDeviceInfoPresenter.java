package www.jinke.com.charmhome.presenter.lock;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.impl.LockDeviceInfoImpl;
import www.jinke.com.charmhome.listener.lock.ILockDeviceInfoListener;
import www.jinke.com.charmhome.view.lock.ILockDeviceInfoView;

/**
 * Created by root on 17-12-12.
 */

public class LockDeviceInfoPresenter implements ILockDeviceInfoListener {
    private Context mContext;
    private ILockDeviceInfoView deviceInfoView;
    private LockDeviceInfoImpl lockDeviceInfo;

    public LockDeviceInfoPresenter(Context mContext, ILockDeviceInfoView deviceInfoView) {
        this.mContext = mContext;
        this.deviceInfoView = deviceInfoView;
        lockDeviceInfo = new LockDeviceInfoImpl();
    }

    public void logoutDevice(String mac, String meterType, String softwareVersion) {
        if (deviceInfoView != null) {
            deviceInfoView.showLoading("正在注销设备");
            lockDeviceInfo.logoutDevice(mac, meterType, softwareVersion, this);
        }
    }

    @Override
    public void onLogOut(String s) {
        if (deviceInfoView != null) {
            deviceInfoView.hideLoading();
            deviceInfoView.onLogOutSuccess(s);
        }
    }

    @Override
    public void onLogOutFail(String s) {
        if (deviceInfoView != null) {
            deviceInfoView.hideLoading();
            deviceInfoView.onLogOutFail(s);
        }
    }
}
