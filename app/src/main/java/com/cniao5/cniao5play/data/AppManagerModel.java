package com.cniao5.cniao5play.data;

import android.content.Context;

import com.cniao5.cniao5play.common.Constant;
import com.cniao5.cniao5play.common.apkparset.AndroidApk;
import com.cniao5.cniao5play.common.util.ACache;
import com.cniao5.cniao5play.common.util.AppUtils;
import com.cniao5.cniao5play.presenter.contract.AppManagerContract;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.data
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class AppManagerModel implements AppManagerContract.IAppManagerModel {



    private  RxDownload mRxDownload;
    private Context mContext;

    public AppManagerModel(Context context,RxDownload rxDownload){

        this.mContext =context;

        mRxDownload = rxDownload;
    }

    @Override
    public Observable<List<DownloadRecord>> getDownloadRecord() {
        return mRxDownload.getTotalDownloadRecords();
    }

    @Override
    public RxDownload getRxDownload() {
        return mRxDownload;
    }

    @Override
    public Observable<List<AndroidApk>> getLocalApks() {

        final String dir = ACache.get(mContext).getAsString(Constant.APK_DOWNLOAD_DIR);

        return Observable.create(new ObservableOnSubscribe<List<AndroidApk>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AndroidApk>> e) throws Exception {

                e.onNext(scanApks(dir));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<AndroidApk>> getInstalledApps() {


        return Observable.create(new ObservableOnSubscribe<List<AndroidApk>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AndroidApk>> e) throws Exception {
                e.onNext(AppUtils.getInstalledApps(mContext));

                e.onComplete();
            }
        });
    }


    private List<AndroidApk> scanApks(String dir){


        File file = new File(dir);


        if(!file.isDirectory()){

            throw  new RuntimeException("is not Dir");
        }



       File[] apks =file.listFiles(new FileFilter(){


            @Override
            public boolean accept(File f) {


                if(f.isDirectory()){
                    return  false;
                }

                return f.getName().endsWith(".apk");
            }
        });


        List<AndroidApk>  androidApks = new ArrayList<>();

        for (File apk : apks){

            AndroidApk androidApk = AndroidApk.read(mContext,apk.getPath());
            if(androidApk !=null)
                androidApks.add(androidApk);
        }


        return androidApks;


    }


}
