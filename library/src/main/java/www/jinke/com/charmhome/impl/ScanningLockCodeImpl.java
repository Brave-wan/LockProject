package www.jinke.com.charmhome.impl;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.dsm.xiaodi.biz.sdk.business.adddevice.AddDevice;
import com.dsm.xiaodi.biz.sdk.servercore.ServerUnit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import www.jinke.com.charmhome.bean.CharmHomeLoginBean;
import www.jinke.com.charmhome.bean.LockAddDeviceBean;
import www.jinke.com.charmhome.bean.LockRequestAddDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;
import www.jinke.com.charmhome.http.CharmHomeMethods;
import www.jinke.com.charmhome.http.HttpResult;
import www.jinke.com.charmhome.http.ProgressSubscriber;
import www.jinke.com.charmhome.http.SubscriberOnNextListener;
import www.jinke.com.charmhome.listener.lock.IScanningLockCodListener;
import www.jinke.com.charmhome.listener.lock.IScanningLockCodeBiz;
import www.jinke.com.charmhome.listener.lock.IStartPageListener;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;

/**
 * Created by root on 17-12-11.
 */

public class ScanningLockCodeImpl implements IScanningLockCodeBiz {
    private Context mContext;

    public ScanningLockCodeImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getAddDevice(LockRequestAddDeviceBean bean, final IScanningLockCodListener listener) {
        new AddDevice(
                bean.getLoginUserMobile(), bean.getLoginUserNickName(), bean.getLocknameString(), bean.getLockmacString(), bean.getManagepwdString()
                , bean.getMetertype(), bean.getDevicetype(), bean.getLatitude(), bean.getLongitude(),
                null,
                new AddDevice.OnAddDeviceListener() {
                    @Override
                    public void addSuccessOnline(final Object object, final String locksn, final String lockChannelPassword, final String lockSoftwareVersion) {
                        LogUtils.i("addSuccessOnline:object=" + object.toString());
                        Type type = new TypeToken<List<LockAddDeviceBean>>() {
                        }.getType();
//                        List<LockAddDeviceBean> list = new Gson().fromJson(object.toString(), type);
//                        LockAddDeviceBean addDeviceBean = GsonUtils.convertObj(object.toString(), LockAddDeviceBean.class);
//                        if (addDeviceBean != null && listener != null) {
//                            listener.onAddDeviceBean(addDeviceBean);
//                        }
                        listener.onLockAddDeviceBean(new LockAddDeviceBean());
                    }

                    @Override
                    public void addSuccessOffline(final String lockmac, final String locknameString, final String belongUserAccount, final String belongUserNickName, final String managepwd, final String metertype, final String locksn, final String lockChannelPassword, final String lockSoftwareVersion) {
                        LogUtils.i("addSuccessOffline");
                    }

                    @Override
                    public void addFailure(final String error, final int loglever) {
                        LogUtils.i("error" + error);
                        listener.showMsg(error);
                    }
                }
        ).walk();
    }

    @Override
    public void getLockLogin(Map<String, String> map, final IScanningLockCodListener listener) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<CharmHomeLoginBean>() {
            @Override
            public void onNext(CharmHomeLoginBean bean) {
//                listener.onLoginBean(data);
                LogUtils.i("门锁登陆" + bean.getPhone());
                listener.onLockLogin(bean);
            }

            @Override
            public void onError(String Code, String Msg) {
                LogUtils.i("msg");
                listener.showMsg(Msg);
            }
        };
        CharmHomeMethods.getInstance().getLockLogin(new ProgressSubscriber<HttpResult<CharmHomeLoginBean>>(onNextListener, mContext), map);
    }

    @Override
    public void getLogin(final String account, final String pwd, final IScanningLockCodListener listener) {
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
                    userBean.get(0).setPassword(pwd);
                    listener.onLoginSuccess(userBean.get(0));
                }
            }

            @Override
            public void failure(String s, int i) {
                LogUtils.i("登陆失败:" + s);
                if (s.equals("当前用户不存在")) {
                    listener.onUserEmpty(account, pwd);
                } else if (s.equals("您输入的用户名和密码不匹配")) {
                    listener.onInputPassWord(account);
                } else if (s.equals("密码校验失败")) {
                    listener.onInputPassWord(account);
                }
//                listener.showMsg("s");
            }
        });
    }

    @Override
    public void getUserRegister(String account, String pwd, final IScanningLockCodListener listener) {
        ServerUnit.getInstance().register(account, pwd, null, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                LogUtils.i("注册小滴用户成功");
                listener.onRegisterSuccess("初始化成功!");
            }

            @Override
            public void failure(String s, int i) {
                LogUtils.i("注册小滴用户失败");
                listener.onUserRegisterFail("初始化失败!");
            }
        });
    }
}
