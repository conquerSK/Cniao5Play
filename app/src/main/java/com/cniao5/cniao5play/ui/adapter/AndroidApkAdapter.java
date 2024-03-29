package com.cniao5.cniao5play.ui.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cniao5.cniao5play.R;
import com.cniao5.cniao5play.common.apkparset.AndroidApk;
import com.cniao5.cniao5play.common.rx.RxSchedulers;
import com.cniao5.cniao5play.common.util.AppUtils;
import com.cniao5.cniao5play.common.util.PackageUtils;
import com.cniao5.cniao5play.ui.widget.DownloadProgressButton;
import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5market.ui.adapter
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class AndroidApkAdapter extends BaseQuickAdapter<AndroidApk,BaseViewHolder> {


    public static final int FLAG_APK=0;
    public static final int FLAG_APP=1;

    private int flag ;

    public AndroidApkAdapter(int flag ) {
        super(R.layout.template_apk);

        this.flag = flag;

    }

    @Override
    protected void convert(BaseViewHolder helper, final AndroidApk item) {


        helper.setText(R.id.txt_app_name,item.getAppName());

        helper.setImageDrawable(R.id.img_app_icon,item.getDrawable());


        helper.addOnClickListener(R.id.btn_action);

        final DownloadProgressButton btn = helper.getView(R.id.btn_action);
        final TextView txtStatus = helper.getView(R.id.txt_status);


        if(flag == FLAG_APK){

            btn.setTag(R.id.tag_package_name,item.getPackageName());
            btn.setText("删除");


            RxView.clicks(btn).subscribe(new Consumer<Object>() {


                @Override
                public void accept(@NonNull Object o) throws Exception {


                    if(btn.getTag(R.id.tag_package_name).toString().equals(item.getPackageName())){

                        Object obj =  btn.getTag();

                        if(obj==null){



                            PackageUtils.install(mContext,item.getApkPath());

                        }
                        else{

                            boolean isInstall = (boolean) obj;
                            if(isInstall){
                                deleteApk(item);
                            }
                            else {
                                PackageUtils.install(mContext,item.getApkPath());
                            }
                        }
                    }

                }
            });

            isInstalled(mContext,item.getPackageName()).subscribe(new Consumer<Boolean>() {


                @Override
                public void accept(@NonNull Boolean aBoolean) throws Exception {
                    btn.setTag(aBoolean);

                    if(aBoolean){
                        txtStatus.setText("已安装");
                        btn.setText("删除");
                    }
                    else {
                        txtStatus.setText("等待安装");
                        btn.setText("安装");
                    }
                }
            });

        }
        else if(flag==FLAG_APP){


            btn.setText("卸载");
            RxView.clicks(btn).subscribe(new Consumer<Object>() {

                @Override
                public void accept(@NonNull Object o) throws Exception {

                    AppUtils.uninstallApk(mContext,item.getPackageName());
                }
            });


            txtStatus.setText("v"+item.getAppVersionName() +" " +(item.isSystem()?"内置":"第三方")); // size 加进来
        }












    }


    private void deleteApk(AndroidApk item){

        // 1. 删除下载记录
        // 2. 删除文件

//        FileUtils.deleteFile(item.getApkPath());


    }


    public Observable<Boolean> isInstalled(final Context context, final String packageName){


      return   Observable.create(new ObservableOnSubscribe<Boolean>() {


            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {

                e.onNext( AppUtils.isInstalled(context,packageName));
            }
        }).compose(RxSchedulers.<Boolean>io_main());


    }



}
