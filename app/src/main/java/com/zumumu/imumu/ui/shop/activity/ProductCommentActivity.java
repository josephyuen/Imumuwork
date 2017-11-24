package com.zumumu.imumu.ui.shop.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nzf.mvpframe.base.BaseActivity;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.shop.adapter.ProductCommentAdapter;
import com.zumumu.imumu.ui.shop.contract.ProductDetailContract;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PC_p on 2017/1/5.
 */

public class ProductCommentActivity extends BaseActivity implements ProductDetailContract.ProductCommentSuperView {
    @Bind(R.id.iv_comment_back)
    ImageButton ivCommentBack;
    @Bind(R.id.tv_procomment_title)
    TextView tvProcommentTitle;
    @Bind(R.id.tv_procomment_num)
    TextView tvProcommentNum;
    @Bind(R.id.tv_procomment_stars)
    TextView tvProcommentStars;
    @Bind(R.id.rv_procomment)
    RecyclerView rvProcomment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_comment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvProcomment.setLayoutManager(manager);
        ProductCommentAdapter adapter = new ProductCommentAdapter(mContext);
        rvProcomment.setAdapter(adapter);

    }

    @Override
    public void returnProductCommentsChannels() {

    }

    @Override
    public void returnProductCommentsChannelsError(String msg) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
