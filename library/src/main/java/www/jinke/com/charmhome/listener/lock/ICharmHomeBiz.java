package www.jinke.com.charmhome.listener.lock;

import www.jinke.com.charmhome.listener.ICharmHomeListener;

/**
 * Created by root on 17-12-11.
 */

public interface ICharmHomeBiz {
    void getLogin(String account, String pwd, final ICharmHomeListener listener);

    /**
     * 获取设备列表
     *
     * @param mobile
     * @param listener
     */
    void getLoadMainDeviceList(String mobile, final ICharmHomeListener listener);

    /**
     * 用户注册
     * @param account
     * @param pwd
     * @param listener
     */
    void getUserRegister(String account, String pwd, final ICharmHomeListener listener);


}
