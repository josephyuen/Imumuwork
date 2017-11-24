package com.zumumu.imumu.ui.shop.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.base.BaseFragmentAdapter;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.shop.fragment.AllIndentFragment;
import com.zumumu.imumu.ui.shop.fragment.NeedAssessFragment;
import com.zumumu.imumu.ui.shop.fragment.NeedReceiptFragment;
import com.zumumu.imumu.ui.shop.fragment.NeedSendFragment;
import com.zumumu.imumu.ui.shop.fragment.NotPayFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PC_p on 2017/1/10.
 */

public class MyIndentActivity extends BaseActivity {

    @Bind(R.id.tv_toolbar)
    TextView tvToolbar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab_myindent)
    TabLayout tabMyindent;
    @Bind(R.id.vp_myindent)
    ViewPager vpMyindent;

    private List<Fragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_myindent;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvToolbar.setText("我的订单");

        String[] title = getResources().getStringArray(R.array.myindent);
        List<String> titles = Arrays.asList(title);
        tabMyindent.setTabMode(TabLayout.MODE_FIXED);
        tabMyindent.addTab(tabMyindent.newTab().setText(title[0]));
        tabMyindent.addTab(tabMyindent.newTab().setText(title[1]));
        tabMyindent.addTab(tabMyindent.newTab().setText(title[2]));
        tabMyindent.addTab(tabMyindent.newTab().setText(title[3]));
        tabMyindent.addTab(tabMyindent.newTab().setText(title[4]));
        //vpMyindent.setAdapter();

        initFragment();
        SetStatusBarColor();
        BaseFragmentAdapter myAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList,titles);
        vpMyindent.setAdapter(myAdapter);

        tabMyindent.setupWithViewPager(vpMyindent);

    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new AllIndentFragment());
        fragmentList.add(new NotPayFragment());
        fragmentList.add(new NeedSendFragment());
        fragmentList.add(new NeedReceiptFragment());
        fragmentList.add(new NeedAssessFragment());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

}
