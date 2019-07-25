package com.dxngxhl.imageselects;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dxngxhl.imageselection.ImageSelectionView;
import com.zhihu.matisse.Matisse;

public class MainActivity extends AppCompatActivity {

    ImageSelectionView imageSelectionView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.getimages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("fffffffffff",imageSelectionView.getImagePaths().toString());
                Toast.makeText(MainActivity.this, imageSelectionView.getImagePaths().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        imageSelectionView = findViewById(R.id.imageselectionview);
        imageSelectionView
                .setMaxCount(9)
                .setImageChoose(new ImageSelect(this))
                .setImageLoader(new ImageLoad())
                .setNumColumn(4)
                .setScaleType(ImageView.ScaleType.CENTER_CROP)
                .init();
//        imageSelectionView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelect.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
//            List<Uri> result = Matisse.obtainResult(data);
            Log.d("ffffffff" ,"  =  " + Matisse.obtainPathResult(data));
            //清理前三个图片，(第四个是video)

            imageSelectionView.addImagePaths(Matisse.obtainPathResult(data));
            //不压缩时
            /**
             for (int i = 0; i < imageCaches.size(); i++) {
             imageBeans.get(i).setPath(imageCaches.get(i));
             }
             */
//            imageCompress(result);
        }
    }
}
