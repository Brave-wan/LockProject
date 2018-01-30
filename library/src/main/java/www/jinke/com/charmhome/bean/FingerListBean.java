package www.jinke.com.charmhome.bean;

import java.io.Serializable;

/**
 * Created by root on 17-10-19.
 */

public class FingerListBean implements Serializable {


    /**
     * deleteState : 0
     * fingerId : 17
     * fingerLockId : 00 00 00 09-00 00 00 0A-00 00 00 0B
     * fingerName : 指纹4
     * fingerNum : 0
     * fingerType : 1
     * item1 : [11][D700_1][A0.1.003_20160826]
     * item2 :
     * item3 :
     * item4 :
     * item5 :
     * lockSeq : FB:30:D6:D6:A5:8A
     * manageAccount : 18623558334
     * remark : 0
     * userAccont : 18623558334
     * userName :
     */

    private int deleteState;
    private int fingerId;
    private String fingerLockId;
    private String fingerName;
    private int fingerNum;
    private int fingerType;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String lockSeq;
    private String manageAccount;
    private String remark;
    private String userAccont;
    private String userName;

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public int getFingerId() {
        return fingerId;
    }

    public void setFingerId(int fingerId) {
        this.fingerId = fingerId;
    }

    public String getFingerLockId() {
        return fingerLockId;
    }

    public void setFingerLockId(String fingerLockId) {
        this.fingerLockId = fingerLockId;
    }

    public String getFingerName() {
        return fingerName;
    }

    public void setFingerName(String fingerName) {
        this.fingerName = fingerName;
    }

    public int getFingerNum() {
        return fingerNum;
    }

    public void setFingerNum(int fingerNum) {
        this.fingerNum = fingerNum;
    }

    public int getFingerType() {
        return fingerType;
    }

    public void setFingerType(int fingerType) {
        this.fingerType = fingerType;
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

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getLockSeq() {
        return lockSeq;
    }

    public void setLockSeq(String lockSeq) {
        this.lockSeq = lockSeq;
    }

    public String getManageAccount() {
        return manageAccount;
    }

    public void setManageAccount(String manageAccount) {
        this.manageAccount = manageAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserAccont() {
        return userAccont;
    }

    public void setUserAccont(String userAccont) {
        this.userAccont = userAccont;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
