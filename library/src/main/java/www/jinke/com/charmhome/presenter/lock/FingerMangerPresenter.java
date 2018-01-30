package www.jinke.com.charmhome.presenter.lock;

import com.blankj.utilcode.util.LogUtils;
import com.dsm.xiaodi.biz.sdk.servercore.ServerUnit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import www.jinke.com.charmhome.bean.FingerListBean;
import www.jinke.com.charmhome.view.lock.IFingerMangerView;

/**
 * Created by root on 17-12-13.
 */

public class FingerMangerPresenter {
    private IFingerMangerView mangerView;

    public FingerMangerPresenter(IFingerMangerView mangerView) {
        this.mangerView = mangerView;
    }

    public void getLoadFingerList(final String mac, String mobile) {
        ServerUnit.getInstance().loadFingerList(mac, mobile, null, new ServerUnit.OnServerUnitListener() {
            @Override
            public void success(List list, String s) {
                if (mangerView != null) {
                    LogUtils.i("finger list:" + list.toString());
                    Type type = new TypeToken<List<List<FingerListBean>>>() {
                    }.getType();
                    List<List<FingerListBean>> listDevice = new Gson().fromJson(list.toString(), type);
                    if (listDevice != null && listDevice.size() > 0) {
                        mangerView.onFingerList(listDevice.get(0));
                        mangerView.hideLoading();
                    }
                }
            }

            @Override
            public void failure(String s, int i) {
                if (mangerView!=null){
                    mangerView.showMsg(s);
                    mangerView.hideLoading();
                }
            }
        });

    }
}
