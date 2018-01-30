package www.jinke.com.charmhome.view.lock;

import java.util.List;

import www.jinke.com.charmhome.bean.FingerListBean;
import www.jinke.com.charmhome.bean.LockUserDeviceListBean;
import www.jinke.com.charmhome.view.BaseView;

/**
 * Created by root on 17-12-12.
 */

public interface IInputFingerView extends BaseView{
    /**
     * 根据mac地址获取该设备下面的用户列表
     * @param lockUserDeviceListBeans
     */
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

    void onInputSuccessOffline(List<FingerListBean> fingerListBeans);

    void showMsg(String s);
}
