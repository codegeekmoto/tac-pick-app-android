package com.tac.pickapp.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.tac.pickapp.app.dagger.AppComponent;
import com.tac.pickapp.app.dagger.ContextModule;
import com.tac.pickapp.app.dagger.DaggerAppComponent;

public class PickApp extends Application {

    private static PickApp INSTANCE;
    private static AppComponent APP_COMPONENT;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = PickApp.this;

        APP_COMPONENT = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        initImageLoader();
    }

    public static PickApp getInstance() {
        return INSTANCE;
    }

    public static AppComponent getComponent() {
        return APP_COMPONENT;
    }

    private void initImageLoader() {
        DisplayImageOptions defaultDisplayImageOptions = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .displayer(new FadeInBitmapDisplayer(250))
                .resetViewBeforeLoading(true)
                .build();

        //Initialize Universal Image Loader
        ImageLoaderConfiguration imgConfig = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(3)
                .memoryCache(new WeakMemoryCache())
                .threadPriority(Thread.MAX_PRIORITY)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .defaultDisplayImageOptions(defaultDisplayImageOptions)
                .discCacheSize(50 * 1024 * 1024)
                .build();

        ImageLoader.getInstance().init(imgConfig);
        ImageLoader.getInstance().handleSlowNetwork(true);
    }
}
