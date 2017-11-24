package com.zumumu.imumu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nzf.mvpframe.base.BaseFragment;
import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;

/**
 * Created by PC_p on 2016/12/28.
 */

public abstract class BaseLazyFragment<T extends BasePresenter, E extends BaseModel> extends BaseFragment<T,E> {
    private boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            lazyLoad();
        }else{
            isVisible = false;
            onInvisible();
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    //当fragment界面不可见的时侯 do something
    private void onInvisible() {

    }


    private void lazyLoad() {
        if(!isPrepared || !isVisible || !isFirst){
            return;
        }
        TriggleData();
        isFirst = false;
    }

    /**
     * 触发fragment数据加载的方法
     */
    protected abstract void TriggleData();

}
