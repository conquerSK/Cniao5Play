package com.cniao5.cniao5play.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cniao5.cniao5play.ui.bean.FragmentInfo;
import com.cniao5.cniao5play.ui.fragment.CategoryAppFragment;
import com.cniao5.cniao5play.ui.fragment.CategoryFragment;
import com.cniao5.cniao5play.ui.fragment.GamesFragment;
import com.cniao5.cniao5play.ui.fragment.RecommendFragment;
import com.cniao5.cniao5play.ui.fragment.TopListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 2016/12/8.
 */

public class CategoryAppViewPagerAdapter extends FragmentStatePagerAdapter {





    private List<String> titles = new ArrayList<>(3);


    private int mCategoryId;
    public CategoryAppViewPagerAdapter(FragmentManager fm,int categoryid) {
        super(fm);
        this.mCategoryId = categoryid;

        titles.add("精品");
        titles.add("排行");
        titles.add("新品");
    }





    @Override
    public Fragment getItem(int position) {

        return new CategoryAppFragment(mCategoryId,position);

    }

    @Override
    public int getCount() {
        return titles.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
