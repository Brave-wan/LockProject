package www.jinke.com.charmhome.listener.lock;

import java.util.Map;

import www.jinke.com.charmhome.bean.LockRequestAddDeviceBean;

/**
 * Created by root on 17-12-11.
 */

public interface IScanningLockCodeBiz {
    void getAddDevice(LockRequestAddDeviceBean bean, final IScanningLockCodListener listener);

    void getLockLogin(Map<String, String> map, final IScanningLockCodListener listener);

    void getLogin(String account, String pwd, final IScanningLockCodListener listener);

    /**
     * 用户注册
     * @param account
     * @param pwd
     * @param listener
     */
    void getUserRegister(String account, String pwd, final IScanningLockCodListener listener);
}
