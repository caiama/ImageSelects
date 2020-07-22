package com.dxngxhl.imgselector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dxngxhl.imgselector.a.ImgSelectorLoader;
import com.dxngxhl.imgselector.itfc.ImgSelectionListener;

import java.util.List;

/**
 * Created by Ma
 */
public class ImgSelectorAdapter extends BaseAdapter {
    private List<String> images;
    private Context context;
    private ImgSelectionListener mSelectionListener;
    private ImgSelectorLoader imageLoader;
    private ImageView.ScaleType scaleType;
    private int imageAddRous,closeRous;
    int width,imgWidth,numColumns;
    boolean isNoInit = true;

    public ImgSelectorAdapter(Context context,List<String> images, ImgSelectorLoader imageLoader,
                              ImageView.ScaleType scaleType, int imageAddRous,
                              int closeRous, int width, int numColumns) {
        this.images = images;
        this.context = context;
        this.imageLoader = imageLoader;
        this.scaleType = scaleType;
        this.imageAddRous = imageAddRous;
        this.closeRous = closeRous;
        this.width = width;
        this.numColumns = numColumns;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_imgselector,null);
            viewHolder.imageView = convertView.findViewById(R.id.item_imgselector_image);
            viewHolder.imgClose = convertView.findViewById(R.id.item_imgselector_close);
            convertView.setTag(viewHolder);
        }
        if (convertView != null && isNoInit && parent.getWidth() > 0){
            imgWidth = (parent.getWidth() - dip2px(context,10 * numColumns * 2)) / numColumns;
            isNoInit = false;
        }
        if (!isNoInit && convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.imageView.getLayoutParams().height = imgWidth;
            viewHolder.imageView.getLayoutParams().width = imgWidth;
            if (scaleType != null){
                viewHolder.imageView.setScaleType(scaleType);
            }
            viewHolder.imageView.setImageResource(imageAddRous);
            //
            viewHolder.imgClose.setImageResource(closeRous);
            if (ImageSelectorView.SELECT_TAG.equals(images.get(position))){
                viewHolder.imgClose.setVisibility(View.GONE);
            }else {
                viewHolder.imgClose.setVisibility(View.VISIBLE);
            }
            viewHolder.imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    images.remove(position);
                    images.remove(ImageSelectorView.SELECT_TAG);
                    images.add(ImageSelectorView.SELECT_TAG);
                    notifyDataSetChanged();
                }
            });
            if (imageLoader != null){
                imageLoader.imageLoad(context,ImageSelectorView.SELECT_TAG.equals(images.get(position))?imageAddRous:images.get(position),viewHolder.imageView);
            }
        }


        return convertView;
    }

    public void setSelectionListener(ImgSelectionListener selectionListener) {
        mSelectionListener = selectionListener;
    }

    class ViewHolder{
        ImageView imageView,imgClose;
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
