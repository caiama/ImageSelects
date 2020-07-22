package com.dxngxhl.imgselector;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by Ma
 * 查看图片
 */
public class ImgShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        String path = getIntent().getStringExtra("path");
        imageView.setImageURI(Uri.parse(path));
//        imageView.setImageBitmap(BitmapFactory.decodeFile(path));
        setContentView(imageView);
    }
}
