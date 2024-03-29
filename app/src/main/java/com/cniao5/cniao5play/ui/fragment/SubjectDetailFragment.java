package com.cniao5.cniao5play.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cniao5.cniao5play.R;
import com.cniao5.cniao5play.bean.Subject;
import com.cniao5.cniao5play.bean.SubjectDetail;
import com.cniao5.cniao5play.common.Constant;
import com.cniao5.cniao5play.common.imageloader.ImageLoader;
import com.cniao5.cniao5play.ui.adapter.AppInfoAdapter;
import com.cniao5.cniao5play.ui.widget.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import zlc.season.rxdownload2.RxDownload;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.ui.fragment
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class SubjectDetailFragment extends BaseSubjectFragment {


    @BindView(R.id.imageview)
    ImageView mImageView;
    @BindView(R.id.txt_desc)
    TextView mtxtDesc;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @Inject
    RxDownload mRxDownload;



    private Subject mSubject;

    private AppInfoAdapter mAdapter;


    public SubjectDetailFragment(Subject subject){

        this.mSubject = subject;

    }

    @Override
    public void init() {

        initRecycleView();

        mPresenter.getSubjectDetail(mSubject.getRelatedId());


    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {

        ImageLoader.load(Constant.BASE_IMG_URL + detail.getPhoneBigIcon(),mImageView);

        mtxtDesc.setText(detail.getDescription());

        mAdapter.addData(detail.getListApp());



    }

    private void initRecycleView() {

        mAdapter = AppInfoAdapter.builder().showBrief(false).showCategoryName(true)
                .rxDownload(mRxDownload).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        mRecyclerView.addItemDecoration(itemDecoration);

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_subject_detail;
    }
}
