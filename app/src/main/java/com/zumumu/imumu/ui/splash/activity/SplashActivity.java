package com.zumumu.imumu.ui.splash.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nzf.mvpframe.base.BaseActivity;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.user.activity.LoginActivity;

import butterknife.Bind;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug　　
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 */
public class SplashActivity extends BaseActivity {
    @Bind(R.id.tv_splash)
    TextView tvSplashText;
    @Bind(R.id.iv_splash_logo)
    ImageView ivSplashLogo;
    @Bind(R.id.tv_splash_name)
    TextView tvSplashName;

    @Override
    public int getLayoutId() {
        return R.layout.acticity_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        SetStatusBarColor(getResources().getColor(R.color.colorPrimary));
        startSplashAnimator();
    }

    private void startSplashAnimator() {
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(ivSplashLogo, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(tvSplashText, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofPropertyValuesHolder(tvSplashName, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2,objectAnimator3);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(LoginActivity.class);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }


}
