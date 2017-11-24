package com.nzf.mvpframe.utils;

import java.io.File;

/**
 * Created by Administrator on 2016/11/27 0027.
 */

public interface OnDownLoadCallback {
    public void onError(String e);
    public void onProgress(long total, long progress);
    public void onSuccess(File file);
}
