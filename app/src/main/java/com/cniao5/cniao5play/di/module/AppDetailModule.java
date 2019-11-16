package com.cniao5.cniao5play.di.module;

import com.cniao5.cniao5play.data.CagegoryModel;
import com.cniao5.cniao5play.data.http.ApiService;
import com.cniao5.cniao5play.presenter.contract.AppInfoContract;
import com.cniao5.cniao5play.presenter.contract.CategoryContract;

import dagger.Module;
import dagger.Provides;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.di
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */




@Module(includes = {AppModelModule.class})
public class AppDetailModule {



    private AppInfoContract.AppDetailView mView;

    public AppDetailModule(AppInfoContract.AppDetailView view){


        this.mView = view;
    }





    @Provides
    public AppInfoContract.AppDetailView provideView(){

        return mView;
    }












}
