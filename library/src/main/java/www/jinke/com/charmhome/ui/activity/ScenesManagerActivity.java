package www.jinke.com.charmhome.ui.activity;

import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.adapter.ScenesManagerAdapter;

/**
 * Created by root on 17-11-7.
 */

public class ScenesManagerActivity extends BaseActivity {
    private SmartRefreshLayout refresh;
    private ListView lv_scenes_manager;
    private ScenesManagerAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scenes_manager;
    }

    @Override
    protected void initView() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        adapter = new ScenesManagerAdapter(this, R.layout.item_scenes_manager, list);
        lv_scenes_manager.setAdapter(adapter);
        setRightVisibility(false);
        setTitleText("场景管理");
    }

    @Override
    protected void findViewById() {
        refresh = findViewById(R.id.refresh);
        lv_scenes_manager = findViewById(R.id.lv_scenes_manager);
    }
}
