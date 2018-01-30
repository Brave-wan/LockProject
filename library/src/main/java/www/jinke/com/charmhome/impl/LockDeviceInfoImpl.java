package www.jinke.com.charmhome.impl;

import com.blankj.utilcode.util.LogUtils;
import com.dsm.xiaodi.biz.sdk.business.BusinessResponse;
import com.dsm.xiaodi.biz.sdk.business.deviceinfo.Logout;

import www.jinke.com.charmhome.listener.lock.ILockDeviceInfoBiz;
import www.jinke.com.charmhome.listener.lock.ILockDeviceInfoListener;

/**
 * Created by root on 17-12-12.
 */

public class LockDeviceInfoImpl implements ILockDeviceInfoBiz {

    @Override
    public void logoutDevice(String mac, String meterType, String softwareVersion, final ILockDeviceInfoListener listener) {
        new Logout(mac, meterType, softwareVersion, new BusinessResponse() {
            @Override
            public void onSuccess(Object o) {
                LogUtils.i("注销设备:" + o);
                listener.onLogOut("注销设备成功!");
            }

            @Override
            public void onFailure(String s, int i) {
                LogUtils.i("注销设备:" + s);
                listener.onLogOutFail("注销设备失败!");

            }
        }).walk();
    }
}
