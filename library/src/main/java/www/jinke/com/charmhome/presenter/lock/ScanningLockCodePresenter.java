package www.jinke.com.charmhome.presenter.lock;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;

import java.util.HashMap;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.application.MyApplication;
import www.jinke.com.charmhome.bean.CharmHomeLoginBean;
import www.jinke.com.charmhome.bean.LockAddDeviceBean;
import www.jinke.com.charmhome.bean.LockRequestAddDeviceBean;
import www.jinke.com.charmhome.bean.LockUserBean;
import www.jinke.com.charmhome.impl.ScanningLockCodeImpl;
import www.jinke.com.charmhome.listener.lock.IScanningLockCodListener;
import www.jinke.com.charmhome.ui.lock.InputLoginPassActivity;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;
import www.jinke.com.charmhome.view.lock.IScanningLockCodeView;
import www.jinke.com.charmhome.widget.MyToast;


/**
 * Created by root on 17-12-11.
 */

public class ScanningLockCodePresenter implements IScanningLockCodListener {
    public static final int REQUEST_CODE = 0x000001;
    private String[] macAddress;
    private IScanningLockCodeView scanningLockCodeView;
    private Activity mContext;
    private ScanningLockCodeImpl lockCode;
    private ACache aCache;
    private CharmHomeLoginBean charmHomeLoginBean;

    public ScanningLockCodePresenter(Activity mContext, IScanningLockCodeView scanningLockCodeView) {
        this.scanningLockCodeView = scanningLockCodeView;
        this.mContext = mContext;
        lockCode = new ScanningLockCodeImpl(mContext);
        aCache = ACache.get(mContext);
        charmHomeLoginBean = (CharmHomeLoginBean) aCache.getAsObject("charmHomeLogin");
        //登陆魅力家平台,获取门锁帐号和密码
        getLockLogin();
        getCameraPermission();
    }

    public void getLockLogin() {
        if (charmHomeLoginBean != null && scanningLockCodeView != null) {
            Map<String, String> map = new HashMap<>();
            map.put("phone", charmHomeLoginBean.getPhone());
            map.put("sessionId", charmHomeLoginBean.getSessionid());
            lockCode.getLockLogin(map, this);
            scanningLockCodeView.showLoading("正在初始化,请稍候");
        }
    }

    public void getCameraPermission() {
        PermissionGen.with(mContext)
                .addRequestCode(106)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();
    }


    public void getScannerCode(int requestCode, Intent data) {
        //扫描二维码返回的值
        if (requestCode == REQUEST_CODE && scanningLockCodeView != null) {
            if (null != data) {
                playSound(R.raw.beep);
                String result = data.getStringExtra("result");
                if (result.indexOf("http://dsmzg.com/app/?") != -1) {
                    String[] lockType;
                    macAddress = result.split("\\|");
                    lockType = macAddress[0].split("=");
                    SharedPreferencesUtils.init(mContext)
                            .put("lock_Meter_type", lockType[1])
                            .put("lock_pwd", macAddress[1])
                            .put("lock_mac", macAddress[2])
                            .put("lock_type", "LOCK")
                            .put("lock_lMode", charmHomeLoginBean.getLockUser())
                            .put("lock_mode", macAddress[4]);
                    LogUtils.i("解析结果:" + result);
                    scanningLockCodeView.startInputDeviceName();
                } else {
                    MyToast.makeText(mContext, "此二维码不合法,请重新扫描!", Toast.LENGTH_SHORT).show();
                }

            }
            //输入门锁密码界面返回的状态值
        }
    }

    public void playSound(int rawId) {
        Vibrator vib = (Vibrator) mContext.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(1000);

        SoundPool soundPool;
        if (Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder builder = new SoundPool.Builder();
            //传入音频的数量
            builder.setMaxStreams(1);
            //AudioAttributes是一个封装音频各种属性的类
            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            //设置音频流的合适属性
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
            builder.setAudioAttributes(attrBuilder.build());
            soundPool = builder.build();
        } else {
            //第一个参数是可以支持的声音数量，第二个是声音类型，第三个是声音品质
            soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
        }
        //第一个参数Context,第二个参数资源Id，第三个参数优先级
        soundPool.load(mContext, rawId, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(1, 1, 1, 0, 0, 1);
            }
        });
    }

    @Override
    public void onLockAddDeviceBean(LockAddDeviceBean lockAddDeviceBean) {

    }

    @Override
    public void showMsg(String error) {
        if (scanningLockCodeView != null) {
            scanningLockCodeView.showMsg(error);
            scanningLockCodeView.hideLoading();
        }
    }

    @Override
    public void onLockLogin(CharmHomeLoginBean bean) {
        if (bean != null) {
            lockCode.getLogin(bean.getLockUser(), bean.getLockPsw(), this);
        }
    }

    @Override
    public void onLoginSuccess(LockUserBean lockUserBean) {
        if (scanningLockCodeView != null) {
            charmHomeLoginBean.setLockUser(lockUserBean.getAccount());
            charmHomeLoginBean.setLockPsw(lockUserBean.getPassword());
            aCache.put("charmHomeLogin", charmHomeLoginBean, ACache.MAX_SIZE);
            scanningLockCodeView.hideLoading();
            scanningLockCodeView.startNext(true);
        }
    }

    @Override
    public void onUserEmpty(String account, String pass) {
        if (scanningLockCodeView != null) {
            lockCode.getUserRegister(account, pass, this);
        }
    }

    @Override
    public void onInputPassWord(String account) {
        if (scanningLockCodeView != null) {
            scanningLockCodeView.hideLoading();
            scanningLockCodeView.onInputPassWord(account);
        }
    }

    @Override
    public void onRegisterSuccess(String s) {
        if (scanningLockCodeView != null) {
            scanningLockCodeView.hideLoading();
            scanningLockCodeView.startNext(true);
            scanningLockCodeView.showMsg(s);
        }
    }

    @Override
    public void onUserRegisterFail(String s) {
        if (scanningLockCodeView != null) {
            scanningLockCodeView.showMsg(s);
            scanningLockCodeView.startNext(false);
        }

    }
}
