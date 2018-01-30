package www.jinke.com.charmhome.view;

/**
 * Created by root on 17-11-3.
 */

public interface IWifiConnectView {
    void showLoading();

    void hideLoading();

    /**
     * 授权成功状态
     * @param state
     */
    void authState(boolean state);
}
