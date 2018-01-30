package www.jinke.com.charmhome.presenter;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.bean.LockMainDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;
import www.jinke.com.charmhome.impl.CharmHomeImpl;
import www.jinke.com.charmhome.listener.ICharmHomeListener;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.view.ICharmHomeView;

/**
 * Created by root on 17-11-2.
 */

public class CharmHomePresenter implements ICharmHomeListener {
    private Context mContext;
    private ICharmHomeView charmHomeView;
    private CharmHomeImpl charmHome;
    private ACache aCache;

    public CharmHomePresenter(Context mContext, ICharmHomeView charmHomeView) {
        this.mContext = mContext;
        this.charmHomeView = charmHomeView;
        charmHome = new CharmHomeImpl(mContext);
        aCache = ACache.get(mContext);
    }

    public void getLogin(String account, String pwd) {
        if (charmHomeView != null) {
            //小弟管家用户登陆
            charmHome.getLogin(account, pwd, this);
        }
    }

    public void getLoadMainDeviceList(String account){
        if (charmHomeView!=null){
            charmHome.getLoadMainDeviceList(LuMiConfig.ACCOUNT, this);
        }
    }

    @Override
    public void onLoginSuccess(LockUserBean lockUserBean) {
        if (charmHomeView != null) {
            aCache.put("UserBean", lockUserBean, ACache.MAX_SIZE);
            charmHomeView.showLoading("正在获取设备列表");
            //获取设备列表
            charmHome.getLoadMainDeviceList(LuMiConfig.ACCOUNT, this);
        }
    }



    @Override
    public void onMainDeviceList(List<LockMainDeviceBean.LockBean> lock) {
        if (charmHomeView != null && lock.size() > 0) {
            aCache.put("MainDeviceBean", lock.get(0), ACache.MAX_SIZE);
            charmHomeView.onMainDeviceList(lock);
        }
        charmHomeView.hideLoading();
    }

    @Override
    public void onUserEmpty(String msg) {
        //用户不存在时注册用户
        if (charmHomeView != null) {
            charmHomeView.showMsg(msg);
            LogUtils.i("正在为用户注册");
            charmHome.getUserRegister(LuMiConfig.ACCOUNT, LuMiConfig.PASSWORD, this);
        }
    }

    @Override
    public void showMsg(String s) {
        if (charmHomeView != null) {
            charmHomeView.showMsg(s);
            charmHomeView.hideLoading();
        }
    }

    @Override
    public void onRegisterSuccess(String s) {

    }
}
