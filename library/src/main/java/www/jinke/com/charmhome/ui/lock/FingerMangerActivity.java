package www.jinke.com.charmhome.ui.lock;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.DeviceListBean;
import www.jinke.com.charmhome.bean.FingerListBean;
import www.jinke.com.charmhome.presenter.lock.FingerMangerPresenter;
import www.jinke.com.charmhome.ui.adapter.FingerListAdapter;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.view.lock.IFingerMangerView;

/**
 * Created by root on 17-12-12.
 */

public class FingerMangerActivity extends BaseActivity implements IFingerMangerView, AdapterView.OnItemClickListener {
    ListView lv_finger_number;
    TextView tx_lock_finger_list_any;
    FingerMangerPresenter presenter;
    View list_empty;
    FingerListAdapter adapter;
    List<FingerListBean> list = new ArrayList<>();
    ACache aCache;
    DeviceListBean deviceListBean;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_finger_manager;
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.charmHome_locke_finger_manager));
        setRightVisibility("添加");
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        Intent intent = new Intent(this, InputFingerActivity.class);
        intent.putExtra("isOver", false);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById();
    }

    @Override
    protected void findViewById() {
        aCache = ACache.get(this);
        deviceListBean = (DeviceListBean) aCache.getAsObject("deviceBean");
        presenter = new FingerMangerPresenter(this);
        lv_finger_number = findViewById(R.id.lv_finger_number);
        list_empty = findViewById(R.id.lv_empty_view);
        tx_lock_finger_list_any = findViewById(R.id.tx_lock_finger_list_any);
        adapter = new FingerListAdapter(this, R.layout.item_finger_list, list);
        lv_finger_number.setAdapter(adapter);
        lv_finger_number.setOnItemClickListener(this);
        lv_finger_number.setEmptyView(list_empty);
        if (deviceListBean != null) {
//            showDialog("加载中");
            presenter.getLoadFingerList(deviceListBean.getLockmac(), deviceListBean.getManageaccount());
        }
    }

    @Override
    public void showLoading(String msg) {
        showDialog(msg);
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void onFingerList(List<FingerListBean> fingerListBeans) {
        adapter.setDataList(fingerListBeans);
        tx_lock_finger_list_any.setVisibility(fingerListBeans.size() > 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMsg(String s) {
        showToast(s);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FingerListBean listBean = (FingerListBean) adapter.getItem(i);
        Intent intent = new Intent(this, EditFingerActivity.class);
        intent.putExtra("listBean", listBean);
        startActivity(intent);
    }
}
