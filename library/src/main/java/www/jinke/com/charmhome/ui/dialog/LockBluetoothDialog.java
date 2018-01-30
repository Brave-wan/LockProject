package www.jinke.com.charmhome.ui.dialog;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kr.co.namee.permissiongen.PermissionGen;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.amin.BaseEffects;
import www.jinke.com.charmhome.ui.amin.Effectstype;

/**
 * Created by root on 17-12-11.
 * 门锁蓝牙提示对话框
 */

public class LockBluetoothDialog extends Dialog implements DialogInterface.OnShowListener, View.OnClickListener {
    private Effectstype type = null;
    private RelativeLayout relativeLayout;
    private int mDuration = 1000;
    private TextView tx_IKnow;
    private onIKnowListener listener;
    private ImageView img_lock_bluetooth;
    private TextView tx_lock_bluetooth_one;
    private TextView tx_lock_dialog_two;
    private Context mContext;

    public void setOnIKnowListener(onIKnowListener listener) {
        this.listener = listener;
    }

    public LockBluetoothDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_lock_bluetooth);
        relativeLayout = findViewById(R.id.rl_root);
        tx_IKnow = findViewById(R.id.tx_IKnow);
        img_lock_bluetooth = findViewById(R.id.img_lock_bluetooth);
        tx_lock_bluetooth_one = findViewById(R.id.tx_lock_bluetooth_one);
        tx_lock_dialog_two = findViewById(R.id.charmHome_lock_dialog_two);

        tx_IKnow.setOnClickListener(this);
        Window window = this.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        WindowManager manager = window.getWindowManager();
        Display display = manager.getDefaultDisplay();
        layoutParams.width = (int) (display.getWidth() * 0.9);
        layoutParams.y = -100;
        window.setAttributes(layoutParams);
        this.setOnShowListener(this);
        tx_IKnow.setOnClickListener(this);
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        if (type == null) {
            type = Effectstype.Sidefill;
            start(type);
        }
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(relativeLayout);
    }

    public void setContentImage(int img, String one, String two) {
        if (img_lock_bluetooth != null) {
            img_lock_bluetooth.setImageResource(img);
            tx_lock_bluetooth_one.setText(one);
            tx_lock_dialog_two.setText(two);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tx_IKnow && listener != null) {
            listener.IKowBack();
            this.dismiss();
        }
    }

    public interface onIKnowListener {
        void IKowBack();
    }
}
