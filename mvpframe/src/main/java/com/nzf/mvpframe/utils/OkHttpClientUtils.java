package com.nzf.mvpframe.utils;

import android.os.SystemClock;

import com.nzf.mvpframe.baseapp.BaseApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class OkHttpClientUtils {

    /**
     * @param map 请求参数
     * @return url
     */
    public static String getUrlParamsByMap(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("?");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }

        String params = sb.toString();
        if (params.endsWith("&")) {
            params = params.substring(0, params.length() - 1);
        }

        return params;
    }

    /**
     * @param url url地址
     * @param map 请求参数,无参数时可传入null
     * @param timeOut  设置超时时间
     * @param isCache  是否设置缓存
     * @param callback  请求后回调
     */
    public static void OKHttpGet(String url, Map<String, String> map, long timeOut,boolean isCache, Callback callback) {
        url = url + getUrlParamsByMap(map);
        System.out.println(url);
        OkHttpClient mHttpClient;
        Request request;
        if (isCache) {
            mHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new CacheInterceptor())
                    .cache(getCache())
                    .connectTimeout(timeOut, TimeUnit.MILLISECONDS)
                    .build();
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(60, TimeUnit.SECONDS)
                    .build();
            request = new Request.Builder()
                    .url(url)
                    .cacheControl(cacheControl)
                    .build();
        }else {
            mHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(timeOut, TimeUnit.MILLISECONDS)
                    .build();
            request = new Request.Builder()
                    .url(url)
                    .build();
        }
        Call call = mHttpClient.newCall(request);
        call.enqueue(callback);
//        try {
//            Response response = call.execute();
//            Log.i("OKHTTP", "testCache: response1 :"+response.body().string());
//            Log.i("OKHTTP", "testCache: response1 cache :"+response.cacheResponse());
//            Log.i("OKHTTP", "testCache: response1 network :"+response.networkResponse());
//            response.body().close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public static Headers OkHttpHeader(){
        Headers header = new Headers.Builder().add("appkey","d6f66ab9872a335bb481ff95c48341f7").build();
        return header;
    }

    //缓存设置
    public static Cache getCache(){
        int cacheSize = 30 * 1024 * 1024;
        Cache cache = new Cache(BaseApplication.getAppContext().getCacheDir(),cacheSize);
        return cache;
    }

    /**
     * @param url url地址
     * @param body 请求体
     * @param isCache 是否需要缓存
     * @param callback 请求回调
     */
    public static void OkHttpPost(String url, FormBody body,boolean isCache, Callback callback){
        OkHttpClient client;
        Request request;
        if (isCache) {
            client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new CacheInterceptor())
                    .cache(getCache())
                    .connectTimeout(3000, TimeUnit.MILLISECONDS)
                    .build();
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(60, TimeUnit.SECONDS)
                    .build();
            request = new Request.Builder()
                    .post(body).header("appkey","android")
                    .url(url)
                    .cacheControl(cacheControl)
                    .build();
        }else {
            client = new OkHttpClient.Builder()
                    .connectTimeout(3000, TimeUnit.MILLISECONDS)
                    .build();
            request = new Request.Builder()
                    .post(body).header("appkey","android")
                    .url(url)
                    .build();
        }
        client.newCall(request).enqueue(callback);
    }

    /**
     * 下载文件
     *
     * @param fileUrl  文件url
     * @param fileDir  存储目标目录
     * @param callback 回调接口
     */
    public static void OkHttpDownLoadFile(String fileUrl, File fileDir, final OnDownLoadCallback callback) {
        String fileName = SystemClock.uptimeMillis() + ".apk";
        final File file = new File(fileDir, fileName);
        OkHttpClient mHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(fileUrl).build();
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String error = e.toString();
                System.out.println(e.toString());
                callback.onError("下载失败，网络连接错误");
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream is = null;
                byte[] buffer = new byte[2048];
                int len = -1;
                FileOutputStream fos = null;
                long total = response.body().contentLength();
                long current = 0;
                is = response.body().byteStream();

                try {
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buffer)) != -1) {
                        current += len;
                        callback.onProgress(total, current);
                        fos.write(buffer, 0, len);
                    }
                    callback.onSuccess(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    callback.onError("找不到该文件.");
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onError("网络连接错误.");
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //缓存头信息拦截器
    public static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for 30 days
                    .header("Cache-Control", "max-age=" + 3600 * 24 * 30)
                    .build();
            return response1;
        }
    }

}
