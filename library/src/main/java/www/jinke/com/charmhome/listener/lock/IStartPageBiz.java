package www.jinke.com.charmhome.listener.lock;

import java.util.Map;

import www.jinke.com.charmhome.listener.ICharmHomeListener;

/**
 * Created by root on 17-12-14.
 */

public interface IStartPageBiz {
    void getLogin(String account, String pwd, final IStartPageListener listener);

    /**
     * 获取设备列表
     *
     * @param mobile
     * @param listener
     */
    void getLoadMainDeviceList(String mobile, final IStartPageListener listener);

    /**
     * 用户注册
     * @param account
     * @param pwd
     * @param listener
     */
    void getUserRegister(String account, String pwd, final IStartPageListener listener);

    /**
     * 魅力家登陆接口
     * @param listener
     */
    void getCharmHomeLogin(Map<String,String> map,final IStartPageListener listener);

    /**
     * 魅力家注册接口
     * @param map
     * @param listener
     */
    void getCharmHomeRegister(Map<String,String> map,final IStartPageListener listener);

    /**
     * 上传日志
     * @param listener
     */
    void getUpLoadOperatorLog(final IStartPageListener listener);
}
