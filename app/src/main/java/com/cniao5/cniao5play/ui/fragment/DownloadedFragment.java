package com.cniao5.cniao5play.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.cniao5.cniao5play.common.apkparset.AndroidApk;
import com.cniao5.cniao5play.di.component.AppComponent;
import com.cniao5.cniao5play.di.component.DaggerAppManagerComponent;
import com.cniao5.cniao5play.di.module.AppManagerModule;
import com.cniao5.cniao5play.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.ui.fragment
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class DownloadedFragment extends AppManagerFragment {




    AndroidApkAdapter mAdapter;

    @Override
    public void init() {
        super.init();


        mPresenter.getLocalApks();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APK);

        return mAdapter;
    }



    @Override
    public void showApps(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }
}
