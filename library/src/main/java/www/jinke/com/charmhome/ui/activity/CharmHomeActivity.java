package www.jinke.com.charmhome.ui.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.blankj.utilcode.util.StringUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.application.LuMiConfig;
import www.jinke.com.charmhome.bean.CharmHomeLoginBean;
import www.jinke.com.charmhome.bean.DeviceListBean;
import www.jinke.com.charmhome.bean.LockMainDeviceBean;
import www.jinke.com.charmhome.presenter.CharmHomePresenter;
import www.jinke.com.charmhome.ui.adapter.HomeListAdapter;
import www.jinke.com.charmhome.ui.lock.LockAddDeviceActivity;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.view.ICharmHomeView;
import www.jinke.com.charmhome.widget.HomeMangerPopupWindow;

/**
 * Created by root on 17-11-1.
 */

public class CharmHomeActivity extends BaseActivity implements ICharmHomeView, View.OnClickListener, OnRefreshListener, OnLoadmoreListener {
    private CharmHomePresenter presenter;
    private SmartRefreshLayout refreshLayout;
    private GridView gv_home_device;
    private List<DeviceListBean> list = new ArrayList<>();
    private HomeListAdapter homeListAdapter;
    private CharmHomeLoginBean charmHomeLoginBean;
    private ACache aCache;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_charm_home;
    }


    @Override
    protected void findViewById() {
        setTitleText(getString(R.string.charmHome_title));
        aCache = ACache.get(this);
        refreshLayout = findViewById(R.id.refreshLayout);
        gv_home_device = findViewById(R.id.lv_home_list);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        homeListAdapter = new HomeListAdapter(this, R.layout.item_home_list, list);
    }

    @Override
    protected void initView() {
        charmHomeLoginBean = (CharmHomeLoginBean) aCache.getAsObject("charmHomeLogin");
        setRightVisibility("", R.drawable.icon_charm_home_add_device);
        presenter = new CharmHomePresenter(this, this);
        gv_home_device.setAdapter(homeListAdapter);
        if (!StringUtils.isEmpty(charmHomeLoginBean.getLockUser())
                && !StringUtils.isEmpty(charmHomeLoginBean.getLockPsw())) {
            presenter.getLoadMainDeviceList(LuMiConfig.ACCOUNT);
        }
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        startActivity(new Intent(this, LockAddDeviceActivity.class));
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
    protected void onCenterView(View view) {
        super.onCenterView(view);
        HomeMangerPopupWindow window = new HomeMangerPopupWindow(this);
        window.showWindow(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!StringUtils.isEmpty(charmHomeLoginBean.getLockUser())
                && !StringUtils.isEmpty(charmHomeLoginBean.getLockPsw())) {
            //更新用户设备列表
            presenter.getLoadMainDeviceList(LuMiConfig.ACCOUNT);
        }
    }

    @Override
    public void onMainDeviceList(List<LockMainDeviceBean.LockBean> lock) {
        list.clear();
        for (LockMainDeviceBean.LockBean lockBean : lock) {
            DeviceListBean deviceListBean = new DeviceListBean();
            deviceListBean.setChannelpassword(lockBean.getChannelpassword());
            deviceListBean.setLockmac(lockBean.getLockmac());
            deviceListBean.setLockname(lockBean.getLockname());
            deviceListBean.setSoftwareVersion(lockBean.getSoftwareVersion());
            deviceListBean.setFingerunuserdnum(lockBean.getFingerunuserdnum());
            deviceListBean.setFingeruserdnum(lockBean.getFingeruserdnum());
            deviceListBean.setManageaccount(lockBean.getManageaccount());
            deviceListBean.setMetertype(lockBean.getMetertype());
            deviceListBean.setLockPasswordstate(lockBean.getLockPasswordstate());
            list.add(deviceListBean);
        }
        homeListAdapter.setDataList(list);
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //设置下拉刷新,动画执行结束的时间
        refreshlayout.finishRefresh(2000);
        if (charmHomeLoginBean != null) {
            if (!StringUtils.isEmpty(charmHomeLoginBean.getLockUser())
                    && !StringUtils.isEmpty(charmHomeLoginBean.getLockPsw())) {
                presenter.getLoadMainDeviceList(LuMiConfig.ACCOUNT);
            }
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore(2000);
    }
}
