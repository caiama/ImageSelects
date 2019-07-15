package com.dxngxhl.imageselects;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dxngxhl.imageselection.ImageLoader;

public class ImageLoad extends ImageLoader {
    @Override
    public void imageLoad(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

}
