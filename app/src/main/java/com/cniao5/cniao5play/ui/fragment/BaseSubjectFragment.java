package com.cniao5.cniao5play.ui.fragment;

import com.cniao5.cniao5play.bean.PageBean;
import com.cniao5.cniao5play.bean.Subject;
import com.cniao5.cniao5play.bean.SubjectDetail;
import com.cniao5.cniao5play.di.component.AppComponent;
import com.cniao5.cniao5play.di.component.DaggerSubjectComponent;
import com.cniao5.cniao5play.di.module.SubjectModule;
import com.cniao5.cniao5play.presenter.SubjectPresenter;
import com.cniao5.cniao5play.presenter.contract.SubjectContract;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.ui.fragment
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public  abstract  class BaseSubjectFragment extends ProgressFragment<SubjectPresenter> implements SubjectContract.SubjectView {




    @Override
    public void showSubjects(PageBean<Subject> subjects) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {

    }


    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerSubjectComponent.builder().appComponent(appComponent).subjectModule(new SubjectModule(this))
                .build().inject(this);
    }
}
