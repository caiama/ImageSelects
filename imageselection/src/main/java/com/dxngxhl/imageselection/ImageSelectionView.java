package com.dxngxhl.imageselection;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ma
 * on 2019/7/12
 *
 * setImageLoader
 *
 * setImagePath
 *
 * setMaxCount
 *
 * addImagePath()
 *
 * getImagePaths
 *
 * //setSelectionListener
 *
 *
 */
public class ImageSelectionView extends GridView {
    public final static String SELECTION_TAG = "ADD";
    //最大可选择张数
    private int maxCount = 3,numCols = 3;
    //图片
    List<String> imagePaths = new ArrayList<>();
    List<String> returnPaths = new ArrayList<>();
    //回调
    ImageSelectionListener selectionListener;
    //
    ImageSelectAdapter imageSelectAdapter;
    //
    ImageLoader imageLoader;
    //
    ImageChoose imageChoose;
    public ImageSelectionView(Context context) {
        super(context);
        init(context);
    }

    public ImageSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
//
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageSelectionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context){
        setBackgroundColor(Color.WHITE);
        imagePaths.add(SELECTION_TAG);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        imageSelectAdapter = new ImageSelectAdapter(imagePaths,context,dm.widthPixels / numCols);
        setAdapter(imageSelectAdapter);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SELECTION_TAG.equals(imagePaths.get(position))){
                    if (imageChoose != null){
                        imageChoose.imageChoose(maxCount - (imagePaths.size() - 1));
                    }
                }
            }
        });
    }

    /**
     * 设置图片加载类
     * @param loader
     */
    public void setImageLoader(ImageLoader loader) {
        imageLoader = loader;
        imageSelectAdapter.setImageLoader(imageLoader);
    }

    /**
     * 添加图片(从外部)
     * @param list
     */
    public void addImagePath(List<String> list){
        for (int i = 0; i < list.size(); i++) {
            imagePaths.add(imagePaths.size() - 1,list.get(i));
        }
        if (imagePaths.size() > maxCount){
            imagePaths.remove(SELECTION_TAG);
        }
        imageSelectAdapter.notifyDataSetChanged();
    }

    /**
     * 获取所有图片地址
     * @return
     */
    public List<String> getImagePaths() {
        returnPaths.clear();
        returnPaths.addAll(imagePaths);
        returnPaths.remove(SELECTION_TAG);
        return returnPaths;
    }

    /**
     * 设置最大图片数
     * @param maxCount：大于0
     */
    public void setMaxCount(int maxCount) {
        if (maxCount < 1) return;
        this.maxCount = maxCount;
    }


    /**
     *
     * @param numColumns
     */
    @Override
    public void setNumColumns(int numColumns) {
        numColumns = numColumns;
        super.setNumColumns(numColumns);
    }

    public void  setImageAddRou(){

    }

    /**
     * 设置加号的图片
     * @param rus
     */
    public void setImageAddResource(int rus){
        imageSelectAdapter.setImageAddResource(rus);
    }

    /**
     * 设置关闭（删除图片）
     * @param rus
     */
    public void setCloseResource(int rus){
        imageSelectAdapter.setCloseResource(rus);
    }

    public void setScaleType(ImageView.ScaleType scaleType){
        imageSelectAdapter.setScaleType(scaleType);
    }

    /**
     *  设置图片选择
     * @param imageChoose
     */
    public void setImageChoose(ImageChoose imageChoose) {
        this.imageChoose = imageChoose;
    }

    public void setSelectionListener(ImageSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }
}
