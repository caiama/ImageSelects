package com.dxngxhl.imageselection;

import android.content.Context;
import android.content.Intent;
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
 * 必须
 * setImageLoader
 *
 * setImageChoose
 *
 * 选择图片后必须传
 * addImagePath()
 *
 * 获取所有展示的图片
 * getImagePaths
 *
 * 选
 * setMaxCount
 * setNumColumn
 *
 * 修改默认图片和关闭默认图片
 * setImageAddResource
 *
 *
 * //setSelectionListener
 *
 *
 */
public class ImageSelectionView extends GridView {
    Context mContxt;
    public final static String SELECTION_TAG = "ADD";
    //最大可选择张数
    private int maxCount = 3,numColumns2;
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
    //
    ImageToView imageToView;
    //
    private ImageView.ScaleType scaleType;
    private int imageAddRous = R.drawable.ic_image_select_add,closeRous = R.drawable.ic_close;
    private int imageWidth;
    public ImageSelectionView(Context context) {
        super(context);
        mContxt = context;
    }

    public ImageSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContxt = context;
    }

    public ImageSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContxt = context;
    }
//
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageSelectionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContxt = context;
    }

    /**
     * 初始化
     */
    public void init(){
        setBackgroundColor(Color.WHITE);
        imagePaths.add(SELECTION_TAG);
        DisplayMetrics dm = mContxt.getResources().getDisplayMetrics();
        imageWidth = dm.widthPixels;
        imageSelectAdapter = new ImageSelectAdapter(
                imagePaths,
                mContxt,
                imageLoader,
                scaleType,
                imageAddRous,
                closeRous,
                imageWidth,
                numColumns2);
        setAdapter(imageSelectAdapter);

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SELECTION_TAG.equals(imagePaths.get(position))){
                    if (imageChoose != null){
                        imageChoose.imageChoose(maxCount - (imagePaths.size() - 1));
                    }
                }else {
                    if (imageToView != null){
                        imageToView.imageToView(mContxt,imagePaths.get(position));
                    }else {
                        mContxt.startActivity(new Intent(mContxt,ImageToVIewActivity.class).putExtra("path",imagePaths.get(position)));
                    }
                }
            }
        });
    }

    /**
     * 添加图片s(从外部)
     * @param list
     */
    public void addImagePaths(List<String> list){
        for (int i = 0; i < list.size(); i++) {
            imagePaths.add(imagePaths.size() - 1,list.get(i));
        }
        if (imagePaths.size() > maxCount){
            imagePaths.remove(SELECTION_TAG);
        }
        imageSelectAdapter.notifyDataSetChanged();
    }

    /**
     * 添加图片(从外部)
     * @param path
     */
    public void addImagePath(String path){
        imagePaths.add(imagePaths.size() - 1,path);
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
    public ImageSelectionView setMaxCount(int maxCount) {
        if (maxCount < 1) maxCount = 1;
        this.maxCount = maxCount;
        return this;
    }
    /**
     * 设置图片加载类
     * @param loader
     */
    public ImageSelectionView setImageLoader(ImageLoader loader) {
        imageLoader = loader;
        return this;
    }
    public ImageSelectionView setImageToView(ImageToView toView){
        imageToView = toView;
        return this;
    }
    /**
     *  设置图片选择
     * @param imageChoose
     */
    public ImageSelectionView setImageChoose(ImageChoose imageChoose) {
        this.imageChoose = imageChoose;
        return this;
    }

    /**
     * 设置加号的图片
     * @param rus
     */
    public ImageSelectionView setImageAddResource(int rus){
        imageAddRous = rus;
        return this;
    }

    /**
     * 设置关闭（删除图片）
     * @param rus
     */
    public ImageSelectionView setCloseResource(int rus){
        closeRous = rus;
        return this;
    }

    /**
     *
     * @param scaletype
     */
    public ImageSelectionView setScaleType(ImageView.ScaleType scaletype){
        scaleType = scaletype;
        return  this;
    }

    /**
     *
     * @param numColumns
     */
    public ImageSelectionView setNumColumn(int numColumns) {
        super.setNumColumns(numColumns);
        numColumns2 = numColumns;
        return this;
    }

//    public void setSelectionListener(ImageSelectionListener selectionListener) {
//        this.selectionListener = selectionListener;
//    }


}
