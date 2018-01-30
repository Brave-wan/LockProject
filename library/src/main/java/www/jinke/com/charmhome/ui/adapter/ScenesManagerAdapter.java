package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by root on 17-11-7.
 */

public class ScenesManagerAdapter extends CommonAdapter<String> {

    public ScenesManagerAdapter(@NonNull Context context, int layoutResId, @NonNull List<String> dataList) {
        super(context, layoutResId, dataList);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s, int position) {

    }
}
