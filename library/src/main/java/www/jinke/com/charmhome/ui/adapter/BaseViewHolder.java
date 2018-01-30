package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root on 17-8-1.
 */

public class BaseViewHolder {
    private View convertView;//adapter中getView() 返回的对象，用于与ListView的数据绑定
    private SparseArrayCompat<View> viewSparseArrayCompat = new SparseArrayCompat<>();

    public BaseViewHolder(View convertView) {
        this.convertView = convertView;
    }

    public static BaseViewHolder getViewHolder(Context context, int position, View convertView, ViewGroup parent, @LayoutRes int resource) {
        BaseViewHolder baseViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            baseViewHolder = new BaseViewHolder(convertView);
            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }
        return baseViewHolder;
    }

    public View getViewByViewId(@IdRes int viewId) {
        View view = viewSparseArrayCompat.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            viewSparseArrayCompat.put(viewId, view);
        }
        return view;
    }

    public View getConvertView() {
        return convertView;
    }
}
