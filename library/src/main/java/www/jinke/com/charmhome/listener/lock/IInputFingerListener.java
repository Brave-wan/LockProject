package www.jinke.com.charmhome.listener.lock;

import java.util.List;

import www.jinke.com.charmhome.bean.FingerListBean;
import www.jinke.com.charmhome.bean.LockUserDeviceListBean;

/**
 * Created by root on 17-12-12.
 */

public interface IInputFingerListener {

    void showMsg(String s);

    void onLoadUserListOnDevice(List<LockUserDeviceListBean> lockUserDeviceListBeans);

    /**
     * 开始录制指纹
     *
     * @param msg
     */
    void onInputStart(String msg);

    /**
     * 指纹录制中
     *
     * @param number
     */
    void onCollectting(int number);

    /***
     * 指纹录制完成
     * @param msg
     */
    void onCollectFinish(String msg);

    /**
     * 指纹录取完成
     * @param fingerListBeans
     */
    void onInputSuccessOffline(List<FingerListBean> fingerListBeans);
}
