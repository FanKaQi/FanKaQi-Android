package com.fkq.skill.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.fkq.common.activity.BaseActivity;
import com.fkq.skill.R;
import com.fkq.skill.constant.Constant;
import com.fkq.skill.fragment.SwipeGridFragment;
import com.fkq.skill.fragment.SwipeListFragment;

public class SkillActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_skill;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String skill = getIntent().getStringExtra("skill");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (skill.equals(Constant.SKILL_SWIPE_LIST)) {
            SwipeListFragment fragment = new SwipeListFragment();
            transaction.add(R.id.ll_fragment, fragment).commit();
        } else if (skill.equals(Constant.SKILL_SWIPE_GRID)) {
            SwipeGridFragment fragment = new SwipeGridFragment();
            transaction.add(R.id.ll_fragment, fragment).commit();
        }
    }
}
