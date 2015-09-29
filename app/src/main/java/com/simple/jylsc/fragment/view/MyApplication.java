package com.simple.jylsc.fragment.view;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2015/9/29.
 */
public class MyApplication extends org.litepal.LitePalApplication {
    public void onCreate()
    {
        super.onCreate();
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .writeDebugLogs() //打印log信息
                .build();
        //初始化ImageLoader参数
        ImageLoader.getInstance().init(configuration);
    }
}
