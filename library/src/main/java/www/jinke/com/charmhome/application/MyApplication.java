package www.jinke.com.charmhome.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.dsm.xiaodi.biz.sdk.XiaodiSdkLibInit;

/**
 * Created by root on 17-11-1.
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);
        XiaodiSdkLibInit.init(instance, "29");
    }
}
