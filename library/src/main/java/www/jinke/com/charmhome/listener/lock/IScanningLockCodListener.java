package www.jinke.com.charmhome.listener.lock;

import www.jinke.com.charmhome.bean.CharmHomeLoginBean;
import www.jinke.com.charmhome.bean.LockAddDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;

/**
 * Created by root on 17-12-11.
 */

public interface IScanningLockCodListener {

    void onLockAddDeviceBean(LockAddDeviceBean lockAddDeviceBean);

    void showMsg(String error);

    void onLockLogin(CharmHomeLoginBean bean);

    void onLoginSuccess(LockUserBean lockUserBean);

    void onUserEmpty(String account, String pwd);

    void onInputPassWord(String account);

    void onRegisterSuccess(String s);

    void onUserRegisterFail(String s);
}
