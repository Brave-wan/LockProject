package www.jinke.com.charmhome.ui.activity;

import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.adapter.AutomationManagerAdapter;

/**
 * Created by root on 17-11-7.
 */

public class AutomationActivity extends BaseActivity {
    ListView lv_automation_manager;
    SmartRefreshLayout refreshLayout;
    AutomationManagerAdapter adapter;

    private List<String> list = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_automation_manager;
    }

    @Override
    protected void initView() {
        setTitleText("自动化管理");
        setRightVisibility(false);
        list.add("");
        list.add("");
        lv_automation_manager = findViewById(R.id.lv_automation_manager);
        refreshLayout = findViewById(R.id.refresh);
        adapter = new AutomationManagerAdapter(this, R.layout.item_scenes_manager, list);
        lv_automation_manager.setAdapter(adapter);
    }

    @Override
    protected void findViewById() {

    }
}
