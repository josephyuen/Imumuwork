package com.zumumu.imumu.ui.pay.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zumumu.imumu.R;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.base.BaseRecylerAdapter;
import com.zumumu.imumu.base.BaseViewHolder;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class AccountsAdpter extends BaseRecylerAdapter<GenerateIndentBean.OrderGoodsListBean,AccountsAdpter.GoodsHolder> {


    public AccountsAdpter(Context mContext, @LayoutRes int mLayoutRes, List<com.zumumu.imumu.ui.shop.model.GenerateIndentBean.OrderGoodsListBean> mList) {
        super(mContext, mLayoutRes, mList);
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return GoodsHolder.get(mContext,R.layout.item_account_goods,parent);
    }

    @Override
    protected void convert(GoodsHolder holder, GenerateIndentBean.OrderGoodsListBean orderGoodsListBean) {
        holder.setImage(R.id.simpleDraweeView, ApiConstant.HOME_URL + orderGoodsListBean.getGoods_play_img());
        holder.setNameText(R.id.tv_accounts_goods_name,orderGoodsListBean.getGoods_name());
        holder.setPrice(R.id.tv_accounts_goods_price,R.id.tv_accounts_goods_originalprice,
                "会员价：￥" +orderGoodsListBean.getGoods_vip_price(), "原价：￥" + orderGoodsListBean.getGoods_price());
        holder.setWeight(R.id.tv_accounts_goods_weight,"产品规格：" + orderGoodsListBean.getGoods_weight()+"克");
        holder.setNum(R.id.tv_accounts_num,R.id.tv_accounts_goods_num,R.id.tv_accounts_goods_number
                ,orderGoodsListBean.getGoods_num()+ "");
        holder.setTotal(R.id.tv_accounts_subtotal,orderGoodsListBean.getGoods_total() + "");
    }




    static class GoodsHolder extends BaseViewHolder{

        private Context mContext;
        private SparseArray<View> mView;

        public GoodsHolder(View itemView) {
            super(itemView);
            mView = new SparseArray<>();
        }

        public GoodsHolder(View itemView,Context mContext){
            this(itemView);
            this.mContext = mContext;
        }

        public static GoodsHolder get(Context mContext, @LayoutRes int layoutRes, ViewGroup parent){
            View view = LayoutInflater.from(mContext).inflate(layoutRes,parent,false);
            return new GoodsHolder(view,mContext);
        }

        public View getItemView(){
            return itemView;
        }

        public GoodsHolder setNameText(@IdRes int viewId, String title) {
            TextView tv = getView(viewId);
            tv.setText(title);
            return this;
        }

        public GoodsHolder setImage(@IdRes int viewId, String imageUrl) {
            SimpleDraweeView sdv = getView(viewId);
            Uri uri = Uri.parse(imageUrl);
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setProgressiveRenderingEnabled(true)
                    .build();
            // 构建显示图片所用到的DraweeController
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(sdv.getController())
                    .build();
            sdv.setController(controller);
            return this;
        }

        public GoodsHolder setPrice(@IdRes int bazaarViewId, @IdRes int memberViewId, String bazaar
                , String member) {
            TextView bazaarTv = getView(bazaarViewId);
            TextView memberTv = getView(memberViewId);
            bazaarTv.setText(bazaar);
            memberTv.setText(member);
            return this;
        }

        public GoodsHolder setNum(@IdRes int numViewId
                , @IdRes int num2ViewId,@IdRes int num3ViewId, String num) {
            TextView numTv = getView(numViewId);
            TextView num2Tv = getView(num2ViewId);
            TextView num3Tv = getView(num3ViewId);
            numTv.setText(num);
            num2Tv.setText("X" + num);
            num3Tv.setText("共"+ num + "件商品");
            return this;
        }

        public GoodsHolder setWeight(@IdRes int weightViewId, String weight) {
            TextView totalTv = getView(weightViewId);
            totalTv.setText(weight);
            return this;
        }

        public GoodsHolder setTotal(@IdRes int totalViewId, String total) {
            TextView totalTv = getView(totalViewId);
            totalTv.setText(total);
            return this;
        }
    }
}
