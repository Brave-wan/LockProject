package www.jinke.com.charmhome.impl;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.dsm.xiaodi.biz.sdk.servercore.ServerUnit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import www.jinke.com.charmhome.bean.CharmHomeLoginBean;
import www.jinke.com.charmhome.bean.LockMainDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;
import www.jinke.com.charmhome.http.CharmHomeMethods;
import www.jinke.com.charmhome.http.HttpResult;
import www.jinke.com.charmhome.http.ProgressSubscriber;
import www.jinke.com.charmhome.http.SubscriberOnNextListener;
import www.jinke.com.charmhome.listener.lock.IStartPageBiz;
import www.jinke.com.charmhome.listener.lock.IStartPageListener;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;

/**
 * Created by root on 17-12-14.
 */

public class StartPageImpl implements IStartPageBiz {
    private Context mContext;

    public StartPageImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getLogin(final String account, String pwd, final IStartPageListener listener) {
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
                LogUtils.i("登陆失败:" + s);
                if (s.equals("当前用户不存在")) {
//                    listener.onUserEmpty(s + "当前用户不存在!");
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
    public void getLoadMainDeviceList(String mobile, final IStartPageListener listener) {
        ServerUnit.getInstance().loadMainDeviceList(mobile, null, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                LogUtils.i("设备列表:" + list.toString());
                Type type = new TypeToken<List<List<LockMainDeviceBean>>>() {
                }.getType();
                List<List<LockMainDeviceBean>> userBean = new Gson().fromJson(list.toString(), type);
                if (userBean != null && userBean.size() > 0) {
//                    listener.onMainDeviceList(userBean.get(0).get(0).getLock());
                }
            }

            @Override
            public void failure(String s, int i) {
                listener.showMsg(s);
            }
        });
    }

    @Override
    public void getUserRegister(String account, String pwd, final IStartPageListener listener) {
        ServerUnit.getInstance().register(account, pwd, null, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
//                listener.onRegisterSuccess(s);
            }

            @Override
            public void failure(String s, int i) {
                LogUtils.i("小滴管家帐号注册失败");
//                listener.showMsg(s);
            }
        });
    }

    @Override
    public void getCharmHomeLogin(Map<String, String> map, final IStartPageListener listener) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<CharmHomeLoginBean>() {
            @Override
            public void onNext(CharmHomeLoginBean bean) {
                LogUtils.i("魅力家登陆home" + bean.getPhone());
                listener.onCharmLoginSuccess(bean);
            }

            @Override
            public void onError(String Code, String Msg) {
                LogUtils.i("msg");
                listener.showMsg(Msg);
                if (Code.equals("2")) {
                    listener.onCharmRegister(Msg);
                }
            }
        };
        CharmHomeMethods.getInstance().getCharmHomeLogin(new ProgressSubscriber<HttpResult<CharmHomeLoginBean>>(onNextListener, mContext), map);

    }

    @Override
    public void getCharmHomeRegister(Map<String, String> map, final IStartPageListener listener) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<CharmHomeLoginBean>() {
            @Override
            public void onNext(CharmHomeLoginBean data) {
                LogUtils.i("魅力家登陆注册:" + data.getPhone());
                listener.CharmHomeRegisterSuccess(data);
            }

            @Override
            public void onError(String Code, String Msg) {
                LogUtils.i("msg");
                listener.showMsg(Msg);
            }
        };
        CharmHomeMethods.getInstance().getCharmHomeRegister(new ProgressSubscriber<HttpResult<CharmHomeLoginBean>>(onNextListener, mContext), map);
    }

    @Override
    public void getUpLoadOperatorLog(IStartPageListener listener) {
        ServerUnit.getInstance().uploadOperatorLog(new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                LogUtils.i("日志上传成功" + s);
            }

            @Override
            public void failure(String s, int i) {
                LogUtils.i("日志上传失败" + s);
            }
        });
    }
}
