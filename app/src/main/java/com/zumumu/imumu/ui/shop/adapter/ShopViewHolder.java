package com.zumumu.imumu.ui.shop.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zumumu.imumu.base.BaseViewHolder;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class ShopViewHolder extends BaseViewHolder {
    public ShopViewHolder(View itemView) {
        super(itemView);
    }

    public ShopViewHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    public static ShopViewHolder get(Context mContext, @LayoutRes int layoutRes, ViewGroup parent){
        View view = LayoutInflater.from(mContext).inflate(layoutRes,parent,false);
        return new ShopViewHolder(view,mContext);
    }

    public ShopViewHolder setImage(@IdRes int viewId, String imageUrl) {
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

    public ShopViewHolder setNameText(@IdRes int viewId, String title) {
        TextView tv = getView(viewId);
        tv.setText(title);
        return this;
    }

    public ShopViewHolder setPrice(@IdRes int bazaarViewId, @IdRes int memberViewId, String bazaar
            , String member) {
        TextView bazaarTv = getView(bazaarViewId);
        TextView memberTv = getView(memberViewId);
        bazaarTv.setText(bazaar);
        memberTv.setText(member);
        return this;
    }

    public ShopViewHolder setParticular(@IdRes int salesViewId
            , @IdRes int numViewId, String sales, String num) {
        TextView salesTv = getView(salesViewId);
        TextView numTv = getView(numViewId);
        salesTv.setText(sales);
        numTv.setText(num);
        return this;
    }
}
