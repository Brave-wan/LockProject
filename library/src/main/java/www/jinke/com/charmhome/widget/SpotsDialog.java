package www.jinke.com.charmhome.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import www.jinke.com.charmhome.R;


/**
 * Created by Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * on 13.01.15 at 14:22
 */
public class SpotsDialog extends AlertDialog {

    private ImageView mLoadingImageView;
    private TextView tx_loading;

    public SpotsDialog(Context context) {
        this(context, R.style.SpotsDialogDefault);
    }

    public SpotsDialog(Context context, int theme) {
        super(context, theme);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View loadingView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null);
        mLoadingImageView = loadingView.findViewById(R.id.image_xiaobai);
        mLoadingImageView.setImageResource(R.drawable.loading_anim);
        tx_loading = loadingView.findViewById(R.id.tx_loading);
        setContentView(loadingView);
    }


    private AnimationDrawable mLoadingAnimationDrawable;

    @Override
    public void show() {
        super.show();
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.9f;
        window.setAttributes(lp);
        //注意将动画的启动放置在Handler中.否则只可看到第一张图片
        new Handler() {
        }.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingAnimationDrawable = (AnimationDrawable) mLoadingImageView.getDrawable();
                mLoadingAnimationDrawable.start();
            }
        }, 0);
    }

    public void setContent(String content) {
        if (tx_loading != null)
            tx_loading.setText(content);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //结束帧动画
        mLoadingAnimationDrawable = (AnimationDrawable) mLoadingImageView.getDrawable();
        mLoadingAnimationDrawable.stop();
    }
}

