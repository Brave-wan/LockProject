package www.jinke.com.charmhome.ui.activity;

import android.view.View;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 17-11-7.
 */

public class AddDeviceActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_device;
    }

    @Override
    protected void initView() {
        setTitleText("添加设备");
        setRightVisibility("保存");
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        finish();
    }

    @Override
    protected void findViewById() {

    }
}
