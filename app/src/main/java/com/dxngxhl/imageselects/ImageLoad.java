package com.dxngxhl.imageselects;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dxngxhl.imgselector.a.ImgSelectorLoader;

public class ImageLoad extends ImgSelectorLoader {
    @Override
    public void imageLoad(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

}
