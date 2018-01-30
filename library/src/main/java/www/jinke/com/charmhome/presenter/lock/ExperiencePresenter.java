package www.jinke.com.charmhome.presenter.lock;

import android.content.Context;

import www.jinke.com.charmhome.impl.ExperienceImpl;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.view.lock.IExperienceView;

/**
 * Created by root on 17-12-11.
 */

public class ExperiencePresenter  {
    private Context mContext;
    private IExperienceView experienceView;
    private ExperienceImpl experience;
    ACache aCache;

    public ExperiencePresenter(Context mContext, IExperienceView experienceView) {
        this.mContext = mContext;
        this.experienceView = experienceView;
        experience = new ExperienceImpl(mContext);
        aCache = ACache.get(mContext);
    }

    public void getLogin(String account, String pwd) {
        if (experienceView != null) {
            //小弟管家用户登陆
//            experience.getLogin(account, pwd, this);
//            //获取设备列表
//            experience.getLoadMainDeviceList(LuMiConfig.ACCOUNT, this);
        }

    }


}
