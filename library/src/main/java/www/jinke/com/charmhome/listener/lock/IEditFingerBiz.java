package www.jinke.com.charmhome.listener.lock;

/**
 * Created by root on 17-12-13.
 */

public interface IEditFingerBiz {
    /**
     * 删除指纹
     * @param macAddress   设备mac地址
     * @param fingerId     要删除的指纹记录ID
     * @param fingerLockID 要删除的指纹设备ID
     * @param fingerType   要删除的指纹的指纹类型
     */
    void getFingerDelete(String macAddress, String fingerId, String fingerLockID, String fingerType, IEditFingerListener listener);

    /**
     * 更新指纹名称
     * @param fingerID 指纹ID
     * @param fingerType 指纹类型
     * @param fingerNewName 指纹分配后归属的用户
     * @param listener
     */
    void getUpdateFingerName(String fingerID, String fingerType, String fingerNewName,IEditFingerListener listener);
}
