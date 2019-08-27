package com.dxngxhl.imageselection.t2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.dxngxhl.imageselection.ImageChoose;
import com.dxngxhl.imageselection.ImageLoader;
import com.dxngxhl.imageselection.ImageToVIewActivity;
import com.dxngxhl.imageselection.ImageToView;
import com.dxngxhl.imageselection.R;

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
public class ImageSelectionView2 extends GridView {
    Context mContxt;
//    public final static String SELECTION_TAG = "ADD";
    //最大可选择张数
    private int maxCount = 3,numColumns2;
    //图片
    List<Bean> imagePaths = new ArrayList<>();
    List<Bean> returnPathBeans = new ArrayList<>();
    //回调
    ImageSelectionListener2 selectionListener;
    RemoveListener removeListener;
    //
    ImageSelectAdapter2 imageSelectAdapter;
    //
    ImageLoader imageLoader;
    //
    ImageChoose imageChoose;
    //
    ImageToView imageToView;
    //
    private ImageView.ScaleType scaleType;
    private int imageAddRous = R.drawable.ic_image_select_add,videoAddRous = R.drawable.ic_video_select,closeRous = R.drawable.ic_close;
    private int imageWidth;
    Bean addBean = new Bean(null,false);
    public ImageSelectionView2(Context context) {
        super(context);
        mContxt = context;
    }

    public ImageSelectionView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContxt = context;
    }

    public ImageSelectionView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContxt = context;
    }
//
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageSelectionView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContxt = context;
    }

    /**
     * 初始化
     */
    public void init(){
        setBackgroundColor(Color.WHITE);
        imagePaths.add(addBean);
        DisplayMetrics dm = mContxt.getResources().getDisplayMetrics();
        imageWidth = dm.widthPixels;
        imageSelectAdapter = new ImageSelectAdapter2(
                imagePaths,
                mContxt,
                imageLoader,
                scaleType,
                addBean,
                imageAddRous,
                videoAddRous,
                closeRous,
                imageWidth,
                numColumns2);
        setAdapter(imageSelectAdapter);

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (imagePaths.get(position).getImagePath() == null){
                    if (imagePaths.get(position).isVideo()){
                        imageChoose.videoChoose();
                    }else {
                        imageChoose.imageChoose(maxCount - (imagePaths.size() - 1));
                    }
                }else {
                    if (!imagePaths.get(position).isVideo()){
                        if (imageToView != null){
                            imageToView.imageToView(mContxt,imagePaths.get(position).getImagePath());
                        }else {
                            mContxt.startActivity(new Intent(mContxt,ImageToVIewActivity.class).putExtra("path",imagePaths.get(position).getImagePath()));
                        }
                    }
                }
            }
        });
    }

    /**
     * 添加图片s(从外部)
     * @param list
     */
    public void addImagePaths(List<Bean> list){
        for (int i = 0; i < list.size(); i++) {
            imagePaths.add(imagePaths.size() - 1,list.get(i));
        }
        if (imagePaths.size() > maxCount){
            imagePaths.remove(addBean);
        }
        imageSelectAdapter.notifyDataSetChanged();
    }

    /**
     * 添加图片(从外部)
     * @param bean
     */
    public void addImagePath(Bean bean){
        imagePaths.add(imagePaths.size() - 1,bean);
        if (imagePaths.size() > maxCount){
            imagePaths.remove(addBean);
        }
        imageSelectAdapter.notifyDataSetChanged();
    }

    public void addImagePath(Bean bean,int position){
        imagePaths.add(position,bean);
        if (imagePaths.size() > maxCount){
            imagePaths.remove(addBean);
        }
        imageSelectAdapter.notifyDataSetChanged();
    }

    /**
     * 更新
     * @param bean
     * @param position
     */
    public void updateImagePath(Bean bean,int position){
        imagePaths.remove(position);
        imagePaths.add(position,bean);
        imageSelectAdapter.notifyDataSetChanged();
    }

    /**
     * 获取所有图片地址
     * @return
     */
    public List<Bean> getImagePaths() {
        returnPathBeans.clear();
        returnPathBeans.addAll(imagePaths);
        returnPathBeans.remove(addBean);
        return returnPathBeans;
    }

    /**
     * 设置最大图片数
     * @param maxCount：大于0
     */
    public ImageSelectionView2 setMaxCount(int maxCount) {
        if (maxCount < 1) maxCount = 1;
        this.maxCount = maxCount;
        return this;
    }
    /**
     * 设置图片加载类
     * @param loader
     */
    public ImageSelectionView2 setImageLoader(ImageLoader loader) {
        imageLoader = loader;
        return this;
    }
    public ImageSelectionView2 setImageToView(ImageToView toView){
        imageToView = toView;
        return this;
    }
    /**
     *  设置图片选择
     * @param imageChoose
     */
    public ImageSelectionView2 setImageChoose(ImageChoose imageChoose) {
        this.imageChoose = imageChoose;
        return this;
    }

    /**
     * 设置加号的图片
     * @param rus
     */
    public ImageSelectionView2 setImageAddResource(int rus){
        imageAddRous = rus;
        return this;
    }

    public ImageSelectionView2 setVideoAddRous(int videoAddRous) {
        this.videoAddRous = videoAddRous;
        return this;
    }

    /**
     * 设置关闭（删除图片）
     * @param rus
     */
    public ImageSelectionView2 setCloseResource(int rus){
        closeRous = rus;
        return this;
    }

    /**
     *
     * @param scaletype
     */
    public ImageSelectionView2 setScaleType(ImageView.ScaleType scaletype){
        scaleType = scaletype;
        return  this;
    }

    /**
     *
     * @param numColumns
     */
    public ImageSelectionView2 setNumColumn(int numColumns) {
        super.setNumColumns(numColumns);
        numColumns2 = numColumns;
        return this;
    }

    public void clear(){
        imagePaths.clear();
        imagePaths.add(addBean);
        imageSelectAdapter.notifyDataSetChanged();
    }
    public void setSelectionListener(ImageSelectionListener2 selectionListener) {
        this.selectionListener = selectionListener;
    }

    public void setRemoveListener(RemoveListener listener) {
        this.removeListener = listener;
        imageSelectAdapter.setRemoveListener(removeListener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
