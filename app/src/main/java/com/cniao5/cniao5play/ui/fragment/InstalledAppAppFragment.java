package com.cniao5.cniao5play.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;


import com.cniao5.cniao5play.common.apkparset.AndroidApk;
import com.cniao5.cniao5play.di.component.AppComponent;
import com.cniao5.cniao5play.di.component.DaggerAppManagerComponent;
import com.cniao5.cniao5play.di.module.AppManagerModule;
import com.cniao5.cniao5play.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstalledAppAppFragment extends AppManagerFragment {


    private  AndroidApkAdapter mAdapter;

    @Override
    public void init() {
        super.init();


        mPresenter.getInstalledApps();
    }
    @Override
    protected RecyclerView.Adapter setupAdapter() {

        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APP);

        return mAdapter;
    }




    @Override
    public void showApps(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }
}
