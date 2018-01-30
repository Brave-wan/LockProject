package www.jinke.com.charmhome.listener.lock;

/**
 * Created by root on 17-12-12.
 */

public interface ILockDeviceInfoBiz {
    /**
     * 设备mac地址
     *
     * @param lockmac
     * @param listener
     */
    void logoutDevice(String lockmac, String meterType, String softwareVersion, ILockDeviceInfoListener listener);
}
