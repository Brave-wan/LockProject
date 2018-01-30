package www.jinke.com.charmhome.ui.lock;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionSuccess;
import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.presenter.lock.ScanningLockCodePresenter;
import www.jinke.com.charmhome.ui.activity.ScannerCodeActivity;
import www.jinke.com.charmhome.view.lock.IScanningLockCodeView;

/**
 * Created by root on 17-12-11.
 * 扫描门锁二维码
 */

public class ScanningLockCodeActivity extends BaseActivity implements View.OnClickListener, IScanningLockCodeView {
    private Button btn_scanning_code;
    private ScanningLockCodePresenter presenter;
    public static Activity instance;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_lock_scanning_code;
    }

    @Override
    protected void initView() {
        presenter = new ScanningLockCodePresenter(this, this);
        setTitleText(getString(R.string.charmHome_lock_entrance));
        instance = this;
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        btn_scanning_code = findViewById(R.id.btn_scanning_code);
        btn_scanning_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_scanning_code && isLogin) {
            Intent intent = new Intent(this, ScannerCodeActivity.class);
            startActivityForResult(intent, ScanningLockCodePresenter.REQUEST_CODE);
        } else {
            presenter.getLockLogin();
        }
    }

    boolean isLogin = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getScannerCode(requestCode, data);
        if (requestCode == LuMiConfig.Scanner_resultCode && data != null) {
            int result = data.getIntExtra("result", 0);
            switch (result) {
                case LuMiConfig.input_pass_result_fail:
                    finish();
                    break;
                case LuMiConfig.input_pass_result_success:
                    isLogin = true;
                    break;
                case 0:
                    finish();
                    break;
            }
        }
    }

    @Override
    public void showLoading(String msg) {
        showDialog(msg, true);
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void startInputDeviceName() {
        startActivity(new Intent(this, InputDeviceNameActivity.class));
    }

    @Override
    public void onInputPassWord(String account) {
        Intent intent = new Intent(this, InputLoginPassActivity.class);
        intent.putExtra("account", account);
        intent.putExtra("code", LuMiConfig.Scanner_resultCode);
        this.startActivityForResult(intent, LuMiConfig.Scanner_resultCode);
    }

    @Override
    public void showMsg(String error) {
        showToast(error);
    }

    @PermissionSuccess(requestCode = 106)
    public void cameraSuccess() {
        LogUtils.i("相机权限获取成功，您可以使用相机功能了");
    }

    @PermissionFail(requestCode = 106)
    public void cameraFail() {
        showToast("权限获取失败，请前往应用权限管理中进行授权!");
    }

    @Override
    public void startNext(boolean b) {
        isLogin = b;
    }

}
