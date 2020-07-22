package com.dxngxhl.imgselector.a;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Ma
 */
public abstract class ImgSelectorLoader {
    //加载图片
    public abstract void imageLoad(Context context, Object path, ImageView imageView);
}
