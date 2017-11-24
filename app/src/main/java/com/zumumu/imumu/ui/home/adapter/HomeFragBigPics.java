package com.zumumu.imumu.ui.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.home.model.HomeBean;

/**
 * Created by PC_p on 2016/12/29.
 * 首页轮播图
 */

public class HomeFragBigPics extends PagerAdapter {
    private Context context;
    private HomeBean mHomeBean;
    public HomeFragBigPics(Context context,HomeBean homeBean){
        this.context = context;
        mHomeBean = homeBean;
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
        SimpleDraweeView sdv = new SimpleDraweeView(context);
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(ApiConstant.HOME_URL+mHomeBean.getData().get(0).getCarousel_img_arr().get(position % mHomeBean.getData().get(0).getCarousel_img_arr().size())))
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                  .setImageRequest(request)
                  .build();
        sdv.setController(controller);

        container.addView(sdv);
        return sdv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
