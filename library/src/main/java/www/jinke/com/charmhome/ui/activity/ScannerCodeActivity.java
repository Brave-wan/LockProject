package www.jinke.com.charmhome.ui.activity;

import android.content.Intent;
import android.graphics.PointF;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.presenter.lock.ScanningLockCodePresenter;

/**
 * Created by root on 17-12-14.
 */

public class ScannerCodeActivity extends BaseActivity implements QRCodeReaderView.OnQRCodeReadListener {
    private QRCodeReaderView qrCodeReaderView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scanner_code;
    }

    @Override
    protected void initView() {
        setTitleText("扫描二维码");
        qrCodeReaderView.setOnQRCodeReadListener(this);
        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);
        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);
        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);
        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();
        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();
    }

    @Override
    protected void findViewById() {
        qrCodeReaderView = findViewById(R.id.qrdecoderview);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        LogUtils.i("+text:" + text);
        Intent mIntent = new Intent();
        mIntent.putExtra("result", text);
        // 设置结果，并进行传送
        setResult(ScanningLockCodePresenter.REQUEST_CODE, mIntent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }
}
