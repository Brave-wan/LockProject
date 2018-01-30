package www.jinke.com.charmhome.impl;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.dsm.xiaodi.biz.sdk.servercore.ServerUnit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import www.jinke.com.charmhome.bean.LockMainDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;
import www.jinke.com.charmhome.listener.ICharmHomeListener;
import www.jinke.com.charmhome.listener.lock.ICharmHomeBiz;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;

/**
 * Created by root on 17-12-11.
 */

public class CharmHomeImpl implements ICharmHomeBiz {
    private Context mContext;

    public CharmHomeImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getLogin(String account, String pwd, final ICharmHomeListener listener) {
        ServerUnit.getInstance().login(account, pwd, null, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                LogUtils.i("login:" + list.toString());
                Type type = new TypeToken<List<LockUserBean>>() {
                }.getType();
                List<LockUserBean> userBean = new Gson().fromJson(list.get(0).toString(), type);
                if (userBean.size() > 0) {
                    SharedPreferencesUtils.init(mContext).put("user_nickName", userBean.get(0).getNickname());
                    SharedPreferencesUtils.init(mContext).put("user_name", userBean.get(0).getName());
                    SharedPreferencesUtils.init(mContext).put("user_account", userBean.get(0).getAccount());
                    SharedPreferencesUtils.init(mContext).put("user_baiduchannelid", userBean.get(0).getBaiduchannelid());
                    SharedPreferencesUtils.init(mContext).put("user_password", userBean.get(0).getPassword());
                    SharedPreferencesUtils.init(mContext).put("user_token", userBean.get(0).getToken());
                    listener.onLoginSuccess(userBean.get(0));
                }
            }

            @Override
            public void failure(String s, int i) {
                if (i == 5) {
                    listener.onUserEmpty(s + "当前用户不存在!");
                } else {
                    listener.showMsg("s");
                }
            }
        });
    }

    @Override
    public void getLoadMainDeviceList(String mobile, final ICharmHomeListener listener) {
        ServerUnit.getInstance().loadMainDeviceList(mobile, null, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                LogUtils.i("设备列表:" + list.toString());
                Type type = new TypeToken<List<List<LockMainDeviceBean>>>() {
                }.getType();
                List<List<LockMainDeviceBean>> userBean = new Gson().fromJson(list.toString(), type);
                if (userBean != null && userBean.size() > 0) {
                    listener.onMainDeviceList(userBean.get(0).get(0).getLock());
                }
            }

            @Override
            public void failure(String s, int i) {
                listener.showMsg(s);
            }
        });
    }

    @Override
    public void getUserRegister(String account, String pwd, final ICharmHomeListener listener) {
        ServerUnit.getInstance().register(account, pwd, null, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                listener.onRegisterSuccess(s);

            }

            @Override
            public void failure(String s, int i) {
                listener.showMsg(s);
            }
        });
    }


}
