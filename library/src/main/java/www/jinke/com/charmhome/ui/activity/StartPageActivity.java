package www.jinke.com.charmhome.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;

import java.util.HashMap;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionSuccess;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.presenter.lock.StartPagePresenter;
import www.jinke.com.charmhome.ui.dialog.InputHomeNameDialog;
import www.jinke.com.charmhome.ui.lock.IStartPageView;

import static com.base.util.ToastUtil.showToast;

/**
 * Created by root on 17-11-28.
 */

public class StartPageActivity extends Activity implements View.OnClickListener, IStartPageView {
    private ImageView img_experience;
    private ImageView img_start_charm_life;
    private InputHomeNameDialog dialog;
    private ImageView img_charm_loading;
    private StartPagePresenter pagePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        initView();
    }

    private void initView() {
        img_experience = findViewById(R.id.img_experience_life);
        img_start_charm_life = findViewById(R.id.img_start_charm_life);
        img_charm_loading = findViewById(R.id.img_charm_loading);
        img_start_charm_life.setOnClickListener(this);
        img_experience.setOnClickListener(this);
        pagePresenter = new StartPagePresenter(this, this);
        Map<String, String> map = new HashMap<>();
        map.put("phone", LuMiConfig.ACCOUNT);
        pagePresenter.getCharmHomeLogin(map);
    }

    @Override
    public void onClick(View view) {
        //体验智能生活
        if (view.getId() == R.id.img_experience_life) {
            startActivity(new Intent(StartPageActivity.this, ExperienceActivity.class));
            //开启您的智慧生活
        } else if (view.getId() == R.id.img_start_charm_life) {
            startActivity(new Intent(StartPageActivity.this, CharmHomeActivity.class));
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LuMiConfig.Home_resultCode && data != null) {
            int result = data.getIntExtra("result", 0);
            switch (result) {
                //非正常流程，回到此页面的,进入首页不加载设备列表
                case LuMiConfig.input_pass_result_fail:
//                    startActivity(new Intent(this, CharmHomeActivity.class));
                    finish();
                    break;
                //进入输入密码页面，并完成登陆第三方平台流程，回到此页面，则进入首页加载设备列表
                case LuMiConfig.input_pass_result_success:
                    startActivity(new Intent(this, CharmHomeActivity.class));
                    finish();
                    break;
                default:
//                    startActivity(new Intent(this, CharmHomeActivity.class));
                    finish();
                    break;
            }
        }
    }

    @PermissionSuccess(requestCode = 107)
    public void cameraSuccess() {
        LogUtils.i("蓝牙权限权限获取成功，您可以使用蓝牙功能了");
    }

    @PermissionFail(requestCode = 107)
    public void cameraFail() {
        showToast("权限获取失败，请前往应用权限管理中进行授权!");
    }

    private AnimationDrawable animationDrawable;

    @Override
    public void showLoading(String msg) {
        img_start_charm_life.setClickable(false);
        img_charm_loading.setImageResource(R.drawable.charm_home_loading_init);
        animationDrawable = (AnimationDrawable) img_charm_loading.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void hideLoading() {
        if (animationDrawable != null) {
            AlphaAnimation mShowAnimation = new AlphaAnimation(1.0f, 0.0f);
            mShowAnimation.setDuration(1000);
            mShowAnimation.setFillAfter(true);
            img_charm_loading.startAnimation(mShowAnimation);
            mShowAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    img_charm_loading.setVisibility(View.GONE);
                    animationDrawable.stop();
                    img_start_charm_life.setBackgroundResource(R.drawable.icon_charm_start_loading_click);
                    img_start_charm_life.setClickable(true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }
}
