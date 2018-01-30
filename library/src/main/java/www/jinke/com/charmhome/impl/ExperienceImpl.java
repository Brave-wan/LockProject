package www.jinke.com.charmhome.impl;

import android.content.Context;

import www.jinke.com.charmhome.listener.lock.IEditFingerListener;
import www.jinke.com.charmhome.listener.lock.IExperienceBiz;

/**
 * Created by root on 17-12-11.
 */

public class ExperienceImpl implements IExperienceBiz {
    private Context mContext;

    public ExperienceImpl(Context mContext) {
        this.mContext = mContext;
    }
}
