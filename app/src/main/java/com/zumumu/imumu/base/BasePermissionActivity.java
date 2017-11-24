package com.zumumu.imumu.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.nzf.mvpframe.baseapp.AppManager;
import com.nzf.mvpframe.utils.PermissionUtils;
import com.nzf.mvpframe.widget.StatusBarCompat;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class BasePermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        PermissionUtils.requestPermission(this,PermissionUtils.CODE_READ_PHONE_STATE,null);
    }

    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetTranslanteBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }

}
