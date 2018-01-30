package www.jinke.com.charmhome.presenter.lock;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dsm.xiaodi.biz.sdk.business.BusinessResponse;
import com.dsm.xiaodi.biz.sdk.business.deviceinfo.OpenPwdUpdateSimple;

import www.jinke.com.charmhome.view.lock.IDevicePassView;

/**
 * Created by root on 17-12-13.
 */

public class DevicePassPresenter {

    private IDevicePassView passView;

    public DevicePassPresenter(IDevicePassView passView) {
        this.passView = passView;
    }


    public void OpenPwdUpdateSimple(String macAddress, String originOpenpwd, String lockPasswordState, String newOpenpwd) {
        new OpenPwdUpdateSimple(macAddress, "", lockPasswordState, newOpenpwd, new BusinessResponse() {
            @Override
            public void onSuccess(Object o) {
                LogUtils.i("设置开锁密码:" + o);
                if (passView != null) {
                    passView.onSuccess("设备密码修改成功!");
                }
            }

            @Override
            public void onFailure(String s, int i) {
                LogUtils.i("设置开锁密码失败:" + s);
                if (passView != null) {
                    passView.showMsg("设备密码修改失败!");
                }
            }
        }).walk();
    }
}
