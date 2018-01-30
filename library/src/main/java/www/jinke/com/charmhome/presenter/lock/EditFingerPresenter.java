package www.jinke.com.charmhome.presenter.lock;

import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.impl.EditFingerImpl;
import www.jinke.com.charmhome.listener.lock.IEditFingerListener;
import www.jinke.com.charmhome.view.lock.IEditFingerView;

/**
 * Created by root on 17-12-13.
 */

public class EditFingerPresenter implements IEditFingerListener {
    private IEditFingerView editFingerView;
    private EditFingerImpl editFinger;

    public EditFingerPresenter(IEditFingerView editFingerView) {
        this.editFingerView = editFingerView;
        editFinger = new EditFingerImpl();
    }

    public void getFingerDelete(String macAddress, String fingerId, String fingerLockID, String fingerType) {
        if (editFingerView != null) {
            editFingerView.showLoading("指纹删除中");
            editFinger.getFingerDelete(macAddress, fingerId, fingerLockID, fingerType, this);
        }
    }

    public void getUpdateFingerName(String fingerID, String fingerType, String fingerNewName) {
        if (editFingerView != null) {
            editFinger.getUpdateFingerName(fingerID, fingerType, fingerNewName, this);
            editFingerView.showLoading("加载中");
        }
    }

    @Override
    public void onUpdateSuccess(String s) {
        if (editFingerView != null) {
            editFingerView.hideLoading();
            editFingerView.onUpdateSuccess(s);
        }
    }

    @Override
    public void showMsg(String s) {
        if (editFingerView != null) {
            editFingerView.showMsg(s);
            editFingerView.hideLoading();
        }
    }

    @Override
    public void onDeleteSuccess(String s) {
        if (editFingerView != null) {
            editFingerView.hideLoading();
            editFingerView.onUpdateSuccess(s);
        }
    }
}
