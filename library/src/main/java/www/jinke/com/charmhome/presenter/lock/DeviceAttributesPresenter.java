package www.jinke.com.charmhome.presenter.lock;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.impl.DeviceAttributesImpl;
import www.jinke.com.charmhome.listener.lock.IDeviceAttributesListener;
import www.jinke.com.charmhome.view.lock.IDeviceAttributesView;

/**
 * Created by root on 17-12-12.
 */

public class DeviceAttributesPresenter implements IDeviceAttributesListener {

    private Context mContext;
    private IDeviceAttributesView attributesView;
    private DeviceAttributesImpl attributes;

    public DeviceAttributesPresenter(Context mContext, IDeviceAttributesView attributesView) {
        this.mContext = mContext;
        this.attributesView = attributesView;
        attributes = new DeviceAttributesImpl();
    }

    public void updateDeviceName(String lockmac, String deviceNewName) {
        if (attributesView != null) {
            attributes.updateDeviceName(lockmac, deviceNewName, this);
            attributesView.showLoading("正在更新设备名");
        }
    }

    @Override
    public void onUpDeviceName() {
        if (attributesView != null) {
            attributesView.hideLoading();
            attributesView.onUpDeviceName();
        }

    }

    @Override
    public void showMsg(String s) {
        if (attributesView != null) {
            attributesView.showMsg(s);
            attributesView.hideLoading();
        }
    }
}
