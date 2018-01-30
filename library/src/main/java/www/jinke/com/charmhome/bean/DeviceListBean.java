package www.jinke.com.charmhome.bean;

import java.io.Serializable;

/**
 * Created by root on 17-12-12.
 */

public class DeviceListBean implements Serializable {
    private String lockname;
    private String lockmac;
    private String softwareVersion;
    private String channelpassword;
    private int fingerunuserdnum;
    private int fingeruserdnum;
    private String usertimeset;
    private String manageaccount;
    private String metertype;
    private int lockPasswordstate;

    public void setLockPasswordstate(int lockPasswordstate) {
        this.lockPasswordstate = lockPasswordstate;
    }

    public int getLockPasswordstate() {
        return lockPasswordstate;
    }

    public void setMetertype(String metertype) {
        this.metertype = metertype;
    }

    public String getMetertype() {
        return metertype;
    }

    public String getUsertimeset() {
        return usertimeset;
    }

    public void setUsertimeset(String usertimeset) {
        this.usertimeset = usertimeset;
    }

    public String getManageaccount() {
        return manageaccount;
    }

    public void setManageaccount(String manageaccount) {
        this.manageaccount = manageaccount;
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

    public String getLockname() {
        return lockname;
    }

    public void setLockname(String lockname) {
        this.lockname = lockname;
    }

    public String getLockmac() {
        return lockmac;
    }

    public void setLockmac(String lockmac) {
        this.lockmac = lockmac;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getChannelpassword() {
        return channelpassword;
    }

    public void setChannelpassword(String channelpassword) {
        this.channelpassword = channelpassword;
    }
}
