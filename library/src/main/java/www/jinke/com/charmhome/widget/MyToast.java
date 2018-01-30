package www.jinke.com.charmhome.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import www.jinke.com.charmhome.R;

/**
 * Created by root on 17-12-14.
 */

public class MyToast {
    private Toast mToast;

    private MyToast(Activity context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_eplay_toast, null);
        TextView textView = v.findViewById(R.id.tx_toast);
        textView.setText(text);


        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
    }

    public static MyToast makeText(Activity context, CharSequence text, int duration) {
        return new MyToast(context, text, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }
}
