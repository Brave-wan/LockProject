package www.jinke.com.charmhome.view.lock;

import www.jinke.com.charmhome.view.BaseView;

/**
 * Created by root on 17-12-11.
 */

public interface IScanningLockCodeView extends BaseView {
    @Override
    void showLoading(String msg);

    @Override
    void hideLoading();

    void startInputDeviceName();
    void onInputPassWord(String account);
    void showMsg(String error);

    void startNext(boolean b);
}
