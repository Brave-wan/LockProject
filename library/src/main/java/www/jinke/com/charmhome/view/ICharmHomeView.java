package www.jinke.com.charmhome.view;

import android.view.ViewGroup;

import java.util.List;

import www.jinke.com.charmhome.bean.LockMainDeviceBean;

/**
 * Created by root on 17-11-2.
 */

public interface ICharmHomeView extends BaseView {


    void onMainDeviceList(List<LockMainDeviceBean.LockBean> lock);

    void showMsg(String msg);
}
