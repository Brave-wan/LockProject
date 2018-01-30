package www.jinke.com.charmhome.impl;

import com.blankj.utilcode.util.LogUtils;
import com.dsm.xiaodi.biz.sdk.business.BusinessResponse;
import com.dsm.xiaodi.biz.sdk.business.deviceuser.FingerDelete;
import com.dsm.xiaodi.biz.sdk.servercore.ServerUnit;

import java.util.List;

import www.jinke.com.charmhome.listener.lock.IEditFingerBiz;
import www.jinke.com.charmhome.listener.lock.IEditFingerListener;

/**
 * Created by root on 17-12-13.
 */

public class EditFingerImpl implements IEditFingerBiz {
    @Override
    public void getFingerDelete(String macAddress, String fingerId, String fingerLockID, String fingerType, final IEditFingerListener listener) {
        new FingerDelete(macAddress, fingerId, fingerLockID, fingerType, new BusinessResponse() {
            @Override
            public void onSuccess(Object o) {
                LogUtils.i("删除指纹:" + o.toString());
                listener.showMsg("指纹删除成功!");
                listener.onDeleteSuccess("");
            }

            @Override
            public void onFailure(String s, int i) {
                LogUtils.i("删除指纹失败:" + s);
                listener.showMsg("指纹删除失败!");
            }
        }).walk();
    }

    @Override
    public void getUpdateFingerName(String fingerID, String fingerType, String fingerNewName, final IEditFingerListener listener) {
        ServerUnit.getInstance().updateFingerName(fingerID, fingerType, fingerNewName, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                LogUtils.i("更新指纹名称:" + s);
                listener.onUpdateSuccess(s);
            }

            @Override
            public void failure(String s, int i) {
                LogUtils.i("更新指纹名称失败:" + s);
                listener.showMsg("指纹更新失败!");
            }
        });
    }
}
