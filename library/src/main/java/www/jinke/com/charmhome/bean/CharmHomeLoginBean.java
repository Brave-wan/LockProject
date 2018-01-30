package www.jinke.com.charmhome.bean;

import java.io.Serializable;

/**
 * Created by root on 17-12-14.
 */

public class CharmHomeLoginBean implements Serializable{

    /**
     * phone : 18623558334
     * sessionid : cbb3f361edeb497080c9050be9c32706
     * lockUser : 18623558334
     * lockPsw :
     */

    private String phone;
    private String sessionid;
    private String lockUser;
    private String lockPsw;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getLockUser() {
        return lockUser;
    }

    public void setLockUser(String lockUser) {
        this.lockUser = lockUser;
    }

    public String getLockPsw() {
        return lockPsw;
    }

    public void setLockPsw(String lockPsw) {
        this.lockPsw = lockPsw;
    }
}
