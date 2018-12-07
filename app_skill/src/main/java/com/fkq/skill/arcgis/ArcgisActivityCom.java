package com.fkq.skill.arcgis;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.fkq.common.activity.ComBaseActivity;
import com.fkq.skill.R;

/**
 * Created by Administrator on 2018/5/29.
 */

public class ArcgisActivityCom extends ComBaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_arcgis;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        String name = getIntent().getStringExtra("name");
        Fragment fragment = null;
        if (name.equals("ArcgisFragment1")) {
            fragment = new ArcgisFragment1();
        } else if (name.equals("ArcgisFragment2")) {
            fragment = new ArcgisFragment2();
        } else if (name.equals("ArcgisFragment3")) {
            fragment = new ArcgisFragment3();
        }else if (name.equals("ArcgisFragment4")) {
            fragment = new ArcgisFragment4();
        }else if (name.equals("ArcgisFragment5")) {
            fragment = new ArcgisFragment5();
        }else if (name.equals("ArcgisFragment6")) {
            fragment = new ArcgisFragment6();
        }else if (name.equals("ArcgisFragment7")) {
            fragment = new ArcgisFragment7();
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }
}
