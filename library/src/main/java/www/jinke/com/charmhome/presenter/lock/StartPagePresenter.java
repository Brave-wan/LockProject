package www.jinke.com.charmhome.presenter.lock;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionGen;
import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.bean.CharmHomeLoginBean;
import www.jinke.com.charmhome.bean.LockMainDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;
import www.jinke.com.charmhome.impl.StartPageImpl;
import www.jinke.com.charmhome.listener.lock.IStartPageListener;
import www.jinke.com.charmhome.ui.activity.CharmHomeActivity;
import www.jinke.com.charmhome.ui.lock.IStartPageView;
import www.jinke.com.charmhome.ui.lock.InputLoginPassActivity;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.widget.MyToast;

/**
 * Created by root on 17-12-14.
 */

public class StartPagePresenter implements IStartPageListener {
    private Activity mContext;
    private IStartPageView startPageView;
    private StartPageImpl startPage;
    private ACache aCache;

    public StartPagePresenter(Activity mContext, IStartPageView startPageView) {
        this.mContext = mContext;
        this.startPageView = startPageView;
        startPage = new StartPageImpl(mContext);
        aCache = ACache.get(mContext);
        getCameraPermission();
    }

    public void getLogin(String account, String pwd) {
        if (startPageView != null) {
            //小弟管家用户登陆
            startPage.getLogin(account, pwd, this);
        }
    }

    @Override
    public void onLoginSuccess(LockUserBean lockUserBean) {
        if (startPageView != null) {
            //第三方门锁平台登陆成功，进入首页加载设备列表
//            startPage.getLoadMainDeviceList(LuMiConfig.ACCOUNT, this);
//            mContext.startActivity(new Intent(mContext, CharmHomeActivity.class));
            aCache.put("UserBean", lockUserBean, ACache.MAX_SIZE);
            startPageView.hideLoading();
        }
    }

    public void getCharmHomeLogin(Map<String, String> map) {
        if (startPageView != null) {
            startPageView.showLoading("");
            startPage.getCharmHomeLogin(map, this);
            startPage.getUpLoadOperatorLog(this);
        }
    }

    public void getCameraPermission() {
        PermissionGen.with(mContext)
                .addRequestCode(107)
                .permissions(Manifest.permission.BLUETOOTH,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .request();
    }

    @Override
    public void showMsg(String s) {
        MyToast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onInputPassWord(String account) {
        //登陆第三方平台失败（帐号密码不匹配）,则进入输入密码页面完成登陆操作
        if (startPageView != null) {
            Intent intent = new Intent(mContext, InputLoginPassActivity.class);
            intent.putExtra("account", account);
            intent.putExtra("code", LuMiConfig.Home_resultCode);
            mContext.startActivityForResult(intent, LuMiConfig.Home_resultCode);
        }
    }

    @Override
    public void onCharmRegister(String msg) {
        if (startPageView != null) {
            Map<String, String> map = new HashMap<>();
            map.put("phone", LuMiConfig.ACCOUNT);
            map.put("nickName", LuMiConfig.NICKNAME);
            map.put("hourseName", LuMiConfig.HOURSENAME);
            map.put("hourseId", LuMiConfig.HOURSEID);
            startPage.getCharmHomeRegister(map, this);
        }
    }

    @Override
    public void onCharmLoginSuccess(CharmHomeLoginBean bean) {
        if (startPageView != null) {
            //保存登陆信息
            aCache.put("charmHomeLogin", bean, ACache.MAX_SIZE);
            //登陆魅力家帐号成功,判断服务器返回的门锁帐号是否为空，如果为空进入首页，反之则进行登陆第三平门锁平台
            if (!StringUtils.isEmpty(bean.getLockUser())) {
                startPage.getLogin(bean.getLockUser(), LuMiConfig.PASSWORD, this);
            } else {
                startPageView.hideLoading();
            }
        }
    }

    @Override
    public void CharmHomeRegisterSuccess(CharmHomeLoginBean bean) {
        if (startPageView != null) {
            //注册魅力家帐号后，服务器会返回一个为空的门锁帐号，判断不用登陆第三方平台，则直接进入首页
//            mContext.startActivity(new Intent(mContext, CharmHomeActivity.class));
            aCache.put("charmHomeLogin", bean, ACache.MAX_SIZE);
            startPageView.hideLoading();
        }
    }
}
