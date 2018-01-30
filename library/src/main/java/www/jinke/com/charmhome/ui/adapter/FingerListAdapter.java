package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.FingerListBean;


/**
 * Created by root on 17-10-19.
 */

public class FingerListAdapter extends CommonAdapter<FingerListBean> {
    Context context;

    public FingerListAdapter(@NonNull Context context, int layoutResId, @NonNull List<FingerListBean> dataList) {
        super(context, layoutResId, dataList);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FingerListBean fingerListBean, int position) {
        TextView tx_fingerName = (TextView) baseViewHolder.getViewByViewId(R.id.tx_item_finger_name);
        tx_fingerName.setText(fingerListBean.getFingerName());
    }

}
