package www.jinke.com.charmhome.view.lock;

import java.util.List;

import www.jinke.com.charmhome.bean.FingerListBean;
import www.jinke.com.charmhome.view.BaseView;

/**
 * Created by root on 17-12-13.
 */

public interface IFingerMangerView extends BaseView{
    void onFingerList(List<FingerListBean> fingerListBeans);

    void showMsg(String s);
}
