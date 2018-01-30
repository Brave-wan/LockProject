package www.jinke.com.charmhome.presenter;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;

import LumiSDK.LumiSDK;
import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.listener.IWifiConnectListener;
import www.jinke.com.charmhome.view.IWifiConnectView;


/**
 * Created by root on 17-11-3.
 */

public class WifiConnectPresenter implements IWifiConnectListener {
    private Context mContext;
    private IWifiConnectView connectView;

    public WifiConnectPresenter(Context mContext, IWifiConnectView connectView) {
        this.connectView = connectView;
        this.mContext = mContext;
        AuthLuMi();
    }

    public void AuthLuMi() {
        if (connectView != null) {
            connectView.showLoading();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean result = false;
                    try {
                        result = LumiSDK.aiotAuth(LuMiConfig.appID, LuMiConfig.appKey, LuMiConfig.openID);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    LogUtils.i("result:" + result);
                    AuthState(result);
                }
            }).start();
        }
    }

    public void AuthState(boolean state) {
        if (connectView != null) {
            connectView.authState(state);
            connectView.hideLoading();
        }
    }
}
