package www.jinke.com.charmhome.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.adapter.HomeManagerListAdapter;

/**
 * Created by root on 17-11-7.
 */

public class HomeMangerActivity extends BaseActivity implements View.OnClickListener {
    SmartRefreshLayout refresh;
    ListView lv_home_list;
    TextView tx_delete_home;
    HomeManagerListAdapter adapter;
    List<String> list = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home_manager;
    }

    @Override
    protected void findViewById() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        refresh = findViewById(R.id.refresh);
        lv_home_list = findViewById(R.id.lv_home_list);
        tx_delete_home = findViewById(R.id.tx_delete_home);
    }

    @Override
    protected void initView() {
        setTitleText(getResources().getString(R.string.charmHome_home_manager));
        setRightVisibility("", R.drawable.icon_dh_add);
        adapter = new HomeManagerListAdapter(this, R.layout.item_home_manager_list, list);
        lv_home_list.setAdapter(adapter);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        startActivity(new Intent(this, CreateHomeActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tx_delete_home) {
            showToast("nice");
        }
    }
}
