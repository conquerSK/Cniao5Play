package com.cniao5.cniao5play.presenter;


import com.cniao5.cniao5play.bean.PageBean;
import com.cniao5.cniao5play.bean.Subject;
import com.cniao5.cniao5play.bean.SubjectDetail;
import com.cniao5.cniao5play.common.rx.RxHttpReponseCompat;
import com.cniao5.cniao5play.common.rx.subscriber.ErrorHandlerSubscriber;
import com.cniao5.cniao5play.common.rx.subscriber.ProgressSubcriber;
import com.cniao5.cniao5play.presenter.contract.SubjectContract;

import javax.inject.Inject;

import io.reactivex.Observer;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5market.presenter
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class SubjectPresenter extends BasePresenter<SubjectContract.ISubjectModel,SubjectContract.SubjectView> {


    @Inject
    public SubjectPresenter(SubjectContract.ISubjectModel iSubjectModel,
                            SubjectContract.SubjectView subjectView) {
        super(iSubjectModel, subjectView);
    }




    public void  getSubjects(int page){


        Observer subscriber =null;

        if(page ==0){

            subscriber= new ProgressSubcriber<PageBean<Subject>>(mContext,mView) {
                @Override
                public void onNext(PageBean<Subject> pageBean) {
                    mView.showSubjects(pageBean);
                }
            };
        }
        else{
            subscriber = new ErrorHandlerSubscriber<PageBean<Subject>>(mContext) {
                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<Subject> pageBean) {

                    mView.showSubjects(pageBean);
                }
            };
        }

        mModel.getSubjects(page)
                .compose(RxHttpReponseCompat.<PageBean<Subject>>compatResult())
                .subscribe(subscriber);


    }



    public void getSubjectDetail(int id){


        mModel.getSubjectDetail(id).compose(RxHttpReponseCompat.<SubjectDetail>compatResult())
                .subscribe(new ProgressSubcriber<SubjectDetail>(mContext,mView) {
                    @Override
                    public void onNext(SubjectDetail subjectDetail) {

                        mView.showSubjectDetail(subjectDetail);
                    }
                });


    }










}
