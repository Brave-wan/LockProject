package www.jinke.com.charmhome.ui.lock;

import android.view.View;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 17-12-11.
 */

public class LockAddFinishActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_lock_add_finish;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.charmHome_lock_add_finish_title));
        setRightVisibility(false);
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
