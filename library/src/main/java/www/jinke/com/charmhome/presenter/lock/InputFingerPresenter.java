package www.jinke.com.charmhome.presenter.lock;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import www.jinke.com.charmhome.bean.FingerListBean;
import www.jinke.com.charmhome.bean.LockUserDeviceListBean;
import www.jinke.com.charmhome.bean.RequestFingerInputBean;
import www.jinke.com.charmhome.impl.InputFingerImpl;
import www.jinke.com.charmhome.listener.lock.IInputFingerListener;
import www.jinke.com.charmhome.view.lock.IInputFingerView;

/**
 * Created by root on 17-12-12.
 */

public class InputFingerPresenter implements IInputFingerListener {
    private Context mContext;
    private IInputFingerView fingerView;
    private InputFingerImpl inputFinger;

    public InputFingerPresenter(Context mContext, IInputFingerView fingerView) {
        this.mContext = mContext;
        this.fingerView = fingerView;
        inputFinger = new InputFingerImpl();
    }

    public void getFingerInput(RequestFingerInputBean bean) {
        if (fingerView != null) {
            fingerView.showLoading("保持蓝牙指示灯长亮");
            inputFinger.getFingerInput(bean, this);
        }
    }

    public void getLoadUserListOnDevice(String mac) {
        if (fingerView != null) {
            inputFinger.getLoadUserListOnDevice(mac, this);
            fingerView.showLoading("加载中...");
        }
    }

    public void getBLEOpenEnableDisable(String macAddress, String userAccount) {
        if (fingerView != null) {
            inputFinger.getBLEOpenEnableDisable(macAddress, userAccount, this);
        }
    }

    @Override
    public void showMsg(String s) {
        if (fingerView != null) {
            fingerView.showMsg(s);
            fingerView.hideLoading();
        }
    }

    @Override
    public void onLoadUserListOnDevice(List<LockUserDeviceListBean> lockUserDeviceListBeans) {
        if (fingerView != null) {
            fingerView.hideLoading();
            fingerView.onLoadUserListOnDevice(lockUserDeviceListBeans);
        }
    }

    @Override
    public void onInputStart(String msg) {
        if (fingerView != null) {
            fingerView.hideLoading();
            fingerView.onInputStart(msg);
        }
    }

    @Override
    public void onCollectting(int number) {
        if (fingerView != null) {
            fingerView.hideLoading();
            fingerView.onCollectting(number);
        }
    }

    @Override
    public void onCollectFinish(String msg) {
        if (fingerView != null) {
            fingerView.onCollectFinish(msg);
        }

    }

    @Override
    public void onInputSuccessOffline(List<FingerListBean> fingerListBeans) {
        if (fingerView != null) {
            fingerView.onInputSuccessOffline(fingerListBeans);
        }

    }
}
