package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.DeviceListBean;
import www.jinke.com.charmhome.ui.lock.DeviceAttributesActivity;
import www.jinke.com.charmhome.utils.ACache;

/**
 * Created by root on 17-11-6.
 */

public class HomeListAdapter extends CommonAdapter<DeviceListBean> {
    private Context context;
    ACache aCache;

    public HomeListAdapter(@NonNull Context context, int layoutResId, @NonNull List<DeviceListBean> dataList) {
        super(context, layoutResId, dataList);
        this.context = context;
        aCache = ACache.get(context);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final DeviceListBean bean, int position) {
        ImageView img_right_dian = (ImageView) baseViewHolder.getViewByViewId(R.id.img_right_dian);
        TextView tx_lock_item_name = (TextView) baseViewHolder.getViewByViewId(R.id.tx_lock_item_name);
        tx_lock_item_name.setText(bean.getLockname());
        img_right_dian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeviceAttributesActivity.class);
                intent.putExtra("deviceBean", bean);
                aCache.put("deviceBean", bean, ACache.MAX_SIZE);
                context.startActivity(intent);
            }
        });
    }


}
