package com.dxngxhl.imageselection;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Ma
 * on 2019/7/12
 */
public class ImageSelectAdapter extends BaseAdapter {
    private List<String> images;
    private Context context;
    private ImageLoader imageLoader;
    private ImageView.ScaleType scaleType;
    private int imageAddRous,closeRous;
    int width,imageWidth,numColumns;
    boolean isNoInit = true;

    public ImageSelectAdapter(List<String> images,
                              Context context,
                              ImageLoader imageLoader,
                              ImageView.ScaleType scaleType,
                              int imageAddRous,
                              int closeRous,
                              int width,
                              int numColumns) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_image_select,null);
            viewHolder.imageView = convertView.findViewById(R.id.item_iamge_select_image);
            viewHolder.imageClose = convertView.findViewById(R.id.item_iamge_select_close);
            convertView.setTag(viewHolder);
        }
        if (convertView != null && isNoInit && parent.getWidth() > 0){
            imageWidth = (parent.getWidth() - dip2px(context,10 * numColumns * 2)) / numColumns;
            isNoInit = false;
        }
        if (!isNoInit && convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.imageView.getLayoutParams().height = imageWidth;
            viewHolder.imageView.getLayoutParams().width = imageWidth;
            if (scaleType != null){
                viewHolder.imageView.setScaleType(scaleType);
            }
            viewHolder.imageView.setImageResource(imageAddRous);
            //
            viewHolder.imageClose.setImageResource(closeRous);
            if (ImageSelectionView.SELECTION_TAG.equals(images.get(position))){
                viewHolder.imageClose.setVisibility(View.GONE);
            }else {
                viewHolder.imageClose.setVisibility(View.VISIBLE);
            }
            viewHolder.imageClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    images.remove(position);
                    images.remove(ImageSelectionView.SELECTION_TAG);
                    images.add(ImageSelectionView.SELECTION_TAG);
                    notifyDataSetChanged();
                }
            });
            if (imageLoader != null){
                imageLoader.imageLoad(context,ImageSelectionView.SELECTION_TAG.equals(images.get(position))?imageAddRous:images.get(position),viewHolder.imageView);
            }
        }


        return convertView;
    }
    class ViewHolder{
        ImageView imageView,imageClose;
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
