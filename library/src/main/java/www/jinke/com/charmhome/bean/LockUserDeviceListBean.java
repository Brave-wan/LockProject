package www.jinke.com.charmhome.bean;

import java.io.Serializable;

/**
 * Created by root on 17-10-16.
 */

public class LockUserDeviceListBean implements Serializable {

    /**
     * iconurl :
     * id : 2
     * item1 : 1
     * item2 :
     * level : 0
     * lockseq : FB:30:D6:D6:A5:8A
     * loveAccountFlag : 0
     * manageaccount : 18623558334
     * nickname : 用户8334
     * remark : 锁具主人
     * remoteopenlock : 0
     * state : 1
     * time : 2017-12-12 15:48
     * type : 2
     * useraccount : 18623558334
     * useropentype : 2
     * usertimeset : 00:00:00-23:59:00#1-2-3-4-5-6-7#2016 01 01-2099 12 31
     */

    private String iconurl;
    private int id;
    private String item1;
    private String item2;
    private int level;
    private String lockseq;
    private int loveAccountFlag;
    private String manageaccount;
    private String nickname;
    private String remark;
    private int remoteopenlock;
    private int state;
    private String time;
    private int type;
    private String useraccount;
    private int useropentype;
    private String usertimeset;

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLockseq() {
        return lockseq;
    }

    public void setLockseq(String lockseq) {
        this.lockseq = lockseq;
    }

    public int getLoveAccountFlag() {
        return loveAccountFlag;
    }

    public void setLoveAccountFlag(int loveAccountFlag) {
        this.loveAccountFlag = loveAccountFlag;
    }

    public String getManageaccount() {
        return manageaccount;
    }

    public void setManageaccount(String manageaccount) {
        this.manageaccount = manageaccount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRemoteopenlock() {
        return remoteopenlock;
    }

    public void setRemoteopenlock(int remoteopenlock) {
        this.remoteopenlock = remoteopenlock;
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

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public int getUseropentype() {
        return useropentype;
    }

    public void setUseropentype(int useropentype) {
        this.useropentype = useropentype;
    }

    public String getUsertimeset() {
        return usertimeset;
    }

    public void setUsertimeset(String usertimeset) {
        this.usertimeset = usertimeset;
    }
}
