package www.jinke.com.charmhome.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.activity.HomeMangerActivity;
import www.jinke.com.charmhome.ui.adapter.HomeMangerAdapter;

/**
 * Created by root on 17-11-6.
 */

public class HomeMangerPopupWindow extends PopupWindow {
    private LayoutInflater inflater;
    private Context mContext;
    private ListView lv_home_area, lv_home_name;
    private PopupWindow popupWindow;
    private HomeMangerAdapter adapter;
    private TextView tx_outside;
    private TextView tx_home_manager;
    private List<String> list = new ArrayList<>();

    public HomeMangerPopupWindow(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("江北");
        }
    }

    private void initView() {
        View view = inflater.inflate(R.layout.include_home_choose_home, null, false);
        lv_home_area = view.findViewById(R.id.lv_home_area);
        lv_home_name = view.findViewById(R.id.lv_home_name);
        tx_outside = view.findViewById(R.id.tx_outside);
        tx_home_manager = view.findViewById(R.id.tx_home_manager);
        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        adapter = new HomeMangerAdapter(mContext, R.layout.item_home_manager, list);
        lv_home_name.setAdapter(adapter);
        lv_home_area.setAdapter(adapter);
        tx_outside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissWindow();
            }
        });
        tx_home_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, HomeMangerActivity.class));

            }
        });
    }

    public void showWindow(View view) {
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAsDropDown(view);
        }
    }

    public void dismissWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }


}
