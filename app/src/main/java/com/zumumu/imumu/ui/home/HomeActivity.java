package com.zumumu.imumu.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.base.BaseFragmentAdapter;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.home.fragment.HomeFragment;
import com.zumumu.imumu.ui.home.fragment.PersonalCenterFragment;
import com.zumumu.imumu.ui.home.fragment.ShopCartFragment;
import com.zumumu.imumu.ui.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC_p on 2016/12/27.
 * 应用程序主界面
 */

public class HomeActivity extends BaseActivity {


    @Bind(R.id.nsvp_home)
    NoScrollViewPager nsvpHome;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_community)
    RadioButton rbCommunity;
    @Bind(R.id.rb_cart)
    RadioButton rbCart;
    @Bind(R.id.rb_center)
    RadioButton rbCenter;
    @Bind(R.id.rg_home)
    RadioGroup rgHome;
    private long exitTime;//记录点击返回键事件
    private List<Fragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initFragment();
        SetStatusBarColor();
        BaseFragmentAdapter myAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList);
        nsvpHome.setAdapter(myAdapter);
        rbHome.setChecked(true);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ShopCartFragment());
        fragmentList.add(new PersonalCenterFragment());

    }


    //当用户连续按两次返回键的时候弹出是否退出对话框
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (nsvpHome.getCurrentItem() == 0 ) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Object mHelperUtils;
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }else {
                nsvpHome.setCurrentItem(0);
                rbHome.setChecked(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.rb_home, R.id.rb_community, R.id.rb_cart, R.id.rb_center})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
             nsvpHome.setCurrentItem(0,false);
                break;
            case R.id.rb_community:

                break;
            case R.id.rb_cart:
             nsvpHome.setCurrentItem(1,false);
                break;
            case R.id.rb_center:
             nsvpHome.setCurrentItem(2,false);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HomeFragment homeFragment =  (HomeFragment)fragmentList.get(0);
        if(homeFragment.getmAdapter() != null){
            homeFragment.getmAdapter().setStop(true);
        }
    }
}
