package www.jinke.com.charmhome.bean;

/**
 * Created by root on 17-10-17.
 */

public class RequestFingerInputBean {
    private String macAddress;//-设备mac地址
    private String softwareVersion;//-设备软件版本号
    private String meterType;//-设备型号
    private String usedFinderNum;//-设备上已经使用的指纹数量
    private String loginUserMobile;//-登录用户的账号
    private String userAccount;//-指纹归属用户的账号
    private String userNickName;//-指纹归属用户的昵称
    private String userTimeRange;//-指纹归属用户的时效
    private String userLoveAlarmFlag;//-指纹归属用户的亲情报警标志
    private String userOpenType;//-指纹归属用户的开门方式(非手机开门方式)

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public String getUsedFinderNum() {
        return usedFinderNum;
    }

    public void setUsedFinderNum(String usedFinderNum) {
        this.usedFinderNum = usedFinderNum;
    }

    public String getLoginUserMobile() {
        return loginUserMobile;
    }

    public void setLoginUserMobile(String loginUserMobile) {
        this.loginUserMobile = loginUserMobile;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserTimeRange() {
        return userTimeRange;
    }

    public void setUserTimeRange(String userTimeRange) {
        this.userTimeRange = userTimeRange;
    }

    public String getUserLoveAlarmFlag() {
        return userLoveAlarmFlag;
    }

    public void setUserLoveAlarmFlag(String userLoveAlarmFlag) {
        this.userLoveAlarmFlag = userLoveAlarmFlag;
    }

    public String getUserOpenType() {
        return userOpenType;
    }

    public void setUserOpenType(String userOpenType) {
        this.userOpenType = userOpenType;
    }
}
