package www.jinke.com.charmhome.ui.lock;

import android.view.View;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 17-12-11.
 * 添加设备失败
 */

public class LockAddFailActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_lock_add_fail;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.charmHome_lock_entrance));
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
    }
}
