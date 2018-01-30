package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {

    private Context context;
    private int layoutResId;
    public List<T> dataList;

    public CommonAdapter(@NonNull Context context, @LayoutRes int layoutResId, @NonNull List<T> dataList) {
        this.context = context;
        this.layoutResId = layoutResId;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = BaseViewHolder.getViewHolder(context, position, convertView, parent, layoutResId);
        convert(baseViewHolder, dataList.get(position),position);
        return baseViewHolder.getConvertView();
    }

    protected abstract void convert(BaseViewHolder baseViewHolder, T t, int position);
}

