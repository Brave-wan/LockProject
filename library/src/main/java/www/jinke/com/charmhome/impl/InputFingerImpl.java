package www.jinke.com.charmhome.impl;

import com.blankj.utilcode.util.LogUtils;
import com.dsm.xiaodi.biz.sdk.business.BusinessResponse;
import com.dsm.xiaodi.biz.sdk.business.deviceuser.BLEOpenEnableDisable;
import com.dsm.xiaodi.biz.sdk.business.deviceuser.FingerInput;
import com.dsm.xiaodi.biz.sdk.business.deviceuser.FingerOpenEnableDisable;
import com.dsm.xiaodi.biz.sdk.business.smartkey.EnableDisableSmartKeyOnDevice;
import com.dsm.xiaodi.biz.sdk.servercore.ServerUnit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import www.jinke.com.charmhome.bean.FingerListBean;
import www.jinke.com.charmhome.bean.LockUserDeviceListBean;
import www.jinke.com.charmhome.bean.RequestFingerInputBean;
import www.jinke.com.charmhome.listener.lock.IInputFingerBiz;
import www.jinke.com.charmhome.listener.lock.IInputFingerListener;

/**
 * Created by root on 17-12-12.
 */

public class InputFingerImpl implements IInputFingerBiz {
    @Override
    public void getFingerInput(RequestFingerInputBean bean, final IInputFingerListener listener) {
        new FingerInput(bean.getMacAddress(), bean.getSoftwareVersion(), bean.getMeterType(), bean.getUsedFinderNum(),
                bean.getLoginUserMobile(), bean.getUserAccount(), bean.getUserNickName(),
                bean.getUserTimeRange(), bean.getUserLoveAlarmFlag(), bean.getUserOpenType(), null,
                new FingerInput.OnFingerInputListener() {
                    @Override
                    public void onInputStart() {
                        LogUtils.i("onInputStart");
                        listener.onInputStart("请将手指放入设备上!");
                    }

                    @Override
                    public void onCollectting(long l, String s) {
                        LogUtils.i("onCollectting:" + s);
                        listener.onCollectting(1);
                    }

                    @Override
                    public void onCollectFinish(String s) {
                        LogUtils.i("onCollectFinish:" + s);
                        listener.onCollectFinish("指纹录取成功,请放心使用!");
                    }

                    @Override
                    public void onInputSuccessOnline(Object o) {
                        LogUtils.i("onInputSuccessOnline:" + o.toString());
                        if (o != null) {
                            listener.showMsg("指纹录入成功");
                            Type type = new TypeToken<List<FingerListBean>>() {
                            }.getType();
                            List<FingerListBean> listDevice = new Gson().fromJson(o.toString(), type);
                            if (listDevice != null && listDevice.size() > 0) {
                                listener.onInputSuccessOffline(listDevice);
                            }
                        }
                    }

                    @Override
                    public void onInputSuccessOffline(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
                        LogUtils.i("onInputSuccessOffline:" + s);

                    }

                    @Override
                    public void onError(String s, int i) {
                        LogUtils.i("onError：" + s);
                    }
                }).walk();
    }

    @Override
    public void getLoadUserListOnDevice(String mac, final IInputFingerListener listener) {
        ServerUnit.getInstance().loadUserListOnDevice(mac, null, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                LogUtils.i("获取设备上面的用户列表：" + list.toString());
                Type type = new TypeToken<List<List<LockUserDeviceListBean>>>() {
                }.getType();
                List<List<LockUserDeviceListBean>> listDevice = new Gson().fromJson(list.toString(), type);
                if (listDevice != null & listDevice.size() > 0) {
                    listener.onLoadUserListOnDevice(listDevice.get(0));
                }
            }

            @Override
            public void failure(String s, int i) {
                LogUtils.i("获取设备上面的用户列表 fail：" + s);
                listener.showMsg(s);
            }
        });
    }

    @Override
    public void getBLEOpenEnableDisable(String macAddress, String userAccount, final IInputFingerListener listener) {
        new BLEOpenEnableDisable(macAddress, userAccount, "1", new BusinessResponse() {
            @Override
            public void onSuccess(Object o) {
                LogUtils.i("更新蓝牙开锁方式：" + o);
            }

            @Override
            public void onFailure(String s, int i) {
                LogUtils.i("更新蓝牙开锁方式：onFailure" + s);
            }
        }).walk();
    }


}
