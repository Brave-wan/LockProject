package www.jinke.com.charmhome.listener;

import java.util.List;

import www.jinke.com.charmhome.bean.LockMainDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;

/**
 * Created by root on 17-11-2.
 */

public interface ICharmHomeListener {

    /**
     * 用户登陆成功
     *
     * @param lockUserBean
     */
    void onLoginSuccess(LockUserBean lockUserBean);

    /**
     * 获取用户名下的所有设备列表
     *
     * @param lock
     */
    void onMainDeviceList(List<LockMainDeviceBean.LockBean> lock);

    /**
     * 判断用户在服务器上是否存在
     *
     * @param msg
     */
    void onUserEmpty(String msg);

    /**
     * 提示消息
     *
     * @param s
     */
    void showMsg(String s);

    /**
     * 用户注册成功
     *
     * @param s
     */
    void onRegisterSuccess(String s);
}
