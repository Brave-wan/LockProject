package www.jinke.com.charmhome.presenter.lock;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.bean.CharmHomeLoginBean;
import www.jinke.com.charmhome.bean.LockAddDeviceBean;
import www.jinke.com.charmhome.bean.LockRequestAddDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;
import www.jinke.com.charmhome.impl.ScanningLockCodeImpl;
import www.jinke.com.charmhome.listener.lock.IScanningLockCodListener;
import www.jinke.com.charmhome.presenter.BasePresenter;
import www.jinke.com.charmhome.view.lock.IInputDeviceNameView;

/**
 * Created by root on 17-12-12.
 */

public class InputDeviceNamePresenter implements IScanningLockCodListener {
    private Context mContext;
    private IInputDeviceNameView nameView;
    private ScanningLockCodeImpl lockCode;

    public InputDeviceNamePresenter(Context mContext, IInputDeviceNameView nameView) {
        this.mContext = mContext;
        this.nameView = nameView;
        lockCode = new ScanningLockCodeImpl(mContext);
    }

    public void getAddDevice(LockRequestAddDeviceBean bean) {
        if (nameView != null) {
            lockCode.getAddDevice(bean, this);
            nameView.showLoading("设备连接中");
        }
    }

    @Override
    public void onLockAddDeviceBean(LockAddDeviceBean lockAddDeviceBean) {
        if (nameView != null) {
            showMsg("设备添加成功,请放心使用!");
            nameView.addDeviceFinish();
            nameView.hideLoading();
            nameView.startInputFinger();
        }
    }

    @Override
    public void showMsg(String error) {
        if (nameView != null) {
            nameView.showMsg(error);
            nameView.hideLoading();
        }
    }

    @Override
    public void onLockLogin(CharmHomeLoginBean bean) {

    }

    @Override
    public void onLoginSuccess(LockUserBean lockUserBean) {

    }

    @Override
    public void onUserEmpty(String account, String pwd) {

    }

    @Override
    public void onInputPassWord(String account) {

    }

    @Override
    public void onRegisterSuccess(String s) {

    }

    @Override
    public void onUserRegisterFail(String s) {

    }
}
