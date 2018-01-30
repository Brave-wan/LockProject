package www.jinke.com.charmhome.bean;

/**
 * Created by root on 17-10-13.
 */

public class LockRequestAddDeviceBean {

    private String loginUserMobile;//- 登录用户手机号码
    private String loginUserNickName;//- 登录用户昵称
    private String locknameString;//- 设备名称
    private String lockmacString;//- 设备mac地址
    private String managepwdString;// - 设备管理密码，后期废弃，默认88888888
    private String metertype;// - 设备固件类型，例如T700_0
    private String latitude;// - 设备所在维度
    private String longitude;//- 设备所在经度
    private String devicetype;//
    private String appType = "LOCK";// 应用类型 LOCK 小嘀管家 GC 绿城 HN 海南 TEMP 临时
    private String deviceSerial;//设备序列号
    private String channelPwd;//信道密码
    private String managePwd = "88888888";//管理密码 固定为88888888
    private String lockSeid;//带加密芯片的锁具Seid，用于获取Sekey
    private String lockTime;//注册时间

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockSeid(String lockSeid) {
        this.lockSeid = lockSeid;
    }

    public String getLockSeid() {
        return lockSeid;
    }

    public void setManagePwd(String managePwd) {
        this.managePwd = managePwd;
    }

    public String getManagePwd() {
        return managePwd;
    }

    public String getChannelPwd() {
        return channelPwd;
    }

    public void setChannelPwd(String channelPwd) {
        this.channelPwd = channelPwd;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public String getLoginUserMobile() {
        return loginUserMobile;
    }

    public void setLoginUserMobile(String loginUserMobile) {
        this.loginUserMobile = loginUserMobile;
    }

    public String getLoginUserNickName() {
        return loginUserNickName;
    }

    public void setLoginUserNickName(String loginUserNickName) {
        this.loginUserNickName = loginUserNickName;
    }

    public String getLocknameString() {
        return locknameString;
    }

    public void setLocknameString(String locknameString) {
        this.locknameString = locknameString;
    }

    public String getLockmacString() {
        return lockmacString;
    }

    public void setLockmacString(String lockmacString) {
        this.lockmacString = lockmacString;
    }

    public String getManagepwdString() {
        return managepwdString;
    }

    public void setManagepwdString(String managepwdString) {
        this.managepwdString = managepwdString;
    }

    public String getMetertype() {
        return metertype;
    }

    public void setMetertype(String metertype) {
        this.metertype = metertype;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
