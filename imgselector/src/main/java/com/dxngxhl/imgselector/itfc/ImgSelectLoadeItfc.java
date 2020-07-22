package com.dxngxhl.imgselector.itfc;

import android.content.Context;
import android.view.View;
import java.io.Serializable;

/**
 * Created by Ma
 */
public interface ImgSelectLoadeItfc<T extends View> extends Serializable {
    void displayImage(Context context, Object path, T imageView);
    T createImageView(Context context);
}
