package com.dxngxhl.imageselection;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Ma
 * on 2019/7/12
 */
public abstract class ImageLoader {
    //加载gridview图片
    public abstract void imageLoad(Context context, Object path, ImageView imageView);
}
