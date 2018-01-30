package www.jinke.com.charmhome.view.lock;

import www.jinke.com.charmhome.view.BaseView;

/**
 * Created by root on 17-12-13.
 */

public interface IDevicePassView extends BaseView{
    void onSuccess(String success);

    void showMsg(String s);
}
