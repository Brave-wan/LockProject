package www.jinke.com.charmhome.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.amin.BaseEffects;
import www.jinke.com.charmhome.ui.amin.Effectstype;

/**
 * Created by root on 17-11-29.
 */

public class InputHomeNameDialog extends Dialog implements DialogInterface.OnShowListener {
    private Context context;
    private Effectstype type = null;
    RelativeLayout relativeLayout;
    private int mDuration = 1000;

    public InputHomeNameDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_input_home_name);
        relativeLayout = findViewById(R.id.dialog_root);
        Window window = this.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        WindowManager manager = window.getWindowManager();
        Display display = manager.getDefaultDisplay();
        layoutParams.width = (int) (display.getWidth() * 0.8);
        layoutParams.y = -200;
        window.setAttributes(layoutParams);
        this.setOnShowListener(this);

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
}
