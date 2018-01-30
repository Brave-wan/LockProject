package www.jinke.com.charmhome.ui.activity;

import android.view.View;

import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.bean.LockMainDeviceBean;
import www.jinke.com.charmhome.presenter.lock.ExperiencePresenter;
import www.jinke.com.charmhome.view.lock.IExperienceView;

/**
 * 体验页
 * Created by root on 17-11-28.
 */

public class ExperienceActivity extends BaseActivity implements IExperienceView {
    ExperiencePresenter presenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_experience_life;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.charmHome_virtual_family));
        setRightVisibility(false);
        presenter = new ExperiencePresenter(this, this);
        presenter.getLogin(LuMiConfig.ACCOUNT, LuMiConfig.PASSWORD);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {

    }

    @Override
    public void onMainDeviceList(List<LockMainDeviceBean.LockBean> lock) {

    }
}
