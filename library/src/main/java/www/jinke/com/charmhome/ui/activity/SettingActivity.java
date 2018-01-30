package www.jinke.com.charmhome.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 17-11-7.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout rl_home_manager;
    RelativeLayout rl_home_create;
    RelativeLayout rl_home_scenes;
    RelativeLayout rl_home_automation;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        setRightVisibility(false);
    }


    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        rl_home_manager = findViewById(R.id.rl_home_manager);
        rl_home_create = findViewById(R.id.rl_home_create);
        rl_home_scenes = findViewById(R.id.rl_home_scenes);
        rl_home_automation = findViewById(R.id.rl_home_automation);
        rl_home_create.setOnClickListener(this);
        rl_home_manager.setOnClickListener(this);
        rl_home_scenes.setOnClickListener(this);
        rl_home_automation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_home_manager) {//家庭管理
            startActivity(new Intent(this, HomeMangerActivity.class));
        } else if (view.getId() == R.id.rl_home_create) {//房间管理
            startActivity(new Intent(this, CreateHomeActivity.class));
        } else if (view.getId() == R.id.rl_home_scenes) {//场景管理
            startActivity(new Intent(this, ScenesManagerActivity.class));
        } else if (view.getId() == R.id.rl_home_automation) {//自动化管理
            startActivity(new Intent(this, AutomationActivity.class));
        }
    }
}
