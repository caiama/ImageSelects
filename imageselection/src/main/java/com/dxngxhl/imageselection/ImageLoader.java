package com.dxngxhl.imageselection;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Ma
 * on 2019/7/12
 */
public abstract class ImageLoader {
    public abstract void imageLoad(Context context, Object path, ImageView imageView);
}
