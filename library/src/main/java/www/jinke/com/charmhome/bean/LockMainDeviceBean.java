package www.jinke.com.charmhome.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 17-10-19.
 */

public class LockMainDeviceBean implements Serializable {


    private List<LockBean> lock;
    private List<?> tempLock;
    private List<?> eques;
    private List<?> publicDevice;
    private List<?> bong;
    private List<?> vidicon;

    public List<LockBean> getLock() {
        return lock;
    }

    public void setLock(List<LockBean> lock) {
        this.lock = lock;
    }

    public List<?> getTempLock() {
        return tempLock;
    }

    public void setTempLock(List<?> tempLock) {
        this.tempLock = tempLock;
    }

    public List<?> getEques() {
        return eques;
    }

    public void setEques(List<?> eques) {
        this.eques = eques;
    }

    public List<?> getPublicDevice() {
        return publicDevice;
    }

    public void setPublicDevice(List<?> publicDevice) {
        this.publicDevice = publicDevice;
    }

    public List<?> getBong() {
        return bong;
    }

    public void setBong(List<?> bong) {
        this.bong = bong;
    }

    public List<?> getVidicon() {
        return vidicon;
    }

    public void setVidicon(List<?> vidicon) {
        this.vidicon = vidicon;
    }

    public static class LockBean implements  Serializable{
        /**
         * batteryStatus : 100
         * channelpassword : 936ffb52
         * deviceType : 11
         * fingerunuserdnum : 79
         * fingeruserdnum : 1
         * hardwareVersion :
         * item1 :
         * item2 :
         * keyunyserdnum : 0
         * keyuserdnum : 0
         * lockAlarmpassword :
         * lockAlarmpasswordstate : 0
         * lockBongNum : 0
         * lockId : 79960
         * lockLat :
         * lockLon :
         * lockLoveuserNum : 0
         * lockManagepwd :
         * lockPassword :
         * lockPasswordstate : 0
         * lockSalescode :
         * lockSalesname :
         * lockSmartkeyNum : 0
         * lockTemppassNum : 0
         * lockWifipwd :
         * lockWifissid :
         * lockWifistate : 0
         * lockmac : C0:D1:AD:04:90:6C
         * lockname : heihei
         * lockseq : C0:D1:AD:04:90:6C
         * manageaccount : 18623558334
         * manageaccountname : sunny bingo
         * managepassword :
         * metertype : T710_0
         * opentypeFinger : 1
         * opentypeKey : 0
         * opentypePhone : 1
         * parentid : 0
         * phoneunuserdnum : 149
         * phoneuserdnum : 1
         * remark :
         * routerid : 0
         * seniorPrivilege : 1
         * softwareVersion : A0.3.007_20170220
         * state : 1
         * time : 2017-11-24 15:11
         * type : 11
         * unixTime : 1511507479000
         * updateFlag : 0
         * usAppstyle : 1
         * usColor : 1
         * usLockname :
         * usOpenMusic : 0
         * usOpenPwd :
         * usOpenType : 2
         * userHandPassword :
         * useropentype : 3
         * usertimeset :
         * wildFinger : 0
         */

        private int batteryStatus;
        private String channelpassword;
        private String deviceType;
        private int fingerunuserdnum;
        private int fingeruserdnum;
        private String hardwareVersion;
        private String item1;
        private String item2;
        private int keyunyserdnum;
        private int keyuserdnum;
        private String lockAlarmpassword;
        private int lockAlarmpasswordstate;
        private int lockBongNum;
        private int lockId;
        private String lockLat;
        private String lockLon;
        private String lockLoveuserNum;
        private String lockManagepwd;
        private String lockPassword;
        private int lockPasswordstate;
        private String lockSalescode;
        private String lockSalesname;
        private int lockSmartkeyNum;
        private String lockTemppassNum;
        private String lockWifipwd;
        private String lockWifissid;
        private int lockWifistate;
        private String lockmac;
        private String lockname;
        private String lockseq;
        private String manageaccount;
        private String manageaccountname;
        private String managepassword;
        private String metertype;
        private int opentypeFinger;
        private int opentypeKey;
        private int opentypePhone;
        private int parentid;
        private int phoneunuserdnum;
        private int phoneuserdnum;
        private String remark;
        private int routerid;
        private int seniorPrivilege;
        private String softwareVersion;
        private int state;
        private String time;
        private int type;
        private long unixTime;
        private int updateFlag;
        private String usAppstyle;
        private String usColor;
        private String usLockname;
        private String usOpenMusic;
        private String usOpenPwd;
        private String usOpenType;
        private String userHandPassword;
        private String useropentype;
        private String usertimeset;
        private String wildFinger;

        public int getBatteryStatus() {
            return batteryStatus;
        }

        public void setBatteryStatus(int batteryStatus) {
            this.batteryStatus = batteryStatus;
        }

        public String getChannelpassword() {
            return channelpassword;
        }

        public void setChannelpassword(String channelpassword) {
            this.channelpassword = channelpassword;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public int getFingerunuserdnum() {
            return fingerunuserdnum;
        }

        public void setFingerunuserdnum(int fingerunuserdnum) {
            this.fingerunuserdnum = fingerunuserdnum;
        }

        public int getFingeruserdnum() {
            return fingeruserdnum;
        }

        public void setFingeruserdnum(int fingeruserdnum) {
            this.fingeruserdnum = fingeruserdnum;
        }

        public String getHardwareVersion() {
            return hardwareVersion;
        }

        public void setHardwareVersion(String hardwareVersion) {
            this.hardwareVersion = hardwareVersion;
        }

        public String getItem1() {
            return item1;
        }

        public void setItem1(String item1) {
            this.item1 = item1;
        }

        public String getItem2() {
            return item2;
        }

        public void setItem2(String item2) {
            this.item2 = item2;
        }

        public int getKeyunyserdnum() {
            return keyunyserdnum;
        }

        public void setKeyunyserdnum(int keyunyserdnum) {
            this.keyunyserdnum = keyunyserdnum;
        }

        public int getKeyuserdnum() {
            return keyuserdnum;
        }

        public void setKeyuserdnum(int keyuserdnum) {
            this.keyuserdnum = keyuserdnum;
        }

        public String getLockAlarmpassword() {
            return lockAlarmpassword;
        }

        public void setLockAlarmpassword(String lockAlarmpassword) {
            this.lockAlarmpassword = lockAlarmpassword;
        }

        public int getLockAlarmpasswordstate() {
            return lockAlarmpasswordstate;
        }

        public void setLockAlarmpasswordstate(int lockAlarmpasswordstate) {
            this.lockAlarmpasswordstate = lockAlarmpasswordstate;
        }

        public int getLockBongNum() {
            return lockBongNum;
        }

        public void setLockBongNum(int lockBongNum) {
            this.lockBongNum = lockBongNum;
        }

        public int getLockId() {
            return lockId;
        }

        public void setLockId(int lockId) {
            this.lockId = lockId;
        }

        public String getLockLat() {
            return lockLat;
        }

        public void setLockLat(String lockLat) {
            this.lockLat = lockLat;
        }

        public String getLockLon() {
            return lockLon;
        }

        public void setLockLon(String lockLon) {
            this.lockLon = lockLon;
        }

        public String getLockLoveuserNum() {
            return lockLoveuserNum;
        }

        public void setLockLoveuserNum(String lockLoveuserNum) {
            this.lockLoveuserNum = lockLoveuserNum;
        }

        public String getLockManagepwd() {
            return lockManagepwd;
        }

        public void setLockManagepwd(String lockManagepwd) {
            this.lockManagepwd = lockManagepwd;
        }

        public String getLockPassword() {
            return lockPassword;
        }

        public void setLockPassword(String lockPassword) {
            this.lockPassword = lockPassword;
        }

        public int getLockPasswordstate() {
            return lockPasswordstate;
        }

        public void setLockPasswordstate(int lockPasswordstate) {
            this.lockPasswordstate = lockPasswordstate;
        }

        public String getLockSalescode() {
            return lockSalescode;
        }

        public void setLockSalescode(String lockSalescode) {
            this.lockSalescode = lockSalescode;
        }

        public String getLockSalesname() {
            return lockSalesname;
        }

        public void setLockSalesname(String lockSalesname) {
            this.lockSalesname = lockSalesname;
        }

        public int getLockSmartkeyNum() {
            return lockSmartkeyNum;
        }

        public void setLockSmartkeyNum(int lockSmartkeyNum) {
            this.lockSmartkeyNum = lockSmartkeyNum;
        }

        public String getLockTemppassNum() {
            return lockTemppassNum;
        }

        public void setLockTemppassNum(String lockTemppassNum) {
            this.lockTemppassNum = lockTemppassNum;
        }

        public String getLockWifipwd() {
            return lockWifipwd;
        }

        public void setLockWifipwd(String lockWifipwd) {
            this.lockWifipwd = lockWifipwd;
        }

        public String getLockWifissid() {
            return lockWifissid;
        }

        public void setLockWifissid(String lockWifissid) {
            this.lockWifissid = lockWifissid;
        }

        public int getLockWifistate() {
            return lockWifistate;
        }

        public void setLockWifistate(int lockWifistate) {
            this.lockWifistate = lockWifistate;
        }

        public String getLockmac() {
            return lockmac;
        }

        public void setLockmac(String lockmac) {
            this.lockmac = lockmac;
        }

        public String getLockname() {
            return lockname;
        }

        public void setLockname(String lockname) {
            this.lockname = lockname;
        }

        public String getLockseq() {
            return lockseq;
        }

        public void setLockseq(String lockseq) {
            this.lockseq = lockseq;
        }

        public String getManageaccount() {
            return manageaccount;
        }

        public void setManageaccount(String manageaccount) {
            this.manageaccount = manageaccount;
        }

        public String getManageaccountname() {
            return manageaccountname;
        }

        public void setManageaccountname(String manageaccountname) {
            this.manageaccountname = manageaccountname;
        }

        public String getManagepassword() {
            return managepassword;
        }

        public void setManagepassword(String managepassword) {
            this.managepassword = managepassword;
        }

        public String getMetertype() {
            return metertype;
        }

        public void setMetertype(String metertype) {
            this.metertype = metertype;
        }

        public int getOpentypeFinger() {
            return opentypeFinger;
        }

        public void setOpentypeFinger(int opentypeFinger) {
            this.opentypeFinger = opentypeFinger;
        }

        public int getOpentypeKey() {
            return opentypeKey;
        }

        public void setOpentypeKey(int opentypeKey) {
            this.opentypeKey = opentypeKey;
        }

        public int getOpentypePhone() {
            return opentypePhone;
        }

        public void setOpentypePhone(int opentypePhone) {
            this.opentypePhone = opentypePhone;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public int getPhoneunuserdnum() {
            return phoneunuserdnum;
        }

        public void setPhoneunuserdnum(int phoneunuserdnum) {
            this.phoneunuserdnum = phoneunuserdnum;
        }

        public int getPhoneuserdnum() {
            return phoneuserdnum;
        }

        public void setPhoneuserdnum(int phoneuserdnum) {
            this.phoneuserdnum = phoneuserdnum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getRouterid() {
            return routerid;
        }

        public void setRouterid(int routerid) {
            this.routerid = routerid;
        }

        public int getSeniorPrivilege() {
            return seniorPrivilege;
        }

        public void setSeniorPrivilege(int seniorPrivilege) {
            this.seniorPrivilege = seniorPrivilege;
        }

        public String getSoftwareVersion() {
            return softwareVersion;
        }

        public void setSoftwareVersion(String softwareVersion) {
            this.softwareVersion = softwareVersion;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getUnixTime() {
            return unixTime;
        }

        public void setUnixTime(long unixTime) {
            this.unixTime = unixTime;
        }

        public int getUpdateFlag() {
            return updateFlag;
        }

        public void setUpdateFlag(int updateFlag) {
            this.updateFlag = updateFlag;
        }

        public String getUsAppstyle() {
            return usAppstyle;
        }

        public void setUsAppstyle(String usAppstyle) {
            this.usAppstyle = usAppstyle;
        }

        public String getUsColor() {
            return usColor;
        }

        public void setUsColor(String usColor) {
            this.usColor = usColor;
        }

        public String getUsLockname() {
            return usLockname;
        }

        public void setUsLockname(String usLockname) {
            this.usLockname = usLockname;
        }

        public String getUsOpenMusic() {
            return usOpenMusic;
        }

        public void setUsOpenMusic(String usOpenMusic) {
            this.usOpenMusic = usOpenMusic;
        }

        public String getUsOpenPwd() {
            return usOpenPwd;
        }

        public void setUsOpenPwd(String usOpenPwd) {
            this.usOpenPwd = usOpenPwd;
        }

        public String getUsOpenType() {
            return usOpenType;
        }

        public void setUsOpenType(String usOpenType) {
            this.usOpenType = usOpenType;
        }

        public String getUserHandPassword() {
            return userHandPassword;
        }

        public void setUserHandPassword(String userHandPassword) {
            this.userHandPassword = userHandPassword;
        }

        public String getUseropentype() {
            return useropentype;
        }

        public void setUseropentype(String useropentype) {
            this.useropentype = useropentype;
        }

        public String getUsertimeset() {
            return usertimeset;
        }

        public void setUsertimeset(String usertimeset) {
            this.usertimeset = usertimeset;
        }

        public String getWildFinger() {
            return wildFinger;
        }

        public void setWildFinger(String wildFinger) {
            this.wildFinger = wildFinger;
        }
    }
}
