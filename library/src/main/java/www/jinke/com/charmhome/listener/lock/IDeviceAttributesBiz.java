package www.jinke.com.charmhome.listener.lock;

/**
 * Created by root on 17-12-12.
 */

public interface IDeviceAttributesBiz {
    /**
     * 更新设备名称
     * @param lockmac
     * @param deviceNewName
     * @param listener
     */
    void updateDeviceName(String lockmac, String deviceNewName, IDeviceAttributesListener listener);
}
