package com.zumumu.imumu.ui.home.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zumumu.imumu.R;
import com.zumumu.imumu.base.BaseLazyFragment;
import com.zumumu.imumu.ui.personcenter.activity.MyAddressActivity;
import com.zumumu.imumu.ui.shop.activity.MyIndentActivity;
import com.zumumu.imumu.ui.user.activity.PersonInfoActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC_p on 2017/1/5.
 */

public class PersonalCenterFragment extends BaseLazyFragment {


    @Bind(R.id.sdv_pcenter_headpic)
    SimpleDraweeView sdvPcenterHeadpic;
    @Bind(R.id.textView7)
    TextView textView7;
    @Bind(R.id.tv_pcenter_nickname)
    TextView tvPcenterNickname;
    @Bind(R.id.tv_pcenter_rank)
    TextView tvPcenterRank;
    @Bind(R.id.textView9)
    TextView textView9;   //个人资料
    @Bind(R.id.tb_pcenter)
    Toolbar tbPcenter;
    @Bind(R.id.ctl_pcenter)
    CollapsingToolbarLayout ctlPcenter;
    @Bind(R.id.appbar_pcenter)
    AppBarLayout appbarPcenter;
    @Bind(R.id.tv_pcenter_myindent)
    TextView tvPcenterMyindent;
    @Bind(R.id.tv_pcenter_address)
    TextView tvPcenterAddress;


    @Override
    protected void TriggleData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_personal_center;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        ctlPcenter.setCollapsedTitleTextColor(Color.WHITE);

        appbarPcenter.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    ctlPcenter.setTitle("个人中心");
                } else {
                    ctlPcenter.setTitle("");
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.textView9, R.id.tv_pcenter_myindent, R.id.tv_pcenter_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView9:
                startActivity(PersonInfoActivity.class);
                break;
            case R.id.tv_pcenter_myindent:
                startActivity(MyIndentActivity.class);
                break;
            case R.id.tv_pcenter_address:
                startActivity(MyAddressActivity.class);
                break;
        }
    }

}
