package com.zumumu.imumu.ui.shop.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.shop.model.ProductDetailBean;

/**
 * Created by PC_p on 2017/1/4.
 * 商品详情的轮播图适配器
 */

public class ProDetailBigPicAdapter extends PagerAdapter {
    private Context mContext;
    private Activity mActivity;
    private ProductDetailBean bean;

    public ProDetailBigPicAdapter(Context context, Activity activity,ProductDetailBean bean){
        mActivity = activity;
        mContext = context;
        this.bean = bean;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView sdv = new SimpleDraweeView(mContext);
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(ApiConstant.HOME_URL+bean.getData().get(0).getPlay_img_arr().get(position%bean.getData().get(0).getPlay_img_arr().size())))
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build();
        sdv.setController(controller);
        sdv.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        container.addView(sdv);
        return sdv;


//        ImageView iv = new ImageView(mContext);
//        iv.setBackgroundResource(R.mipmap.image1);
//        container.addView(iv);
//        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
