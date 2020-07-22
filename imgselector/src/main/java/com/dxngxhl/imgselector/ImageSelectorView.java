package com.dxngxhl.imgselector;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.dxngxhl.imgselector.a.ImgSelectorLoader;
import com.dxngxhl.imgselector.a.ImgShow;
import com.dxngxhl.imgselector.itfc.ImgChooseItfc;
import com.dxngxhl.imgselector.itfc.ImgSelectionListener;

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
public class ImageSelectorView extends GridView {
    Context mContxt;
    public final static String SELECT_TAG = "ADD";
    //最大可选择张数
    private int maxCount = 3,numColumns2;
    //图片
    List<String> imagePaths = new ArrayList<>();
    List<String> returnPaths = new ArrayList<>();
    //
    ImgSelectorAdapter imgSelectAdapter;
    //
    ImgSelectorLoader imageLoader;
    //
    ImgChooseItfc imageChoose;
    //
    ImgShow imageShow;
    //
    private ImageView.ScaleType scaleType;
    private int addRous = R.drawable.ic_imgselect_def_add;
    private int closeRous = R.drawable.ic_close;
    private int imageWidth;
    public ImageSelectorView(Context context) {
        super(context);
        mContxt = context;
    }

    public ImageSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContxt = context;
    }

    public ImageSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContxt = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageSelectorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContxt = context;
    }

    /**
     * 初始化
     */
    public void init(){
        imagePaths.add(SELECT_TAG);
        DisplayMetrics dm = mContxt.getResources().getDisplayMetrics();
        imageWidth = dm.widthPixels;
        imgSelectAdapter = new ImgSelectorAdapter(
                mContxt,
                imagePaths,
                imageLoader,
                scaleType,
                addRous,
                closeRous,
                imageWidth,
                numColumns2);
        setAdapter(imgSelectAdapter);

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SELECT_TAG.equals(imagePaths.get(position))){
                    if (imageChoose != null){
                        imageChoose.imageChoose(maxCount - (imagePaths.size() - 1));
                    }
                }else {
                    if (imageShow != null){
                        imageShow.imageToView(mContxt,imagePaths.get(position));
                    }else {
                        mContxt.startActivity(new Intent(mContxt,ImgShowActivity.class).putExtra("path",imagePaths.get(position)));
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
            imagePaths.remove(SELECT_TAG);
        }
        imgSelectAdapter.notifyDataSetChanged();
    }

    /**
     * 添加图片(从外部)
     * @param path
     */
    public void addImagePath(String path){
        imagePaths.add(imagePaths.size() - 1,path);
        if (imagePaths.size() > maxCount){
            imagePaths.remove(SELECT_TAG);
        }
        imgSelectAdapter.notifyDataSetChanged();
    }

    /**
     * 获取所有图片地址
     * @return
     */
    public List<String> getImagePaths() {
        returnPaths.clear();
        returnPaths.addAll(imagePaths);
        returnPaths.remove(SELECT_TAG);
        return returnPaths;
    }

    /**
     * 设置最大图片数
     * @param maxCount：大于0
     */
    public ImageSelectorView setMaxCount(int maxCount) {
        if (maxCount < 1) maxCount = 1;
        this.maxCount = maxCount;
        return this;
    }
    /**
     * 设置图片加载类
     * @param loader
     */
    public ImageSelectorView setImgLoader(ImgSelectorLoader loader) {
        imageLoader = loader;
        return this;
    }
    public ImageSelectorView setImgShow(ImgShow toView){
        imageShow = toView;
        return this;
    }
    /**
     *  设置图片选择
     * @param imageChoose
     */
    public ImageSelectorView setImageChoose(ImgChooseItfc imageChoose) {
        this.imageChoose = imageChoose;
        return this;
    }

    /**
     * 设置添加图片
     */
    public ImageSelectorView setAddRes(int rus){
        addRous = rus;
        return this;
    }

    /**
     * 设置关闭（删除图片）
     */
    public ImageSelectorView setCloseRes(int rus){
        closeRous = rus;
        return this;
    }

    /**
     *
     * @param scaletype
     */
    public ImageSelectorView setScaleType(ImageView.ScaleType scaletype){
        scaleType = scaletype;
        return  this;
    }

    /**
     *
     * @param numColumns
     */
    public ImageSelectorView setNumColumn(int numColumns) {
        super.setNumColumns(numColumns);
        numColumns2 = numColumns;
        return this;
    }

    public void clear(){
        imagePaths.clear();
        imagePaths.add(SELECT_TAG);
        imgSelectAdapter.notifyDataSetChanged();
    }

    /**
     * 添加监听
     * @param selectionListener
     */
    public void setSelectionListener(ImgSelectionListener selectionListener) {
        imgSelectAdapter.setSelectionListener(selectionListener);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
