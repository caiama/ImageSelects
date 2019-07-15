package com.dxngxhl.imageselection;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * Created by Ma
 * on 2019/7/12
 */
public interface ImageLoaderInterface<T extends View> extends Serializable {

    void displayImage(Context context, Object path, T imageView);

    T createImageView(Context context);
}
