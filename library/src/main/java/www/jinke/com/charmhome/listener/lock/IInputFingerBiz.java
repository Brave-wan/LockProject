package www.jinke.com.charmhome.listener.lock;

import www.jinke.com.charmhome.bean.RequestFingerInputBean;

/**
 * Created by root on 17-12-12.
 */

public interface IInputFingerBiz {
    /**
     * 录指纹
     *
     * @param bean
     * @param listener
     */
    void getFingerInput(RequestFingerInputBean bean, IInputFingerListener listener);

    /**
     * 获取设备下面的用户列表
     *
     * @param mac
     * @param listener
     */
    void getLoadUserListOnDevice(String mac,  IInputFingerListener listener);

    /**
     * 更新用户蓝牙开锁方式
     * @param macAddress 设备mac地址
     * @param userAccount 用户账号
     * @param userOpenType 要更新的开锁方式 1 指纹 2 蓝牙 组合叠加，可为0，指纹权志改变请调用更新指纹开锁方式的接口
     */
    void getBLEOpenEnableDisable(String macAddress, String userAccount, IInputFingerListener listener);
}
