package www.jinke.com.charmhome.impl;

import com.blankj.utilcode.util.LogUtils;
import com.dsm.xiaodi.biz.sdk.servercore.ServerUnit;

import java.util.List;

import www.jinke.com.charmhome.listener.lock.IDeviceAttributesBiz;
import www.jinke.com.charmhome.listener.lock.IDeviceAttributesListener;

/**
 * Created by root on 17-12-12.
 */

public class DeviceAttributesImpl implements IDeviceAttributesBiz {
    @Override
    public void updateDeviceName(String lockmac, String deviceNewName, final IDeviceAttributesListener listener) {
        ServerUnit.getInstance().updateDeviceName(lockmac, deviceNewName, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                LogUtils.i("更新设备名:" + s);
                listener.onUpDeviceName();
                listener.showMsg("设备名更新成功!");
            }

            @Override
            public void failure(String s, int i) {
                LogUtils.i("更新设备名:" + s);
                listener.showMsg("设备名更新失败!");

            }
        });
    }
}
